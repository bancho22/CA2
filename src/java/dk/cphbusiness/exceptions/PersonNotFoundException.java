/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.exceptions;

/**
 *
 * @author Bancho
 */
public class PersonNotFoundException extends Exception {

    public PersonNotFoundException() {
        
        super("There is no person with the info");
    }
    
    
    
    
}
