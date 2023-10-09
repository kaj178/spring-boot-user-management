package com.kaj.rest.api.repository;

import com.kaj.rest.api.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleInterface extends JpaRepository<Role, String> {
}
