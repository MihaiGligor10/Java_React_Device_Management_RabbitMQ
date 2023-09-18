package ps2.PS2.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class AuthDTO {
    private String email;
    private String password;
    private int role;
}
