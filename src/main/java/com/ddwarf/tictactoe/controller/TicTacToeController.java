package com.ddwarf.tictactoe.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import com.ddwarf.tictactoe.core.ttt.FieldState;
import com.ddwarf.tictactoe.core.ttt.GameState;
import com.ddwarf.tictactoe.core.ttt.ai.MainAI;
import com.ddwarf.tictactoe.core.ttt.classes.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/game")
public class TicTacToeController
{
    Logger logger = LoggerFactory.getLogger("Tic-Tac-Toe");
    
    HashMap<String, Game> games = new HashMap<>();

    @Autowired private SimpMessagingTemplate messagingTemplate;

    /**
     * Создать новую игру
     * 
     * Передается логин игрока, который хочет создать.
     * Высота, ширина, условия окончания игры и режим игры(с онлайн игроком или с ботом).
     * Игрок, который создает игру ходит первым и играет за крестики
     */
    @PostMapping("/new_game")
    GameItemResponse createNewGame(@RequestBody NewGameResponse settings) {
        UUID uuid = UUID.randomUUID();
        Game game = new Game();
        game.engine.generateField(settings.height, settings.width);
        game.engine.condition = settings.condition;
        game.player1 = settings.login;
        game.queue = game.player1;
        games.put(uuid.toString(), game);

        if(!settings.mode.equals("Online")) {
            game.playerAI = MainAI.getAI(settings.mode, game.engine.field, FieldState.ZEROS);
            game.player2 = settings.mode;
        }

        GameItemResponse resp = new GameItemResponse(
            game.player1,
            uuid.toString(),
            true
        );
        logger.info(game.player1 + " create new game - " + uuid);
        sendMessage("new-game", resp, "");
        return resp;
    }

    /**
     * Удалить игру
     * 
     */
    @PostMapping("/remove_game")
    ResponseEntity<Object> removeGame(@RequestBody LoadGameBody settings) {
        Game game = games.get(settings.uuid);
        if(game == null) 
            return ResponseTTTStatus.getMessage("Вы пытаетесь удалить несуществующую игру!", HttpStatus.INTERNAL_SERVER_ERROR);
        if(game.player1.equals(settings.login))
            games.remove(settings.uuid);
        else return ResponseTTTStatus.getMessage("У вас нет прав для удаления игры!", HttpStatus.INTERNAL_SERVER_ERROR);

        logger.info(game.player1 + " remove game - " + settings.uuid);
        sendMessage("remove-game", settings, "");
        return ResponseTTTStatus.getMessage("Игра " + settings.uuid + " была удалена!", HttpStatus.OK);
    }

    /**
     * Получить список доступных игр
     */
    @PostMapping("/get_game_list")
    ArrayList<GameItemResponse> getGameList()
    {
        ArrayList<GameItemResponse> allGames = new ArrayList<>();
        for(Entry<String, Game> entry: games.entrySet())
        {
            Game game = entry.getValue();
            allGames.add(new GameItemResponse(
                game.player1,
                entry.getKey(),
                game.player2 != ""
            ));
        }
        return allGames;
    }

    /**
     * Получить список доступных ботов
     */
    @PostMapping("/get_ai_list")
    String[] getAIList()
    {
        return MainAI.allAI;
    }

