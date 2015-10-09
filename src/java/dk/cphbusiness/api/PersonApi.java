/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.cphbusiness.converters.JSONInfoEntity;
import dk.cphbusiness.entity.Person;
import dk.cphbusiness.exceptions.PersonNotFoundException;
import dk.cphbusiness.exceptions.PhoneDoesNotBelongToPersonException;
import dk.cphbusiness.facade.InfoEntityFacade;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Mato
 */
@Path("person")
public class PersonApi {

    @Context
    private UriInfo context;
    
    private InfoEntityFacade ief;
    private Gson gson;
    
    public PersonApi() {
        ief = new InfoEntityFacade(Persistence.createEntityManagerFactory("CA2PU"));
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getPerson(@PathParam("id") String id) throws PersonNotFoundException{
        Person p = ief.getPerson(Integer.parseInt(id));
        String json = JSONInfoEntity.toJson(p).toString();
        return Response.status(Response.Status.OK).entity(json).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(String json){
        Person p = gson.fromJson(json, Person.class);
        p = (Person) ief.addInfoEntity(p);
        json = JSONInfoEntity.toJson(p).toString();
        return Response.status(Response.Status.CREATED).entity(json).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPerson(String json) throws PersonNotFoundException{
        Person p = gson.fromJson(json, Person.class);
        p = ief.editPerson(p);
        json = JSONInfoEntity.toJson(p).toString();
        return Response.status(Response.Status.OK).entity(json).build();
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deletePerson(@PathParam("id") String id) throws PersonNotFoundException{
        Person p = ief.deletePerson(Integer.parseInt(id));
        String json = JSONInfoEntity.toJson(p).toString();
        return Response.status(Response.Status.OK).entity(json).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("byphone/{phone}")
    public Response getPersonByPhone(@PathParam("phone") String phone) throws PhoneDoesNotBelongToPersonException, PersonNotFoundException{
        Person p = ief.getPersonByPhone(phone);
        String json = JSONInfoEntity.toJson(p).toString();
        return Response.status(Response.Status.OK).entity(json).build();
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("byhobby/countPeople/{hobby}")
    public Response getCountOfPeopleWithHobby(@PathParam("hobby") String hobby) throws PhoneDoesNotBelongToPersonException, PersonNotFoundException{
        int p = ief.getCountOfPeopleWithHobby(hobby);
        //String json = JSONHobby.toJson(p).toString();
        return Response.status(Response.Status.OK).entity(p).build();
    }
    
    
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("byhobby/{hobby}")
    public Response getPeopleWithHobby(@PathParam("hobby") String hobby) throws PhoneDoesNotBelongToPersonException, PersonNotFoundException{
        List<Person> p = ief.getPeopleWithHobby(hobby);
        String json = JSONInfoEntity.PersonListToJson(p).toString();
        return Response.status(Response.Status.OK).entity(json).build();
    }

}
