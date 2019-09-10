package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "Members.deleteAllRows", query = "DELETE from Members")
public class Members implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name, email;
    @Enumerated(EnumType.STRING)
    private Colour colourLevelOfStudent;
    
    public Members() {
    }
    
    public Members(String name, String email, Colour colourLevelOfStudent)
    {
        this.name = name;
        this.email = email;
        this.colourLevelOfStudent = colourLevelOfStudent;
    }
        
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Colour getColourLevelOfStudent() {
        return colourLevelOfStudent;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setColourLevelOfStudent(Colour colourLevelOfStudent) {
        this.colourLevelOfStudent = colourLevelOfStudent;
    }
    
    
    
    
    
   
    


   

   
    
    
    
    

   
}
