package jim044.anthem.BO;

import java.util.Date;

/**
 * Created by user on 09/06/2016.
 */
public class Personne {

    private int id;
    private String prenom;
    private String nom;
    private Date dateDeNaissance;
    private String adresseMail;
    private String adresse;

    public Personne(int id, String prenom, String nom, Date dateDeNaissance, String adresseMail, String adresse) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresseMail = adresseMail;
        this.adresse = adresse;
    }

    public Personne()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
