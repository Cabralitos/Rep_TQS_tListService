/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servicetlist;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos Cabral
 */
@Entity
@Table(name = "TASK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findByIdtask", query = "SELECT t FROM Task t WHERE t.idtask = :idtask"),
    @NamedQuery(name = "Task.findByComplete", query = "SELECT t FROM Task t WHERE t.complete = :complete"),
    @NamedQuery(name = "Task.findByDescription", query = "SELECT t FROM Task t WHERE t.description = :description"),
    @NamedQuery(name = "Task.findByDuedate", query = "SELECT t FROM Task t WHERE t.duedate = :duedate"),
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findByIdtask", query = "SELECT t FROM Task t WHERE t.idtask = :idtask"),
    @NamedQuery(name = "Task.findByDescription", query = "SELECT t FROM Task t WHERE t.description = :description"),
    @NamedQuery(name = "Task.findByDueDate", query = "SELECT t FROM Task t WHERE t.duedate = :dueDate"),
    @NamedQuery(name = "Task.getAllTasksByComplete", query = "SELECT t FROM Task t JOIN t.member1List m WHERE m.idmember = :idmember AND t.complete = :complete"),
    @NamedQuery(name = "Task.getAllTasksByTag", query ="SELECT t FROM Task t JOIN t.tagList tg WHERE tg.idtag = :idtag"),
    @NamedQuery(name = "Task.getAllTaskByName", query="SELECT t FROM Task t WHERE t.description LIKE :name "),
    @NamedQuery(name = "Task.getAllTaskByMember", query="SELECT t FROM Task t JOIN t.member1List m WHERE m.idmember = :idmember ORDER BY t.idpriority.value ASC"),
    @NamedQuery(name = "Task.getLastId", query="SELECT t.idtask FROM Task t ORDER BY t.idtask DESC"),
    @NamedQuery(name = "Task.getAllTasksByCategory", query ="SELECT t FROM Task t WHERE t.idcategory.idcategory = :idcategory"),
    @NamedQuery(name = "Task.deleteTask" , query = "DELETE FROM Task t WHERE t.idtask =:idtask" ),
    //@NamedQuery(name = "Task.deleteTag", query = "DELETE FROM Tag t WHERE t.taskList.idtask = :idtask")
})
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTASK")
    private Integer idtask;
    @Column(name = "COMPLETE")
    private Short complete;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DUEDATE")
    @Temporal(TemporalType.DATE)
    private Date duedate;
    @ManyToMany(mappedBy = "taskList")
    private List<Member1> member1List;
    @JoinTable(name = "TASK_HAS_TAG", joinColumns = {
        @JoinColumn(name = "IDTASK", referencedColumnName = "IDTASK")}, inverseJoinColumns = {
        @JoinColumn(name = "IDTAG", referencedColumnName = "IDTAG")})
    @ManyToMany
    private List<Tag> tagList;
    @JoinColumn(name = "IDPRIORITY", referencedColumnName = "IDPRIORITY")
    @ManyToOne
    private Priority idpriority;
    @JoinColumn(name = "IDCATEGORY", referencedColumnName = "IDCATEGORY")
    @ManyToOne
    private Category idcategory;

    public Task() {
    }

    public Task(Integer idtask) {
        this.idtask = idtask;
    }

    public Integer getIdtask() {
        return idtask;
    }

    public void setIdtask(Integer idtask) {
        this.idtask = idtask;
    }

    public Short getComplete() {
        return complete;
    }

    public void setComplete(Short complete) {
        this.complete = complete;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    @XmlTransient
    public List<Member1> getMember1List() {
        return member1List;
    }

    public void setMember1List(List<Member1> member1List) {
        this.member1List = member1List;
    }

    @XmlTransient
    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public Priority getIdpriority() {
        return idpriority;
    }

    public void setIdpriority(Priority idpriority) {
        this.idpriority = idpriority;
    }

    public Category getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Category idcategory) {
        this.idcategory = idcategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtask != null ? idtask.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.idtask == null && other.idtask != null) || (this.idtask != null && !this.idtask.equals(other.idtask))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.servicetlist.Task[ idtask=" + idtask + " ]";
    }
    
}
