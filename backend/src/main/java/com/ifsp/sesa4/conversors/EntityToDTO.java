package com.ifsp.sesa4.conversors;

import com.ifsp.sesa4.DTOs.UserInfoDTO;
import com.ifsp.sesa4.entities.User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class EntityToDTO {

    public UserInfoDTO convert(User user){
        return new UserInfoDTO(user);
    }

}
