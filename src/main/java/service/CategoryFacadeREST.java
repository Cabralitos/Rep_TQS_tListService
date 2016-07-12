/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mycompany.servicetlist.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("CategoryService")
@Produces ("application/json")
public class CategoryFacadeREST {
    @PersistenceContext(unitName = "com.mycompany_ServicetList_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @GET
    @Path("/getAllCategories")
    public List<Category> getAllCategories(){

       System.out.println("getAllCategories");
 
       Query query = em.createNamedQuery("Category.findAll");
       List<Category> categories = query.getResultList();

       System.out.println("getAllCategories: "+categories);
       return categories;
    }
    
    @GET
    @Path("/getCategory/{idcategory}/")
    public Category getCategory(@PathParam("idcategory") int idcategory){

       System.out.println("getCategory");
 
       Query query = em.createNamedQuery("Category.findByIdcategory");
       query.setParameter("idcategory", idcategory);
       Category cat = (Category) query.getSingleResult();

       System.out.println("getCategory: " + cat);
       return cat;
    }
    
    @GET
    @Path("/getCategoryByName/{name}/")
    public Category getCategoryByName(@PathParam("name") String name){

       System.out.println("getCategoryByName" + name);
 
       Query query = em.createNamedQuery("Category.categoryByName");
       query.setParameter("name", "%" + name + "%");
       Category cat = (Category) query.getSingleResult();

       System.out.println("getCategoryByName: " + cat);
       return cat;
    }
    
    @POST
    @Path("/createCategory")
    @Consumes({MediaType.APPLICATION_JSON})
    public void createCategory(Category category)
    {
        System.out.println("createCategory");
        
        /*Query query = em.createNativeQuery("INSERT INTO Category (idcategory,name) " +
            " VALUES(?,?)");*/
        Query query = em.createNativeQuery("INSERT INTO Category (name) " +
            " VALUES(?)");
        query.setParameter(1, category.getName());
        query.executeUpdate();
    }
    
    @DELETE
    @Path("/deleteCategory/{id}/")
    public void deleteCategory(@PathParam("id") int id){

       System.out.println("id : "+id);
       String param = id+"%";
 
       Query query = em.createNamedQuery("Category.deleteCategory");
       query.setParameter("idcategory", param);

    }
    
}
