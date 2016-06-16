package jim044.anthem.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import jim044.anthem.BO.Hymne;
import jim044.anthem.BO.Pays;

/**
 * Created by user on 15/06/2016.
 */
public class PaysBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "anthem.db";

    private static final String TABLE_PAYS = "table_pays";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NOM = "nom";
    private static final int NUM_COL_NOM = 1;

    private SQLiteDatabase bdd;

    private DataBaseAnthem dataBaseAnthem;

    public PaysBDD(Context context){
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

    public long insertPays(Pays pays){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_NOM, pays.getNom());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_PAYS, null, values);
    }

    public Pays getPays(){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_PAYS, new String[] {COL_ID, COL_NOM}, null, null, null, null, null);
        return cursorToPays(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Pays cursorToPays(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Pays pays = new Pays();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        pays.setId(c.getInt(NUM_COL_ID));
        pays.setNom(c.getString(NUM_COL_NOM));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return pays;
    }
}
