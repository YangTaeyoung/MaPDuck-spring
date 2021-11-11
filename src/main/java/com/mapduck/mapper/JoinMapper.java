package com.mapduck.mapper;

import com.mapduck.domain.Member;
import com.mapduck.domain.Role;
import com.mapduck.domain.User;
import com.mapduck.dto.JoinFormDto;
import com.mapduck.dto.UserVO;
import com.mapduck.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class JoinMapper {
    private final RoleRepository roleRepository;

    /**
     * 작성자: 양태영
     * 작성일: 21.11.11
     * 설명: 회원가입 폼에서 user객체로 변환시키는 함수.
     * @param joinFormDto
     * @return user
     */
    public User joinFormToUser(JoinFormDto joinFormDto){
        var user = new User();
        user.setUsername(joinFormDto.getEmail());
        user.setPassword(joinFormDto.getPassword());
        var role = roleRepository.findByRoleName("MEMBER");
        user.setRoles(Arrays.asList(role));
        user.setEmail(joinFormDto.getEmail());
        return user;
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.11
     * 설명: 회원 가입 폼에서 멤버 형식으로 만드는 것.
     *
     * @param joinFormDto
     * @return member
     */
    public Member joinFormToMember(JoinFormDto joinFormDto){
        var member = new Member();
        member.setName(joinFormDto.getName());
        member.setPhone(joinFormDto.getPhone());
        member.setEmail(joinFormDto.getEmail());
        return member;
    }

    /**
     * 작성자: 양태영
     * 작성일: 21.11.11
     * 설명: 회원가입 폼에서 UserVO 객체로 변환하는 함수
     * @param joinFormDto
     * @return userVO
     */
    public UserVO joinFormToUserVO(@RequestBody JoinFormDto joinFormDto){
        var userVO = new UserVO();
        userVO.setEmail(joinFormDto.getEmail());
        userVO.setUsername(joinFormDto.getEmail());
        Role role = roleRepository.findByRoleName("MEMBER");
        userVO.setRoles(Arrays.asList("MEMBER"));
        userVO.setPassword(joinFormDto.getPassword());
        return userVO;
    }
}
