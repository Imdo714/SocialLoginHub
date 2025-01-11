package com.api.User.Service;

import com.api.OAuth.Dto.CustomUser;
import com.api.OAuth.Jwt.JwtInterface;
import com.api.User.Dto.TokenDTO;
import com.api.User.Entity.TokenEntity;
import com.api.User.Redis.TokenRedis;
import com.api.User.Repository.TokenRepository;
import com.api.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Transactional
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
        String accessToken = jwtInterface.getAccess(customUser.getName());
        saveAccessToken(customUser.getName(), accessToken, 60); // accessToken 저장
        String res = getAccessToken(customUser.getName()); // 키값 정보 뺴오기
        log.info("res = {}", res);

        Long res3 = getTTLAccess(customUser.getName()); // TTL 시간 확인
        log.info("res3 = {}", res3);

        setTTLAccess(customUser.getName(), 30);
        log.info("==============시간 수정============");

        Long res4 = getTTLAccess(customUser.getName()); // TTL 시간 확인
        log.info("res4 = {}", res4);

        return new TokenRedis(customUser.getName(), accessToken);
    }

    // accessToken 저장
    public void saveAccessToken(String userId, String accessToken, long accessTokenTTL) {
        redisTemplate.opsForValue().set("accessToken:" + userId, accessToken, Duration.ofSeconds(accessTokenTTL));
    }

    // accessToken 조회
    public String getAccessToken(String userId) {
        return (String) redisTemplate.opsForValue().get("accessToken:" + userId);
    }

    // accessToken TTL 조회
    public Long getTTLAccess(String key) {
        return redisTemplate.getExpire("accessToken:" + key);
    }

    // accessToken TTL 갱신
    public void setTTLAccess(String key, long ttlInSeconds) {
        redisTemplate.expire("accessToken:" + key, Duration.ofSeconds(ttlInSeconds));
    }

    // accessToken 삭제
    public void deleteDataAccess(String key) {
        redisTemplate.delete("accessToken:" + key);
    }

}
