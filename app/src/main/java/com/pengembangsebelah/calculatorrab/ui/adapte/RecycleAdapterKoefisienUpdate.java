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

import java.util.List;

public class RecycleAdapterKoefisienUpdate extends RecyclerView.Adapter<RecycleAdapterKoefisienUpdate.ViewHolder> {
    public interface _EditText{
        void Change(EditText editText,String id);
    }

    List<AnalisaData> koefisien;
    _EditText editText;
    Context context;
    public RecycleAdapterKoefisienUpdate(List<AnalisaData> analisaData,Context context){
        this.koefisien = analisaData;
        this.context=context;
    }
    public RecycleAdapterKoefisienUpdate(List<AnalisaData> analisaData,_EditText editText){
        this.koefisien = analisaData;
        this.editText = editText;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_koefisien_edit, parent, false);
        RecycleAdapterKoefisienUpdate.ViewHolder viewHolder = new RecycleAdapterKoefisienUpdate.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.title.setText(koefisien.get(position).nama);
        Float asd = Check.getFloat(koefisien.get(position).id, context, koefisien.get(position).koef.get(0).floatValue());
        holder.editText.setText(asd.toString());
        holder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()){
                    Check.saveFloat(koefisien.get(position).id, Float.valueOf(s.toString()),context);
                    Log.d("MASMAS", "onTextChanged: MAS"+koefisien.get(position).id+""+s.toString());
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
