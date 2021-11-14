package com.mapduck.repository;

import com.mapduck.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("from Role where roleName=:roleName")
    Role findByRoleName(@Param("roleName") String roleName);
}
