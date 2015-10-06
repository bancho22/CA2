/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Bancho
 */
@Entity
public class InfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String Email;
    
    @OneToMany(mappedBy = "owner")
    @JoinTable
    private List<Phone> phones;
    @ManyToOne
    private Address address;

    public InfoEntity() {
    }

    public InfoEntity(Integer id, String Email, List<Phone> phones, Address address) {
        this.id = id;
        this.Email = Email;
        this.phones = phones;
        this.address = address;
    }
    
    

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "InfoEntity{" + "id=" + id + ", Email=" + Email + ", phones=" + phones + ", address=" + address + '}';
    }

    
    
}
