package com.miarrendart.arrendart_v01.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.MenuInflater;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.miarrendart.arrendart_v01.Classes.Favorites;
import com.miarrendart.arrendart_v01.PublicationAdapter;
import com.miarrendart.arrendart_v01.R;
import com.miarrendart.arrendart_v01.Settings;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

public class Drawer extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener, Drawer_home.OnFragmentInteractionListener, Drawer_favorites.OnFragmentInteractionListener, Drawer_publication.OnFragmentInteractionListener, Drawer_account.OnFragmentInteractionListener {
    NavigationView navigationView;
    public static int star;
    SearchView searchView;
    public static String search;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        progressDialog = new ProgressDialog(Drawer.this);



        toolbar.setTitle("ArrendArt");
        setSupportActionBar(toolbar);
        final FloatingActionButton fab = findViewById(R.id.fab);
        FloatingButton(fab);//icono añadir nueva publicación

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            navigationView = findViewById(R.id.nav_view);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            navigationView.setNavigationItemSelectedListener(this);
            //navigationView.getMenu().findItem(R.id.nav_publication).setVisible(false);
        if(Singin.user==null) {
            navigationView.getMenu().findItem(R.id.nav_publication).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_exit).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_account).setVisible(false);
            View headerView = navigationView.getHeaderView(0);

            CircleImageView nav_img_circle = (CircleImageView) headerView.findViewById(R.id.nav_img_circle);
            nav_img_circle.setImageResource(R.drawable.ico);
            nav_img_circle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Drawer.this, Singin.class);
                    startActivity(i);
                }
            });
            TextView nav_txt_email = (TextView) headerView.findViewById(R.id.nav_txt_email);
            nav_txt_email.setText("ArrendArt");
            nav_txt_email.setVisibility(View.GONE);
            TextView nav_txt_name = (TextView) headerView.findViewById(R.id.nav_txt_name);
            nav_txt_name.setText("Iniciar sesión");
            nav_txt_name.setTextSize(25);
            nav_txt_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Drawer.this, Singin.class);
                    startActivity(i);
                }
            });
        }else{
            View headerView = navigationView.getHeaderView(0);
            TextView nav_txt_email = (TextView) headerView.findViewById(R.id.nav_txt_email);
            TextView nav_txt_name = (TextView) headerView.findViewById(R.id.nav_txt_name);
            nav_txt_email.setText(Singin.user.getUser_email());
            nav_txt_name.setText(Singin.user.getUser_name() + " " + Singin.user.getUser_lastnames());

        }




        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_drawer,new Drawer_home());
        ft.commit();
        navigationView.setCheckedItem(R.id.nav_home);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater charge_menu = getMenuInflater();
        charge_menu.inflate(R.menu.drawer,menu);
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                //Here u can get the value "query" which is entered in the search box.
                search = query;
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        return super.onCreateOptionsMenu(menu);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_gps) {

            Intent i = new Intent(Drawer.this,Map2.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_filter) {
            AlertFilter();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            ft.replace(R.id.content_drawer,new Drawer_home());
            ft.commit();
        } else if (id == R.id.nav_account) {
            ft.replace(R.id.content_drawer,new Drawer_account());
            ft.commit();
        } else if (id == R.id.nav_favorites) {
            ft.replace(R.id.content_drawer,new Drawer_favorites());
            ft.commit();
        } else if (id == R.id.nav_publication) {
            ft.replace(R.id.content_drawer,new Drawer_publication());
            ft.commit();
        } else if (id == R.id.nav_tools) {
            Intent i = new Intent(Drawer.this, Settings.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {
            //f = null;
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "Hola bb, que esperas, descarga ArrendArt! http://play.google.com/store/apps/details?id=com.miarrendart.arrendart_v01");
            startActivity(Intent.createChooser(intent, "Comparte ArrendArt!"));
        } else if (id == R.id.nav_exit) {

            showDialog();




        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    public void AlertFilter(){
        AlertDialog.Builder alertdialogbuilder;
        String[] AlertDialogItems = new String[]{"Todo","Casa", "Departamento", "Habitación", "Arriendo Diario","Arriendo Mensual","Arriendo por 1 año"};
        List<String> ItemsIntoList;
        boolean[] Selectedtruefalse = new boolean[]{true,false, false, false, false, false,false,false};
        alertdialogbuilder = new AlertDialog.Builder(Drawer.this,R.style.AlertDialogStyle);
        ItemsIntoList = Arrays.asList(AlertDialogItems);
        alertdialogbuilder.setMultiChoiceItems(AlertDialogItems, Selectedtruefalse, new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) { }});
        alertdialogbuilder.setCancelable(false);
        alertdialogbuilder.setTitle("Filtrar por :");
        alertdialogbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            //acción para filtrar
                }
           });
        alertdialogbuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            }
        });
         AlertDialog dialog = alertdialogbuilder.create();
                dialog.show();
    }



    public void FloatingButton(FloatingActionButton fab){
        if(Singin.user!=null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   /*Snackbar.make(view, "Este botón agregará nueva publicación", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/
                   Intent i = new Intent(Drawer.this,SpinnerType.class);
                   startActivity(i);
                }
            });
        }else{
            fab.hide();
        }
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogStyle2);
        builder.setMessage("Seguro que deseas cerrar sesión")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Singin.user=null;
                        Intent i = new Intent(Drawer.this,Singin.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "Has cerrado sesión", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }




    public Favorites createFavorite(){
        Favorites fav = new Favorites();
        if(Singin.user!=null) {
            fav.setFav_pub(PublicationAdapter.pub_id);
            fav.setFav_user(Singin.user.getUser_email());
        }
        return fav;
    }


    public void addFavorites(Favorites f){
        String url = "http://www.tuxdeudas.com/arrendart/insertFavorites.php?fav_pub="+f.getFav_pub() +
                "&fav_user="+f.getFav_user();
        AsyncHttpClient client;
        client = new AsyncHttpClient();

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }


    public void deleteFavorites(String pub,String user) {
        String url = "http://www.tuxdeudas.com/arrendart/deleteFavorite.php?fav_pub="+ pub + "&fav_user=" + user;
        AsyncHttpClient client;
        client = new AsyncHttpClient();

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    public class HeavyWorker extends AsyncTask< String , Context , Void > {

        private ProgressDialog progressDialog ;
        private Context             targetCtx ;
        private boolean             needToShow;

        public HeavyWorker ( Context context ) {
            this.targetCtx = context ;
            this.needToShow= true;
            progressDialog = new ProgressDialog ( targetCtx ) ;
            progressDialog.setCancelable ( false ) ;
            progressDialog.setMessage ( "Retrieving data..." ) ;
            progressDialog.setTitle ( "Please wait" ) ;
            progressDialog.setIndeterminate ( true ) ;
        }

        @ Override
        protected void onPreExecute ( ) {
            progressDialog.show ( ) ;
        }

        @ Override
        protected Void doInBackground ( String ... params ) {
            // Do Your WORK here

            return null ;
        }

        @ Override
        protected void onPostExecute ( Void result ) {
            if(progressDialog != null && progressDialog.isShowing()){
                progressDialog.dismiss ( ) ;
            }
        }
    }






}







