package com.mapduck.serivce;

import com.mapduck.domain.Member;
import com.mapduck.dto.MemberResDto;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface MemberService {
    Member getMember(Long id);
    void saveMember(Member member);
    void deleteMember(Long id);
    Member metaUserToMember(User user);
    boolean checkEmail(String email);
    boolean checkPhone(String phone);
    MemberResDto memberToMemberResDto(Member member);
}
