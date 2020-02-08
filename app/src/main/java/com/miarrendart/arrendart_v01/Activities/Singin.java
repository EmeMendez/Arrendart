package com.miarrendart.arrendart_v01.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.miarrendart.arrendart_v01.Classes.User;
import com.miarrendart.arrendart_v01.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Singin extends AppCompatActivity implements View.OnClickListener {
    TextView link_singup;
    Button btn_skip, btn_login;
    EditText singin_id;
    EditText singin_pass;
    public static User user = null;
    ProgressDialog progressDialog;
    ImageView ico2;
    TextView politics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_singin);

        ConnectivityManager cn = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cn.getActiveNetworkInfo();

        /*if (activeNetwork!=null && activeNetwork.isConnected()) {
            //aqui si tiene Net
            Toast.makeText(this,"Si está conectado",Toast.LENGTH_LONG).show();
        }else{
            //aqui no
            Toast.makeText(this,"No está conectado",Toast.LENGTH_LONG).show();

        }*/




        ico2 = (ImageView) findViewById(R.id.ico2);
        String uri = "@drawable/ico2";
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable imagen = ContextCompat.getDrawable(getApplicationContext(), imageResource);
        ico2.setImageDrawable(imagen);

        politics = (TextView) findViewById(R.id.politics);
        link_singup = (TextView) findViewById(R.id.link_singup);
        btn_skip = (Button) findViewById(R.id.btn_skip);
        btn_login = (Button) findViewById(R.id.btn_login);

        singin_id = (EditText) findViewById(R.id.singin_id);
        singin_pass = (EditText) findViewById(R.id.singin_pass);

        link_singup.setOnClickListener(this);
        btn_skip.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        politics.setOnClickListener(this);

        progressDialog = new ProgressDialog(Singin.this);











    }


    @Override
    public void onClick(View v) {
        NewHouse.longitude=0;
        NewHouse.latitude=0;
        if (v.getId() == R.id.link_singup) {
            Intent i = new Intent(Singin.this, Singup.class);
            startActivity(i);
        }
        if (v.getId() == R.id.btn_login) {

           if(validationIsGood()) {
                new ifExist().execute("http://tuxdeudas.com/arrendart/userexist.php?user_email=" + singin_id.getText().toString());
            }
        }
        if (v.getId() == R.id.btn_skip) {
            progressDialog.setTitle("Buscando Arriendos...");
            progressDialog.setMessage("Un momento por favor");
            progressDialog.show();
            user = null;
            Intent i = new Intent(Singin.this,Drawer.class);
            startActivity(i);
        }
        if (v.getId() == R.id.politics) {
            Uri uri = Uri.parse("http://www.tuxdeudas.com/arrendart/privacy_policy.html/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }


    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL", "" + myurl);
        myurl = myurl.replace(" ", "%20");
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("respuesta", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }


    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }


    private void onNetworkChange(NetworkInfo networkInfo) {
        if (networkInfo != null) {
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                Toast.makeText(this,"Si está conectado",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"No está conectado",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private class ifExist extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

            JSONArray ja = new JSONArray();
            try {
                ja = new JSONArray(result);
                String email = ja.getString(0);
                String pass = ja.getString(5);

                if (email.equalsIgnoreCase(singin_id.getText().toString()) && pass.equals(singin_pass.getText().toString())) {
                    user = new User();
                    user.setUser_email(ja.getString(0));
                    user.setUser_pass(ja.getString(5));
                    user.setUser_name(ja.getString(1));
                    user.setUser_lastnames(ja.getString(2));
                    user.setUser_date(ja.getString(3));
                    user.setUser_address(ja.getString(4));
                    user.setUser_phone(ja.getString(6));

                    progressDialog.setTitle("Iniciando Sesión");
                    progressDialog.setMessage("Un momento por favor...");
                    progressDialog.show();
                    Intent i = new Intent(getApplicationContext(), Drawer.class);

                    startActivity(i);

                } else {
                    Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_SHORT).show();

            }


        }
    }


    public boolean validationIsGood(){
        Drawable dr = getResources().getDrawable(R.drawable.ic_error);
        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
        String msg = "Debe completar este campo";
        boolean condition = true;
        if(singin_id.getText().toString().isEmpty()){
            singin_id.setError(msg,dr);
            condition = false;
        }
        if(singin_pass.getText().toString().isEmpty()){
            singin_pass.setError(msg,dr);
            condition = false;
        }

        return condition;
    }





}







