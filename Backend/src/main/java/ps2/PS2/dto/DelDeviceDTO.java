package ps2.PS2.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class DelDeviceDTO {
    private String name;
    private int id;
    private int idc;
}
