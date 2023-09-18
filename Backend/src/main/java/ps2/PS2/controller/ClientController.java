package ps2.PS2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ps2.PS2.service.impl.ClientServiceImpl;

@CrossOrigin
@RestController
public class ClientController {

    private final ClientServiceImpl clientService;

    public ClientController( ClientServiceImpl clientService) {
        this.clientService = clientService;
    }


    @RequestMapping("/deleteClient")
    @PostMapping
    public ResponseEntity<?> deleteClient(@RequestBody String email){
        System.out.println(email+" for client delete");
        try
        {
            clientService.deleteClientByEmail(email);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/getDevicesByClient")
    @PostMapping
    public ResponseEntity<?> getDevicesByClient(@RequestBody int client){
        System.out.println(client + "for seeing devices ");
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findDevicesByClient(client));

    }



}

