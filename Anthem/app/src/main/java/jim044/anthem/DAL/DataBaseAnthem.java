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
            + "(0, 'AFGHANISTAN'), (1, 'AFRIQUE_DU_SUD'), (2, 'ALBANIE'), (3, 'ALGERIE'), (4, 'ALLEMAGNE'), (5, 'ANDORRE'), (6, 'ANGOLA')"
            + ", (7, 'ARABIE_SAOUDITE'), (8, 'ARGENTINE'), (9, 'AUSTRALIE'), (10, 'AUTRICHE'), (11, 'AZERBAIDJAN'), (12, 'BELGIQUE'), (13, 'BIELORUSSIE')"
            + " , (14, 'BRESIL'), (15, 'CAMBODGE'), (16, 'CAMEROUN'), (17, 'CANADA'), (18, 'CHILI'), (19, 'CHINE'), (20, 'COLOMBIE'), (21, 'COREE_DU_NORD')"
            + ", (22, 'COREE_DU_SUD')";
    private static final String INSERTION_HYMNE = "INSERT INTO " + TABLE_HYMNE + " (parole, musique, id_pays) VALUES "
            + "('test', 'test', 0), ('test', 'test', 1), ('test', 'test', 2), ('test', 'test', 3),('test', 'test', 4), ('test', 'test', 5), ('test', 'test', 6)"
            + ", ('test', 'test', 7), ('test', 'test', 8), ('test', 'test', 9), ('test', 'test', 10), ('test', 'test', 11), ('test', 'test', 12), ('test', 'test', 13)"
            + ", ('test', 'test', 14), ('test', 'test', 15), ('test', 'test', 16), ('test', 'test', 17), ('test', 'test', 18), ('test', 'test', 19), ('test', 'test', 20),"
            + "('test', 'test', 21), ('test', 'test', 22)";

    private static final String INSERTION_DRAPEAU = "INSERT INTO " + TABLE_DRAPEAU + " (description, id_pays) VALUES "
            + "('AFGHANISTAN',0 ), ('AFRIQUE DU SUD',1), ('ALBANIE',2), ('ALGERIE',3), ('ALLEMAGNE',4), ('ANDORRE',5), ('ANGOLA',6)"
            + ", ('ARABIE SAOUDITE',7), ('ARGENTINE',8), ('AUSTRALIE',9), ('AUTRICHE',10), ('AZERBAIDJAN',11), ('BELGIQUE',12), ('BIELORUSSIE',13)"
            + " , ('BRESIL',14), ('CAMBODGE',15), ('CAMEROUN',16), ('CANADA',17), ('CHILI',18), ('CHINE',19), ('COLOMBIE',20), ('COREE DU NORD',21)"
            + ", ('COREE DU SUD',22)";
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
