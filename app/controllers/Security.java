package controllers;

import models.Utilisateur;

public class Security extends Secure.Security {

    private static final String CONNECTED_USER = "connectedUser";

    static boolean authenticate(String username, String password) {
        Utilisateur user = Utilisateur.find("byEmail", username).first();
        if(user != null) {
            renderArgs.put(CONNECTED_USER, user);
            return true;
        }
        return false;
    }

    public static Utilisateur connectedUser() {
        Utilisateur user = (Utilisateur) renderArgs.get(CONNECTED_USER);

        if (user == null) {
            user = Utilisateur.find("byEmail", connected()).first();

            if (user == null) {
                session.clear();
                redirect("Secure.logout");
            }
            renderArgs.put(CONNECTED_USER, user);

            return user;
        }

        return user;
    }

}