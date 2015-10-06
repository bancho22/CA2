/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.facade;

import dk.cphbusiness.entity.Address;
import dk.cphbusiness.entity.CityInfo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author user
 */
public class CityInfoFacade {

    //private CityInfo cityInfo;
    //GET,ADD,EDIT,DELETE

    private EntityManagerFactory emf;

    public CityInfoFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CityInfo getCityInfo(int id) {
        EntityManager em = getEntityManager();
        CityInfo cityInfo = null;
        try {

            cityInfo = em.find(CityInfo.class, id);
//            System.out.println(cityInfo.toString());

        } finally {
            em.close();
        }
        return cityInfo;
    }

    public CityInfo addCityInfo(CityInfo cityInfo) {
        EntityManager em = getEntityManager();

        try {

//            System.out.println(cityInfo.toString());
            em.getTransaction().begin();
            em.persist(cityInfo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return cityInfo;

    }

    public CityInfo editCityInfo(CityInfo cityInfo) {
        EntityManager em = getEntityManager();

        try {

            em.getTransaction().begin();
            em.merge(cityInfo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return cityInfo;

    }

    public CityInfo deleteCityInfo(int id) {
        EntityManager em = getEntityManager();
        CityInfo cityInfo = null;

        try {

            cityInfo = em.find(CityInfo.class, id);

            em.getTransaction().begin();
            em.remove(cityInfo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return cityInfo;

    }

//    public List<Address> getAddresses(){
//         EntityManager em = getEntityManager();
//        List<Address> addresses = new ArrayList<Address>();
//        try{
//        Query query = em.createNamedQuery("Address.findAll");
//            addresses = query.getResultList();
//        }finally{
//            em.close();
//        }
//        return addresses;
//    }
}
