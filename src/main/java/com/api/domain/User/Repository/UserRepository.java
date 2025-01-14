package com.api.domain.User.Repository;

import com.api.domain.User.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(String username);

}
