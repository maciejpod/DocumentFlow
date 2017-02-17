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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maciej
 */
@Entity
@Table(name = "\"T_DOCTYPE\"")
@XmlRootElement
public class Doctype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "doctype_id")
    private Integer doctypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
    @ManyToOne
    private Transaction transactionId;
    @OneToMany(mappedBy = "docType")
    private Collection<Request> requestCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctype")
    private Collection<Connection> connectionCollection;

    public Doctype() {
    }

    public Doctype(Integer doctypeId) {
        this.doctypeId = doctypeId;
    }

    public Doctype(Integer doctypeId, String name) {
        this.doctypeId = doctypeId;
        this.name = name;
    }

    public Integer getDoctypeId() {
        return doctypeId;
    }

    public void setDoctypeId(Integer doctypeId) {
        this.doctypeId = doctypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Transaction getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Transaction transactionId) {
        this.transactionId = transactionId;
    }

    @XmlTransient
    public Collection<Request> getRequestCollection() {
        return requestCollection;
    }

    public void setRequestCollection(Collection<Request> requestCollection) {
        this.requestCollection = requestCollection;
    }

    @XmlTransient
    public Collection<Connection> getConnectionCollection() {
        return connectionCollection;
    }

    public void setConnectionCollection(Collection<Connection> connectionCollection) {
        this.connectionCollection = connectionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (doctypeId != null ? doctypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctype)) {
            return false;
        }
        Doctype other = (Doctype) object;
        return !((this.doctypeId == null && other.doctypeId != null) || (this.doctypeId != null && !this.doctypeId.equals(other.doctypeId)));
    }

    @Override
    public String toString() {
        return "net.podolanski.dao.Doctype[ doctypeId=" + doctypeId + " ]";
    }
    
}
