package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class CookieClic extends Model {

    public Long value;

    @OneToOne
    public Utilisateur utilisateur;

    public CookieClic() {
        this.value = 0L;
    }
}
