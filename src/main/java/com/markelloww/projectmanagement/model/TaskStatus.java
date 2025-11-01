package com.markelloww.projectmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "task_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks = new HashSet<>();
}
