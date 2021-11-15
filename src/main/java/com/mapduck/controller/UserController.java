package com.mapduck.controller;

import com.mapduck.domain.Member;
import com.mapduck.domain.User;
import com.mapduck.dto.JoinFormDto;
import com.mapduck.dto.UserVO;
import com.mapduck.mapper.JoinMapper;
import com.mapduck.serivce.MemberService;
import com.mapduck.serivce.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JoinMapper joinMapper;
    private final MemberService memberService;

    @Transactional
    @PostMapping("")
    public ResponseEntity register(@RequestBody JoinFormDto joinFormDto) {
        UserVO user = joinMapper.joinFormToUserVO(joinFormDto);
        Member member = joinMapper.joinFormToMember(joinFormDto);
        memberService.saveMember(member);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/chke")
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam("ea") String encodedEmail){
        String decodedEmail = new String(Base64.decodeBase64(encodedEmail));
        var validation = new HashMap<String, Boolean>();
        validation.put("chk_value", memberService.checkEmail(decodedEmail));
        return new ResponseEntity<>(validation, HttpStatus.OK);
    }

    @GetMapping("/chkp")
    public ResponseEntity<Map<String, Boolean>> checkPhone(@RequestParam("pn") String encodedPhone){
        String decodedPhone = new String(Base64.decodeBase64(encodedPhone));
        var validation = new HashMap<String, Boolean>();
        validation.put("chk_value", memberService.checkPhone(decodedPhone));
        return new ResponseEntity<>(validation, HttpStatus.OK);
    }
}
