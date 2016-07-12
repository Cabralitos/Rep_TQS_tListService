/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mycompany.servicetlist.Task;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Carlos Cabral
 */
@Stateless
@Path("TaskService")
@Produces ("application/json")
public class TaskFacadeREST {
    @PersistenceContext(unitName = "com.mycompany_ServicetList_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @POST
    @Path("/createTask")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces("text/plain")
    public int createTask(Task task) {
        
        System.out.println("createTask");
        System.out.println(task.getIdcategory().getIdcategory());
        
        Query query = em.createNativeQuery("INSERT INTO Task (complete,description, duedate, idcategory, idpriority) " +
            " VALUES(?,?,?,?,?)");
        query.setParameter(1, task.getComplete());
        query.setParameter(2, task.getDescription());
        query.setParameter(3, task.getDuedate());
        query.setParameter(4, task.getIdcategory().getIdcategory());
        query.setParameter(5, task.getIdpriority().getIdpriority());
        query.executeUpdate();
        
        int idtask = getLastId();
        
        return idtask;
    }
    
    @POST
    @Path("/createTaskTag/{idtask}/{idtag}")
    public void createTaskTag(@PathParam("idtask") int idtask, @PathParam("idtag") int idtag) {
        
        System.out.println("createTaskTag");
        
        Query query = em.createNativeQuery("INSERT INTO TASK_HAS_TAG (idtask,idtag) " +
            " VALUES(?,?)");
        query.setParameter(1, idtask);
        query.setParameter(2, idtag);
        
        query.executeUpdate();

    }
    

    @DELETE
    @Path("/deleteTask/{idtask}/")
    public void deleteTask(@PathParam("idtask") int idtask){

       System.out.println("id : "+idtask);
 
       Query query = em.createNamedQuery("Task.deleteTask");
       query.setParameter("idtask", idtask);

    }
    
    @PUT
    @Path("/editTask")
    @Consumes({MediaType.APPLICATION_JSON})
    public void editTask(Task task) {
        
        System.out.println("editTask");
        
        Query query = em.createNamedQuery("Member1.editTask");
        query.setParameter("complete", task.getComplete());
        query.setParameter("description", task.getDescription());
        query.setParameter("duedate", task.getDuedate());
        query.setParameter("idcategory", task.getIdcategory());
        query.setParameter("idpriority", task.getIdpriority());
        query.setParameter("idtask", task.getIdtask());
        query.executeUpdate();
        
    }
       
    @POST
    @Path("/setPriority/{idtask}/{idpriority}")
    public void setPriority(@PathParam("idtask") int idtask, @PathParam("idpriority") int idpriority)
    {
        System.out.println("setPriority");
        System.out.println("idtask :"+idtask + " idpiority:" + idpriority);
        
       Query query = em.createNativeQuery("UPDATE Task SET idpriority=? WHERE idtask=? ");
       query.setParameter(1, idpriority);
       query.setParameter(2, idtask);
       query.executeUpdate();
    }
    
    @GET
    @Path("/getAllTasksByMember/{idmember}")
    public List<Task> getAllTasksByMember(@PathParam("idmember") int idmember)
    {
        System.out.println("getAllTasksByMember");
        System.out.println("idmember:" + idmember);
        
        Query query = em.createNamedQuery("Task.getAllTaskByMember");
        query.setParameter("idmember", idmember);
        List<Task> tasks = query.getResultList();

        System.out.println("getAllTasksByMember : "+tasks);

        return tasks;
    }
    
    @GET
    @Path("/getAllTasksByName/{name}")
    public List<Task> getAllTasksByName(@PathParam("name") String name)
    {
        System.out.println("getAllTasksByName");
        System.out.println("name:" + name);
        
        Query query = em.createNamedQuery("Task.getAllTaskByName");
        query.setParameter("name", name);
        List<Task> tasks = query.getResultList();

        System.out.println("getAllTasksByName : "+tasks);

        return tasks;
    }
    
    @GET
    @Path("/getAllTasksByTag/{idtag}")
    public List<Task> getAllTasksByTag(@PathParam("idtag") int idtag)
    {
        System.out.println("getAllTasksByTag");
        System.out.println("idtag:" + idtag);
        
        Query query = em.createNamedQuery("Task.getAllTasksByTag");
        query.setParameter("idtag", idtag);
        List<Task> tasks = query.getResultList();

        System.out.println("getAllTasksByTag : "+tasks);

        return tasks;
    }
    
    @GET
    @Path("/getAllTasksByComplete/{idmember}")
    public List<Task> getAllTasksByComplete(@PathParam("idmember") int idmember)
    {
        System.out.println("getAllTasksByComplete");
        
        Query query = em.createNamedQuery("Task.getAllTasksByComplete");
        query.setParameter("complete", 0);
        query.setParameter("idmember", idmember);
        List<Task> tasks = query.getResultList();

        System.out.println("getAllTasksByComplete : "+tasks);

        return tasks;
    }
    
    @GET
    @Path("/getAllTasksByCategory/{idcategory}")
    public List<Task> getAllTasksByCategory(@PathParam("idcategory") int idcategory)
    {
        System.out.println("getAllTasksByCategory");
        System.out.println("idtag:" + idcategory);
        
        Query query = em.createNamedQuery("Task.getAllTasksByCategory");
        query.setParameter("idcategory", idcategory);
        List<Task> tasks = query.getResultList();

        System.out.println("getAllTasksByCategory : "+tasks);

        return tasks;
    }
    
    @GET
    @Path("/getAllTasks")
    public List<Task> getAllTasks()
    {
        System.out.println("getAllTasksByTag");
        
        Query query = em.createNamedQuery("Task.findAll");
        List<Task> tasks = query.getResultList();

        System.out.println("getAllTasks : "+tasks);

        return tasks;
    }
    
    @POST
    @Path("/setState/{idtask}/{complete}")
    public void setState(@PathParam("complete") int complete,@PathParam("idtask") int idtask)
    {
       System.out.println("setState - idtask:" + idtask + " complete:" + complete );
 
       Query query = em.createNativeQuery("UPDATE Task SET complete=? WHERE idtask=? ");
       query.setParameter(1, complete);
       query.setParameter(2, idtask);
       query.executeUpdate();
       
    }
    
    private int getLastId() {
        System.out.println("getLastId");
        
        Query query = em.createNamedQuery("Task.getLastId");
        int idtask = (int) query.setMaxResults(1).getSingleResult();
        
        System.out.println("getLastId : "+idtask);

        return idtask;
    }
    
}
