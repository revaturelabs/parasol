package com.revature.parasol.domain.dao;

import com.revature.parasol.domain.Modules;
import com.revature.parasol.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Billy on 2/9/2017.
 */
public interface ModulesRepo extends JpaRepository<Modules, Integer> {

    //void insertModuleByNams(Roles role);
}
