package com.pengembangsebelah.calculatorrab.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.pengembangsebelah.calculatorrab.model.baru.KoefisienPk;
import com.pengembangsebelah.calculatorrab.model.baru.PekerjaanProyek;
import com.pengembangsebelah.calculatorrab.myutils.OnClickCustomListener;
import com.pengembangsebelah.calculatorrab.splash;
import com.pengembangsebelah.calculatorrab.ui.UpdateAnalisaActivity;
import com.pengembangsebelah.calculatorrab.ui.adapte.RecycleAdapterList;

import java.util.List;
import java.util.Objects;

public class AnalisaActivity extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private static final String ARG_SECTION_NUMBER = "section_number";

    public AnalisaActivity() {
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
    public static AnalisaActivity newInstance(int sectionNumber) {
        AnalisaActivity fragment = new AnalisaActivity();
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
        View rootView = inflater.inflate(R.layout.activity_analisa, container, false);
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
        swipeRefreshLayout= view.findViewById(R.id.swift_home_anala);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView=view.findViewById(R.id.recycle_view_analisa);
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
        final List<PekerjaanProyek> hallo = DataKu.data;
        RecycleAdapterList recycleAdapterList = new RecycleAdapterList(hallo, new OnClickCustomListener.Analisa() {

            @Override
            public void OnClick(KoefisienPk koefisien) {
                UpdateAnalisaActivity.Init(getActivity(),koefisien);
            }
        },getActivity());
        recyclerView.setAdapter(recycleAdapterList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        Refresh();
    }
}
