package com.kaj.rest.api.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_info")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String user_id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_gender")
    private String gender;

    @Column(name = "user_status")
    private String status;
}
