package com.revature.parasol.domain.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.parasol.domain.Modules;
import com.revature.parasol.domain.Permissions;
import com.revature.parasol.domain.Roles;

/**
 * Created by Billy on 2/9/2017.
 */
@Repository
public interface PermissionsRepo extends JpaRepository<Permissions, Integer> {

    List<Permissions> findByRole(Roles role);
}
