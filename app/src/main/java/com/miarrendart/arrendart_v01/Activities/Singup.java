package com.miarrendart.arrendart_v01.Activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Singup extends AppCompatActivity implements View.OnClickListener {
    TextView termines;
    TextView display;
    Button btn_insert;
    CheckBox conditions;

    EditText email,names,lastnames,date,address,password,rpassword,phone;
    public static final String TAG = "Singup";
    private DatePickerDialog.OnDateSetListener dateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_singup);
        conditions=(CheckBox) findViewById(R.id.conditions) ;
        termines = (TextView) findViewById(R.id.termines);
        termines.setOnClickListener(this);
        selectDate();
        email = (EditText) findViewById(R.id.txt_email);
        names = (EditText) findViewById(R.id.txt_names);
        lastnames = (EditText) findViewById(R.id.txt_lastnames);
        date = (EditText) findViewById(R.id.txt_date);
        address = (EditText) findViewById(R.id.txt_address);
        password = (EditText) findViewById(R.id.txt_pass);
        rpassword = (EditText) findViewById(R.id.txt_rpass);
        phone = (EditText) findViewById(R.id.txt_phone);
        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.termines){
            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this,R.style.AlertDialogStyle);
            dialogo1.setTitle("Terminos y Condiciones ");
            dialogo1.setMessage("LEA ESTAS CONDICIONES DETENIDAMENTE ANTES DE ACCEDER O USAR LOS SERVICIOS.\n" +
                    "\n" +
                    "Mediante su acceso y uso de los Servicios usted acuerda vincularse jurídicamente por estas Condiciones, que establecen una relación contractual entre usted y ArrendArt. Si usted no acepta estas Condiciones, no podrá acceder o usar los Servicios. Estas Condiciones sustituyen expresamente los acuerdos o compromisos previos con usted. ArrendArt podrá poner fin de inmediato a estas Condiciones o cualquiera de los Servicios respecto de usted o, en general, dejar de ofrecer o denegar el acceso a los Servicios o cualquier parte de ellos, en cualquier momento y por cualquier motivo.\n" +
                    "\n" +
                    "Se podrán aplicar condiciones suplementarias a determinados Servicios, como políticas para un evento, una actividad o una promoción particular, y dichas condiciones suplementarias se le comunicarán en relación con los Servicios aplicables. Las condiciones suplementarias se establecen además de las Condiciones, y se considerarán una parte de estas, para los fines de los Servicios aplicables. Las condiciones suplementarias prevalecerán sobre las Condiciones en el caso de conflicto con respecto a los Servicios aplicables.\n" +
                    "\n" +
                    "ArrendArt podrá modificar las Condiciones relativas a los Servicios cuando lo considere oportuno. Las modificaciones serán efectivas después de la publicación por parte de ArrendArt de dichas Condiciones actualizadas en esta ubicación o las políticas modificadas o condiciones suplementarias sobre el Servicio aplicable. Su acceso o uso continuado de los Servicios después de dicha publicación constituye su consentimiento a vincularse por las Condiciones y sus modificaciones.\n" +
                    "\n" +
                    "La recopilación y el uso que hacemos de la información personal en relación con los Servicios es conforme se dispone en la Política de privacidad de ArrendArt.ArrendArt podrá facilitar a un procesador de reclamaciones o a una aseguradora cualquier información necesaria (incluida su información de contacto) si hubiera quejas, disputas o conflictos, que pudieran incluir un accidente, implicándole a usted y a un tercero (incluido el prestador particular de servicios de transporte privado) y dicha información o dichos datos fueran necesarios para resolver la queja, la disputa o el conflicto.\n" +
                    "\n" +
                    "2. Los Servicios\n" +
                    "Los Servicios constituyen una plataforma de tecnología que permite a los usuarios de aplicaciones móviles de ArrendArt o páginas web proporcionadas como parte de los Servicios (cada una, una “Aplicación”) organizar y planear el transporte privado y/o servicios de logística con terceros proveedores independientes de dichos servicios, incluidos terceros prestadores particulares independientes de servicios de transporte privado y terceros proveedores logísticos independientes, conforme a un acuerdo con ArrendArt o algunos afiliados de ArrendArt (“Terceros proveedores”). A no ser que ArrendArt lo acepte mediante un contrato separado por escrito con usted, los Servicios se ponen a disposición solo para su uso personal, no comercial. USTED RECONOCE QUE ARRENDART NO PRESTA SERVICIOS DE TRANSPORTE DE NENGÚN TIPO O DE LOGÍSTICA O FUNCIONA COMO UNA EMPRESA DE TRANSPORTES Y QUE DICHOS SERVICIOS DE TRANSPORTE O LOGÍSTICA SE PRESTAN POR TERCEROS PRESTADORES PARTICULARES INDEPENDIENTES, QUE NO ESTÁN EMPLEADOS POR ARRENDART NI POR NINGUNO DE SUS AFILIADOS.\n" +
                    "\n" +
                    "Licencia.\n" +
                    "Sujeto al cumplimiento de estas Condiciones, ArrendArt le otorga una licencia limitada, no exclusiva, no sublicenciable, revocable, no transferible para: (i) el acceso y uso de las Aplicaciones en su dispositivo personal solo en relación con su uso de los Servicios; y (ii) el acceso y uso de cualquier contenido, información y material relacionado que pueda ponerse a disposición a través de los Servicios, en cada caso solo para su uso personal, no comercial. ArrendArt y sus licenciantes se reservan cualquier derecho que no haya sido expresamente otorgado por el presente.\n" +
                    "\n" +
                    "Restricciones.\n" +
                    "Usted no podrá: (i) retirar cualquier nota de derechos de autor, marca registrada u otra nota de propiedad de cualquier parte de los Servicios; (ii) reproducir, modificar, preparar obras derivadas sobre los Servicios, distribuir, licenciar, arrendar, revender, transferir, exhibir públicamente, presentar públicamente, transmitir, retransmitir o explotar de otra forma los Servicios, excepto como se permita expresamente por ArrendArt; (iii) descompilar, realizar ingeniería inversa o desmontar los Servicios, excepto como se permita por la ley aplicable; (iv) enlazar, reflejar o enmarcar cualquier parte de los Servicios; (v) causar o lanzar cualquier programa o script con el objeto de extraer, indexar, analizar o de otro modo realizar prospección de datos de cualquier parte de los Servicios o sobrecargar o bloquear indebidamente la operación y/o funcionalidad de cualquier aspecto de los Servicios; o (vi) intentar obtener un acceso no autorizado o dañar cualquier aspecto de los Servicios o sus sistemas o redes relacionados.");
            dialogo1.setCancelable(false);
            dialogo1.setPositiveButton("Acepto", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                }
            });
            dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    dialogo1.cancel();
                }
            });
            dialogo1.show();
        }
        if(v.getId() == R.id.btn_insert){
           //insertUser();
           // getUser();
            //ifExist();
            //new userInsert().execute("http://tuxdeudas.com/arrendart/userinsert.php?user_email="+email.getText().toString()+"&user_pass="+password.getText().toString());
            this.insert();

        }

    }


    public void selectDate(){
        display = (TextView) findViewById(R.id.txt_date);

        display.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(Singup.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateListener, year, month, day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            }
        });

        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int d, int m, int y) {
                m = m + 1;
                Log.d(TAG, "onDateSet dd/mm/yyy : " + d + "/" + m + "/" + y);
                String date = y + "-" + m + "-" + d;
                display.setText(date);
            }
        };


    }
    //http://localhost:82/android/registro.php?user_email=melon&user_names=melon&user_lastnames=melon&user_date=12345&user_address=direccion&user_pass=1234&user_phone=11&user_photo=null


    public User newUser(){
        User u = new User();
        u.setUser_email(email.getText().toString());
        u.setUser_name(names.getText().toString());
        u.setUser_lastnames(lastnames.getText().toString());
        u.setUser_date(date.getText().toString());
        u.setUser_address(address.getText().toString());
        u.setUser_pass(password.getText().toString());
        u.setUser_phone(phone.getText().toString());
        u.setUser_date(date.getText().toString());
        return u;
    }


    /*public void initFirebase(){
        FirebaseApp.initializeApp(this);
        fire = FirebaseDatabase.getInstance();
        reference = fire.getReference();
    }*/


    /*public void insertUser() {
        try {
        User u = new User();
        u = newUser();
        DatabaseReference newUser = reference.push();
        u.setUser_id(newUser.getKey());
        reference.child("User").child(u.getUser_id()).setValue(u);
        Toast.makeText(this,"Usuario Creado, por favor inicia sesión",Toast.LENGTH_LONG).show();
        clear();
        }catch (Exception e){

        }
    }

    public void getUser(){
        Query q = reference.child("User").orderByChild("user_email").equalTo("chino");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                User u = new User();
                u = firstChild.getValue(User.class);
                Toast.makeText(Singup.this, "Encontrado "+ u.getUser_address(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        }



    /*public void ifExist(){
        Query q = reference.child("User").orderByChild("user_email").equalTo("chino");
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    Toast.makeText(Singup.this, "Existe", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Singup.this, "No existe", Toast.LENGTH_LONG).show();

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }*/




    public void insert() {
        /*User u = new User();
        u = this.newUser();
        final DAO_User2 dao = new DAO_User2(u);

        dao.insertUser(getApplicationContext());
        dao.userExist( new IUserExist() {
            @Override
            public void onUserExists(Boolean exists) {
                if(exists){
                    Toast.makeText(Singup.this, "usuario existe", Toast.LENGTH_LONG).show();

                }else if(!exists){
                        dao.insertUser(getApplicationContext());
                }
            }

            @Override
            public void onUserFetchError(String error) {

            }
        });
        DAO_User.subInsert xx= new DAO_User().new subInsert(Singup.this);
        xx.execute();*/
        if(validationIsGood()) {
            if (conditions.isChecked()) {
                new ifExist().execute("http://tuxdeudas.com/arrendart/userexist.php?user_email=" + email.getText().toString());
            } else {
                Toast.makeText(getApplicationContext(), "Debe aceptar condiciones y servicios", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void clear(){
        email.setText("");
        names.setText("");
        lastnames.setText("");
        date.setText("");
        address.setText("");
        password.setText("");
        rpassword.setText("");
        phone.setText("");
    }



    private String downloadUrl(String myurl) throws IOException {
        Log.i("URL",""+myurl);
        myurl = myurl.replace(" ","%20");
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



    private class  userInsert extends AsyncTask<String, Void, String> {
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

            Toast.makeText(getApplicationContext(), "Usuario registrado exitosamente!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(Singup.this,Singin.class);
            startActivity(i);
        }
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
                Toast.makeText(getApplicationContext(), "Usuario ya se encuentra registrado", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
                new userInsert().execute("http://tuxdeudas.com/arrendart/userinsert.php?" +
                        "user_email="+email.getText().toString()+
                        "&user_names="+names.getText().toString()+
                        "&user_lastnames="+lastnames.getText().toString()+
                        "&user_date="+date.getText().toString()+
                        "&user_address="+address.getText().toString()+
                        "&user_pass="+password.getText().toString()+
                        "&user_phone="+phone.getText().toString());
            }
        }
    }





    public boolean validationIsGood(){
        Drawable dr = getResources().getDrawable(R.drawable.ic_error);
        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
        String msg = "Este campo es obligatorio";
        boolean condition = true;
        if(email.getText().toString().isEmpty()){
            email.setError(msg,dr);
            condition = false;
        }
        if(!isEmailValid(email.getText().toString())){
            email.setError("Debe proporcionar una dirección de email",dr);
            condition = false;
        }

        if(names.getText().toString().isEmpty()){
            names.setError(msg,dr);
            condition = false;
        }
        if(lastnames.getText().toString().isEmpty()){
            lastnames.setError(msg,dr);
            condition = false;
        }
        if(date.getText().toString().isEmpty()){
            date.setError(msg,dr);
            condition = false;
        }
        if(address.getText().toString().isEmpty()){
            address.setError(msg,dr);
            condition = false;
        }
        if (password.getText().toString().isEmpty()){
            password.setError(msg,dr);
            condition = false;
        }
        if(rpassword.getText().toString().isEmpty()){
            rpassword.setError(msg,dr);
            condition = false;
        }
        if(phone.getText().toString().isEmpty()){
            phone.setError(msg,dr);
            condition = false;
        }
        if(!password.getText().toString().equals(rpassword.getText().toString())){
            msg = "Consetraseñas no coinciden";
            rpassword.setError(msg,dr);
            condition = false;
        }
        if(!conditions.isChecked()){
            condition = false;
            Toast.makeText(this,"Debe aceptar condiciones y servicios",Toast.LENGTH_LONG).show();
        }

        return condition;
        }



    public boolean isEmailValid(String email) {
        String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@" +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE); Matcher matcher = pattern.matcher(inputStr);
        if(matcher.matches()) {
            return true;
        }
        else {
            return false;
        }
    }


}









