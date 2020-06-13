package com.pengembangsebelah.calculatorrab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.pengembangsebelah.calculatorrab.model.PekerjaanProjek;
import com.pengembangsebelah.calculatorrab.model.baru.AnalisaData;
import com.pengembangsebelah.calculatorrab.model.baru.KoefisienPk;
import com.pengembangsebelah.calculatorrab.model.baru.PekerjaanKoefisien;
import com.pengembangsebelah.calculatorrab.model.baru.Volume;
import com.pengembangsebelah.calculatorrab.myutils.Angka;
import com.pengembangsebelah.calculatorrab.myutils.Check;
import com.pengembangsebelah.calculatorrab.myutils.Satuan;
import com.pengembangsebelah.calculatorrab.ui.UpdateAnalisaActivity;
import com.pengembangsebelah.calculatorrab.ui.adapte.RecycleKoefisienEdit;
import com.pengembangsebelah.calculatorrab.ui.adapte.RecycleVolumeEdt;
import com.pengembangsebelah.calculatorrab.ui.fragment.HargaSatuanFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EditPekerjaan extends AppCompatActivity {
    public static String POLUME = "volumezd";
    public static String HARGASATUAN = "HARSAT";
    public static String PERCENTAGE = "Percentage";



    static PekerjaanProjek _pekerjaanProject;
    Toolbar mActionBarToolbar;
    RecyclerView recyclerViewKoefisien,recyclevolume;
    TextView jumanal,profite,hsp,hspvol,hasil,hasiljumper;

    EditText editPercentage;
    Double _percenta,hartot,vol;
    Float getPercentage(){
        Float h = Check.getFloat(PERCENTAGE+_pekerjaanProject.id+_pekerjaanProject.title,this,0f);
        if(h==null){
            return 0f;
        }else {
            return h;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pekerjaan);
        _percenta= Double.valueOf(getPercentage()/100);
        hartot = Double.valueOf(0);
        vol = Double.valueOf(1);

        jumanal = findViewById(R.id.tv_hasil_jum);
        hsp = findViewById(R.id.edt_hsat);
        hspvol = findViewById(R.id.edt_hsatvol);
        hasil = findViewById(R.id.tv_hasil);
        hasiljumper = findViewById(R.id.tv_hasil_jumper);

        hasil.setText(vol+"");
        profite =findViewById(R.id.edt_profit);
        jumanal.setText("Rp "+Angka.Rupiah(0));
        editPercentage = findViewById(R.id.edt_percenta);
        editPercentage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty()){
                    return;
                }
                Check.saveFloat(PERCENTAGE+_pekerjaanProject.id+_pekerjaanProject.title, Float.valueOf(s.toString()),EditPekerjaan.this);
                _percenta = (Double.valueOf(s.toString())/100);
                profite.setText("Rp "+Angka.Rupiah(_percenta*hartot));
                hsp.setText("Rp "+Angka.Rupiah(hartot+(_percenta*hartot)));
                Check.saveFloat(HARGASATUAN+_pekerjaanProject.id+_pekerjaanProject.title,(float) (hartot+(_percenta*hartot)),EditPekerjaan.this);
                hspvol.setText("Rp "+Angka.Rupiah(vol*(hartot+(_percenta*hartot))));
                hasiljumper.setText("Rp "+Angka.Rupiah(vol*(hartot+(_percenta*hartot))));
                Hitung();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editPercentage.setText(""+getPercentage());
        profite.setText("Rp "+Angka.Rupiah(_percenta*0));


        mActionBarToolbar = (Toolbar) findViewById(R.id.confirm_order_toolbar_layoute);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(_pekerjaanProject.title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclevolume = findViewById(R.id.recycleVolume);
        recyclevolume.setHasFixedSize(true);
        recyclevolume.setLayoutManager(new LinearLayoutManager(this));



        recyclerViewKoefisien = findViewById(R.id.recycle_koefisien);
        recyclerViewKoefisien.setHasFixedSize(true);
        recyclerViewKoefisien.setLayoutManager(new LinearLayoutManager(this));
        pekerjaanKoefisien = new KoefisienPk();

       // Log.d("DOIK", "onCreate: "+_pekerjaanProject.idKoef+" "+pekerjaanKoefisien.id);
        RecycleKoefisienEdit recycleKoefisienEdit = new RecycleKoefisienEdit(analisaDatas(_pekerjaanProject.idKoef), new RecycleKoefisienEdit.ListenerEdit() {
            @Override
            public void OnChange(List<List<Double>> total) {
                hartot = Double.valueOf(0);
                Log.d("MACITO", "OnChange: "+total.size());
                if(total.size()>1){

                }else {
                    for (int cc = 0; cc < total.get(0).size(); cc++) {
                        Log.d("MACITO", "OnChange: 45 "+total.get(0).get(cc));
                        hartot += total.get(0).get(cc);
                    }
                }


                jumanal.setText("Rp "+Angka.Rupiah(hartot));
                profite.setText("Rp "+Angka.Rupiah(_percenta*hartot));
                hsp.setText("Rp "+Angka.Rupiah(hartot+(_percenta*hartot)));
                Check.saveFloat(HARGASATUAN+_pekerjaanProject.id+_pekerjaanProject.title,(float) (hartot+(_percenta*hartot)),EditPekerjaan.this);
                hspvol.setText("Rp "+Angka.Rupiah(vol*(hartot+(_percenta*hartot))));
                hasiljumper.setText("Rp "+Angka.Rupiah(vol*(hartot+(_percenta*hartot))));
                double ssd = (vol*(hartot+(_percenta*hartot)));
                Check.saveFloat(_pekerjaanProject.id+_pekerjaanProject.title, (float) ssd,getApplicationContext());
            }
        },this,pekerjaanKoefisien.id);
        recyclerViewKoefisien.setAdapter(recycleKoefisienEdit);
        RecycleVolumeEdt recycleVolumeEdt = new RecycleVolumeEdt(VolCod+"%56%"+_pekerjaanProject.id+_pekerjaanProject.title, new RecycleVolumeEdt.ListenerEdit() {
            @Override
            public void OnChange(List<Float> volume) {
                float guy = 0;
                for (int uy=0;uy<volume.size();uy++){
                    guy += volume.get(uy);
                }
                vol = Double.valueOf(guy);

                hasil.setText(String.format("%.02f", vol)+ Satuan.get(_pekerjaanProject.titlemain,_pekerjaanProject.title));
                Check.saveFloat(POLUME+_pekerjaanProject.id+_pekerjaanProject.title,guy,EditPekerjaan.this);
                Hitung();
            }
        },this);
        String[] guyumu = _pekerjaanProject.idKoef.split("#");
        if(guyumu.length>1){
            Log.d("KASDU", "onCreate: "+guyumu[1]);
            float esemanmu = Float.valueOf(guyumu[1]);
            recycleVolumeEdt.SetSpesial(esemanmu);
        }

        recyclevolume.setAdapter(recycleVolumeEdt);
        InitEDT();
    }
    static String VolCod;
    KoefisienPk pekerjaanKoefisien;
    List<AnalisaData> analisaDatas(String id){
        List<AnalisaData> analisaDatas2 =new ArrayList<>();
        if(pekerjaanKoefisien.id!=null){
            analisaDatas2.addAll(Objects.requireNonNull(UpdateAnalisaActivity.AnalisaDatas(pekerjaanKoefisien.analisa, this,analisaDatas2)));
        }

        List<AnalisaData> analisaDatasada =new ArrayList<>();
        for (int a=0;a<DataKu.data.size();a++){
            List<PekerjaanKoefisien> _pekerjaanKoefisien = DataKu.data.get(a).pekerjaanKoefisiens;
            for (int b=0;b<_pekerjaanKoefisien.size();b++){
                List<KoefisienPk> _koe = _pekerjaanKoefisien.get(b).koefisienPkList;
                for(int c=0;c<_koe.size();c++){
                    if(_koe.get(c).id.equals(id)){
                        pekerjaanKoefisien = _koe.get(c);

                        List<AnalisaData> purge = new ArrayList<>();

                        for (int y=0;y<_koe.get(c).analisa2.size();y++){
                            String[] fd = _koe.get(c).analisa2.get(y).id.split("#");
                            Double harga = 0.0;
                            Double hargaw = 0.0;
                            if(fd.length>=2){
                                hargaw = (double) Check.getFloat(HargaSatuanFragment.HARGA+fd[1],this,0f);
                                if(hargaw == 0){
                                    final List<AnalisaData> hallo = UpdateAnalisaActivity.AnalisaDatas(EditPekerjaan.this);
                                    for (int ae = 0; ae<hallo.size();ae++){
                                        if(hallo.get(ae).id.equals(fd[1])){
                                            harga = hallo.get(ae).hargasatuan;
                                            AnalisaData anal = new AnalisaData(
                                                    _koe.get(c).analisa2.get(y).id,
                                                    _koe.get(c).analisa2.get(y).nama,
                                                    _koe.get(c).analisa2.get(y).satuan,
                                                    _koe.get(c).analisa2.get(y).koef,
                                                    harga
                                            );
                                            purge.add(anal);
                                            Log.d("NGANUE", "analisaDatas: "+fd.length+" "+fd[1]+" "+hallo.get(ae).id+" "+harga);
                                        }
                                    }

                                }
                                else {
                                    harga = hargaw;
                                    AnalisaData anal = new AnalisaData(
                                            _koe.get(c).analisa2.get(y).id,
                                            _koe.get(c).analisa2.get(y).nama,
                                            _koe.get(c).analisa2.get(y).satuan,
                                            _koe.get(c).analisa2.get(y).koef,
                                            harga
                                    );
                                    purge.add(anal);
                                }
                            }
                            else {
                                harga = _koe.get(c).analisa2.get(y).hargasatuan;
                                AnalisaData anal = new AnalisaData(
                                        _koe.get(c).analisa2.get(y).id,
                                        _koe.get(c).analisa2.get(y).nama,
                                        _koe.get(c).analisa2.get(y).satuan,
                                        _koe.get(c).analisa2.get(y).koef,
                                        harga
                                );
                                purge.add(anal);
                            }

                        }

                       // analisaDatasada.addAll(_koe.get(c).analisa2);
                        VolCod = _koe.get(c).volume;
                        analisaDatasada.addAll(purge);
                    }
                }
            }
        }

        return analisaDatasada;
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Hitung();
        }
    };
    void InitEDT(){
//        EditText lebar = findViewById(R.id.edt_lebar);
//        EditText tinggi = findViewById(R.id.edt_tinggi);
//        EditText panjang = findViewById(R.id.edt_panjang);
//        EditText jumlah = findViewById(R.id.edt_jumlah);

//        lebar.setText("1");
//        tinggi.setText("1");
//        panjang.setText("1");
//        jumlah.setText("1");
//
//        lebar.addTextChangedListener(watcher);
//        tinggi.addTextChangedListener(watcher);
//        panjang.addTextChangedListener(watcher);
//        jumlah.addTextChangedListener(watcher);
    }

    void Hitung(){
//        EditText lebar = findViewById(R.id.edt_lebar);
//        EditText tinggi = findViewById(R.id.edt_tinggi);
//        EditText panjang = findViewById(R.id.edt_panjang);
//        EditText jumlah = findViewById(R.id.edt_jumlah);

//        float volume = GetWae(lebar)*GetWae(tinggi)*GetWae(panjang);
//        float volumetotal = volume*GetWae(jumlah) ;
//        vol = (double) volumetotal;

      //  hasil.setText(volumetotal+"");
        hspvol.setText("Rp "+Angka.Rupiah(vol*(hartot+(_percenta*hartot))));
        hasiljumper.setText("Rp "+Angka.Rupiah(vol*(hartot+(_percenta*hartot))));
        double ssd = (vol*(hartot+(_percenta*hartot)));

        Check.saveFloat(_pekerjaanProject.id+_pekerjaanProject.title, (float) ssd,this);

    }

    float GetWae(EditText editText){
        if(editText.getText().toString().isEmpty()){
            return 0;
        }else {
            return Float.valueOf(editText.getText().toString());
        }
    }

    public static void Init(Activity activity, PekerjaanProjek pekerjaanProject){
        _pekerjaanProject = pekerjaanProject;
        Intent i = new Intent(activity,EditPekerjaan.class);
        activity.startActivity(i);
    }
}
