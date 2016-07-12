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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos Cabral
 */
@Entity
@Table(name = "MEMBER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Member1.findAll", query = "SELECT m FROM Member1 m"),
    @NamedQuery(name = "Member1.findByIdmember", query = "SELECT m FROM Member1 m WHERE m.idmember = :idmember"),
    @NamedQuery(name = "Member1.findByPassword", query = "SELECT m FROM Member1 m WHERE m.password = :password"),
    @NamedQuery(name = "Member1.getAllMembersByUsername", query = "SELECT m FROM Member1 m WHERE m.username = :username"),
    @NamedQuery(name = "Member1.confirmLogin" , query= "SELECT m FROM Member1 m WHERE m.username =:username AND m.password =:password"),
    //@NamedQuery(name = "Member1.deleteTaskMember", query = "DELETE FROM Member1 m WHERE m.taskList.idtask = :idtask")
})
public class Member1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMEMBER")
    private Integer idmember;
    @Size(max = 255)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 255)
    @Column(name = "USERNAME")
    private String username;
    @JoinTable(name = "USER_HAS_TASK", joinColumns = {
        @JoinColumn(name = "IDMEMBER", referencedColumnName = "IDMEMBER")}, inverseJoinColumns = {
        @JoinColumn(name = "IDTASK", referencedColumnName = "IDTASK")})
    @ManyToMany
    private List<Task> taskList;

    public Member1() {
    }

    public Member1(Integer idmember) {
        this.idmember = idmember;
    }

    public Integer getIdmember() {
        return idmember;
    }

    public void setIdmember(Integer idmember) {
        this.idmember = idmember;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        hash += (idmember != null ? idmember.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Member1)) {
            return false;
        }
        Member1 other = (Member1) object;
        if ((this.idmember == null && other.idmember != null) || (this.idmember != null && !this.idmember.equals(other.idmember))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.servicetlist.Member1[ idmember=" + idmember + " ]";
    }
    
}
