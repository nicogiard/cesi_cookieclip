package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class CookieClic extends NewModel {

    public Long value;

    @OneToOne
    public Utilisateur utilisateur;

    public CookieClic() {
        this.value = 0L;
    }
}
