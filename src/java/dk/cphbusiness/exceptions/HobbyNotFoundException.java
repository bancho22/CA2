/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.exceptions;

/**
 *
 * @author user
 */
public class HobbyNotFoundException extends Exception{

    public HobbyNotFoundException(String string) {
    super(string);
    }
    public HobbyNotFoundException() {
            super("There is no person with such hobby");

    }
  
    
}
