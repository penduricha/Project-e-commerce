package com.example.project_e_commerce_backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(columnDefinition = "nvarchar(50)",unique = true)
    private String email;

    @Column(columnDefinition = "nvarchar(11)",unique = true)
    private String phoneNumber;

    @Column(columnDefinition = "nvarchar(130)",nullable = false)
    private String password;

    @Column(columnDefinition = "nvarchar(30)",nullable = false)
    private String firstName;

    @Column(columnDefinition = "nvarchar(30)",nullable = false)
    private String lastName;

    @Column(columnDefinition = "nvarchar(30)",nullable = false)
    private String middleName;

    @Column(columnDefinition = "nvarchar(255)")
    private String address;

    @Column(columnDefinition = "DATETIME", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "DATETIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreated(){
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdated(){
        updatedAt = LocalDateTime.now();
    }

    @PreRemove
    protected void onDeleted(){
        //updatedAt = LocalDateTime.now();
    }

    public User(String email, String phoneNumber, String password, String firstName, String lastName, String middleName, String address) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.address = address;
    }
}
