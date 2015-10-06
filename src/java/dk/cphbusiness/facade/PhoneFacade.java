/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.facade;

import dk.cphbusiness.entity.Phone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mato
 */
public class PhoneFacade {

    //get,add,edit,delete
    private EntityManagerFactory emf;

    public PhoneFacade() {
    }

    public EntityManager getEntityManager() {

        return emf.createEntityManager();

    }

    public Phone addPerson(Phone p) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }
    
    
    public Phone deletePhone(int id)throws PhoneNotFoundException{
    EntityManager em = getEntityManager();
    try {
      Phone p = em.find(Phone.class, id);
      if(p == null){
        throw new Phone("No phone found with provided id");
      }
      em.getTransaction().begin();
      em.remove(p);
      em.getTransaction().commit();
      return p;
    } finally {
      em.close();
    }
  }

}
