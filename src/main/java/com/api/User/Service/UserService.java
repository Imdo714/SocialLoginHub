package com.api.User.Service;

import com.api.OAuth.Dto.CustomUser;
import com.api.OAuth.Jwt.JwtInterface;
import com.api.User.Entity.TokenEntity;
import com.api.User.Entity.UserEntity;
import com.api.User.Repository.TokenRepository;
import com.api.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtInterface jwtInterface;


    public TokenEntity token(CustomUser customUser){
        log.info("getName = {}", customUser.getName());
//        log.info("getEmail = {}", customUser.getEmail());
//        log.info("getNickname = {}", customUser.getNickname());

/**
 *  직접 생성할때는 위에 코드처럼 프로젝트가 단순하고 토큰 생성 방식이 고정되었을 경우
 *  인터페이스 형식은 프로젝트가 커지거나 유지보수 가능성을 고려할 때 JWT 생성 로직이 변경 될 가능성이 높은 경우
 *
 *  String AccessToken = jwtInterface.getAccess(customUser.getName());
 *  Timestamp AccessExpiration = jwtInterface.getAccessExpiration(AccessToken);
 *  String RefreshToken = jwtInterface.getRefresh(customUser.getName());
 *  Timestamp RefreshExpiration = jwtInterface.getRefreshExpiration(RefreshToken);
 */

/**
 *  builder는 새로운 객체를 새로 생성할 때 사용 수정 시에도 builder패턴을 사용할려면 객체를 새로 만들고 모든 필드를 다시 설정해야 하기 때문에
 *  수정시에는 Setter 방식으로 부분적으로 수정
 */

        TokenEntity isToken = tokenRepository.findByUserid(customUser.getUserid());
        log.info("isToken = {}", isToken);

        if (isToken != null) { // 이미 있어서 토큰 업데이트로 수정해야 함

            return isToken;
        } else {
            TokenEntity token = TokenEntity.builder()
                    .userid(customUser.getUserid())
                    .accessToken(jwtInterface.getAccess(customUser.getName()))
                    .accessExpirationTime(jwtInterface.getAccessExpiration(jwtInterface.getAccess(customUser.getName())))
                    .refreshToken(jwtInterface.getRefresh(customUser.getName()))
                    .refreshExpirationTime(jwtInterface.getRefreshExpiration(jwtInterface.getRefresh(customUser.getName())))
                    .build();

            log.info("처음 token = {}", token);
            tokenRepository.save(token); // 처음이여서 INSERT
            isToken = tokenRepository.findByUserid(customUser.getUserid());
            return isToken;
        }
    }


}
