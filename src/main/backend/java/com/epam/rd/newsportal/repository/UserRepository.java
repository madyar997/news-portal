package com.epam.rd.newsportal.repository;

import com.epam.rd.newsportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Modifying(clearAutomatically = true, flushAutomatically = true)
  @Query("UPDATE User u SET u.isActive = false WHERE u.id= :id")
  void deactivateUserById(@Param("id") Long id);



}