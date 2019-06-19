package services;

import models.CookieClic;
import models.Utilisateur;
import models.types.EBooster;
import play.db.jpa.JPA;

import java.math.BigInteger;
import java.util.List;

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

    public static long getCookiePerSecond(Utilisateur utilisateur) {
        long cookiePerSecond = 0;

        // 1 requete SQL mais autant d'objets que de ligne en BDD
//        List<Booster> boosterList = BoosterService.findBoosterForUser(utilisateur);
//        for (Booster booster : boosterList) {
//            cookiePerSecond += booster.booster.getMultiplier();
//        }

        // 1 requete SQL mais autant de ligne que de type de Booster
        List resultList = JPA.em().createNativeQuery("SELECT booster, count(id) as nb FROM cookieclip.Booster WHERE utilisateur_id=" + utilisateur.id + " GROUP BY utilisateur_id, booster;").getResultList();
        for (Object o : resultList) {
            EBooster booster = EBooster.valueOf((String) ((Object[]) o)[0]);
            int nombre = ((BigInteger) ((Object[]) o)[1]).intValue();
            cookiePerSecond += booster.getMultiplier() * nombre;
        }

        // Autant de requete SQL que de type de Booster
//        for (EBooster eBooster : EBooster.values()) {
//            long nombre = Booster.count("booster=?1 AND utilisateur=?2", eBooster, utilisateur);
//            cookiePerSecond += eBooster.getMultiplier() * nombre;
//        }

        return cookiePerSecond;
    }
}
