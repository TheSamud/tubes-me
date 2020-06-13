package com.pengembangsebelah.calculatorrab.ui.adapte;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pengembangsebelah.calculatorrab.R;
import com.pengembangsebelah.calculatorrab.model.baru.AnalisaData;
import com.pengembangsebelah.calculatorrab.model.baru.KoefisienPk;
import com.pengembangsebelah.calculatorrab.myutils.Angka;
import com.pengembangsebelah.calculatorrab.myutils.Check;
import com.pengembangsebelah.calculatorrab.ui.fragment.HargaSatuanFragment;

import java.util.ArrayList;
import java.util.List;

public class RecycleKoefisienEdit extends RecyclerView.Adapter<RecycleKoefisienEdit.ViewHolder> {

    List<AnalisaData> analisaData;
    public List<Double> jumlahHargane;
    public List<Double> jumlahHargane1;
    public List<Double> jumlahHargane2;
    public List<Double> jumlahHargane3;
    public List<Double> jumlahHargane3fix;

    int yu = 0;
    public String name;


    public interface ListenerEdit{
        void OnChange(List<List<Double>> total);
    }

    ListenerEdit listenerEdit;
    public RecycleKoefisienEdit (List<AnalisaData> analisaData){
        this.analisaData = analisaData;
        jumlahHargane = new ArrayList<>();
    }
    Context context;
    public RecycleKoefisienEdit (List<AnalisaData> analisaData, ListenerEdit listenerEdit, Context context, String name){
        this.listenerEdit = listenerEdit;
        jumlahHargane = new ArrayList<>();
        jumlahHargane1 = new ArrayList<>();
        jumlahHargane2 = new ArrayList<>();
        jumlahHargane3 = new ArrayList<>();
        jumlahHargane3fix = new ArrayList<>();
        this.analisaData = analisaData;
        this.context = context;
        this.name = name;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_analisa_harga_satuan, parent, false);
        RecycleKoefisienEdit.ViewHolder viewHolder = new RecycleKoefisienEdit.ViewHolder(listItem);
        return viewHolder;
    }
    int ann =0;
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final AnalisaData analisa = analisaData.get(position);
        String code = analisa.nama;
        if (position > 0) {
            holder.title_linear.setVisibility(View.GONE);
        }

        if(code.equals("Perlengkapan")){
            holder.koefisien.setVisibility(View.VISIBLE);
            holder.hargasatuan.setVisibility(View.VISIBLE);
            holder.satuan.setVisibility(View.VISIBLE);
            final AnalisaData analisa2 = analisaData.get(position-1);
            String kof22 = analisa2.koef.get(0).toString();
            String harganow2 = String.valueOf(Check.getFloat(HargaSatuanFragment.HARGA + analisa2.id, context, analisa2.hargasatuan.floatValue()));

            holder.name.setText(analisa.nama);
            final String[] kof2 = analisa.id.split("#");


            final String kof = analisa.koef.get(0).toString();

            holder.koefisien.setText((Float.valueOf(kof)*100)+"%");

            String harganow;

            if (kof2.length >= 2) {
                harganow = String.valueOf(Check.getFloat(HargaSatuanFragment.HARGA + kof2[1], context, analisa.hargasatuan.floatValue()));
            } else {
                harganow = String.valueOf(Check.getFloat(HargaSatuanFragment.HARGA + analisa.id, context, analisa.hargasatuan.floatValue()));
            }

            holder.hargasatuan2.setText("Hitung Harga");
            holder.satuan.setText(analisa.satuan);
            double jumlah = Float.valueOf(kof) * Float.parseFloat(harganow2);
            jumlahHargane.add(jumlah);


            holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));
            holder.hargasatuan.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.toString().isEmpty()) {
                        return;
                    }
                    if (kof2.length >= 2) {
                        Check.saveFloat(HargaSatuanFragment.HARGA + kof2[1], Float.valueOf(s.toString()), context);
                    } else {
                        Check.saveFloat(HargaSatuanFragment.HARGA + analisa.id, Float.valueOf(s.toString()), context);
                    }

                    double jumlah = Check.getFloat(analisa.id, context, analisa.koef.get(0).floatValue()) * Float.parseFloat(s.toString());
                    jumlahHargane.set(position, jumlah);
                    holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));

                    Kampreto();
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // notifyDataSetChanged();
                }
            });
            holder.koefisien.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (s.toString().isEmpty()) {
                        return;
                    }
                    Check.saveFloat(analisa.id, Float.valueOf(s.toString()), context);
                    double jumlah = Float.parseFloat(s.toString()) * Check.getFloat(HargaSatuanFragment.HARGA + analisa.id, context, analisa.hargasatuan.floatValue());
                    jumlahHargane.set(position, jumlah);
                    holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));

                    Kampreto();

                }

                @Override
                public void afterTextChanged(Editable s) {
                    // notifyDataSetChanged();
                }
            });
            holder.koefisien.setEnabled(false);
            holder.hargasatuan.setVisibility(View.GONE);
            holder.hargasatuan2.setVisibility(View.VISIBLE);
            holder.hargasatuan2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Kampreto();
                    String harganow2 = String.valueOf(Check.getFloat(HargaSatuanFragment.HARGA + analisa2.id, context, analisa2.hargasatuan.floatValue()));
                    double jumlah = Float.valueOf(kof) * Float.parseFloat(harganow2);
                    holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));
                    jumlahHargane.set(position,jumlah);
                    Kampreto();
                }
            });


            Kampreto();


        }
        else if(code.equals("Begesting")||code.equals("Besi Beton")||code.equals("Beton")||code.equals(" ")){
            holder.tit2.setBackgroundColor(context.getResources().getColor(R.color.Greene));
            holder.name.setText(analisa.nama);
            holder.name.setTypeface(Typeface.DEFAULT_BOLD);
            holder.koefisien.setVisibility(View.GONE);
            holder.hargasatuan.setVisibility(View.GONE);
            holder.satuan.setVisibility(View.GONE);
            holder.jumlahharga.setVisibility(View.GONE);
            yu++;
            //jumlahHargane.add(0.00);
        }
        else {
            if(yu>0){
                if(yu==1){
                    holder.koefisien.setVisibility(View.VISIBLE);
                    holder.hargasatuan.setVisibility(View.VISIBLE);
                    holder.satuan.setVisibility(View.VISIBLE);
                    //Mulai
                    holder.name.setText(analisa.nama);
                    final String[] kof2 = analisa.id.split("#");


                    String kof = analisa.koef.get(0).toString();

                    holder.koefisien.setText(kof);

                    String harganow;

                    if (kof2.length >= 2) {
                        harganow = String.valueOf(Check.getFloat(HargaSatuanFragment.HARGA + kof2[1], context, analisa.hargasatuan.floatValue()));
                    } else {
                        harganow = String.valueOf(Check.getFloat(HargaSatuanFragment.HARGA + analisa.id, context, analisa.hargasatuan.floatValue()));
                    }

                    holder.hargasatuan.setText(harganow);
                    holder.satuan.setText(analisa.satuan);
                    double jumlah = Float.valueOf(kof) * Float.parseFloat(harganow);
                    jumlahHargane1.add(jumlah);


                    holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));
                    holder.hargasatuan.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.toString().isEmpty()) {
                                return;
                            }
                            if (kof2.length >= 2) {
                                Check.saveFloat(HargaSatuanFragment.HARGA + kof2[1], Float.valueOf(s.toString()), context);
                            } else {
                                Check.saveFloat(HargaSatuanFragment.HARGA + analisa.id, Float.valueOf(s.toString()), context);
                            }

                            double jumlah = Check.getFloat(analisa.id, context, analisa.koef.get(0).floatValue()) * Float.parseFloat(s.toString());
                            jumlahHargane1.set(position-1, jumlah);
                            holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));

                            Kampreto();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            // notifyDataSetChanged();
                        }
                    });
                    holder.koefisien.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            if (s.toString().isEmpty()) {
                                return;
                            }
                            Check.saveFloat(analisa.id, Float.valueOf(s.toString()), context);
                            double jumlah = Float.parseFloat(s.toString()) * Check.getFloat(HargaSatuanFragment.HARGA + analisa.id, context, analisa.hargasatuan.floatValue());
                            jumlahHargane1.set(position-1, jumlah);
                            holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));

                            Kampreto();

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            // notifyDataSetChanged();
                        }
                    });
                }
                else if(yu==2){
                    holder.koefisien.setVisibility(View.VISIBLE);
                    holder.hargasatuan.setVisibility(View.VISIBLE);
                    holder.satuan.setVisibility(View.VISIBLE);
                    //Mulai
                    holder.name.setText(analisa.nama);
                    final String[] kof2 = analisa.id.split("#");


                    String kof = analisa.koef.get(0).toString();

                    holder.koefisien.setText(kof);

                    String harganow;

                    if (kof2.length >= 2) {
                        harganow = String.valueOf(Check.getFloat(HargaSatuanFragment.HARGA + kof2[1], context, analisa.hargasatuan.floatValue()));
                    } else {
                        harganow = String.valueOf(Check.getFloat(HargaSatuanFragment.HARGA + analisa.id, context, analisa.hargasatuan.floatValue()));
                    }

                    holder.hargasatuan.setText(harganow);
                    holder.satuan.setText(analisa.satuan);
                    double jumlah = Float.valueOf(kof) * Float.parseFloat(harganow);
                    jumlahHargane2.add(jumlah);


                    holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));
                    holder.hargasatuan.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.toString().isEmpty()) {
                                return;
                            }
                            if (kof2.length >= 2) {
                                Check.saveFloat(HargaSatuanFragment.HARGA + kof2[1], Float.valueOf(s.toString()), context);
                            } else {
                                Check.saveFloat(HargaSatuanFragment.HARGA + analisa.id, Float.valueOf(s.toString()), context);
                            }

                            double jumlah = Check.getFloat(analisa.id, context, analisa.koef.get(0).floatValue()) * Float.parseFloat(s.toString());
                            jumlahHargane2.set(position-jumlahHargane1.size()-2, jumlah);
                            holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));

                            Kampreto();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            // notifyDataSetChanged();
                        }
                    });
                    holder.koefisien.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            if (s.toString().isEmpty()) {
                                return;
                            }
                            Check.saveFloat(analisa.id, Float.valueOf(s.toString()), context);
                            double jumlah = Float.parseFloat(s.toString()) * Check.getFloat(HargaSatuanFragment.HARGA + analisa.id, context, analisa.hargasatuan.floatValue());
                            jumlahHargane2.set(position-jumlahHargane1.size()-2, jumlah);
                            holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));

                            Kampreto();

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            // notifyDataSetChanged();
                        }
                    });
                }
                else if(yu==3){
                    holder.koefisien.setVisibility(View.VISIBLE);
                    holder.hargasatuan.setVisibility(View.VISIBLE);
                    holder.satuan.setVisibility(View.VISIBLE);
                    //Mulai
                    holder.name.setText(analisa.nama);
                    final String[] kof2 = analisa.id.split("#");


                    String kof = analisa.koef.get(0).toString();

                    holder.koefisien.setText(kof);

                    String harganow;

                    if (kof2.length >= 2) {
                        harganow = String.valueOf(Check.getFloat(HargaSatuanFragment.HARGA + kof2[1], context, analisa.hargasatuan.floatValue()));
                    } else {
                        harganow = String.valueOf(Check.getFloat(HargaSatuanFragment.HARGA + analisa.id, context, analisa.hargasatuan.floatValue()));
                    }

                    holder.hargasatuan.setText(harganow);
                    holder.satuan.setText(analisa.satuan);
                    double jumlah = Float.valueOf(kof) * Float.parseFloat(harganow);
                    jumlahHargane3.add(jumlah);
                    jumlahHargane3fix.add(Double.valueOf(Float.parseFloat(harganow)));


                    holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));
                    holder.hargasatuan.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.toString().isEmpty()) {
                                return;
                            }
                            if (kof2.length >= 2) {
                                Check.saveFloat(HargaSatuanFragment.HARGA + kof2[1], Float.valueOf(s.toString()), context);
                            } else {
                                Check.saveFloat(HargaSatuanFragment.HARGA + analisa.id, Float.valueOf(s.toString()), context);
                            }

                            double jumlah = Check.getFloat(analisa.id, context, analisa.koef.get(0).floatValue()) * Float.parseFloat(s.toString());
                            jumlahHargane3.set(position-(jumlahHargane1.size()+jumlahHargane2.size())-3, jumlah);
                            holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));

                            Kampreto();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            // notifyDataSetChanged();
                        }
                    });
                    holder.koefisien.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            if (s.toString().isEmpty()) {
                                return;
                            }
                            Check.saveFloat(analisa.id, Float.valueOf(s.toString()), context);
                            double jumlah = Float.parseFloat(s.toString()) * Check.getFloat(HargaSatuanFragment.HARGA + analisa.id, context, analisa.hargasatuan.floatValue());
                            jumlahHargane3.set(position-(jumlahHargane1.size()+jumlahHargane2.size())-3, jumlah);
                            holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));

                            Kampreto();

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            // notifyDataSetChanged();
                        }
                    });
                }
                else if(yu==4){
                    holder.koefisien.setVisibility(View.VISIBLE);
                    holder.hargasatuan.setVisibility(View.VISIBLE);
                    holder.satuan.setVisibility(View.VISIBLE);
                    //Mulai
                    holder.name.setText(analisa.nama);
                    final String[] kof2 = analisa.id.split("#");


                    final String kof = analisa.koef.get(0).toString();

                    holder.koefisien.setText(kof);
                    Log.d("KASAN"," "+kof);

                    String harganow;

                    if (kof2.length >= 2) {
                        harganow = String.valueOf(Check.getFloat(HargaSatuanFragment.HARGA + kof2[1], context, analisa.hargasatuan.floatValue()));
                    } else {
                        harganow = String.valueOf(Check.getFloat(HargaSatuanFragment.HARGA + analisa.id, context, analisa.hargasatuan.floatValue()));
                    }

                    holder.satuan.setText(analisa.satuan);
                    final double[] jumlah = {0.00};
                    ann++;
                    if(ann==0){
                        jumlah[0] = Float.valueOf(kof) * Float.parseFloat(harganow);
                    }else if(ann==1){
                        holder.jumlahharga.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jumlah[0] = 0;
                                Double s = 0.00;
                                for (int u =0;u<jumlahHargane1.size();u++){
                                    s += jumlahHargane1.get(u);
                                }
                                jumlah[0] += Float.valueOf(kof) *s;
                                holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah[0]));
                            }
                        });
                        Double s = 0.00;
                        for (int u =0;u<jumlahHargane1.size();u++){
                            s += jumlahHargane1.get(u);
                        }
                        jumlah[0] += Float.valueOf(kof) *s;
                        holder.hargasatuan.setText("Rp " + Angka.Rupiah(s));
                    }else if(ann==2){
                        holder.jumlahharga.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jumlah[0] = 0;
                                Double s = 0.00;
                                for (int u =0;u<jumlahHargane2.size();u++){
                                    s += jumlahHargane2.get(u);
                                }
                                jumlah[0] += (Float.valueOf(kof) *(s/10));
                                holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah[0]));
                            }
                        });
                        Double s = 0.00;
                        for (int u =0;u<jumlahHargane2.size();u++){
                            s += jumlahHargane2.get(u);
                        }
                        jumlah[0] += (Float.valueOf(kof) *(s/10));
                        holder.hargasatuan.setText("Rp " + Angka.Rupiah((s/10)));
                    }else if(ann==3){
                        holder.jumlahharga.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jumlah[0] = 0;
                                Double s = 0.00;
                                for (int u =0;u<jumlahHargane3.size();u++){
                                    s += jumlahHargane3.get(u);
                                }
                                jumlah[0] += Float.valueOf(kof) *s;
                                holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah[0]));
                            }
                        });
                        Double s = 0.00;
                        for (int u =0;u<jumlahHargane3.size();u++){
                            s += jumlahHargane3.get(u);
                        }
                        jumlah[0] += Float.valueOf(kof) *s;
                        holder.hargasatuan.setText("Rp " + Angka.Rupiah(s));
                    }else if(ann==4){
                        holder.jumlahharga.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jumlah[0] = 0;
                                Double s = jumlahHargane3.get(1);
                                jumlah[0] += (Float.valueOf(kof)*(s*4));
                                holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah[0]));
                            }
                        });
                        Double s = jumlahHargane3fix.get(1);

                        jumlah[0] += ((Float.valueOf(kof) *s)*4);
                        holder.hargasatuan.setText("Rp " + Angka.Rupiah(jumlah[0]));
                    }
                    jumlahHargane.add(jumlah[0]);
                    holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah[0]));
                    holder.koefisien.setEnabled(false);
                    holder.hargasatuan.setEnabled(false);
                }
            }
            else{
                holder.koefisien.setVisibility(View.VISIBLE);
                holder.hargasatuan.setVisibility(View.VISIBLE);
                holder.satuan.setVisibility(View.VISIBLE);
                //Mulai
                holder.name.setText(analisa.nama);
                final String[] kof2 = analisa.id.split("#");


                String kof = analisa.koef.get(0).toString();

                holder.koefisien.setText(kof);

                String harganow;

                if (kof2.length >= 2) {
                    harganow = String.valueOf(Check.getFloat(HargaSatuanFragment.HARGA + kof2[1], context, analisa.hargasatuan.floatValue()));
                } else {
                    harganow = String.valueOf(Check.getFloat(HargaSatuanFragment.HARGA + analisa.id, context, analisa.hargasatuan.floatValue()));
                }

                holder.hargasatuan.setText(harganow);
                holder.satuan.setText(analisa.satuan);
                double jumlah = Float.valueOf(kof) * Float.parseFloat(harganow);
                jumlahHargane.add(jumlah);


                holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));
                holder.hargasatuan.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.toString().isEmpty()) {
                            return;
                        }
                        if (kof2.length >= 2) {
                            Check.saveFloat(HargaSatuanFragment.HARGA + kof2[1], Float.valueOf(s.toString()), context);
                        } else {
                            Check.saveFloat(HargaSatuanFragment.HARGA + analisa.id, Float.valueOf(s.toString()), context);
                        }

                        double jumlah = Check.getFloat(analisa.id, context, analisa.koef.get(0).floatValue()) * Float.parseFloat(s.toString());
                        jumlahHargane.set(position, jumlah);
                        holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));

                       Kampreto();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // notifyDataSetChanged();
                    }
                });
                holder.koefisien.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        if (s.toString().isEmpty()) {
                            return;
                        }
                        Check.saveFloat(analisa.id, Float.valueOf(s.toString()), context);
                        double jumlah = Float.parseFloat(s.toString()) * Check.getFloat(HargaSatuanFragment.HARGA + analisa.id, context, analisa.hargasatuan.floatValue());
                        jumlahHargane.set(position, jumlah);
                        holder.jumlahharga.setText("Rp " + Angka.Rupiah(jumlah));

                        Kampreto();

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // notifyDataSetChanged();
                    }
                });
            }
        }

        Kampreto();

    }

    void Kampreto(){
        if (listenerEdit != null) {
//            if(yu>1){
//                List<List<Double>> jus = new ArrayList<>();
//                jus.add(jumlahHargane1);
//                jus.add(jumlahHargane2);
//                jus.add(jumlahHargane3);
//                listenerEdit.OnChange(jus);
//            }else{
                List<List<Double>> jus = new ArrayList<>();
                jus.add(jumlahHargane);
                listenerEdit.OnChange(jus);
          //  }
        }
    }

    @Override
    public int getItemCount() {
        return analisaData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout title_linear,tit2;
        public TextView name,satuan,jumlahharga,hargasatuan2;
        public EditText koefisien,hargasatuan;
        public ViewHolder(View itemView) {
            super(itemView);
            title_linear = itemView.findViewById(R.id.title_linear);
            tit2 = itemView.findViewById(R.id.hujua);
            name = itemView.findViewById(R.id.nama);
            satuan = itemView.findViewById(R.id.satuan);
            hargasatuan = itemView.findViewById(R.id.hargasatuan);
            hargasatuan2 = itemView.findViewById(R.id.hargasatuan2);
            jumlahharga = itemView.findViewById(R.id.jumlahharga);
            koefisien = itemView.findViewById(R.id.koefisien);
        }
    }
}
