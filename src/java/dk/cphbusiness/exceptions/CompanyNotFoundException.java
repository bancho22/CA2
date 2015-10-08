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
public class CompanyNotFoundException extends Exception {

    public CompanyNotFoundException(String message) {
                    super("The Company you requested has not been found");

    }
        
}
