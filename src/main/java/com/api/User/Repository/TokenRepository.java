package com.api.User.Repository;

import com.api.User.Entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {

    TokenEntity findByUserid(int userid);
}
