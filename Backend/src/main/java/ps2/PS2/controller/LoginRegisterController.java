package ps2.PS2.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ps2.PS2.dto.AuthDTO;
import ps2.PS2.service.ClientService;

@CrossOrigin
@RestController
public class LoginRegisterController {

    private final ClientService clientService;

    public LoginRegisterController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping("/login")
    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthDTO auth) throws Exception {
        System.out.println(auth + " for login");
        return ResponseEntity.status(HttpStatus.OK).body(clientService.validateClient(auth));

    }

    @RequestMapping("/logout")
    @PostMapping
    public ResponseEntity<?> logout(@RequestBody int id){
        System.out.println(id + " for logout");
        return ResponseEntity.status(HttpStatus.OK).body(clientService.logout(id));

    }


    @RequestMapping("/register")
    @PostMapping
    public ResponseEntity<?> register(@RequestBody AuthDTO authDTO) throws Exception {
        System.out.println(authDTO+" for register");
        return ResponseEntity.status(HttpStatus.OK).body(clientService.addClient(authDTO));
    }
}
