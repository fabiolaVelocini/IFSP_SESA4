package com.ifsp.sesa4.DTOs;

import com.ifsp.sesa4.entities.User;

public class UserInfoDTO {

    public Long id;

    public String name;

    public String username;

    public UserInfoDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
    }
}
