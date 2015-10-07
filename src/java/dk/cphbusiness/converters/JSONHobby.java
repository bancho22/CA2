/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.converters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dk.cphbusiness.entity.Hobby;
import java.util.List;

/**
 *
 * @author Mato
 */
public class JSONHobby {
    
    public static JsonObject getJsonFromHobby(Hobby h){
        
        JsonObject hJson = new JsonObject();
        hJson.addProperty("description", h.getDescription());
        hJson.addProperty("name", h.getName());
        return hJson;
        
    }
    
    public static JsonArray getJsonFromHobbies(List<Hobby> hobbies){//for getting all hobbies in a future
        JsonArray hJson = new JsonArray();
        for(Hobby h: hobbies){
            hJson.add(getJsonFromHobby(h));
        }
        return hJson;
    }
    
}
