package jim044.anthem.BO;

/**
 * Created by user on 09/06/2016.
 */
public class Hymne {

    private int id;
    private String parole;
    private String url;
    private String musique;
    private Pays pays;

    public Hymne(String parole, String url, String musique, Pays pays) {
        this.parole = parole;
        this.url = url;
        this.musique = musique;
        this.pays = pays;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Hymne(int id, String parole, String url, String musique) {
        this.id = id;
        this.parole = parole;
        this.url = url;
        this.musique = musique;
    }

    public Hymne(String parole, String url, String musique) {
        this.parole = parole;
        this.url = url;
        this.musique = musique;
    }

    public Hymne(int id, String parole, String musique) {
        this.id = id;
        this.parole = parole;
        this.musique = musique;
    }

    public Hymne()
    {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
