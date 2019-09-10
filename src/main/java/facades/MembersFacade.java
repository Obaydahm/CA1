package facades;

import DTO.MembersDTO;
import entities.Members;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
    

}
