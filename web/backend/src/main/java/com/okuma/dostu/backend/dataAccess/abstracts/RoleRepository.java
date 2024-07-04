package com.okuma.dostu.backend.dataAccess.abstracts;

import com.okuma.dostu.backend.core.security.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String roleName);
}
