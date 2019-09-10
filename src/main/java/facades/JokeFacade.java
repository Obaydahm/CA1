/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTO.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
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
    
    public List<JokeDTO> getAllJokes(){
        EntityManager em = emf.createEntityManager();
        List<JokeDTO> dtoList = new ArrayList<>();
        try{
            TypedQuery<Joke> tq = em.createNamedQuery("SELECT j FROM Joke j", Joke.class);
            List<Joke> jokes = tq.getResultList();
            for(Joke j : jokes){
                dtoList.add(new JokeDTO(j));
            }
            return dtoList;
        }finally{
            em.close();
        }
    }
    
    public JokeDTO getJokeById(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            return new JokeDTO(em.find(Joke.class, id));
        }finally{
            em.close();
        }
    }
    
    public JokeDTO getRandomJoke(){
        List<JokeDTO> dtoList = getAllJokes();
        Random r = new Random();
        return dtoList.get(r.nextInt(dtoList.size()));
    }
}
