package services;

import models.Booster;
import models.CookieClic;
import models.Utilisateur;
import models.types.EBooster;

import java.util.Date;

public class CookieClicService {

    public static CookieClic getCoockieClicForUser(Utilisateur connectedUser) {
        CookieClic cookieClic = CookieClic.find("utilisateur=?1", connectedUser).first();
        if (cookieClic == null) {
            cookieClic = new CookieClic();
            cookieClic.utilisateur = connectedUser;
            cookieClic.save();
        }
        return cookieClic;
    }

    public static void clicOnCookie(CookieClic cookieClic) {
        cookieClic.value++;
        cookieClic.save();
    }
}
