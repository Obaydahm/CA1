package rest;

import DTO.MembersDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Colour;
import entities.Members;
import utils.EMF_Creator;
import facades.MembersFacade;
import java.util.List;
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
@Path("groupmembers")
public class MembersResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ca1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final MembersFacade FACADE =  MembersFacade.getMembersFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    {
        //populates the database, if its empty
        if(FACADE.getMembersCount() == 0)
        {
            FACADE.createMember("Lars", "lars@cphbusiness.dk", Colour.RED);
            FACADE.createMember("Tobias", "tobias@cphbusiness.dk", Colour.GREEN);
            FACADE.createMember("Erika", "erika@cphbusiness.dk", Colour.YELLOW);
            FACADE.createMember("Louise", "louise@cphbusiness.dk", Colour.YELLOW);
            FACADE.createMember("Mads", "mads@cphbusiness.dk", Colour.GREEN);
        }
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRMembersCount() {
        long count = FACADE.getMembersCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getListOffMembers()
    {
        List<MembersDTO> allMembers = FACADE.getAllMembers();
        return GSON.toJson(allMembers);
    }
        
    

 
}
