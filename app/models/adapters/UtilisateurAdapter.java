package models.adapters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import models.Utilisateur;

import java.lang.reflect.Type;

public class UtilisateurAdapter implements JsonSerializer<Utilisateur> {

    private static UtilisateurAdapter instance;

    private UtilisateurAdapter() {
    }

    public static UtilisateurAdapter get() {
        if(instance == null) {
            instance = new UtilisateurAdapter();
        }
        return instance;
    }

    @Override
    public JsonElement serialize(Utilisateur utilisateur, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();
        obj.addProperty("id", utilisateur.id);
        obj.addProperty("email", utilisateur.email);
        obj.addProperty("prenom", utilisateur.prenom);
        obj.addProperty("nom", utilisateur.nom);
        return obj;
    }

}
