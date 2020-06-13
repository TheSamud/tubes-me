package com.pengembangsebelah.calculatorrab.ui.adapte;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pengembangsebelah.calculatorrab.R;
import com.pengembangsebelah.calculatorrab.model.PekerjaanProjek;
import com.pengembangsebelah.calculatorrab.model.baru.PekerjaanKoefisien;
import com.pengembangsebelah.calculatorrab.myutils.Angka;
import com.pengembangsebelah.calculatorrab.myutils.Check;
import com.pengembangsebelah.calculatorrab.ui.fragment.HargaSatuanFragment;

import java.util.List;

public class RecycleAdapterProjectInProject extends RecyclerView.Adapter<RecycleAdapterProjectInProject.ViewHolder> {
    public interface ListenerButton{
        void Edit(PekerjaanProjek pekerjaanProject);
        void Delete(PekerjaanProjek pekerjaanProject);
        void ItemSelect(PekerjaanProjek pekerjaanProject);
    }
    ListenerButton listenerButton;
    List<PekerjaanProjek> pekerjaanProject;
    public RecycleAdapterProjectInProject(List<PekerjaanProjek> pekerjaanProject){
        this.pekerjaanProject=pekerjaanProject;
    }
    Context context;
    public RecycleAdapterProjectInProject(List<PekerjaanProjek> pekerjaanProject,ListenerButton listenerButton,Context context){
        this.pekerjaanProject = pekerjaanProject;
        this.listenerButton = listenerButton;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_pekerjaan_list, parent, false);
        RecycleAdapterProjectInProject.ViewHolder viewHolder = new RecycleAdapterProjectInProject.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.title.setText(pekerjaanProject.get(position).title);
//        if(pekerjaanProject.get(position).) {
//            holder.total_biaya.setText(pekerjaanProject.get(position).totalBiaya);
//        }else {
            holder.total_biaya.setText("Rp."+ Angka.Rupiah((double) Check.getFloat(pekerjaanProject.get(position).id+pekerjaanProject.get(position).title,context, (float) 0)));
        //}

        if(listenerButton!=null){
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerButton.Delete(pekerjaanProject.get(position));
                }
            });
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerButton.Edit(pekerjaanProject.get(position));
                }
            });
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerButton.ItemSelect(pekerjaanProject.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return pekerjaanProject.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title,total_biaya;
        public TextView edit,delete;
        public View view;
        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.tv_nama_project);
            this.total_biaya = itemView.findViewById(R.id.tv_total_harga);
            this.edit = itemView.findViewById(R.id.edit_item);
            this.delete = itemView.findViewById(R.id.hapus_item);
            this.view = itemView;
        }
    }
}
