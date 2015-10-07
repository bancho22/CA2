package dk.cphbusiness.facade;

import dk.cphbusiness.entity.Phone;
import dk.cphbusiness.exceptions.PhoneNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mato
 */
public class PhoneFacade {

    //get,add,edit,delete
    private EntityManagerFactory emf;

    public PhoneFacade(EntityManagerFactory e) {
    emf = e;
  }

    public EntityManager getEntityManager() {

        return emf.createEntityManager();

    }

    public Phone addPhone(Phone p) {
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
        throw new PhoneNotFoundException("No phone found with provided id");
      }
      em.getTransaction().begin();
      em.remove(p);
      em.getTransaction().commit();
      return p;
    } finally {
      em.close();
    }
  }
    
    
    public Phone getPhone(int id) throws PhoneNotFoundException {
    EntityManager em = getEntityManager();
    try {
      Phone p = em.find(Phone.class,id);
       if(p == null){
        throw new PhoneNotFoundException("No phone found with provided id");
      }
       return p;
    } finally {
      em.close();
    }
  }
   
    
    public Phone editPhone(Phone p) throws PhoneNotFoundException{
        EntityManager em = getEntityManager();
        try{
            if(p==null){
                throw new PhoneNotFoundException("NO PERSON FOUND WITH PROVIDED ID");
            }
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            return p;
        }finally{
            em.close();
        }
        
    }
     

}
