package com.miarrendart.arrendart_v01.Activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.miarrendart.arrendart_v01.Classes.City;
import com.miarrendart.arrendart_v01.Classes.Period;
import com.miarrendart.arrendart_v01.Classes.Publication;
import com.miarrendart.arrendart_v01.Classes.State;
import com.miarrendart.arrendart_v01.Classes.SubCity;
import com.miarrendart.arrendart_v01.Classes.Type;
import com.miarrendart.arrendart_v01.Classes.User;
import com.miarrendart.arrendart_v01.PublicationAdapter;
import com.miarrendart.arrendart_v01.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Drawer_publication.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Drawer_publication#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Drawer_publication extends Fragment {
    ListView listView;
    TextView textView;
    String[] listItem;
    AsyncHttpClient client;
    PublicationAdapter publicationAdapter;
    RecyclerView myRecycler;
    View layout_house;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Drawer_publication() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Drawer_publication.
     */
    // TODO: Rename and change types and number of parameters
    public static Drawer_publication newInstance(String param1, String param2) {
        Drawer_publication fragment = new Drawer_publication();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.f_drawer_publication, container, false);
        layout_house = (View) view.findViewById(R.id.layout_house);
        client = new AsyncHttpClient();
        myRecycler = (RecyclerView)  view.findViewById(R.id.pub_recycler);
        layout_house.setVisibility(View.GONE);
        getMyPublications();

       /* listView=(ListView)view.findViewById(R.id.listView);
        textView=(TextView)view.findViewById(R.id.textView);


        listItem = getResources().getStringArray(R.array.array_technology);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.listview_model, R.id.textView, listItem);
        listView.setAdapter(adapter);*/

        return view;
        // Inflate the layout for this fragment
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void getMyPublications(){
        String url  = "http://www.tuxdeudas.com/arrendart/getmypublications.php?pub_user="+Singin.user.getUser_email();
        client.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                listMyPublications(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public void listMyPublications(String response){
        ArrayList<Publication> array;
        try {
            JSONArray ja = new JSONArray(response);
            array=new ArrayList<Publication>();
            for (int i = 0; i<ja.length();i++){
                Publication  p = new Publication();
                p.setPub_id(ja.getJSONObject(i).getString("pub_id"));
                p.setPub_state(new State());
                p.getPub_state().setState_id(ja.getJSONObject(i).getString("pub_state"));
                p.setPub_user(new User());
                p.getPub_user().setUser_id(ja.getJSONObject(i).getString("pub_user"));
                p.setPub_city(new City());
                p.getPub_city().setCity_id(ja.getJSONObject(i).getString("pub_city"));
                p.getPub_city().setCity_description(ja.getJSONObject(i).getString("city_description"));

                p.setPub_subcity(new SubCity());
                p.getPub_subcity().setSubcity_id(ja.getJSONObject(i).getString("pub_subcity"));
                p.setPub_type(new Type());
                p.getPub_type().setType_id(ja.getJSONObject(i).getString("pub_type"));
                p.setPub_period(new Period());
                p.getPub_period().setPeriod_id(ja.getJSONObject(i).getString("pub_period"));
                p.setPub_name(ja.getJSONObject(i).getString("pub_name"));
                p.setPub_description(ja.getJSONObject(i).getString("pub_description"));
                p.setPub_address(ja.getJSONObject(i).getString("pub_address"));
                p.setPub_latitude(ja.getJSONObject(i).getDouble("pub_latitude"));
                p.setPub_longitude(ja.getJSONObject(i).getDouble("pub_longitude"));
                p.setPub_numerroom(ja.getJSONObject(i).getString("pub_numberroom"));
                p.setPub_numberbath(ja.getJSONObject(i).getString("pub_numberbath"));
                p.setPub_price(ja.getJSONObject(i).getString("pub_price"));
                p.setPub_surface(ja.getJSONObject(i).getString("pub_surface"));
                p.setPub_numerfloor(ja.getJSONObject(i).getString("pub_numberfloor"));
                p.setPub_forniture(ja.getJSONObject(i).getString("pub_numberfloor"));
                p.setPub_date(ja.getJSONObject(i).getString("pub_date"));
                p.setPub_img(R.drawable.img1);

                array.add(p);
            }
            if(array.isEmpty()) {
                layout_house.setVisibility(View.VISIBLE);
            }
           else{
                layout_house.setVisibility(View.GONE);
            }
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager( getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            myRecycler.setLayoutManager(mLinearLayoutManager);
            myRecycler.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(),
                    DividerItemDecoration.VERTICAL));
            publicationAdapter = new PublicationAdapter(array, getActivity().getApplicationContext());
            myRecycler.setAdapter(publicationAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(),"Este es el error " + e, Toast.LENGTH_LONG).show();
        }

    }


}
