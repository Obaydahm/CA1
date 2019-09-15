package rest;

import entities.Joke;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class JokeResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    //Read this line from a settings-file  since used several places
    private static final String TEST_DB = "jdbc:mysql://localhost:3307/startcode_test";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    
    Joke j = new Joke("Hvad sagde koen til kyllingen?: muuuuuuuuuuuuuuuuuh", "Ko og Kylling", "Cartoonnetwork");
    Joke j2 = new Joke("Hvad sagde Pickachu til Ash: Picka Picka", "Pokemon", "Cartoonnetwork");
    Joke j3 = new Joke("Joke3", "Kategori3", "google");
            
    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.CREATE);

        //NOT Required if you use the version of EMF_Creator.createEntityManagerFactory used above        
        //System.setProperty("IS_TEST", TEST_DB);
        //We are using the database on the virtual Vagrant image, so username password are the same for all dev-databases
        
        httpServer = startServer();
        
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
   
        RestAssured.defaultParser = Parser.JSON;
    }
    
    @AfterAll
    public static void closeTestServer(){
        //System.in.read();
         httpServer.shutdownNow();
    }
    
    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(j);
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            em.persist(j2);
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            em.persist(j3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    
    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/joke").then().statusCode(200);
    }
   
    //This test assumes the database contains two rows
    
    @Test
    public void testDummyMsg() throws Exception {
        given()
        .contentType("application/json")
        .get("/joke/").then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .body("msg", equalTo("Hello World"));   
    }
    
    @Test
    public void testGetAll() throws Exception {
        given()
        .contentType("application/json")
        .get("/joke/all").then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .log().body()
        .body("[0].joke", equalTo("Hvad sagde koen til kyllingen?: muuuuuuuuuuuuuuuuuh"))
        .and()
        .body("[1].type", equalTo("Pokemon"))
        .and()  
        .body("[2].type", equalTo("Kategori3"))
        .and()
        .body("size()", equalTo(3));
        
    }
    
    @Test
    public void testGetById() throws Exception {
        //Joke 1
        given()
        .contentType("application/json")
        .get("/joke/" + j.getId()).then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .log().body()
        .body("id", equalTo(j.getId().intValue()))
        .and()
        .body("joke", equalTo(j.getJoke()))
        .and()  
        .body("type", equalTo(j.getType()));
        
        //Joke 2
        given()
        .contentType("application/json")
        .get("/joke/" + j2.getId()).then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .log().body()
        .body("id", equalTo(j2.getId().intValue()))
        .and()
        .body("joke", equalTo(j2.getJoke()))
        .and()  
        .body("type", equalTo(j2.getType()));
        
        //Joke 3
        given()
        .contentType("application/json")
        .get("/joke/" + j3.getId()).then()
        .assertThat()
        .statusCode(HttpStatus.OK_200.getStatusCode())
        .log().body()
        .body("id", equalTo(j3.getId().intValue()))
        .and()
        .body("joke", equalTo(j3.getJoke()))
        .and()  
        .body("type", equalTo(j3.getType()));
    }
}
