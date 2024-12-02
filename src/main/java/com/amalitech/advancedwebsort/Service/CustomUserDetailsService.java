package com.amalitech.advancedwebsort.Service;

import com.amalitech.advancedwebsort.Entity.User;
import com.amalitech.advancedwebsort.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser= userRepository.findByEmail(email);
        if(  optionalUser.isEmpty()) {
              throw new UsernameNotFoundException(" user not found");
        }
        return   optionalUser.get();
    }
}
