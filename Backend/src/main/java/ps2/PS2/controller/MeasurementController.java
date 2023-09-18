package ps2.PS2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ps2.PS2.dto.ChartInit;
import ps2.PS2.dto.DeviceDTO;
import ps2.PS2.service.MeasurementService;

import java.sql.Timestamp;
import java.util.Date;

@CrossOrigin
@RestController
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }


    @RequestMapping("/generateMeasures")
    @PostMapping
    public ResponseEntity<?> generateMeasures(@RequestBody int device){

        try
        {
            System.out.println("added measures for device " + device);
            measurementService.generateMeasurements(device);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/generateDataForChart")
    @PostMapping
    public ResponseEntity<?> generateDataForChart(@RequestBody ChartInit dto){
            System.out.println(dto + " maybe?");
        return ResponseEntity.status(HttpStatus.OK).body(measurementService.generateDataForChart(dto));

    }
}
