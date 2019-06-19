package jobs;

import models.Booster;
import models.CookieClic;
import models.types.EBooster;
import play.Logger;
import play.jobs.Every;
import play.jobs.Job;
import services.CookieClicService;

import java.util.List;

@Every("1s")
public class TickJob extends Job {

    public void doJob() {
        Logger.info("TickJob starting...");

        List<Booster> boostersClicker = Booster.find("").fetch();
        for (Booster booster : boostersClicker) {
            CookieClic cookieClicUser = CookieClicService.getCoockieClicForUser(booster.utilisateur);
            if (booster.booster.equals(EBooster.CLICKER)) {
                cookieClicUser.value += booster.nombre * 4;
            } else if (booster.booster.equals(EBooster.GRANDMA)) {
                cookieClicUser.value += booster.nombre * 6;
            }
            cookieClicUser.save();
        }
        Logger.info("TickJob ended");
    }

}