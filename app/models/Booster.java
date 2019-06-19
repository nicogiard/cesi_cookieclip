package models;

import models.types.EBooster;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Booster extends Model {

    @Enumerated(EnumType.STRING)
    public EBooster booster;

    @ManyToOne
    public Utilisateur utilisateur;

    @Temporal(TemporalType.TIMESTAMP)
    public Date dateAchat;

}
