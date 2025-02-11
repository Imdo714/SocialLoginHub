package com.api.domain.User.Repository;

import com.api.domain.User.Entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {

//    TokenEntity findByUserid(int userid);
//
//    @Modifying
//    @Query("UPDATE TokenEntity t " +
//            "SET t.accessToken = :#{#token.accessToken}, " +
//            "t.accessExpirationTime = :#{#token.accessExpirationTime}, " +
//            "t.refreshToken = :#{#token.refreshToken}, " +
//            "t.refreshExpirationTime = :#{#token.refreshExpirationTime} " +
//            "WHERE t.userEntity.userid = :#{#token.userid}")
//    void updateToken(TokenEntity token);
}
