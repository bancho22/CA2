/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.facade;

import dk.cphbusiness.entity.Company;
import dk.cphbusiness.entity.InfoEntity;
import dk.cphbusiness.entity.Person;
import dk.cphbusiness.exceptions.CompanyNotFoundException;
import dk.cphbusiness.exceptions.PersonNotFoundException;
import dk.cphbusiness.exceptions.PhoneDoesNotBelongToCompanyException;
import dk.cphbusiness.exceptions.PhoneDoesNotBelongToPersonException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author user
 */
public class InfoEntityFacade {

    private EntityManagerFactory emf;

    public InfoEntityFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public InfoEntity getInfoEntity(int id) {
        EntityManager em = getEntityManager();
        InfoEntity infoEntity = null;
        try {
            infoEntity = em.find(InfoEntity.class, id);
            Query query = em.createQuery("SELECT p FROM Phone p WHERE p.owner = :owner");
            query.setParameter("owner", infoEntity);
            infoEntity.setPhones(query.getResultList());
        } finally {
            em.close();
        }
        return infoEntity;
    }

    public InfoEntity addInfoEntity(InfoEntity infoEntity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(infoEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return infoEntity;
    }

    public InfoEntity editInfoEntity(InfoEntity infoEntity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(infoEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return infoEntity;
    }

    public InfoEntity deleteInfoEntity(int id) {
        EntityManager em = getEntityManager();
        InfoEntity infoEntity = null;
        try {
            infoEntity = em.find(InfoEntity.class, id);
            em.getTransaction().begin();
            em.remove(infoEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return infoEntity;
    }

    //get a list of companies with more than xx employees
    public List<Company> getCompanies(int numEmployees) {
        EntityManager em = getEntityManager();
        List<Company> companies = new ArrayList<Company>();
        try {
            Query query = em.createQuery("SELECT c FROM Company c WHERE c.numEmployees > :num");
            query.setParameter("num", numEmployees);
            companies = query.getResultList();
        } finally {
            em.close();
        }
        return companies;
    }

    public Company getCompanyByPhone(String phone) throws PhoneDoesNotBelongToCompanyException, CompanyNotFoundException {
        EntityManager em = getEntityManager();
        Company company = null;

        InfoEntity infoEntity = null;
        try {
            Query ownerQuery = em.createQuery("SELECT p.owner FROM Phone p WHERE p.number = :number");
            ownerQuery.setParameter("number", phone);
            infoEntity = (InfoEntity) ownerQuery.getSingleResult();
            if (infoEntity == null) {
                throw new CompanyNotFoundException();
            }
            if (infoEntity.getClass().equals(Company.class)) {
                company = (Company) infoEntity;
                Query companyQuery = em.createQuery("SELECT p FROM Phone p WHERE p.owner = :owner");
                companyQuery.setParameter("owner", company);
                company.setPhones(companyQuery.getResultList());
            } else {
                throw new PhoneDoesNotBelongToCompanyException();

            }

//        }catch(NonUniqueResultException e){ - no need to catch it, have an ExceptionMapper for it
            
        } finally {
            em.close();
        }
        return company;
    }

    public Person getPersonByPhone(String phone) throws PhoneDoesNotBelongToPersonException, PersonNotFoundException {
        EntityManager em = getEntityManager();
        Person person = null;
        try {
            Query query = em.createQuery("SELECT p.owner FROM Phone p WHERE p.number = :num");
            query.setParameter("num", phone);
            InfoEntity result = (InfoEntity) query.getSingleResult();
            if (result == null) {
                throw new PersonNotFoundException();
            }
            if (result.getClass().equals(Person.class)) {
                person = (Person) result;
                Query phonesQuery = em.createQuery("SELECT p FROM Phone p WHERE p.owner = :owner");
                phonesQuery.setParameter("owner", person);
                person.setPhones(phonesQuery.getResultList());
            } else {
                throw new PhoneDoesNotBelongToPersonException();
            }
        } finally {
            em.close();
        }
        return person;
    }
    
    public Person getPerson(int id) throws PersonNotFoundException{
        EntityManager em = getEntityManager();
        InfoEntity ie = null;
        Person person = null;
        try {
            ie = em.find(InfoEntity.class, id);
            if (ie == null) {
                throw new PersonNotFoundException();
            }
            if (ie.getClass().equals(Person.class)) {
                person = (Person) ie;
                Query query = em.createQuery("SELECT p FROM Phone p WHERE p.owner = :owner");
                query.setParameter("owner", person);
                person.setPhones(query.getResultList());
            } else {
                throw new PersonNotFoundException();
            }
        } finally {
            em.close();
        }
        return person;
    }
    
    public List<Person> getPeopleWithHobby(String hobbyName) throws PersonNotFoundException{
         EntityManager em = getEntityManager();
        List<Person> person = null;
        try {
            Query query = em.createQuery("SELECT h.id FROM Hobby h WHERE h.name = :name");
            query.setParameter("name", hobbyName);
            InfoEntity result = (InfoEntity) query.getSingleResult();
            Query query1 = em.createQuery("SELECT h.peopleEnjoying_Id FROM infoentity_hobby h Where h.id =:id");
            query1.setParameter("id", result);
            person = query.getResultList();
            //javascreipt files htmls 
            //methods in the apis for hobby company and person 
            //Exceptions and exception Mappers
            //CityInfo Facade and a little bit of the InfoEntity Facade
            //Entity Classes and database we did together
            
            
            if (result == null) {
                throw new PersonNotFoundException();
            }
            
        } finally {
            em.close();
        }
        return person;
        
    }
    public int getCountOfPeopleWithHobby(String hobbyName) throws PersonNotFoundException{
         EntityManager em = getEntityManager();
        List<Person> person = null;
        try {
            Query query = em.createQuery("SELECT h.peopleEnjoying FROM Hobby h WHERE h.name = :name");
            query.setParameter("name", hobbyName);
            InfoEntity result = (InfoEntity) query.getSingleResult();
            if (result == null) {
                throw new PersonNotFoundException();
            }
            
        } finally {
            em.close();
            
        }
        
        return person.size();
        
    }
    
    public Person editPerson(Person p) throws PersonNotFoundException{
        EntityManager em = getEntityManager();
        InfoEntity ie = null;
        try {
            ie = em.find(InfoEntity.class, p.getId());
            if (ie == null) {
                throw new PersonNotFoundException();
            }
            if (ie.getClass().equals(Person.class) == false) {
                throw new PersonNotFoundException();
            }
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }
    
    public Person deletePerson(int id) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        InfoEntity infoEntity = null;
        try {
            infoEntity = em.find(InfoEntity.class, id);
            if (infoEntity == null) {
                throw new PersonNotFoundException();
            }
            if (infoEntity.getClass().equals(Person.class) == false) {
                throw new PersonNotFoundException();
            }
            em.getTransaction().begin();
            em.remove(infoEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return (Person) infoEntity;
    }
    
    public Company getCompany(int id) throws CompanyNotFoundException{
        EntityManager em = getEntityManager();
        InfoEntity ie = null;
        Company c = null;
        try {
            ie = em.find(InfoEntity.class, id);
            if (ie == null) {
                throw new CompanyNotFoundException();
            }
            if (ie.getClass().equals(Company.class)) {
                c = (Company) ie;
                Query query = em.createQuery("SELECT p FROM Phone p WHERE p.owner = :owner");
                query.setParameter("owner", c);
                c.setPhones(query.getResultList());
            } else {
                throw new CompanyNotFoundException();
            }
        } finally {
            em.close();
        }
        return c;
    }
    
    public Company editCompany(Company c) throws CompanyNotFoundException{
        EntityManager em = getEntityManager();
        InfoEntity ie = null;
        try {
            ie = em.find(InfoEntity.class, c.getId());
            if (ie == null) {
                throw new CompanyNotFoundException();
            }
            if (ie.getClass().equals(Company.class) == false) {
                throw new CompanyNotFoundException();
            }
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return c;
    }
    
    public Company deleteCompany(int id) throws CompanyNotFoundException {
        EntityManager em = getEntityManager();
        InfoEntity infoEntity = null;
        try {
            infoEntity = em.find(InfoEntity.class, id);
            if (infoEntity == null) {
                throw new CompanyNotFoundException();
            }
            if (infoEntity.getClass().equals(Company.class) == false) {
                throw new CompanyNotFoundException();
            }
            em.getTransaction().begin();
            em.remove(infoEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return (Company) infoEntity;
    }
    
    public List<Company> getAllCompanies(){
        EntityManager em = getEntityManager();
        List<Company> companies = new ArrayList<Company>();
        try{
            Query query = em.createQuery("SELECT c FROM Company c");
            companies = query.getResultList();
        }finally{
            em.close();
        }
        return companies;
    }
    
    public List<Person> getAllPeople(){
        EntityManager em = getEntityManager();
        List<Person> people = new ArrayList<Person>();
        try{
            Query query = em.createQuery("SELECT p FROM Person p");
            people = query.getResultList();
        }finally{
            em.close();
        }
        return people;
    }
}
