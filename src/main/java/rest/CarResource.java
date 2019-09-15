package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Car;
import facades.CarFacade;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("car")
public class CarResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/ca1",
            "dev",
            "ax2",
            EMF_Creator.Strategy.DROP_AND_CREATE);
    private static final CarFacade FACADE = CarFacade.getCarFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

//    {
//        EntityManager em = EMF.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
//            em.persist(new Car(2019, "Mercedes Benz", 600000, "CLS"));
//            em.persist(new Car(1999, "BMW", 1000000, "M8"));
//            em.persist(new Car(2017, "Random1", 900000, "random1"));
//            em.persist(new Car(2019, "Random2", 1500000, "random2"));
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllCars() {
        return GSON.toJson(FACADE.allCars());
    }
}
