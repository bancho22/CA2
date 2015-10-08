/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.converters;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dk.cphbusiness.entity.Company;
import dk.cphbusiness.entity.InfoEntity;
import dk.cphbusiness.entity.Person;
import java.util.List;

/**
 *
 * @author Bancho
 */
public class JSONInfoEntity {
    
    public static JsonObject toJson(InfoEntity ie){
        JsonObject jsonIE = new JsonObject();
        if(ie != null){
            jsonIE.addProperty("id", ie.getId());
            jsonIE.addProperty("email", ie.getEmail());
            jsonIE.add("address", JSONAddress.toJson(ie.getAddress()));
            jsonIE.add("phones", JSONPhone.getJsonFromPhones(ie.getPhones()));

            if (ie.getClass().equals(Person.class)) {
                Person p = (Person) ie;
                jsonIE.addProperty("firstName", p.getFirstName());
                jsonIE.addProperty("lastName", p.getLastName());
                jsonIE.add("hobbies", JSONHobby.getJsonFromHobbies(p.getHobbies()));
            }

            if (ie.getClass().equals(Company.class)) {
                Company c = (Company) ie;
                jsonIE.addProperty("name", c.getName());
                jsonIE.addProperty("description", c.getDescription());
                jsonIE.addProperty("cvr", c.getCvr());
                jsonIE.addProperty("numEmployees", c.getNumEmployees());
                jsonIE.addProperty("marketValue", c.getMarketValue());
            }
        }
        return jsonIE;
    }
    
    public static JsonArray toJson(List<InfoEntity> infoEntities){
        JsonArray jsonPeople = new JsonArray();
        for (InfoEntity ie : infoEntities) {
            jsonPeople.add(toJson(ie));
        }
        return jsonPeople;
    }
    
    public static JsonArray companyListToJson(List<Company> infoEntities){
        JsonArray jsonPeople = new JsonArray();
        for (InfoEntity ie : infoEntities) {
            jsonPeople.add(toJson(ie));
        }
        return jsonPeople;
    }

}
