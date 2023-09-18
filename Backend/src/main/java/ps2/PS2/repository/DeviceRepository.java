package ps2.PS2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ps2.PS2.model.Device;

@Repository
public interface  DeviceRepository extends JpaRepository<Device,Long> {

    Device findById(int id);
    Device findByName(String name);
    void deleteByNameAndId(String name,int id);
}
