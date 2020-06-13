package com.pengembangsebelah.calculatorrab.ui.adapte;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.pengembangsebelah.calculatorrab.R;
import com.pengembangsebelah.calculatorrab.model.baru.AnalisaData;
import com.pengembangsebelah.calculatorrab.myutils.Angka;
import com.pengembangsebelah.calculatorrab.myutils.Check;
import com.pengembangsebelah.calculatorrab.ui.fragment.HargaSatuanFragment;

import java.util.ArrayList;
import java.util.List;

public class RecycleVolumeEdt extends RecyclerView.Adapter<RecycleVolumeEdt.ViewHolder> {

    String LuasPembersihan = "#1#";
    String LuasPembersihan2 = "#2#";

    String LuasPekerjaanTanah = "#3#"; //pekerjaan
    String LuasPekerjaanTanah2 = "#4#";
    String LuasPekerjaanTanah3 = "#5#";
    String LuasPekerjaanTanah4 = "#55#";

    String LuasPekerjaanTanah5 = "#31#";
    String LuasPekerjaanTanah6 = "#32#";
    String LuasPekerjaanTanah7 = "#34#";
    String LuasPekerjaanTanah8 = "#35#";
    String LuasPekerjaanTanah81 = "#351#";
    String LuasPekerjaanTanah82 = "#352#";
    String LuasPekerjaanTanah83 = "#353#";

    String LuasPekerjaanTanah9 = "#36#";
    String LuasPekerjaanTanah10 = "#37#";
    String LuasPekerjaanTanah11 = "#38#";
    String LuasPekerjaanTanah12 = "#39#";
    String LuasPekerjaanTanah121 = "#391#";
    String LuasPekerjaanTanah122 = "#392#";

    String LuasPondasi2 = "#6#";
    String LuasPondasi3 = "#7#";

    String LuasBeton = "#8#";
    String LuasBeton88 = "#88#";
    String LuasBeton888 = "#888#";
    String LuasBeton2 = "#9#";
    String LuasBeton3 = "#10#";

    String LuasPeding = "#11#";
    String LuasPeding3 = "#113#";
    String LuasPeding2 = "#12#";

    String LuasPlester = "#13#";
    String LuasPlester2 = "#131#";

    String Lantai = "#14#";
    String Lantai2 = "#15#";
    String Lantai3 = "#16#";
    String Lantai4 = "#161#";

    String kayu = "#17#";
    String kayu2 = "#18#";

    String kunci = "#19#";
    String kunci3 = "#191#";
    String kunci2 = "#20#";

    String cat2 = "#212#";

    String cat = "#21#";

    String cat3 = "#222#";
    String cat4 = "#223#";

    String betonSpesial = "#2331#";
    String betonSpesial2 = "#2332#";
    String betonSpesial3 = "#2333#";
    String betonSpesial4 = "#2334#";

    float betonSpesialVolume = 1f;


    String Mycode= "";
    static String MycodeIndex= "";

    public List<Double> jumlahHargane;

    int banyakList=0;

    boolean CanChange = false;
    public List<Float> luasw;
    public List<Float> luaswa;
    public List<Float> luaswb;
    public List<Float> luaswc;
    public List<Float> luasw2;
    Button button;

    void AddKolom(){
        for(int y = 0;y<13;y++){
            luaswa.add(0f);
        }
    }

    public interface ListenerEdit{
        void OnChange(List<Float> volume);
    }
    List<Float> takan;

