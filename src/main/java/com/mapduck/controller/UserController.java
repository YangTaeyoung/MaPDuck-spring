package com.mapduck.controller;

import com.mapduck.domain.Member;
import com.mapduck.domain.User;
import com.mapduck.dto.JoinFormDto;
import com.mapduck.dto.UserVO;
import com.mapduck.mapper.JoinMapper;
import com.mapduck.serivce.MemberService;
import com.mapduck.serivce.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JoinMapper joinMapper;
    private final MemberService memberService;


    @Transactional
    @PostMapping("/user")
    public ResponseEntity<UserVO> register(@RequestBody JoinFormDto joinFormDto) {
        UserVO user = joinMapper.joinFormToUserVO(joinFormDto);
        Member member = joinMapper.joinFormToMember(joinFormDto);
        memberService.saveMember(member);
        userService.save(user);
        return new ResponseEntity<UserVO>(user, HttpStatus.CREATED);
    }
}
