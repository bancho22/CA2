/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.exceptions;

/**
 *
 * @author Mato
 */
public class AddressNotFoundException extends Exception{
    


  public AddressNotFoundException(String string) {
    super(string);
  }
  public AddressNotFoundException() {
    super("Address with requested id not found");
  }
  
}
    

