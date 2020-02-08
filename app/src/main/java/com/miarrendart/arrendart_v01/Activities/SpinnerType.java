package com.miarrendart.arrendart_v01.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.miarrendart.arrendart_v01.Classes.Type;
import com.miarrendart.arrendart_v01.R;

import org.json.JSONArray;
import org.json.JSONException;


import java.util.ArrayList;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class SpinnerType extends AppCompatActivity implements View.OnClickListener {
    Spinner spinner_type;
    ImageView img_type;
    Button btn_next;
    ArrayList<Type> array_type = new ArrayList<Type>();
    AsyncHttpClient client;
    public static Type type;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_selecttype);

        spinner_type = (Spinner)findViewById(R.id.spinner_type);
        img_type = (ImageView) findViewById(R.id.img_type);
        btn_next = (Button) findViewById(R.id.btn_type);
        client = new AsyncHttpClient();
        btn_next.setOnClickListener(this);
        getTypes();

           spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Type select = (Type) parent.getSelectedItem();
                if(select.getType_description().equals("Casa")){
                    img_type.setImageResource(R.drawable.ic_house);

                }
                else if(select.getType_description().equals("Departamento")){
                    img_type.setImageResource(R.drawable.ic_apartment);
                }
                else if(select.getType_description().equals("Habitacion")){
                    img_type.setImageResource(R.drawable.ic_room);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_type){
            type = (Type) spinner_type.getSelectedItem();

            if(type.getType_description().equals("Casa")) {
                Intent i = new Intent(SpinnerType.this, NewHouse.class);
                startActivity(i);
            }
            else if(type.getType_description().equals("Departamento")){
                Toast.makeText(getApplicationContext(),"Próximamente Departamentos!",Toast.LENGTH_SHORT).show();
                //Intent i = new Intent(SpinnerType.this, NewApartment.class);
                //startActivity(i);
            }
            else if(type.getType_description().equals("Habitacion")){
                //Intent i = new Intent(SpinnerType.this, NewRoom.class);
                //startActivity(i);
                Toast.makeText(getApplicationContext(),"Próximamente Habitaciones!",Toast.LENGTH_SHORT).show();

            }

        }
    }



    public void getTypes(){
            String url  = "http://www.tuxdeudas.com/arrendart/combo_pubtype.php";
            client.post(url, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    listType(new String(responseBody));
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
    }


    public void listType(String response){
        try {
            JSONArray ja = new JSONArray(response);
            for (int i = 0; i<ja.length();i++){
                Type t = new Type();
                t.setType_id(ja.getJSONObject(i).getString("pt_id"));
                t.setType_description(ja.getJSONObject(i).getString("pt_description"));
                array_type.add(t);

            }
            ArrayAdapter<Type> dataAdapter = new ArrayAdapter<Type>(getApplicationContext(), android.R.layout.simple_spinner_item,array_type);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_type.setAdapter(dataAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"aqui mato" ,Toast.LENGTH_LONG).show();

        }

    }








}
