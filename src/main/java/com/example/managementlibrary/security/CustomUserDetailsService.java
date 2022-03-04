package com.example.managementlibrary.security;



import com.example.managementlibrary.entity.User;
import com.example.managementlibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user=userRepository.findByEmail(s);
        if(user==null){
            throw new UsernameNotFoundException("User " + s + " was not found in the database");
        }
        return MyUser.build(user);
    }

}
