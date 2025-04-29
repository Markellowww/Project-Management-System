package com.markelloww.projectmanagement.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @Author: Markelloww
 */

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password", length = 1000)
    private String password;
}
