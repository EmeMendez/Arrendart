package com.miarrendart.arrendart_v01;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miarrendart.arrendart_v01.Activities.Drawer;
import com.miarrendart.arrendart_v01.Activities.Map2;
import com.miarrendart.arrendart_v01.Activities.Singin;
import com.miarrendart.arrendart_v01.Classes.Publication;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.DataHolder> implements View.OnClickListener{
    ArrayList<Publication> arrayData;
    Context context;
    View view;
    View.OnClickListener listener;
    public static String pub_id;


    public PublicationAdapter(ArrayList<Publication> arrayData, Context context)
    {
        this.context=context;
        this.arrayData = arrayData;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){

            listener.onClick(v);
        }
    }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;

    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.a_filter_list_model,
                null,false);
        view.setOnClickListener(this);

        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataHolder h, int i) {
        double lat1,lon1,lat2,lon2;
        double distance = 0;
        if(Map2.latitude_blue !=0 && Map2.longitude_blue!=0) {
           distance = calculDistance(Map2.latitude_blue, Map2.longitude_blue, arrayData.get(i).getPub_latitude(), arrayData.get(i).getPub_longitude());
        }
        DecimalFormat f = new DecimalFormat("#.##");
        String dis = f.format(distance);
        h.hpub_id.setText(arrayData.get(i).getPub_id());
        h.himg.setImageResource(arrayData.get(i).getPub_img());
        h.htitle.setText(arrayData.get(i).getPub_name());
        h.hcity.setText(arrayData.get(i).getPub_city().getCity_description());
        h.hdescription.setText(arrayData.get(i).getPub_description());
        h.hdistance.setText(String.valueOf(dis) + " km");

        if(Singin.user!=null) {
            h.hstar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //estrella click listener

                    if (h.hstar.getColorFilter() != null) {
                        h.hstar.clearColorFilter();
                        pub_id = h.hpub_id.getText().toString();
                        //aquí elimina
                        Drawer dra = new Drawer();
                        dra.deleteFavorites(h.hpub_id.getText().toString(), Singin.user.getUser_email());
                        Toast.makeText(context, "Favorito eliminado"/*+ pub_id*/, Toast.LENGTH_SHORT).show();

                    } else {
                        h.hstar.setColorFilter(ContextCompat.getColor(context,
                                R.color.star_color));
                        pub_id = h.hpub_id.getText().toString();
                        //aquí añade
                        Drawer dra = new Drawer();
                        dra.addFavorites(dra.createFavorite());
                        Toast.makeText(context, "Favorito Añadido"/*+ pub_id*/, Toast.LENGTH_SHORT).show();


                    }
                }
            });
        }

            h.hlayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    pub_id = h.hpub_id.getText().toString();
                    Intent i = new Intent(context, DetailHouse.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }

            });


    }

    @Override
    public int getItemCount() {
        return arrayData.size();

    }


    public static double calculDistance(double lat1, double lng1, double lat2, double lng2) {
        //double radioTierra = 3958.75;//en millas
        double radioTierra = 6371;//en kilómetros
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;
        return distancia;
    }

    public class DataHolder extends RecyclerView.ViewHolder {
        TextView hpub_id;
        TextView htitle,hdescription,hcity,hdistance;
        ImageView himg,hstar;
        RelativeLayout hlayout;

        public DataHolder(View itemView) {
            super(itemView);
            hpub_id=itemView.findViewById(R.id.pub_id);
            himg = itemView.findViewById(R.id.img);
            htitle = itemView.findViewById(R.id.title);
            hdescription = itemView.findViewById(R.id.description);
            hcity= itemView.findViewById(R.id.city);
            hstar = itemView.findViewById(R.id.star);
            hlayout = itemView.findViewById(R.id.layout);
            hdistance =  itemView.findViewById(R.id.distance);

        }
    }












}
