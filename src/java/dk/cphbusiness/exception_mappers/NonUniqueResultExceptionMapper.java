/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.exception_mappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javax.persistence.NonUniqueResultException;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author Bancho
 */
public class NonUniqueResultExceptionMapper implements ExceptionMapper<NonUniqueResultException> {

    @Context
    ServletContext context;
    
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(NonUniqueResultException e) {
        boolean isDebug = context.getInitParameter("debug").equals("true");
        JsonObject eObj = new JsonObject();
        eObj.addProperty("code", 404);
        eObj.addProperty("msg", e.toString());
        if (isDebug) {
            eObj.addProperty("stackTrace", e.getStackTrace().toString());
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(gson.toJson(eObj))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
    
}
