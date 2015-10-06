/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.facade;

import dk.cphbusiness.entity.Company;
import dk.cphbusiness.entity.InfoEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author user
 */
public class InfoEntityFacade {
    
    private EntityManagerFactory emf;
    
    public InfoEntityFacade(EntityManagerFactory emf){
        this.emf = emf;
    }
    
    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public InfoEntity getInfoEntity(int id){
        EntityManager em = getEntityManager();
        InfoEntity infoEntity = null;
        try{
            infoEntity = em.find(InfoEntity.class, id);
        }finally{
            em.close();
        }
        return infoEntity;
    }
    
    public InfoEntity addInfoEntity(InfoEntity infoEntity){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(infoEntity);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return infoEntity;
    }
    
    public InfoEntity editInfoEntity(InfoEntity infoEntity){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(infoEntity);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return infoEntity;
    }
    
    public InfoEntity deleteInfoEntity(int id){
        EntityManager em = getEntityManager();
        InfoEntity infoEntity = null;
        try{
            infoEntity = em.find(InfoEntity.class, id);
            em.getTransaction().begin();
            em.remove(infoEntity);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return infoEntity;
    }
    
    //get a list of companies with more than xx employees
    public List<Company> getCompanies(int numEmployees){
        EntityManager em = getEntityManager();
        List<Company> companies = null;
        try{
            Query query = em.createQuery("SELECT c FROM Company c WHERE c.numEmployees > :num");
            query.setParameter("num", numEmployees);
            companies = query.getResultList();
        }finally{
            em.close();
        }
        return companies;
    }
}
