package jim044.anthem.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by user on 12/06/2016.
 */
public class DataBaseAnthem extends SQLiteOpenHelper {

    private static final String TABLE_DRAPEAU = "table_drapeau";
    private static final String TABLE_HYMNE = "table_hymne";
    private static final String TABLE_PERSONNE = "table_personne";
    private static final String TABLE_PAYS = "table_personne";
    private InputStream is;
    private Context Context;

    private static final String CREATE_TABLE_DRAPEAU = "CREATE TABLE " + TABLE_DRAPEAU + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, description VARCHAR NOT NULL, id_pays INTEGER NOT NULL);";
    private static final String CREATE_TABLE_HYMNE = "CREATE TABLE " + TABLE_HYMNE + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, parole VARCHAR NOT NULL, musique VARCHAR NOT NULL, id_pays INTEGER NOT NULL);";
    private static final String CREATE_TABLE_PERSONNE = "CREATE TABLE " + TABLE_PERSONNE + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, nom VARCHAR NOT NULL, prenom VARCHAR NOT NULL"
            + ", dateNaissance DATE NOT NULL, email STRING NOT NULL, adresse STRING NOT NULL);";
    private static final String CREATE_TABLE_PAYS = "CREATE TABLE " + TABLE_PAYS + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, nom VARCHAR NOT NULL);";

    public DataBaseAnthem(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            is = Context.getResources().getAssets().open("anthem.sql");
            String[] statements = FileHelper.parseSqlFile(is);

            for (String statement : statements) {
                db.execSQL(statement);   //execution des requêtes
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("BDD", "Création ou connexion fichié échouée");
        }
        db.execSQL(CREATE_TABLE_DRAPEAU);
        db.execSQL(CREATE_TABLE_HYMNE);
        db.execSQL(CREATE_TABLE_PERSONNE);
        db.execSQL(CREATE_TABLE_PAYS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE " + TABLE_DRAPEAU + ";");
        db.execSQL("DROP TABLE " + TABLE_HYMNE + ";");
        db.execSQL("DROP TABLE " + TABLE_PERSONNE + ";");
        db.execSQL("DROP TABLE " + TABLE_PAYS + ";");

        onCreate(db);
    }
}
