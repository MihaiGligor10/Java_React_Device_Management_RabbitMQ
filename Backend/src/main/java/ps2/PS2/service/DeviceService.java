package ps2.PS2.service;

import org.springframework.stereotype.Component;
import ps2.PS2.dto.DelDeviceDTO;
import ps2.PS2.dto.DeviceDTO;
import ps2.PS2.dto.LDTC;
import ps2.PS2.model.Device;

import java.util.List;

@Component
public interface DeviceService {

    Device addDevice(DeviceDTO movie);
    Boolean doesDeviceExist(String nume);
    void deleteDevice(DelDeviceDTO delMovieDTO);
    Device findById(int id);
    void linkDeviceToClient(LDTC dto);
}
