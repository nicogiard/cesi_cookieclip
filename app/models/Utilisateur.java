package models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Utilisateur extends NewModel {

    public String nom;

    public String prenom;

    @Column(columnDefinition = "VARCHAR(10)")
    public String telephone;

    public String email;

    @OneToOne
    public Adresse adresse;

    @OneToMany(mappedBy = "utilisateur")
    List<Booster> boosters;

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
