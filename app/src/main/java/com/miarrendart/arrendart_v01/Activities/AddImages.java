package com.miarrendart.arrendart_v01.Activities;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.miarrendart.arrendart_v01.Classes.Publication;
import com.miarrendart.arrendart_v01.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddImages extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Button btn_images;
    Button btn_continue;




    ImageView img;
    public static Publication publica;
    public static final int REQUEST_CODE_TAKE_PHOTO = 0 /*1*/;
    private Uri photoURI;
    public String mCurrentPhotoPath;
    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_addimages);
        toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        toolbar.setTitle("Añadir Imagen");
        setSupportActionBar(toolbar);

        // para rotar la imagen
        img= (ImageView)findViewById(R.id.img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_images = (Button) findViewById(R.id.btn_images);
        btn_images.setOnClickListener(this);
        btn_continue = (Button) findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(this);
        //rotarImagen(img);





    }

    /*private void rotarImagen(View view){
        RotateAnimation animation = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        animation.setDuration(2000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(animation);
    }*/

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_images:
                showOptions();
                break;
            case R.id.btn_continue:
                //Intent i = new Intent(AddImages.this,Drawer.class);
                if (imageIsGood()) {
                    //String image = convertToString(bitmap);

                    new publicationInsert().execute("http://tuxdeudas.com/arrendart/insertpublication.php?" +
                            "pub_state=" + publica.getPub_state().getState_id() +
                            "&pub_user=" + publica.getPub_user().getUser_email() +
                            "&pub_city=" + publica.getPub_city().getCity_id() +
                            "&pub_subcity=" + publica.getPub_subcity().getSubcity_id() +
                            "&pub_type=" + publica.getPub_type().getType_id() +
                            "&pub_period=" + publica.getPub_period().getPeriod_id() +
                            "&pub_name=" + publica.getPub_name() +
                            "&pub_description=" + publica.getPub_description() +
                            "&pub_address=" + publica.getPub_address() +
                            "&pub_latitude=" + publica.getPub_latitude() +
                            "&pub_longitude=" + publica.getPub_longitude() +
                            "&pub_numberroom=" + publica.getPub_numerroom() +
                            "&pub_numberbath=" + publica.getPub_numberbath() +
                            "&pub_price=" + publica.getPub_price() +
                            "&pub_surface=" + publica.getPub_surface() +
                            "&pub_numberfloor=" + publica.getPub_numerfloor() +
                            "&pub_forniture=" + publica.getPub_forniture()+
                            "&pub_date=1" +
                            "&pub_block=" + publica.getPub_block() +
                            "&pub_numberblock=" + publica.getPub_numberblock());
                    Intent i = new Intent(AddImages.this,Success.class);
                    startActivity(i);
                    break;
                }
        }

    }

    private String convertToString(Bitmap bitmap) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
        byte[] imageByte=array.toByteArray();
        String imgString= Base64.encodeToString(imageByte,Base64.DEFAULT);
        return imgString;
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



    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException
    {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    private class  publicationInsert extends AsyncTask<String, Void, String> {
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

            Toast.makeText(getApplicationContext(), "Publicación registrada existosamente!", Toast.LENGTH_LONG).show();
        }
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {

                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "MyPicture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
                photoURI = getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                //Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), "com.example.android.fileprovider", photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath= image.getAbsolutePath();
        return image;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TAKE_PHOTO && resultCode == RESULT_OK) {

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoURI);
                img.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(requestCode==100){
            photoURI = data.getData();
            img.setImageURI(photoURI);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoURI);
            } catch (IOException e) {
                e.printStackTrace();
            }
            img.setImageBitmap(bitmap);

        }
    }





    private void showOptions() {
        final CharSequence[] option = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddImages.this);
        builder.setTitle("Eleige una opción");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (option[which] == "Tomar foto") {
                    openCamera();
                } else if (option[which] == "Elegir de galeria") {
                    openGallery();

                } else {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }



    public void openCamera() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(AddImages.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(AddImages.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(AddImages.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        225);
            }


            if (ActivityCompat.shouldShowRequestPermissionRationale(AddImages.this,
                    Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(AddImages.this,
                        new String[]{Manifest.permission.CAMERA},
                        226);
            }
        } else {
            dispatchTakePictureIntent();
        }

    }


    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,100 );
    }

    public boolean imageIsGood(){
        if (img.getDrawable()==null){
            Toast.makeText(getApplicationContext(),"Es obligatorio añadir una imagen",Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

}

