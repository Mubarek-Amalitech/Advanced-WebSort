package com.amalitech.advancedwebsort.SecurityConfig;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtKeyConfig  {
    private String jwtKey;
}
