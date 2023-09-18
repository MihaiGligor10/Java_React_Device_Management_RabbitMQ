package ps2.PS2.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode
public class Device {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
   // @UniqueElements
    private String name;
    private String location;
    @Column(length = 65555)
    private String description;
    private float maxConsumption;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Measurement> measurementList = new ArrayList<>();


    public Device(String name, String location, String description, float maxConsumption) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.maxConsumption = maxConsumption;
    }


    public Device(int i, String s, String s1, String s2, int i1) {
    }
}
