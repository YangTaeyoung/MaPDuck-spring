package com.mapduck.repository;

import com.mapduck.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("from Member where name like concat('%',:keyword,'%')")
    List<Member> search(@Param("keyword")String keyword);
}
