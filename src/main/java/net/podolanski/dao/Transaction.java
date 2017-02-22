/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.podolanski.dao;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maciej
 */
@Entity
@Table(name = "t_transaction")
@XmlRootElement
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "transaction_id")
    private Integer transactionId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "transactionId")
    private Collection<Doctype> doctypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction")
    private Collection<CurrentState> currentStateCollection;
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @ManyToOne
    private Role roleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction")
    private Collection<Connection> connectionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction1")
    private Collection<Connection> connectionCollection1;

    public Transaction() {
    }

    public Transaction(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Transaction(Integer transactionId, String name) {
        this.transactionId = transactionId;
        this.name = name;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Doctype> getDoctypeCollection() {
        return doctypeCollection;
    }

    public void setDoctypeCollection(Collection<Doctype> doctypeCollection) {
        this.doctypeCollection = doctypeCollection;
    }

    @XmlTransient
    public Collection<CurrentState> getCurrentStateCollection() {
        return currentStateCollection;
    }

    public void setCurrentStateCollection(Collection<CurrentState> currentStateCollection) {
        this.currentStateCollection = currentStateCollection;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    @XmlTransient
    public Collection<Connection> getConnectionCollection() {
        return connectionCollection;
    }

    public void setConnectionCollection(Collection<Connection> connectionCollection) {
        this.connectionCollection = connectionCollection;
    }

    @XmlTransient
    public Collection<Connection> getConnectionCollection1() {
        return connectionCollection1;
    }

    public void setConnectionCollection1(Collection<Connection> connectionCollection1) {
        this.connectionCollection1 = connectionCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.podolanski.dao.Transaction[ transactionId=" + transactionId + " ]";
    }
    
}
