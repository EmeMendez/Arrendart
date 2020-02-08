package com.miarrendart.arrendart_v01.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.miarrendart.arrendart_v01.R;

public class Success extends AppCompatActivity {

    Button btn_finish ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_success);
        btn_finish = (Button)findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Success.this,Drawer.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //if (drawer.isDrawerOpen(GravityCompat.START)) {
        //  drawer.closeDrawer(GravityCompat.START);
        //} else {
        //    super.onBackPressed();
        //}
    }
}
