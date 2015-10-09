/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import deploy.DeploymentConfiguration;
import dk.cphbusiness.converters.JSONAddress;
import dk.cphbusiness.entity.Address;
import dk.cphbusiness.exceptions.AddressNotFoundException;
import dk.cphbusiness.facade.AddressFacade;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Bancho
 */
@Path("address")
public class AddressApi {
    @Context
    private UriInfo context;
    
    private AddressFacade af;
    private Gson gson;
    
    public AddressApi() {
        af = new AddressFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getAddress(@PathParam("id") String id) throws AddressNotFoundException{
        Address a = af.getAddress(Integer.parseInt(id));
        String json = JSONAddress.toJson(a).toString();
        return Response.status(Response.Status.OK).entity(json).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAddress(String json){
        Address a = gson.fromJson(json, Address.class);
        a = af.addAddress(a);
        json = JSONAddress.toJson(a).toString();
        return Response.status(Response.Status.CREATED).entity(json).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editAddress(String json) throws AddressNotFoundException{
        Address a = gson.fromJson(json, Address.class);
        a = af.editAddress(a);
        json = JSONAddress.toJson(a).toString();
        return Response.status(Response.Status.OK).entity(json).build();
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteAddress(@PathParam("id") String id) throws AddressNotFoundException{
        Address a = af.deleteAddress(Integer.parseInt(id));
        String json = JSONAddress.toJson(a).toString();
        return Response.status(Response.Status.OK).entity(json).build();
    }
}
