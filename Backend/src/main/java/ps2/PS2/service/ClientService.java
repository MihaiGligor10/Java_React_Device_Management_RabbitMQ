package ps2.PS2.service;

import org.springframework.stereotype.Component;
import ps2.PS2.dto.AuthDTO;
import ps2.PS2.model.Client;
import ps2.PS2.model.Device;

import java.util.List;


@Component
public interface ClientService {
Client addClient(AuthDTO client) throws Exception;
void deleteClientByEmail(String e);
Client validateClient(AuthDTO authDTO) throws Exception;
Boolean doesEmailExists(String email);
Client findClientById(int id);
Client logout(int id );
List<Device> findDevicesByClient(int client);

}
