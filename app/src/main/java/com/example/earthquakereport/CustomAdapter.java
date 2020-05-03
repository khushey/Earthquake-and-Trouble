package com.example.earthquakereport;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    private final String TAG = "CustomAdapter";
    private Context context;
    private List<Earthquake> earthquakeList;
    private Double double_magnitude;
    private String string_magnitude;
    private Date date_;
    private String dateInString;

    public CustomAdapter(Context context, int resource, List objects) {

        super(context, 0, objects);

        this.context = context;
        this.earthquakeList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_layout, parent, false);

        TextView magnitude = (TextView) convertView.findViewById(R.id.magnitude);
        TextView location = (TextView) convertView.findViewById(R.id.location);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        date.setText(earthquakeList.get(position).getDate());
        magnitude.setText(earthquakeList.get(position).getMagnitude());
        location.setText(earthquakeList.get(position).getLocation());

        return convertView;
    }
}
