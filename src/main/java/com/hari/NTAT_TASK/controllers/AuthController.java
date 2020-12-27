package com.hari.NTAT_TASK.controllers;

import com.hari.NTAT_TASK.models.*;
import com.hari.NTAT_TASK.repository.UserRepository;
import com.hari.NTAT_TASK.services.MyUserDetailsService;
import com.hari.NTAT_TASK.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.Dictionary;
import java.util.List;
import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping("/current_user")
    public Optional<Users> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> user = repository.findByusername(auth.getName());
        if(user.isPresent()) {
            user.get().setPassword(null);
            user.get().setUpi_pin(null);
        }
        return user;
    }

    @GetMapping("/users")
    public List<Users> getRemainingUsers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return repository.findByUsernameNot(auth.getName());
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e){
            return ResponseEntity.status(401).body(new AuthenticationResponse(null,"Invalid username or password"));
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt,null));
    }

    @RequestMapping(value = "/check_upi", method = RequestMethod.POST)
    public ResponseEntity<?> CheckUpi(@RequestBody UpiPinRequest upiPinRequest){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> user = repository.findByusername(auth.getName());
        if(user.isEmpty())
            return ResponseEntity.status(401).body(new UpiPinResponse(false));
        if(user.get().getUpi_pin().equals(upiPinRequest.getPin()))
            return ResponseEntity.ok(new UpiPinResponse(true));
        return ResponseEntity.status(401).body(new UpiPinResponse(false));
    }
}
