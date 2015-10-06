/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.facade_test;

import dk.cphbusiness.entity.Company;
import dk.cphbusiness.entity.InfoEntity;
import dk.cphbusiness.entity.Person;
import dk.cphbusiness.entity.Phone;
import dk.cphbusiness.exceptions.AddressNotFoundException;
import dk.cphbusiness.exceptions.PhoneDoesNotBelongToPersonException;
import dk.cphbusiness.exceptions.PhoneNotFoundException;
import dk.cphbusiness.facade.InfoEntityFacade;
import dk.cphbusiness.facade.PhoneFacade;
import dk.cphbusiness.test_data.TestDataGenerator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Persistence;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bancho
 */
public class InfoEntityFacadeTest {

    private static final String PERSISTENCE_UNIT_NAME = "CA2PU";
    private InfoEntityFacade ief;
    private PhoneFacade pf;

    public InfoEntityFacadeTest() {
        ief = new InfoEntityFacade(Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME));
        pf = new PhoneFacade(Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME));
    }
    
    @BeforeClass
    public static void setUpDataBase() throws AddressNotFoundException{
        TestDataGenerator.populateTables(PERSISTENCE_UNIT_NAME);
    }

    @Test
    public void getInfoEntityTest() {
        InfoEntity ie = ief.getInfoEntity(1);
        //System.out.println(ie.getPhones());
        assertTrue(ie.getId() == 1);
    }
    
    @Test
    public void getCompaniesByNumEmplTest(){
        int limit = 500;
        List<Company> companies = (List<Company>) ief.getCompanies(limit);
        
        //testing boundary conditions
        Collections.sort(companies, new Comparator<Company>() {

            @Override
            public int compare(Company c1, Company c2) {
                return c1.getNumEmployees() - c2.getNumEmployees();
            }
        });
        
        assertTrue(companies.get(0).getNumEmployees() > limit);
    }

    @Test
    //for this test, I trust the methods in the PhoneFacade class
    public void getPersonByPhoneTest() throws PhoneNotFoundException, PhoneDoesNotBelongToPersonException{
        Phone phone = pf.getPhone(200);
        
        InfoEntity owner = phone.getOwner();
        
        if (owner.getClass().equals(Person.class) == false) {
            fail("The owner of the supplied phone number is not a person, but a company.");
        }
        
        Person expected = (Person) owner;
        Person result = ief.getPersonByPhone(phone.getNumber());
//        System.out.println(expected.getPhones());
//        System.out.println(result.getPhones());
        assertTrue("expected: " + expected.getFirstName() + ", result: " + result.getFirstName(), expected.getFirstName().equals(result.getFirstName()));
    }
}
