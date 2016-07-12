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
@Table(name = "TAG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t"),
    @NamedQuery(name = "Tag.findByIdtag", query = "SELECT t FROM Tag t WHERE t.idtag = :idtag"),
    //@NamedQuery(name = "Tag.deleteTag", query = "DELETE FROM Tag t WHERE t.taskList.idtask = :idtask"),
    @NamedQuery(name = "Tag.getLastId", query="SELECT t.idtag FROM Tag t ORDER BY t.idtag DESC"),
    @NamedQuery(name = "Tag.getTagByName", query = "SELECT t FROM Tag t WHERE t.name LIKE :name")
})
public class Tag implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTAG")
    private Integer idtag;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @ManyToMany(mappedBy = "tagList")
    private List<Task> taskList;

    public Tag() {
    }

    public Tag(Integer idtag) {
        this.idtag = idtag;
    }

    public Integer getIdtag() {
        return idtag;
    }

    public void setIdtag(Integer idtag) {
        this.idtag = idtag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (idtag != null ? idtag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tag)) {
            return false;
        }
        Tag other = (Tag) object;
        if ((this.idtag == null && other.idtag != null) || (this.idtag != null && !this.idtag.equals(other.idtag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.servicetlist.Tag[ idtag=" + idtag + " ]";
    }
    
}
