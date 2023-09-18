package ps2.PS2.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class ChartInit {
    Timestamp t;
    int device;
}
