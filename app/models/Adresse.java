package models;

import javax.persistence.Entity;

@Entity
public class Adresse extends NewModel {

    public String rue;

    public String codePostal;

    public String ville;

}
