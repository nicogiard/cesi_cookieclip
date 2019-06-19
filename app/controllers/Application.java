package controllers;

import models.Booster;
import models.CookieClic;
import models.Utilisateur;
import models.types.EBooster;
import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import services.CookieClicService;

import java.util.List;

@With(Secure.class)
public class Application extends Controller {

    public static final String COOCKIE_CLIC = "coockieClic";

    @Before
    private static void before() {
        Logger.info("--------------");
        Logger.info("nav : %s", request.action);

        Utilisateur connectedUser = Security.connectedUser();
        renderArgs.put(COOCKIE_CLIC, CookieClicService.getCoockieClicForUser(connectedUser));
    }

    public static void index() {
        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);

        List<Booster> boosters = Booster.find("utilisateur = ?1", Security.connectedUser()).fetch();

        render(cookieClic, boosters);
    }

    public static void coockieClic() {
        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);
        CookieClicService.clicOnCookie(cookieClic);
        renderText(cookieClic.value);
    }

    public static void acheter(EBooster booster) {
        Logger.info("%s", booster);

        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);
        CookieClicService.buyBooster(cookieClic, booster);

        index();
    }

}