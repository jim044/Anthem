package jim044.anthem.BO;

/**
 * Created by user on 09/06/2016.
 */
public class Drapeau {

    private int id;
    private String description;

    public Drapeau(int id, String description)
    {
        this.id = id;
        this.description = description;
    }

    public Drapeau(String description)
    {
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
