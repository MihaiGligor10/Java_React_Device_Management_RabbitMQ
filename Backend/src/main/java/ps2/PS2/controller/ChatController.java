package ps2.PS2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ps2.PS2.dto.AuthDTO;
import ps2.PS2.dto.MessageDTO;
import ps2.PS2.service.ClientService;

@CrossOrigin
@RestController
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpleMessagingTemplate;
    private final ClientService clientService;

    public ChatController(ClientService clientService) {
        this.clientService = clientService;
    }

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    private MessageDTO receivePublicMessage(@Payload MessageDTO messageDTO){
        return messageDTO;
    }

    @MessageMapping("/private-message")
    public MessageDTO receivePrivateMessage(@Payload MessageDTO messageDTO){
        simpleMessagingTemplate.convertAndSendToUser(messageDTO.getReceiverName(),"/private",messageDTO);
        return messageDTO;
    }

    @RequestMapping("/chatConnect")
    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthDTO auth) throws Exception {
        System.out.println(auth + " for login");
        return ResponseEntity.status(HttpStatus.OK).body(clientService.validateClient(auth));

    }
}
