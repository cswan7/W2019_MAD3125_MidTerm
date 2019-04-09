package com.midtermmad3125.ui;

import android.app.LauncherActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.midtermmad3125.R;
import com.midtermmad3125.utils.ReadJSONUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainCityActivity extends AppCompatActivity
{
    private static final String TAG = "MainCityActivity";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;
    //hello
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void processJSON()
    {
        String jsonString = loadJSONFromAsset();
        //Read city JSON Object
        JSONObject mCity= mCity.getJSONObject("city");
        String name=mCity.getString("name");
        String country=mCity.getString("country");
        String population=mCity.getString("population");
        Log.d("JSON:",name+" , "+country+" , "+population);


        //Read coord JSON Object
        JSONObject mCoord = mCoord.getJSONObject("coord");
        String lon = mCoord.getString("lon");
        String lat = mCoord.getString("lat");

        Log.d(TAG, "JSON:", lon + " , " + lat);

        //Read list JSON Object
        if(jsonString!=null){
            try{
                JSONArray mJSONArray=new JSONArray(jsonString);
                JSONObject mJSONObject = new JSONObject("list");
                for (int i=0;i<16;i++)
                {
                   JSONObject mJSONObject = mJSONArray.getJSONObject(i);

                   String dt = mJSONObject.getString("dt");
                   String pressure = mJSONObject.getString("pressure");
                   String humidity = mJSONObject.getString("humidity");
                   String speed = mJSONObject.getString("speed");
                   String deg= mJSONObject.getString("deg");
                   String clouds=mJSONObject.getString("clouds");
                   String rain=mJSONObject.getString("rain");
                    Log.d("JSON:", dt, pressure, humidity, speed, deg, clouds, rain);

                   //Read temp JSON Object
                    JSONObject mTemp = mJSONObject.getJSONObject("temp");
                    String day = mTemp.getString("day");
                    String min = mTemp.getString("min");
                    String max = mTemp.getString("max");
                    String night = mTemp.getString("night");
                    String eve = mTemp.getString("eve");
                    String morn = mTemp.getString("morn");
                    Log.d("JSON:",day, min, max, night, eve, morn);

                    //Read weather JSON Array
                    JSONArray mWeather = new JSONArray(jsonString);
                    JSONObject weather = new JSONObject("weather");
                    for(int j=0; j<weather.length();j++)
                    {
                    JSONObject weather = mWeather.getJSONObject(j);

                    String id = weather.getString("id");
                    String main = weather.get("main");
                    String description = weather.getString("description");
                    String icon= weather.getString("icon");
                        Log.d("JSON:",id,main,description,icon);

                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }

    private String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("moscow_weather.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
