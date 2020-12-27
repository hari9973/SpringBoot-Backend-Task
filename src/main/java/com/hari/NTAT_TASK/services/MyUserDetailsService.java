package com.hari.NTAT_TASK.services;

import com.hari.NTAT_TASK.repository.UserRepository;
import com.hari.NTAT_TASK.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUser = usersRepository.findByusername(username);
        if(optionalUser.isPresent()) {
            Users users = optionalUser.get();
            return new User(users.getUsername(),users.getPassword(),new ArrayList<>());
        } else {
            System.out.println("username not found");
            throw new UsernameNotFoundException("User Name is not Found");
        }
    }
}
