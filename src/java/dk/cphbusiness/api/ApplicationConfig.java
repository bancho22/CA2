/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.api;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Mato
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(dk.cphbusiness.api.CompanyApi.class);
        resources.add(dk.cphbusiness.api.HobbyApi.class);
        resources.add(dk.cphbusiness.api.PersonApi.class);
        resources.add(dk.cphbusiness.api.PhoneApi.class);
        resources.add(dk.cphbusiness.exception_mappers.CompanyNotFoundExceptionMapper.class);
        resources.add(dk.cphbusiness.exception_mappers.PersonNotFoundExceptionMapper.class);
    }
    
}
