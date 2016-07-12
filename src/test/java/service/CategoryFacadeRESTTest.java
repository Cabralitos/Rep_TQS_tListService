/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import com.mycompany.servicetlist.Category;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Carlos Cabral
 */
public class CategoryFacadeRESTTest {
    
    Random random = new Random();
    int randomID = random.nextInt((99 - 1) + 1) + 1;
    private int idcategory;
    private String name = "Category" + randomID;

    public int getIdcategory() {
        return idcategory;
    }

    public String getName() {
        return name;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }
     
    
    public CategoryFacadeRESTTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        String port = System.getProperty("server.port");
        if(port == null)
        {
            RestAssured.port = Integer.valueOf(8080);
        }
        else
        {
            RestAssured.port = Integer.valueOf(port);
        }
        
        String basePath = System.getProperty("server.base");
        if(basePath == null)
        {
            basePath = "/ServicetList/webresources/CategoryService";
        }
        RestAssured.basePath = basePath;
        
        String baseHost = System.getProperty("server.host");
        if(baseHost == null)
        {
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

  
    @Test 
    public void testAll() throws Exception {
        testCreateCategory();
        testGetAllCategories();
        testGetCategoryByName();
        testGetCategory();
    }
    
    /**
     * Test of createCategory method, of class CategoryFacadeREST.
     */
    //@Test
    public void testCreateCategory() throws Exception {
        
        System.out.println("Test createCategory");
        
        Category category = new Category();
        category.setName(getName());

        given()
        .contentType("application/json")
        .body(category)
        .when().post("/createCategory").then()
        .statusCode(204);
        
    }
       
    /**
     * Test of getAllCategories method, of class CategoryFacadeREST.
     */
    //@Test
    public void testGetAllCategories() throws Exception {
        
        System.out.println("Test getAllCategories");
        
        expect().contentType("application/json").statusCode(200).when().get("/getAllCategories");

    }
    
    /**
     * Test of getCategoryByName method, of class CategoryFacadeREST.
     */
    //@Test
    public void testGetCategoryByName() throws Exception {
        System.out.println("getCategoryByName");
        
        idcategory = given().contentType("application/json").pathParam("name", "Pessoal").when().get("/getCategoryByName/{name}/").then().statusCode(200).extract().jsonPath().getInt("idcategory");                
    }

    /**
     * Test of getCategory method, of class CategoryFacadeREST.
     */
    //Test
    public void testGetCategory() throws Exception {
        System.out.println("getCategory");
          
        given().contentType("application/json").pathParam("idcategory", getIdcategory()).when().get("/getCategory/{idcategory}/").then().statusCode(200);

    }

    
}
