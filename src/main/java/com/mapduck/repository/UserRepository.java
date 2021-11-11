package com.mapduck.repository;

import com.mapduck.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "from User where username=:username")
   User findByUsername(@Param("username") String username);

}
