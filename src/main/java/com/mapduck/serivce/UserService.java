package com.mapduck.serivce;

import com.mapduck.dto.UserVO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 작성자: 강동연
 * 작성일시: 2021.11.03
 * 설명: UserService 인터페이스
 */
public interface UserService extends UserDetailsService {
    void save(UserVO userDto);

    UserVO findUserByUsername(String username);
}
