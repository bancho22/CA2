/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.converters;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dk.cphbusiness.entity.Address;
import java.util.List;

/**
 *
 * @author Bancho
 */
public class JSONAddress {
    
    public static JsonObject toJson(Address a){
        JsonObject jsonA = new JsonObject();
        if (a != null) {
            jsonA.addProperty("id", a.getId());
            jsonA.addProperty("street", a.getStreet());
            jsonA.addProperty("addInfo", a.getAdditionalInfo());
            //jsonA.add("residents", JSONInfoEntity.toJson(a.getResidents()));
        }
        return jsonA;
    }
    
    public static JsonArray toJson(List<Address> addresses){
        JsonArray jsonAddresses = new JsonArray();
        for (Address address : addresses) {
            jsonAddresses.add(toJson(address));
        }
        return jsonAddresses;
    }
    
}
