package com.example.earthquakereport;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import android.graphics.drawable.GradientDrawable;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArraySet;

public class CustomAdapter extends ArrayAdapter {

    private final String TAG = "CustomAdapter";
    private Context context;
    private List<Earthquake> earthquakeList;

    public CustomAdapter(Context context, int resource, List objects) {

        super(context, 0, objects);

        this.context = context;
        this.earthquakeList = objects;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_layout, parent, false);

        TextView magnitude = (TextView) convertView.findViewById(R.id.magnitude);
        TextView location = (TextView) convertView.findViewById(R.id.primaryLoc);
        TextView offset = (TextView) convertView.findViewById(R.id.offsetLoc);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView time = (TextView )convertView.findViewById(R.id.time);

        String locArray[] = earthquakeList.get(position).getLocation().split("of ", 2);

        if (locArray.length == 2)
        {
            offset.setText(locArray[0].toUpperCase()+" OF");
            location.setText(locArray[1]);
        }
        else if (locArray.length == 1)
        {
            offset.setText("NEAR THE");
            location.setText(locArray[0]);
        }

        time.setText(earthquakeList.get(position).formatTime());
        date.setText(earthquakeList.get(position).fotmatDate());
        magnitude.setText(earthquakeList.get(position).getMagnitude().second);

//        GradientDrawable myDrawable = (GradientDrawable) magnitude.getBackground();

        Drawable magCircle = (Drawable) magnitude.getBackground();
//        Drawable magCircle = ContextCompat.getDrawable(context, R.drawable.magnitude_circle);
        switch(earthquakeList.get(position).getMagnitude().first){
            case 0:
                magCircle.setTint(ContextCompat.getColor(getContext(), R.color.magnitude1));
                break;
            case 1:
                magCircle.setTint(ContextCompat.getColor(getContext(), R.color.magnitude2));
                break;
            case 2:
                magCircle.setTint(ContextCompat.getColor(getContext(), R.color.magnitude3));
                break;
            case 3:
                magCircle.setTint(ContextCompat.getColor(getContext(), R.color.magnitude3));
                break;
            case 4:
                magCircle.setTint(ContextCompat.getColor(getContext(), R.color.magnitude4));
                break;
            case 5:
                magCircle.setTint(ContextCompat.getColor(getContext(), R.color.magnitude5));
                break;
            case 6:
                magCircle.setTint(ContextCompat.getColor(getContext(), R.color.magnitude6));
                break;
            case 7:
                magCircle.setTint(ContextCompat.getColor(getContext(), R.color.magnitude7));
                break;
            case 8:
                magCircle.setTint(ContextCompat.getColor(getContext(), R.color.magnitude8));
                break;
            case 9:
                magCircle.setTint(ContextCompat.getColor(getContext(), R.color.magnitude9));
                break;
            case 10:
                magCircle.setTint(ContextCompat.getColor(getContext(), R.color.magnitude10plus));
                break;
                default:
                    magCircle.setTint(ContextCompat.getColor(getContext(), R.color.magnitude1));
        }
        return convertView;
    }
}
