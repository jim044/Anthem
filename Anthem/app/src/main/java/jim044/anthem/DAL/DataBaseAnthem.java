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
            + "(‘test’, ‘test’, 'AFGHANISTAN',0), (‘test’, ‘test’, 'AFRIQUE_DU_SUD',1), (‘test’, ‘test’, 'ALBANIE',2), (‘test’, ‘test’, 'ALGERIE',3), (‘test’, ‘test’, 'ALLEMAGNE',4), (‘test’, ‘test’, 'ANDORRE',5), (‘test’, ‘test’, 'ANGOLA',6)"
            + ", (‘test’, ‘test’, 'ARABIE_SAOUDITE',7), (‘test’, ‘test’, 'ARGENTINE',8), (‘test’, ‘test’, 'ARMENIE',9), (‘test’, ‘test’, 'ARUBA',10), (‘test’, ‘test’, 'AUSTRALIE',11), (‘test’, ‘test’, 'AUTRICHE',12)"
            + ", (‘test’, ‘test’, 'AZERBAIDJAN',13), (‘test’, ‘test’, 'BAHAMAS',14), (‘test’, ‘test’, 'BAHREIN',15), (‘test’, ‘test’, 'BANGADLESH',16), (‘test’, ‘test’, 'BARBADE',17), (‘test’, ‘test’, 'BELGIQUE'18), (‘test’, ‘test’, 'BHOUTAN',19)"
            + ", (‘test’, ‘test’, 'BIELORUSSIE',20), (‘test’, ‘test’, 'BOLIVIE',21), (‘test’, ‘test’, 'BRESIL',22), (‘test’, ‘test’, 'BULGARIE',23), (‘test’, ‘test’, 'BURKINA',24), (‘test’, ‘test’, 'CAMBODGE',25), (‘test’, ‘test’, 'CAMEROUN',26), (‘test’, ‘test’, 'CANADA',27)"
            + ", (‘test’, ‘test’, 'CHILI',28), (‘test’, ‘test’, 'CHINE',29), (‘test’, ‘test’, 'COLOMBIE',30), (‘test’, ‘test’, 'CONGO',31), (‘test’, ‘test’, 'COREE_DU_NORD',32), (‘test’, ‘test’, 'COREE_DU_SUD',33), (‘test’, ‘test’, 'COSTA_RICA',34), (‘test’, ‘test’, 'CROATIE',35)"
            + ", (‘test’, ‘test’, 'CUBA',36), (‘test’, ‘test’, 'DANEMARK',37), (‘test’, ‘test’, 'DJIBOUTI',38), (‘test’, ‘test’, 'EGYPTE',39), ('EMIRATS_ARABES_UNIS',40), ('EQUATEUR',41), ('ERYTHREE',42), ('ESPAGNE',43)"
            + ", ('ESTONIE',44), ('ETATS_UNIS',45), ('ETHIOPIE',46), ('FINLANDE',47), ('FRANCE',48), ('GABON',49), ('GAMBIE',50)"
            + ", ('GEORGIE',51), ('GRECE',52), ('GUATEMALA',53), ('HONDURAS',54), ('HONGRIE',55), ('INDE',56)"
            + ", ('INDONESIE',57), ('IRAK',58), ('IRAN',59), ('IRLANDE',60), ('ISLANDE',61), ('ISRAEL',62), ('ITALIE',63)"
            + ", ('JAMAIQUE',64), ('JAPON',65), ('JORDANIE',66), ('KAZAKHSTAN',67), ('KENYA',68), ('KOSOVO',69), ('KOWEIT',70), ('LAOS',71)"
            + ", ('LETTONIE',72), ('LIBAN',73), ('LIBYE',74), ('LIECHTENSTEIN',75), ('LITUANIE',76), ('LUXEMBOURG',77), ('MACEDOINE',78),('MADAGASCAR',79)"
            + ", ('MALAWI',80), ('MAROC',81), ('MEXIQUE',82), ('MOLDAVIE',83), ('MONGOLIE',84), ('MONTENEGRO',85), ('MOZAMBIQUE',86), ('NAMIBIE',87)"
            + ", ('NICARAGUA',88), ('NORVEGE',89), ('NOUVELLE_ZELANDE',90), ('OMAN',91), ('OUZBEKISTAN',92), ('PAKISTAN',93), ('PARAGUAY',94)"
            + ", ('PAYS_BAS',95), ('PEROU',96), ('POLOGNE',97), ('QATAR',98), ( 'ROUMANIE',99), ('ROYAUME_UNI',100), ('RUSSIE',101), ('RWANDA',102)"
            + ", ('SAINT_MARIN',103), ('SERBIE',104), ('SLOVAQUIE',105), ('SLOVENIE',106), ('SOMALIE',107), ('SOUDAN',108), ('SUEDE',109), ('SUISSE',110)"
            + ", ('SURINAME',111), ('SYRIE',112), ('TANZANIE',113), ('TCHAD',114), ('THAILANDE',115), ('TUNISIE',116), ('TURKMENISTAN',117), ('TURQUIE',118)"
            + ", ('UKRAINE',119), ('URUGUAY',120), ('VATICAN',121), ('VENEZUELA',122), ('VIETNAM',123), ('YEMEN',124), ('ZAMBIE',125)"
            + ", ('ZIMBABWE',126);";

    private static final String INSERTION_DRAPEAU = "INSERT INTO " + TABLE_DRAPEAU + " (description, id_pays) VALUES "
            + "(AFGHANISTAN ', 0), (' SOUTH AFRICA ', 1), (ALBANIA', 2), (ALGERIA ', 3), (GERMANY', 4), (ANDORRA ', 5) (ANGOLA ', 6),"
            + "( 'ARABIE SAOUDITE', 7), (ARGENTINA ', 8), (ARMENIA', 9), ( 'ARUBA', 10), ( 'AUSTRALIA', 11), (AUSTRIA ', 12), "
            + ", (AZERBAIJAN ', 13), (' BAHAMAS ', 14), (' BAHREIN ', 15), (' BANGADLESH ', 16), (' BARBADOS ', 17), (' BELGIQUE'18) ( 'BHUTAN', 19), "
            + "( 'BELARUS', 20), ( 'BOLIVIA', 21), ( ‘BRAZIL’, 22), ( 'BULGARIA', 23), ( 'BURKINA', 24), ( CAMBODIA, 25) ( 'CAMEROON', 26), ( 'CANADA', 27), "
            + "( 'CHILE', 28), ( CHINA, 29), ( 'BRITISH', 30), ( 'CONGO', 31), ( 'COREE_DU_NORD', 32), ( 'SOUTH KOREA', 33) ( 'COSTA RICA', 34), ( 'CROATIA', 35), "
            + "( 'CUBA', 36), ( 'DENMARK', 37), ( 'DJIBOUTI', 38), (EGYPT ', 39), (' EMIRATS ARABES UNIS ', 40), (ECUADOR', 41) (ERITREA ', 42), (SPAIN', 43), "
            + "(ESTONIA ', 44), (' ETATS_UNIS ', 45), (ETHIOPIA', 46), ( 'FINLAND', 47), ( 'FRANCE', 48), ( 'GABON', 49) ( 'GAMBIA', 50), "
            + "( 'GEORGIA', 51), ( 'GREECE', 52), ( 'GUATEMALA', 53), ( 'HONDURAS', 54), ( 'HUNGARY', 55), (INDIA ', 56), "
            + "(INDONESIA ', 57), (' IRAQ', 58), (IRAN', 59), (IRELAND ', 60), (ICELAND', 61), ( 'ISRAEL', 62) (ITALY ', 63), "
            + "( 'JAMAICA', 64), ( 'JAPAN', 65), ( 'JORDAN', 66), ( 'KAZAKHSTAN', 67) ( 'KENYA', 68), ( 'KOSOVO', 69) ( 'KUWAIT', 70), ( 'LAOS', 71), "
            + "( 'LATVIA', 72), ( 'LEBANON', 73), ( 'Libya', 74), ( 'LIECHTENSTEIN', 75), ( 'LITHUANIA', 76), ( 'LUXEMBOURG', 77) ( 'MACEDONIA', 78)( 'MADAGASCAR', 79), "
            + "( 'MALAWI', 80), ( 'MOROCCO', 81), ( 'MEXICO', 82), ( 'MOLDOVA', 83), ( 'MONGOLIA', 84), ( 'MONTENEGRO', 85) ( 'MOZAMBIQUE', 86), ( 'NAMIBIA', 87),"
            + "( 'NICARAGUA', 88), ( 'NORWAY', 89) ( 'NOUVELLE_ZELANDE', 90), (OMAN ', 91), (UZBEKISTAN', 92), (PAKISTAN ', 93) ( 'PARAGUAY', 94), "
            + "( 'PAYS_BAS', 95) ( 'PERU', 96), ( 'POLAND', 97), ( 'QATAR', 98), ( 'ROMANIA', 99), ( 'ROYAUME UNI', 100) ( 'RUSSIA' 101) ( 'RWANDA', 102), "
            + "( 'SAINT MARIN', 103), ( 'SERBIA', 104), ( 'SLOVAKIA', 105), ( 'SLOVENIE', 106), ( 'SOMALIA', 107), ( 'SUDAN', 108), ( 'SWEDEN' 109), ( 'SWITZERLAND', 110), "
            + "( 'SURINAME' 111), ( 'Syria' 112), ( 'TANZANIA' 113), ( 'CHAD' 114), ( 'THAILAND' 115), ( 'TUNISIA', 116), ( 'TURKMENISTAN' 117), ( 'TURKEY', 118), "
            + "(UKRAINE '119), (' URUGUAY '120), (' VATICAN '121), (' VENEZUELA '122), (' VIETNAM '123), (' YEMEN ', 124), ( 'ZAMBIA', 125), "
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
