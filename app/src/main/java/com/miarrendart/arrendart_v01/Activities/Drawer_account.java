package com.miarrendart.arrendart_v01.Activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.miarrendart.arrendart_v01.Classes.User;
import com.miarrendart.arrendart_v01.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Drawer_account.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Drawer_account#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Drawer_account extends Fragment {
    AsyncHttpClient client;
    TextView u_firstemail;
    TextView u_secondemail;
    TextView u_firstname;
    TextView u_secondname;
    TextView u_pass;
    TextView u_phone;
    TextView u_address;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Drawer_account() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Drawer_account.
     */
    // TODO: Rename and change types and number of parameters
    public static Drawer_account newInstance(String param1, String param2) {
        Drawer_account fragment = new Drawer_account();
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
        View v = inflater.inflate(R.layout.f_drawer_account, container, false);
        client = new AsyncHttpClient();
        u_firstemail = (TextView) v.findViewById(R.id.u_firstemail);
        u_secondemail= (TextView) v.findViewById(R.id.u_secondemail);
        u_firstname= (TextView) v.findViewById(R.id.u_firstname);
        u_secondname= (TextView) v.findViewById(R.id.u_secondname);
        u_pass= (TextView) v.findViewById(R.id.u_pass);
        u_phone= (TextView) v.findViewById(R.id.u_phone);
        u_address= (TextView) v.findViewById(R.id.u_address);
        getAccount();
        return v;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    public void getAccount(){
        String url  = "http://www.tuxdeudas.com/arrendart/getUser.php?user_email="+Singin.user.getUser_email();
        client.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                myAccount(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    public void myAccount(String response){
        try {
            JSONArray ja = new JSONArray(response);
            User u = new User();
            u.setUser_email(ja.getString(0));
            u.setUser_name(ja.getString(1));
            u.setUser_lastnames(ja.getString(2));
            u.setUser_date(ja.getString(3));
            u.setUser_address(ja.getString(4));
            u.setUser_pass(ja.getString(5));
            u.setUser_phone(ja.getString(6));

            //pass.replaceAll("\\*", "\\\\*");
            u_firstemail.setText(u.getUser_email());
            u_secondemail.setText(u.getUser_email());
            u_firstname.setText(u.getUser_name() + " " + u.getUser_lastnames());
            u_secondname.setText(u.getUser_name() + " " + u.getUser_lastnames());
            String pass = "";
            int numberPass = u.getUser_pass().length();
            for(int i = 0; i < numberPass; i++){

                pass += "*";

            }
            u_pass.setText(pass);
            u_phone.setText(u.getUser_phone());
            u_address.setText(u.getUser_address());




        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(),"Este es el error " + e, Toast.LENGTH_LONG).show();
        }

    }
}
