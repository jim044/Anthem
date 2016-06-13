package jim044.anthem.BO;

/**
 * Created by user on 13/06/2016.
 */
public class Pays {
    private int id;
    private String nom;

    public Pays()
    {
    }
    public Pays(int id)
    {
        this.id = id;
    }
    public Pays(String nom) {
        this.nom = nom;
    }

    public Pays(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
