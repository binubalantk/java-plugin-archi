package com.binubalan.pluginarchi.plugin.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer_tbl")
public class CustomerUser extends User{
    @Column(name = "organization")
    private String organization;

    public CustomerUser(String id, String firstName, String lastName, String dob, String organization) {
        super(id, firstName, lastName, dob);
        this.organization = organization;
    }

    public CustomerUser() {
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
