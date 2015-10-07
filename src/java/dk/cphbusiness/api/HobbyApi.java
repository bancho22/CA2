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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import dk.cphbusiness.entity.Hobby;

/**
 * REST Web Service
 *
 * @author Mato
 */
@Path("hobby")
public class HobbyApi {

    @Context
    private UriInfo context;

    HobbyFacade facade = new HobbyFacade(Persistence.createEntityManagerFactory("CA2PU"));
    private static Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();

    /**
     * Creates a new instance of GenericResource
     */
    public HobbyApi() {
    }

    /**
     * Retrieves representation of an instance of
     * dk.cphbusiness.api.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHobby(@PathParam("id") int id) {
        return gson.toJson(facade.getHobby(id));
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public String putJson(String json) {
        Hobby h = gson.fromJson(json, Hobby.class);
        return gson.toJson(facade.editHobby(h));
    }

    @DELETE
    @Consumes("application/json")
    public String deleteJson(@PathParam("id") String id) {
        return gson.toJson(facade.deleteHobby(Integer.parseInt(id)));
    }

}
