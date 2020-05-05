package com.example.earthquakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public final class QueryUtils {


    private static final String SAMPLE_JSON_RESPONSE =
            "{\"type\":\"FeatureCollection\",\"metadata\":{\"generated\":1462295443000,\"url\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-01-31&minmag=6&limit=10\",\"title\":\"USGS Earthquakes\",\"status\":200,\"api\":\"1.5.2\",\"limit\":10,\"offset\":1,\"count\":10},\"features\":[" +
                    "{\"type\":\"Feature\",\"properties\":{\"mag\":6.9343242342234436,\"place\":\"Pacific-Antarctic Ridge\",\"time\":1451986454620,\"updated\":1459202978040,\"tz\":-540,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/us10004bgk\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us10004bgk&format=geojson\",\"felt\":0,\"cdi\":1,\"mmi\":0,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":554,\"net\":\"us\",\"code\":\"10004bgk\",\"ids\":\",us10004bgk,gcmt20160105093415,\",\"sources\":\",us,gcmt,\",\"types\":\",cap,dyfi,geoserve,losspager,moment-tensor,nearby-cities,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":30.75,\"rms\":0.67,\"gap\":71,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.0 - Pacific-Antarctic Ridge\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-136.2603,-54.2906,10]},\"id\":\"us10004bgk\"}],\"bbox\":[-153.4051,-54.2906,10,158.5463,59.6363,582.56]}";


    public static ArrayList<Earthquake> extractEarthquakes(){
        ArrayList<Earthquake> earthquakeArrayList = new ArrayList<>();
        try{
            JSONObject earthquakeObject = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONArray earthquakeArray = earthquakeObject.optJSONArray("features");
            String time, location, magnitude;

            for(int i = 0; i < earthquakeArray.length(); i++){
                JSONObject propeties = earthquakeArray.getJSONObject(i).optJSONObject("properties");
                earthquakeArrayList.add(new Earthquake(propeties.optDouble("mag"), propeties.optString("place"), propeties.optLong("time"), propeties.optString("url")) );
            }
        }
        catch(JSONException e)
        {
            Log.d("QueryUtils", "Nothing too good");
        }
        return earthquakeArrayList;
    }
}
