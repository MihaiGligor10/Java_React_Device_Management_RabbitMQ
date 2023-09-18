package ps2.PS2.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class ChartInfoDTO {
    int hour;
    float consumption;
}
