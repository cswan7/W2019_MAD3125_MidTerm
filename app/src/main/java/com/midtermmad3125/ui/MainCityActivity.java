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

        //Read cnt JSON object
        JSONObject mCnt = mCnt.getJSONObject();
        String cnt = mCnt.getString("cnt");
        Log.d("JSON:");


        //Read coord JSON Object
        JSONObject mCoord = mCoord.getJSONObject("coord");
        String lon = mCoord.getString("lon");
        String lat = mCoord.getString("lat");

        Log.d(TAG, "JSON:", lon + " , " + lat);
        if(jsonString!=null){
            try{
                JSONArray mJSONArray=new JSONArray(jsonString);
                JSONObject mList = new JSONObject("list");
                for (int i=0;i<mList.length();i++)
                {
                   JSONObject mJSONObject = mJSONArray.getJSONObject(i);

                   String dt = mJSONObject.getString("dt");
                    Log.d("JSON:", dt);

                   //Read temp JSON Object
                    JSONObject mTemp = mJSONObject.getJSONObject("temp");
                    String day = mTemp.getString("day");
                    String


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
