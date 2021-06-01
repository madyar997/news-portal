package com.epam.rd.newsportal.repository;

import com.epam.rd.newsportal.entity.role.ERole;
import com.epam.rd.newsportal.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}