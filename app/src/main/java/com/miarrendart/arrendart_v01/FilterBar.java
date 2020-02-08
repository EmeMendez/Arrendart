package com.miarrendart.arrendart_v01;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterBar extends AppCompatActivity {
    Toolbar toolbar_menu;
    RecyclerView myRecycler;
    PublicationAdapter publicationAdapter;
    TextView txt_filter;
    ArrayList<Data> array =  new ArrayList<Data>();
    Button btn_filter;
    AlertDialog.Builder alertdialogbuilder;
    String[] AlertDialogItems = new String[]{"Casa", "Departamento", "Habitación", "Arriento Diario","Arriendo Mensual","Arriendo por 1 año"
    };
    List<String> ItemsIntoList;
    boolean[] Selectedtruefalse = new boolean[]{false, false, false, false, false,false,false};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a_filterbar);
        txt_filter = (TextView) findViewById(R.id.txt_filter) ;
        myRecycler = (RecyclerView) findViewById(R.id.myRecycler);
        btn_filter= (Button) findViewById(R.id.btn_filter);



        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(FilterBar.this, LinearLayoutManager.VERTICAL, false);
        myRecycler.setLayoutManager(mLinearLayoutManager);
        myRecycler.addItemDecoration(new DividerItemDecoration(FilterBar.this,
                DividerItemDecoration.VERTICAL));
        completeData();
        alert();

        toolbar_menu = (Toolbar) findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar_menu);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    public void completeData() {
    /*
        City ls = new City("1","La Serena");
        City cqb = new City("2","Coquimbo");


        array.add(new Data("Casa casita muy bonita",ls,"Es una casita muy mona y chevere, tiene 2 waters, 2 piezas","777","los perales #234",R.drawable.img1,false,"5km"));
        array.add(new Data("Grande casona cerca del ekono",cqb,"En esta casa penan, pero son amistosos, solo molestan en la tarde","888","los lucumos #432",R.drawable.img2,false,"6km"));
        array.add(new Data("Casa casita muy bonita",ls,"Es una casita muy mona y chevere, tiene 2 waters, 2 piezas","777","los perales #234",R.drawable.img1,false,"5km"));
        array.add(new Data("Grande casona cerca del ekono",cqb,"En esta casa penan, pero son amistosos, solo molestan en la tarde","888","los lucumos #432",R.drawable.img2,false,"6km"));
        array.add(new Data("Casa casita muy bonita",ls,"Es una casita muy mona y chevere, tiene 2 waters, 2 piezas","777","los perales #234",R.drawable.img1,false,"5km"));
        array.add(new Data("Grande casona cerca del ekono",cqb,"En esta casa penan, pero son amistosos, solo molestan en la tarde","888","los lucumos #432",R.drawable.img2,false,"6km"));
        array.add(new Data("Casa casita muy bonita",ls,"Es una casita muy mona y chevere, tiene 2 waters, 2 piezas","777","los perales #234",R.drawable.img1,false,"5km"));
        array.add(new Data("Grande casona cerca del ekono",cqb,"En esta casa penan, pero son amistosos, solo molestan en la tarde","888","los lucumos #432",R.drawable.img2,false,"6km"));
        array.add(new Data("Casa casita muy bonita",ls,"Es una casita muy mona y chevere, tiene 2 waters, 2 piezas","777","los perales #234",R.drawable.img1,false,"5km"));
        array.add(new Data("Grande casona cerca del ekono",cqb,"En esta casa penan, pero son amistosos, solo molestan en la tarde","888","los lucumos #432",R.drawable.img2,false,"6km"));
        publicationAdapter = new PublicationAdapter(array, FilterBar.this);

        myRecycler.setAdapter(publicationAdapter);*/
    }


    public void alert(){
        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertdialogbuilder = new AlertDialog.Builder(FilterBar.this,R.style.AlertDialogStyle);
                ItemsIntoList = Arrays.asList(AlertDialogItems);
                alertdialogbuilder.setMultiChoiceItems(AlertDialogItems, Selectedtruefalse, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    }
                });
                alertdialogbuilder.setCancelable(false);
                alertdialogbuilder.setTitle("Titulo del dialogo");
                alertdialogbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override


                    public void onClick(DialogInterface dialog, int which) {
                        txt_filter.setText("");
                        int a = 0;
                        while(a < Selectedtruefalse.length)
                        {
                            boolean value = Selectedtruefalse[a];

                            if(value){
                                txt_filter.setText(txt_filter.getText() + ItemsIntoList.get(a) + ",");
                            }

                            a++;
                        }

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
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater charge_menu = getMenuInflater();
        charge_menu.inflate(R.menu.m_list,menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())

        {
            case R.id.menu_gps:
                Intent i = new Intent(FilterBar.this,Maps.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
