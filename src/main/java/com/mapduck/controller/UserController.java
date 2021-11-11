package com.mapduck.controller;

import com.mapduck.dto.UserVO;
import com.mapduck.serivce.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public UserVO login(@RequestBody UserVO user){
        UserVO userVO = userService.findUserByUsername(user.getUsername());

        return userVO;
    }

    @PostMapping("/users")
    public ResponseEntity<UserVO> register(@RequestBody UserVO user) {
        this.userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
