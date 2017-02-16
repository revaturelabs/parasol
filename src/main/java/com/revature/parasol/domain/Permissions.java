package com.revature.parasol.domain;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.*;

/**
 * Created by Billy on 2/9/2017.
 */

@Entity
@Table(name = "PERMISSIONS")
public class Permissions implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "id_gen")
    @GenericGenerator(name="id_gen", strategy = "increment")
    @Column(name="P_ID")
    private int pID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="MODULE_NAME")
    private Modules module;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ROLE_NAME")
    private Roles role;

    public Permissions(){}

    public Permissions(Modules newMod, Roles newRole){
        this.module = newMod;
        this.role = newRole;
    }

    public Permissions(int i, Modules newMod, Roles newRole){
        this.pID = i;
        this.module = newMod;
        this.role = newRole;
    }

    //pID
    public int getpID() { return pID; }
    public void setpID(int pID) { this.pID = pID; }

    //Mod Name
    public Modules getModule() { return module; }
    public void setModule(Modules module) { this.module = module; }

    //Role Name
    public Roles getRole() { return role; }
    public void setRole(Roles roleName) { this.role = role; }
}
