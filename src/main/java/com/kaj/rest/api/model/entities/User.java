package com.kaj.rest.api.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "user_info")
@Data
public class User {
    @Id
    @Column(name = "user_id")
    private String userID;

    @Column(name = "user_last_name")
    private String userLastName;

    @Column(name = "user_first_name")
    private String userFirstName;

    @Column(name = "user_birthday")
    @Temporal(TemporalType.DATE)
    private LocalDate userBirthDay;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_gender")
    private String userGender;
}
