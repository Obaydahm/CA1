/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Joke;

/**
 *
 * @author Obaydah Mohamad
 */
public class JokeDTO {
    private Long id;
    private String joke;
    private String type;

    public JokeDTO(Joke j){
        this.id = j.getId();
        this.joke = j.getJoke();
        this.type = j.getType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