    float panjang=0,lebar=0,tinggi=0;
    ListenerEdit listenerEdit;
    Context context;
    public void SetSpesial(float val){
        betonSpesialVolume = val;
    }
    public RecycleVolumeEdt(String Code, ListenerEdit listenerEdit, Context context){
        this.listenerEdit = listenerEdit;
        jumlahHargane = new ArrayList<>();
        String[] ju = Code.split("%56%");

        this.Mycode = ju[0];
        this.MycodeIndex = ju[1];
        this.context = context;
        takan = new ArrayList<>();
        luasw = new ArrayList<>();
        luaswa = new ArrayList<>();
        luaswb = new ArrayList<>();
        luaswc = new ArrayList<>();
        luasw2 = new ArrayList<>();
        button = new Button(context);
        for (int d =0 ;d<Check.getInteger(MycodeIndex+"l",context,1);d++) {
            luasw.add(0f);
        }
        for (int d =0 ;d<Check.getInteger(MycodeIndex+"la",context,0);d++) {
            luaswa.add(0f);
        }
        for (int d =0 ;d<Check.getInteger(MycodeIndex+"l2",context,1);d++) {
            luasw2.add(0f);
        }
    }
    Float get(String gg){
        Log.d("SUMRINGAH", "get: "+gg);
        try {
            return Check.getFloat(gg,context,0f);
        }catch (ClassCastException f){
            Log.d("SUMRINGAH", "get: "+f.getMessage());
            return 0f;
        }

    }
    void HitungLuas(int index){
        panjang = get("ed1"+index+MycodeIndex);
        lebar = get("ed2"+index+MycodeIndex);
        float ha = panjang * lebar;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(index, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasP2(int index){
        panjang = get("ed1"+index+MycodeIndex);
        lebar = get("ed2"+index+MycodeIndex);
        float ha = (panjang + 2) + (lebar +2);
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(index, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasP22(int index){
        panjang = get("ed1"+index+MycodeIndex);
        float ha = panjang ;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(index, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasP0(int index){
        panjang = get("ed1"+index+MycodeIndex);
        float ha = panjang;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(index, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan(int index){
        panjang = get("ed1"+1+MycodeIndex);
        tinggi = get("ed2"+1+MycodeIndex);
        lebar = get("ed3"+1+MycodeIndex);

        float tpanjang = get("ed1"+0+MycodeIndex);
        float ha = ((panjang + tinggi) * (lebar/2))*tpanjang;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan2(int index){
        panjang = get("ed1"+index+MycodeIndex);
        float ha = panjang/3 ;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(index, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan3(int index){
        float tebal=0f;
        float totalluas=0f;
        for (int w=0;w<index;w++){
            if(w==0){
                tebal = get("ed1"+w+MycodeIndex);
            }else {
                float p = get("ed1"+w+MycodeIndex);
                float l = get("ed2"+w+MycodeIndex);
                float y = p*l;
                totalluas += y;
                //Log.d("KAMPRETO", w+"HitungLuasPekTan3: "+p+" "+l+" "+totalluas);
            }
        }
        float ha = totalluas * tebal ;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan33(int index){
        float tebal=0f;
        float totalluas=0f;
        for (int w=0;w<index;w++){
            if(w==0){
                tebal = get("ed1"+w+MycodeIndex);
            }else {
                float p = get("ed1"+w+MycodeIndex);
                float l = get("ed2"+w+MycodeIndex);
                float y = p*l;
                totalluas += y;
                Log.d("KAMPRETO", w+"HitungLuasPekTan3: "+p+" "+l+" "+totalluas);
            }
        }
        float ha = totalluas * tebal ;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan31(int index){
        float l = get("ed1"+0+MycodeIndex);
        float t = get("ed1"+0+MycodeIndex);
        float y = l*t*4;

        float j = get("ed1"+1+MycodeIndex);
        float ha = y * j ;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan4(int index){
        panjang = get("ed1"+1+MycodeIndex);
        tinggi = get("ed2"+1+MycodeIndex);

        float tpanjang = get("ed1"+0+MycodeIndex);
        float ha = (panjang + tinggi) * tpanjang ;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan5(int index){
        panjang = get("ed1"+0+MycodeIndex);
        lebar = get("ed2"+0+MycodeIndex);
        float lebarr = get("ed3"+0+MycodeIndex);
        float jum = get("ed1"+1+MycodeIndex);
        float ha = panjang * lebar * lebarr *jum;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(index, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan6(int inde){
        float totalluas = 0f;
        if(inde==1){
            for (int w = 0; w < luasw.size(); w++) {
                float p = get("ed1" + w + MycodeIndex);
                float l = get("ed2" + w + MycodeIndex);
                float y = p * l;
                totalluas += y;
            }
        }else if(inde==2){
            for (int w = 0; w < luasw.size(); w++) {
                float p = get("ed1" + w + MycodeIndex);
                float l = get("ed2" + w + MycodeIndex);
                float t = get("ed3" + w + MycodeIndex);
                float y = p * l * t ;
                totalluas += y;
            }
        }else {
            for (int w = 0; w < luasw.size(); w++) {
                float p = get("ed1" + w + MycodeIndex);
                float l = get("ed2" + w + MycodeIndex);
                float t = get("ed3" + w + MycodeIndex);
                float j = get("ed4" + w + MycodeIndex);
                float y = p * l * t * j;
                totalluas += y;
            }
        }
        float ha = totalluas;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan61(int inde){
        float totalluas = 0f;
        for (int w = 0; w < luasw.size(); w++) {
            float p = get("ed1" + w + MycodeIndex);
            float l = get("ed2" + w + MycodeIndex);
            float t = get("ed3" + w + MycodeIndex);
            float y = p * l * t ;
            totalluas += y;
        }
        float ha = totalluas;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan62(int inde){
        float totalluas = 0f;
        for (int w = 0; w < luasw.size(); w++) {
            float p = get("ed1" + w + MycodeIndex);
            float l = get("ed2" + w + MycodeIndex);
            float t = get("ed3" + w + MycodeIndex);
            float y = p * l * t ;
            totalluas += y;
        }
        float ha = totalluas;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan63(int inde){
        float totalluas = 0f;
        int a =0;
        for (int w = 0; w < luasw.size(); w++) {
            float p = get("ed1" + (w+a) + MycodeIndex);
            float l = get("ed2" + (w+a) + MycodeIndex);
            float y = p * l * 2;

            float p2 = get("ed1" + (w+1+a) + MycodeIndex);
            float l2 = get("ed2" + (w+1+a) + MycodeIndex);
            float p3 = get("ed3" + (w+1+a) + MycodeIndex);
            float y2 = p2*l2*p3;

            float y3 = y+y2;
            totalluas += y3;
            a++;
        }
        float ha = totalluas;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan631(int inde){
        float totalluas = 0f;
        int a =0;
        for (int w = 0; w < luasw.size(); w++) {
            float p = get("ed1" + (w+a) + MycodeIndex);
            float l = get("ed1" + (w+1+a) + MycodeIndex);
            float y = p * l;
            totalluas += y;
            a++;
        }
        float ha = totalluas;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan6312(int inde){
        float totalluas = 0f;
        int a =0;
        for (int w = 0; w < luasw.size(); w++) {
            float p = get("ed1" + (w+a) + MycodeIndex);
            float p2 = get("ed2" + (w+a) + MycodeIndex);
            float l = get("ed1" + (w+1+a) + MycodeIndex);
            float y = p * l *p2;
            totalluas += y;
            a++;
        }
        float ha = totalluas;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }

    void HitungLuasPekTan64(int inde){
        float totalluas = 0f;
        int a =0;
        for (int w = 0; w < luasw.size(); w++) {
            float p = get("ed1" + (w+a) + MycodeIndex);
            float l = get("ed2" + (w+a) + MycodeIndex);
            float l2 = get("ed3" + (w+a) + MycodeIndex);
            float l3= get("ed4" + (w+a) + MycodeIndex);
            float y = p * l;
            float na = l2+15;
            float tu = na*l3;
            float pa = y+tu;

            float p2 = get("ed1" + (w+1+a) + MycodeIndex);
            float y2 = pa*p2;
            totalluas += y2;
            a++;
        }
        float ha = totalluas;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan65(int inde){
        float totalluas = 0f;
        for (int w = 0; w < luasw.size(); w++) {
            float p = get("ed1" + w + MycodeIndex);
            float l = get("ed2" + w + MycodeIndex);
            float t = get("ed3" + w + MycodeIndex);
            float y = p * l * t * 2;
            totalluas += y;
        }
        float ha = totalluas;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan66(int inde){
        float totalluas = 0f;
        //  for (int w = 0; w < luasw.size(); w++) {
        float p = get("ed1" + 0 + MycodeIndex);
        float l = get("ed2" + 0 + MycodeIndex);

        float t = get("ed1" + 1 + MycodeIndex);
        float t2 = get("ed2" + 1 + MycodeIndex);

        float p3 = get("ed1" + 2 + MycodeIndex);
        float l3 = get("ed2" + 2 + MycodeIndex);
        float s3 = get("ed3" + 2 + MycodeIndex);

        float sat = p*l;
        float sat2 = t*t2*2;
        float sat3 = p3*l3*s3;

        float y = sat * sat2 * sat3;
        totalluas += y;
        //}
        float ha = totalluas;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan616(int inde){
        if(inde==1){
            float totalluas = 0f;
            int a=0;
            for (int w = 1; w <= luasw.size(); w++) {
                float p = get("ed1" + (w+a) + MycodeIndex);
                float l = get("ed2" + (w+a) + MycodeIndex);
                float t = get("ed3" + (w+a) + MycodeIndex);
                float y = ((p * l)/2) * t;

                float p2 = get("ed1" + (w+a+1) + MycodeIndex);
                float l2 = get("ed2" + (w+a+1) + MycodeIndex);
                float t2 = get("ed3" + (w+a+1) + MycodeIndex);
                float y2 = p2 * (l2/2) * t2;
                totalluas += y+y2;
                a++;
            }
            float ha = totalluas;
            if (takan.size() < 1) {
                takan.add(ha);
            } else {
                takan.set(0, ha);
            }
            listenerEdit.OnChange(takan);
        }else {
            float totalluas = 0f;
            for (int w = 1; w <= luasw.size(); w++) {
                float p = get("ed1" + w + MycodeIndex);
                float l = get("ed2" + w + MycodeIndex);
                float t = get("ed3" + w + MycodeIndex);
                float y = p * l * t;
                totalluas += y;
            }
            float ha = totalluas;
            if (takan.size() < 1) {
                takan.add(ha);
            } else {
                takan.set(0, ha);
            }
            listenerEdit.OnChange(takan);
        }
    }
    void HitungLuasPekTan6161(int inde){
        float totalluas = 0f;
        for (int w = 1; w <= luasw.size(); w++) {
            float p = get("ed1" + w + MycodeIndex);
            float l = get("ed2" + w + MycodeIndex);
            float t = get("ed3" + w + MycodeIndex);
            float y = p * l * t;
            totalluas += y;
        }
        float ha = totalluas;
        if (takan.size() < 1) {
            takan.add(ha);
        } else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan7(int a, int b, int c){
        float p = get("ed1"+a+MycodeIndex);
        float p2 = get("ed1"+b+MycodeIndex);
        float p3 = get("ed1"+c+MycodeIndex);
        float totalluas = p+p2+p3;
        float ha = totalluas;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        //listenerEdit.OnChange(takan);
    }

    void HitungLuasPekTan777(int a){
        float p=0;
        if(a==1||a==2) {
            p = get("ed1" + 13 + MycodeIndex);
        }else if(a==3) {
            p = get("ed1" + 6 + luasw.size() + MycodeIndex);
        }
        float totalluas = p;
        float ha = totalluas;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan8(){
        float lubut = get("ed1"+2+MycodeIndex);
        float ber = get("ed1"+0+MycodeIndex);

        float p = get("ed1"+1+MycodeIndex);
        float l = get("ed2"+1+MycodeIndex);
        float luas = p*l;

        float ha = (lubut/luas)*ber;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan9(){
        float volgun = (get("ed1" + 0 + MycodeIndex) * get("ed2" + 0 + MycodeIndex)/2) * get("ed3" + 0 + MycodeIndex);
        int y=1;
        float volpeng = 0f;

        for (int r=1;r<=luasw2.size();r++) {
            y++;
            float volpeng2 = get("ed1" + r + MycodeIndex) * get("ed2" + r + MycodeIndex) * get("ed3" + r + MycodeIndex);
            volpeng +=volpeng2;
        }

        float volada = 0f;
        for (int t = 0 ; t<luasw.size();t++){
            float vol = get("ed1" + (y+t) + MycodeIndex) * get("ed2" + (y+t) + MycodeIndex) * get("ed3" + (y+t) + MycodeIndex);
            volada += vol;
        }


        float ha = (volgun+volada)-volpeng;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan10(){
        float totalluas = 0f;
        for (int w=0;w<luasw.size();w++){
            float p = get("ed1"+w+MycodeIndex);
            float l = get("ed2"+w+MycodeIndex);
            float y = p*l;
            totalluas += y;
        }
        float ha = totalluas;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan11(){
        float volgun = get("ed1" + 0 + MycodeIndex) * get("ed2" + 0 + MycodeIndex) ;
        float volada = 0f;

        for (int t = 0 ; t<luasw.size();t++){
            float volq = get("ed1" + (1+t) + MycodeIndex) * get("ed2" + (1+t) + MycodeIndex) * 2;
            //    Log.d("KASDU", "HitungLuasPekTan11: "+volq);
            volada += volq;
        }
        //Log.d("KASDU", "HitungLuasPekTan11: "+luasw.size()+" "+volada+" "+get("ed1" + 1 + MycodeIndex));


        float ha = volada - volgun;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan12(){
        float volada = 0f;

        for (int t = 0 ; t<luasw.size();t++){
            float volq = get("ed1" + (t) + MycodeIndex) * get("ed2" + (t) + MycodeIndex);
            //    Log.d("KASDU", "HitungLuasPekTan11: "+volq);
            volada += volq;
        }
        float ha = volada;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuasPekTan122(){
        float volada = 0f;

        for (int t = 0 ; t<luasw.size();t++){
            float volq = get("ed1" + (t) + MycodeIndex) * get("ed2" + (t) + MycodeIndex);
            volada += volq;
        }

        float ha = volada;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }

    void HitungLuaskayu(){
        float vol4 = get("ed1" + (0) + MycodeIndex)*get("ed1" + (1) + MycodeIndex)*get("ed1" + (2) + MycodeIndex);
        save("ed1"+3+MycodeIndex,vol4);
        float vol8 = get("ed1" + (6) + MycodeIndex)*get("ed1" + (4) + MycodeIndex)*get("ed1" + (5) + MycodeIndex);
        save("ed1"+7+MycodeIndex,vol8);

        float yuk = 0f;

        for (int ui=0;ui<luasw.size();ui++){
            float vo4 = get("ed1" + (8*ui) + MycodeIndex)*get("ed1" + (9*ui) + MycodeIndex)*get("ed1" + (10*ui) + MycodeIndex);
            yuk+=vo4;
            save("ed1"+(11*ui)+MycodeIndex,vo4);
        }


        float ha = vol4+vol8+yuk;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
        notifyDataSetChanged();
    }
    void HitungLuaskayu2(){

        float yuk = 0f;

        for (int ui=0;ui<luasw.size();ui++){
            float vo4 = get("ed1" + (ui) + MycodeIndex)*get("ed2" + (ui) + MycodeIndex)*get("ed3" + (ui) + MycodeIndex);
            yuk+=vo4;
        }


        float ha = yuk;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }
    void HitungLuaskayu3(){

        float yuk = 0f;

        for (int ui=0;ui<luasw.size();ui++){
            float vo4 = get("ed1" + (ui) + MycodeIndex)*get("ed2" + (ui) + MycodeIndex);
            yuk+=vo4;
        }


        float ha = yuk;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }

    void HitungSpesialButton(int val){
        float volada = 0f;
        //begin
        if(val==1){

            float volq = get("ed1" + (0) + MycodeIndex) * betonSpesialVolume;
            volada += volq;
        }else if(val==2){
            float volq = get("ed1" + (0) + MycodeIndex) * betonSpesialVolume;
            volada += volq;
        }else if(val==4){
            float sapi=0f;
            for (int a=0;a<luasw.size();a++) {
                float sapiw = get("ed1" + (a) + MycodeIndex) * get("ed2" + (a) + MycodeIndex);
                sapi += sapiw;
            }
            float volq = sapi * betonSpesialVolume;
            volada += volq;
        }else if(val==3){
            float volq = get("ed1" + (0) + MycodeIndex) * get("ed2" + (0) + MycodeIndex) * betonSpesialVolume;
            volada += volq;
        }

        //end

        float ha = volada;
        if(takan.size()<1){
            takan.add(ha);
        }else {
            takan.set(0, ha);
        }
        listenerEdit.OnChange(takan);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_volume_edite, parent, false);
        RecycleVolumeEdt.ViewHolder viewHolder = new RecycleVolumeEdt.ViewHolder(listItem);
        return viewHolder;
    }
    void save(String gg,Float s){
        Check.saveFloat(gg,s,context);
    }
    void save(String gg,int s){
        Check.saveInteger(gg,s,context);
    }

    int addNow = 0;
    int addNow2 = 0;

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if(Mycode.equals(LuasPembersihan)){
            holder.add.setVisibility(View.GONE);
            holder.remov2.setVisibility(View.GONE);

            holder.plate.setVisibility(View.VISIBLE);
            holder.name_plate.setText("Luas "+(position+1));
            holder.ed1.setHint("Panjang");
            holder.ed2.setHint("Lebar");
            holder.ed3.setVisibility(View.GONE);
            holder.tv1.setText("Panjang");
            holder.tv2.setText("Lebar");
            holder.tv3.setVisibility(View.GONE);

            holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());
            holder.ed2.setText(get("ed2"+position+MycodeIndex).toString());
            holder.ed3.setText(get("ed3"+position+MycodeIndex).toString());

            holder.ed1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()&&CanChange){
                        save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                        HitungLuas(position);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            holder.ed2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()&&CanChange){
                        save("ed2"+position+MycodeIndex,Float.parseFloat(s.toString()));
                        HitungLuas(position);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            if((position+1)==banyakList){
                HitungLuas(position);
                CanChange = true;
            }
        }
        else if(Mycode.equals(LuasPembersihan2)){
            holder.add.setVisibility(View.GONE);
            holder.remov2.setVisibility(View.GONE);

            holder.plate.setVisibility(View.VISIBLE);
            holder.name_plate.setText("Total panjang Pondasi");
            holder.ed1.setHint("Horizontal");
            holder.ed2.setHint("Vertical");
            holder.ed3.setVisibility(View.GONE);
            holder.tv1.setText("Horizontal");
            holder.tv2.setText("Vertical");
            holder.tv3.setVisibility(View.GONE);

            holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());

            holder.ed1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()&&CanChange){
                        save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                        HitungLuasP2(position);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            holder.ed2.setText(get("ed2"+position+MycodeIndex).toString());

            holder.ed2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()&&CanChange){
                        save("ed2"+position+MycodeIndex,Float.parseFloat(s.toString()));
                        HitungLuasP2(position);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if((position+1)==banyakList){
                HitungLuasP2(position);
                CanChange = true;
            }
        }
        else if(Mycode.equals(LuasPekerjaanTanah)){
            if (position==0) {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Total Panjang");
                holder.ed1.setHint("Total Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Tinggi");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.GONE);
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Tebal");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Tinggi");

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan(1 + luasw.size());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume");
                holder.ed1.setHint("Lebar Atas");
                holder.ed2.setHint("Lebar Bawah");
                holder.ed3.setHint("Tinggi");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.tv1.setText("Lebar Atas");
                holder.tv2.setText("Lebar Bawah");
                holder.tv3.setText("Tinggi");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan(position);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan(position);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan(position);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            if(banyakList == position-1) {
                HitungLuasPekTan(position);
                CanChange = true;
            }
        }
        else if(Mycode.equals(LuasPekerjaanTanah2)){
            holder.add.setVisibility(View.GONE);
            holder.remov2.setVisibility(View.GONE);

            holder.plate.setVisibility(View.VISIBLE);
            holder.name_plate.setText("Volume "+(position+1));
            holder.ed1.setHint("Volume Tanah");
            holder.ed2.setHint("Lebar Bawah");
            holder.ed3.setHint("Tinggi");

            holder.ed1.setVisibility(View.VISIBLE);
            holder.ed2.setVisibility(View.GONE);
            holder.ed3.setVisibility(View.GONE);
            holder.tv1.setText("Volume Tanah");
            holder.tv2.setText("Lebar Bawah");
            holder.tv3.setText("Tinggi");

            holder.tv1.setVisibility(View.VISIBLE);
            holder.tv2.setVisibility(View.GONE);
            holder.tv3.setVisibility(View.GONE);

            holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());
            holder.ed1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()&&CanChange){
                        save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                        HitungLuasPekTan2(position);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if(banyakList==position-1) {
                HitungLuasPekTan2(position);
                CanChange = true;
            }

        }
        else if(Mycode.equals(LuasPekerjaanTanah3)){
            if (position==0) {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Tebal");
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Tinggi");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.GONE);
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Tebal");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Tinggi");

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan3(1 + luasw.size());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                //HitungLuasPekTan3(1 + luasw.size());
            }
            else if (position==1) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff(0f,addNow);
                    }
                });
                holder.add.setText("Tambah Luas");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Luas " + (position));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Tinggi");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Tinggi");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan3(1 + luasw.size());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan3(1 + luasw.size());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position-1,true,luasw.size());
                        HitungLuasPekTan3(1 + luasw.size());
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Luas " + position);
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Tinggi");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Tinggi");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan3(1 + luasw.size());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan3(1 + luasw.size());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

            }
            if(banyakList-1 == position){
                HitungLuasPekTan3(1 + luasw.size());
                CanChange = true;
            }
        }
        else if(Mycode.equals(LuasPekerjaanTanah4)){
            if (position==0) {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Tebal");
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Tinggi");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.GONE);
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Tebal");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Tinggi");

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan3(1 + luasw.size());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else if (position==1) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff(0f,addNow);
                    }
                });
                holder.add.setText("Tambah Luas");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Luas " + (position));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Tinggi");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Tinggi");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan33(1 + luasw.size());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan33(1 + luasw.size());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position-1,true,luasw.size());
                        HitungLuasPekTan33(1 + luasw.size());
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Luas " + position);
                holder.ed1.setHint("Tebal");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Tinggi");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Tebal");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Tinggi");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan33(1 + luasw.size());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan33(1 + luasw.size());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            if(banyakList == position+1){
                CanChange = true;
                HitungLuasPekTan3(1 + luasw.size());
            }
        }
        else if(Mycode.equals(LuasPekerjaanTanah5)){
            if (position==0) {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Luas");
                holder.ed1.setHint("Tebal");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Tinggi");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Tebal");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Tinggi");

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan31(1 + luasw.size());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.add.setText("Tambah Luas");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Jumlah Pondasi");
                holder.ed1.setHint("Jumlah Pondasi");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Tinggi");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.GONE);
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Jumlah Pondasi");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Tinggi");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan31(1 + luasw.size());
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan31(1 + luasw.size());
            }
        }
        else if(Mycode.equals(LuasPekerjaanTanah6)){
            if (position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff(0f,addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah Bekesting");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah bekesting");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                        HitungLuasPekTan61(0);
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah Bekesting");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah Bekesting");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            if(position == banyakList-1){
                CanChange = true;
                HitungLuasPekTan61(0);
            }
        }
        else if(Mycode.equals(LuasPekerjaanTanah7)){
            if (position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff(0f,addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Lebar");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Lebar");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan62(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan62(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan62(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                        HitungLuasPekTan61(0);
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Lebar");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Lebar");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan62(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan62(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan62(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan62(0);
            }
            if(banyakList-1==position){
                CanChange = true;
                HitungLuasPekTan61(0);
            }
        }
        else if(Mycode.equals(LuasPekerjaanTanah8)){
            if (position==0) {
                holder.title.setText("Luas 1");
                holder.title.setVisibility(View.VISIBLE);
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuas8(0f, addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position + 1));
                holder.name_plate.setVisibility(View.GONE);
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan63(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan63(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan63(0);
            }
            else {
                if((position+1)%2!=0) {
                    holder.title.setText("Luas "+((position/2)+1));
                    holder.title.setVisibility(View.VISIBLE);
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.VISIBLE);
                    holder.remov2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RemoveLuas8(position,luasw.size());
                            HitungLuasPekTan61(0);
                        }
                    });

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setVisibility(View.GONE);
                    holder.ed1.setHint("Panjang");
                    holder.ed2.setHint("Tinggi");
                    holder.ed3.setHint("Jumlah");
                    holder.ed4.setHint("Jumlah");

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.VISIBLE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Tinggi");
                    holder.tv3.setText("Jumlah");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.VISIBLE);
                    holder.tv2.setVisibility(View.VISIBLE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                    holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                    holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                    holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan63(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan63(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
                else{
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);
                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setVisibility(View.GONE);
                    holder.ed1.setHint("Panjang");
                    holder.ed2.setHint("Lebar");
                    holder.ed3.setHint("Jumlah");
                    holder.ed4.setHint("Jumlah");

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.VISIBLE);
                    holder.ed3.setVisibility(View.VISIBLE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Jumlah");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.VISIBLE);
                    holder.tv2.setVisibility(View.VISIBLE);
                    holder.tv3.setVisibility(View.VISIBLE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                    holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                    holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                    holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan63(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan63(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed3.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan63(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan63(0);
            }
        }
        else if(Mycode.equals(LuasPekerjaanTanah81)){
            if (position==0) {
                holder.title.setText("Kayu 1");
                holder.title.setVisibility(View.VISIBLE);
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuas8(0f, addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Luas Penampang Kayu");
                holder.name_plate.setVisibility(View.GONE);
                holder.ed1.setHint("Luas Penampang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.GONE);
                holder.ed3.setVisibility(View.GONE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Luas Penampang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan631(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan631(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else {
                if((position+1)%2!=0) {
                    holder.title.setText("Kayu "+((position/2)+1));
                    holder.title.setVisibility(View.VISIBLE);
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.VISIBLE);
                    holder.remov2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RemoveLuas8(position,luasw.size());
                            HitungLuasPekTan631(0);
                        }
                    });

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText("Luas Penampang Kayu");
                    holder.name_plate.setVisibility(View.GONE);
                    holder.ed1.setHint("Luas Penampang");
                    holder.ed2.setHint("Tinggi");
                    holder.ed3.setHint("Jumlah");
                    holder.ed4.setHint("Jumlah");

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Luas Penampang");
                    holder.tv2.setText("Tinggi");
                    holder.tv3.setText("Jumlah");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.VISIBLE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                    holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                    holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                    holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan631(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan631(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    HitungLuasPekTan631(0);
                }
                else{
                    holder.title.setVisibility(View.GONE);
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);
                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setVisibility(View.GONE);
                    holder.ed1.setHint("Panjang");
                    holder.ed2.setHint("Lebar");
                    holder.ed3.setHint("Jumlah");
                    holder.ed4.setHint("Jumlah");

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Jumlah");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.VISIBLE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                    holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                    holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                    holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan631(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan631(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed3.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan631(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan631(0);
            }
        }
        else if(Mycode.equals(LuasPekerjaanTanah82)){
            if (position==0) {
                if(position==0) {
                    holder.title.setText("Luas");
                    holder.title.setVisibility(View.VISIBLE);
                    holder.add.setVisibility(View.GONE);
                    holder.add.setText("Tambah Volume");
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText("Luas Penampang Kayu");
                    holder.name_plate.setVisibility(View.GONE);
                    holder.ed1.setHint("Luas Penampang");
                    holder.ed2.setHint("Tinggi");
                    holder.ed3.setHint("Jumlah");
                    holder.ed4.setHint("Jumlah");

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Luas Penampang");
                    holder.tv2.setText("Tinggi");
                    holder.tv3.setText("Jumlah");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.VISIBLE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                    holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan631(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan631(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    HitungLuasPekTan631(0);
                }
            }
            else {
                if((position+1)%2!=0) {
                    holder.title.setText("Luas "+((position/2)+1));
                    holder.title.setVisibility(View.VISIBLE);
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.VISIBLE);
                    holder.remov2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RemoveLuas8(position,luasw.size());
                            HitungLuasPekTan631(0);
                        }
                    });

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText("Luas Penampang Kayu");
                    holder.name_plate.setVisibility(View.GONE);
                    holder.ed1.setHint("Luas Penampang");
                    holder.ed2.setHint("Tinggi");
                    holder.ed3.setHint("Jumlah");
                    holder.ed4.setHint("Jumlah");

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Luas Penampang");
                    holder.tv2.setText("Tinggi");
                    holder.tv3.setText("Jumlah");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.VISIBLE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                    holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                    holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                    holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan631(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan631(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
                else{
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);
                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setVisibility(View.GONE);
                    holder.ed1.setHint("Panjang");
                    holder.ed2.setHint("Lebar");
                    holder.ed3.setHint("Jumlah");
                    holder.ed4.setHint("Jumlah");

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Jumlah");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.VISIBLE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                    holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                    holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                    holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan631(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan631(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed3.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan631(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan631(0);
            }
        }
        else if(Mycode.equals(LuasPekerjaanTanah9)){

            if (position==0) {
                if(position==0) {
                    holder.title.setText("Luas 1");
                    holder.title.setVisibility(View.VISIBLE);
                    holder.add.setVisibility(View.VISIBLE);
                    holder.add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AddLuas8(0f, addNow);
                        }
                    });
                    holder.add.setText("Tambah Volume");
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText("Volume " + (position + 1));
                    holder.name_plate.setVisibility(View.GONE);
                    holder.ed1.setHint("Panjang");
                    holder.ed2.setHint("Lebar");
                    holder.ed3.setHint("Tebal Plat");
                    holder.ed4.setHint("Keliling Lantai");

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.VISIBLE);
                    holder.ed3.setVisibility(View.VISIBLE);
                    holder.ed4.setVisibility(View.VISIBLE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tebal Plat");
                    holder.tv4.setText("Keliling Lantai");

                    holder.tv1.setVisibility(View.VISIBLE);
                    holder.tv2.setVisibility(View.VISIBLE);
                    holder.tv3.setVisibility(View.VISIBLE);
                    holder.tv4.setVisibility(View.VISIBLE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                    holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                    holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                    holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan64(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan64(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed3.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan64(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed4.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan64(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            }
            else {
                if((position+1)%2!=0) {
                    holder.title.setText("Luas "+((position/3)+1));
                    holder.title.setVisibility(View.VISIBLE);
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.VISIBLE);
                    holder.remov2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RemoveLuas8(position,luasw.size());
                            HitungLuasPekTan64(0);
                        }
                    });

                    holder.name_plate.setVisibility(View.GONE);
                    holder.ed1.setHint("Panjang");
                    holder.ed2.setHint("Lebar");
                    holder.ed3.setHint("Tebal Plat");
                    holder.ed4.setHint("Keliling Lantai");

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.VISIBLE);
                    holder.ed3.setVisibility(View.VISIBLE);
                    holder.ed4.setVisibility(View.VISIBLE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tebal Plat");
                    holder.tv4.setText("Keliling Lantai");

                    holder.tv1.setVisibility(View.VISIBLE);
                    holder.tv2.setVisibility(View.VISIBLE);
                    holder.tv3.setVisibility(View.VISIBLE);
                    holder.tv4.setVisibility(View.VISIBLE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                    holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                    holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                    holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan64(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan64(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed3.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan64(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed4.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan64(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
                else{
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);
                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setVisibility(View.GONE);
                    holder.ed1.setHint("Jumlah");
                    holder.ed2.setHint("Lebar");
                    holder.ed3.setHint("Jumlah");
                    holder.ed4.setHint("Jumlah");

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Jumlah");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.VISIBLE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                    holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                    holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                    holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan64(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan64(0);;
            }
        }
        else if(Mycode.equals(LuasPekerjaanTanah10)){

            if (position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff(0f,addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan65(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan65(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan65(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan65(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan65(0);
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,banyakList);
                        HitungLuasPekTan61(0);
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan65(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan65(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan65(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan65(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan65(0);;
            }
        }
        else if(Mycode.equals(LuasPekerjaanTanah11)) {
            if (position == 0) {
                holder.add.setVisibility(View.GONE);
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position + 1));
                holder.ed1.setHint("Lebar Tangga");
                holder.ed2.setHint("Panjang tangga miring");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Lebar Tangga");
                holder.tv2.setText("Panjang tangga miring");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan66(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan66(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan66(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan66(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else if (position == 1) {
                holder.add.setVisibility(View.GONE);
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position + 1));
                holder.ed1.setHint("Tebal Plat tangga");
                holder.ed2.setHint("Panjang Tangga Miring");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Tebal Plat tangga");
                holder.tv2.setText("Panjang Tangga Miring");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan66(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan66(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan66(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan66(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position + 1));
                holder.ed1.setHint("Lebar Tangga");
                holder.ed2.setHint("Tinggi Anak Tangga");
                holder.ed3.setHint("Jumlah anak tangga");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Lebar Tangga");
                holder.tv2.setText("Tinggi Anak Tangga");
                holder.tv3.setText("Jumlah anak tangga");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan66(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan66(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan66(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan66(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan66(0);
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan66(0);
            }

        }
        else if(Mycode.equals(LuasPekerjaanTanah12)){

            if (position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff(0f,addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan61(0);
            }
        }
        else if(Mycode.equals(LuasPekerjaanTanah121)){

            if (position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff(0f,addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan61(0);
            }
        }
        else if(Mycode.equals(LuasPekerjaanTanah122)){

            if (position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff(0f,addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Tinggi");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Tinggi");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan61(0);
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                        HitungLuasPekTan61(0);
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Tinggi");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Tinggi");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan61(0);
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan61(0);
            }
        }
        else if(Mycode.equals(LuasPekerjaanTanah83)){

            if (position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff(0f,addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Baja " + (position+1));
                holder.ed1.setHint("Keliling Penampang Besi");
                holder.ed2.setHint("Panjang Besi");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Keliling Penampang Besi");
                holder.tv2.setText("Panjang Besi");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan61(0);
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                        HitungLuasPekTan61(0);
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Baja " + (position+1));
                holder.ed1.setHint("Keliling Penampang Besi");
                holder.ed2.setHint("Panjang Besi");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Keliling Penampang Besi");
                holder.tv2.setText("Panjang Besi");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan61(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan61(0);
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan61(0);
            }
        }

        else if(Mycode.equals(LuasPondasi2)){
            if (position==0) {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Total Panjang");
                holder.ed1.setHint("Total Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Tinggi");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.GONE);
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Tebal");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Tinggi");

                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan4(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
//                holder.ed2.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//                        if (!s.toString().isEmpty()&&CanChange) {
//                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
//                            HitungLuasPekTan3(1 + luasw.size());
//                        }
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//
//                    }
//                });
//            holder.ed3.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    if(!s.toString().isEmpty()&&CanChange){
//                        save("ed3"+position+MycodeIndex,Float.parseFloat(s.toString()));
//                        HitungLuasPekTan(position);
//                    }
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//                }
//            });
                //HitungLuasPekTan3(1 + luasw.size());
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume");
                holder.ed1.setHint("Lebar");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Tinggi");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Lebar");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Tinggi");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan4(position);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan4(position);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan4(position);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan4(position);
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan4(position);
            }
        }
        else if(Mycode.equals(LuasPondasi3)){
            if(position==0) {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume");
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Tinggi");
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Tinggi");

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan5(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan5(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan5(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                //HitungLuasPekTan5(0);
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Jumlah");
                holder.ed1.setHint("Panjang");
                holder.ed2.setVisibility(View.GONE);
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());


                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan5(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan5(position);
            }
        }
        else if(Mycode.equals(LuasBeton)){

            if (position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff(0f,addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Tinggi");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.VISIBLE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Tinggi");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.VISIBLE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan6(0);
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                        HitungLuasPekTan6(0);
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Tinggi");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.VISIBLE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Tinggi");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.VISIBLE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan6(0);
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan6(0);
            }
        }
        else if(Mycode.equals(LuasBeton88)){

            if (position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff(0f,addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Tinggi");
                holder.ed2.setHint("Jumlah Kolom");
                holder.ed3.setHint("Tinggi");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Tinggi");
                holder.tv2.setText("Jumlah Kolom");
                holder.tv3.setText("Tinggi");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(1);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(1);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan6(1);
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                        HitungLuasPekTan6(0);
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Tinggi");
                holder.ed2.setHint("Jumlah Kolom");
                holder.ed3.setHint("Tinggi");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Tinggi");
                holder.tv2.setText("Jumlah Kolom");
                holder.tv3.setText("Tinggi");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(1);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(1);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan6(1);
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan6(0);
            }
        }
        else if(Mycode.equals(LuasBeton888)){

            if (position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuas(0f,addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Jumlah Balok");
                holder.ed3.setHint("Tinggi");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Jumlah Balok");
                holder.tv3.setText("Tinggi");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(1);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(1);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan6(1);
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                        HitungLuasPekTan6(0);
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Jumlah Balok");
                holder.ed3.setHint("Tinggi");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Jumlah Balok");
                holder.tv3.setText("Tinggi");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(1);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(1);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan6(1);
            }
            if(banyakList-1==position){
                CanChange=true;
                HitungLuasPekTan6(0);
            }
        }
        else if(Mycode.equals(LuasBeton2)){
            if (position==0) {
                holder.plate.setVisibility(View.GONE);
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setText("Kolom");
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Check.saveInteger(MycodeIndex+"juni",1,context);
                        notifyDataSetChanged();
                    }
                });
                holder.add2.setVisibility(View.VISIBLE);
                holder.add2.setText("Balok");
                holder.add2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Check.saveInteger(MycodeIndex+"juni",2,context);
                        notifyDataSetChanged();
                    }
                });
                holder.add3.setVisibility(View.VISIBLE);
                holder.add3.setText("Pelat");
                holder.add3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Check.saveInteger(MycodeIndex+"juni",3,context);
                        notifyDataSetChanged();
                    }
                });


            }
            final int lastkol = 13;
            final int lastkol2 = lastkol+13;

            int gi = Check.getInteger(MycodeIndex+"juni",context,0);
            if(gi==1) {
                if (position == 1) {
                    String tit = "Jumlah Besi";
                    holder.title.setVisibility(View.VISIBLE);
                    holder.title.setText("Kolom");
                    holder.add.setVisibility(View.GONE);
                    holder.add2.setVisibility(View.GONE);
                    holder.add3.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            //notifyDataSetChanged();
                        }
                    });
                }
                else if (position == 2) {
                    String tit = "Jumlah Sengkang";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            //notifyDataSetChanged();
                        }
                    });
                }
                else if (position == 3) {
                    String tit = "Panjang Besi";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            //notifyDataSetChanged();
                        }
                    });
                }
                else if (position == 4) {
                    String tit = "Panjang Begel";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            //notifyDataSetChanged();
                        }
                    });
                }
                else if (position == 5) {
                    String tit = "Diameter Besi Utama";
                    String[] isi = {
                            "12", "13", "16", "19", "22"
                    };
                    final ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_spinner_item, isi);
                    holder.sp5.setVisibility(View.VISIBLE);
                    holder.sp5.setAdapter(adapter);
                    holder.sp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int positionw, long id) {
                            save("ed1" + position + MycodeIndex, positionw);
                            // notifyDataSetChanged();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            parent.setSelection(Check.getInteger("s1ed1" + position + MycodeIndex, context, 0));
                        }
                    });
                    holder.sp5.setSelection(Check.getInteger("s1ed1" + position + MycodeIndex, context, 0));

                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                }
                else if (position == 6) {
                    String tit = "Diameter Besi Sengkang";
                    String[] isi = {
                            "8", "9", "10", "12"
                    };
                    final ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_spinner_item, isi);
                    holder.sp5.setVisibility(View.VISIBLE);
                    holder.sp5.setAdapter(adapter);
                    holder.sp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int positionw, long id) {
                            save("ed1" + position + MycodeIndex, positionw);
                            //notifyDataSetChanged();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            parent.setSelection(Check.getInteger("s1ed1" + position + MycodeIndex, context, 0));
                        }
                    });
                    holder.sp5.setSelection(Check.getInteger("s1ed1" + position + MycodeIndex, context, 0));

                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);
                }
                else if (position == 7) {
                    String tit = "Bobot Utama";
                    final List<Float> isi = new ArrayList<>();
                    isi.add(0.89f);
                    isi.add(1.04f);
                    isi.add(1.58f);
                    isi.add(2.23f);
                    isi.add(2.98f);

                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv3.setText("Hitung Kembali " + tit);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            float bp = isi.get(Check.getInteger("sled1" + 4 + MycodeIndex, context, 0));
                            float tui = bp * get("ed1" + 0 + MycodeIndex) * get("sled1" + 2 + MycodeIndex);
                            holder.tv1.setText(String.valueOf(tui));
                            save("ed1" + position + MycodeIndex, tui);
                        }
                    });

                    float bp = isi.get(Check.getInteger("sled1" + 4 + MycodeIndex, context, 0));
                    float tui = bp * get("ed1" + 0 + MycodeIndex) * get("sled1" + 2 + MycodeIndex);
                    holder.tv1.setText(String.valueOf(tui));
                    save("ed1" + position + MycodeIndex, tui);
                }
                else if (position == 8) {
                    String tit = "Bobot Sengkang";
                    final List<Float> isi = new ArrayList<>();
                    isi.add(0.395f);
                    isi.add(0.5f);
                    isi.add(0.617f);
                    isi.add(0.888f);

                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);


                    float bp = isi.get(Check.getInteger("sled1" + 5 + MycodeIndex, context, 0));
                    float tui = bp * get("ed1" + 1 + MycodeIndex) * get("sled1" + 3 + MycodeIndex);
                    holder.tv1.setText(String.valueOf(tui));
                    save("ed1" + position + MycodeIndex, tui);

                    holder.tv3.setText("Hitung Kembali " + tit);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            float bp = isi.get(Check.getInteger("sled1" + 5 + MycodeIndex, context, 0));
                            float tui = bp * get("ed1" + 1 + MycodeIndex) * get("sled1" + 3 + MycodeIndex);
                            holder.tv1.setText(String.valueOf(tui));
                            save("ed1" + position + MycodeIndex, tui);
                        }
                    });
                }
                else if (position == 9) {
                    String tit = "Kolom 1 per 1 m";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    float tui = get("ed1" + 6 + MycodeIndex) + get("ed1" + 7 + MycodeIndex);
                    holder.tv1.setText(String.valueOf(tui));
                    save("ed1" + position + MycodeIndex, tui);

                    holder.tv3.setText("Hitung Kembali " + tit);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            float tui = get("ed1" + 6 + MycodeIndex) + get("ed1" + 7 + MycodeIndex);
                            holder.tv1.setText(String.valueOf(tui));
                            save("ed1" + position + MycodeIndex, tui);
                        }
                    });
                }
                else if (position == 10) {
                    String tit = "Panjang Kolom";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            // notifyDataSetChanged();
                        }
                    });
                }
                else if (position == 11) {
                    String tit = "Jumlah kolom";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            //notifyDataSetChanged();
                        }
                    });
                }
                else if (position == 12) {
                    String tit = "Total Panjang Kolom";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    float tui = get("ed1" + 9 + MycodeIndex) * get("ed1" + 10 + MycodeIndex);
                    holder.tv1.setText(String.valueOf(tui));
                    save("ed1" + position + MycodeIndex, tui);

                    holder.tv3.setText("Hitung Kembali " + tit);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            float tui = get("ed1" + 9 + MycodeIndex) * get("ed1" + 10 + MycodeIndex);
                            holder.tv1.setText(String.valueOf(tui));
                            save("ed1" + position + MycodeIndex, tui);
                        }
                    });
                }
                else if (position == 13) {
                    String tit = "Volume";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    float tui = get("ed1" + 8 + MycodeIndex) * get("ed1" + 11 + MycodeIndex);
                    holder.tv1.setText(String.valueOf(tui));
                    save("ed1" + position + MycodeIndex, tui);

                    holder.tv3.setText("Hitung Kembali " + tit);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            float tui = get("ed1" + 8 + MycodeIndex) * get("ed1" + 11 + MycodeIndex);
                            holder.tv1.setText(String.valueOf(tui));
                            save("ed1" + position + MycodeIndex, tui);
                        }
                    });
                }
                HitungLuasPekTan777(1);
            }
            else if(gi==2){
                if (position==1) {
                    String tit = "Jumlah Besi";
                    holder.title.setVisibility(View.VISIBLE);
                    holder.title.setText("Balok");
                    holder.add.setVisibility(View.GONE);
                    holder.add2.setVisibility(View.GONE);
                    holder.add3.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            //notifyDataSetChanged();
                        }
                    });
                }
                else if (position==2) {
                    String tit = "Jumlah Sengkang";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            //notifyDataSetChanged();
                        }
                    });
                }
                else if (position==3) {
                    String tit = "Panjang Besi";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            //notifyDataSetChanged();
                        }
                    });
                }
                else if (position==4) {
                    String tit = "Panjang Begel";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            //notifyDataSetChanged();
                        }
                    });
                }
                else if (position==5) {
                    String tit = "Diameter Besi Utama";
                    String[] isi = {
                            "12","13","16","19","22"
                    };
                    final ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_spinner_item, isi);
                    holder.sp5.setVisibility(View.VISIBLE);
                    holder.sp5.setAdapter(adapter);
                    holder.sp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int positionw, long id) {
                            save("ed1" + position + MycodeIndex, positionw);
                            // notifyDataSetChanged();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            parent.setSelection(Check.getInteger("s2ed1" + position + MycodeIndex, context,0));
                        }
                    });
                    holder.sp5.setSelection(Check.getInteger("s2ed1" + position + MycodeIndex, context,0));

                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                }
                else if (position==6) {
                    String tit = "Diameter Besi Sengkang";
                    String[] isi = {
                            "8","9","10","12"
                    };
                    final ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_spinner_item, isi);
                    holder.sp5.setVisibility(View.VISIBLE);
                    holder.sp5.setAdapter(adapter);
                    holder.sp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int positionw, long id) {
                            save("ed1" + position + MycodeIndex, positionw);
                            //notifyDataSetChanged();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            parent.setSelection(Check.getInteger("s2ed1" + position + MycodeIndex, context,0));
                        }
                    });
                    holder.sp5.setSelection(Check.getInteger("s2ed1" + position + MycodeIndex, context,0));

                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);
                }
                else if (position==7) {
                    String tit = "Bobot Utama";
                    final List<Float> isi = new ArrayList<>();
                    isi.add(0.89f);
                    isi.add(1.04f);
                    isi.add(1.58f);
                    isi.add(2.23f);
                    isi.add(2.98f);

                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv3.setText("Hitung Kembali "+tit);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            float bp = isi.get(Check.getInteger("ed1" + (lastkol+4) + MycodeIndex, context,0));
                            float tui = bp * get("ed1" + (lastkol+0) + MycodeIndex) * get("ed1" + (lastkol+2) + MycodeIndex);
                            holder.tv1.setText(String.valueOf(tui));
                            save("ed1" + position + MycodeIndex, tui);
                        }
                    });

                    float bp = isi.get(Check.getInteger("ed1" + (lastkol+4) + MycodeIndex, context,0));
                    float tui = bp * get("ed1" + (lastkol+0) + MycodeIndex) * get("ed1" + (lastkol+2) + MycodeIndex);
                    holder.tv1.setText(String.valueOf(tui));
                    save("ed1" + position + MycodeIndex, tui);
                }
                else if (position==8) {
                    String tit = "Bobot Sengkang";
                    final List<Float> isi = new ArrayList<>();
                    isi.add(0.395f);
                    isi.add(0.5f);
                    isi.add(0.617f);
                    isi.add(0.888f);

                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    float bp = isi.get(Check.getInteger("ed1" + (lastkol+5) + MycodeIndex, context,0));
                    float tui = bp * get("ed1" + (lastkol+1) + MycodeIndex) * get("ed1" + (lastkol+3) + MycodeIndex);
                    holder.tv1.setText(String.valueOf(tui));
                    save("ed1" + position + MycodeIndex, tui);

                    holder.tv3.setText("Hitung Kembali "+tit);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            float bp = isi.get(Check.getInteger("ed1" + (lastkol+5) + MycodeIndex, context,0));
                            float tui = bp * get("ed1" + (lastkol+1) + MycodeIndex) * get("ed1" + (lastkol+3) + MycodeIndex);
                            holder.tv1.setText(String.valueOf(tui));
                            save("ed1" + position + MycodeIndex, tui);
                        }
                    });
                }
                else if (position==9) {
                    String tit = "Kolom 1 per 1 m";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    float tui = get("ed1" + (lastkol+6) + MycodeIndex) + get("ed1" + (lastkol+7) + MycodeIndex);
                    holder.tv1.setText(String.valueOf(tui));
                    save("ed1" + position + MycodeIndex, tui);

                    holder.tv3.setText("Hitung Kembali "+tit);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            float tui = get("ed1" + (lastkol+6) + MycodeIndex) + get("ed1" + (lastkol+7) + MycodeIndex);
                            holder.tv1.setText(String.valueOf(tui));
                            save("ed1" + position + MycodeIndex, tui);
                        }
                    });
                }
                else if (position==10) {
                    String tit = "Panjang Kolom";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            // notifyDataSetChanged();
                        }
                    });
                }
                else if (position==11) {
                    String tit = "Jumlah kolom";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            //notifyDataSetChanged();
                        }
                    });
                }
                else if (position==12) {
                    String tit = "Total Panjang Kolom";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    float tui = get("ed1" + (lastkol+9) + MycodeIndex) * get("ed1" + (lastkol+10) + MycodeIndex);
                    holder.tv1.setText(String.valueOf(tui));
                    save("ed1" + position + MycodeIndex, tui);

                    holder.tv3.setText("Hitung Kembali "+tit);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            float tui = get("ed1" + (lastkol+9) + MycodeIndex) * get("ed1" + (lastkol+10) + MycodeIndex);
                            holder.tv1.setText(String.valueOf(tui));
                            save("ed1" + position + MycodeIndex, tui);
                        }
                    });
                }
                else if (position==13) {
                    String tit = "Volume";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    float tui = get("ed1" + (lastkol+8) + MycodeIndex) * get("ed1" + (lastkol+11) + MycodeIndex);
                    holder.tv1.setText(String.valueOf(tui));
                    save("ed1" + position + MycodeIndex, tui);

                    holder.tv3.setText("Hitung Kembali "+tit);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            float tui = get("ed1" + (lastkol+8) + MycodeIndex) * get("ed1" + (lastkol+11) + MycodeIndex);
                            holder.tv1.setText(String.valueOf(tui));
                            save("ed1" + position + MycodeIndex, tui);
                        }
                    });
                }
                HitungLuasPekTan777(2);
            }
            else if(gi==3){
                if (position==1) {
                    holder.title.setVisibility(View.VISIBLE);
                    holder.title.setText("Pelat");

                    String tit = "Tulangan Bawah Vertikal";
                    String[] isi = {
                            "8","9","10","12"
                    };
                    final ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_spinner_item, isi);
                    holder.sp5.setVisibility(View.VISIBLE);
                    holder.sp5.setAdapter(adapter);
                    holder.sp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int positionw, long id) {
                            save("ed1" + position + MycodeIndex, positionw);
                            // notifyDataSetChanged();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            parent.setSelection(Check.getInteger("s3ed1" + position + MycodeIndex, context,0));
                        }
                    });
                    holder.sp5.setSelection(Check.getInteger("s3ed1" + position + MycodeIndex, context,0));

                    holder.add.setVisibility(View.GONE);
                    holder.add2.setVisibility(View.GONE);
                    holder.add3.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);
                }
                else if (position==2) {

                    String tit = "Tulangan Atas Vertikal";
                    String[] isi = {
                            "8","9","10","12"
                    };
                    final ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_spinner_item, isi);
                    holder.sp5.setVisibility(View.VISIBLE);
                    holder.sp5.setAdapter(adapter);
                    holder.sp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int positionw, long id) {
                            save("ed1" + position + MycodeIndex, positionw);
                            // notifyDataSetChanged();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            parent.setSelection(Check.getInteger("ed1" + position + MycodeIndex, context,0));
                        }
                    });
                    holder.sp5.setSelection(Check.getInteger("ed1" + position + MycodeIndex, context,0));

                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);
                }
                else if (position==3) {

                    String tit = "Tulangan Bawah Horizontal";
                    String[] isi = {
                            "8","9","10","12"
                    };
                    final ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_spinner_item, isi);
                    holder.sp5.setVisibility(View.VISIBLE);
                    holder.sp5.setAdapter(adapter);
                    holder.sp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int positionw, long id) {
                            save("ed1" + position + MycodeIndex, positionw);
                            // notifyDataSetChanged();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            parent.setSelection(Check.getInteger("ed1" + position + MycodeIndex, context,0));
                        }
                    });
                    holder.sp5.setSelection(Check.getInteger("ed1" + position + MycodeIndex, context,0));

                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);
                }
                else if (position==4) {

                    String tit = "Tulangan Atas Horizontal";
                    String[] isi = {
                            "8","9","10","12"
                    };
                    final ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_spinner_item, isi);
                    holder.sp5.setVisibility(View.VISIBLE);
                    holder.sp5.setAdapter(adapter);
                    holder.sp5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int positionw, long id) {
                            save("ed1" + position + MycodeIndex, positionw);
                            // notifyDataSetChanged();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            parent.setSelection(Check.getInteger("ed1" + position + MycodeIndex, context,0));
                        }
                    });
                    holder.sp5.setSelection(Check.getInteger("ed1" + position + MycodeIndex, context,0));

                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);
                }
                else if (position==5) {
                    String tit = "Panjang Besi";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Panjang");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Tinggi");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.GONE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            //notifyDataSetChanged();
                        }
                    });
                }
                else if (position==6) {
                    final List<Float> si = new ArrayList<>();
                    si.add(0.395f);
                    si.add(0.5f);
                    si.add(0.617f);
                    si.add(0.888f);


                    String tit = "Bobot";
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText(tit);
                    holder.ed1.setHint(tit);

                    holder.ed1.setVisibility(View.GONE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    float tutul = si.get(Check.getInteger("ed1" + (lastkol2+0) + MycodeIndex,context,0))+
                            si.get(Check.getInteger("ed1" + (lastkol2+1) + MycodeIndex,context,0))+
                            si.get(Check.getInteger("ed1" + (lastkol2+2) + MycodeIndex,context,0))+
                            si.get(Check.getInteger("ed1" + (lastkol2+3) + MycodeIndex,context,0));
                    float tui = tutul * get("ed1" + (lastkol2+4) + MycodeIndex);
                    holder.tv1.setText(String.valueOf(tui));
                    save("ed1" + position + MycodeIndex, tui);

                    holder.tv3.setText("Hitung Kembali "+tit);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            float tutul = si.get(Check.getInteger("ed1" + (lastkol2+0) + MycodeIndex,context,0))+
                                    si.get(Check.getInteger("ed1" + (lastkol2+1) + MycodeIndex,context,0))+
                                    si.get(Check.getInteger("ed1" + (lastkol2+2) + MycodeIndex,context,0))+
                                    si.get(Check.getInteger("ed1" + (lastkol2+3) + MycodeIndex,context,0));
                            float tui = tutul * get("ed1" + (lastkol2+4) + MycodeIndex);
                            holder.tv1.setText(String.valueOf(tui));
                            save("ed1" + position + MycodeIndex, tui);
                        }
                    });
                }
                else if(position==7){
                    if (position==7) {
                        holder.add.setVisibility(View.VISIBLE);
                        holder.add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AddLuas(0f,position);
                            }
                        });
                        holder.remov2.setVisibility(View.GONE);

                        holder.plate.setVisibility(View.VISIBLE);
                        holder.name_plate.setText("Luas 1");
                        holder.ed1.setHint("Panjang");
                        holder.ed2.setHint("Lebar");
                        holder.ed3.setHint("Tinggi");

                        holder.ed1.setVisibility(View.VISIBLE);
                        holder.ed2.setVisibility(View.VISIBLE);
                        holder.ed3.setVisibility(View.GONE);
                        holder.tv1.setText("Panjang");
                        holder.tv2.setText("Lebar");
                        holder.tv3.setText("Tinggi");

                        holder.tv1.setVisibility(View.VISIBLE);
                        holder.tv2.setVisibility(View.VISIBLE);
                        holder.tv3.setVisibility(View.GONE);

                        holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                        holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                        holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                        holder.ed1.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed2.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                    }
                    else if (position==7+luasw.size()+1) {
                        holder.add.setVisibility(View.GONE);
                        holder.add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AddLuas(0f,position);
                            }
                        });
                        holder.remov2.setVisibility(View.GONE);

                        holder.plate.setVisibility(View.VISIBLE);
                        holder.name_plate.setText("Volume Plate");
                        holder.ed1.setHint("Panjang");
                        holder.ed2.setHint("Lebar");
                        holder.ed3.setHint("Tinggi");

                        holder.ed1.setVisibility(View.GONE);
                        holder.ed2.setVisibility(View.GONE);
                        holder.ed3.setVisibility(View.GONE);

                        holder.tv2.setText("Lebar");
                        holder.tv3.setText("Hitung Volume Plate");

                        holder.tv1.setVisibility(View.VISIBLE);
                        holder.tv2.setVisibility(View.GONE);
                        holder.tv3.setVisibility(View.VISIBLE);

                        float total = 0f;
                        for (int s = 0;s<luasw.size();s++){
                            float luasee = get("ed1" + (position-luasw.size()+s) + MycodeIndex) * get("ed2" + (position-luasw.size()+s) + MycodeIndex);
                            total+= luasee;
                        }
                        float sf = total * get("ed1"+(lastkol2+5)+MycodeIndex);
                        save("ed1" + position + MycodeIndex, sf);
                        holder.tv1.setText(String.valueOf(sf));
                        holder.tv3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                float total = 0f;
                                for (int s = 0;s<luasw.size();s++){
                                    float luasee = get("ed1" + (position-luasw.size()+s) + MycodeIndex) * get("ed2" + (position-luasw.size()+s) + MycodeIndex);
                                    total+= luasee;
                                }
                                float sf = total * get("ed1"+(lastkol2+5)+MycodeIndex);
                                holder.tv1.setText(String.valueOf(sf));
                                save("ed1" + position + MycodeIndex, sf);
                                HitungLuasPekTan7(12,lastkol+12,33+luasw.size());
                            }
                        });
                    }
                    else {
                        holder.add.setVisibility(View.GONE);
                        holder.add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AddLuas(0f,position);
                            }
                        });
                        holder.remov2.setVisibility(View.VISIBLE);
                        holder.remov2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                RemoveLuas(position-(6+1));
                            }
                        });

                        holder.plate.setVisibility(View.VISIBLE);
                        holder.name_plate.setText("Luas "+(position-(lastkol2+5)));
                        holder.ed1.setHint("Panjang");
                        holder.ed2.setHint("Lebar");
                        holder.ed3.setHint("Tinggi");

                        holder.ed1.setVisibility(View.VISIBLE);
                        holder.ed2.setVisibility(View.VISIBLE);
                        holder.ed3.setVisibility(View.GONE);
                        holder.tv1.setText("Panjang");
                        holder.tv2.setText("Lebar");
                        holder.tv3.setText("Tinggi");

                        holder.tv1.setVisibility(View.VISIBLE);
                        holder.tv2.setVisibility(View.VISIBLE);
                        holder.tv3.setVisibility(View.GONE);

                        holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                        holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                        holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                        holder.ed1.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed2.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                    }
                }
                HitungLuasPekTan777(3);
            }

            if(banyakList-1==position){
                CanChange=true;
//                Hit(0);
            }
        }
        else if(Mycode.equals(LuasBeton3)){
            if(position==0) {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Berat 1 Wiremesh");
                holder.ed1.setHint("Berat");
                holder.ed2.setHint("Lebar");
                holder.ed3.setVisibility(View.GONE);
                holder.ed2.setVisibility(View.GONE);
                holder.tv1.setText("Berat");
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan8();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else if(position==1) {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Ukuran");
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan8();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan8();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else  {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Luas yang dibutuhkan");
                holder.ed1.setHint("Total Luas");
                holder.ed2.setHint("Lebar");
                holder.ed3.setVisibility(View.GONE);
                holder.ed2.setVisibility(View.GONE);
                holder.tv1.setText("Total Luas");
                holder.tv2.setText("Lebar");
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan8();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan8();

            }

            if(banyakList-1==position) {
                CanChange = true;
                HitungLuasPekTan8();
            }
        }
        else if(Mycode.equals(LuasPeding)){
            if(position==0) {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume Gunung-Gunung");
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan9();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan9();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan9();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else if(position==1) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff2x(0,luasw2.size(),(1+luasw.size()+luasw2.size()));
                    }
                });
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume Pengurang 1");
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan9();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan9();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan9();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else if(position>1&&position<=luasw2.size()){
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas2((position-1),true,(luasw.size()+luasw2.size()));
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume Pengurang "+position);
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan9();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan9();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan9();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else if(position==(luasw2.size()+1)) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff(0,position+1);
                    }
                });
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume "+(position-(luasw2.size())));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan9();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan9();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan9();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else  {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position-(1+luasw2.size()));
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume "+(position-(luasw2.size())));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan9();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan9();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan9();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            if(banyakList-1==position) {
                CanChange = true;
                HitungLuasPekTan9();
            }
        }
        else if(Mycode.equals(LuasPeding2)){

            if (position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff(0f,addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Tinggi");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Tinggi");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan10();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan10();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Tinggi");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Tinggi");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan10();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan10();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan10();
            }
            if(banyakList-1==position) {
                CanChange = true;
                HitungLuasPekTan10();
            }
        }
        else if(Mycode.equals(LuasPlester)){
            if (position == 0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff2x(0,addNow,(luasw.size()+luasw2.size()));
                    }
                });
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume Pegurang 1");
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");
                holder.ed3.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan11();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan11();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else if (position < luasw2.size()) {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas2((position-1),true,(luasw.size()+luasw2.size()-1));
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume Pengurangan " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");
                holder.ed3.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan11();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan11();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else if (position == luasw2.size()) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuasff(0,luasw2.size()+luasw.size());
                    }
                });
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume 1" );
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");
                holder.ed3.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan11();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan11();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position - (luasw2.size()+1));
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (luasw.size()));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");
                holder.ed3.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan11();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan11();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            if(banyakList-1==position) {
                CanChange = true;
                HitungLuasPekTan11();
            }
        }
        else if(Mycode.equals(LuasPlester2)){
            holder.add.setVisibility(View.GONE);
            holder.remov2.setVisibility(View.GONE);

            holder.plate.setVisibility(View.VISIBLE);
            holder.name_plate.setText("Volume");
            holder.ed1.setHint("Total Panjang");
            holder.ed2.setVisibility(View.GONE);
            holder.ed3.setVisibility(View.GONE);
            holder.tv1.setText("Total Panjang");
            holder.tv2.setVisibility(View.GONE);
            holder.tv3.setVisibility(View.GONE);

            holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());

            holder.ed1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()&&CanChange){
                        save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                        HitungLuasP0(0);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if(banyakList-1==position) {
                CanChange = true;
                HitungLuasP0(0);
            }

        }
        else if(Mycode.equals(Lantai)){
            if(position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuas(0f,addNow);
                    }
                });
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume "+(position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Jumlah");
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Jumlah");
                holder.ed3.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan12();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan12();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan12();
            }
            else  {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume "+(position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Jumlah");
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Jumlah");
                holder.ed3.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan12();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan12();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan12();
            }
            if(banyakList-1==position){
                CanChange = true;
                HitungLuasPekTan12();
            }
        }
        else if(Mycode.equals(Lantai2)){
            if(position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuas(0f,addNow);
                    }
                });
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume "+(position+1));
                holder.ed1.setHint("Panjang Miring");
                holder.ed2.setHint("Lebar Miring");
                holder.ed3.setHint("Jumlah");
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Jumlah");
                holder.ed3.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan122();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan122();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan122();
            }
            else  {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume "+(position+1));
                holder.ed1.setHint("Panjang Miring");
                holder.ed2.setHint("Lebar Miring");
                holder.ed3.setHint("Jumlah");
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Jumlah");
                holder.ed3.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan122();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan122();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

            }
            if(banyakList-1==position){
                HitungLuasPekTan122();
                CanChange = true;
            }
        }
        else if(Mycode.equals(Lantai3)){
            holder.add.setVisibility(View.GONE);
            holder.remov2.setVisibility(View.GONE);

            holder.plate.setVisibility(View.VISIBLE);
            holder.name_plate.setText("Total Panjang Bubung");
            holder.ed1.setHint("Total Panjang Bubung");
            holder.ed2.setVisibility(View.GONE);
            holder.ed3.setVisibility(View.GONE);
            holder.tv1.setVisibility(View.GONE);
            holder.tv2.setVisibility(View.GONE);
            holder.tv3.setVisibility(View.GONE);

            holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());

            holder.ed1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()&&CanChange){
                        save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                        HitungLuasP22(0);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if(banyakList-1==position){
                HitungLuasP22(0);
                CanChange = true;
            }


        }
        else if(Mycode.equals(Lantai4)){
            holder.add.setVisibility(View.GONE);
            holder.remov2.setVisibility(View.GONE);

            holder.plate.setVisibility(View.VISIBLE);
            holder.name_plate.setText("Total Panjang Nok");
            holder.ed1.setHint("Total Panjang Bubung");
            holder.ed2.setVisibility(View.GONE);
            holder.ed3.setVisibility(View.GONE);
            holder.tv1.setVisibility(View.GONE);
            holder.tv2.setVisibility(View.GONE);
            holder.tv3.setVisibility(View.GONE);

            holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());

            holder.ed1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()&&CanChange){
                        save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                        HitungLuasP2(0);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if(banyakList-1==position){
                HitungLuasP2(0);
                CanChange = true;
            }

        }
        else if(Mycode.equals(kayu)){
            if (position==0)
            {
                holder.title.setText("Volume Kusen 1");
                holder.title.setVisibility(View.VISIBLE);
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuas8(0f, addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume");
                holder.name_plate.setVisibility(View.GONE);
                holder.ed1.setHint("Luas Penampang");
                holder.ed2.setHint("Panjang Kusen");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.GONE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Luas Penampang");
                holder.tv2.setText("Panjang Kusen");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.GONE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6312(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange ) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6312(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan6312(0);
            }
            else {
                if((position+1)%2!=0) {
                    holder.title.setText("Volume Kusen "+((position/2)+1));
                    holder.title.setVisibility(View.VISIBLE);
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.VISIBLE);
                    holder.remov2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RemoveLuas8(position,luasw.size());
                        }
                    });

                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setText("Volume");
                    holder.name_plate.setVisibility(View.GONE);
                    holder.ed1.setHint("Luas Penampang");
                    holder.ed2.setHint("Panjang Kusen");
                    holder.ed3.setHint("Jumlah");
                    holder.ed4.setHint("Jumlah");

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.VISIBLE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Luas Penampang");
                    holder.tv2.setText("Panjang Kusen");
                    holder.tv3.setText("Jumlah");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.VISIBLE);
                    holder.tv2.setVisibility(View.VISIBLE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                    holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                    holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                    holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan6312(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan6312(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    HitungLuasPekTan6312(0);
                }
                else{
                    holder.title.setVisibility(View.GONE);
                    holder.add.setVisibility(View.GONE);
                    holder.remov2.setVisibility(View.GONE);
                    holder.plate.setVisibility(View.VISIBLE);
                    holder.name_plate.setVisibility(View.GONE);
                    holder.ed1.setHint("Jumlah");
                    holder.ed2.setHint("Lebar");
                    holder.ed3.setHint("Jumlah");
                    holder.ed4.setHint("Jumlah");

                    holder.ed1.setVisibility(View.VISIBLE);
                    holder.ed2.setVisibility(View.GONE);
                    holder.ed3.setVisibility(View.GONE);
                    holder.ed4.setVisibility(View.GONE);

                    holder.tv1.setText("Jumlah");
                    holder.tv2.setText("Lebar");
                    holder.tv3.setText("Jumlah");
                    holder.tv4.setText("Jumlah");

                    holder.tv1.setVisibility(View.VISIBLE);
                    holder.tv2.setVisibility(View.GONE);
                    holder.tv3.setVisibility(View.GONE);
                    holder.tv4.setVisibility(View.GONE);

                    holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                    holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                    holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                    holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                    holder.ed1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan6312(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan6312(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    holder.ed3.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!s.toString().isEmpty()&&CanChange ) {
                                save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                HitungLuasPekTan6312(0);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    HitungLuasPekTan6312(0);
                }
            }
            if(banyakList-1==position){
                CanChange = true;
                HitungLuasPekTan6312(0);
            }
        }
        else if(Mycode.equals(kayu2)){
            if(position==0){
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuas(0f,addNow);
                    }
                });
                holder.remov2.setVisibility(View.GONE);
                String tit = "Volume";

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText(tit);
                holder.ed1.setHint("Lebar");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.tv1.setText("Lebar");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");
                holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());
                holder.ed2.setText(get("ed2"+position+MycodeIndex).toString());
                holder.ed3.setText(get("ed3"+position+MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(!s.toString().isEmpty()&&CanChange){
                            save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                            HitungLuaskayu2();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(!s.toString().isEmpty()&&CanChange){
                            save("ed2"+position+MycodeIndex,Float.parseFloat(s.toString()));
                            HitungLuaskayu2();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(!s.toString().isEmpty()&&CanChange){
                            save("ed3"+position+MycodeIndex,Float.parseFloat(s.toString()));
                            HitungLuaskayu2();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else{
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                    }
                });
                String tit = "Volume";

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText(tit);
                holder.ed1.setHint("Lebar");
                holder.ed2.setHint("Tinggi");
                holder.ed3.setHint("Jumlah");
                holder.tv1.setText("Lebar");
                holder.tv2.setText("Tinggi");
                holder.tv3.setText("Jumlah");
                holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());
                holder.ed2.setText(get("ed2"+position+MycodeIndex).toString());
                holder.ed3.setText(get("ed3"+position+MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(!s.toString().isEmpty()&&CanChange){
                            save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                            HitungLuaskayu2();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(!s.toString().isEmpty()&&CanChange){
                            save("ed2"+position+MycodeIndex,Float.parseFloat(s.toString()));
                            HitungLuaskayu2();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(!s.toString().isEmpty()&&CanChange){
                            save("ed3"+position+MycodeIndex,Float.parseFloat(s.toString()));
                            HitungLuaskayu2();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            if(banyakList-1==position){
                CanChange = true;
                HitungLuaskayu2();
            }
        }
        else if(Mycode.equals(kunci)){
            holder.add.setVisibility(View.GONE);
            holder.remov2.setVisibility(View.GONE);

            holder.plate.setVisibility(View.VISIBLE);
            holder.name_plate.setText("Volume");
            holder.ed1.setHint("Jumlah");
            holder.ed2.setVisibility(View.GONE);
            holder.ed3.setVisibility(View.GONE);
            holder.tv1.setText("Jumlah");
            holder.tv2.setVisibility(View.GONE);
            holder.tv3.setVisibility(View.GONE);

            holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());

            holder.ed1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()&&CanChange){
                        save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                        HitungLuasP0(0);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            HitungLuasP0(0);
            CanChange = true;
        }
        else if(Mycode.equals(kunci3)){
            holder.add.setVisibility(View.GONE);
            holder.remov2.setVisibility(View.GONE);

            holder.plate.setVisibility(View.VISIBLE);
            holder.name_plate.setText("Volume");
            holder.ed1.setHint("Total Panjang");
            holder.ed2.setVisibility(View.GONE);
            holder.ed3.setVisibility(View.GONE);
            holder.tv1.setText("Total Panjang");
            holder.tv2.setVisibility(View.GONE);
            holder.tv3.setVisibility(View.GONE);

            holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());

            holder.ed1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()&&CanChange){
                        save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                        HitungLuasP0(0);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            HitungLuasP0(0);
            CanChange = true;
        }
        else if(Mycode.equals(LuasPeding3)){
            holder.add.setVisibility(View.GONE);
            holder.remov2.setVisibility(View.GONE);

            holder.plate.setVisibility(View.VISIBLE);
            holder.name_plate.setText("Volume");
            holder.ed1.setHint("Total Panjang");
            holder.ed2.setVisibility(View.GONE);
            holder.ed3.setVisibility(View.GONE);
            holder.tv1.setText("Total Panjang");
            holder.tv2.setVisibility(View.GONE);
            holder.tv3.setVisibility(View.GONE);

            holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());

            holder.ed1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()&&CanChange){
                        save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                        HitungLuasP0(0);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            HitungLuasP0(0);
            CanChange = true;
        }
        else if(Mycode.equals(kunci2)){
            if(position==0){
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuas(0f,addNow);
                    }
                });
                holder.remov2.setVisibility(View.GONE);
                String tit = "Volume";

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText(tit);
                holder.ed1.setHint("Lebar");
                holder.ed2.setHint("Panjang");
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Lebar");
                holder.tv2.setText("Panjang");
                holder.tv3.setVisibility(View.GONE);
                holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());
                holder.ed2.setText(get("ed2"+position+MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(!s.toString().isEmpty()&&CanChange){
                            save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                            HitungLuaskayu3();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(!s.toString().isEmpty()&&CanChange){
                            save("ed2"+position+MycodeIndex,Float.parseFloat(s.toString()));
                            HitungLuaskayu3();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else{
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                    }
                });
                String tit = "Volume";

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText(tit);
                holder.ed1.setHint("Lebar");
                holder.ed2.setHint("Panjang");
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Lebar");
                holder.tv2.setText("Panjang");
                holder.tv3.setVisibility(View.GONE);
                holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());
                holder.ed2.setText(get("ed2"+position+MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(!s.toString().isEmpty()&&CanChange){
                            save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                            HitungLuaskayu3();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(!s.toString().isEmpty()&&CanChange){
                            save("ed2"+position+MycodeIndex,Float.parseFloat(s.toString()));
                            HitungLuaskayu3();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            if(banyakList-1==position){
                HitungLuaskayu3();
                CanChange = true;
            }
        }
        else if(Mycode.equals(cat)){

            if (position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuas(0f,addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Jumlah Sisi");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Jumlah Sisi");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(2);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(2);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(2);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan6(2);
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Jumlah Sisi");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Jumlah Sisi");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(2);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(2);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(2);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan6(2);
            }
            if(banyakList-1==position){
                CanChange = true;
                HitungLuasPekTan6(0);
            }
        }
        else if(Mycode.equals(cat2)){

            if (position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuas(0f,addNow);
                    }
                });
                holder.add.setText("Tambah Volume");
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Tinggi");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Tinggi");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(2);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(2);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(2);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan6(2);
            }
            else {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());

                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Volume " + (position+1));
                holder.ed1.setHint("Tinggi");
                holder.ed2.setHint("Lebar");
                holder.ed3.setHint("Jumlah");
                holder.ed4.setHint("Jumlah");

                holder.ed1.setVisibility(View.VISIBLE);
                holder.ed2.setVisibility(View.VISIBLE);
                holder.ed3.setVisibility(View.VISIBLE);
                holder.ed4.setVisibility(View.GONE);

                holder.tv1.setText("Tinggi");
                holder.tv2.setText("Lebar");
                holder.tv3.setText("Jumlah");
                holder.tv4.setText("Jumlah");

                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
                holder.tv4.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(2);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(2);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(2);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed4.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungLuasPekTan6(0);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungLuasPekTan6(2);
            }
            if(banyakList-1==position){
                HitungLuasPekTan6(0);
                CanChange = true;
            }
        }
        else if(Mycode.equals(cat3)){
            if (position==0) {
                holder.plate.setVisibility(View.GONE);
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setText("Pelana");
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Check.saveInteger(MycodeIndex+"juni",2,context);
                        notifyDataSetChanged();
                    }
                });
                holder.add2.setVisibility(View.VISIBLE);
                holder.add2.setText("Limasan");
                holder.add2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Check.saveInteger(MycodeIndex+"juni",1,context);
                        notifyDataSetChanged();
                    }
                });


            }
            else {
                int gi = Check.getInteger(MycodeIndex+"juni",context,0);
                if(gi==2){
                    if (position==1) {
                        holder.add2.setVisibility(View.GONE);
                        holder.add.setVisibility(View.VISIBLE);
                        holder.add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AddLuas(0f,position);
                            }
                        });
                        holder.add.setText("Tambah Volume");
                        holder.remov2.setVisibility(View.GONE);

                        holder.plate.setVisibility(View.VISIBLE);
                        holder.name_plate.setText("Volume " + (position));
                        holder.ed1.setHint("Panjang Miring");
                        holder.ed2.setHint("Lebar Miring");
                        holder.ed3.setHint("Jumlah");
                        holder.ed4.setHint("Jumlah");

                        holder.ed1.setVisibility(View.VISIBLE);
                        holder.ed2.setVisibility(View.VISIBLE);
                        holder.ed3.setVisibility(View.VISIBLE);
                        holder.ed4.setVisibility(View.GONE);

                        holder.tv1.setText("Panjang Miring");
                        holder.tv2.setText("Lebar Miring");
                        holder.tv3.setText("Jumlah");
                        holder.tv4.setText("Jumlah");

                        holder.tv1.setVisibility(View.VISIBLE);
                        holder.tv2.setVisibility(View.VISIBLE);
                        holder.tv3.setVisibility(View.VISIBLE);
                        holder.tv4.setVisibility(View.GONE);

                        holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                        holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                        holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                        holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                        holder.ed1.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed2.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed3.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed4.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        HitungLuasPekTan616(0);
                    }
                    else {
                        holder.add.setVisibility(View.GONE);
                        holder.add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AddLuas(0f);
                            }
                        });
                        holder.remov2.setVisibility(View.VISIBLE);
                        holder.remov2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                RemoveLuas(position-1);
                                HitungLuasPekTan61(0);
                            }
                        });

                        holder.plate.setVisibility(View.VISIBLE);
                        holder.name_plate.setText("Volume " + (position));
                        holder.ed1.setHint("Panjang Miring");
                        holder.ed2.setHint("Lebar Miring");
                        holder.ed3.setHint("Jumlah");
                        holder.ed4.setHint("Jumlah");

                        holder.ed1.setVisibility(View.VISIBLE);
                        holder.ed2.setVisibility(View.VISIBLE);
                        holder.ed3.setVisibility(View.VISIBLE);
                        holder.ed4.setVisibility(View.GONE);

                        holder.tv1.setText("Panjang Miring");
                        holder.tv2.setText("Lebar Miring");
                        holder.tv3.setText("Jumlah");
                        holder.tv4.setText("Jumlah");

                        holder.tv1.setVisibility(View.VISIBLE);
                        holder.tv2.setVisibility(View.VISIBLE);
                        holder.tv3.setVisibility(View.VISIBLE);
                        holder.tv4.setVisibility(View.GONE);

                        holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                        holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                        holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                        holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                        holder.ed1.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed2.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed3.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed4.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        HitungLuasPekTan616(0);
                    }
                }
                else if(gi==1){
                    //limasan
                    if (position==1) {
                        holder.add2.setVisibility(View.GONE);
                        holder.add.setVisibility(View.VISIBLE);
                        holder.add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AddLuas(0f,position);
                            }
                        });
                        holder.add.setText("Tambah Volume");
                        holder.remov2.setVisibility(View.GONE);

                        holder.plate.setVisibility(View.VISIBLE);
                        holder.name_plate.setText("Volume Atap Segitiga " + (position));
                        holder.ed1.setHint("Panjang Miring");
                        holder.ed2.setHint("Lebar Miring");
                        holder.ed3.setHint("Jumlah");
                        holder.ed4.setHint("Jumlah");

                        holder.ed1.setVisibility(View.VISIBLE);
                        holder.ed2.setVisibility(View.VISIBLE);
                        holder.ed3.setVisibility(View.VISIBLE);
                        holder.ed4.setVisibility(View.GONE);

                        holder.tv1.setText("Panjang Miring");
                        holder.tv2.setText("Lebar Miring");
                        holder.tv3.setText("Jumlah");
                        holder.tv4.setText("Jumlah");

                        holder.tv1.setVisibility(View.VISIBLE);
                        holder.tv2.setVisibility(View.VISIBLE);
                        holder.tv3.setVisibility(View.VISIBLE);
                        holder.tv4.setVisibility(View.GONE);

                        holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                        holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                        holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                        holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                        holder.ed1.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(1);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed2.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(1);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed3.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(1);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed4.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(1);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        HitungLuasPekTan616(1);
                    }
                    else if(position==2){
                        holder.add.setVisibility(View.GONE);
                        holder.add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AddLuas(0f,position);
                            }
                        });
                        holder.add.setText("Tambah Volume");
                        holder.remov2.setVisibility(View.GONE);

                        holder.plate.setVisibility(View.VISIBLE);
                        holder.name_plate.setText("Volume Atap Trapesium " + (position-1));
                        holder.ed1.setHint("Jumlah Sisi Panjang Sejajar");
                        holder.ed2.setHint("Tinggi miring");
                        holder.ed3.setHint("Jumlah");
                        holder.ed4.setHint("Jumlah");

                        holder.ed1.setVisibility(View.VISIBLE);
                        holder.ed2.setVisibility(View.VISIBLE);
                        holder.ed3.setVisibility(View.VISIBLE);
                        holder.ed4.setVisibility(View.GONE);

                        holder.tv1.setText("Jumlah Sisi Panjang Sejajar");
                        holder.tv2.setText("Tinggi miring");
                        holder.tv3.setText("Jumlah");
                        holder.tv4.setText("Jumlah");

                        holder.tv1.setVisibility(View.VISIBLE);
                        holder.tv2.setVisibility(View.VISIBLE);
                        holder.tv3.setVisibility(View.VISIBLE);
                        holder.tv4.setVisibility(View.GONE);

                        holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                        holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                        holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                        holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                        holder.ed1.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(1);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed2.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(1);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed3.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(1);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed4.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan616(1);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        HitungLuasPekTan616(1);
                    }
                    else {
                        if (position % 2 != 0) {
                            holder.add.setVisibility(View.GONE);
                            holder.add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AddLuas(0f);
                                }
                            });
                            holder.remov2.setVisibility(View.VISIBLE);
                            holder.remov2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    RemoveLuas((position)-((position+1)/2));
                                    HitungLuasPekTan61(0);
                                }
                            });

                            holder.plate.setVisibility(View.VISIBLE);
                            holder.name_plate.setText("Volume  Atap Segitiga " + (position));
                            holder.ed1.setHint("Panjang Alas");
                            holder.ed2.setHint("Tinggi");
                            holder.ed3.setHint("Jumlah");
                            holder.ed4.setHint("Jumlah");

                            holder.ed1.setVisibility(View.VISIBLE);
                            holder.ed2.setVisibility(View.VISIBLE);
                            holder.ed3.setVisibility(View.VISIBLE);
                            holder.ed4.setVisibility(View.GONE);

                            holder.tv1.setText("Panjang Alas");
                            holder.tv2.setText("Tinggi");
                            holder.tv3.setText("Jumlah");
                            holder.tv4.setText("Jumlah");

                            holder.tv1.setVisibility(View.VISIBLE);
                            holder.tv2.setVisibility(View.VISIBLE);
                            holder.tv3.setVisibility(View.VISIBLE);
                            holder.tv4.setVisibility(View.GONE);

                            holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                            holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                            holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                            holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                            holder.ed1.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (!s.toString().isEmpty()&&CanChange ) {
                                        save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                        HitungLuasPekTan616(1);
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });
                            holder.ed2.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (!s.toString().isEmpty()&&CanChange ) {
                                        save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                        HitungLuasPekTan616(1);
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });
                            holder.ed3.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (!s.toString().isEmpty()&&CanChange ) {
                                        save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                        HitungLuasPekTan616(1);
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });
                            holder.ed4.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (!s.toString().isEmpty()&&CanChange ) {
                                        save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                        HitungLuasPekTan616(1);
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });
                            HitungLuasPekTan616(1);
                        }
                        else {
                            holder.add.setVisibility(View.GONE);
                            holder.add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AddLuas(0f, position);
                                }
                            });
                            holder.add.setText("Tambah Volume");
                            holder.remov2.setVisibility(View.GONE);

                            holder.plate.setVisibility(View.VISIBLE);
                            holder.name_plate.setText("Volume Atap Trapesium " + (position - 1));
                            holder.ed1.setHint("Jumlah Sisi Panjang Sejajar");
                            holder.ed2.setHint("Tinggi miring");
                            holder.ed3.setHint("Jumlah");
                            holder.ed4.setHint("Jumlah");

                            holder.ed1.setVisibility(View.VISIBLE);
                            holder.ed2.setVisibility(View.VISIBLE);
                            holder.ed3.setVisibility(View.VISIBLE);
                            holder.ed4.setVisibility(View.GONE);

                            holder.tv1.setText("Jumlah Sisi Panjang Sejajar");
                            holder.tv2.setText("Tinggi miring");
                            holder.tv3.setText("Jumlah");
                            holder.tv4.setText("Jumlah");

                            holder.tv1.setVisibility(View.VISIBLE);
                            holder.tv2.setVisibility(View.VISIBLE);
                            holder.tv3.setVisibility(View.VISIBLE);
                            holder.tv4.setVisibility(View.GONE);

                            holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                            holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                            holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                            holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                            holder.ed1.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (!s.toString().isEmpty()&&CanChange ) {
                                        save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                        HitungLuasPekTan616(1);
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });
                            holder.ed2.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (!s.toString().isEmpty()&&CanChange ) {
                                        save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                        HitungLuasPekTan616(1);
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });
                            holder.ed3.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (!s.toString().isEmpty()&&CanChange ) {
                                        save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                        HitungLuasPekTan616(1);
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });
                            holder.ed4.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    if (!s.toString().isEmpty()&&CanChange ) {
                                        save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                        HitungLuasPekTan616(1);
                                    }
                                }

                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });
                            HitungLuasPekTan616(1);
                        }

                    }
                }else {
                    holder.plate.setVisibility(View.GONE);
                    holder.kamboja.setVisibility(View.GONE);
                }
            }
            if(banyakList-1==position){
                CanChange = true;
            }
        }
        else if(Mycode.equals(cat4)){
            if (position==0) {
                holder.plate.setVisibility(View.GONE);
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setText("Kusen");
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Check.saveInteger(MycodeIndex+"juni",2,context);
                        notifyDataSetChanged();
                    }
                });
                holder.add2.setVisibility(View.VISIBLE);
                holder.add2.setText("Daun");
                holder.add2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Check.saveInteger(MycodeIndex+"juni",1,context);
                        notifyDataSetChanged();
                    }
                });


            }
            else {
                int gi = Check.getInteger(MycodeIndex+"juni",context,0);
                if(gi==2){
                    //Kusen
                    if (position==1) {
                        holder.add2.setVisibility(View.GONE);
                        holder.add.setVisibility(View.VISIBLE);
                        holder.add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AddLuas(0f,position);
                            }
                        });
                        holder.add.setText("Tambah Volume");
                        holder.remov2.setVisibility(View.GONE);

                        holder.plate.setVisibility(View.VISIBLE);
                        holder.name_plate.setText("Volume " + (position));
                        holder.ed1.setHint("Keliling Penampang Kayu");
                        holder.ed2.setHint("Panjang Kusen");
                        holder.ed3.setHint("Jumlah");
                        holder.ed4.setHint("Jumlah");

                        holder.ed1.setVisibility(View.VISIBLE);
                        holder.ed2.setVisibility(View.VISIBLE);
                        holder.ed3.setVisibility(View.VISIBLE);
                        holder.ed4.setVisibility(View.GONE);

                        holder.tv1.setText("Keliling Penampang Kayu");
                        holder.tv2.setText("Panjang Kusen");
                        holder.tv3.setText("Jumlah");
                        holder.tv4.setText("Jumlah");

                        holder.tv1.setVisibility(View.VISIBLE);
                        holder.tv2.setVisibility(View.VISIBLE);
                        holder.tv3.setVisibility(View.VISIBLE);
                        holder.tv4.setVisibility(View.GONE);

                        holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                        holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                        holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                        holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                        holder.ed1.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed2.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed3.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed4.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        HitungLuasPekTan6161(0);
                    }
                    else {
                        holder.add2.setVisibility(View.GONE);
                        holder.add.setVisibility(View.GONE);
                        holder.add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AddLuas(0f);
                            }
                        });
                        holder.remov2.setVisibility(View.VISIBLE);
                        holder.remov2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                RemoveLuas(position-1);
                                HitungLuasPekTan6161(0);
                            }
                        });

                        holder.plate.setVisibility(View.VISIBLE);
                        holder.name_plate.setText("Volume " + (position));
                        holder.ed1.setHint("Keliling Penampang Kayu");
                        holder.ed2.setHint("Panjang Kusen");
                        holder.ed3.setHint("Jumlah");
                        holder.ed4.setHint("Jumlah");

                        holder.ed1.setVisibility(View.VISIBLE);
                        holder.ed2.setVisibility(View.VISIBLE);
                        holder.ed3.setVisibility(View.VISIBLE);
                        holder.ed4.setVisibility(View.GONE);

                        holder.tv1.setText("Keliling Penampang Kayu");
                        holder.tv2.setText("Panjang Kusen");
                        holder.tv3.setText("Jumlah");
                        holder.tv4.setText("Jumlah");

                        holder.tv1.setVisibility(View.VISIBLE);
                        holder.tv2.setVisibility(View.VISIBLE);
                        holder.tv3.setVisibility(View.VISIBLE);
                        holder.tv4.setVisibility(View.GONE);

                        holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                        holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                        holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                        holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                        holder.ed1.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed2.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed3.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed4.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        HitungLuasPekTan6161(0);
                    }
                }
                else if(gi==1){
                    //Daun
                    if (position==1) {
                        holder.add2.setVisibility(View.GONE);
                        holder.add.setVisibility(View.VISIBLE);
                        holder.add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AddLuas(0f,position);
                            }
                        });
                        holder.add.setText("Tambah Volume");
                        holder.remov2.setVisibility(View.GONE);

                        holder.plate.setVisibility(View.VISIBLE);
                        holder.name_plate.setText("Volume " + (position));
                        holder.ed1.setHint("Lebar");
                        holder.ed2.setHint("Tinggi");
                        holder.ed3.setHint("Jumlah");
                        holder.ed4.setHint("Jumlah");

                        holder.ed1.setVisibility(View.VISIBLE);
                        holder.ed2.setVisibility(View.VISIBLE);
                        holder.ed3.setVisibility(View.VISIBLE);
                        holder.ed4.setVisibility(View.GONE);

                        holder.tv1.setText("Lebar");
                        holder.tv2.setText("Tinggi");
                        holder.tv3.setText("Jumlah");
                        holder.tv4.setText("Jumlah");

                        holder.tv1.setVisibility(View.VISIBLE);
                        holder.tv2.setVisibility(View.VISIBLE);
                        holder.tv3.setVisibility(View.VISIBLE);
                        holder.tv4.setVisibility(View.GONE);

                        holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                        holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                        holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                        holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                        holder.ed1.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed2.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed3.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed4.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        HitungLuasPekTan616(0);
                    }
                    else {
                        holder.add2.setVisibility(View.GONE);
                        holder.add.setVisibility(View.GONE);
                        holder.add.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AddLuas(0f);
                            }
                        });
                        holder.remov2.setVisibility(View.VISIBLE);
                        holder.remov2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                RemoveLuas(position-1);
                                HitungLuasPekTan6161(0);
                            }
                        });

                        holder.plate.setVisibility(View.VISIBLE);
                        holder.name_plate.setText("Volume " + (position));
                        holder.ed1.setHint("Lebar");
                        holder.ed2.setHint("Tinggi");
                        holder.ed3.setHint("Jumlah");
                        holder.ed4.setHint("Jumlah");

                        holder.ed1.setVisibility(View.VISIBLE);
                        holder.ed2.setVisibility(View.VISIBLE);
                        holder.ed3.setVisibility(View.VISIBLE);
                        holder.ed4.setVisibility(View.GONE);

                        holder.tv1.setText("Lebar");
                        holder.tv2.setText("Tinggi");
                        holder.tv3.setText("Jumlah");
                        holder.tv4.setText("Jumlah");

                        holder.tv1.setVisibility(View.VISIBLE);
                        holder.tv2.setVisibility(View.VISIBLE);
                        holder.tv3.setVisibility(View.VISIBLE);
                        holder.tv4.setVisibility(View.GONE);

                        holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                        holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                        holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());
                        holder.ed4.setText(get("ed4" + position + MycodeIndex).toString());

                        holder.ed1.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed2.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed3.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed3" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        holder.ed4.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                if (!s.toString().isEmpty()&&CanChange) {
                                    save("ed4" + position + MycodeIndex, Float.parseFloat(s.toString()));
                                    HitungLuasPekTan6161(0);
                                }
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                        HitungLuasPekTan616(0);
                    }
                }
                else {
                    holder.plate.setVisibility(View.GONE);
                    holder.kamboja.setVisibility(View.GONE);
                }
            }
            if(banyakList-1==position){
                CanChange = true;
            }
        }
        else if(Mycode.equals(betonSpesial)){
            holder.add.setVisibility(View.GONE);
            holder.remov2.setVisibility(View.GONE);

            holder.plate.setVisibility(View.VISIBLE);
            holder.name_plate.setText("Jumlah Footplate");
            holder.ed1.setHint("Jumlah");
            holder.ed2.setVisibility(View.GONE);
            holder.ed3.setVisibility(View.GONE);
            holder.tv1.setText("Jumlah");
            holder.tv2.setVisibility(View.GONE);
            holder.tv3.setVisibility(View.GONE);

            holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());
            holder.ed2.setText(get("ed2"+position+MycodeIndex).toString());
            holder.ed3.setText(get("ed3"+position+MycodeIndex).toString());

            holder.ed1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()&&CanChange){
                        save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                        HitungSpesialButton(1);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            if(banyakList-1==position){
                CanChange = true;
                HitungSpesialButton(1);
            }
        }
        else if(Mycode.equals(betonSpesial2)){
            holder.add.setVisibility(View.GONE);
            holder.remov2.setVisibility(View.GONE);

            holder.plate.setVisibility(View.VISIBLE);
            holder.name_plate.setText("Total panjang Kebutuhan");
            holder.ed1.setHint("Jumlah");
            holder.ed2.setVisibility(View.GONE);
            holder.ed3.setVisibility(View.GONE);
            holder.tv1.setText("Jumlah");
            holder.tv2.setVisibility(View.GONE);
            holder.tv3.setVisibility(View.GONE);

            holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());
            holder.ed2.setText(get("ed2"+position+MycodeIndex).toString());
            holder.ed3.setText(get("ed3"+position+MycodeIndex).toString());

            holder.ed1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()&&CanChange){
                        save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                        HitungSpesialButton(2);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if(banyakList-1==position){
                CanChange = true;
                HitungSpesialButton(2);
            }
        }
        else if(Mycode.equals(betonSpesial3)){
            holder.add.setVisibility(View.GONE);
            holder.remov2.setVisibility(View.GONE);

            holder.plate.setVisibility(View.VISIBLE);
            holder.name_plate.setText("Volume");
            holder.ed1.setHint("Tinggi kolom");
            holder.ed2.setHint("Jumlah kolom");
            holder.ed3.setVisibility(View.GONE);
            holder.tv1.setText("Tinggi kolom");
            holder.tv2.setText("Jumlah kolom");
            holder.tv3.setVisibility(View.GONE);

            holder.ed1.setText(get("ed1"+position+MycodeIndex).toString());
            holder.ed2.setText(get("ed2"+position+MycodeIndex).toString());
            holder.ed3.setText(get("ed3"+position+MycodeIndex).toString());

            holder.ed1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()&&CanChange){
                        save("ed1"+position+MycodeIndex,Float.parseFloat(s.toString()));
                        HitungSpesialButton(3);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            holder.ed2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty()&&CanChange){
                        save("ed2"+position+MycodeIndex,Float.parseFloat(s.toString()));
                        HitungSpesialButton(3);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            if(banyakList-1==position){
                CanChange = true;
                HitungSpesialButton(3);
            }

        }
        else if(Mycode.equals(betonSpesial4)){
            if(position==0) {
                holder.add.setVisibility(View.VISIBLE);
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddLuas(0f,addNow);
                    }
                });
                holder.remov2.setVisibility(View.GONE);

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Ukuran");
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());
                holder.ed3.setText(get("ed3" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungSpesialButton(4);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungSpesialButton(4);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
            else  {
                holder.add.setVisibility(View.GONE);
                holder.remov2.setVisibility(View.VISIBLE);
                holder.remov2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RemoveLuas(position,true,luasw.size());
                    }
                });

                holder.plate.setVisibility(View.VISIBLE);
                holder.name_plate.setText("Luas yang dibutuhkan");
                holder.ed1.setHint("Panjang");
                holder.ed2.setHint("Lebar");
                holder.ed3.setVisibility(View.GONE);
                holder.tv1.setText("Panjang");
                holder.tv2.setText("Lebar");
                holder.tv3.setVisibility(View.GONE);

                holder.ed1.setText(get("ed1" + position + MycodeIndex).toString());
                holder.ed2.setText(get("ed2" + position + MycodeIndex).toString());

                holder.ed1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed1" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungSpesialButton(4);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                holder.ed2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().isEmpty()&&CanChange) {
                            save("ed2" + position + MycodeIndex, Float.parseFloat(s.toString()));
                            HitungSpesialButton(4);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                HitungSpesialButton(4);

            }
            if(banyakList-1==position){
                CanChange = true;
                HitungSpesialButton(4);
            }
        }
        else {

        }
    }
    @Override
    public int getItemCount() {
        //return 1 + luasw.size();
        if(Mycode.equals(LuasPembersihan)){
            banyakList = 1;
            return 1;
        }
        else if(Mycode.equals(betonSpesial)){
            banyakList = 1;
            return 1;
        }
        else if(Mycode.equals(betonSpesial2)){
            banyakList = 1;
            return 1;
        }
        else if(Mycode.equals(betonSpesial3)){
            banyakList = 1;
            return 1;
        }
        else if(Mycode.equals(betonSpesial4)){
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(LuasPembersihan2)){
            banyakList = 1;
            return 1;
        }
        else if(Mycode.equals(LuasPekerjaanTanah)){
            banyakList = 1;
            return 2;
        }
        else if(Mycode.equals(LuasPekerjaanTanah2)){
            banyakList = 1;
            return 1;
        }
        else if(Mycode.equals(LuasPekerjaanTanah3)){
            addNow = 1+luasw.size();
            banyakList = 1+luasw.size();
            return 1 +luasw.size();
        }
        else if(Mycode.equals(LuasPekerjaanTanah4)){
            addNow = 1+luasw.size();
            banyakList = 1+luasw.size();
            return 1 +luasw.size();
        }
        else if(Mycode.equals(LuasPekerjaanTanah5)){
            banyakList = 2;
            return 2;
        }
        else if(Mycode.equals(LuasPekerjaanTanah6)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(LuasPekerjaanTanah12)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(LuasPekerjaanTanah121)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(LuasPekerjaanTanah122)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(LuasPekerjaanTanah83)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }

        else if(Mycode.equals(LuasPekerjaanTanah7)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(LuasPekerjaanTanah8)){
            addNow = luasw.size();
            banyakList = luasw.size()*2;
            return luasw.size()*2;
        }
        else if(Mycode.equals(LuasPekerjaanTanah81)){
            addNow = luasw.size();
            banyakList = luasw.size()*2;
            return luasw.size()*2;
        }
        else if(Mycode.equals(LuasPekerjaanTanah82)){
            addNow = luasw.size();
            banyakList = luasw.size()*2;
            return luasw.size()*2;
        }

        else if(Mycode.equals(LuasPekerjaanTanah9)){
            addNow = luasw.size();
            banyakList = luasw.size()*2;
            return luasw.size()*2;
        }
        else if(Mycode.equals(LuasPekerjaanTanah10)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(LuasPekerjaanTanah11)){
            banyakList = 3;
            return 3;
        }
        else if(Mycode.equals(LuasPondasi2)){
            banyakList = 2;
            return 2;
        }
        else if(Mycode.equals(LuasPondasi3)){
            banyakList = 2;
            return 2;
        }
        else if(Mycode.equals(LuasBeton)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(LuasBeton88)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(LuasBeton888)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(LuasBeton2)){
            addNow = luasw.size();
            int gi = Check.getInteger(MycodeIndex+"juni",context,0);
            int a=1;
            if(gi==3){
                a+=6+luasw.size();
            }else if(gi==2||gi==1){
                a+=13;
            }
            banyakList = a;
            return a;
        }
        else if(Mycode.equals(LuasBeton3)){
            banyakList = 3;
            return 3;
        }
        else  if(Mycode.equals(LuasPeding3)){
            banyakList = 1;
            return 1;
        }
        else if(Mycode.equals(LuasPeding)){
            addNow = luasw.size();
            addNow2 = luasw2.size();
            banyakList = 1+luasw2.size()+luasw.size();
            return 1+luasw2.size()+luasw.size();
        }
        else if(Mycode.equals(LuasPeding2)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(LuasPlester)){
            addNow = luasw.size()-1;
            addNow2 = luasw2.size();
            banyakList = luasw2.size()+luasw.size();
            return luasw2.size()+luasw.size();
        }
        else if(Mycode.equals(Lantai)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(Lantai2)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(Lantai3)){
            banyakList = 1;
            return 1;
        }
        else if(Mycode.equals(Lantai4)){
            banyakList = 1;
            return 1;
        }
        else if(Mycode.equals(kayu)){
            addNow = luasw.size();
            banyakList = luasw.size()*2;
            return luasw.size()*2;
        }
        else if(Mycode.equals(kayu2)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(kunci)){
            banyakList = 1;
            return 1;
        }
        else if(Mycode.equals(kunci3)){
            banyakList = 1;
            return 1;
        }
        else if(Mycode.equals(kunci2)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(cat)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(cat2)){
            addNow = luasw.size();
            banyakList = luasw.size();
            return luasw.size();
        }
        else if(Mycode.equals(cat3)){
            addNow = luasw.size();
            int a=1;
            if(Check.getInteger(MycodeIndex+"juni",context,0)==1){
                a=2;
            }
            banyakList = 1+(luasw.size()*a);
            return 1+(luasw.size()*a);
        }
        else if(Mycode.equals(cat4)){
            addNow = luasw.size();
            int a=1;
            banyakList = 1+luasw.size();
            return 1+luasw.size();
        }
        else {
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public EditText ed1,ed2,ed3,ed4;
        public Spinner sp5;
        public TextView tv1,tv2,tv3,tv4,tv5,title;
        public LinearLayout plate,kamboja,tit_plat,cont_plat;
        public TextView name_plate;
        public TextView add,add3,add2,remov2;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            sp5 = itemView.findViewById(R.id.sp5);
            ed1 = itemView.findViewById(R.id.ed1);
            ed2 = itemView.findViewById(R.id.ed2);
            ed3 = itemView.findViewById(R.id.ed3);
            ed4 = itemView.findViewById(R.id.ed4);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            tv4 = itemView.findViewById(R.id.tv4);
            tv5 = itemView.findViewById(R.id.tv5);
            kamboja = itemView.findViewById(R.id.kamboja);
            plate = itemView.findViewById(R.id.plate);
            name_plate = itemView.findViewById(R.id.vol_tex);
            add = itemView.findViewById(R.id.buttonAdd);
            add2 = itemView.findViewById(R.id.buttonAdd2);
            add3 = itemView.findViewById(R.id.buttonAdd3);
            remov2 = itemView.findViewById(R.id.remov2);

            tit_plat = itemView.findViewById(R.id.titt_li);
            cont_plat = itemView.findViewById(R.id.content_li);
        }
    }

    void AddLuas(float va){
        CanChange = false;
        luasw.add(va);
        Check.saveInteger(MycodeIndex+"l",luasw.size(),context);
        notifyDataSetChanged();
    }
    void AddLuas2(float va){
        CanChange = false;
        luasw2.add(va);
        Check.saveInteger(MycodeIndex+"l2",luasw2.size(),context);
        notifyDataSetChanged();
    }
    void AddLuas(float va,int pos){
        CanChange = false;
        luasw.add(va);
        Check.saveInteger(MycodeIndex+"l",luasw.size(),context);
        notifyDataSetChanged();
    }
    void AddLuas8(float va,int po){
        banyakList+=2;
        CanChange = false;
        luasw.add(va);
        int pos = (po*2);
        Log.d("Kalongan", "ADD: "+pos);
        save("ed1" + pos + MycodeIndex, va);
        save("ed2" + pos + MycodeIndex, va);
        save("ed3" + pos + MycodeIndex, va);
        save("ed4" + pos + MycodeIndex, va);
        save("ed1" + (pos+1) + MycodeIndex, va);
        save("ed2" + (pos+1) + MycodeIndex, va);
        save("ed3" + (pos+1) + MycodeIndex, va);
        save("ed4" + (pos+1) + MycodeIndex, va);
        Check.saveInteger(MycodeIndex+"l",luasw.size(),context);
        notifyDataSetChanged();
    }
    void AddLuasff(float va,int pos){
        CanChange = false;
        addNow ++;
        banyakList++;
        Log.d("Kalongan", "AddLuasff: "+pos);
        luasw.add(va);
        save("ed1" + pos + MycodeIndex, va);
        save("ed2" + pos + MycodeIndex, va);
        save("ed3" + pos + MycodeIndex, va);
        save("ed4" + pos + MycodeIndex, va);
        Check.saveInteger(MycodeIndex+"l",luasw.size(),context);
        notifyDataSetChanged();
    }
    void AddLuasff2(float va,int pos){
        CanChange = false;
        addNow ++;
        banyakList++;
        Log.d("Kalongan", "AddLuasff: "+pos);
        luasw2.add(va);
        save("ed1" + pos + MycodeIndex, va);
        save("ed2" + pos + MycodeIndex, va);
        save("ed3" + pos + MycodeIndex, va);
        save("ed4" + pos + MycodeIndex, va);
        Check.saveInteger(MycodeIndex+"l2",luasw2.size(),context);
        notifyDataSetChanged();
    }
    void AddLuasff2x(float va,int pos,int banyak){
        CanChange = false;
        addNow ++;
        banyakList++;

        for (int r=banyak;r>(pos);r--){
            String ed1 = get("ed1" + (r) + MycodeIndex).toString();
            String ed2 = get("ed2" + (r) + MycodeIndex).toString();
            String ed3 = get("ed3" + (r) + MycodeIndex).toString();
            String ed4 = get("ed4" + (r) + MycodeIndex).toString();

            save("ed1" + (r+1) + MycodeIndex, Float.parseFloat(ed1));
            save("ed2" + (r+1) + MycodeIndex, Float.parseFloat(ed2));
            save("ed3" + (r+1) + MycodeIndex, Float.parseFloat(ed3));
            save("ed4" + (r+1) + MycodeIndex, Float.parseFloat(ed4));

            String ed1t = get("ed1" + (r+1) + MycodeIndex).toString();
            Log.d("Kalongan 2", "AddLuas: "+r +" "+ pos +" " +ed1 + " "+ed1t);
        }

        Log.d("Kalongan", "AddLuasff: "+pos);
        luasw2.add(va);
        save("ed1" + (pos+1) + MycodeIndex, va);
        save("ed2" + (pos+1) + MycodeIndex, va);
        save("ed3" + (pos+1) + MycodeIndex, va);
        save("ed4" + (pos+1) + MycodeIndex, va);
        Check.saveInteger(MycodeIndex+"l2",luasw2.size(),context);
        notifyDataSetChanged();
    }
    void AddLuas2(float va,int pos){
        CanChange = false;
        luasw2.add(va);
        save("ed1" + pos + MycodeIndex, va);
        save("ed2" + pos + MycodeIndex, va);
        save("ed3" + pos + MycodeIndex, va);
        save("ed4" + pos + MycodeIndex, va);
        Check.saveInteger(MycodeIndex+"l2",luasw2.size(),context);
        notifyDataSetChanged();
    }
    void RemoveLuas(int index){
        CanChange = false;
        addNow--;
        luasw.remove(index);
        Check.saveInteger(MycodeIndex+"l",luasw.size(),context);
        notifyDataSetChanged();
    }
    void RemoveLuas(int index,Boolean U,int banyak){
        if(U){
            for (int r=(index+1);r<banyak;r++){
                String ed1 = get("ed1" + (r+1) + MycodeIndex).toString();
                String ed2 = get("ed2" + (r+1) + MycodeIndex).toString();
                String ed3 = get("ed3" + (r+1) + MycodeIndex).toString();
                String ed4 = get("ed4" + (r+1) + MycodeIndex).toString();

                String ed1t = get("ed1" + (r) + MycodeIndex).toString();

                save("ed1" + (r) + MycodeIndex, Float.parseFloat(ed1));
                save("ed2" + (r) + MycodeIndex, Float.parseFloat(ed2));
                save("ed3" + (r) + MycodeIndex, Float.parseFloat(ed3));
                save("ed4" + (r) + MycodeIndex, Float.parseFloat(ed4));

                Log.d("Kalongan 2", "RemoveLuas: "+r +" "+ banyak +" " +ed1 + " "+ed1t);
            }
        }
        banyakList--;
        CanChange = false;
        addNow--;
        luasw.remove(index);
        Check.saveInteger(MycodeIndex+"l",luasw.size(),context);
        notifyDataSetChanged();
    }
    void RemoveLuas2(int index,Boolean U,int banyak){
        Log.d("Kalongan 2", "RemoveLuas: "+index +" "+ banyak);
        if(U){
            for (int r=(index+1);r<banyak;r++){
                String ed1 = get("ed1" + (r+1) + MycodeIndex).toString();
                String ed2 = get("ed2" + (r+1) + MycodeIndex).toString();
                String ed3 = get("ed3" + (r+1) + MycodeIndex).toString();
                String ed4 = get("ed4" + (r+1) + MycodeIndex).toString();

                String ed1t = get("ed1" + (r) + MycodeIndex).toString();

                save("ed1" + (r) + MycodeIndex, Float.parseFloat(ed1));
                save("ed2" + (r) + MycodeIndex, Float.parseFloat(ed2));
                save("ed3" + (r) + MycodeIndex, Float.parseFloat(ed3));
                save("ed4" + (r) + MycodeIndex, Float.parseFloat(ed4));

                Log.d("Kalongan 2", "RemoveLuas: "+r +" "+ banyak +" " +ed1 + " "+ed1t);
            }
        }
        banyakList--;
        CanChange = false;
        addNow--;
        luasw2.remove(index);
        Check.saveInteger(MycodeIndex+"l2",luasw2.size(),context);
        notifyDataSetChanged();
    }
    void RemoveLuas8(int index,int banyak){
        Log.d("Kalongan", "RemoveLuas: "+index +" "+ banyak );
            for (int r=(index+3);r<=(banyak*2);r++){
                String ed1 = get("ed1" + (r-1) + MycodeIndex).toString();
                String ed2 = get("ed2" + (r-1) + MycodeIndex).toString();
                String ed3 = get("ed3" + (r-1) + MycodeIndex).toString();
                String ed4 = get("ed4" + (r-1) + MycodeIndex).toString();

                save("ed1" + (r-3) + MycodeIndex, Float.parseFloat(ed1));
                save("ed2" + (r-3) + MycodeIndex, Float.parseFloat(ed2));
                save("ed3" + (r-3) + MycodeIndex, Float.parseFloat(ed3));
                save("ed4" + (r-3) + MycodeIndex, Float.parseFloat(ed4));

                String ed1t = get("ed1" + (r-3) + MycodeIndex).toString();
                Log.d("Kalongan 2", "RemoveLuas: "+r +" "+ banyak +" " +ed1 + " "+ed1t);
            }
        banyakList-=2;
        CanChange = false;
        addNow--;
        luasw.remove((index/2));
        Check.saveInteger(MycodeIndex+"l",luasw.size(),context);
        notifyDataSetChanged();
    }
    void RemoveLuas3(int index){
        CanChange = false;
        luasw.remove((index/2));
        Check.saveInteger(MycodeIndex+"l",luasw.size(),context);
        notifyDataSetChanged();
    }
    void RemoveLuas2(int index){
        CanChange = false;
        luasw2.remove(index);
        Check.saveInteger(MycodeIndex+"l2",luasw2.size(),context);
        notifyDataSetChanged();
    }

}
