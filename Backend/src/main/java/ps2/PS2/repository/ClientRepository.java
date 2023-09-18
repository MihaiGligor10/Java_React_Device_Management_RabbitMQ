package ps2.PS2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ps2.PS2.model.Client;
import ps2.PS2.model.Device;

import java.util.ArrayList;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findClientByEmailAndPassword(String email,  String password);
    Client findClientByEmail(String email);
    Boolean existsByEmail(String e);
    Client findById(int id);
    void deleteClientByEmail(String e);
}