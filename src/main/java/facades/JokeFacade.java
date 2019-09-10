/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Joke;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Obaydah Mohamad
 */
public class JokeFacade {
    
    private static JokeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private JokeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getJokeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<Joke> getAllJokes(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Joke> tq = em.createNamedQuery("SELECT j FROM Joke j", Joke.class);
            return tq.getResultList();
        }finally{
            em.close();
        }
    }
    
    public Joke getJokeById(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(Joke.class, id);
        }finally{
            em.close();
        }
    }
    
    public Joke getRandomJoke(){
        List<Joke> jokes = getAllJokes();
        Random r = new Random();
        return jokes.get(r.nextInt(jokes.size()));
    }
}
