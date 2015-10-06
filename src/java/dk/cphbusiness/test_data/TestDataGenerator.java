/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.test_data;

import dk.cphbusiness.entity.Company;
import dk.cphbusiness.entity.Person;
import dk.cphbusiness.facade.InfoEntityFacade;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Bancho
 */
public class TestDataGenerator {
    
    private static Random rand;
    
    public static void populateTables(){
        
        Persistence.generateSchema("CA2PU", null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2PU");
        
        rand = new Random();
        
        populateInfoEntityTable(emf);
        
    }
    
    
    private static void populateInfoEntityTable(EntityManagerFactory emf){
        InfoEntityFacade ief = new InfoEntityFacade(emf);
        
        final int NAME_ARRAYS_LENGTH = 7;
        
        String[] firstNames = new String[NAME_ARRAYS_LENGTH];
        String[] lastNames = new String[NAME_ARRAYS_LENGTH];
        
        firstNames[0] = "Jim";
        firstNames[1] = "Johnny";
        firstNames[2] = "Jack";
        firstNames[3] = "Captian";
        firstNames[4] = "Alex";
        firstNames[5] = "Bancho";
        firstNames[6] = "Martin";
        
        lastNames[0] = "Daniels";
        lastNames[1] = "Morgan";
        lastNames[2] = "Beam";
        lastNames[3] = "Walker";
        lastNames[4] = "Gyurov";
        lastNames[5] = "Petrov";
        lastNames[6] = "Macej";
        
        for (int i = 0; i < 100; i++) {
            Person p = new Person();
            p.setFirstName(firstNames[rand.nextInt(NAME_ARRAYS_LENGTH)]);
            p.setLastName(lastNames[rand.nextInt(NAME_ARRAYS_LENGTH)]);
            p.setEmail(p.getFirstName().toLowerCase() + "-" + p.getLastName().toLowerCase() + "@gmail.com");
            ief.addInfoEntity(p);
        }
        
        for (int i = 0; i < 100; i++) {
            Company c = new Company();
            c.setEmail("comp" + i + "@info.com");
            c.setName("comp" + i);
            c.setDescription("desc of comp" + i);
            c.setCvr(Integer.toString(rand.nextInt(1000)) + "-" + Integer.toString(rand.nextInt(1000)) + "-" + Integer.toString(rand.nextInt(1000)));
            c.setNumEmployees(rand.nextInt(1000));
            c.setMarketValue(rand.nextInt(1000000000));
            ief.addInfoEntity(c);
        }
    }
    
}
