package controllers;

import models.Booster;
import models.CookieClic;
import models.CookieJson;
import models.Utilisateur;
import models.adapters.BoosterAdapter;
import models.types.EBooster;
import play.Logger;
import play.mvc.After;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import services.BoosterService;
import services.CookieClicService;

import java.util.List;

@With(Secure.class)
public class ApplicationJavascript extends Controller {

    public static final String COOCKIE_CLIC = "coockieClic";

    @Before
    public static void before() {
        Logger.info("--------------");
        Logger.info("nav : %s", request.url);

        Utilisateur connectedUser = Security.connectedUser();
        renderArgs.put(COOCKIE_CLIC, CookieClicService.getCoockieClicForUser(connectedUser));
    }

    @After
    public static void after() {
    }

    public static void index() {
        render();
    }

    public static void apiRefresh() {
        Utilisateur connectedUser = Security.connectedUser();
        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);

        CookieJson cookieJson = new CookieJson();
        cookieJson.totalCookie = cookieClic.value + BoosterService.getCookies(connectedUser);
        cookieJson.cookiePerSecond = CookieClicService.getCookiePerSecond(connectedUser);
        cookieJson.boosters = BoosterService.findBoosterForUser(connectedUser);

        renderJSON(cookieJson, new BoosterAdapter());
    }

    public static void apiCookieClic() {
        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);
        CookieClicService.clicOnCookie(cookieClic);

        Utilisateur connectedUser = Security.connectedUser();

        CookieJson cookieJson = new CookieJson();
        cookieJson.totalCookie = cookieClic.value + BoosterService.getCookies(connectedUser);
        cookieJson.cookiePerSecond = CookieClicService.getCookiePerSecond(connectedUser);
        cookieJson.boosters = BoosterService.findBoosterForUser(connectedUser);

        renderJSON(cookieJson, new BoosterAdapter());
    }

    public static void apiAcheter(EBooster booster) {
        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);
        BoosterService.buyBooster(cookieClic, booster);

        Utilisateur connectedUser = Security.connectedUser();

        CookieJson cookieJson = new CookieJson();
        cookieJson.totalCookie = cookieClic.value + BoosterService.getCookies(connectedUser);
        cookieJson.cookiePerSecond = CookieClicService.getCookiePerSecond(connectedUser);
        cookieJson.boosters = BoosterService.findBoosterForUser(connectedUser);

        renderJSON(cookieJson, new BoosterAdapter());
    }

}