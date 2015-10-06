/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Bancho
 */
public class Tester {
    
    public static void main(String[] args) {
        Persistence.generateSchema("CA2PU", null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2PU");
    }
    
}
