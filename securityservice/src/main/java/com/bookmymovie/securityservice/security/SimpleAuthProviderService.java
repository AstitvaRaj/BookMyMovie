package com.bookmymovie.securityservice.security;

import com.bookmymovie.securityservice.entity.Users;
import com.bookmymovie.securityservice.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SimpleAuthProviderService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("inside loading username");
        Optional<Users> usersOptional = usersRepository.findByEmail(username);
        if (usersOptional.isPresent()) {
            Users users = usersOptional.get();
            return new User(users.getUserId().toString(), users.getPassword(), List.of(new SimpleGrantedAuthority(users.getUserTypeEntity().getUserType())));
        }
        throw new UsernameNotFoundException("User " + username + " not found.");
//        return new User("username","pwd", List.of(new SimpleGrantedAuthority("ADMIN")));
    }

}
