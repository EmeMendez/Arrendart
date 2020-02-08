package com.miarrendart.arrendart_v01.Activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.miarrendart.arrendart_v01.Classes.City;
import com.miarrendart.arrendart_v01.Classes.Period;
import com.miarrendart.arrendart_v01.Classes.Publication;
import com.miarrendart.arrendart_v01.Classes.State;
import com.miarrendart.arrendart_v01.Classes.SubCity;
import com.miarrendart.arrendart_v01.Classes.User;
import com.miarrendart.arrendart_v01.Maps;
import com.miarrendart.arrendart_v01.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class NewHouse extends AppCompatActivity  implements View.OnClickListener {
    Spinner city;
    Spinner subcity;
    Spinner period;
    String city_id = "";
    Toolbar toolbar;
    Button btn_map;
    Button btn_continue;
    AsyncHttpClient client;
    AsyncHttpClient client2;
    AsyncHttpClient client3;

    EditText p_name;
    EditText p_description;
    EditText p_address;
    public static EditText p_latitude;
    public static EditText p_longitude;
    EditText p_numberroom;
    EditText p_numberbath;
    EditText p_price;
    EditText p_surface;
    EditText p_numberfloor;
    CheckBox p_forniture;
    EditText block;
    EditText numberblock;

    ArrayList<City> array_city = new ArrayList<City>();
    ArrayList<SubCity> array_subcity = new ArrayList<SubCity>();
    ArrayList<Period> array_period = new ArrayList<Period>();

    public static double latitude;
    public static double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_new_house);
        client = new AsyncHttpClient();
        client2 = new AsyncHttpClient();
        client3 = new AsyncHttpClient();

        city = (Spinner) findViewById(R.id.p_city);
        subcity = (Spinner) findViewById(R.id.p_subcity);
        period = (Spinner) findViewById(R.id.p_period);
        toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        p_name=(EditText) findViewById(R.id.p_name);

        p_description=(EditText) findViewById(R.id.p_description);
        p_address=(EditText) findViewById(R.id.p_address);
        p_latitude=(EditText) findViewById(R.id.p_latitude);
        p_longitude=(EditText) findViewById(R.id.p_longitude);
        p_numberbath=(EditText) findViewById(R.id.p_numberbath);
        p_numberroom=(EditText) findViewById(R.id.p_numberroom);
        p_numberfloor=(EditText) findViewById(R.id.p_numberfloor);

        p_surface=(EditText) findViewById(R.id.p_surface);
        p_surface=(EditText) findViewById(R.id.p_surface);
        p_price=(EditText) findViewById(R.id.p_price);
        p_forniture =(CheckBox) findViewById(R.id.p_forniture);

        toolbar.setTitle("Nueva Publicación - Casa");
        setSupportActionBar(toolbar);
        btn_continue = (Button) findViewById(R.id.btn_continue) ;
        btn_map = (Button) findViewById(R.id.btn_map) ;
        btn_continue.setOnClickListener(this);
        btn_map.setOnClickListener(this);
        getPeriod();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getCity();

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                City city_selected = (City) parent.getSelectedItem();
                city_id=city_selected.getCity_id();
                getSubCity(city_id);
              }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_map:
                Intent i = new Intent(getApplicationContext(),Maps.class);
                startActivity(i);
                break;
            case R.id.btn_continue:
                //setiando el static del objeto publicacion para enviar
                if(validationIsGood()) {
                    AddImages.publica = newPublication();
                    Intent ii = new Intent(NewHouse.this, AddImages.class);
                    startActivity(ii);
                }
                break;
        }
    }


    public void getCity(){
        String url  = "http://www.tuxdeudas.com/arrendart/combo_city.php";
        client.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                listCity(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void listCity(String response){
        try {
            JSONArray ja = new JSONArray(response);
            for (int i = 0; i<ja.length();i++){
                City c = new City();
                c.setCity_id(ja.getJSONObject(i).getString("city_id"));
                c.setCity_description(ja.getJSONObject(i).getString("city_description"));
                array_city.add(c);

            }
            ArrayAdapter<City> adapter_city = new ArrayAdapter<City>(this, android.R.layout.simple_spinner_item,array_city);
            adapter_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            city.setAdapter(adapter_city);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"aqui mato" ,Toast.LENGTH_LONG).show();

        }

    }



    public void getSubCity(String idCity){
        String url  = "http://www.tuxdeudas.com/arrendart/combo_subcity.php?idCity=" + idCity;
        client2.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                listSubCity(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void listSubCity(String response){
        try {
            JSONArray ja = new JSONArray(response);
            array_subcity=new ArrayList<SubCity>();
            for (int i = 0; i<ja.length();i++){
                SubCity sc = new SubCity();
                sc.setSubcity_id(ja.getJSONObject(i).getString("sc_id"));
                sc.setSubcity_description(ja.getJSONObject(i).getString("sc_description"));
                City c = new City();
                c.setCity_id(ja.getJSONObject(i).getString("sc_city"));
                sc.setSubcity_city(c);
                array_subcity.add(sc);

            }
            ArrayAdapter<SubCity> adapter_subity = new ArrayAdapter<SubCity>(NewHouse.this, android.R.layout.simple_spinner_item,array_subcity);
            adapter_subity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            subcity.setAdapter(adapter_subity);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"aqui mato" ,Toast.LENGTH_LONG).show();

        }

    }


    public void getPeriod(){
        String url  = "http://www.tuxdeudas.com/arrendart/combo_period.php";
        client2.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                listPeriod(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void listPeriod(String response){
        try {
            JSONArray ja = new JSONArray(response);
            for (int i = 0; i<ja.length();i++){
                Period p = new Period();
                p.setPeriod_id(ja.getJSONObject(i).getString("per_id"));
                p.setPerdio_description(ja.getJSONObject(i).getString("per_description"));

                array_period.add(p);

            }
            ArrayAdapter<Period> adapter_period = new ArrayAdapter<Period>(this, android.R.layout.simple_spinner_item,array_period);
            adapter_period.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            period.setAdapter(adapter_period);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"aqui mato + " + e ,Toast.LENGTH_LONG).show();

        }

    }


    public Publication newPublication(){
        Publication p = new Publication();
        State s = new State();
        s.setState_id("1");
        p.setPub_state(s);
        User u = new User();
        u=Singin.user;
        p.setPub_user(u);
        City c = new City();
        c=(City) city.getSelectedItem();
        p.setPub_city(c);
        SubCity sub = new SubCity();
        sub = (SubCity) subcity.getSelectedItem();
        p.setPub_subcity(sub);
        p.setPub_type(SpinnerType.type);
        Period per = new Period();
        per = (Period) period.getSelectedItem();
        p.setPub_period(per);
        p.setPub_name(p_name.getText().toString());
        p.setPub_description(p_description.getText().toString());
        p.setPub_address(p_address.getText().toString());
        p.setPub_latitude(Double.valueOf(p_latitude.getText().toString()));
        p.setPub_longitude(Double.valueOf(p_longitude.getText().toString()));
        p.setPub_numerroom(p_numberroom.getText().toString());
        p.setPub_numberbath(p_numberbath.getText().toString());
        p.setPub_price(p_price.getText().toString());
        p.setPub_surface(p_surface.getText().toString());
        p.setPub_numerfloor(p_numberfloor.getText().toString());
        p.setPub_latitude(Double.valueOf(p_latitude.getText().toString()));
        p.setPub_longitude(Double.valueOf(p_longitude.getText().toString()));

        if(p_forniture.isChecked()){
            p.setPub_forniture("1");
        }else if(!p_forniture.isChecked()){
            p.setPub_forniture("0");
        }

        return  p;

    }


    public boolean validationIsGood(){
         Drawable dr = getResources().getDrawable(R.drawable.ic_error);
        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
        String msg = "Este campo es obligatorio";
        boolean condition = true;
        if(p_name.getText().toString().isEmpty()){
            p_name.setError(msg,dr);
            condition = false;
        }
        if(p_description.getText().toString().isEmpty()){
            p_description.setError(msg,dr);
            condition = false;
        }
        if(p_address.getText().toString().isEmpty()){
            p_address.setError(msg,dr);
            condition = false;
        }
        if(p_numberroom.getText().toString().isEmpty()){
            p_numberbath.setError(msg,dr);
            condition = false;
        }
        if(p_price.getText().toString().isEmpty()){
            p_price.setError(msg,dr);
            condition = false;
        }
        if (p_surface.getText().toString().isEmpty()){
            p_surface.setError(msg,dr);
            condition = false;
        }
        if(p_numberfloor.getText().toString().isEmpty()){
            p_numberfloor.setError(msg,dr);
            condition = false;
        }
        if(p_latitude.getText().toString().isEmpty()){
            p_latitude.setError(msg,dr);
            condition = false;
        }
        if(p_longitude.getText().toString().isEmpty()){
            p_longitude.setError(msg,dr);
            condition = false;
        }
        return condition;
    }
}












