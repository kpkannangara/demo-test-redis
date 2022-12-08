package com.example.demotestredis.controller;

import com.example.demotestredis.dto.AppUserDTO;
import com.example.demotestredis.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/getUsers")
    public ResponseEntity<?> getAllUsers() {
        List<AppUserDTO> allusers = appUserService.getAllUsers();
        if (allusers.isEmpty()) {
            return new ResponseEntity<>("Haven't any Users Yet", HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<List<AppUserDTO>>(allusers, HttpStatus.OK);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AppUserDTO appUserDTO){
        return new ResponseEntity<AppUserDTO>(appUserService.addNewUser(appUserDTO), HttpStatus.CREATED);

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody AppUserDTO appUserDTO){
        return new ResponseEntity<>("user updated" +"\n"+ appUserService.updateUser(appUserDTO), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId){
        return new ResponseEntity<String>(appUserService.deleteUser(userId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<?> getUsers(@PathVariable int userId) {
        AppUserDTO appUser = appUserService.findUser(userId);
        if (appUser==null) {
            return new ResponseEntity<>("Haven't any User With user_id : " +userId, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<AppUserDTO>(appUser, HttpStatus.FOUND);
        }
    }

}
