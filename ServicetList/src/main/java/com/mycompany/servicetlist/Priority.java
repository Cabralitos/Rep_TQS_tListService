/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servicetlist;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos Cabral
 */
@Entity
@Table(name = "PRIORITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Priority.findAll", query = "SELECT p FROM Priority p"),
    @NamedQuery(name = "Priority.findByIdpriority", query = "SELECT p FROM Priority p WHERE p.idpriority = :idpriority"),
    @NamedQuery(name = "Priority.findByName", query = "SELECT p FROM Priority p WHERE p.name = :name"),
    @NamedQuery(name = "Priority.getIDPriority", query= "SELECT p.idpriority FROM Priority p WHERE p.value =:value")
})
public class Priority implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPRIORITY")
    private Integer idpriority;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Column(name = "VALUE")
    private Integer value;
    @OneToMany(mappedBy = "idpriority")
    private List<Task> taskList;

    public Priority() {
    }

    public Priority(Integer idpriority) {
        this.idpriority = idpriority;
    }

    public Integer getIdpriority() {
        return idpriority;
    }

    public void setIdpriority(Integer idpriority) {
        this.idpriority = idpriority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @XmlTransient
    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpriority != null ? idpriority.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Priority)) {
            return false;
        }
        Priority other = (Priority) object;
        if ((this.idpriority == null && other.idpriority != null) || (this.idpriority != null && !this.idpriority.equals(other.idpriority))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.servicetlist.Priority[ idpriority=" + idpriority + " ]";
    }
    
}
