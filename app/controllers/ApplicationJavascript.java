package controllers;

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
        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);

        CookieJson cookieJson = CookieClicService.computeCookieJson(cookieClic);

        renderJSON(cookieJson, BoosterAdapter.get());
    }

    public static void apiCookieClic() {
        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);
        CookieClicService.clicOnCookie(cookieClic);

        CookieJson cookieJson = CookieClicService.computeCookieJson(cookieClic);

        renderJSON(cookieJson, BoosterAdapter.get());
    }

    public static void apiAcheter(EBooster booster) {
        CookieClic cookieClic = (CookieClic) renderArgs.get(COOCKIE_CLIC);
        BoosterService.buyBooster(cookieClic, booster);

        CookieJson cookieJson = CookieClicService.computeCookieJson(cookieClic);

        renderJSON(cookieJson, BoosterAdapter.get());
    }

}