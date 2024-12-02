package com.amalitech.advancedwebsort.Requests;

import org.springframework.stereotype.Component;

public record RegisterRequest(String username, String email,String password) {
}
