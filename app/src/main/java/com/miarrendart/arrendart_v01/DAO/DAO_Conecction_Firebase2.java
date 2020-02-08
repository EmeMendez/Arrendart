package com.miarrendart.arrendart_v01.DAO;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAO_Conecction_Firebase2 extends AppCompatActivity {

    FirebaseDatabase fire;
    DatabaseReference reference;



       public FirebaseDatabase getFire() {
        return fire;
    }
    public DatabaseReference getReference() {
        return reference;
    }

    public void initFirebase(){
            FirebaseApp.initializeApp(this);
            fire = FirebaseDatabase.getInstance();
            reference = fire.getReference();
    }
}
