package controllers;

import models.Booster;
import models.CookieClic;
import models.Utilisateur;
import models.types.EBooster;
import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import services.BoosterService;
import services.CookieClicService;

import java.util.List;

@With(Secure.class)
public class Application extends Controller {

    public static final String COOCKIE_CLIC = "coockieClic";

    @Before
    private static void before() {
        Logger.info("--------------");
        Logger.info("nav : %s", request.url);

        Utilisateur connectedUser = Security.connectedUser();
        renderArgs.put(COOCKIE_CLIC, CookieClicService.getCoockieClicForUser(connectedUser));
    }

    public static void index() {
        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);
        List<Booster> boosters = BoosterService.findBoosterForUser(Security.connectedUser());
        render(cookieClic, boosters);
    }

    public static void refresh() {
        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);
        List<Booster> boosters = BoosterService.findBoosterForUser(Security.connectedUser());
        renderTemplate("Application/refresh.html", cookieClic, boosters);
    }

    public static void cookieClic() {
        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);
        CookieClicService.clicOnCookie(cookieClic);
        renderText("OK");
    }

    public static void acheter(EBooster booster) {
        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);
        CookieClicService.buyBooster(cookieClic, booster);
        index();
    }

}