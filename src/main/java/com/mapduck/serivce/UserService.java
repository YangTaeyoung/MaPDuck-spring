package com.mapduck.serivce;

import com.mapduck.dto.UserDto;

/**
 * 작성자: 강동연
 * 작성일시: 2021.11.03
 * 설명: UserService 인터페이스
 */
public interface UserService {

    UserDto join(UserDto userDto);
    UserDto findByEmail(String email);
    UserDto updateProfile(UserDto userDto);
    UserDto changePassword(UserDto userDto);

}
