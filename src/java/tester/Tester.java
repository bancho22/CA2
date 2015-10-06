package tester;

<<<<<<< HEAD
import dk.cphbusiness.entity.CityInfo;
import dk.cphbusiness.facade.CityInfoFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
=======

import dk.cphbusiness.test_data.TestDataGenerator;
>>>>>>> 924c0548eda54aecab0b9422a156683deae8833b

/**
 *
 * @author Bancho
 */
public class Tester {
    
    public static void main(String[] args) {
<<<<<<< HEAD
        Persistence.generateSchema("CA2PU", null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2PU");
        CityInfoFacade cityInfoFacade = new CityInfoFacade(emf);
        CityInfo cityInfo = new CityInfo();
        cityInfo.setCity("Columbia");
        cityInfo.setZipCode("1300");
        
        cityInfo =cityInfoFacade.addCityInfo(cityInfo);
        System.out.println(cityInfoFacade.getCityInfo(cityInfo.getId()).getId());
        
        
=======
        
        TestDataGenerator.populateTables();
>>>>>>> 924c0548eda54aecab0b9422a156683deae8833b
        
    }
    
}
