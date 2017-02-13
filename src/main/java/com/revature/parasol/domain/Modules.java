package com.revature.parasol.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billy on 2/9/2017.
 */

@Entity
@Table(name = "MODULES")
public class Modules {

    @Id
    @Column(name="MODULE_NAME")
    private String moduleName;

    @Column(name="MODLUE_URL")
    private String moduleURL;

    public Modules(){}
    
    //moduleName
    public String getModuleName() {
        return moduleName;
    }
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    //moduleURL
    public String getModuleURL() {
        return moduleURL;
    }
    public void setModuleURL(String moduleURL) {
        this.moduleURL = moduleURL;
    }
}
