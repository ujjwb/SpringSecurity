package com.assignments.org.demo.controllers;

import com.assignments.org.demo.dto.LoginDto;
import com.assignments.org.demo.dto.RegisterDto;
import com.assignments.org.demo.entities.Role;
import com.assignments.org.demo.entities.UserEntity;
import com.assignments.org.demo.repositories.RoleRepository;
import com.assignments.org.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/myapp")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/start")
    public String startApp(){
        Role r1=new Role();
        r1.setName("USER");
        roleRepository.save(r1);
        Role r2=new Role();
        r2.setName("ADMIN");
        roleRepository.save(r1);
        return "Done";
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if(userRepository.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }
        UserEntity user=new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles=roleRepository.findByName("USER").get();
        user.setRoles(Arrays.asList(roles));
        userRepository.save(user);
        return new ResponseEntity<>("User Registered",HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){

        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));
        if(userRepository.existsByUsername(loginDto.getUsername())){
            userRepository.findByUsername(loginDto.getUsername());
        }
        else{
            return new ResponseEntity<>("Username not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Logged In",HttpStatus.ACCEPTED);
    }

}
