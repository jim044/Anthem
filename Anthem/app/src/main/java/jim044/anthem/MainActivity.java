package jim044.anthem;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
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

public class MainActivity extends Activity {

    private HymneBDD hymneBDD;
    private DrapeauBDD drapeauBDD;
    private PaysBDD paysBDD;
    private ArrayList<Pays> listPays;
    private ListView listViewHymne;
    private ListView listHymneBis;
    private ArrayList<Hymne> listHymne;
    private ArrayList<Drapeau> listDrapeau;
    private EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        insertionDonnees();
        listPays = new ArrayList<Pays>();
        listPays = null;
        listPays = paysBDD.listPays();

        listHymne = new ArrayList<Hymne>();
        listHymne = null;
        listHymne = hymneBDD.listHymnes();

        listDrapeau = new ArrayList<Drapeau>();
        listDrapeau = null;
        listDrapeau = drapeauBDD.listDrapeaux();

        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.equals("") )
                {
                    listPays = paysBDD.listPaysByNomPays(s.toString());
                    listHymne = hymneBDD.listHymnesByNomPays(s.toString());
                    listDrapeau = drapeauBDD.listDrapeauxByNomPays(s.toString());
                    adapter();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }
        });

        adapter();
    }

    public void insertionDonnees()
    {
        hymneBDD = new HymneBDD(this);
        drapeauBDD = new DrapeauBDD(this);
        paysBDD = new PaysBDD(this);

        drapeauBDD.open();
        hymneBDD.open();
        paysBDD.open();
    }

    public void adapter()
    {
        if(listHymne != null)
        {
            Hymne_Drapeau_Adapter adapter = new Hymne_Drapeau_Adapter(this, listHymne, listDrapeau);

            listHymneBis = (ListView) findViewById(R.id.listViewHymne);

            listHymneBis.setAdapter(adapter);
        }
    }


}
