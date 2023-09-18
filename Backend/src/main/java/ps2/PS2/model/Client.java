package ps2.PS2.model;


import jakarta.persistence.*;
import lombok.*;
import ps2.PS2.validators.Password;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode

public class Client {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Email(message = "invalid format",
            regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Password
    private String password;
    private boolean  logged=false;
    private int role;


    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    //@OneToMany(fetch = FetchType.LAZY,  cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
    private List<Device> deviceLists = new ArrayList<>();

    public Client(String email, String password, int role) {
        this.email=email;
        this.password = password;
        this.role=role;
    }



    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}