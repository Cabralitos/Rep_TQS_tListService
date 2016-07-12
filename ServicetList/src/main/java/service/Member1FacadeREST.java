/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mycompany.servicetlist.Member1;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Carlos Cabral
 */
@Stateless
@Path("MemberService")
@Produces ("application/json")
public class Member1FacadeREST {
    @PersistenceContext(unitName = "com.mycompany_ServicetList_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @GET
    @Path("/getAllMembers")
    public List<Member1> getAllMembers(){

       System.out.println("GetAllMembers");
 
       Query query = em.createNamedQuery("Member1.findAll");
       List<Member1> members = query.getResultList();

       System.out.println("getAllMembers : "+members);

       return members;
    }
    
    @POST
    @Path("/confirmLogin")
    @Consumes({MediaType.APPLICATION_JSON})
    public Member1 confirmLogin(Member1 member){

       System.out.println("confirmLogin");
 
       Query query = em.createNamedQuery("Member1.confirmLogin");
       query.setParameter("username", member.getUsername());
       query.setParameter("password", member.getPassword());
       
        List results = query.getResultList();
        Member1 m = new Member1();
        
        if (results.isEmpty())
        {
            m =  new Member1();
        }
        else if (results.size() == 1)
        {
            System.out.println(results.get(0).toString());
            m = (Member1) results.get(0);
        }
        return m;
        
       
   
    }
    
    @POST
    @Path("/createMember")
    @Consumes({MediaType.APPLICATION_JSON})
    public void createMember(Member1 member) {
        
        System.out.println("createMember");
        
        Query query = em.createNativeQuery("INSERT INTO Member (username,password) " +
            " VALUES(?,?)");
        query.setParameter(1, member.getUsername());
        query.setParameter(2, member.getPassword());
        query.executeUpdate();
        
    }
    
    @POST
    @Path("/createTaskMember/{idtask}/{idmember}")
    public void createTaskMember(@PathParam("idtask") int idtask, @PathParam("idmember") int idmember) {
        
        System.out.println("createTaskMember");
        
        Query query = em.createNativeQuery("INSERT INTO USER_HAS_TASK (idmember,idtask) " +
            " VALUES(?,?)");
        query.setParameter(1, idmember);
        query.setParameter(2, idtask);
        
        query.executeUpdate();

    }
    
   
}
