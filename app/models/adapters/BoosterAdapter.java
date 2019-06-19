package models.adapters;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import models.Booster;
import models.Utilisateur;
import org.joda.time.DateTime;

import java.lang.reflect.Type;

public class BoosterAdapter implements JsonSerializer<Booster> {

    private static BoosterAdapter instance;

    private BoosterAdapter() {
    }

    public static BoosterAdapter get() {
        if(instance == null) {
            instance = new BoosterAdapter();
        }
        return instance;
    }

    @Override
    public JsonElement serialize(Booster booster, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();
        obj.addProperty("booster", booster.booster.name());
        obj.addProperty("dateAchat", new DateTime(booster.dateAchat).toString("yyyy-MM-dd'T'HH:mm:ss"));
        return obj;
    }

}
