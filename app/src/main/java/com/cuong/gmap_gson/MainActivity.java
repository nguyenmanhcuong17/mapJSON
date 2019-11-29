package com.cuong.gmap_gson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cuong.model.Address;
import com.cuong.model.Geometry;
import com.cuong.model.Location;
import com.cuong.model.North;
import com.cuong.model.PlusCode;
import com.cuong.model.Result;
import com.cuong.model.South;
import com.cuong.model.Viewport;
import com.cuong.model.ZMap;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    ListView lvKq;
    ArrayList<Address> dsZMap1;
//    ArrayAdapter<Address>adapterZMap;

    TextView txtKq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        txtKq = findViewById(R.id.txtKq);
        txtKq.setMovementMethod(new ScrollingMovementMethod());
//        lvKq = findViewById(R.id.lvKq);
//        dsZMap = new ArrayList<>();
//        adapterZMap = new ArrayAdapter<>(
//                MainActivity.this,
//                android.R.layout.simple_list_item_1,
//                dsZMap
//        );
//
//        lvKq.setAdapter(adapterZMap);
    }

    private void addEvents() {
        MapTask task = new MapTask();
        task.execute();
    }

    class MapTask extends AsyncTask<Void, Void, ZMap>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtKq.setText("");
        }

        @Override
        protected void onPostExecute(ZMap zMap) {
            super.onPostExecute(zMap);
            txtKq.setText(zMap.toString());
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ZMap doInBackground(Void... voids) {

            try {
                String link = "https://maps.googleapis.com/maps/api/geocode/json?address=Nikolaus-Gro%C3%9F-Str.%208%2C%2046397&key=AIzaSyA3ddb-ngiE805gJiRqEWwoF1Qwjhfjg6Y";
                URL url = new URL(link);
//                InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
//
//                ZMap zz = new Gson().fromJson(inputStreamReader,ZMap.class);
//
//                JSONObject jsonZMap = new JSONObject(zz.toString());
//                JSONArray resultArr = jsonZMap.getJSONArray("results");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json; charset=utf-8");
                connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
                connection.setRequestProperty("Accept", "*/*");

                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String line = br.readLine();
                StringBuilder sb = new StringBuilder();
                while (line!= null)
                {
                    sb.append(line);
                    line = br.readLine();
                }

                String json = sb.toString();

                // jsonObject obj dau tien , toan bo thong tin - obj lv 1
                JSONObject jsonObject = new JSONObject(json);
                ZMap zMap = new ZMap();

                // Array lv 1 ( chi co 1 phan tu )
                JSONArray jsonArrayResults = jsonObject.getJSONArray("results");


                // lay phan tu trong array
                JSONObject jsonObjResult = jsonArrayResults.getJSONObject(0);
                    JSONObject plusCodeObject  = jsonObjResult.getJSONObject("plus_code");
                //JSONObject plusCodeObject  = new JSONObject(jsonObjResult.getString("plus_code")) ;

                JSONArray componentArr = jsonObjResult.getJSONArray("address_components");
                ArrayList<Address>dsAddress = new ArrayList<>();
                for (int i = 0; i< componentArr.length(); i++)
                {

                    JSONObject js = componentArr.getJSONObject(i);
                    Address address = new Address();
                    address.setLong_name(js.getString("long_name"));
                    address.setShort_name(js.getString("short_name"));
                    dsAddress.add(address);

                    ArrayList<String>dsType2 = new ArrayList<>();
                    JSONArray typeArr = js.getJSONArray("types");
                    for (int j = 0 ; j<typeArr.length(); j++ )
                    {
                        dsType2.add(typeArr.getString(j));
                    }
                    address.setTypes(dsType2);
                }

                // xu ly obj
                Result resultObj = new Result();

                ArrayList<String>dsType = new ArrayList<>();

                    PlusCode plusCode = new PlusCode();

                //Location location = new Location();

                    // geometry
                    Geometry geometry = new Geometry();
                    JSONObject geometryObject = jsonObjResult.getJSONObject("geometry");

                        // location obj
                        Location location  = new Location();
                        JSONObject locationObject = geometryObject.getJSONObject("location");
                        location.setLat(Double.parseDouble(locationObject.getString("lat")) );
                        location.setLng(Double.parseDouble(locationObject.getString("lng")));

                    // geometry set
                    geometry.setLocation(location);
                    geometry.setLocation_type(geometryObject.getString("location_type"));

                    resultObj.setGeometry(geometry);

                        // north obj
                North north = new North();
                South south = new South();
                Viewport viewport = new Viewport();

                JSONObject viewportObj = geometryObject.getJSONObject("viewport");
                JSONObject northObj = viewportObj.getJSONObject("northeast");
                JSONObject southObj = viewportObj.getJSONObject("southwest");
                north.setLat(Double.parseDouble(northObj.getString("lat")));
                north.setLng(Double.parseDouble(northObj.getString("lng")));
                south.setLat(Double.parseDouble(southObj.getString("lat")));
                south.setLng(Double.parseDouble(southObj.getString("lng")));
                viewport.setNortheast(north);
                viewport.setSouthwest(south);
                    geometry.setViewport(viewport);

                    // place id
                    resultObj.setPlace_id(jsonObjResult.getString("place_id"));
                    // plus code
                    plusCode.setCompound_code(plusCodeObject.getString("compound_code"));
                    plusCode.setGlobal_code(plusCodeObject.getString("global_code"));
                    resultObj.setPlus_code(plusCode);

                    resultObj.setAddress_components(dsAddress);

                    // type
                    dsType.add(jsonObjResult.getJSONArray("types").getString(0)  );
                    resultObj.setTypes(dsType);

                resultObj.setFormatted_address(jsonObjResult.getString("formatted_address"));




                zMap.setStatus(jsonObject.getString("status"));
                zMap.setResults(resultObj);

                return zMap;
            }catch (Exception ex)
            {
                Log.e("ERROR", ex.toString());
            }

            return null;
        }
        //        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            adapterZMap.clear();
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<Address> zMaps) {
//            super.onPostExecute(zMaps);
//            adapterZMap.clear();
//            adapterZMap.addAll(zMaps);
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }

//        @Override
//        protected ArrayList<Address> doInBackground(Void... voids) {
//            ArrayList<Address>ds = new ArrayList<>();
//            try {
//
//                String link = "https://maps.googleapis.com/maps/api/geocode/json?address=Nikolaus-Gro%C3%9F-Str.%208%2C%2046397&key=AIzaSyA3ddb-ngiE805gJiRqEWwoF1Qwjhfjg6Y";
//                URL url = new URL(link);
//                InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
//                Result zMap = new Gson().fromJson(inputStreamReader,Result.class);
//                ds = zMap.getAddress_components();
//                //adapterZMap.addAll(ds);
//
//
//            }catch (Exception ex)
//            {
//                Log.e("ERROR", ex.toString());
//            }
//            return ds;
//        }
    }
}
