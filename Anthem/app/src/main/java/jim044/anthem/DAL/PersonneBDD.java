package jim044.anthem.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import jim044.anthem.BO.Hymne;
import jim044.anthem.BO.Personne;

/**
 * Created by user on 12/06/2016.
 */
public class PersonneBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "anthem.db";

    private static final String TABLE_PERSONNE = "table_personne";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NOM = "nom";
    private static final int NUM_COL_NOM = 1;
    private static final String COL_PRENOM = "prenom";
    private static final int NUM_COL_PRENOM = 2;
    private static final String COL_DATE_NAISSANCE = "date_naissance";
    private static final int NUM_COL_DATE_NAISSANCE = 3;
    private static final String COL_EMAIL = "email";
    private static final int NUM_COL_EMAIL = 4;
    private static final String COL_ADRESSE = "adresse";
    private static final int NUM_COL_ADRESSE = 5;

    private SQLiteDatabase bdd;

    private DataBaseAnthem dataBaseAnthem;

    public PersonneBDD(Context context){
        //On créer la BDD et sa table
        dataBaseAnthem = new DataBaseAnthem(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = dataBaseAnthem.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertPersonne(Personne personne){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_NOM, personne.getNom());
        values.put(COL_PRENOM, personne.getPrenom());
        values.put(COL_DATE_NAISSANCE, personne.getDateDeNaissance().getTime());
        values.put(COL_EMAIL, personne.getAdresseMail());
        values.put(COL_ADRESSE, personne.getAdresse());

        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_PERSONNE, null, values);
    }

    public Personne getPersonne(){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_PERSONNE, new String[] {COL_ID, COL_NOM, COL_PRENOM, COL_DATE_NAISSANCE, COL_EMAIL, COL_ADRESSE}, null, null, null, null, null);
        return cursorToPersonne(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Personne cursorToPersonne(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Personne personne = new Personne();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        personne.setId(c.getInt(NUM_COL_ID));
        personne.setNom(c.getString(NUM_COL_NOM));
        personne.setPrenom(c.getString(NUM_COL_PRENOM));
        personne.setDateDeNaissance(new Date(c.getLong(NUM_COL_DATE_NAISSANCE)));
        personne.setAdresseMail(c.getString(NUM_COL_EMAIL));
        personne.setAdresse(c.getString(NUM_COL_ADRESSE));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return personne;
    }

}
