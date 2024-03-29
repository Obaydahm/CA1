/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author sinanjasar
 */
@Entity
@NamedQuery(name = "Car.deleteAllRows", query = "DELETE from Car")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private String make;
    private int price;
    private String model;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created;
    private String owner;
    
    public Car() {
    }

    public Car(int year, String make, int price, String model, String owner) {
        this.year = year;
        this.make = make;
        this.price = price;
        this.model = model;
        this.created = new Date();
        this.owner = owner;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public int getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public Date getCreated() {
        return created;
    }

    public String getOwner() {
        return owner;
    }
    

}
