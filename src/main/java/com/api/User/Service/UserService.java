package com.api.User.Service;

import com.api.OAuth.Dto.CustomUser;
import com.api.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void token(CustomUser customUser){
        log.info("getName = {}", customUser.getName());
        log.info("getEmail = {}", customUser.getEmail());
        log.info("getNickname = {}", customUser.getNickname());
        // 토큰 생성 로직 작성 해야 함
    }

}
