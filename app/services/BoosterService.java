package services;

import models.Booster;
import models.Utilisateur;

import java.util.Date;
import java.util.List;

public class BoosterService {

    public static List<Booster> findBoosterForUser(Utilisateur utilisateur) {
        return Booster.find("utilisateur = ?1", utilisateur).fetch();
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
