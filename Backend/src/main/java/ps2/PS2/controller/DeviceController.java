package ps2.PS2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ps2.PS2.dto.AuthDTO;
import ps2.PS2.dto.DelDeviceDTO;
import ps2.PS2.dto.DeviceDTO;
import ps2.PS2.dto.LDTC;
import ps2.PS2.service.impl.DeviceServiceImpl;

@CrossOrigin
@RestController
public class DeviceController {

    private final DeviceServiceImpl deviceService;

    public DeviceController( DeviceServiceImpl deviceService) {
        this.deviceService = deviceService;
    }

    @RequestMapping("/addDevice")
    @PostMapping
    public ResponseEntity<?> addDevice(@RequestBody DeviceDTO deviceDTO){
        System.out.println(deviceDTO + "for adding device");
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.addDevice(deviceDTO));
    }


   /* @RequestMapping("/getAllMovies")
    @PostMapping
    public ResponseEntity<?> getAll(){
        System.out.println("get all movies");
        return ResponseEntity.status(HttpStatus.OK).body(movieService.findAllMovies());
    }*/

    @RequestMapping("/getByIdDevice")
    @PostMapping
    public ResponseEntity<?> getById(@RequestBody int id){
        System.out.println(id + "get device by id");
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.findById(id));
    }

    @RequestMapping("/deleteDevice")
    @PostMapping
    public ResponseEntity<?> deleteDevice(@RequestBody DelDeviceDTO mDTO){
        System.out.println(mDTO+" for deleting device");
        try
        {
            deviceService.deleteDevice(mDTO);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/linkClient")
    @PostMapping
    public ResponseEntity<?> linkClient(@RequestBody LDTC dto) throws Exception {
        System.out.println(dto+" for link");
        try
        {
            deviceService.linkDeviceToClient(dto);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }



}