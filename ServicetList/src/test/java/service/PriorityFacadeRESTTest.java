/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import com.mycompany.servicetlist.Priority;
import java.util.List;
import java.util.Random;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joana Silva
 */
public class PriorityFacadeRESTTest {
    
    public PriorityFacadeRESTTest() {
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
            basePath = "/ServicetList/webresources/PriorityService";
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
        testGetAllPriorities();
        testGetIDPriority();
        testGetPriority();
    }
    
    /**
     * Test of getAllPriorities method, of class PriorityFacadeREST.
     */
    
    //@Test
    public void testGetAllPriorities() throws Exception {
        System.out.println("getAllPriorities");
        
        expect().contentType("application/json").statusCode(200).when().get("/getAllPriorities");

    }

    /**
     * Test of getPriority method, of class PriorityFacadeREST.
     */
    //@Test
    public void testGetPriority() throws Exception {
        System.out.println("getPriority");
        
        Random random = new Random();
        int randomID = random.nextInt((3 - 1) + 1) + 1;
        given().contentType("application/json").pathParam("idpriority", randomID).when().get("/getPriority/{idpriority}/").then().statusCode(200);

    }

    /**
     * Test of getIDPriority method, of class PriorityFacadeREST.
     */
    //@Test
    public void testGetIDPriority() throws Exception {
        System.out.println("getIDPriority");
        
        Random random = new Random();
        int randomID = random.nextInt((3 - 1) + 1) + 1;
        given().pathParam("value", randomID).when().get("/getIDPriority/{value}/").then().statusCode(200);

    }
    
}
