package com.mapduck.repository;

import com.mapduck.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * member 관리를 위한 레파지토리
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findByEmail(String email);
}
