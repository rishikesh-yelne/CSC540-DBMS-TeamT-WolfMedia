package edu.ncsu.csc540.s23.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "user_id")
    private Long userId;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "phone_num")
    private String phoneNum;

    @Column(nullable = false, name = "email_id")
    private String emailId;

    @Column(nullable = false, name = "reg_date")
    private Instant regDate;
}
