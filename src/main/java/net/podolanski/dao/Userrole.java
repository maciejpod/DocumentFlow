/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maciej
 */
@Entity
@Table(name = "\"T_USERROLE\"")
@XmlRootElement
public class Userrole implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserrolePK userrolePK;
    @JoinColumn(name = "department_id", referencedColumnName = "department_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Department department;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Role role;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Userrole() {
    }

    public Userrole(UserrolePK userrolePK) {
        this.userrolePK = userrolePK;
    }

    public Userrole(int userId, int roleId, int departmentId) {
        this.userrolePK = new UserrolePK(userId, roleId, departmentId);
    }

    public UserrolePK getUserrolePK() {
        return userrolePK;
    }

    public void setUserrolePK(UserrolePK userrolePK) {
        this.userrolePK = userrolePK;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userrolePK != null ? userrolePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userrole)) {
            return false;
        }
        Userrole other = (Userrole) object;
        if ((this.userrolePK == null && other.userrolePK != null) || (this.userrolePK != null && !this.userrolePK.equals(other.userrolePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.podolanski.dao.Userrole[ userrolePK=" + userrolePK + " ]";
    }
    
}
