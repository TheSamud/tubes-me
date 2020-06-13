package com.pengembangsebelah.calculatorrab.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.pengembangsebelah.calculatorrab.DataKu;
import com.pengembangsebelah.calculatorrab.MainActivity;
import com.pengembangsebelah.calculatorrab.R;
import com.pengembangsebelah.calculatorrab.model.ProjectList;
import com.pengembangsebelah.calculatorrab.model.baru.AnalisaData;
import com.pengembangsebelah.calculatorrab.model.baru.KoefisienPk;
import com.pengembangsebelah.calculatorrab.model.baru.PekerjaanProyek;
import com.pengembangsebelah.calculatorrab.myutils.OnClickCustomListener;
import com.pengembangsebelah.calculatorrab.splash;
import com.pengembangsebelah.calculatorrab.ui.UpdateAnalisaActivity;
import com.pengembangsebelah.calculatorrab.ui.adapte.RecycleAdapterHargaSatuan;
import com.pengembangsebelah.calculatorrab.ui.adapte.RecycleAdapterList;

import java.util.List;
import java.util.Objects;

public class HargaSatuanFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    public static final String HARGA = "harga";
    private static final String ARG_SECTION_NUMBER = "section_number";

    public HargaSatuanFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HargaSatuanFragment newInstance(int sectionNumber) {
        HargaSatuanFragment fragment = new HargaSatuanFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    List<ProjectList> GetDataProject(){
        return ((MainActivity) Objects.requireNonNull(getActivity())).ReadProjectDatabase();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_harga_satuan, container, false);
        init(rootView);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.button_setting_menu2, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_project:
                ((MainActivity)getActivity()).ShowDialogMakeProject(new MainActivity.AddSucces() {
                    @Override
                    public void Success(String s) {
                        onRefresh();
                    }
                });
                return true;
            case R.id.new_project2:
                splash.instant.playMusic.Stop();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void init(View view){
        Button reset = view.findViewById(R.id.Reset);
        reset.setVisibility(View.GONE);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Reset", Toast.LENGTH_SHORT).show();
            }
        });
        Button save = view.findViewById(R.id.Save);
        save.setVisibility(View.GONE);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Save", Toast.LENGTH_SHORT).show();
            }
        });

        swipeRefreshLayout= view.findViewById(R.id.swift_harsat);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView=view.findViewById(R.id.recycle_harsat);
        recyclerView.setItemViewCacheSize(100);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        //GetData();
        //Update();
        //Valid(GetDataProject());
        Refresh();
    }
    void Refresh(){
        final List<AnalisaData> hallo = UpdateAnalisaActivity.AnalisaDatas(getActivity());
        RecycleAdapterHargaSatuan recycleAdapterList =new RecycleAdapterHargaSatuan(hallo,this.getContext());
        recyclerView.setAdapter(recycleAdapterList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        Refresh();
    }
}
