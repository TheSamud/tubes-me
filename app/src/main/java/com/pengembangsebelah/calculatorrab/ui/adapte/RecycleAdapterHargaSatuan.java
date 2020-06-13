package com.pengembangsebelah.calculatorrab.ui.adapte;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pengembangsebelah.calculatorrab.R;
import com.pengembangsebelah.calculatorrab.model.baru.AnalisaData;
import com.pengembangsebelah.calculatorrab.myutils.Check;
import com.pengembangsebelah.calculatorrab.ui.fragment.HargaSatuanFragment;

import java.util.List;

public class RecycleAdapterHargaSatuan extends RecyclerView.Adapter<RecycleAdapterHargaSatuan.ViewHolder> {
    public interface _EditText{
        void Change(EditText editText, String id);
    }
    Context context;
    List<AnalisaData> koefisien;
    _EditText editText;
    public RecycleAdapterHargaSatuan(List<AnalisaData> analisaData, Context context){
        this.koefisien = analisaData;
        this.context=context;
    }
    public RecycleAdapterHargaSatuan(List<AnalisaData> analisaData, _EditText editText){
        this.koefisien = analisaData;
        this.editText = editText;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_koefisien_edit, parent, false);
        RecycleAdapterHargaSatuan.ViewHolder viewHolder = new RecycleAdapterHargaSatuan.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.title.setText(koefisien.get(position).nama);
        String harganow = String.valueOf(Check.getFloat(HargaSatuanFragment.HARGA+koefisien.get(position).id,context,koefisien.get(position).hargasatuan.floatValue()));
        holder.editText.setText(harganow);
        holder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()) {
                    Check.saveFloat(HargaSatuanFragment.HARGA+koefisien.get(position).id, Float.valueOf(s.toString()), context);
                    Log.d("NGANU", "analisaDatas: "+HargaSatuanFragment.HARGA+koefisien.get(position).id);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.satuan.setText(koefisien.get(position).satuan);
        if(editText!=null) {
            editText.Change(holder.editText, String.valueOf(koefisien.get(position).id));
        }
    }

    @Override
    public int getItemCount() {
        return koefisien.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title,satuan;
        public EditText editText;
        public ViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.edt_title);
            this.editText = itemView.findViewById(R.id.edt_text);
            this.satuan = itemView.findViewById(R.id.satuan_o);
        }
    }
}
