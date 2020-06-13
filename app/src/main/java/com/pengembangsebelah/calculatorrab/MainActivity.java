package com.pengembangsebelah.calculatorrab;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.pengembangsebelah.calculatorrab.model.ProjectList;
import com.pengembangsebelah.calculatorrab.myutils.db.sqlite.ReaderContract;
import com.pengembangsebelah.calculatorrab.myutils.db.sqlite.ReaderHelperDb;
import com.pengembangsebelah.calculatorrab.myutils.dialogmaker.DialogMakerHandler;
import com.pengembangsebelah.calculatorrab.myutils.dialogmaker.DialogMakerUtils;
import com.pengembangsebelah.calculatorrab.myutils.view.CustomViewPager;
import com.pengembangsebelah.calculatorrab.ui.PaduanActi;
import com.pengembangsebelah.calculatorrab.ui.adapte.SectionHomeAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.provider.BaseColumns;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public interface AddSucces{
        void Success(String s);
    }
    boolean isexit = false;
    boolean canExit = true;
    AddSucces addSucces;
    //DatabaseSQlite
    ReaderHelperDb databaseProject;
    CustomViewPager viewPager;
    SectionHomeAdapter sectionHomeAdapter;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_one:
                    viewPager.setCurrentItem(0, true);
                    //AnalisaActivity.Init(MainActivity.this);
                    return true;
                case R.id.navigation_two:
                    viewPager.setCurrentItem(1, true);
                    return true;
                case R.id.navigation_three:
                    viewPager.setCurrentItem(2, true);
                    return true;
            }
            return false;
        }
    };

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void setupToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        // Hide the title
        getSupportActionBar().setTitle(null);
        // Set onClickListener to customView
        TextView tvSave = (TextView) findViewById(R.id.toolbar_save);
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     Intent i = new Intent(MainActivity.this, splash.class);
             //   startActivity(i);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!splash.instant.playMusic.IsPlaying()&&isexit) {
            splash.instant.playMusic.Start();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(splash.instant.playMusic.IsPlaying()&&isexit) {
            splash.instant.playMusic.Stop();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setupToolbar();

        if(!splash.instant.playMusic.IsPlaying()) {
            splash.instant.playMusic.Start();
        }

        new DataKu(loadJSONFromAsset());
        Log.d(DataKu.TAG, "onCreate: "+DataKu.data.get(0).pekerjaanKoefisiens.get(0).title);

        //db
        databaseProject = new ReaderHelperDb(this);
        sectionHomeAdapter = new SectionHomeAdapter(getSupportFragmentManager());
        //viewinit
        viewPager = findViewById(R.id.container_home);
        viewPager.setAdapter(sectionHomeAdapter);
        viewPager.SetPagingEnable(false);
    }

    @Override
    public void onBackPressed() {
        if(canExit){
//            ShowDialogNotif(new AddSucces() {
//                @Override
//                public void Success(String s) {
//                    finish();
//                }
//            },false,"Yakin ingin keluar applikasi?");
        }else {
            super.onBackPressed();
        }
    }

    public void InsertProject(String namaproject,String location){
        SQLiteDatabase database = databaseProject.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ReaderContract.FeedEntry.COLUMN_NAME_PROJECT_NAME, namaproject);
        values.put(ReaderContract.FeedEntry.COLUMN_NAME_LOCATION, location);
        long newRowId = database.insert(ReaderContract.FeedEntry.TABLE_NAME_PROJECT, null, values);
        addSucces.Success(namaproject);
    }

    public List<ProjectList> ReadProjectDatabase(){
        SQLiteDatabase db = databaseProject.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                ReaderContract.FeedEntry.COLUMN_NAME_PROJECT_NAME,
                ReaderContract.FeedEntry.COLUMN_NAME_LOCATION,
                ReaderContract.FeedEntry.COLUMN_NAME_IDSKOEF
        };

        String selection = ReaderContract.FeedEntry.COLUMN_NAME_PROJECT_NAME + " = ?";
        String[] selectionArgs = { "My Project" };
        String sortOrder =
                ReaderContract.FeedEntry.COLUMN_NAME_LOCATION + " DESC";

        Cursor cursor = db.query(
                ReaderContract.FeedEntry.TABLE_NAME_PROJECT,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null             // The sort order
        );

        List<ProjectList> item = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(ReaderContract.FeedEntry._ID));
            ProjectList projectList = new ProjectList(
                    cursor.getInt(cursor.getColumnIndexOrThrow(ReaderContract.FeedEntry._ID))
                    ,cursor.getString(cursor.getColumnIndexOrThrow(ReaderContract.FeedEntry.COLUMN_NAME_PROJECT_NAME))
                    ,cursor.getString(cursor.getColumnIndexOrThrow(ReaderContract.FeedEntry.COLUMN_NAME_LOCATION))
                    ,cursor.getString(cursor.getColumnIndexOrThrow(ReaderContract.FeedEntry.COLUMN_NAME_IDSKOEF))
            );

            item.add(projectList);
        }
        cursor.close();
        return item;
    }
    public void DeleteList(ProjectList projectList){
        SQLiteDatabase db = databaseProject.getWritableDatabase();
        String selection = ReaderContract.FeedEntry._ID+ " LIKE ?";
        String[] selectionArgs = {String.valueOf(projectList.ID)};
        int deletedRows = db.delete(ReaderContract.FeedEntry.TABLE_NAME_PROJECT, selection, selectionArgs);
        Log.d("HALLO", "DeleteList: "+deletedRows);
    }
    public void Edit(ProjectList projectList,String namaproject,String location){
        SQLiteDatabase db = databaseProject.getWritableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(ReaderContract.FeedEntry.COLUMN_NAME_PROJECT_NAME, namaproject);
        values.put(ReaderContract.FeedEntry.COLUMN_NAME_LOCATION, location);

// Which row to update, based on the title
        String selection = ReaderContract.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(projectList.ID)};

        int count = db.update(
                ReaderContract.FeedEntry.TABLE_NAME_PROJECT,
                values,
                selection,
                selectionArgs);

        addSucces.Success(namaproject);
    }

    DialogMakerUtils dialogMakerUtils;

    public void ShowDialogMakeProject(AddSucces addSucces){
        canExit = false;
        this.addSucces = addSucces;
        final DialogMakerHandler dialogMakerHandler = new DialogMakerHandler() {
            @Override
            public void handler(View view) {
                TextView btn2 = view.findViewById(R.id.btn_2);
                btn2.setText("Tambah");
                TextView btn1 = view.findViewById(R.id.btn_1);
                btn1.setText("Kembali");

                final TextInputEditText namaProject = view.findViewById(R.id.input_name_project);
                final TextInputEditText location = view.findViewById(R.id.input_location);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!namaProject.getText().toString().isEmpty()&&!location.getText().toString().isEmpty()){
                            InsertProject(namaProject.getText().toString(),location.getText().toString());
                            dialogMakerUtils.Dismiss();
                        }
                    }
                });
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogMakerUtils.Dismiss();
                    }
                });
            }
        };

        dialogMakerUtils = new DialogMakerUtils(this,R.layout.dialog_add_project,dialogMakerHandler);
        dialogMakerUtils.SetOnDismis(dimisLis);
        dialogMakerUtils.Show();
    }
    public void ShowDialogNotif(final AddSucces addSucces, final boolean setImg, final String mess){
        canExit = false;
        this.addSucces = addSucces;
        final DialogMakerHandler dialogMakerHandler = new DialogMakerHandler() {
            @Override
            public void handler(View view) {
                ImageView img = view.findViewById(R.id.image_notifa);
                if(!setImg){img.setVisibility(View.GONE);}
                TextView textView = view.findViewById(R.id.text_notifa);
                textView.setText(mess);
                TextView btn1 = view.findViewById(R.id.btn_1);
                btn1.setText("Ya");
                TextView btn2 = view.findViewById(R.id.btn_2);
                btn2.setText("Tidak");

                final TextInputEditText namaProject = view.findViewById(R.id.input_name_project);
                final TextInputEditText location = view.findViewById(R.id.input_location);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogMakerUtils.Dismiss();
                        addSucces.Success("Ya");
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogMakerUtils.Dismiss();
                    }
                });
            }
        };

        dialogMakerUtils = new DialogMakerUtils(this,R.layout.dialog_exit,dialogMakerHandler);
        dialogMakerUtils.SetOnDismis(dimisLis);
        dialogMakerUtils.Show();
    }
    public void ShowDialogMakeProject(final ProjectList projectList, AddSucces addSucces){
        canExit = false;
        this.addSucces = addSucces;
        final DialogMakerHandler dialogMakerHandler = new DialogMakerHandler() {
            @Override
            public void handler(View view) {
                TextView btn2 = view.findViewById(R.id.btn_1);
                btn2.setText("Simpan");
                TextView btn1 = view.findViewById(R.id.btn_2);
                btn1.setText("Kembali");

                final TextInputEditText namaProject = view.findViewById(R.id.input_name_project);
                namaProject.setText(projectList.Name);
                final TextInputEditText location = view.findViewById(R.id.input_location);
                location.setText(projectList.Location);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!namaProject.getText().toString().isEmpty()&&!location.getText().toString().isEmpty()){
                            Edit(projectList,namaProject.getText().toString(),location.getText().toString());
                            dialogMakerUtils.Dismiss();
                        }
                    }
                });
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogMakerUtils.Dismiss();
                    }
                });
            }
        };

        dialogMakerUtils = new DialogMakerUtils(this,R.layout.dialog_add_project,dialogMakerHandler);
        dialogMakerUtils.SetOnDismis(dimisLis);
        dialogMakerUtils.Show();
    }

    DialogMakerUtils.Listener dimisLis = new DialogMakerUtils.Listener() {
        @Override
        public void OnDismis() {
            canExit = true;
        }
    };

    public static void Init(Activity activity){
        Intent i = new Intent(activity,MainActivity.class);
        activity.startActivity(i);
    }

}
