package com.mapduck.serivce;

import com.mapduck.domain.Member;

import java.util.List;

public interface MemberService {
    Member getMember(Long id);
    void saveMember(Member member);
    void deleteMember(Long id);
}
