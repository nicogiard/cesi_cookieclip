package models.utils;


import models.types.EBooster;

public class BoosterCount {

    public EBooster booster;

    public Integer nombre;

    public BoosterCount(String booster, Integer nombre) {
        this.booster = EBooster.valueOf(booster);
        this.nombre = nombre;
    }

}
