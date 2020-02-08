package com.miarrendart.arrendart_v01.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.miarrendart.arrendart_v01.Classes.Service;
import com.miarrendart.arrendart_v01.Classes.ServiceAdapter;
import com.miarrendart.arrendart_v01.R;

import java.util.ArrayList;

public class AddServices extends AppCompatActivity {
    ListView listview_id;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_add_services);
        toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        toolbar.setTitle("Añadir Servicios");
        setSupportActionBar(toolbar);
        listview_id = (ListView) findViewById(R.id.listview_id);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showList();
    }



    public void showList(){

        ArrayList<Service> array_service = new ArrayList<Service>();
        array_service.add(new Service("1",false,"Acensor",R.drawable.elevator));
        array_service.add(new Service("2",false,"Gimnasio",R.drawable.gym));
        array_service.add(new Service("3",false,"Areas no fumadores",R.drawable.nosmoke));





        ServiceAdapter customAdapter = new ServiceAdapter(getApplicationContext(), array_service);
        listview_id.setAdapter(customAdapter);




    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}



