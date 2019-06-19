package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
public class Utilisateur extends Model {

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
