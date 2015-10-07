/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.converters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dk.cphbusiness.entity.Phone;
import java.util.List;

/**
 *
 * @author Mato
 */
public class JSONPhone {
    
    public static JsonObject getJsonFromPhone(Phone p){
        
        JsonObject pJson = new JsonObject();
        pJson.addProperty("number", p.getNumber());
        pJson.addProperty("description", p.getDescription());
        return pJson;
        
    }
    
    public static JsonArray getJsonFromPhones(List<Phone> phones){//for getting all hobbies in a future
        JsonArray pJson = new JsonArray();
        for(Phone p: phones){
            pJson.add(getJsonFromPhone(p));
        }
        return pJson;
    }
    
}
