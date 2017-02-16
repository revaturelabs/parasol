package com.revature.parasol.domain;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Billy on 2/9/2017.
 */

@Entity
@Table(name = "ROLES")
public class Roles {

    @Id
    @Column(name="ROLE_NAME")
    private  String roleName;

    public Roles(){}
    public  Roles(String rName){
        this.roleName = rName;
    }

    //roleName
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }



}
