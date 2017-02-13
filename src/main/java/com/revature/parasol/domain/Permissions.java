package com.revature.parasol.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Billy on 2/9/2017.
 */

@Entity
@Table(name = "PERMISSIONS")
public class Permissions implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="P_ID")
    private int pID;

    @OneToOne
    @JoinColumn(name="MODULE_NAME")
    private Modules module;

    @OneToOne
    @JoinColumn(name="ROLE_NAME")
    private Roles role;

    public Permissions(){}


    //Mod Name
    public Modules getModule() {
        return module;
    }

    public void setModule(Modules module) {
        this.module = module;
    }



    //Role Name
    public Roles getRole() {
        return role;
    }

    public void setRole(Roles roleName) {
        this.role = role;
    }
}
