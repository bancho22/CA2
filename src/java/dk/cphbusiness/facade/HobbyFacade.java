/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.facade;

import dk.cphbusiness.entity.Hobby;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author user
 */
public class HobbyFacade {
    
    private  EntityManagerFactory emf;

    public HobbyFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public Hobby getHobby(int id){
        EntityManager em = getEntityManager();
        Hobby hobby = null;
        try{
            hobby = em.find(Hobby.class, id);
        }finally{
            em.close();
        }
        return hobby;
    }
    
    public Hobby addHobby(Hobby hobby){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return hobby;
    }
    
    public Hobby editHobby(Hobby hobby){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(hobby);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return hobby;
    }
    
    public Hobby deleteHobby(int id){
        EntityManager em = getEntityManager();
        Hobby hobby = null;
        try{
            hobby = em.find(Hobby.class, id);
                   
            em.getTransaction().begin();
            em.remove(hobby);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return hobby;
    }
}
