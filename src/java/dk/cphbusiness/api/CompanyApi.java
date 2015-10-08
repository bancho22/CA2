package dk.cphbusiness.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dk.cphbusiness.converters.JSONInfoEntity;
import dk.cphbusiness.entity.Company;
import dk.cphbusiness.entity.InfoEntity;
import dk.cphbusiness.entity.Person;
import dk.cphbusiness.exceptions.CompanyNotFoundException;
import dk.cphbusiness.exceptions.PersonNotFoundException;
import dk.cphbusiness.exceptions.PhoneDoesNotBelongToCompanyException;
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
@Path("company")
public class CompanyApi {

    @Context
    private UriInfo context;
    
    private InfoEntityFacade ief;
    private Gson gson;
    
    public CompanyApi() {
        ief = new InfoEntityFacade(Persistence.createEntityManagerFactory("CA2PU"));
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getCompany(@PathParam("id") String id) throws CompanyNotFoundException{
        Company c = ief.getCompany(Integer.parseInt(id));
        String json = JSONInfoEntity.toJson(c).toString();
        return Response.status(Response.Status.OK).entity(json).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCompany(String json){
        Company c = gson.fromJson(json, Company.class);
        c = (Company) ief.addInfoEntity(c);
        json = JSONInfoEntity.toJson(c).toString();
        return Response.status(Response.Status.CREATED).entity(json).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editCompany(String json) throws CompanyNotFoundException{
        Company c = gson.fromJson(json, Company.class);
        c = ief.editCompany(c);
        json = JSONInfoEntity.toJson(c).toString();
        return Response.status(Response.Status.OK).entity(json).build();
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteCompany(@PathParam("id") String id) throws CompanyNotFoundException{
        Company c = ief.deleteCompany(Integer.parseInt(id));
        String json = JSONInfoEntity.toJson(c).toString();
        return Response.status(Response.Status.OK).entity(json).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("byphone/{phone}")
    public Response getCompanyByPhone(@PathParam("phone") String phone) throws PhoneDoesNotBelongToCompanyException, CompanyNotFoundException{
        Company c = ief.getCompanyByPhone(phone);
        String json = JSONInfoEntity.toJson(c).toString();
        return Response.status(Response.Status.OK).entity(json).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("emplAbove/{num}")
    public Response getCompaniesEmplAbove(@PathParam("num") String num){
        List<Company> companies = ief.getCompanies(Integer.parseInt(num));
        String json = JSONInfoEntity.companyListToJson(companies).toString();
        
        return Response.status(Response.Status.OK).entity(json).build();
    }
}
