/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import com.mycompany.servicetlist.Tag;
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
public class TagFacadeRESTTest {
    
    Random random = new Random();
    int randomID = random.nextInt((99 - 1) + 1) + 1;
    private String name = "Tag" + randomID;

    public String getName() {
        return name;
    }
    
    public TagFacadeRESTTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
            basePath = "/ServicetList/webresources/TagService";
        }
        RestAssured.basePath = basePath;
        
        String baseHost = System.getProperty("server.host");
        if(baseHost == null)
        {
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;
    }
    
    @After
    public void tearDown() {
    }

    @Test 
    public void testAll() throws Exception {
        testGetAllTags();
        testCreateTag();
        testGetTagByName();
    }
    
    /**
     * Test of getAllTags method, of class TagFacadeREST.
     */
    
    //@Test
    public void testGetAllTags() throws Exception {
        System.out.println("getAllTags");
        
        expect().contentType("application/json").statusCode(200).when().get("/getAllTags");
                
    }

    /**
     * Test of createTag method, of class TagFacadeREST.
     */
    //@Test
    public void testCreateTag() throws Exception {
        System.out.println("createTag");
        
        Tag tag = new Tag();
        tag.setName(getName());
        
        given()
        .contentType("application/json")
        .body(tag)
        .when().post("/createTag").then()
        .statusCode(200);
        
    }

    /**
     * Test of getTagByName method, of class TagFacadeREST.
     */
    //@Test
    public void testGetTagByName() throws Exception {
        System.out.println("getTagByName");
        
        given().contentType("application/json").pathParam("name", getName() ).when().get("/getTagByName/{name}/").then().statusCode(200);
        
    }
    
}
