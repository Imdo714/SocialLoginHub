package com.api.domain.User.Service;

import com.api.domain.User.Entity.TokenEntity;
import com.api.domain.User.Repository.TokenRepository;
import com.api.domain.Utils.RedisUtil.RedisUtil;
import com.api.global.OAuth.CustomHandler.Dto.CustomUser;
import com.api.global.OAuth.Jwt.JwtInterface;
import com.api.domain.User.Redis.TokenRedis;
import com.api.domain.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtInterface jwtInterface;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
    * 직접 생성할때는 위에 코드처럼 프로젝트가 단순하고 토큰 생성 방식이 고정되었을 경우
    * 인터페이스 형식은 프로젝트가 커지거나 유지보수 가능성을 고려할 때 JWT 생성 로직이 변경 될 가능성이 높은 경우
    *
    * builder는 새로운 객체를 새로 생성할 때 사용 수정 시에도 builder패턴을 사용할려면 객체를 새로 만들고
    * 모든 필드를 다시 설정해야 하기 때문에 수정시에는 Setter 방식으로 부분적으로 수정
    */

//    @Transactional // DB에 저장 하는 메서드
//    public TokenDTO token(CustomUser customUser) {
//        TokenEntity token = createToken(customUser); // 토큰 생성
//        TokenEntity existingToken = tokenRepository.findByUserid(customUser.getUserid()); // 토큰 있는지 유무 확인
//
//        if (existingToken != null) { // 토큰이 있으면 업데이트
//            updateToken(existingToken, token);
//            return new TokenDTO(existingToken);
//        } else {
//            TokenEntity savedToken = tokenRepository.save(token);
//            return new TokenDTO(savedToken);
//        }
//    }

    private TokenEntity createToken(CustomUser customUser) {
        return TokenEntity.builder()
                .userid(customUser.getUserid())
                .accessToken(jwtInterface.getAccess(customUser.getName()))
                .accessExpirationTime(jwtInterface.getAccessExpiration(jwtInterface.getAccess(customUser.getName())))
                .refreshToken(jwtInterface.getRefresh(customUser.getName()))
                .refreshExpirationTime(jwtInterface.getRefreshExpiration(jwtInterface.getRefresh(customUser.getName())))
                .build();
    }

    private void updateToken(TokenEntity existingToken, TokenEntity newToken) {
        existingToken.setAccessToken(newToken.getAccessToken());
        existingToken.setAccessExpirationTime(newToken.getAccessExpirationTime());
        existingToken.setRefreshToken(newToken.getRefreshToken());
        existingToken.setRefreshExpirationTime(newToken.getRefreshExpirationTime());
        tokenRepository.save(existingToken); // save를 호출하면 JPA가 변경 감지를 통해 업데이트 실행
    }

    @Transactional
    public TokenRedis token(CustomUser customUser) {
        String accessToken = null;
        try {
            accessToken = jwtInterface.getAccess(customUser.getName());
            log.info("accessToken = {}", accessToken);

            RedisUtil.saveAccessToken(customUser.getName(), accessToken, 60); // accessToken 저장
            String getAccess = RedisUtil.getAccessToken(customUser.getName()); // accessToken 값 조회
            log.info("getAccess = {}", getAccess);

            Long getTTL = RedisUtil.getTTLAccess(customUser.getName()); // TTL 시간 확인
            log.info("getTTL = {}", getTTL);

        } catch (RedisConnectionFailureException e){
            log.error("Redis 서버 연결 실패: ", e);
            throw new RedisConnectionFailureException("Redis 문제");
        } catch (Exception e) {
            log.error("Redis 작업 중 오류가 발생했습니다: ", e);
        }

        return new TokenRedis(customUser.getName(), accessToken);
    }


}
