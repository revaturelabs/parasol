package com.revature.parasol.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.parasol.domain.Roles;

/**
 * Created by Billy on 2/9/2017.
 */
public interface RolesRepo extends JpaRepository<Roles, Integer> {

	Roles findByRoleName(String roleName);
}
