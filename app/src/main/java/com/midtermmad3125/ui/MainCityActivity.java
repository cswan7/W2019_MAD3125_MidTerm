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
        try {
            JSONObject mCity = new JSONObject();
            mCity = mCity.getJSONObject("city");

            String name = mCity.getString("name");
            String country = mCity.getString("country");
            String population = mCity.getString("population");
            Log.d("JSON:", name + " , " + country + " , " + population);
        }catch (Exception e){
            e.printStackTrace();
        }

        //Read coord JSON Object
        JSONObject mCoord= new JSONObject();
        try {
            mCoord = mCoord.getJSONObject("coord");
            String lon = mCoord.getString("lon");
            String lat = mCoord.getString("lat");

            Log.d("JSON:", lon + " , " + lat);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //Read list JSON Object
        if(jsonString!=null){
            try{
                JSONArray mJSONArray=new JSONArray(jsonString);
                JSONObject mJSONObject = new JSONObject("list");
                for (int i=0;i<16;i++)
                {
                   JSONObject mList = mJSONArray.getJSONObject(i);

                   String dt = mList.getString("dt");
                   String pressure = mList.getString("pressure");
                   String humidity = mList.getString("humidity");
                   String speed = mList.getString("speed");
                   String deg= mList.getString("deg");
                   String clouds=mList.getString("clouds");
                   String rain=mList.getString("rain");


                   //Read temp JSON Object
                    JSONObject mTemp = mJSONObject.getJSONObject("temp");
                    String day = mTemp.getString("day");
                    String min = mTemp.getString("min");
                    String max = mTemp.getString("max");
                    String night = mTemp.getString("night");
                    String eve = mTemp.getString("eve");
                    String morn = mTemp.getString("morn");


                    //Read weather JSON Array
                    JSONArray mWeather = new JSONArray(jsonString);
                    JSONObject weather = new JSONObject("weather");
                    for(int j=0; j<weather.length();j++)
                    {
                    JSONObject myWeather = mWeather.getJSONObject(j);

                    String id = myWeather.getString("id");
                    String main = myWeather.getString("main");
                    String description = myWeather.getString("description");
                    String icon= myWeather.getString("icon");


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
