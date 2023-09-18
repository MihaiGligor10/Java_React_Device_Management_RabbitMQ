package ps2.PS2.dto;

import lombok.*;

import java.lang.annotation.Target;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageDTO {
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;
}
