package jim044.anthem.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import jim044.anthem.BO.Drapeau;
import jim044.anthem.BO.Pays;

/**
 * Created by user on 12/06/2016.
 */
public class DrapeauBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "anthem.db";

    private static final String TABLE_DRAPEAU = "table_drapeau";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_DESCRIPTION = "description";
    private static final int NUM_COL_DESCRIPTION = 1;
    private static final String COL_PAYS = "id_pays";
    private static final int NUM_COL_PAYS = 2;

    private SQLiteDatabase bdd;

    private DataBaseAnthem dataBaseAnthem;

    public DrapeauBDD(Context context){
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

    public long insertDrapeau(Drapeau drapeau){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_DESCRIPTION, drapeau.getDescription());
        values.put(COL_PAYS, drapeau.getPays().getId());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_DRAPEAU, null, values);
    }

    public Drapeau getDrapeau(){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_DRAPEAU, new String[] {COL_ID, COL_DESCRIPTION, COL_PAYS}, null, null, null, null, null);
        return cursorToDrapeau(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Drapeau cursorToDrapeau(Cursor c){

        if (c.getCount() == 0)
            return null;


        c.moveToFirst();
        Drapeau drapeau = new Drapeau();

        drapeau.setId(c.getInt(NUM_COL_ID));
        drapeau.setDescription(c.getString(NUM_COL_DESCRIPTION));
        drapeau.setPays(new Pays(c.getInt(NUM_COL_PAYS)));

        c.close();

        return drapeau;
    }

    public ArrayList<Drapeau> listDrapeaux()
    {
        Cursor c = bdd.rawQuery("SELECT td.id, td.description, tp.id, tp.nom FROM " + TABLE_DRAPEAU + " AS td INNER JOIN table_pays AS tp ON tp.id = td.id_pays;" , null);
        return cursorToListDrapeaux(c);
    }

    private ArrayList<Drapeau> cursorToListDrapeaux(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        ArrayList<Drapeau> listDrapeaux = new ArrayList<Drapeau>();
        if (c.getCount() == 0)
            return null;

        while(c.moveToNext())   {
            listDrapeaux.add(new Drapeau(c.getInt(NUM_COL_ID),c.getString(NUM_COL_DESCRIPTION), new Pays(c.getInt(2), c.getString(3))));
        }

        //On ferme le cursor
        c.close();

        //On retourne le livre
        return listDrapeaux;
    }

}
