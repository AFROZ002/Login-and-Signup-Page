package com.useraccess.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "requests")
public class AccessRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "software_id")
    private Software software;

    @Column(name = "access_type")
    private String accessType;

    private String reason;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;
}