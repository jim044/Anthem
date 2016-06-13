package jim044.anthem.BO;

/**
 * Created by user on 09/06/2016.
 */
public class Hymne {

    private int id;
    private String parole;
    private String musique;

    public Hymne(int id, String parole, String musique) {
        this.id = id;
        this.parole = parole;
        this.musique = musique;
    }

    public Hymne()
    {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParole() {
        return parole;
    }

    public void setParole(String parole) {
        this.parole = parole;
    }

    public String getMusique() {
        return musique;
    }

    public void setMusique(String musique) {
        this.musique = musique;
    }
}
