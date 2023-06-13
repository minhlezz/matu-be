package com.workspace.shopping.role;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

    Set<Role> findByNameIn(List<String> names);

    default Set<Role> getDefaultRoles() {
        return this.findByNameIn(List.of("USER", "ADMIN"));
    }
}
