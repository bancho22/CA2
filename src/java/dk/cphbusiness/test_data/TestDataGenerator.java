/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.test_data;

import dk.cphbusiness.entity.Company;
import dk.cphbusiness.entity.Person;
import dk.cphbusiness.entity.Phone;
import dk.cphbusiness.facade.InfoEntityFacade;
import dk.cphbusiness.facade.PhoneFacade;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Bancho
 */
public class TestDataGenerator {
    
    public static void main(String[] args) {
        populateTables("CA2PU");
    }
    
    private static Random rand;
    
    private static InfoEntityFacade ief;
    private static PhoneFacade pf;
    
    public static void populateTables(String persistenceUnitName){
        
        Persistence.generateSchema(persistenceUnitName, null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        
        rand = new Random();
        ief = new InfoEntityFacade(emf);
        pf = new PhoneFacade(emf);
        
        populateInfoEntityTable();
        populatePhoneTable();
    }
    
    
    private static void populateInfoEntityTable(){
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
    
    
    private static void populatePhoneTable(){
        final int UPPER_BOUND = 99999999 + 1;
        final int LOWER_BOUND = 77777777;
        int[] phoneNumCombiations = new int[UPPER_BOUND - LOWER_BOUND];
        
        for (int i = LOWER_BOUND; i < UPPER_BOUND; i++) {
            phoneNumCombiations[i - LOWER_BOUND] = i;
        }
        
        for (int i = 0; i < 600; i++) {
            Phone phone = new Phone();
            phone.setNumber("+45" + phoneNumCombiations[rand.nextInt(UPPER_BOUND - LOWER_BOUND)]);
            phone.setOwner(ief.getInfoEntity(i/3+1));
            phone.setDescription("blah-blah");
            pf.addPerson(phone);
        }
    }
    
}
