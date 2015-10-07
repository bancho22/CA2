/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.test_data;

import dk.cphbusiness.entity.Address;
import dk.cphbusiness.entity.Company;
import dk.cphbusiness.entity.Hobby;
import dk.cphbusiness.entity.InfoEntity;
import dk.cphbusiness.entity.Person;
import dk.cphbusiness.entity.Phone;
import dk.cphbusiness.exceptions.AddressNotFoundException;
import dk.cphbusiness.facade.AddressFacade;
import dk.cphbusiness.facade.CityInfoFacade;
import dk.cphbusiness.facade.HobbyFacade;
import dk.cphbusiness.facade.InfoEntityFacade;
import dk.cphbusiness.facade.PhoneFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Bancho
 */
public class TestDataGenerator {
    
    public static void main(String[] args) throws AddressNotFoundException {
        populateTables("CA2PU");
    }
    
    private static Random rand;
    
    private static InfoEntityFacade ief;
    private static PhoneFacade pf;
    private static AddressFacade af;
    private static CityInfoFacade cif;
    private static HobbyFacade hf;
    
    public static void populateTables(String persistenceUnitName) throws AddressNotFoundException{
        
        Persistence.generateSchema(persistenceUnitName, null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        
        rand = new Random();
        ief = new InfoEntityFacade(emf);
        pf = new PhoneFacade(emf);
        af = new AddressFacade(emf);
        cif = new CityInfoFacade(emf);
        hf = new HobbyFacade(emf);
        
        populateAddressTable();
        populateHobbyTable();
        populateInfoEntityTable();
        populatePhoneTable();
    }
    
    
    private static void populateInfoEntityTable() throws AddressNotFoundException{
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
        
        int addressID = 0;
        for (int i = 0; i < 100; i++) {
            Person p = new Person();
            p.setFirstName(firstNames[rand.nextInt(NAME_ARRAYS_LENGTH)]);
            p.setLastName(lastNames[rand.nextInt(NAME_ARRAYS_LENGTH)]);
            p.setEmail(p.getFirstName().toLowerCase() + "-" + p.getLastName().toLowerCase() + "@gmail.com");
            
            if (i % 2 == 0) {
                addressID++;
            }
            p.setAddress(af.getAddress(addressID));
            
            ArrayList<Hobby> hobbies = new ArrayList<Hobby>();
//            ArrayList<Integer> hobbiesPicked = new ArrayList<Integer>();
//            for (int j = 0; j < 3; j++) {
//                if (rand.nextInt(2) % 2 == 0) {
//                    int idPicked;
//                    do {                        
//                        idPicked = rand.nextInt(5) + 1;
//                    } while (idAlreadyPicked(idPicked, hobbiesPicked));
//                    hobbiesPicked.add(idPicked);
//                    hobbies.add(hf.getHobby(idPicked));
//                }
//                hobbiesPicked.clear();
//            }
            hobbies.add(hf.getHobby(rand.nextInt(5) + 1));
            p.setHobbies(hobbies);
            
            ief.addInfoEntity(p);
        }
        
        addressID = 50;
        for (int i = 0; i < 100; i++) {
            Company c = new Company();
            c.setEmail("comp" + i + "@info.com");
            c.setName("comp" + i);
            c.setDescription("desc of comp" + i);
            c.setCvr(Integer.toString(rand.nextInt(1000)) + "-" + Integer.toString(rand.nextInt(1000)) + "-" + Integer.toString(rand.nextInt(1000)));
            c.setNumEmployees(rand.nextInt(1000));
            c.setMarketValue(rand.nextInt(1000000000));
            c.setAddress(af.getAddress(++addressID));
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
            pf.addPhone(phone);
        }
    }
    
    
    private static void populateAddressTable(){
        final int NUM_OF_ZIP_CODES = 1352;
        final int STREET_NAMES_ARRAY_LENGTH = 2;
        String[] streetNames = new String[STREET_NAMES_ARRAY_LENGTH];
        streetNames[0] = "Baker st.";
        streetNames[1] = "Spooner st.";
        
        for (int i = 0; i < 150; i++) {
            Address address = new Address();
            address.setStreet(streetNames[rand.nextInt(STREET_NAMES_ARRAY_LENGTH)]);
            address.setAdditionalInfo("some info");
            address.setCityInfo(cif.getCityInfo(rand.nextInt(NUM_OF_ZIP_CODES) + 1));
            af.addAddress(address);
        }
    }
    
    
    private static void populateHobbyTable(){
        final int HOBBIES_ARRAY_LENGTH = 5;
        String[] hobbies = new String[HOBBIES_ARRAY_LENGTH];
        hobbies[0] = "drinking";
        hobbies[1] = "swimming";
        hobbies[2] = "stamp-collecting";
        hobbies[3] = "bullying";
        hobbies[4] = "coding";
        
        for (int i = 0; i < HOBBIES_ARRAY_LENGTH; i++) {
            Hobby hobby = new Hobby();
            hobby.setName(hobbies[i]);
            hobby.setDescription("some cool desc here");
            hf.addHobby(hobby);
        }
    }
    
    
//    private static boolean idAlreadyPicked(int n, ArrayList<Integer> list){
//        for (Integer i : list) {
//            if (n == i) {
//                return true;
//            }
//        }
//        return false;
//    }
}
