package com.ddwarf.tictactoe.controller;

import com.ddwarf.tictactoe.core.HappyTicket;
import org.springframework.web.bind.annotation.*;

@RestController
public class HappyController {
    HappyTicket happyTicket = new HappyTicket();

    @CrossOrigin
    @PostMapping("/happy")
    HappyTicketResponse generate(@RequestBody HappyTicketResponse ticket) {
        return new HappyTicketResponse(
                happyTicket.generate(ticket.number)
        );
    }
}

class HappyTicketResponse
{
    public int number;
    public HappyTicketResponse() {}
    public HappyTicketResponse(int number)
    {
        this.number = number;
    }
}
