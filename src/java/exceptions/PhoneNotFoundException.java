/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Mato
 */
public class PhoneNotFoundException extends Exception{
    


  public PhoneNotFoundException(String string) {
    super(string);
  }
  public PhoneNotFoundException() {
    super("Phone with requested id not found");
  }
  
}

    

