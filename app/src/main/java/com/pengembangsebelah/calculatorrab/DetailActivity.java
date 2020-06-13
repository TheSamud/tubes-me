package com.pengembangsebelah.calculatorrab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.pengembangsebelah.calculatorrab.model.PekerjaanProjek;
import com.pengembangsebelah.calculatorrab.model.ProjectList;
import com.pengembangsebelah.calculatorrab.model.baru.KoefisienPk;
import com.pengembangsebelah.calculatorrab.model.baru.PekerjaanKoefisien;
import com.pengembangsebelah.calculatorrab.myutils.Check;
import com.pengembangsebelah.calculatorrab.myutils.db.sqlite.ReaderContract;
import com.pengembangsebelah.calculatorrab.myutils.db.sqlite.ReaderHelperDb;
import com.pengembangsebelah.calculatorrab.myutils.dialogmaker.DialogMakerHandler;
import com.pengembangsebelah.calculatorrab.myutils.dialogmaker.DialogMakerUtils;
import com.pengembangsebelah.calculatorrab.myutils.export.ExportData;
import com.pengembangsebelah.calculatorrab.ui.adapte.RecycleAdapterProjectInProject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    static ProjectList _projectList;
    Toolbar mActionBarToolbar;
    FloatingActionButton floatingActionButton;
    ReaderHelperDb databaseProject;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mActionBarToolbar = (Toolbar) findViewById(R.id.confirm_order_toolbar_layout);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(_projectList.Name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseProject = new ReaderHelperDb(this);
        recyclerView = findViewById(R.id.recycle_detail_ac);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        floatingActionButton = findViewById(R.id.floating_add_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogMakeProject(new MainActivity.AddSucces() {
                    @Override
                    public void Success(String s) {
                        Toast.makeText(DetailActivity.this, "Success Added", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        SetRefreshData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SetRefreshData();
    }

    void SetRefreshData(){
        Float totals = 0f;
        for (int ir=0;ir<ReadPekerjaanProject(_projectList).size();ir++){
            float ma = Check.getFloat(ReadPekerjaanProject(_projectList).get(ir).id+ReadPekerjaanProject(_projectList).get(ir).title,this, (float) 0);
            totals+=ma;
        }
        Check.saveFloat(_projectList.ID+_projectList.idKoef,totals,this);
        RecycleAdapterProjectInProject adapter = new RecycleAdapterProjectInProject(ReadPekerjaanProject(_projectList), new RecycleAdapterProjectInProject.ListenerButton() {
            @Override
            public void Edit(PekerjaanProjek pekerjaanProject) {
                EditPekerjaan.Init(DetailActivity.this,pekerjaanProject);
            }

            @Override
            public void Delete(final PekerjaanProjek pekerjaanProject) {
                    final DialogMakerHandler dialogMakerHandler = new DialogMakerHandler() {
                        @Override
                        public void handler(View view) {
                            ImageView img = view.findViewById(R.id.image_notifa);
                            if(false){img.setVisibility(View.GONE);}
                            TextView textView = view.findViewById(R.id.text_notifa);
                            textView.setText("Hapus "+pekerjaanProject.title+"?");
                            TextView btn1 = view.findViewById(R.id.btn_1);
                            btn1.setText("Ya");
                            TextView btn2 = view.findViewById(R.id.btn_2);
                            btn2.setText("Tidak");

      //                      final TextInputEditText namaProject = view.findViewById(R.id.input_name_project);
    //                        final TextInputEditText location = view.findViewById(R.id.input_location);
//                            namaProject.setVisibility(View.GONE);
  //                          location.setVisibility(View.GONE);
                            btn1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialogMakerUtils.Dismiss();
                                    DeleteList(pekerjaanProject);
                                    SetRefreshData();
                                }
                            });
                            btn2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialogMakerUtils.Dismiss();
                                    SetRefreshData();
                                }
                            });
                        }
                    };

                    dialogMakerUtils = new DialogMakerUtils(DetailActivity.this,R.layout.dialog_exit,dialogMakerHandler);
                    dialogMakerUtils.Show();

            }

            @Override
            public void ItemSelect(PekerjaanProjek pekerjaanProject) {
                Toast.makeText(DetailActivity.this, ""+pekerjaanProject.title, Toast.LENGTH_SHORT).show();
            }
        },this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.button_setting_menu_detail, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    //resume tasks needing this permission
                    Toast.makeText(this, "Write External Storage Granted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Write External Storage UnGranted", Toast.LENGTH_SHORT).show();
                }
                break;

            case 3:
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Read External Storage Granted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Read External Storage UnGranted", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    public  boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ex_ecel:
                //TODO EXPORT EXCEl
                if(isWriteStoragePermissionGranted()&&isReadStoragePermissionGranted()) {
                    new ExportData(_projectList, this, databaseProject, new ExportData.Listener() {
                        @Override
                        public void OnSucces(String path) {
                            if(isReadStoragePermissionGranted()) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                Uri uri = Uri.parse(path);
                                intent.setDataAndType(uri, "application/vnd.ms-excel");
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                startActivity(Intent.createChooser(intent, "Open file xls" + uri));
                                Log.d("NGANU", "OnSucces: " + uri + "\n" + path+" "+getExternalFilesDir(path));
                            }
                        }
                    }).execute();
                    Toast.makeText(this, "Export To Excel To Storage Sucess", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public List<PekerjaanProjek> ReadPekerjaanProject(ProjectList projectList){
        SQLiteDatabase db = databaseProject.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                ReaderContract.FeedEntry.COLUMN_ID_PEKERJAAN,
                ReaderContract.FeedEntry.COLUMN_ID_PEKERJAAN2,
                ReaderContract.FeedEntry.COLUMN_ID_PEKERJAAN3,
                ReaderContract.FeedEntry.COLUMN_ID_KOEFISIWN,
                ReaderContract.FeedEntry.VALUE_DATA_PROJECT
        };

        String selection = ReaderContract.FeedEntry.COLUMN_ID_PEKERJAAN+ " = ?";
        String[] selectionArgs = {String.valueOf(projectList.ID)};

        Cursor cursor = db.query(
                ReaderContract.FeedEntry.TABLE_NAME_DATA_PROJECT,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null             // The sort order
        );

        List<PekerjaanProjek> item = new ArrayList<>();
        while(cursor.moveToNext()) {
            String idKof = cursor.getString(cursor.getColumnIndexOrThrow(ReaderContract.FeedEntry.COLUMN_ID_KOEFISIWN));
            String naker = cursor.getString(cursor.getColumnIndexOrThrow(ReaderContract.FeedEntry.COLUMN_ID_PEKERJAAN2));
            String ma = cursor.getString(cursor.getColumnIndexOrThrow(ReaderContract.FeedEntry.COLUMN_ID_PEKERJAAN3));
            PekerjaanProjek _projectList = new PekerjaanProjek(
                    cursor.getInt(cursor.getColumnIndexOrThrow(ReaderContract.FeedEntry._ID))
                    ,naker
                    ,idKof
                    ,ma
            );

            item.add(_projectList);
        }
        Log.d("KANGYUDI", "ReadPekerjaanProject: "+item.size());
        cursor.close();
        return item;
    }
    interface InsertListener{
        void onList(String CoP2,String kof,String id,String metal);
    }
    public void InsertProjectKOKO(){
        SQLiteDatabase database = databaseProject.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ReaderContract.FeedEntry.COLUMN_ID_PEKERJAAN, _projectList.ID);
        values.put(ReaderContract.FeedEntry.COLUMN_ID_PEKERJAAN2, pekerjaanKoefisien.title);
        values.put(ReaderContract.FeedEntry.COLUMN_ID_KOEFISIWN, koefisienPk.id);
        values.put(ReaderContract.FeedEntry.VALUE_DATA_PROJECT,"");
        long newRowId = database.insert(ReaderContract.FeedEntry.TABLE_NAME_DATA_PROJECT, null, values);
    }
    public void InsertProjectKOKO(InsertListener insertListener){
        SQLiteDatabase database = databaseProject.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ReaderContract.FeedEntry.COLUMN_ID_PEKERJAAN, _projectList.ID);
        values.put(ReaderContract.FeedEntry.COLUMN_ID_PEKERJAAN2, pekerjaanKoefisien.title+"("+koefisienPk.a+")");
        values.put(ReaderContract.FeedEntry.COLUMN_ID_PEKERJAAN3, DataKu.data.get(IDSUSU).title);
        values.put(ReaderContract.FeedEntry.COLUMN_ID_KOEFISIWN, koefisienPk.id);
        values.put(ReaderContract.FeedEntry.VALUE_DATA_PROJECT,"");
        long newRowId = database.insert(ReaderContract.FeedEntry.TABLE_NAME_DATA_PROJECT, null, values);
        insertListener.onList(pekerjaanKoefisien.title,koefisienPk.id, String.valueOf(newRowId),DataKu.data.get(IDSUSU).title);
    }
    public void DeleteList(PekerjaanProjek pekerjaanproject){
        SQLiteDatabase db = databaseProject.getWritableDatabase();
        String selection = ReaderContract.FeedEntry._ID+ " LIKE ?";
        String[] selectionArgs = {String.valueOf(pekerjaanproject.id)};
        int deletedRows = db.delete(ReaderContract.FeedEntry.TABLE_NAME_DATA_PROJECT, selection, selectionArgs);
        Toast.makeText(this, "Delete "+pekerjaanproject.title, Toast.LENGTH_SHORT).show();
    }


    public static void Init(Activity activity,ProjectList projectList){
        _projectList = projectList;
        Intent i = new Intent(activity,DetailActivity.class);
        activity.startActivity(i);
    }

    DialogMakerUtils dialogMakerUtils;
    List<PekerjaanKoefisien> pekerjaanKoefisiens;
    List<KoefisienPk> koefisienPkList;

    PekerjaanKoefisien pekerjaanKoefisien;
    KoefisienPk koefisienPk;
    int IDSUSU = 1;

    public void ShowDialogMakeProject(MainActivity.AddSucces addSucces){
        final DialogMakerHandler dialogMakerHandler = new DialogMakerHandler() {
            @Override
            public void handler(View view) {
                TextView btn2 = view.findViewById(R.id.btn_2);
                btn2.setText("Tambah");
                TextView btn1 = view.findViewById(R.id.btn_1);
                btn1.setText("Kembali");

                final Spinner spinner = view.findViewById(R.id.spiner);
                final Spinner spinner2 = view.findViewById(R.id.spiner2);
                final Spinner spinner3 = view.findViewById(R.id.spiner3);
                List<String> strings = new ArrayList<>();
                for (int i=0;i<DataKu.data.size();i++){
                    strings.add(DataKu.data.get(i).title);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DetailActivity.this, android.R.layout.simple_spinner_dropdown_item, strings);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        IDSUSU = (int)id;
                        pekerjaanKoefisiens = DataKu.data.get((int) id).pekerjaanKoefisiens;
                        List<String> strings2 = new ArrayList<>();
                        for (int i=0;i<pekerjaanKoefisiens.size();i++){
                            strings2.add(pekerjaanKoefisiens.get(i).title);
                        }
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(DetailActivity.this, android.R.layout.simple_spinner_dropdown_item, strings2);
                        spinner2.setAdapter(adapter2);
                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                koefisienPkList = pekerjaanKoefisiens.get((int)id).koefisienPkList;
                                pekerjaanKoefisien = pekerjaanKoefisiens.get((int)id);
                                List<String> strings3 = new ArrayList<>();
                                for (int i=0;i<koefisienPkList.size();i++){
                                    strings3.add(koefisienPkList.get(i).a);
                                }
                                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(DetailActivity.this, android.R.layout.simple_spinner_dropdown_item, strings3);
                                spinner3.setAdapter(adapter3);
                                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        koefisienPk = koefisienPkList.get((int)id);
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String NamePekerjaan = "";
                        final String[] idKof = {""};
                        final String[] naker = {""};
                        final int[] id = {0};
                        final String[] titmain = {""};

                            InsertProjectKOKO(new InsertListener() {
                                @Override
                                public void onList(String CoP2, String kof,String i2d,String me) {
                                    id[0] = Integer.valueOf(i2d);
                                    idKof[0] = kof;
                                    naker[0] = CoP2;
                                    titmain[0] = me;
                                }
                            });
                            NamePekerjaan = pekerjaanKoefisien.title +"("+koefisienPk.a+")";
                            Toast.makeText(DetailActivity.this, "Add "+NamePekerjaan, Toast.LENGTH_SHORT).show();
                            dialogMakerUtils.Dismiss();
                            SetRefreshData();

                        PekerjaanProjek pps = new PekerjaanProjek(
                                id[0]
                                , naker[0]+"("+koefisienPk.a+")"
                                , idKof[0]
                                , titmain[0]
                        );

//                        new CountDownTimer(2000,1000){
//
//                            @Override
//                            public void onTick(long millisUntilFinished) {
//
//                            }
//
//                            @Override
//                            public void onFinish() {
//
//                            }
//                        }.start();
                        EditPekerjaan.Init(DetailActivity.this,pps);

                    }
                });
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogMakerUtils.Dismiss();
                        SetRefreshData();
                    }
                });
            }
        };

        dialogMakerUtils = new DialogMakerUtils(this,R.layout.dialog_add_jenis_pekerjaan,dialogMakerHandler);
        dialogMakerUtils.Show();
    }
}
