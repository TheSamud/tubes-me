package com.pengembangsebelah.calculatorrab.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import com.pengembangsebelah.calculatorrab.R;
import com.pengembangsebelah.calculatorrab.model.baru.AnalisaData;
import com.pengembangsebelah.calculatorrab.model.baru.KoefisienPk;
import com.pengembangsebelah.calculatorrab.myutils.Check;
import com.pengembangsebelah.calculatorrab.myutils.db.sqlite.ReaderHelperDb;
import com.pengembangsebelah.calculatorrab.ui.adapte.RecycleAdapterKoefisienUpdate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UpdateAnalisaActivity extends AppCompatActivity {
    ReaderHelperDb databaseProject;
    static KoefisienPk mykoefisien;

    public static List<AnalisaData> AnalisaDatas(String analisa,Activity a,List<AnalisaData> da){
        Log.d("DOIK", "AnalisaDatas: "+analisa);
        try {
            List<AnalisaData> _DATA =new ArrayList<>();
            JSONObject obj = new JSONObject(loadJSON(a));
            JSONArray m_jArry = obj.getJSONArray("data");
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject pek_proy = m_jArry.getJSONObject(i);
                if(analisa.contains(pek_proy.getString("id"))){


                    String id = pek_proy.getString("id");
                    String nama = pek_proy.getString("nama");
                    String satuan = pek_proy.getString("satuan");
                    String koef = pek_proy.getString("koef");
                    Double harga = 0.0;
                    harga =(double) Check.getFloat(id, a.getBaseContext(),(float) pek_proy.getDouble("hargasatuan"));

                    List<Double> ddd = new ArrayList<>();
                    String[] d =koef.split(",");
                    for (int e=0;e<d.length;e++){
                        ddd.add(Double.valueOf(d[e]));
                    }

                    AnalisaData analisaData = new AnalisaData(id,nama,satuan,ddd,harga);
                    _DATA.add(analisaData);
                }
            }
            return _DATA;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<AnalisaData> AnalisaDatas(Activity a){
        try {
            List<AnalisaData> _DATA =new ArrayList<>();
            JSONObject obj = new JSONObject(loadJSON(a));
            JSONArray m_jArry = obj.getJSONArray("data");
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject pek_proy = m_jArry.getJSONObject(i);
//                if(analisa.contains(pek_proy.getString("id"))){
                    String id = pek_proy.getString("id");
                    String nama = pek_proy.getString("nama");
                    String satuan = pek_proy.getString("satuan");
                String koef = pek_proy.getString("koef");
                Double harga = 0.0;
                harga =(double) Check.getFloat(id, a.getBaseContext(),(float) pek_proy.getDouble("hargasatuan"));

                List<Double> ddd = new ArrayList<>();
                String[] d =koef.split(",");
                for (int e=0;e<d.length;e++){
                    ddd.add(Double.valueOf(d[e]));
                }

                AnalisaData analisaData = new AnalisaData(id,nama,satuan,ddd,harga);

                    _DATA.add(analisaData);
               // }
            }
            return _DATA;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_analisa);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = findViewById(R.id.list_recycle_updt_anls);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DATA = new ArrayList<>();
        TransliteData(mykoefisien.analisa);
    }
    String TAG = "UPTOYU";
    List<AnalisaData> DATA;
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("analisadata.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    static String loadJSON(Activity a) {
        String json = null;
        try {
            InputStream is = a.getAssets().open("analisadata.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    void TransliteData(String analisa){
      //  try {
//            JSONObject obj = new JSONObject(loadJSONFromAsset());
//            JSONArray m_jArry = obj.getJSONArray("data");
//            for (int i = 0; i < m_jArry.length(); i++) {
//                JSONObject pek_proy = m_jArry.getJSONObject(i);
//                Log.d(TAG, "TransliteData: "+analisa+" "+pek_proy.getString("id"));
//                if(analisa.contains(pek_proy.getString("id"))){
//
//                    String id = pek_proy.getString("id");
//                    String nama = pek_proy.getString("nama");
//                    String satuan = pek_proy.getString("satuan");
//                    String koef = pek_proy.getString("koef");
//                    Double harga = 0.0;
//                    harga =(double) Check.getFloat(id, getApplicationContext(),(float) pek_proy.getDouble("hargasatuan"));
//                    Log.d("MASMAS",harga.toString()+""+id);
//                    List<Double> ddd = new ArrayList<>();
//                    String[] d =koef.split(",");
//                    for (int e=0;e<d.length;e++){
//                        ddd.add(Double.valueOf(d[e]));
//                    }
//
//                    AnalisaData analisaData = new AnalisaData(id,nama,satuan,ddd,harga);
//                    DATA.add(analisaData);
//                }
//            }
            DATA.addAll(mykoefisien.analisa2);
            LoadList();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    void LoadList(){

        RecycleAdapterKoefisienUpdate recycleAdapterKoefisienUpdate = new RecycleAdapterKoefisienUpdate(DATA,this);
        recyclerView.setAdapter(recycleAdapterKoefisienUpdate);
    }


    public static void Init(Activity activity, KoefisienPk koefisien){
        mykoefisien = koefisien;
        Intent i = new Intent(activity, UpdateAnalisaActivity.class);
        activity.startActivity(i);
    }
}
