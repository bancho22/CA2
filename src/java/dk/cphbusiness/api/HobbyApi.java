package dk.cphbusiness.api;

import dk.cphbusiness.facade.HobbyFacade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import deploy.DeploymentConfiguration;
import dk.cphbusiness.converters.JSONHobby;
import dk.cphbusiness.entity.Hobby;
import dk.cphbusiness.exceptions.HobbyNotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Mato
 */
@Path("hobby")
public class HobbyApi {

    @Context
    private UriInfo context;

    HobbyFacade facade = new HobbyFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
    private static Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();

    public HobbyApi() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getHobby(@PathParam("id") String id) throws HobbyNotFoundException {
        Hobby h = facade.getHobby(Integer.parseInt(id));
        String temp = JSONHobby.getJsonFromHobby(h).toString();
        return Response.status(Response.Status.OK).entity(temp).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response addHobby(String json) {
        Hobby h = gson.fromJson(json, Hobby.class);
        h = facade.addHobby(h);
        String temp = JSONHobby.getJsonFromHobby(h).toString();
        return Response.status(Response.Status.OK).entity(temp).build();
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public Response putHobby(String json) throws HobbyNotFoundException {
        Hobby h = gson.fromJson(json, Hobby.class);
        h = facade.editHobby(h);
        String temp = JSONHobby.getJsonFromHobby(h).toString();
        return Response.status(Response.Status.OK).entity(temp).build();

    }

    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public Response deleteHobby(@PathParam("id") String id) throws HobbyNotFoundException {
        Hobby h = facade.deleteHobby(Integer.parseInt(id));
        String temp = JSONHobby.getJsonFromHobby(h).toString();
        return Response.status(Response.Status.OK).entity(temp).build();
    }

}
