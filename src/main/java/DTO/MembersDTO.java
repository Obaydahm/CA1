/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Colour;
import entities.Members;

/**
 *
 * @author Kasper Jeppesen
 */
public class MembersDTO 
{
    private Long id;
    private String name;
    private Colour colourLevelOfStudent;
    
    public MembersDTO(Members member)
    {
        this.id = member.getId();
        this.name = member.getName();
        this.colourLevelOfStudent = member.getColourLevelOfStudent();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Colour getColourLevelOfStudent() {
        return colourLevelOfStudent;
    }
    
    
}
