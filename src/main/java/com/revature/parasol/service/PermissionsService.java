package com.revature.parasol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.parasol.domain.Permissions;
import com.revature.parasol.domain.Roles;
import com.revature.parasol.domain.dao.PermissionsRepo;


/**
 * Created by Billy on 2/9/2017.
 */

@Service
@Transactional
public class PermissionsService {
	@Autowired
	PermissionsRepo pr;
	
	public List<Permissions> findByRole(Roles role) {
		return pr.findByRole(role);
	}
}
