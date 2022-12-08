package com.example.demotestredis.util;

import com.example.demotestredis.dto.AppUserDTO;
import com.example.demotestredis.model.AppUser;

public class ValueMapper {
    public static AppUserDTO appUserToDto(AppUser appUser){
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setUser_id(appUser.getUser_id());
        appUserDTO.setName(appUser.getName());
        appUserDTO.setEmail(appUser.getEmail());
        return appUserDTO;
    }

}
