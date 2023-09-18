package ps2.PS2.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ps2.PS2.dto.AuthDTO;
import ps2.PS2.model.Client;
import ps2.PS2.model.Device;
import ps2.PS2.repository.ClientRepository;
import ps2.PS2.service.ClientService;
import ps2.PS2.utils.Encoder;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    Client client0=new Client("0","0",0);
   // @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;

    }




    @Override
    @Transactional
    public void deleteClientByEmail(String e) {
        try {
            clientRepository.deleteClientByEmail(e);
        }catch (Exception a)
        {
            System.out.println("error \n client might not exist");
        }
    }

    @Override
    public Client validateClient(AuthDTO authDTO) throws Exception {
        Encoder encoder = new Encoder();
        Client client =clientRepository.findClientByEmailAndPassword(authDTO.getEmail(), encoder.encrypt(authDTO.getPassword()));
        System.out.println(client.getPassword());
        client.setLogged(true);
        clientRepository.save(client);
        client.setPassword(encoder.decrypt(client.getPassword()));
            return client;
    }


    @Override
    public Boolean doesEmailExists(String email) {
           return clientRepository.existsByEmail(email);
    }

    @Override
    public Client addClient(AuthDTO client) throws Exception {
        Encoder encoder = new Encoder();
        System.out.println(client);
        if(doesEmailExists(client.getEmail()))
        {
            return client0;
        }else
        {
            Client c;
            if(client.getEmail().equals("admin@gmail.com") && client.getPassword().equals("admin000"))
            {
                c = new Client(client.getEmail(), encoder.encrypt(client.getPassword()), 1);
            }else
            {
                c = new Client(client.getEmail(), encoder.encrypt(client.getPassword()), 0);
            }
            System.out.println(c.getPassword());
            return clientRepository.save(c);

        }

    }

    @Override
    public Client findClientById(int id) {
        if(clientRepository.findById(id)==null)
        {
            return new Client("0","0",0);
        }
        else
        {
            return clientRepository.findById(id);
        }
    }

    @Override
    public Client logout(int id) {

        Client client=clientRepository.findById(id);
        client.setLogged(false);
        clientRepository.save(client);
        return client;
    }

    public List<Device> findDevicesByClient(int client) {
        if(clientRepository.findById(client)==null)
        {
            return null;
        }
        else
        {
            return clientRepository.findById(client).getDeviceLists();
        }
    }




}
