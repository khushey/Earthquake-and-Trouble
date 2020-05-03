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
        TextView time = (TextView )convertView.findViewById(R.id.time);

        time.setText(earthquakeList.get(position).formatTime());
        date.setText(earthquakeList.get(position).fotmatDate());
        magnitude.setText(earthquakeList.get(position).getMagnitude());

        String offset, primaryLoc;
        String locArray[] = earthquakeList.get(position).getLocation().split("of", 2);

        if (locArray.length == 2)
        {
            offset = locArray[0];
            primaryLoc = locArray[1];
            Log.d(TAG, offset + ":" + primaryLoc);
        }
        else if (locArray.length == 1)
        {
            offset = "Near the";
            primaryLoc = locArray[0];
            Log.d(TAG, offset + " " + primaryLoc);
        }


        return convertView;
    }
}
