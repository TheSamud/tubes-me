package com.pengembangsebelah.calculatorrab.ui.adapte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pengembangsebelah.calculatorrab.R;
import com.pengembangsebelah.calculatorrab.model.ProjectList;
import com.pengembangsebelah.calculatorrab.myutils.Angka;
import com.pengembangsebelah.calculatorrab.myutils.Check;
import com.pengembangsebelah.calculatorrab.myutils.OnClickCustomListener;

import java.util.List;

class RecycleAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
    ImageView preview;
    TextView namaproject,location,price,detail,edit,hapus;
    OnClickCustomListener.Item clik;

    public RecycleAdapterHolder(@NonNull View itemView) {
        super(itemView);
        preview = itemView.findViewById(R.id.item_home_preview);
        namaproject = itemView.findViewById(R.id.tv_nama_project);
        location = itemView.findViewById(R.id.tv_lokasi);
        price = itemView.findViewById(R.id.tv_total_harga);
        detail = itemView.findViewById(R.id.detail_item);
        edit = itemView.findViewById(R.id.edit_item);
        hapus = itemView.findViewById(R.id.hapus_item);
        itemView.findViewById(R.id.item_layote).setOnClickListener(this);
    }

    public void SetOnClick(OnClickCustomListener.Item onclickListener){
        this.clik=onclickListener;
    }

    @Override
    public void onClick(View v) {
        clik.OnClick();
    }

    @Override
    public boolean onLongClick(View v) {
        clik.LongClick();
        return true;
    }
}

public class RecycleAdapterProject extends RecyclerView.Adapter<RecycleAdapterHolder>{
    public interface OnClickAction{
        void Edit(ProjectList projectList);
        void Detail(ProjectList projectList);
        void Hapus(ProjectList projectList);
    }

    OnClickAction onClickAction;
    List<ProjectList> items;
    Context context;
    public RecycleAdapterProject(List<ProjectList> items, Context context,OnClickAction onClickAction) {
        this.items=items;
        this.context=context;
        this.onClickAction = onClickAction;
    }

    String GetRupiah(long val){
        return "Rp."+ Angka.Rupiah(val);
    }

    @NonNull
    @Override
    public RecycleAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_project_list,viewGroup,false);
        return new RecycleAdapterHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final RecycleAdapterHolder adapterHolder, final int i) {
        adapterHolder.namaproject.setText(items.get(i).Name);
        adapterHolder.location.setText(items.get(i).Location);
        adapterHolder.price.setText(GetRupiah((long) Check.getFloat(items.get(i).ID+items.get(i).idKoef,context,(float)items.get(i).Price)));

        adapterHolder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAction.Hapus(items.get(i));
            }
        });

        adapterHolder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAction.Detail(items.get(i));
            }
        });

        adapterHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAction.Edit(items.get(i));
            }
        });
        adapterHolder.SetOnClick(new OnClickCustomListener.Item() {
            @Override
            public void OnClick() {
                onClickAction.Detail(items.get(i));
            }

            @Override
            public void LongClick() {
                Toast.makeText(context, items.get(i).Name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(items!=null)
            return items.size();
        else return 0;
    }
}
