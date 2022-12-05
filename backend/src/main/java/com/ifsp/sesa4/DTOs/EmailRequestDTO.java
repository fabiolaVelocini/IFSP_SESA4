package com.ifsp.sesa4.DTOs;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailRequestDTO {

    public String toEmail;
    public String body;
    //public Boolean isHtml;
    public String subject;

}
