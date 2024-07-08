package com.cursosdedesarrollo.apirestjpa.entities.relations.onetooneuni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unoaunouni")
public class UserUniController {
    @Autowired
    private UserUniRepository userRepository;
    @Autowired
    private UserProfileUniRepository userProfileRepository;

    @GetMapping("/")
    public List<User> index (){
        return this.userRepository.findAll();
    }

    @PostMapping("/")
    public User postUser (@RequestBody User user){
        return this.userRepository.save(user);
    }

    @PostMapping("/{id}/userprofile")
    public User postUserProfile (@PathVariable Long id, @RequestBody UserProfile userProfile){
        User user = this.userRepository.findById(id).orElse(new User());
        user.setUserProfile(userProfile);
        this.userRepository.save(user);
        //log.info(user.toString());
        return user;
    }
}
