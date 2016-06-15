package jim044.anthem;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import jim044.anthem.BO.ADAPTER.Hymne_Drapeau_Adapter;
import jim044.anthem.BO.Drapeau;
import jim044.anthem.BO.Hymne;
import jim044.anthem.BO.Pays;
import jim044.anthem.DAL.DataBaseAnthem;
import jim044.anthem.DAL.DrapeauBDD;
import jim044.anthem.DAL.HymneBDD;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Pays unPays;

        unPays = new Pays(1, "FRANCE");
        Hymne unHymne = new Hymne("Test", "../res/HYMNES/FRANCE - HYMNE.mp3", "Test", unPays);

        HymneBDD hymneBDD = new HymneBDD(this);

        try{
            SQLiteDatabase checkDB = null;
            checkDB = SQLiteDatabase.openDatabase("/data/data/package/databases/anthem.db", null, SQLiteDatabase.OPEN_READONLY);

            System.out.println("La base existe");
        }catch(SQLiteException e){
            System.out.println("La base n'existe pas");
        }

        hymneBDD.open();
        hymneBDD.insertHymne(unHymne);

        Drapeau drapeau = new Drapeau("JAUNE BLEU ORANGE");

        ListView listViewHymne = (ListView) findViewById(R.id.listViewHymne);

        //Récupération de la liste des personnes
        ArrayList<Hymne> listHymne = new ArrayList<Hymne>();
        listHymne.add(unHymne);

        ArrayList<Drapeau> listDrapeau = new ArrayList<Drapeau>();
        listDrapeau.add(drapeau);

        //Création et initialisation de l'Adapter pour les personnes
        Hymne_Drapeau_Adapter adapter = new Hymne_Drapeau_Adapter(this, listHymne, listDrapeau);

        //Récupération du composant ListView
        ListView listHymneBis = (ListView)findViewById(R.id.listViewHymne);

        //Initialisation de la liste avec les données
        listHymneBis.setAdapter(adapter);
    }

    //public void insertionDonnees()
    //{
    //    HymneBDD hymneBDD = new HymneBDD(this);
    //    hymneBDD.open();

    //    Hymne hymne = new Hymne("Test", "../res/HYMNES/FRANCE - HYMNE.mp3", "Test");
    //    hymneBDD.insertHymne(hymne);

    //    Drapeau drapeau = new Drapeau("JAUNE BLEU ORANGE");
    //}

}
