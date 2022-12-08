package com.example.demotestredis.service;

import com.example.demotestredis.dto.AppUserDTO;
import com.example.demotestredis.model.AppUser;

import java.util.List;

public interface AppUserService {

    AppUserDTO addNewUser(AppUserDTO appUserDTO);
    List<AppUserDTO> getAllUsers();
    AppUser updateUser(AppUserDTO appUserDTO);
    String deleteUser(int userID);
    AppUserDTO findUser(int userID);

}
