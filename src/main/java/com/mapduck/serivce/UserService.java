package com.mapduck.serivce;

import com.mapduck.dto.UserDto;

public interface UserService {

    UserDto join(UserDto userDto);
    UserDto findByEmail(String email);
    UserDto updateProfile(UserDto userDto);
    UserDto changePassword(UserDto userDto);

}