    /**
     * Подключиться к игре
     */
    @PostMapping("/load_game")
    ResponseEntity<Object> loadGame(@RequestBody LoadGameBody settings)
    {
        Game game = games.get(settings.uuid);
        int fieldType = FieldState.CROSSES;
        if(game == null) {
            return ResponseTTTStatus.getMessage("Игра " + settings.uuid + " была удалена!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Добавление второго игрока
        // Первый кто подключится тот второй игрок
        if(!game.player1.equals(settings.login) && game.player2 == null)
        {
            game.player2 = settings.login;
            if(game.queue == null) game.queue = game.player2;
            fieldType = FieldState.ZEROS;
        }
        // Если подключается наблюдатель выдать сообщение
        if(!game.player1.equals(settings.login) && !game.player2.equals(settings.login)) {
            logger.info("User " + settings.login + " connect to game " + settings.uuid + " as observer");
        }
        else {
            logger.info("User " + settings.login + " connect to game " + settings.uuid);
        }
        return ResponseTTTStatus.getResponseEntity(
            new LoadGameResponse(
                game.engine.state,
                game.queue,
                game.engine.field, 
                fieldType
            )
        );
    }

    @PostMapping("/click_by_field")
    ResponseEntity<Object> clickByField(@RequestBody ClickByFieldBody body)
    {
        Game game = games.get(body.uuid);
        ClickByFieldEmit emit;
        if(game != null
            && game.engine.state.equals(GameState.GAME_BEGIN)
            && body.login.equals(game.queue)
            && game.engine.field[body.i][body.j] == FieldState.EMPTY)
        {
            logger.info(body.login + " click by field [" + body.i + ", " + body.j + "]");
            if(body.login.equals(game.player1))
            {
                game.engine.insertCrosse(body.i, body.j);
                game.queue = game.player2;
                emit = new ClickByFieldEmit(FieldState.CROSSES, body.i, body.j, game.engine.state, game.queue);
                sendMessage("click-by-field", emit, body.uuid);
                logger.info("sendMessage by CROSSES, next step " + game.queue);

                if(game.playerAI != null) {
                    ClickByFieldEmit position = game.playerAI.getNextClick();
                    if (position == null) { // временный фикс проблемы 
                        logger.info("Bot poshol popit chaiy");
                        return ResponseTTTStatus.getMessage("Бот пошел попить чаю!", HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    else {
                        game.engine.insertZero(position.i, position.j);
                        game.queue = game.player1;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {}
                        sendMessage("click-by-field", new ClickByFieldEmit(FieldState.ZEROS, position.i, position.j, game.engine.state, game.queue), body.uuid);
                        logger.info(game.player2 + " click by field [" + position.i + ", " + position.j + "]");
                    }
                }
            }
            else {
                game.engine.insertZero(body.i, body.j);
                game.queue = game.player1;
                emit = new ClickByFieldEmit(FieldState.ZEROS, body.i, body.j, game.engine.state, game.queue);
                sendMessage("click-by-field", emit, body.uuid);
                logger.info("sendMessage by ZEROS, next step " + game.queue);
            }
        }
        else {
            return ResponseTTTStatus.getMessage("Вы пытаетесь сделать что-то не хорошее!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseTTTStatus.getResponseEntity(emit);
    }

    private void sendMessage(String name, Object data, String uuid)
    {
        GameMessage message = new GameMessage();
        message.name = name;
        message.data = data;
        messagingTemplate.convertAndSend("/game/events" + (uuid.equals("") ? "" : "/" + uuid), message);
    }
}

class NewGameResponse
{
    public String login;
    public int width;
    public int height;
    public int condition;
    public String mode;
}
class LoadGameBody
{
    public String login;
    public String uuid;
}
class LoadGameResponse
{
    public String queue;
    public int[][] field;
    public String state;
    public int fieldType;
    public String message;
    public LoadGameResponse(String state, String queue, int[][] field, int fieldType)
    {
        this.state = state;
        this.queue = queue;
        this.field = field;
        this.fieldType = fieldType;
    }
    public LoadGameResponse(String message)
    {
        this.message = message;
    }
}
class GameItemResponse
{
    public String name;
    public String uuid;
    public boolean open;
    GameItemResponse(String name, String uuid, boolean open)
    {
        this.name = name;
        this.uuid = uuid;
        this.open = open;
    }
}
class GameMessage
{
    public String name;
    public Object data;
}
class ResponseMessage
{
    public String message;
    ResponseMessage(String message) {
        this.message = message;
    }
}
class ResponseTTTStatus
{
    public static ResponseEntity<Object> getMessage(String message, HttpStatus status) {
        return new ResponseEntity<Object>(
            new ResponseMessage(message),
            new HttpHeaders(),
            status
        );
    }
    public static ResponseEntity<Object> getResponseEntity(Object object) {
        return new ResponseEntity<Object>(
            object,
            new HttpHeaders(),
            HttpStatus.OK
        );
    }
}