package ps2.PS2.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
//import ps2.PS2.config.NotificationEndPoint;
import ps2.PS2.dto.DelDeviceDTO;
import ps2.PS2.dto.DeviceDTO;
import ps2.PS2.dto.LDTC;
import ps2.PS2.model.Client;
import ps2.PS2.model.Device;
import ps2.PS2.repository.ClientRepository;
import ps2.PS2.repository.DeviceRepository;
import ps2.PS2.service.DeviceService;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    private final Device device0 = new Device(0,"0","0","0",0);
   // private final SimpMessagingTemplate template;
    private final ClientRepository clientRepository;
    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl( ClientRepository clientRepository, DeviceRepository deviceRepository) {

        this.clientRepository = clientRepository;
        this.deviceRepository = deviceRepository;
    }


    @Override
    @Transactional
    public Device addDevice(DeviceDTO device) {
        if(doesDeviceExist(device.getName()))
        {
            return device0;
        }else
        {
            //this.template.convertAndSend(NotificationEndPoint.MOVIE_ADDED,"The device " + device.getName() +" has been added");

            Device d= new Device(device.getName(),device.getLocation(),device.getDescription(),device.getMaxConsumption());

            return deviceRepository.save(d);
        }
    }

    @Override
    public Boolean doesDeviceExist(String name) {
        return deviceRepository.findByName(name)!= null;
    }

    @Override
    @Transactional
    public void deleteDevice(DelDeviceDTO dto) {
        if(dto.getIdc()==0)
        {
            try{
                int i = dto.getId();
                String s = dto.getName();
                System.out.println(s +" <+> " +  i);

                deviceRepository.deleteByNameAndId(dto.getName(),dto.getId());
            }catch (Exception e)
            {
                System.out.println("Device might not exist");
                System.out.println(e);
            }
        }else
        {
            Client client;
            Device device;
            if(clientRepository.findById(dto.getIdc())==null)
            {
                System.out.println("client not found");
                return;
            }
            else
            {
                client = clientRepository.findById(dto.getIdc());
            }
            if(deviceRepository.findById(dto.getId())==null)
            {
                System.out.println("device not found");
                return;
            }
            else
            {
                device = deviceRepository.findById(dto.getId());
            }

            List<Device> list = client.getDeviceLists();
            list.remove(device);
            client.setDeviceLists(list);
            clientRepository.save(client);
            try{
                int i = dto.getId();
                String s = dto.getName();
                System.out.println(s +" <+> " +  i);

                deviceRepository.deleteByNameAndId(dto.getName(),dto.getId());
            }catch (Exception e)
            {
                System.out.println("Device might not exist");
                System.out.println(e);
            }
        }

    }

    @Override
    public Device findById(int id) {
        if(deviceRepository.findById(id)==null)
        {
            return null;
        }else
        {
            return deviceRepository.findById(id);
        }
    }

    @Override
    public void linkDeviceToClient(LDTC dto) {
        Client client;
        Device device;
        if(clientRepository.findById(dto.getClient())==null)
        {
            System.out.println("client not found");
            return;
        }
        else
        {
            client = clientRepository.findById(dto.getClient());
        }
        if(deviceRepository.findById(dto.getDevice())==null)
        {
            System.out.println("device not found");
            return;
        }
        else
        {
            device = deviceRepository.findById(dto.getDevice());
        }

        List<Device> list = client.getDeviceLists();
        list.add(device);
        client.setDeviceLists(list);
        clientRepository.save(client);
    }

}
