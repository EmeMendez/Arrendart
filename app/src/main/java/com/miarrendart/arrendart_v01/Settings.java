package com.miarrendart.arrendart_v01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class Settings extends AppCompatActivity {
    Toolbar toolbar_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_settings);

        toolbar_id = (Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar_id);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(findViewById(R.id.preference_container)!=null){
            if(savedInstanceState!=null)
                return;
                getFragmentManager().beginTransaction().add(R.id.preference_container,new SettingsFragment()).commit();

        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}


/*
import java.util.List;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;

public class Settings extends PreferenceActivity {
    Toolbar toolbar_id;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }
}
*/