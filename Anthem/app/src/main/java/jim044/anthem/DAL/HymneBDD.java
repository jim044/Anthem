package jim044.anthem.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import jim044.anthem.BO.Drapeau;
import jim044.anthem.BO.Hymne;

/**
 * Created by user on 12/06/2016.
 */
public class HymneBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "anthem.db";

    private static final String TABLE_HYMNE = "table_hymne";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_PAROLE = "parole";
    private static final int NUM_COL_PAROLE = 1;
    private static final String COL_MUSIQUE = "musique";
    private static final int NUM_COL_MUSIQUE = 2;

    private SQLiteDatabase bdd;

    private DataBaseAnthem dataBaseAnthem;

    public HymneBDD(Context context){
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

    public long insertHymne(Hymne hymne){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_PAROLE, hymne.getParole());
        values.put(COL_MUSIQUE, hymne.getMusique());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_HYMNE, null, values);
    }

    public Hymne getHymne(){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_HYMNE, new String[] {COL_ID, COL_PAROLE, COL_MUSIQUE}, null, null, null, null, null);
        return cursorToHymne(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Hymne cursorToHymne(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Hymne hymne = new Hymne();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        hymne.setId(c.getInt(NUM_COL_ID));
        hymne.setParole(c.getString(NUM_COL_PAROLE));
        hymne.setMusique(c.getString(NUM_COL_MUSIQUE));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return hymne;
    }

}
