package facades;

import DTO.MembersDTO;
import entities.Colour;
import entities.Members;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MembersFacade {

    private static MembersFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MembersFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MembersFacade getMembersFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MembersFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getMembersCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(m) FROM Members m").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
    }
    
    public MembersDTO getMemberById(Long id)
    {
         //open a database connection (and creates a database, if one doesnt exists)
        EntityManager em = emf.createEntityManager();
        
        try
        {
            return new MembersDTO(em.find(Members.class, id));
        }
        finally
        {
            em.close();
        }
    }
    
    public List<MembersDTO> getAllMembers()
    {
        //open a database connection (and creates a database, if one doesnt exists)
        EntityManager em = emf.createEntityManager();
        
        try
        {
            TypedQuery<Members> query = 
                       em.createQuery("Select m from Members m",Members.class);
            
            List<Members> allMembers =  query.getResultList();
            
            List<MembersDTO> allMembersDTO = new LinkedList<>();
            
            for(Members member : allMembers)
            {
                allMembersDTO.add(new MembersDTO(member));
            }
            
            return allMembersDTO;
        }
        finally
        {
            em.close();
        }
    }
    
    public Members createMember(String name, String email, Colour colourLevelOfStudent)
    {
        //open a database connection (and creates a database, if one doesnt exists)
        EntityManager em = emf.createEntityManager();
        
        try
        {
            Members member = new Members(name, email, Colour.GREEN);
            
            em.getTransaction().begin();
            em.persist(member);
            em.getTransaction().commit();
            
            return member;
        }
        finally
        {
            em.close();
        }
    }
    

}
