package services;

import controllers.Security;
import models.Booster;
import models.Utilisateur;

import java.util.List;

public class BoosterService {
    public static List<Booster> findBoosterForUser(Utilisateur utilisateur) {
        return Booster.find("utilisateur = ?1", utilisateur).fetch();
    }
}
