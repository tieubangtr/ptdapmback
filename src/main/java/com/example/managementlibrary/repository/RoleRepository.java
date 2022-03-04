package com.example.managementlibrary.repository;

import com.example.managementlibrary.common.ERole;
import com.example.managementlibrary.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(ERole name);
}
