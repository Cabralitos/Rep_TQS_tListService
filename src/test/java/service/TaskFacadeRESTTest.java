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
import com.mycompany.servicetlist.Member1;
import com.mycompany.servicetlist.Priority;
import com.mycompany.servicetlist.Tag;
import com.mycompany.servicetlist.Task;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
public class TaskFacadeRESTTest {
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    
    Random random = new Random();
    int randomID = random.nextInt((99 - 1) + 1) + 1;
    private final String description = "Task" + randomID;
    private Date duedate = new Date();
    private int idcategory = 1;
    private int idpriority = 2;
    private int complete = 0;
    private int idtask;

    public String getDescription() {
        return description;
    }

    public Date getDuedate() {
        return duedate;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public int getIdpriority() {
        return idpriority;
    }

    public int getComplete() {
        return complete;
    }

    public int getIdtask() {
        return idtask;
    }
    
        
    public TaskFacadeRESTTest() {
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
            basePath = "/ServicetList/webresources/TaskService";
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
        testCreateTask();
        testGetAllTasks();
        testGetAllTasksByName();
        testCreateTaskTag();
        testGetAllTasksByCategory();
        testGetAllTasksByComplete();
        testGetAllTasksByMember();
        testGetAllTasksByTag();
        testSetPriority();
        testSetState();
    }

    /**
     * Test of createTask method, of class TaskFacadeREST.
     */
    //@Test
    public void testCreateTask() throws Exception {
        System.out.println("createTask");
        
        Category category = given().contentType("application/json").pathParam("name", "Pessoal").when().get("http://localhost:8080/ServicetList/webresources/CategoryService/getCategoryByName/{name}/").as(Category.class);
        
        Random random = new Random();
        int randomID = random.nextInt((3 - 1) + 1) + 1;
        Priority priority = (Priority) given().contentType("application/json").pathParam("idpriority", randomID).when().get("http://localhost:8080/ServicetList/webresources/PriorityService/getPriority/{idpriority}/").as(Priority.class);
       
        Task t = new Task();
        t.setDescription(getDescription());
        t.setComplete((short)1);
        t.setDuedate(new Date());
        t.setIdcategory(category);
        t.setIdpriority(priority);
                
        given()
        .contentType("application/json")
        .body(t)
        .when().post("/createTask").then()
        .statusCode(200);
    
    }

    /**
     * Test of createTaskTag method, of class TaskFacadeREST.
     */
    //@Test
    public void testCreateTaskTag() throws Exception {
        System.out.println("createTaskTag");
        
        Tag[] t = given().contentType("application/json").when().get("http://localhost:8080/ServicetList/webresources/TagService/getAllTags/").as(Tag[].class);         
        
        int randomID = random.nextInt(((t.length-1) - 1) + 1) + 1;
        int id = t[randomID].getIdtag();
        
        given()
        .pathParam("idtask", getIdtask())
        .pathParam("idtag", id)
        .when().post("/createTaskTag/{idtask}/{idtag}").then()
        .statusCode(204);
        
    }


    /**
     * Test of setPriority method, of class TaskFacadeREST.
     */
    //@Test
    public void testSetPriority() throws Exception {
        System.out.println("setPriority");
        
        Random random = new Random();
        int randomPriority = random.nextInt((3 - 1) + 1) + 1;
               
        given().pathParam("idtask", getIdtask()).pathParam("idpriority", randomPriority).when().post("/setPriority/{idtask}/{idpriority}/").then().statusCode(204);
        
    }

    /**
     * Test of getAllTasksByMember method, of class TaskFacadeREST.
     */
    //@Test
    public void testGetAllTasksByMember() throws Exception {
        System.out.println("getAllTasksByMember");
        
        Member1[] m = given().contentType("application/json").when().get("http://localhost:8080/ServicetList/webresources/MemberService/getAllMembers/").as(Member1[].class);         
        
        int randomID = random.nextInt(((m.length-1) - 1) + 1) + 1;
        int id = m[randomID].getIdmember();
        
        given().contentType("application/json").pathParam("idmember", id).when().get("/getAllTasksByMember/{idmember}/").then().statusCode(200);
        
    }

    /**
     * Test of getAllTasksByName method, of class TaskFacadeREST.
     */
    //@Test
    public void testGetAllTasksByName() throws Exception {
        System.out.println("getAllTasksByName");
        
        Task[] t = given().contentType("application/json").pathParam("name", getDescription()).when().get("/getAllTasksByName/{name}/").as(Task[].class);
        idtask = t[0].getIdtask();
    }

    /**
     * Test of getAllTasksByTag method, of class TaskFacadeREST.
     */
    //@Test
    public void testGetAllTasksByTag() throws Exception {
        System.out.println("getAllTasksByTag");
        
        Tag[] t = given().contentType("application/json").when().get("http://localhost:8080/ServicetList/webresources/TagService/getAllTags/").as(Tag[].class);         
        
        int randomID = random.nextInt(((t.length-1) - 1) + 1) + 1;
        int id = t[randomID].getIdtag();
        
        given().contentType("application/json").pathParam("idtag", id).when().get("/getAllTasksByTag/{idtag}/").then().statusCode(200);
        
    }

    /**
     * Test of getAllTasksByComplete method, of class TaskFacadeREST.
     */
    //@Test
    public void testGetAllTasksByComplete() throws Exception {
        System.out.println("getAllTasksByComplete");
        
        Random random = new Random();
        int randomComplete = random.nextInt((1 - 0) + 0) + 1;
        given().contentType("application/json").pathParam("complete", randomComplete).when().get("/getAllTasksByComplete/{complete}/").then().statusCode(200);
        
    }

    /**
     * Test of getAllTasksByCategory method, of class TaskFacadeREST.
     */
    //@Test
    public void testGetAllTasksByCategory() throws Exception {
        System.out.println("getAllTasksByCategory");
        
        given().contentType("application/json").pathParam("idcategory", "1").when().get("/getAllTasksByCategory/{idcategory}/").then().statusCode(200);
        
    }

    /**
     * Test of getAllTasks method, of class TaskFacadeREST.
     */
    //@Test
    public void testGetAllTasks() throws Exception {
        System.out.println("getAllTasks");
        
        expect().contentType("application/json").statusCode(200).when().get("/getAllTasks");
    }

    /**
     * Test of setState method, of class TaskFacadeREST.
     */
    //@Test
    public void testSetState() throws Exception {
        System.out.println("setState");
        
        Random random = new Random();
        int randomComplete = random.nextInt((1 - 0) + 1) + 0;
       
        given().pathParam("idtask", getIdtask()).pathParam("complete", randomComplete).when().post("/setState/{idtask}/{complete}/").then().statusCode(204);

    }
    
}
