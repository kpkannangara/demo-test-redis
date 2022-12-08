package com.example.demotestredis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AppUserDTO implements Serializable {

    private int user_id;
    private String name;
    private String email;

}
