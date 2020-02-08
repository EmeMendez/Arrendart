package com.miarrendart.arrendart_v01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.miarrendart.arrendart_v01.Classes.City;
import com.miarrendart.arrendart_v01.Classes.Period;
import com.miarrendart.arrendart_v01.Classes.Publication;
import com.miarrendart.arrendart_v01.Classes.State;
import com.miarrendart.arrendart_v01.Classes.SubCity;
import com.miarrendart.arrendart_v01.Classes.Type;
import com.miarrendart.arrendart_v01.Classes.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

public class DetailHouse extends AppCompatActivity {
    Button view_map;
    Toolbar detail_toolbar;
    AsyncHttpClient client;
    Button map3;

    TextView pub_name;
    TextView pub_description;
    TextView pub_price;
    TextView pub_surface;
    TextView pub_numberfloor;
    TextView pub_numberbath;
    TextView pub_numberroom;
    TextView pub_state;
    TextView pub_city;
    TextView pub_subcity;
    TextView pub_date;
    TextView pub_type;
    TextView pub_period;
    TextView pub_address;
    TextView pub_forniture;
    TextView user_email;
    TextView user_name;
    TextView user_phone;

    static double detail_latitude;
    static double detail_longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_detail_house);
        client = new AsyncHttpClient();
        pub_name = (TextView) findViewById(R.id.pub_name);
        map3 = (Button) findViewById(R.id.map3);
        map3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailHouse.this,Map3.class);
                startActivity(i);
            }
        });

        pub_description= (TextView) findViewById(R.id.pub_description);
        pub_price= (TextView) findViewById(R.id.pub_price);
        pub_surface = (TextView) findViewById(R.id.pub_surface);
        pub_numberfloor= (TextView) findViewById(R.id.pub_numberfloor);
        pub_numberbath= (TextView) findViewById(R.id.pub_numberbath);

        pub_numberroom= (TextView) findViewById(R.id.pub_numberroom);
        pub_state= (TextView) findViewById(R.id.pub_state);
        pub_city= (TextView) findViewById(R.id.pub_numberbath);
        pub_subcity= (TextView) findViewById(R.id.pub_subcity);
        pub_date= (TextView) findViewById(R.id.pub_date);
        pub_type= (TextView) findViewById(R.id.pub_type);;
        pub_period= (TextView) findViewById(R.id.pub_period);
        pub_address= (TextView) findViewById(R.id.pub_address);
        pub_forniture= (TextView) findViewById(R.id.pub_forniture);
        user_email= (TextView) findViewById(R.id.user_email);
        user_name= (TextView) findViewById(R.id.user_name);
        user_phone= (TextView) findViewById(R.id.user_phone);

        /*view_map = (Button) findViewById(R.id.view_map);
        view_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailHouse.this,Maps.class);
                startActivity(i);
            }
        });
        */
        detail_toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(detail_toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getPublication();

    }


   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public void getPublication(){
        String url  = "http://www.tuxdeudas.com/arrendart/getPublication.php?pub_id="+PublicationAdapter.pub_id;
        client.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                myPublication(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    public void myPublication(String response){
        try {
             JSONArray ja = new JSONArray(response);
             Publication p = new Publication();
             p.setPub_name(ja.getString(7));
             p.setPub_description(ja.getString(8));
             p.setPub_price(ja.getString(14));
             p.setPub_state(new State());
             p.getPub_state().setState_description(ja.getString(29));
             p.setPub_date(ja.getString(18));
             p.setPub_city(new City());
             p.getPub_city().setCity_id(ja.getString(3));
             p.setPub_subcity(new SubCity());
             p.getPub_subcity().setSubcity_description(ja.getString(31));
             p.setPub_type(new Type());
             p.getPub_type().setType_description(ja.getString(34));
             p.setPub_period(new Period());
             p.getPub_period().setPerdio_description(ja.getString(36));
             p.setPub_latitude(Double.valueOf(ja.getString(10)));
            p.setPub_longitude(Double.valueOf(ja.getString(11)));
            p.setPub_address(ja.getString(9));
             p.setPub_numerroom(ja.getString(12));
             p.setPub_numberbath(ja.getString(13));
             p.setPub_numerfloor(ja.getString(16));
             p.setPub_surface(ja.getString(15));
            p.setPub_forniture(ja.getString(17));

             p.setPub_user(new User());
             p.getPub_user().setUser_email(ja.getString(2));
             p.getPub_user().setUser_name(ja.getString(22) + " " + ja.getString(23) );
             p.getPub_user().setUser_phone(ja.getString(27));





            pub_name.setText(p.getPub_name());
            pub_description.setText(p.getPub_description());
            pub_price.setText(p.getPub_price());
            pub_surface.setText(p.getPub_surface());
            pub_numberfloor.setText(p.getPub_numerfloor());
            pub_numberbath.setText(p.getPub_numberbath());
            pub_numberroom.setText(p.getPub_numerroom());
            pub_state.setText(p.getPub_state().getState_description());
            pub_city.setText(p.getPub_city().getCity_id());
            pub_subcity.setText(p.getPub_subcity().getSubcity_description());
            pub_date.setText(p.getPub_date());
            pub_type.setText(p.getPub_type().getType_description());
            pub_period.setText(p.getPub_period().getPerdio_description());
            pub_address.setText(p.getPub_address());

            detail_latitude = p.getPub_latitude();
            detail_longitude = p.getPub_longitude();



            if(p.getPub_forniture().equals("0")){
                pub_forniture.setText("No");
            }else{
                pub_forniture.setText("Si");
            }

            user_email.setText(p.getPub_user().getUser_email());
            user_name.setText(p.getPub_user().getUser_name());
            user_phone.setText(p.getPub_user().getUser_phone());

             //falta settiar mas parametros





        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Este es el error " + e, Toast.LENGTH_LONG).show();
        }

    }
}
