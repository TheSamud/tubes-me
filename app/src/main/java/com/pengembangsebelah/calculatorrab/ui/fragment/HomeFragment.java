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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.pengembangsebelah.calculatorrab.DetailActivity;
import com.pengembangsebelah.calculatorrab.MainActivity;
import com.pengembangsebelah.calculatorrab.R;
import com.pengembangsebelah.calculatorrab.model.ProjectList;
import com.pengembangsebelah.calculatorrab.splash;
import com.pengembangsebelah.calculatorrab.ui.adapte.RecycleAdapterProject;

import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private static final String ARG_SECTION_NUMBER = "section_number";

    public HomeFragment() {
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
    public static HomeFragment newInstance(int sectionNumber) {
        HomeFragment fragment = new HomeFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        init(rootView);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        GetData();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.button_setting_menu, menu);
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
                if(splash.instant.playMusic.IsPlaying()) {
                    splash.instant.playMusic.Stop();
                    item.setIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_volume_up_black_24dp));
                }else {
                    splash.instant.playMusic.Start();
                    item.setIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_volume_off_black_24dp));
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void init(View view){
        swipeRefreshLayout= view.findViewById(R.id.swift_home_ac);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView=view.findViewById(R.id.recycle_home_ac);
        recyclerView.setItemViewCacheSize(100);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        GetData();
        //Update();
        Valid(GetDataProject());
    }
    RecycleAdapterProject.OnClickAction onClickAction = new RecycleAdapterProject.OnClickAction() {
        @Override
        public void Edit(ProjectList projectList) {
            ((MainActivity)getActivity()).ShowDialogMakeProject(projectList, new MainActivity.AddSucces() {
                @Override
                public void Success(String s) {
                    onRefresh();
                }
            });
        }

        @Override
        public void Detail(ProjectList projectList) {
            Toast.makeText(getActivity(), "Detail", Toast.LENGTH_SHORT).show();
            DetailActivity.Init(getActivity(),projectList);
        }

        @Override
        public void Hapus(final ProjectList projectList) {
            ((MainActivity)getActivity()).ShowDialogNotif(new MainActivity.AddSucces() {
                @Override
                public void Success(String s) {
                    ((MainActivity)getActivity()).DeleteList(projectList);
                    GetData();
                }
            },true,"Anda ingin menghapus data project \""+projectList.Name+"\" ?");

        }
    };
    void Valid(List<ProjectList> data){
        swipeRefreshLayout.setRefreshing(false);
        RecycleAdapterProject recycleAdapterProject = new RecycleAdapterProject(data,getActivity(),onClickAction);
        recyclerView.setAdapter(recycleAdapterProject);
        recycleAdapterProject.notifyDataSetChanged();
    }

    void GetData(){
        swipeRefreshLayout.setRefreshing(true);
        Valid(GetDataProject());
//        FirebaseDatabase.getInstance().getReference().child(Kontent.ARG_RADIO_PROM).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterator<DataSnapshot> dataSnapshotIterator=dataSnapshot.getChildren().iterator();
//                List<PromotionData> schedulePrograms=new ArrayList<>();
//                while (dataSnapshotIterator.hasNext()){
//                    DataSnapshot dataSnapshot1=dataSnapshotIterator.next();
//                    PromotionData itm=dataSnapshot1.getValue(PromotionData.class);
//                    schedulePrograms.add(itm);
//                }
//                Collections.shuffle(schedulePrograms);
//                Valid(schedulePrograms);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });

    }
    @Override
    public void onRefresh() {
        GetData();
    }
}
