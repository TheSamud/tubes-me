package com.pengembangsebelah.calculatorrab;

import android.os.Debug;
import android.util.Log;

import com.pengembangsebelah.calculatorrab.model.baru.AnalisaData;
import com.pengembangsebelah.calculatorrab.model.baru.KoefisienPk;
import com.pengembangsebelah.calculatorrab.model.baru.PekerjaanKoefisien;
import com.pengembangsebelah.calculatorrab.model.baru.PekerjaanProyek;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataKu {
    public static String TAG = "DATAKU DEBUG";
    public static List<PekerjaanProyek> data;

    public DataKu(String datajson){
        try {
            data = new ArrayList<>();
            JSONObject obj = new JSONObject(datajson);
            JSONArray m_jArry = obj.getJSONArray("data");

            Log.d(TAG, "Dataku: "+m_jArry.length());

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject pek_proy = m_jArry.getJSONObject(i);
                JSONArray pekKofarr = pek_proy.getJSONArray("pkp");

                List<PekerjaanKoefisien> pekerjaanKoefisiens = new ArrayList<>();

                for(int j=0;j<pekKofarr.length();j++){
                    JSONObject pek_arr = pekKofarr.getJSONObject(j);
                    JSONArray kofar = pek_arr.getJSONArray("ko");

                    List<KoefisienPk> koefisienPks = new ArrayList<>();
                    for (int k=0;k<kofar.length();k++){
                        JSONObject ko = kofar.getJSONObject(k);
                        KoefisienPk koefisienPk = new KoefisienPk(ko.getString("a"),ko.getString("id"));
                        //koefisienPk.TambahAnalisa(ko.getString("analisa"));
                        koefisienPk.TambahVolume(ko.getString("volume"));
                        JSONArray kofar2 = ko.getJSONArray("analisa2");

                        List<AnalisaData> analisaData = new ArrayList<>();
                        for (int ke=0;ke<kofar2.length();ke++){
                            JSONObject kofar22 = kofar2.getJSONObject(ke);
                            String id = kofar22.getString("id");
                            String nama = kofar22.getString("nama");
                            String satuan = kofar22.getString("satuan");
                            String koef = kofar22.getString("koef");
                            Double harga = kofar22.getDouble("hargasatuan");

                            List<Double> ddd = new ArrayList<>();
                            String[] d =koef.split(",");
                            for (int e=0;e<d.length;e++){
                                ddd.add(Double.valueOf(d[e]));
                            }

                            AnalisaData analisa2 = new AnalisaData(id,nama,satuan,ddd,harga);
                            analisaData.add(analisa2);

                        }

                        koefisienPk.TambahanAnalisa(analisaData);
                        koefisienPks.add(koefisienPk);
                    }

                    PekerjaanKoefisien pekerjaanKoefisien = new PekerjaanKoefisien(pek_arr.getString("title"),koefisienPks);

                    pekerjaanKoefisiens.add(pekerjaanKoefisien);
                }
                //get title pekpoy
                PekerjaanProyek pekerjaanProyek = new PekerjaanProyek(pek_proy.getString("title"),pekerjaanKoefisiens);
                Log.d("GUNA",pek_proy.getString("title")+"  "+m_jArry.length());
                data.add(pekerjaanProyek);
            }
        } catch (JSONException e) {
            Log.d("GUNA",e.getLocalizedMessage()+"/n"+e.getMessage());
            e.printStackTrace();
        }
    }
}
