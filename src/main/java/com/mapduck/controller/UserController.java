package com.mapduck.controller;

import com.mapduck.domain.Member;
import com.mapduck.domain.User;
import com.mapduck.dto.JoinFormDto;
import com.mapduck.dto.UserVO;
import com.mapduck.mapper.JoinMapper;
import com.mapduck.serivce.MemberService;
import com.mapduck.serivce.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Api(description = "유저의 가입과 관련된 API")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JoinMapper joinMapper;
    private final MemberService memberService;

    @Operation(
            summary = "회원가입을 하는 API",
            description = "사용자 이름, 이메일, 비밀번호, 핸드폰 주소를 받아 DB에 저장합니다."
    )
    @Transactional
    @PostMapping("")
    public ResponseEntity register(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "사용자로부터 받을 정보 JSON", required = true)
            @RequestBody JoinFormDto joinFormDto
    ) {
        UserVO user = joinMapper.joinFormToUserVO(joinFormDto);
        Member member = joinMapper.joinFormToMember(joinFormDto);
        memberService.saveMember(member);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "이메일을 중복확인 하는 API",
            description = "확인할 이메일을 BASE64로 인코딩하여 Request Param에 실어서 전송합니다. </br>이메일 주소에 중복이 없다면 {chk_value: true} 가 반환됩니다."
    )
    @GetMapping("/chke")
    public ResponseEntity<Map<String, Boolean>> checkEmail(
            @Parameter(description = "Base 64로 인코딩 된 이메일 주소")
            @RequestParam("ea") String encodedEmail
    ) {
        String decodedEmail = new String(Base64.decodeBase64(encodedEmail));
        var validation = new HashMap<String, Boolean>();
        validation.put("chk_value", memberService.checkEmail(decodedEmail));
        return new ResponseEntity<>(validation, HttpStatus.OK);
    }

    @Operation(
            summary = "핸드폰 번호를 중복확인하는 API",
            description = "확인할 핸드폰 번호를 BASE64로 인코딩하여 Request Param에 실어서 전송합니다. </br>핸드폰 번호에 중복이 없다면 {chk_value: true} 가 반환됩니다."
    )
    @GetMapping("/chkp")
    public ResponseEntity<Map<String, Boolean>> checkPhone(
            @Parameter(description = "Base 64로 인코딩 된 핸드폰 번호")
            @RequestParam("pn") String encodedPhone
    ) {
        String decodedPhone = new String(Base64.decodeBase64(encodedPhone));
        var validation = new HashMap<String, Boolean>();
        validation.put("chk_value", memberService.checkPhone(decodedPhone));
        return new ResponseEntity<>(validation, HttpStatus.OK);
    }
}
