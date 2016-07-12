/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import com.mycompany.servicetlist.Member1;
import com.mycompany.servicetlist.Task;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class Member1FacadeRESTTest {
    
    Random random = new Random();
    int randomID = random.nextInt((99 - 1) + 1) + 1;
    private int idmember;
    private String username = "User" + randomID;
    private String password = "Password" + randomID;

    public int getIdmember() {
        return idmember;
    }

       
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public Member1FacadeRESTTest() {
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
            basePath = "/ServicetList/webresources/MemberService";
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
        testCreateMember();
        testConfirmLogin();
        testGetAllMembers();
        //testCreateTaskMember();
    }
    
    
    /**
     * Test of getAllMembers method, of class Member1FacadeREST.
     */
    
    //@Test
    public void testGetAllMembers() throws Exception {
        System.out.println("getAllMembers");
        
        given().contentType("application/json").when().get("/getAllMembers").then().statusCode(200);    
        
    }

    /**
     * Test of confirmLogin method, of class Member1FacadeREST.
     */
    
    //@Test
    public void testConfirmLogin() throws Exception {
        System.out.println("confirmLogin");
        
        Member1 member = new Member1();
        member.setUsername(getUsername());
        member.setPassword(getPassword());
        
        idmember = given().contentType("application/json").body(member).when().post("/confirmLogin").then().statusCode(200).extract().jsonPath().getInt("idmember");
        
    }

    /**
     * Test of createMember method, of class Member1FacadeREST.
     */
    //@Test
    public void testCreateMember() throws Exception {
        System.out.println("createMember");
        
        Member1 member = new Member1();
        member.setUsername(getUsername());
        member.setPassword(getPassword());

        given()
        .contentType("application/json")
        .body(member)
        .when().post("/createMember").then()
        .statusCode(204);

    }

    /**
     * Test of createTaskMember method, of class Member1FacadeREST.
     */
    //@Test
    public void testCreateTaskMember() throws Exception {
        System.out.println("createTaskMember");
        
        List<Task> idtask = (List<Task>) given().contentType("application/json").when().get("http://localhost:8080/ServicetList/webresources/TaskService/getAllTasks").then().statusCode(200).extract().response();         
        int randomID = random.nextInt(((idtask.size()-1) - 1) + 1) + 1;
        int id = idtask.get(randomID).getIdtask();
        
        System.out.println(id);
        given().pathParam("idtask", id).pathParam("idmember", getIdmember()).when().get("/createTaskMember/{idtask}/{idmember}/").then().statusCode(204);
        
    }


}
