package jim044.anthem;

import android.content.Context;
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
import jim044.anthem.DAL.PaysBDD;

public class MainActivity extends AppCompatActivity {

    private Pays unPays;
    private Hymne unHymne;
    private HymneBDD hymneBDD;
    private DrapeauBDD drapeauBDD;
    private PaysBDD paysBDD;
    private Drapeau unDrapeau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertionDonnees();

        ListView listViewHymne = (ListView) findViewById(R.id.listViewHymne);

        //Récupération de la liste des personnes
        ArrayList<Hymne> listHymne = new ArrayList<Hymne>();
        listHymne.add(unHymne);

        ArrayList<Drapeau> listDrapeau = new ArrayList<Drapeau>();
        listDrapeau.add(unDrapeau);

        //Création et initialisation de l'Adapter pour les personnes
        Hymne_Drapeau_Adapter adapter = new Hymne_Drapeau_Adapter(this, listHymne, listDrapeau);

        //Récupération du composant ListView
        ListView listHymneBis = (ListView)findViewById(R.id.listViewHymne);

        //Initialisation de la liste avec les données
        listHymneBis.setAdapter(adapter);
    }

    public void insertionDonnees()
    {

        hymneBDD = new HymneBDD(this);
        drapeauBDD = new DrapeauBDD(this);
        paysBDD = new PaysBDD(this);

        drapeauBDD.open();
        hymneBDD.open();
        paysBDD.open();

        unPays = new Pays(1, "FRANCE");
        unHymne = new Hymne("Test","Test", unPays);
        unDrapeau = new Drapeau("JAUNE BLEU ORANGE",unPays);

        hymneBDD.insertHymne(unHymne);
        paysBDD.insertPays(unPays);
        drapeauBDD.insertDrapeau(unDrapeau);




    }

}
