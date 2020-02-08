package com.miarrendart.arrendart_v01.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.miarrendart.arrendart_v01.R;

import java.util.ArrayList;

public class ServiceAdapter extends BaseAdapter {
    Context context;
    ArrayList<Service> array_ser = new ArrayList<Service>();
    LayoutInflater inflater;


    public ServiceAdapter(Context context, ArrayList<Service> array_ser) {
        this.context = context;
        this.array_ser = array_ser;
        inflater = (LayoutInflater.from(context));

    }

    @Override
    public int getCount() {
        return array_ser.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.a_services_template, null);
        CheckBox check = (CheckBox) view.findViewById(R.id.scheck);
        TextView item = (TextView) view.findViewById(R.id.sitem);
        ImageView image = (ImageView) view.findViewById(R.id.simg);
        item.setText(array_ser.get(i).getSer_description());
        check.setChecked(array_ser.get(i).getSer_check());
        image.setImageResource(array_ser.get(i).getSer_img());
        return view;
    }
}
