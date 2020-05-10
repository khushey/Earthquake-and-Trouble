package com.example.earthquakereport;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeAsyncTask extends AsyncTask<URL, Void, List<Earthquake>> //params, progress, result.
{

    public static final String TAG = "EarthquakeAsyncTask";
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=4";
//           "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-12-01&minmagnitude=5.5";
    private String jsonResponse = "";
    private Context context;
    private CustomAdapter customAdapter;
    ArrayList<Earthquake> earthquakeArrayList = null;

    public AsyncResponse delegate = null;

    public EarthquakeAsyncTask(AsyncResponse delegate, Context context){
        this.context = context;
        this.delegate = delegate; //object implementing interface Async Response.
    }

    @Override
    protected ArrayList<Earthquake> doInBackground(URL... urls) {
        //convert string to url
        URL url = createURL(USGS_REQUEST_URL);

        try{
            jsonResponse = makeHttpRequest(url);
        }
        catch (IOException exception){
        }
        return earthquakeArrayList;
    }

    public ArrayList<Earthquake> getEarthquakeList(){
        return earthquakeArrayList;
    }

    public String getJsonResponse()
    {
        return jsonResponse;
    }

    private URL createURL(String stringUrl){
        URL url = null;
        try{
            url = new URL(stringUrl);
            Log.d(TAG, "url");
        }
        catch(MalformedURLException exception){
            return null;
        }
        return url;
    }

    private String makeHttpRequest(URL url) throws IOException {
        //create connection object
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String jsonResponse = null;

        try {

            urlConnection = (HttpURLConnection) url.openConnection();

            //manipulate set up parameters
            urlConnection.setRequestMethod("GET");


            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(10000 /* milliseconds */);
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            if (urlConnection!=null)
                urlConnection.disconnect();
            if(inputStream!=null)
                inputStream.close();
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line = bufferedReader.readLine();

        while(line!=null){
            Log.d(TAG, line);
            output.append(line);
            line = bufferedReader.readLine();
        }
        return output.toString();
    }

    public interface AsyncResponse{
        void processCompleted();
    }

    @Override
    protected void onPostExecute(List<Earthquake> earthquakes) {
        super.onPostExecute(earthquakes);

        earthquakeArrayList = QueryUtils.extractEarthquakes(jsonResponse);

        customAdapter = new CustomAdapter
                (context, R.layout.activity_earthquake, earthquakeArrayList);
        delegate.processCompleted();
        p.hide();
    }

    ProgressDialog p;
    @Override
    protected void onPreExecute() {
        Log.d(TAG, "onPreExecute");
        super.onPreExecute();
        p = new ProgressDialog(context);
        p.setMessage("Please wait! Fetching data!");
        p.show();
    }

    public void setAdapter(ListView listView){
        listView.setAdapter(customAdapter);
    }
}
