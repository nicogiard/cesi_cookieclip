package models;

import models.types.EBooster;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Booster extends NewModel {

    @Enumerated(EnumType.STRING)
    public EBooster booster;

    @ManyToOne
    public Utilisateur utilisateur;

    @Temporal(TemporalType.TIMESTAMP)
    public Date dateAchat;

}
