package com.mapduck.repository;

import com.mapduck.domain.Member;
import com.mapduck.domain.Own;
import com.mapduck.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnRepository extends JpaRepository<Own, Long> {
    List<Own> findAllByOwner(Member member);

}