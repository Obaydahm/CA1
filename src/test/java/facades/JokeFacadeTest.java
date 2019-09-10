package facades;

import DTO.JokeDTO;
import entities.Joke;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class JokeFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade facade;
    Joke j = new Joke("En røver kommer ind i butikken og stjæler et TV. blondinen løber efter ham og råber, “Vent, du har glemt fjernbetjeningen!”",
            "Blondine", "vitser-jokes.dk");
    Joke j2 = new Joke("Hvad laver edderkoppen når den keder sig? – Den går på nettet", "Dårlige", "vitser-jokes.dk");
    Joke j3 = new Joke("Din mor er så fed, at hvis hun går forbi tv’et går man forbi 3 reklamer.", "Din mor", "vitser-jokes.dk");
    Joke j4 = new Joke("Min datter skreg “Faar hører du overhovedet efter?!!!?!”\n"
            + "– Det en mærkelig måde, at starte en samtale på…", "Far", "vitser-jokes.dk");
    Joke j5 = new Joke("Hvad er ordet som man aldrig vil kalde en sort mand? Det starter med \n" + "– Nabo",
            "Grove", "vitser-jokes.dk");

    public JokeFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = JokeFacade.getJokeFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = JokeFacade.getJokeFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
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
            em.getTransaction().begin();
            em.persist(j4);
            em.getTransaction().commit();
            em.getTransaction().begin();
            em.persist(j5);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testGetAllJokes() throws Exception {
        System.out.println("1. Get get all jokes test");
        // assertEquals(2, facade.getRenameMeCount(), "Expects two rows in the database");
        assertEquals(5, facade.getAllJokes().size(), "Expects two rows in the database");
        assertEquals("En røver kommer ind i butikken og stjæler et TV. blondinen løber efter ham og råber, “Vent, du har glemt fjernbetjeningen!”", facade.getAllJokes().get(0).getJoke());
        assertEquals("Hvad laver edderkoppen når den keder sig? – Den går på nettet", facade.getAllJokes().get(1).getJoke());
        assertEquals("Din mor er så fed, at hvis hun går forbi tv’et går man forbi 3 reklamer.", facade.getAllJokes().get(2).getJoke());

    }

    @Test
    public void testGetJokeById() throws Exception {
        JokeDTO jDTO = facade.getJokeById(j.getId());
        assertEquals(j.getId().longValue(), jDTO.getId().longValue());
        assertEquals(j.getType(), jDTO.getType());
        assertEquals(j.getJoke(), jDTO.getJoke());
        
        jDTO = facade.getJokeById(j2.getId());
        assertEquals(j2.getId().longValue(), jDTO.getId().longValue());
        assertEquals(j2.getType(), jDTO.getType());
        assertEquals(j2.getJoke(), jDTO.getJoke());
        
        jDTO = facade.getJokeById(j3.getId());
        assertEquals(j3.getId().longValue(), jDTO.getId().longValue());
        assertEquals(j3.getType(), jDTO.getType());
        assertEquals(j3.getJoke(), jDTO.getJoke());
        
        jDTO = facade.getJokeById(j4.getId());
        assertEquals(j4.getId().longValue(), jDTO.getId().longValue());
        assertEquals(j4.getType(), jDTO.getType());
        assertEquals(j4.getJoke(), jDTO.getJoke());
        
        jDTO = facade.getJokeById(j5.getId());
        assertEquals(j5.getId().longValue(), jDTO.getId().longValue());
        assertEquals(j5.getType(), jDTO.getType());
        assertEquals(j5.getJoke(), jDTO.getJoke());
    }

    @Test
    public void testGetRandomJoke() throws Exception {
        System.out.println("3. Get random joke test");
    }

}
