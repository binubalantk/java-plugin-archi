package com.binubalan.pluginarchi.plugin.api.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "administrator_tbl")
public class AdministratorUser extends User {

    public AdministratorUser(String id, String firstName, String lastName, String dob, List<AccessRules> accessRulesList) {
        super(id, firstName, lastName, dob);
        this.accessRulesList = accessRulesList;
    }

    public AdministratorUser() {
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = AccessRules.class)
    @Fetch(FetchMode.SUBSELECT)
    private List<AccessRules> accessRulesList;

    public List<AccessRules> getAccessRulesList() {
        return accessRulesList;
    }

    public void setAccessRulesList(List<AccessRules> accessRulesList) {
        this.accessRulesList = accessRulesList;
    }
}