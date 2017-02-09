/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author maciej
 */
@Embeddable
public class UserrolePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "role_id")
    private int roleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "department_id")
    private int departmentId;

    public UserrolePK() {
    }

    public UserrolePK(int userId, int roleId, int departmentId) {
        this.userId = userId;
        this.roleId = roleId;
        this.departmentId = departmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) roleId;
        hash += (int) departmentId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserrolePK)) {
            return false;
        }
        UserrolePK other = (UserrolePK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.roleId != other.roleId) {
            return false;
        }
        if (this.departmentId != other.departmentId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.podolanski.dao.UserrolePK[ userId=" + userId + ", roleId=" + roleId + ", departmentId=" + departmentId + " ]";
    }
    
}
