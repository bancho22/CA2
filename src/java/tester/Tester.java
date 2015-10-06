/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import dk.cphbusiness.entity.Company;
import dk.cphbusiness.entity.Person;
import dk.cphbusiness.facade.InfoEntityFacade;
import dk.cphbusiness.test_data.TestDataGenerator;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Bancho
 */
public class Tester {
    
    public static void main(String[] args) {
//        Persistence.generateSchema("CA2PU", null);
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2PU");
        
//        Person p = new Person();
//        p.setFirstName("Bancho");
//        p.setLastName("Petrov");
//        p.setEmail("banchopetrov@gmail.com");
        
//        InfoEntityFacade ief = new InfoEntityFacade(Persistence.createEntityManagerFactory("CA2PU"));
        
//        ief.addInfoEntity(p);
//        System.out.println(p.getId());
        
//        Person p = (Person) ief.getInfoEntity(1);
//        System.out.println(p.getId() + ". " + p.getFirstName());
        
        TestDataGenerator.populateTables();
        
    }
    
}
