package com.amalitech.advancedwebsort.Controllers;


import com.amalitech.advancedwebsort.Requests.ProductRequest;
import com.amalitech.advancedwebsort.Requests.SortRequest;
import com.amalitech.advancedwebsort.Service.SortingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/sorting")
public class SortingController {
     private final SortingService sortingService;

    @PostMapping("/sort")
    public ResponseEntity<?> Sort( @RequestBody  SortRequest request) {
         return  ResponseEntity.ok().body(sortingService.Sorting(request));
    }



}
