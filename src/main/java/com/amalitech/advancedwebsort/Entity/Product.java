package com.amalitech.advancedwebsort.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ID;
    @Column(unique = true)
    private String productId;
    private  String name;
    private  Long quantity;
}
