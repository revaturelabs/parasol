package com.revature.parasol.domain.service;

import com.revature.parasol.domain.Modules;
import com.revature.parasol.domain.Roles;
import com.revature.parasol.domain.dao.ModulesRepo;
import com.revature.parasol.domain.dao.RolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Billy on 2/13/2017.
 */

@Service
public class ModulesService {

    @Autowired
    private ModulesRepo mr;

    public void insertModuleByName(Modules newMod){
        mr.save(newMod);
    }
}
