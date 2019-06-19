package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Adresse extends Model {

    public String rue;

    public String codePostal;

    public String ville;

}
