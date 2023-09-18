package ps2.PS2.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode
public class Measurement {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;
    private float valueConsumed;
    private Timestamp time;
    Boolean taken ;


    public Measurement(float valueConsumed, Timestamp time , Boolean taken) {
        this.valueConsumed = valueConsumed;
        this.time = time;
        this.taken = taken;
    }
}
