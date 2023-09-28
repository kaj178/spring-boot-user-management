package com.kaj.rest.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_info")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long user_id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_gender")
    private String gender;

    @Column(name = "user_status")
    private String status;
}
