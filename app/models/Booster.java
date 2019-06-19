package models;

import models.types.EBooster;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Entity
public class Booster extends Model {

    @Enumerated(EnumType.STRING)
    public EBooster booster;

    @OneToOne
    public Utilisateur utilisateur;

    public int nombre;

}
