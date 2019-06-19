package models;

import models.types.EBooster;
import models.utils.BoosterCount;

import javax.persistence.*;
import java.util.Date;

// Utilisé dans CookieClicService.getCookiePerSecond()
// Doit être déclaré dans une classe @Entity
@SqlResultSetMapping(
        name = "BoosterCountMapping",
        classes = @ConstructorResult(
                targetClass = BoosterCount.class,
                columns = {
                        @ColumnResult(name = "booster", type = String.class),
                        @ColumnResult(name = "nombre", type = Integer.class)
                }))
@Entity
public class Booster extends NewModel {

    @Enumerated(EnumType.STRING)
    public EBooster booster;

    @ManyToOne
    public Utilisateur utilisateur;

    @Temporal(TemporalType.TIMESTAMP)
    public Date dateAchat;

}
