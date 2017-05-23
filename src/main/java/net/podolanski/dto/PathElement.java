package net.podolanski.dto;

import net.podolanski.dao.Role;

/**
 * Created by maciej on 21.05.17.
 */
public class PathElement {
    private String transactionName;
    private Role assingnedRole;

    public PathElement(String transactionName, Role assingnedRole) {
        this.transactionName = transactionName;
        this.assingnedRole = assingnedRole;
    }

    public PathElement() {}

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public Role getAssingnedRole() {
        return assingnedRole;
    }

    public void setAssingnedRole(Role assingnedRole) {
        this.assingnedRole = assingnedRole;
    }
}
