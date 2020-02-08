package com.miarrendart.arrendart_v01;

import com.google.firebase.iid.FirebaseInstanceIdService;

public class C_id extends FirebaseInstanceIdService {
    private static  String TAG = "IDService";
    String idUser;

    @Override
    public void onTokenRefresh() {

        super.onTokenRefresh();
    }
}
