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
            + ", (7, 'ARABIE_SAOUDITE'), (8, 'ARGENTINE'), (9, 'ARMENIE'), (10, 'ARUBA'), (11, 'AUSTRALIE'), (12, 'AUTRICHE')"
            + ", (13, 'AZERBAIDJAN'), (14, 'BAHAMAS'), (15, 'BAHREIN'), (16, 'BANGADLESH'), (17, 'BARBADE'), (18, 'BELGIQUE'), (19, 'BHOUTAN')"
            + ", (20, 'BIELORUSSIE'), (21, 'BOLIVIE'), (22, 'BRESIL'), (23, 'BULGARIE'), (24, 'BURKINA'), (25, 'CAMBODGE'), (26, 'CAMEROUN'), (27, 'CANADA')"
            + ", (28, 'CHILI'), (29, 'CHINE'), (30, 'COLOMBIE'), (31, 'CONGO'), (32, 'COREE_DU_NORD'), (33, 'COREE_DU_SUD'), (34, 'COSTA_RICA'), (35, 'CROATIE')"
            + ", (36, 'CUBA'), (37, 'DANEMARK'), (38, 'DJIBOUTI'), (39, 'EGYPTE'), (40, 'EMIRATS_ARABES_UNIS'), (41, 'EQUATEUR'), (42, 'ERYTHREE'), (43, 'ESPAGNE')"
            + "(44, 'ESTONIE'), (45, 'ETATS_UNIS'), (46, 'ETHIOPIE'), (47, 'FINLANDE'), (48, 'FRANCE'), (49, 'GABON'), (50, 'GAMBIE')"
            + ", (51, 'GEORGIE'), (52, 'GRECE'), (53, 'GUATEMALA'), (54, 'HONDURAS'), (55, 'HONGRIE'), (56, 'INDE')"
            + ", (57, 'INDONESIE'), (58, 'IRAK'), (59, 'IRAN'), (60, 'IRLANDE'), (61, 'ISLANDE'), (62, 'ISRAEL'), (63, 'ITALIE')"
            + ", (64, 'JAMAIQUE'), (65, 'JAPON'), (66, 'JORDANIE'), (67, 'KAZAKHSTAN'), (68, 'KENYA'), (69, 'KOSOVO'), (70, 'KOWEIT'), (71, 'LAOS')"
            + ", (72, 'LETTONIE'), (73, 'LIBAN'), (74, 'LIBYE'), (75, 'LIECHTENSTEIN'), (76, 'LITUANIE'), (77, 'LUXEMBOURG'), (78, 'MACEDOINE'), (79, 'MADAGASCAR')"
            + ", (80, 'MALAWI'), (81, 'MAROC'), (82, 'MEXIQUE'), (83, 'MOLDAVIE'), (84, 'MONGOLIE'), (85, 'MONTENEGRO'), (86, 'MOZAMBIQUE'), (87, 'NAMIBIE')"
            + ", (88, 'NICARAGUA'), (89, 'NORVEGE'), (90, 'NOUVELLE_ZELANDE'), (91, 'OMAN'), (92, 'OUZBEKISTAN'), (93, 'PAKISTAN'), (94, 'PARAGUAY')"
            + ", (95, 'PAYS_BAS'), (96, 'PEROU'), (97, 'POLOGNE'), (98, 'QATAR'), (99, 'ROUMANIE'), (100, 'ROYAUME_UNI'), (101, 'RUSSIE'), (102, 'RWANDA')"
            + ", (103, 'SAINT_MARIN'), (104, 'SERBIE'), (105, 'SLOVAQUIE'), (106, 'SLOVENIE'), (107, 'SOMALIE'), (108, 'SOUDAN'), (109, 'SUEDE'), (110, 'SUISSE')"
            + ", (111, 'SURINAME'), (112, 'SYRIE'), (113, 'TANZANIE'), (114, 'TCHAD'), (115, 'THAILANDE'), (116, 'TUNISIE'), (117, 'TURKMENISTAN'), (118, 'TURQUIE')"
            + ", (119, 'UKRAINE'), (120, 'URUGUAY'), (121, 'VATICAN'), (122, 'VENEZUELA'), (123, 'VIETNAM'), (124, 'YEMEN'), (125, 'ZAMBIE')"
            + ", (126, 'ZIMBABWE');";

    private static final String INSERTION_HYMNE = "INSERT INTO " + TABLE_HYMNE + " (parole, musique, id_pays) VALUES "
            + "('test', 'test', 0), ('test', 'test', 1), ('test', 'test', 2), ('test', 'test', 3), ('test', 'test', 4), ('test', 'test', 5), ('test', 'test', 6)"
            + ", ('test', 'test', 7), ('test', 'test', 8), ('test', 'test', 9), ('test', 'test', 10), ('test', 'test', 11), ('test', 'test', 12)"
            + ", ('test', 'test',13), ('test', 'test', 14), ('test', 'test',15), ('test', 'test', 16), ('test', 'test', 17), ('test', 'test', 18), ('test', 'test', 19)"
            + ", ('test', 'test', 20), ('test', 'test', 21), ('test', 'test', 22), ('test', 'test', 23), ('test', 'test', 24), ('test', 'test', 25), ('test', 'test', 26), ('test', 'test', 27)"
            + ", ('test', 'test', 28), ('test', 'test', 29), ('test', 'test', 30), ('test', 'test', 31), ('test', 'test', 32), ('test', 'test', 33), ('test', 'test', 34), ('test', 'test', 35)"
            + ", ('test', 'test',36), ('test', 'test', 37), ('test', 'test', 38), ('test', 'test', 39), ('test', 'test',40), ('test', 'test',41), ('test', 'test',42), ('test', 'test',43)"
            + ", ('test', 'test',44), ('test', 'test',45), ('test', 'test',46), ('test', 'test',47), ('test', 'test',48), ('test', 'test',49), ('test', 'test',50)"
            + ", ('test', 'test',51), ('test', 'test',52), ('test', 'test',53), ('test', 'test',54), ('test', 'test',55), ('test', 'test',56)"
            + ", ('test', 'test',57), ('test', 'test',58), ('test', 'test',59), ('test', 'test',60), ('test', 'test',61), ('test', 'test',62), ('test', 'test',63)"
            + ", ('test', 'test',64), ('test', 'test',65), ('test', 'test',66), ('test', 'test',67), ('test', 'test',68), ('test', 'test',69), ('test', 'test',70), ('test', 'test',71)"
            + ", ('test', 'test',72), ('test', 'test',73), ('test', 'test',74), ('test', 'test',75), ('test', 'test',76), ('test', 'test',77), ('test', 'test',78),('test', 'test',79)"
            + ", ('test', 'test',80), ('test', 'test',81), ('test', 'test',82), ('test', 'test',83), ('test', 'test',84), ('test', 'test',85), ('test', 'test',86), ('test', 'test',87)"
            + ", ('test', 'test',88), ('test', 'test',89), ('test', 'test',90), ('test', 'test',91), ('test', 'test',92), ('test', 'test',93), ('test', 'test',94)"
            + ", ('test', 'test',95), ('test', 'test',96), ('test', 'test',97), ('test', 'test',98), ('test', 'test', 99), ('test', 'test',100), ('test', 'test',101), ('test', 'test',102)"
            + ", ('test', 'test',103), ('test', 'test',104), ('test', 'test',105), ('test', 'test',106), ('test', 'test',107), ('test', 'test',108), ('test', 'test',109), ('test', 'test',110)"
            + ", ('test', 'test' ,111), ('test', 'test',112), ('test', 'test',113), ('test', 'test',114), ('test', 'test',115), ('test', 'test',116), ('test', 'test',117), ('test', 'test',118)"
            + ", ('test', 'test',119), ('test', 'test',120), ('test', 'test',121), ('test', 'test',122), ('test', 'test',123), ('test', 'test',124), ('test', 'test',125)"
            + ", ('test', 'test',126);";


    private static final String INSERTION_DRAPEAU = "INSERT INTO " + TABLE_DRAPEAU + " (description, id_pays) VALUES "
            + "('AFGHANISTAN', 0), ('SOUTH AFRICA', 1), ('ALBANIA', 2), ('ALGERIA', 3), ('GERMANY', 4), ('ANDORRA', 5) ('ANGOLA', 6),"
            + "('SAUDI ARABIA', 7), ('ARGENTINA', 8), ('ARMENIA', 9), ('ARUBA', 10), ('AUSTRALIA', 11), ('AUSTRIA', 12), "
            + ", ('AZERBAIJAN', 13), ('BAHAMAS', 14), ('BAHREIN', 15), ('BANGADLESH', 16), ('BARBADOS ', 17), ('BELGIUM',18) ('BHUTAN', 19), "
            + "('BELARUS', 20), ('BOLIVIA', 21), ('BRAZIL', 22), ('BULGARIA', 23), ('BURKINA', 24), ('CAMBODIA', 25) ('CAMEROON', 26), ('CANADA', 27), "
            + "('CHILE', 28), ('CHINA', 29), ( 'BRITISH', 30), ( 'CONGO', 31), ( 'NORTH KOREA', 32), ( 'SOUTH KOREA', 33) ( 'COSTA RICA', 34), ( 'CROATIA', 35), "
            + "( 'CUBA', 36), ( 'DENMARK', 37), ( 'DJIBOUTI', 38), ('EGYPT', 39), ('UNITED ARAB EMIRATES', 40), ('ECUADOR', 41) ('ERITREA', 42), ('SPAIN', 43), "
            + "('ESTONIA', 44), ('UNITED STATES', 45), ('ETHIOPIA', 46), ( 'FINLAND', 47), ( 'FRANCE', 48), ( 'GABON', 49) ( 'GAMBIA', 50), "
            + "( 'GEORGIA', 51), ( 'GREECE', 52), ( 'GUATEMALA', 53), ( 'HONDURAS', 54), ( 'HUNGARY', 55), ('INDIA', 56), "
            + "('INDONESIA', 57), ('IRAQ', 58), ('IRAN', 59), ('IRELAND ', 60), ('ICELAND', 61), ( 'ISRAEL', 62) ('ITALY', 63), "
            + "( 'JAMAICA', 64), ( 'JAPAN', 65), ( 'JORDAN', 66), ( 'KAZAKHSTAN', 67) ( 'KENYA', 68), ( 'KOSOVO', 69) ( 'KUWAIT', 70), ( 'LAOS', 71), "
            + "( 'LATVIA', 72), ( 'LEBANON', 73), ( 'LIBYA', 74), ( 'LIECHTENSTEIN', 75), ( 'LITHUANIA', 76), ( 'LUXEMBOURG', 77) ( 'MACEDONIA', 78)( 'MADAGASCAR', 79), "
            + "( 'MALAWI', 80), ( 'MOROCCO', 81), ( 'MEXICO', 82), ( 'MOLDOVA', 83), ( 'MONGOLIA', 84), ( 'MONTENEGRO', 85) ( 'MOZAMBIQUE', 86), ( 'NAMIBIA', 87),"
            + "( 'NICARAGUA', 88), ( 'NORWAY', 89) ( 'NEW ZEELAND', 90), ('OMAN', 91), ('UZBEKISTAN', 92), ('PAKISTAN ', 93) ( 'PARAGUAY', 94), "
            + "( 'NETHERLANDS', 95) ( 'PERU', 96), ( 'POLAND', 97), ( 'QATAR', 98), ( 'ROMANIA', 99), ( 'UNITED KINGDOM', 100) ( 'RUSSIA' 101) ( 'RWANDA', 102), "
            + "( 'SAINT MARIN', 103), ( 'SERBIA', 104), ( 'SLOVAKIA', 105), ( 'SLOVENIA', 106), ( 'SOMALIA', 107), ( 'SUDAN', 108), ( 'SWEDEN' 109), ( 'SWITZERLAND', 110), "
            + "( 'SURINAME' 111), ( 'SYRIA' 112), ( 'TANZANIA' 113), ( 'CHAD' 114), ( 'THAILAND' 115), ( 'TUNISIA', 116), ( 'TURKMENISTAN' 117), ( 'TURKEY', 118), "
            + "('UKRAINE', 119), ('URUGUAY', 120), ('VATICAN', 121), ('VENEZUELA', 122), ('VIETNAM',123), ('YEMEN', 124), ( 'ZAMBIA', 125), "
            + "( 'ZIMBABWE', 126);";

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
