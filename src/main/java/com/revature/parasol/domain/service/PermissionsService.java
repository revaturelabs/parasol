package com.revature.parasol.domain.service;

import com.revature.parasol.domain.Modules;
import com.revature.parasol.domain.Permissions;
import com.revature.parasol.domain.Roles;
import com.revature.parasol.domain.dao.PermissionsRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
	public void insertPermissionByName(Permissions newPerm){ pr.save(newPerm); }

}
