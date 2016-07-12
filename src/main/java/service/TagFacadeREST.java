/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mycompany.servicetlist.Tag;
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
@Path("TagService")
@Produces ("application/json")
public class TagFacadeREST {
    @PersistenceContext(unitName = "com.mycompany_ServicetList_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @GET
    @Path("/getAllTags")
    public List<Tag> getAllTags(){

       System.out.println("getAllTags");
       Query query = em.createNamedQuery("Tag.findAll");
       List<Tag> tags = query.getResultList();

       System.out.println("getAllTags: "+tags);
       return tags;
    }
    
    @POST
    @Path("/createTag")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces("text/plain")
    public int createTag(Tag tag)
    {
        System.out.println("createTag");
        
        Query query = em.createNativeQuery("INSERT INTO Tag (name) " +
            " VALUES(?)");
        query.setParameter(1, tag.getName());
        query.executeUpdate();
        
        int idtag = getLastId();
        return idtag;
    }
    
    /*@GET
    @Path("/deleteTag/{idtask}/")
    public void deleteTag(@PathParam("idtask") int idtask){

       System.out.println("idtask : "+idtask);
 
       Query query = em.createNamedQuery("Tag.deleteTag");
       query.setParameter("idtask", idtask);

    }*/
    
    private int getLastId() {
        System.out.println("getLastId");
        
        Query query = em.createNamedQuery("Tag.getLastId");
        int idtag = (int) query.setMaxResults(1).getSingleResult();
        
        System.out.println("getLastId : "+idtag);

        return idtag;
    }
    
    @GET
    @Path("/getTagByName/{name}/")
    public List<Tag> getTagByName(@PathParam("name") String name)
    {
        Query query = em.createNamedQuery("Tag.getTagByName");
        query.setParameter("name", "%" + name + "%");
        List<Tag> tags = query.getResultList();
        
        return tags;
    }

}
