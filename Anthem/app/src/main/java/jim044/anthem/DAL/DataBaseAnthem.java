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
    private static final String TABLE_PAYS = "table_pays";
    private InputStream is;

    private static final String CREATE_TABLE_DRAPEAU = "CREATE TABLE " + TABLE_DRAPEAU + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, description VARCHAR NOT NULL, id_pays INTEGER NOT NULL);";
    private static final String CREATE_TABLE_HYMNE = "CREATE TABLE " + TABLE_HYMNE + " ( "
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, parole VARCHAR NOT NULL, musique VARCHAR NOT NULL, id_pays INTEGER NOT NULL);";
    private static final String CREATE_TABLE_PERSONNE = "CREATE TABLE " + TABLE_PERSONNE + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, nom VARCHAR NOT NULL, prenom VARCHAR NOT NULL"
            + ", dateNaissance DATE NOT NULL, email STRING NOT NULL, adresse STRING NOT NULL);";
    private static final String CREATE_TABLE_PAYS = "CREATE TABLE " + TABLE_PAYS + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, nom VARCHAR NOT NULL);";

    private static final String INSERTION_PAYS = "INSERT INTO " + TABLE_PAYS + " (id, nom) VALUES "
            + "(0, 'AFGANISTAN'), (1, 'ALLEMAGNE'), (2, 'CANADA'), (3, 'CHINE'), (4, 'BELGIQUE'), (5, 'BRESIL'), (6, 'ESPAGNE')"
            + ", (7, 'ETATS_UNI'), (8, 'FINLANDE'), (9, 'FRANCE')";

    private static final String INSERTION_HYMNE = "INSERT INTO " + TABLE_HYMNE + " (parole, musique, id_pays) VALUES "
            + "('test', 'test', 0), ('test', 'test', 1), ('test', 'test', 2), ('test', 'test', 3),('test', 'test', 4), ('test', 'test', 5), ('test', 'test', 6)"
            + ", ('test', 'test', 7), ('test', 'test', 8), ('test', 'test', 9)";

    private static final String INSERTION_DRAPEAU = "INSERT INTO " + TABLE_DRAPEAU + " (description, id_pays) VALUES "
            + "('AFGANISTAN', 0), ('ALLEMAGNE', 1), ('CANADA', 2), ('CHINE', 3), ('BELGIQUE', 4), ('BRESIL', 5), ('ESPAGNE', 6)"
            + ", ('ETATS_UNI', 7), ('FINLANDE', 8), ('FRANCE', 9)";

    public DataBaseAnthem(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_DRAPEAU);
        db.execSQL(CREATE_TABLE_HYMNE);
        db.execSQL(CREATE_TABLE_PERSONNE);
        db.execSQL(CREATE_TABLE_PAYS);
        db.execSQL(INSERTION_PAYS);
        db.execSQL(INSERTION_HYMNE);
        db.execSQL(INSERTION_DRAPEAU);
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
