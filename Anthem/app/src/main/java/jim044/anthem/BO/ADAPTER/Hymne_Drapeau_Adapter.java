package jim044.anthem.BO.ADAPTER;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
    private MediaPlayer player;
    private int lengtMusic;
    private String musiqueHymne;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LinearLayout layoutItem;
        AssetManager manager;
        InputStream open;
        Bitmap bitmap;
        TextView tv_Hymne;
        TextView tv_Drapeau;
        ImageView imgViewDrapeau;
        final ImageView imgViewPlay;
        ImageView imgViewStop;
        AssetFileDescriptor descriptor;

        //(1) : Réutilisation des layouts
        if (convertView == null) {
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.list_hymnes, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        manager = mContext.getAssets();

        open = null;

        try {
            open = manager.open(listeHymne.get(position).getPays().getNom().toLowerCase() + "_drapeau.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmap = BitmapFactory.decodeStream(open);

        tv_Drapeau = (TextView) layoutItem.findViewById(R.id.textViewDrapeau);

        imgViewPlay = (ImageView) layoutItem.findViewById(R.id.imageViewPlay);

        if(musiqueHymne == listeHymne.get(position).getPays().getNom())
        {
            imgViewPlay.setImageResource(R.drawable.pause);
        }
        else
        {
            imgViewPlay.setImageResource(R.drawable.play);
        }

        imgViewPlay.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                if(imgViewPlay.getTag() == listeHymne.get(position).getPays().getNom())
                {
                    imgViewPlay.setImageResource(R.drawable.play);
                    imgViewPlay.setTag("play");
                    musiqueHymne = "";
                    player.pause();
                    lengtMusic = player.getCurrentPosition();
                }
                else {
                    if(imgViewPlay.getTag() != "play" && listeHymne.get(position).getPays().getNom() != imgViewPlay.getTag())
                    {
                        lengtMusic = 0;

                        if(musiqueHymne == null) {
                            player = new MediaPlayer();
                        }
                        if (player.isPlaying()) {
                            player.stop();
                        }
                    }
                    AssetFileDescriptor descriptor = null;
                    try {
                        descriptor = mContext.getAssets().openFd(listeHymne.get(position).getPays().getNom().toLowerCase() + "_hymne.mp3");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player = new MediaPlayer();
                    try {
                        player.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        descriptor.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        player.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.setVolume(1f, 1f);
                    player.setLooping(true);
                    player.seekTo(lengtMusic);
                    player.start();

                    musiqueHymne = listeHymne.get(position).getPays().getNom();
                    imgViewPlay.setTag(listeHymne.get(position).getPays().getNom());

                    imgViewPlay.setImageResource(R.drawable.pause);
                    Hymne_Drapeau_Adapter.this.notifyDataSetChanged();
                }
            }
        });

        imgViewStop = (ImageView) layoutItem.findViewById(R.id.imageViewStop);
        imgViewStop.setImageResource(R.drawable.stop);

        imgViewStop.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {

                if (player != null && musiqueHymne == listeHymne.get(position).getPays().getNom())
                {
                    if (player.isPlaying()) {
                        imgViewPlay.setImageResource(R.drawable.play);
                        imgViewPlay.setTag(null);
                        player.stop();
                    }
                }
            }
        });
        imgViewDrapeau = (ImageView) layoutItem.findViewById(R.id.imageViewDrapeau);
        imgViewDrapeau.setImageBitmap(bitmap);
        
        tv_Drapeau.setText(listDrapeau.get(position).getDescription());


        return layoutItem;

    }
}
