package dk.cphbusiness.exceptions;

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

    

