package dk.cphbusiness.facade;

import dk.cphbusiness.entity.Address;
import dk.cphbusiness.exceptions.AddressNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Mato
 */
public class AddressFacade {
    
    //get,add,edit,delete
    //street, AdditionalInfo
    private EntityManagerFactory emf;

    public AddressFacade(EntityManagerFactory e) {
    emf = e;
  }

    public EntityManager getEntityManager() {

        return emf.createEntityManager();

    }

    public Address addAddress(Address a) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
            return a;
        } finally {
            em.close();
        }
    }
    
    
    public Address deleteAddress(int id)throws AddressNotFoundException{
    EntityManager em = getEntityManager();
    try {
      Address a = em.find(Address.class, id);
      if(a == null){
        throw new AddressNotFoundException("No address found with provided id");
      }
      em.getTransaction().begin();
      em.remove(a);
      em.getTransaction().commit();
      return a;
    } finally {
      em.close();
    }
  }
    
    
    public Address getAddress(int id) throws AddressNotFoundException {
    EntityManager em = getEntityManager();
    try {
      Address a = em.find(Address.class,id);
       if(a == null){
        throw new AddressNotFoundException("No address found with provided id");
      }
       return a;
    } finally {
      em.close();
    }
  }
    
    public Address editAddress(Address a) throws AddressNotFoundException {
    EntityManager em = getEntityManager();
    try {
      Address edited = em.find(Address.class,a.getId());
       if(edited == null){
        throw new AddressNotFoundException("No address found with provided id");
      }
      edited.setStreet(a.getStreet());
      edited.setAdditionalInfo(a.getAdditionalInfo());
      em.getTransaction().begin();
      em.merge(edited);
      em.getTransaction().commit();
      return edited;
    } finally {
      em.close();
    }
  }

}

    

