package facades;

import DTO.MembersDTO;
import entities.Colour;
import utils.EMF_Creator;
import entities.Members;
import java.awt.Color;
import java.util.List;
import javassist.CtClass;
import javassist.bytecode.annotation.Annotation;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MembersFacadeTest {

    private static EntityManagerFactory emf;
    private static MembersFacade facade;
    
    Members memberUsedByIdTest = new Members("Bob", "bob@cphbusiness.dk", Colour.GREEN);

    public MembersFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = MembersFacade.getMembersFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = MembersFacade.getMembersFacade(emf);
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
            em.createNamedQuery("Members.deleteAllRows").executeUpdate();
            em.persist(new Members("Tom", "Tom@cphbusiness.dk", Colour.GREEN));
            em.persist(new Members("Lone", "Lone@cphbusiness.dk", Colour.YELLOW));
            em.persist(new Members("Sigurd", "Sigurd@cphbusiness.dk", Colour.RED));
            em.persist(memberUsedByIdTest);
            
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testGetMembersCount() {
        assertEquals(4, facade.getMembersCount(), "Expects two rows in the database");
    }
    
    @Test
    public void testGetMemberById()
    {
        //Arrange
        MembersDTO member;
        
        //Act
        member = facade.getMemberById( memberUsedByIdTest.getId());
        
        //Arrange
        assertEquals("Bob", member.getName());
        assertEquals(Colour.GREEN, member.getColourLevelOfStudent());
    }
    
    @Test
    public void testGetAllMembers()
    {
        //Arrange
        List<MembersDTO> allMembers;
        
        //Act
        allMembers = facade.getAllMembers();
        
        //Assert
        assertEquals(4, allMembers.size());
    }
    
    @Test
    public void testCreateMember()
    {
        //Arrange
        Long count;
        
        //Act
        count = facade.getMembersCount();
        facade.createMember(new Members("Sine", "sine@cphbusiness.dk", Colour.RED));
        
        //Assert (if the member above got persisted, the members count should be equal to the count before it got persisted +1 )
        assertEquals(count+1, facade.getMembersCount());
    }
    
   

}
