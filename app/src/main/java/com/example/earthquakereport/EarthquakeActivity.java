package com.example.earthquakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        ArrayList<Earthquake> earthquakeArrayList = QueryUtils.extractEarthquakes();

        CustomAdapter customAdapter = new CustomAdapter
                (this, R.layout.activity_earthquake, earthquakeArrayList);

        ListView listView =  (ListView) findViewById(R.id.earthquake_list);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(onItemClickListener);
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            QueryUtils.extractEarthquakes().get(position).getURL();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(QueryUtils.extractEarthquakes().get(position).getURL()));
            startActivity(intent);
        }
    };
}
