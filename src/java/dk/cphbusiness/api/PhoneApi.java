package dk.cphbusiness.api;

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
import dk.cphbusiness.converters.JSONPhone;
import dk.cphbusiness.entity.Phone;
import dk.cphbusiness.exceptions.PhoneNotFoundException;
import dk.cphbusiness.facade.PhoneFacade;
import javax.ws.rs.POST;

/**
 * REST Web Service
 *
 * @author Mato
 */
@Path("phone")
public class PhoneApi {

    @Context
    private UriInfo context;

    PhoneFacade facade = new PhoneFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
    private static Gson gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();

    public PhoneApi() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getPhone(@PathParam("id") String id) throws PhoneNotFoundException {
        Phone p = facade.getPhone(Integer.parseInt(id));
        return JSONPhone.getJsonFromPhone(p).toString();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public String addPhone(String json) {
        Phone p = gson.fromJson(json, Phone.class);
        p = facade.addPhone(p);
        return JSONPhone.getJsonFromPhone(p).toString();
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public String putPhone(String json) throws PhoneNotFoundException {
        Phone p = gson.fromJson(json, Phone.class);
        p = facade.editPhone(p);
        return JSONPhone.getJsonFromPhone(p).toString();

    }

    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public String deletePhone(@PathParam("id") String id) throws PhoneNotFoundException {
        Phone p = facade.deletePhone(Integer.parseInt(id));
        return JSONPhone.getJsonFromPhone(p).toString();
    }

}
