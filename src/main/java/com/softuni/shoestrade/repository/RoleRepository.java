package com.softuni.shoestrade.repository;

import com.softuni.shoestrade.model.Role;
import com.softuni.shoestrade.model.dto.RoleDTO;
import com.softuni.shoestrade.model.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findRoleByName(UserRoles userRoles);
}
