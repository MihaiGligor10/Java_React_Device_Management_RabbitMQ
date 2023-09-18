package ps2.PS2.mapper;

import org.springframework.http.HttpStatus;
import ps2.PS2.dto.AuthDTO;
import ps2.PS2.exceptions.ApiExceptionResponse;
import ps2.PS2.model.Client;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ClientMapper {
    public static List<AuthDTO> mapClientToAuthDTO(List<Client> clients) throws ApiExceptionResponse {
        List<AuthDTO> dtos=new LinkedList<>();
        for(Client client:clients){
            if(client.getEmail()==null){
                throw ApiExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message("No email for: "+client.getEmail()).errors(Collections.singletonList("error.email.not_found")).build();
            }
            dtos.add(AuthDTO.builder().email(client.getEmail()).password(client.getPassword()).build());
        }
        return dtos;
    }
}
