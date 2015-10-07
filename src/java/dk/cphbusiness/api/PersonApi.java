/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.cphbusiness.entity.Person;
import dk.cphbusiness.exceptions.PersonNotFoundException;
import dk.cphbusiness.facade.InfoEntityFacade;
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
    
    //to use: JsonObject jsonP = new JsonParser().parse(json).getAsJsonObject();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getPerson(@PathParam("id") String id) throws PersonNotFoundException{
        
        return Response.status(Response.Status.OK).entity(gson.toJson(ief.getPerson(Integer.parseInt(id)))).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(String json){
        Person p = gson.fromJson(json, Person.class);
        return Response.status(Response.Status.OK).entity(gson.toJson(ief.addInfoEntity(p))).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPerson(String json){
        Person p = gson.fromJson(json, Person.class);
        return Response.status(Response.Status.OK).entity(gson.toJson(ief.editInfoEntity(p))).build();
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deletePerson(@PathParam("id") String id){
        return Response.status(Response.Status.OK).entity(gson.toJson(ief.deleteInfoEntity(Integer.parseInt(id)))).build();
    }
}
