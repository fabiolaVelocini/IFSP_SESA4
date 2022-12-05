package com.ifsp.sesa4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    private String recipient;
    private String content;
    private String subject;
    private String attachment;
    //tpiqspmihpzrzmti

}
