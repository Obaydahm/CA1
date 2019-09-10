/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Car;

/**
 *
 * @author sinanjasar
 */
public class CarDTO {

    private float id;
    private String model;
    private String make;
    private int year;
    private int price;

    public CarDTO(Car car) {
        this.id = car.getId();
        this.model = car.getModel();
        this.make = car.getMake();
        this.year = car.getYear();
        this.price = car.getPrice();
    }

    public float getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

}
