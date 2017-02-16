package com.revature.parasol.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.parasol.domain.Modules;
import com.revature.parasol.domain.Permissions;
import com.revature.parasol.domain.Roles;
import com.revature.parasol.domain.dao.PermissionsRepo;
import com.revature.parasol.domain.dao.RolesRepo;
import com.revature.parasol.domain.dto.ModuleRegDTO;
import com.revature.parasol.domain.dto.RolesDTO;


/**
 * Created by Billy on 2/9/2017.
 */

@Service
@Transactional
public class PermissionsService {
	@Autowired
	RolesRepo rr;
	@Autowired
	PermissionsRepo pr;

	public List<Permissions> findByRole(Roles role) {
		return pr.findByRole(role);
	}
	public void insertPermissionByName(ModuleRegDTO data){ 
		
		
		//parsing DTO object for PERMISSIONS object to be inserted
		
		//Getting Module object setup
        Modules newMod = new Modules();
        newMod.setModuleName(data.getModuleName());
        newMod.setModuleURL(data.getUrl());
		
        //Getting Roles objects setup
        List<RolesDTO> rdList = data.getCheckedData();
        for(RolesDTO dto : rdList){
        	if (dto.isChecked()) {
            	Permissions newPerm = new Permissions(newMod, rr.findByRoleName(dto.getValue()));
        		pr.save(newPerm); 
        	}
        }
	}

}
