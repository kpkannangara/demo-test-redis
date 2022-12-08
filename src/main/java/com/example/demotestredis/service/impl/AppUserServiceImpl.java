package com.example.demotestredis.service.impl;

import com.example.demotestredis.dto.AppUserDTO;
import com.example.demotestredis.model.AppUser;
import com.example.demotestredis.repository.AppUserRepository;
import com.example.demotestredis.service.AppUserService;
import com.example.demotestredis.util.ValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository apppUserRepository;

    @Override
    public AppUserDTO addNewUser(AppUserDTO appUserDTO) {
        AppUser appUser = new AppUser();
        appUser.setName(appUserDTO.getName());
        appUser.setEmail(appUserDTO.getEmail());
        apppUserRepository.save(appUser);
        return appUserDTO;
    }

    @Override
    public List<AppUserDTO> getAllUsers(){
        List<AppUser> appUsers = apppUserRepository.findAll();
        return appUsers.stream().map(ValueMapper::appUserToDto).collect(Collectors.toList());
    }

    @Override
//    @CachePut(value = "appUser",key = "#result.user_id")
//    can't use @CachePut this time reason - for this you need to send #result.user_id as a key in @Cacheable
    @CacheEvict(value = "appUser",key = "#result.user_id")
    public AppUser updateUser(AppUserDTO appUserDTO) {
        AppUser appUser = new AppUser();
        appUser.setUser_id(appUserDTO.getUser_id());
        appUser.setName(appUserDTO.getName());
        appUser.setEmail(appUserDTO.getEmail());
        apppUserRepository.save(appUser);
        return appUser;

    }

    @Override
    @CacheEvict(value = "appUser",key = "#userID")
    public String deleteUser(int userID) {
        Optional<AppUser> appUser = apppUserRepository.findById(userID);
        if (appUser.isPresent()){
            apppUserRepository.deleteById(userID);
            return "User deleted success!!";
        }
        else{
            return "User Not Found With Id - " +userID;
        }
    }

    @Override
    @Cacheable(value = "appUser",key = "#userID",unless="#result == null")
//  unless="#result == null OR #result.name == 'Piyumi'" - we can use "OR" for give two conditions in result with unless=.
    public AppUserDTO findUser(int userID) {
        Optional<AppUser> appUser = apppUserRepository.findById(userID);
        if (appUser.isPresent()) {
            AppUserDTO appUserDTO = new AppUserDTO();
            appUserDTO.setUser_id(appUser.get().getUser_id());
            appUserDTO.setName(appUser.get().getName());
            appUserDTO.setEmail(appUser.get().getEmail());
            return appUserDTO;
        }
        return  null;
    }


}
