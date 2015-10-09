/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.exception_mappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import dk.cphbusiness.exceptions.PhoneDoesNotBelongToPersonException;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;


/**
 *
 * @author Bancho
 */
@Provider
public class PhoneDoesNotBelongToPersonExceptionMapper implements ExceptionMapper<PhoneDoesNotBelongToPersonException> {

    @Context
    ServletContext context;
    
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(PhoneDoesNotBelongToPersonException e) {
        boolean isDebug = context.getInitParameter("debug").equals("true");
        JsonObject eObj = new JsonObject();
        eObj.addProperty("code", 400);
        eObj.addProperty("msg", e.getMessage());
        if (isDebug) {
            eObj.addProperty("stackTrace", e.getStackTrace().toString());
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(gson.toJson(eObj))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
