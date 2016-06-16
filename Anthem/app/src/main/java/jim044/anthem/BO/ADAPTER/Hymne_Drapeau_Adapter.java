package jim044.anthem.BO.ADAPTER;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jim044.anthem.BO.Drapeau;
import jim044.anthem.BO.Hymne;
import jim044.anthem.R;

/**
 * Created by jimmy on 13/06/2016.
 */
public class Hymne_Drapeau_Adapter extends BaseAdapter {

    // Une liste de personnes
    private List<Drapeau> listDrapeau;
    private List<Hymne> listeHymne;
    //Le contexte dans lequel est présent notre adapter
    private Context mContext;
    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    public Hymne_Drapeau_Adapter(Context mContext, List<Hymne> listeHymne, List<Drapeau> listeDrapeau) {
        this.listeHymne = listeHymne;
        this.listDrapeau = listeDrapeau;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return listeHymne.size();
    }

    @Override
    public Object getItem(int position) {
        return listeHymne.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;
        AssetManager manager;
        InputStream open;
        Bitmap bitmap;
        TextView tv_Hymne;
        TextView tv_Drapeau;
        ImageView imgViewDrapeau;

        //(1) : Réutilisation des layouts
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.list_hymnes, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        manager = mContext.getAssets();

        open = null;

        if(!listeHymne.get(position).getPays().getNom().isEmpty()) {
            try {
                open = manager.open(listeHymne.get(position).getPays().getNom().toLowerCase() + "_drapeau.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            bitmap = BitmapFactory.decodeStream(open);

            tv_Hymne = (TextView) layoutItem.findViewById(R.id.textViewHymne);
            tv_Drapeau = (TextView) layoutItem.findViewById(R.id.textViewDrapeau);

            imgViewDrapeau = (ImageView) layoutItem.findViewById(R.id.imageViewDrapeau);
            imgViewDrapeau.setImageBitmap(bitmap);

            tv_Hymne.setText(listeHymne.get(position).getParole());
            tv_Drapeau.setText(listDrapeau.get(position).getDescription());
        }

        return layoutItem;

    }
}
