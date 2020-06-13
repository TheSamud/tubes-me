package com.pengembangsebelah.calculatorrab.ui.adapte;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pengembangsebelah.calculatorrab.R;
import com.pengembangsebelah.calculatorrab.model.baru.KoefisienPk;
import com.pengembangsebelah.calculatorrab.model.baru.PekerjaanKoefisien;
import com.pengembangsebelah.calculatorrab.model.baru.PekerjaanProyek;
import com.pengembangsebelah.calculatorrab.myutils.OnClickCustomListener;

import java.util.List;

public class RecycleAdapterList extends RecyclerView.Adapter<RecycleAdapterList.ViewHolder> {
    class PekerjaanKoefisienAdapter extends RecyclerView.Adapter<ViewHolder>{
        List<PekerjaanKoefisien> pekerjaanKoefisien;
        Activity _activity;
        public PekerjaanKoefisienAdapter(List<PekerjaanKoefisien> pekerjaanKoefisien,Activity _activity){
            this.pekerjaanKoefisien = pekerjaanKoefisien;
            this._activity = _activity;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.item_analisa_add, parent, false);
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            int no = position+1;
            holder.textView.setText(no+"."+pekerjaanKoefisien.get(position).title);
            holder.textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.textView.setTextColor(Color.BLACK);

            holder.linearLayout.setBackgroundColor(Color.parseColor("#708DFD68"));

            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
            holder.rec.setLayoutManager(linearLayoutManager);
            holder.rec.setAdapter(new KoefisienPkAdapte(pekerjaanKoefisien.get(position).koefisienPkList,_activity));
        }

        @Override
        public int getItemCount() {
            return pekerjaanKoefisien.size();
        }
    }
    class KoefisienPkAdapte extends RecyclerView.Adapter<ViewHolder>{
        List<KoefisienPk> koefisienPkList;
        Activity __activity;
        public KoefisienPkAdapte(List<KoefisienPk> koefisienPkList,Activity __activity){
            this.koefisienPkList = koefisienPkList;
            this.__activity = __activity;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.item_analisa_add, parent, false);
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.textView.setText(koefisienPkList.get(position).a);
            holder.textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.textView.setTextColor(Color.BLACK);
            holder.linearLayout.setBackgroundColor(Color.WHITE);
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.OnClick(koefisienPkList.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return koefisienPkList.size();
        }
    }
    List<PekerjaanProyek> data;
    OnClickCustomListener.Analisa onClickListener;
    Activity activity;
    public RecycleAdapterList(List<PekerjaanProyek> data, OnClickCustomListener.Analisa analisa,Activity activity) {
        this.data = data;
        this.onClickListener = analisa;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_analisa_add, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(data.get(position).title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onClickListener.OnClick(data.get(position));
            }
        });

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(activity);
        holder.rec.setLayoutManager(linearLayoutManager);
        holder.rec.setAdapter(new PekerjaanKoefisienAdapter(data.get(position).pekerjaanKoefisiens,activity));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RecyclerView rec;
        public LinearLayout linearLayout;
        public View item;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.ed_1);
            this.rec = itemView.findViewById(R.id.rek1);
            this.linearLayout = itemView.findViewById(R.id.linearlayout);
            this.item = itemView;
        }
    }
}
