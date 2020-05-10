package com.example.earthquakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    private static final String TAG = "EarthquakeActivity";
    EarthquakeAsyncTask earthquakeAsyncTask = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        earthquakeAsyncTask = new EarthquakeAsyncTask(asyncResponse, this);
        earthquakeAsyncTask.execute();
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            QueryUtils.extractEarthquakes(earthquakeAsyncTask.getJsonResponse()).get(position).getURL();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(QueryUtils.extractEarthquakes(earthquakeAsyncTask.getJsonResponse()).get(position).getURL()));
            startActivity(intent);
        }
    };

    EarthquakeAsyncTask.AsyncResponse asyncResponse = new EarthquakeAsyncTask.AsyncResponse() {

        //this function will be called in the onPostExecute() method in earthquakeAsynkTask.
        @Override
        public void processCompleted() {
            Log.d(TAG, "Inside Main Activity; process completed");
            ListView listView =  (ListView) findViewById(R.id.earthquake_list);
            earthquakeAsyncTask.setAdapter(listView);
            listView.setOnItemClickListener(onItemClickListener);
        }
    };
}