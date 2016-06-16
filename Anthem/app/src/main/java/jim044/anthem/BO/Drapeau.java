package jim044.anthem.BO;

/**
 * Created by user on 09/06/2016.
 */
public class Drapeau {

    private int id;
    private String description;
    private Pays pays;

    public Drapeau(int id, String description, Pays pays) {
        this.id = id;
        this.description = description;
        this.pays = pays;
    }

    public Drapeau(String description, Pays pays) {
        this.description = description;
        this.pays = pays;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Drapeau(String description) {
        this.description = description;
    }

    public Drapeau(int id, String description)
    {
        this.id = id;
        this.description = description;
    }

    public Drapeau()
    {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
