package com.kaj.rest.api.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "role_id")
    private String roleID;

    @Column(name = "role_name")
    private String roleName;
}
