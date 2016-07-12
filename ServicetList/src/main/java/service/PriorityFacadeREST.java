/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mycompany.servicetlist.Priority;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Carlos Cabral
 */
@Stateless
@Path("PriorityService")
@Produces ("application/json")
public class PriorityFacadeREST{
    @PersistenceContext(unitName = "com.mycompany_ServicetList_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @GET
    @Path("/getAllPriorities")
    public List<Priority> getAllPriorities(){

       System.out.println("getAllPriorities");
 
       Query query = em.createNamedQuery("Priority.findAll");
       List<Priority> priorities = query.getResultList();

       System.out.println("getAllPriorities: "+priorities);
       return priorities;
    }
    
    @GET
    @Path("/getPriority/{idpriority}/")
    public Priority getPriority(@PathParam("idpriority") int idpriority){

       System.out.println("getPriority");
 
       Query query = em.createNamedQuery("Priority.findByIdpriority");
       query.setParameter("idpriority", idpriority);
       Priority pr = (Priority) query.getSingleResult();

       System.out.println("getPriority: " + pr);
       return pr;
    }
	
    @GET
    @Path("/getIDPriority/{value}/")
    @Produces("text/plain")
    public int getIDPriority(@PathParam("value") int value){

       System.out.println("getIDPriority");
 
       Query query = em.createNamedQuery("Priority.getIDPriority");
       query.setParameter("value", value);
       int pr = (int) query.getSingleResult();

       System.out.println("getIDPriority: " + pr);
       return pr;
    }
    
    
}
