package com.miarrendart.arrendart_v01.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.miarrendart.arrendart_v01.R;

public class NewApartment extends AppCompatActivity {
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_new_apartment);
        toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        toolbar.setTitle("Nueva Publicación - Departamento");
        setSupportActionBar(toolbar);
    }

}
