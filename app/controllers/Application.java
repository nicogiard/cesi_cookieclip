package controllers;

import models.Booster;
import models.CookieClic;
import models.Utilisateur;
import models.types.EBooster;
import org.apache.log4j.MDC;
import play.Logger;
import play.mvc.After;
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
    static void before() {
        Logger.info("--------------");
        Logger.info("nav : %s", request.url);

        Utilisateur connectedUser = Security.connectedUser();
        renderArgs.put(COOCKIE_CLIC, CookieClicService.getCoockieClicForUser(connectedUser));

        MDC.put("username", connectedUser.email);
    }

    @After
    static void after() {
        MDC.remove("username");
    }

    public static void index() {
        render();
    }

    public static void refresh() {
        Utilisateur connectedUser = Security.connectedUser();
        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);
        List<Booster> boosters = BoosterService.findBoosterForUser(connectedUser);

        long totalCookie = cookieClic.value + BoosterService.getCookies(connectedUser);
        long cookiePerSecond = CookieClicService.getCookiePerSecond(connectedUser);

//        renderTemplate("Application/refresh.html", totalCookie, boosters, cookiePerSecond);
        render(totalCookie, boosters, cookiePerSecond);
    }

    public static void cookieClic() {
        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);
        CookieClicService.clicOnCookie(cookieClic);
        renderText("OK");
    }

    public static void acheter(EBooster booster) {
        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);
        BoosterService.buyBooster(cookieClic, booster);
        index();
    }

}