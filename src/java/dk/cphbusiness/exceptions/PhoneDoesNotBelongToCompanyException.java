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
public class PhoneDoesNotBelongToCompanyException extends Exception {

    public PhoneDoesNotBelongToCompanyException() {
        super("There is no company with the provided phone");
    }
    
    
}
