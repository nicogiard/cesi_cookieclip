package services;

import models.Booster;
import models.CookieClic;
import models.Utilisateur;
import models.types.EBooster;

import java.util.Date;
import java.util.List;

public class BoosterService {

    public static List<Booster> findBoosterForUser(Utilisateur utilisateur) {
        return Booster.find("utilisateur = ?1 ORDER BY dateAchat DESC", utilisateur).fetch();
    }

    public static void buyBooster(CookieClic cookieClic, EBooster booster) {
        // TODO : Est ce que je peux l'acheter ????

        Booster userBooster = new Booster();
        userBooster.utilisateur = cookieClic.utilisateur;
        userBooster.booster = booster;
        userBooster.dateAchat = new Date();
        userBooster.save();
    }

    public static long getCookies(Utilisateur utilisateur) {
        List<Booster> boosters = findBoosterForUser(utilisateur);

        long totalCout = 0;
        long totalCookie = 0;

        Date now = new Date();
        for (Booster booster : boosters) {
            long intervalSeconds = (now.getTime() - booster.dateAchat.getTime()) / 1000;
            totalCout += booster.booster.getCost();
            totalCookie += intervalSeconds * booster.booster.getMultiplier();
        }

        totalCookie -= totalCout;

        return totalCookie;
    }

}
