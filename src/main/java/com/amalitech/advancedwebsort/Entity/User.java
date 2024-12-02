package com.amalitech.advancedwebsort.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails, Principal {
    @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private UUID ID;
     @Column(nullable = false,unique = true)
     private  String email;
     @Column(nullable = false)
     private  String password;
     private String username;
    private  boolean isEnabled;
     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
          return List.of();
     }

     @Override
     public String getPassword() {
          return password;
     }
     @Override
     public String getUsername() {
          return email;
     }

     @Override
     public boolean isEnabled() {
          return isEnabled;
     }
     @Override
     public String getName() {
          return username;
     }

}