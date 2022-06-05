package com.ddwarf.tictactoe.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ddwarf.tictactoe.core.ttt.FieldState;
import com.ddwarf.tictactoe.core.ttt.TicTacToeEngine;

@RestController
@RequestMapping(value = "/game")
public class TicTacToeController
{
    private Set<SseEmitter> sseEmitters = new HashSet<SseEmitter>();
    HashMap<String, Game> games = new HashMap<>();
    /* @Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomService chatRoomService; */
    /* @CrossOrigin
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());

        ChatMessage saved = chatMessageService.save(chatMessage);
        
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(),"/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId(),
                        saved.getSenderName()));
    } */


    @CrossOrigin
    @PostMapping("/new_game")
    GameItemResponse createNewGame(@RequestBody NewGameResponse settings) {
        UUID uuid = UUID.randomUUID();
        Game game = new Game();
        game.engine.generateField(settings.height, settings.width);
        System.out.println(settings.login + " " + settings.width);
        game.player1 = settings.login;
        game.queue = game.player1;
        games.put(uuid.toString(), game);

        GameItemResponse resp = new GameItemResponse(
            game.player1,
            uuid.toString(),
            true
        );
        // sendMessage(resp);
        return resp;
    }

    @CrossOrigin
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

    @CrossOrigin
    @PostMapping("/load_game")
    LoadGameResponse loadGame(@RequestBody LoadGameBody settings)
    {
        Game game = games.get(settings.uuid);
        int fieldType = FieldState.CROSSES;
        if(game == null) return new LoadGameResponse("", "", new int[1][1], 0);
        if(!game.player1.equals(settings.login))
        {
            game.player2 = settings.login;
            fieldType = FieldState.ZEROS;
        }
        return new LoadGameResponse(game.engine.state, game.queue, game.engine.field, fieldType);
    }

    @CrossOrigin
    @PostMapping("/click_by_field")
    ClickByFieldResponse loadGame(@RequestBody ClickByFieldBody body)
    {
        Game game = games.get(body.uuid);
        boolean error = true;
        if(body.login.equals(game.queue)
            && game.engine.field[body.i][body.j] == FieldState.EMPTY)
        {
            if(body.login.equals(game.player1))
            {
                game.engine.insertCrosse(body.i, body.j);
                game.queue = game.player2;
                sendMessage(new ClickByFieldEmit(FieldState.CROSSES, body.i, body.j));
            }
            else {
                game.engine.insertZero(body.i, body.j);
                game.queue = game.player1;
                sendMessage(new ClickByFieldEmit(FieldState.ZEROS, body.i, body.j));
            }
            error = false;
        }
        return new ClickByFieldResponse(game.engine.state, error);
    }

    @Scheduled(fixedDelay = 2*1000)
    private void sendMessage(Object object)
    {
        if(sseEmitters.isEmpty())
            System.out.println("No active Emitters ");

        System.out.println("Sent Messages : " + object);

        sseEmitters.forEach(emitter -> {
            if (null != emitter)
                try {
                    System.out.println("Timeout : "+ emitter.getTimeout());
                    emitter.send(object);
                    emitter.complete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
    }
}
class NewGameResponse
{
    public String login;
    public int width;
    public int height;
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
    public LoadGameResponse(String state, String queue, int[][] field, int fieldType)
    {
        this.queue = queue;
        this.field = field;
        this.fieldType = fieldType;
    }
}
class ClickByFieldBody
{
    public String login;
    public String uuid;
    public int i;
    public int j;
}
class ClickByFieldEmit
{
    public int fieldType;
    public int i;
    public int j;
    public ClickByFieldEmit(int fieldType, int i, int j)
    {
        this.fieldType = fieldType;
        this.i = i;
        this.j = j;
    }
}
class ClickByFieldResponse
{
    public String state;
    public boolean error;
    ClickByFieldResponse(String state, boolean error)
    {
        this.state = state;
        this.error = error;
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
class Game
{
    public String player1;
    public String player2;
    public String queue;
    public TicTacToeEngine engine = new TicTacToeEngine();
}