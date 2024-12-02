package com.amalitech.advancedwebsort.Service;
import com.amalitech.advancedwebsort.Entity.User;
import com.amalitech.advancedwebsort.Event.RegistrationCompleteEvent;
import com.amalitech.advancedwebsort.Repository.UserRepository;
import com.amalitech.advancedwebsort.Requests.AuthenticationRequest;
import com.amalitech.advancedwebsort.Requests.RegisterRequest;
import com.amalitech.advancedwebsort.Response.AuthenticationResponse;
import com.amalitech.advancedwebsort.SecurityConfig.SecurityBeansInjector;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private  final SecurityBeansInjector securityBeansInjector;
    private  final UserRepository userRepository;
    private  final  JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(authenticationRequest.email(),authenticationRequest.password());
        try{
            Authentication authentication =  securityBeansInjector.getAuthenticationManager().authenticate(authenticationToken);


            User user= (User) authentication.getPrincipal();
            System.out.println(user);
            if(!user.isEnabled()){
                throw  new DisabledException("");
            }
            Map<String,Object > claims = new HashMap<>();
            claims.put("name",user.getUsername());
            String jwtToken= jwtService.generateToken(claims,user);
            return    new AuthenticationResponse(jwtToken);
        }
        catch ( Exception e) {
            throw  new BadCredentialsException(e.getMessage());
        }

    }
      @Transactional
    public  void RegisterUser(RegisterRequest request) {
        Optional<User> optionalUser=  userRepository.findByEmail(request.email());
        if (optionalUser.isPresent()){
            throw  new RuntimeException("user already exits");
        }
       User user= User.builder().username(request.username()).email(request.email()).password(passwordEncoder.encode(request.password())) .isEnabled(true).build();
         userRepository.save(user);
    }

}
