package jim044.anthem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import jim044.anthem.BO.Drapeau;
import jim044.anthem.DAL.DataBaseAnthem;
import jim044.anthem.DAL.DrapeauBDD;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrapeauBDD drapeauBDD = new DrapeauBDD(this);
        Drapeau drapeau = new Drapeau("1531");
        drapeauBDD.open();
        drapeauBDD.insertDrapeau(drapeau);

        Drapeau drapeauFromBdd = drapeauBDD.getDrapeau();
        //Si un livre est retourné (donc si le livre à bien été ajouté à la BDD)
        if(drapeauFromBdd != null){
            //On affiche les infos du livre dans un Toast
            Toast.makeText(this, drapeauFromBdd.getDescription(), Toast.LENGTH_LONG).show();
            //On modifie le titre du livre
            //drapeauFromBdd.setDescription("J'ai modifié le titre du livre");
            //Puis on met à jour la BDD
            //drapeauFromBdd.(livreFromBdd.getId(), livreFromBdd);
        }
    }
}
