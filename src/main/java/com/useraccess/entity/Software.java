package com.useraccess.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "software")
public class Software {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "access_levels")
    private String accessLevels;
}
