package com.mapduck.repository;

import com.mapduck.domain.Member;
import com.mapduck.domain.Own;
import com.mapduck.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnRepository extends JpaRepository<Own, Long> {
    List<Own> findAllByOwner(Member member);

}