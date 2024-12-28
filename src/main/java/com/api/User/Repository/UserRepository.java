package com.api.User.Repository;

import com.api.User.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);
}
