package com.ifsp.sesa4.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateUserDTO {

    public String username;

    public String name;

    public String email;

}
