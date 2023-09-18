package ps2.PS2.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString

public class DeviceDTO {
    //private int id;
    private String name;
    private String location;
    private String description;
    private float maxConsumption;
}
