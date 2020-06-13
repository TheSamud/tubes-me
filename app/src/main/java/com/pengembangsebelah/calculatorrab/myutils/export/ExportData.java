package com.pengembangsebelah.calculatorrab.myutils.export;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.BaseColumns;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.Toast;

import com.pengembangsebelah.calculatorrab.EditPekerjaan;
import com.pengembangsebelah.calculatorrab.model.PekerjaanProjek;
import com.pengembangsebelah.calculatorrab.model.ProjectList;
import com.pengembangsebelah.calculatorrab.model.baru.PekerjaanProyek;
import com.pengembangsebelah.calculatorrab.myutils.Angka;
import com.pengembangsebelah.calculatorrab.myutils.Check;
import com.pengembangsebelah.calculatorrab.myutils.db.sqlite.ReaderContract;
import com.pengembangsebelah.calculatorrab.myutils.db.sqlite.ReaderHelperDb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExportData extends AsyncTask<String, String, Boolean> {
    File myFile;
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss");
    String TimeStampDB = sdf.format(cal.getTime());
    ProjectList projectList;
    ReaderHelperDb databaseProject;

    String nameSave = "";
    public interface Listener{
        void OnSucces(String path);
    }
    public ExportData(){

    }
    Listener listener;
    public ExportData(Listener listener){
        this.listener = listener;
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
            String maintel = cursor.getString(cursor.getColumnIndexOrThrow(ReaderContract.FeedEntry.COLUMN_ID_PEKERJAAN3));
            PekerjaanProjek _projectList = new PekerjaanProjek(
                    cursor.getInt(cursor.getColumnIndexOrThrow(ReaderContract.FeedEntry._ID))
                    ,naker
                    ,idKof
                    ,maintel
            );

            item.add(_projectList);
        }
        Log.d("KANGYUDI", "ReadPekerjaanProject: "+item.size());
        cursor.close();
        return item;
    }
    Context context;
    public ExportData(ProjectList projectList,Context context,ReaderHelperDb databaseProject,Listener listener){
        this.projectList=projectList;
        this.context = context;
        this.databaseProject = databaseProject;
        this.listener = listener;
    }

    WritableCellFormat WCF(int size){
        WritableFont wfont = new WritableFont(WritableFont.ARIAL, size, WritableFont.BOLD, false,
                UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
        WritableCellFormat wcfFC = new WritableCellFormat(wfont);
        try {
            wcfFC.setBorder(Border.ALL, BorderLineStyle.THIN);
            return wcfFC;
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return wcfFC;
    }
    WritableCellFormat Border(){
        WritableCellFormat wcfFC = new WritableCellFormat();
        try {
            wcfFC.setBorder(Border.ALL, BorderLineStyle.THIN);
            return wcfFC;
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return wcfFC;
    }
    @Override
    protected Boolean doInBackground(String... strings) {
        boolean success = false;
        int aa = 0;

        try {
            nameSave = projectList.Name+"_"+TimeStampDB+"_data.xls";
            File folder = new File(Environment.getExternalStorageDirectory() + "/RABDatas");
            boolean successw = true;
            if (!folder.exists()) {
                successw = folder.mkdir();
            }
            if (successw) {
                WritableWorkbook w = Workbook.createWorkbook(new File(Environment.getExternalStorageDirectory()+"/RABDatas/"+nameSave));

                WritableSheet s = w.createSheet("Sheet1", 0);

                s.setColumnView(0,4);
                s.setColumnView(1,90);
                s.setColumnView(2,16);
                s.setColumnView(4,16);
                s.setColumnView(5,16);

                s.addCell(new Label(0, 0, "RENCANA ANGGARAN BIAYA",WCF(20)));
                if(projectList!=null) {
                    s.addCell(new Label(0, 1, "Pekerjaan : " + projectList.Name,Border()));
                    s.addCell(new Label(0, 2, "Lokasi : " + projectList.Location,Border()));
                }else {
                    s.addCell(new Label(0, 1, "Pekerjaan : ",Border()));
                    s.addCell(new Label(0, 2, "Lokasi : ",Border()));
                }
                s.addCell(new Label(0, 3, "TA : "+TimeStampDB,Border()));

                //Table
                s.addCell(new Label(0, 5, "No",Border()));
                s.addCell(new Label(1, 5, "Uraian Pekerjaan",Border()));
                s.addCell(new Label(2, 5, "Volume",Border()));
                s.addCell(new Label(3, 5, "Satuan",Border()));
                s.addCell(new Label(4, 5, "Harga Satuan",Border()));
                s.addCell(new Label(5, 5, "Harga",Border()));

                s.addCell(new Label(0, 6, "I.",Border()));
                s.addCell(new Label(1, 6, "PEKERJAAN PERSIAPAN",WCF(10)));
                s.addCell(new Label(2, 6, "",Border()));
                s.addCell(new Label(3, 6, "",Border()));
                s.addCell(new Label(4, 6, "",Border()));
                s.addCell(new Label(5, 6, "",Border()));
                int pp = 1;
                Float yu = 0f;
                for (int a=0;a<ReadPekerjaanProject(projectList).size();a++){
                    if(ReadPekerjaanProject(projectList).get(a).titlemain.equals("Pekerjaan Persiapan")){
                        aa++;
                        s.addCell(new Label(0, 6+aa, pp+"",Border()));
                        pp++;
                        s.addCell(new Label(1, 6+aa, ReadPekerjaanProject(projectList).get(a).title,Border()));
                        s.addCell(new Label(2, 6+aa, ""+Check.getFloat(EditPekerjaan.POLUME+ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0),Border()));
                        if(ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1 m1 Bowplank")){
                            s.addCell(new Label(3, 6 + aa, "m1",Border()));
                        }else {
                            s.addCell(new Label(3, 6 + aa, "m2",Border()));
                        }

                        s.addCell(new Label(4, 6+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(EditPekerjaan.HARGASATUAN +ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        s.addCell(new Label(5, 6+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        yu+=Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0);
                    }
                }
                s.addCell(new Label(5, 6, "Rp."+ Angka.Rupiah((double)yu),Border()));
                yu=0f;

                s.addCell(new Label(0, 8+aa, "II.",Border()));
                s.addCell(new Label(1, 8+aa, "PEKERJAAN GALIAN DAN URUGAN",WCF(10)));
                s.addCell(new Label(2, 8+aa, "",Border()));
                s.addCell(new Label(3, 8+aa, "",Border()));
                s.addCell(new Label(4, 8+aa, "",Border()));
                s.addCell(new Label(5, 8+aa, "",Border()));
                int ppp = 1;
                for (int a=0;a<ReadPekerjaanProject(projectList).size();a++){
                    if(ReadPekerjaanProject(projectList).get(a).titlemain.equals("Pekerjaan Tanah")){
                        aa++;
                        s.addCell(new Label(0, 8+aa, ppp+"",Border()));
                        ppp++;
                        s.addCell(new Label(1, 8+aa, ReadPekerjaanProject(projectList).get(a).title,Border()));
                        s.addCell(new Label(2, 8+aa, ""+Check.getFloat(EditPekerjaan.POLUME+ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0),Border()));
                        s.addCell(new Label(3, 8+aa, "m3",Border()));
                        s.addCell(new Label(4, 8+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(EditPekerjaan.HARGASATUAN +ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        s.addCell(new Label(5, 8+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        yu+=Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0);
                    }
                }
                s.addCell(new Label(5, 8+aa, "Rp."+ Angka.Rupiah((double)yu),Border()));
                yu=0f;

                s.addCell(new Label(0, 10+aa, "III.",Border()));
                s.addCell(new Label(1, 10+aa, "PEKERJAAN PONDASI",WCF(10)));
                s.addCell(new Label(2, 10+aa, "",Border()));
                s.addCell(new Label(3, 10+aa, "",Border()));
                s.addCell(new Label(4, 10+aa, "",Border()));
                s.addCell(new Label(5, 10+aa, "",Border()));

                int ppps = 1;
                for (int a=0;a<ReadPekerjaanProject(projectList).size();a++){
                    if(ReadPekerjaanProject(projectList).get(a).titlemain.equals("Pekerjaan Pondasi")){
                        aa++;
                        s.addCell(new Label(0, 10+aa, ppps+"",Border()));
                        ppps++;
                        s.addCell(new Label(1, 10+aa, ReadPekerjaanProject(projectList).get(a).title,Border()));
                        s.addCell(new Label(2, 10+aa, ""+Check.getFloat(EditPekerjaan.POLUME+ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0),Border()));
                        s.addCell(new Label(3, 10+aa, "m3",Border()));
                        s.addCell(new Label(4, 10+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(EditPekerjaan.HARGASATUAN +ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        s.addCell(new Label(5, 10+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        yu+=Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0);
                    }
                }
                s.addCell(new Label(5, 10+aa, "Rp."+ Angka.Rupiah((double)yu),Border()));
                yu=0f;

                s.addCell(new Label(0, 12+aa, "IV.",Border()));
                s.addCell(new Label(1, 12+aa, "PEKERJAAN BETON BERTULANG",WCF(10)));
                s.addCell(new Label(2, 12+aa, "",Border()));
                s.addCell(new Label(3, 12+aa, "",Border()));
                s.addCell(new Label(4, 12+aa, "",Border()));
                s.addCell(new Label(5, 12+aa, "",Border()));
                int pppsw = 1;
                for (int a=0;a<ReadPekerjaanProject(projectList).size();a++){
                    if(ReadPekerjaanProject(projectList).get(a).titlemain.equals("Beton")){
                        aa++;
                        s.addCell(new Label(0, 12+aa, pppsw+"",Border()));
                        pppsw++;
                        s.addCell(new Label(1, 12+aa, ReadPekerjaanProject(projectList).get(a).title,Border()));
                        s.addCell(new Label(2, 12+aa, ""+Check.getFloat(EditPekerjaan.POLUME+ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0),Border()));
                        if(ReadPekerjaanProject(projectList).get(a).title.contains("Pembesian 10 kg dengan besi polos atau besi ulir")||ReadPekerjaanProject(projectList).get(a).title.equals("Pemasangan 10 kg jaring kawat (wiremesh)")){
                            s.addCell(new Label(3, 12 + aa, "kg",Border()));
                        }else if(ReadPekerjaanProject(projectList).get(a).title.contains("Pembuatan 1 m2 bekisting")||
                                ReadPekerjaanProject(projectList).get(a).title.contains("Pembuatan 1 m3 Beton Bertulang")){
                            s.addCell(new Label(3, 12 + aa, "m2",Border()));
                        }else if(ReadPekerjaanProject(projectList).get(a).title.contains("Pembuatan 1m1 Ring Balok ( 10 x 15 cm)")) {
                            s.addCell(new Label(3, 12 + aa, "m1",Border()));
                        }else {
                            s.addCell(new Label(3, 12 + aa, "m3",Border()));
                        }
                        s.addCell(new Label(4, 12+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(EditPekerjaan.HARGASATUAN +ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        s.addCell(new Label(5, 12+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        yu+=Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0);
                    }
                }
                s.addCell(new Label(5, 12+aa, "Rp."+ Angka.Rupiah((double)yu),Border()));
                yu=0f;

                s.addCell(new Label(0, 14+aa, "V.",Border()));
                s.addCell(new Label(1, 14+aa, "PEKERJAAN PASANGAN DAN PLESTERAN",WCF(10)));
                s.addCell(new Label(2, 14+aa, "",Border()));
                s.addCell(new Label(3, 14+aa, "",Border()));
                s.addCell(new Label(4, 14+aa, "",Border()));
                s.addCell(new Label(5, 14+aa, "",Border()));
                int pppsww = 1;
                for (int a=0;a<ReadPekerjaanProject(projectList).size();a++){
                    if(ReadPekerjaanProject(projectList).get(a).titlemain.equals("Pekerjaan dinding")||ReadPekerjaanProject(projectList).get(a).titlemain.equals("Pekerjaan Plesteran")){
                        aa++;
                        s.addCell(new Label(0, 14+aa, pppsww+"",Border()));
                        pppsww++;
                        s.addCell(new Label(1, 14+aa, ReadPekerjaanProject(projectList).get(a).title,Border()));
                        s.addCell(new Label(3, 14+aa, "m2",Border()));
                        s.addCell(new Label(2, 14+aa, ""+Check.getFloat(EditPekerjaan.POLUME+ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0),Border()));
                        s.addCell(new Label(4, 14+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(EditPekerjaan.HARGASATUAN +ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        s.addCell(new Label(5, 14+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        yu+=Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0);
                    }
                }
                s.addCell(new Label(5, 14+aa, "Rp."+ Angka.Rupiah((double)yu),Border()));
                yu=0f;

                s.addCell(new Label(0, 16+aa, "VI.",Border()));
                s.addCell(new Label(1, 16+aa, "PEKERJAAN PENUTUP LANTAI DAN PENUTUP DINDING",WCF(10)));
                s.addCell(new Label(2, 16+aa, "",Border()));
                s.addCell(new Label(3, 16+aa, "",Border()));
                s.addCell(new Label(4, 16+aa, "",Border()));
                s.addCell(new Label(5, 16+aa, "",Border()));
                int pppswww = 1;
                for (int a=0;a<ReadPekerjaanProject(projectList).size();a++){
                    if(ReadPekerjaanProject(projectList).get(a).titlemain.equals("Pekerjaan Penutup Lantai & Dinding")){
                        aa++;
                        s.addCell(new Label(0, 16+aa, pppswww+"",Border()));
                        pppswww++;
                        s.addCell(new Label(3, 16+aa, "m2",Border()));
                        s.addCell(new Label(1, 16+aa, ReadPekerjaanProject(projectList).get(a).title,Border()));
                        s.addCell(new Label(2, 16+aa, ""+Check.getFloat(EditPekerjaan.POLUME+ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0),Border()));
                        s.addCell(new Label(4, 16+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(EditPekerjaan.HARGASATUAN +ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        s.addCell(new Label(5, 16+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        yu+=Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0);
                    }
                }
                s.addCell(new Label(5, 16+aa, "Rp."+ Angka.Rupiah((double)yu),Border()));
                yu=0f;

                s.addCell(new Label(0, 18+aa, "VII.",Border()));
                s.addCell(new Label(1, 18+aa, "PEKERJAAN PLAFOND",WCF(10)));
                s.addCell(new Label(2, 18+aa, "",Border()));
                s.addCell(new Label(3, 18+aa, "",Border()));
                s.addCell(new Label(4, 18+aa, "",Border()));
                s.addCell(new Label(5, 18+aa, "",Border()));
                int pppswwws = 1;
                for (int a=0;a<ReadPekerjaanProject(projectList).size();a++){
                    if(ReadPekerjaanProject(projectList).get(a).titlemain.equals("Plafon")){
                        aa++;
                        s.addCell(new Label(0, 18+aa, pppswwws+"",Border()));
                        pppswwws++;
                        s.addCell(new Label(3, 18+aa, "m2",Border()));
                        s.addCell(new Label(1, 18+aa, ReadPekerjaanProject(projectList).get(a).title,Border()));
                        s.addCell(new Label(2, 18+aa, ""+Check.getFloat(EditPekerjaan.POLUME+ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0),Border()));
                        s.addCell(new Label(4, 18+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(EditPekerjaan.HARGASATUAN +ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        s.addCell(new Label(5, 18+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        yu+=Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0);
                    }
                }
                s.addCell(new Label(5, 18+aa, "Rp."+ Angka.Rupiah((double)yu),Border()));
                yu=0f;

                s.addCell(new Label(0, 20+aa, "VIII.",Border()));
                s.addCell(new Label(1, 20+aa, "PEKERJAAN PENUTUP ATAP",WCF(10)));
                s.addCell(new Label(2, 20+aa, "",Border()));
                s.addCell(new Label(3, 20+aa, "",Border()));
                s.addCell(new Label(4, 20+aa, "",Border()));
                s.addCell(new Label(5, 20+aa, "",Border()));
                int pppswwwse = 1;
                for (int a=0;a<ReadPekerjaanProject(projectList).size();a++){
                    if(ReadPekerjaanProject(projectList).get(a).titlemain.contains("Pekerjaan Penutup Atap")){
                        aa++;
                        s.addCell(new Label(0, 20+aa, pppswwwse+"",Border()));
                        pppswwwse++;

                        if(ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1 m1 bubung genteng")||
                                ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1 m1 roof light fibreglass 90 x 180")||
                                ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1 m1 Nok ")
                        ){
                            s.addCell(new Label(3, 20 + aa, "m1",Border()));
                        }else {
                            s.addCell(new Label(3, 20 + aa, "m2",Border()));
                        }
                        s.addCell(new Label(1, 20+aa, ReadPekerjaanProject(projectList).get(a).title,Border()));
                        s.addCell(new Label(2, 20+aa, ""+Check.getFloat(EditPekerjaan.POLUME+ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0),Border()));
                        s.addCell(new Label(4, 20+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(EditPekerjaan.HARGASATUAN +ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        s.addCell(new Label(5, 20+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        yu+=Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0);
                    }
                }
                s.addCell(new Label(5, 20+aa, "Rp."+ Angka.Rupiah((double)yu),Border()));
                yu=0f;

                s.addCell(new Label(0, 22+aa, "IX.",Border()));
                s.addCell(new Label(1, 22+aa, "PEKERJAAN KAYU",WCF(10)));
                s.addCell(new Label(2, 22+aa, "",Border()));
                s.addCell(new Label(3, 22+aa, "",Border()));
                s.addCell(new Label(4, 22+aa, "",Border()));
                s.addCell(new Label(5, 22+aa, "",Border()));
                int qw = 1;
                for (int a=0;a<ReadPekerjaanProject(projectList).size();a++){
                    if(ReadPekerjaanProject(projectList).get(a).titlemain.equals("Pekerjaan Kayu")){
                        aa++;
                        s.addCell(new Label(0, 22+aa, qw+"",Border()));
                        qw++;
                        if(ReadPekerjaanProject(projectList).get(a).title.contains("Pembuatan dan pemasangan 1m3 kusen pintu dan kusen jendela")||
                                ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1m3 konstruksi kuda kuda konvensional, kayu kelas I, II dan III")||
                                ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1m3 konstruksi kuda kuda expose, kayu kelas I")||
                                ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1m3 konstruksi gording, kayu kelas II")
                        ){
                            s.addCell(new Label(3, 22 + aa, "m3",Border()));
                        }else if(ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1m' lisplank  kayu kelas I atau II")
                        ){
                            s.addCell(new Label(3, 22 + aa, "m1",Border()));
                        }else {
                            s.addCell(new Label(3, 22 + aa, "m2",Border()));
                        }
                        s.addCell(new Label(1, 22+aa, ReadPekerjaanProject(projectList).get(a).title,Border()));
                        s.addCell(new Label(2, 22+aa, ""+Check.getFloat(EditPekerjaan.POLUME+ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0),Border()));
                        s.addCell(new Label(4, 22+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(EditPekerjaan.HARGASATUAN +ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        s.addCell(new Label(5, 22+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        yu+=Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0);
                    }
                }
                s.addCell(new Label(5, 22+aa, "Rp."+ Angka.Rupiah((double)yu),Border()));
                yu=0f;

                s.addCell(new Label(0, 24+aa, "X.",Border()));
                s.addCell(new Label(1, 24+aa, "PEKERJAAN PENGGANTUNG DAN PENGUNCI",WCF(10)));
                s.addCell(new Label(2, 24+aa, "",Border()));
                s.addCell(new Label(3, 24+aa, "",Border()));
                s.addCell(new Label(4, 24+aa, "",Border()));
                s.addCell(new Label(5, 24+aa, "",Border()));
                int qws = 1;
                for (int a=0;a<ReadPekerjaanProject(projectList).size();a++){
                    if(ReadPekerjaanProject(projectList).get(a).titlemain.equals("Pekerjaan Kunci dan Kaca")){
                        aa++;
                        s.addCell(new Label(0, 24+aa, qws+"",Border()));
                        qws++;
                        if(ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1 m2 Kaca")
                                ||ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1 m2 Kaca Buram Tebal 12mm")
                                ||ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1 m2 Kaca Cermin")
                                ||ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1 m2 Kaca Wireglassed Tebal 5mm")
                                ||ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1 m2 Kaca Patri Tebal 5mm")
                        ){
                            s.addCell(new Label(3, 24 + aa, "m2",Border()));
                        }else {
                            s.addCell(new Label(3, 24 + aa, "buah",Border()));
                        }
                        s.addCell(new Label(1, 24+aa, ReadPekerjaanProject(projectList).get(a).title,Border()));
                        s.addCell(new Label(2, 24+aa, ""+Check.getFloat(EditPekerjaan.POLUME+ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0),Border()));
                        s.addCell(new Label(4, 24+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(EditPekerjaan.HARGASATUAN +ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        s.addCell(new Label(5, 24+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        yu+=Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0);
                    }
                }
                s.addCell(new Label(5, 24+aa, "Rp."+ Angka.Rupiah((double)yu),Border()));
                yu=0f;

                s.addCell(new Label(0, 26+aa, "XI.",Border()));
                s.addCell(new Label(1, 26+aa, "PEKERJAAN CAT",WCF(10)));
                s.addCell(new Label(2, 26+aa, "",Border()));
                s.addCell(new Label(3, 26+aa, "",Border()));
                s.addCell(new Label(4, 26+aa, "",Border()));
                s.addCell(new Label(5, 26+aa, "",Border()));
                int qwsq = 1;
                for (int a=0;a<ReadPekerjaanProject(projectList).size();a++){
                    if(ReadPekerjaanProject(projectList).get(a).titlemain.equals("Pekerjaan Pengecatan")){
                        aa++;
                        s.addCell(new Label(0, 26+aa, qwsq+"",Border()));
                        qwsq++;
                        s.addCell(new Label(3, 26+aa, "m2",Border()));
                        s.addCell(new Label(1, 26+aa, ReadPekerjaanProject(projectList).get(a).title,Border()));
                        s.addCell(new Label(2, 26+aa, ""+Check.getFloat(EditPekerjaan.POLUME+ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0),Border()));
                        s.addCell(new Label(4, 26+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(EditPekerjaan.HARGASATUAN +ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        s.addCell(new Label(5, 26+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        yu+=Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0);
                    }
                }
                s.addCell(new Label(5, 26+aa, "Rp."+ Angka.Rupiah((double)yu),Border()));
                yu=0f;

                s.addCell(new Label(0, 28+aa, "XII.",Border()));
                s.addCell(new Label(1, 28+aa, "PEKERJAAN SANITASI DAN INSTALASI AIR",WCF(10)));
                s.addCell(new Label(2, 28+aa, "",Border()));
                s.addCell(new Label(3, 28+aa, "",Border()));
                s.addCell(new Label(4, 28+aa, "",Border()));
                s.addCell(new Label(5, 28+aa, "",Border()));
                int qwsqr = 1;
                for (int a=0;a<ReadPekerjaanProject(projectList).size();a++){
                    if(ReadPekerjaanProject(projectList).get(a).titlemain.equals("Pekerjaan Sanitasi dalam Gedung")){
                        aa++;
                        s.addCell(new Label(0, 28+aa, qwsqr+"",Border()));
                        qwsqr++;
                        if(ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1 m' pipa galvanis")
                                ||ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1 m' pipa PVC tipe AW")
                        ){
                            s.addCell(new Label(3, 28 + aa, "m1",Border()));
                        }else {
                            s.addCell(new Label(3, 28 + aa, "buah",Border()));
                        }
                        s.addCell(new Label(1, 28+aa, ReadPekerjaanProject(projectList).get(a).title,Border()));
                        s.addCell(new Label(2, 28+aa, ""+Check.getFloat(EditPekerjaan.POLUME+ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0),Border()));
                        s.addCell(new Label(4, 28+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(EditPekerjaan.HARGASATUAN +ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        s.addCell(new Label(5, 28+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        yu+=Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0);
                    }
                }
                s.addCell(new Label(5, 28+aa, "Rp."+ Angka.Rupiah((double)yu),Border()));
                yu=0f;

                s.addCell(new Label(0, 30+aa, "XIII."));
                s.addCell(new Label(1, 30+aa, "PEKERJAAN INSTALASI LISTRIK",WCF(10)));
                s.addCell(new Label(2, 30+aa, "",Border()));
                s.addCell(new Label(3, 30+aa, "",Border()));
                s.addCell(new Label(4, 30+aa, "",Border()));
                s.addCell(new Label(5, 30+aa, "",Border()));
                int qwsqrr = 1;
                for (int a=0;a<ReadPekerjaanProject(projectList).size();a++){
                    if(ReadPekerjaanProject(projectList).get(a).titlemain.equals("Pekerjaan Mekanikal Eletrikal")){
                        aa++;
                        s.addCell(new Label(0, 30+aa, qwsqrr+"",Border()));
                        qwsqrr++;
                        if(ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1 titik Stop kontak")
                                ||ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1 titik Saklar")
                        ){
                            s.addCell(new Label(3, 30 + aa, "titik",Border()));
                        }else {
                            s.addCell(new Label(3, 30 + aa, "buah",Border()));
                        }
                        s.addCell(new Label(1, 30+aa, ReadPekerjaanProject(projectList).get(a).title));
                        s.addCell(new Label(2, 30+aa, ""+Check.getFloat(EditPekerjaan.POLUME+ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0),Border()));
                        s.addCell(new Label(4, 30+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(EditPekerjaan.HARGASATUAN +ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        s.addCell(new Label(5, 30+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        yu+=Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0);
                    }
                }
                s.addCell(new Label(5, 30+aa, "Rp."+ Angka.Rupiah((double)yu),Border()));
                yu=0f;

                s.addCell(new Label(0, 32+aa, "XIV.",Border()));
                s.addCell(new Label(1, 32+aa, "PEKERJAAN BONGKARAN",WCF(10)));
                s.addCell(new Label(2, 32+aa, "",Border()));
                s.addCell(new Label(3, 32+aa, "",Border()));
                s.addCell(new Label(4, 32+aa, "",Border()));
                s.addCell(new Label(5, 32+aa, "",Border()));
                int qwsqrwr = 1;
                for (int a=0;a<ReadPekerjaanProject(projectList).size();a++){
                    if(ReadPekerjaanProject(projectList).get(a).titlemain.equals("Pekerjaan Bongkaran")){
                        aa++;
                        s.addCell(new Label(0, 32+aa, qwsqrwr+"",Border()));
                        qwsqrwr++;
                        if(ReadPekerjaanProject(projectList).get(a).title.contains("Pembongkaran 1m3 Beton Bertulang")
                                ||ReadPekerjaanProject(projectList).get(a).title.contains("Pembongkaran 1m3 Kuda-Kuda dipakai Lagi")
                        ){
                            s.addCell(new Label(3, 32 + aa, "m3",Border()));
                        }else {
                            s.addCell(new Label(3, 32 + aa, "m2",Border()));
                        }
                        s.addCell(new Label(1, 32+aa, ReadPekerjaanProject(projectList).get(a).title,Border()));
                        s.addCell(new Label(2, 32+aa, ""+Check.getFloat(EditPekerjaan.POLUME+ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0),Border()));
                        s.addCell(new Label(4, 32+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(EditPekerjaan.HARGASATUAN +ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        s.addCell(new Label(5, 32+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        yu+=Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0);
                    }
                }
                s.addCell(new Label(5, 32+aa, "Rp."+ Angka.Rupiah((double)yu),Border()));
                yu=0f;

                s.addCell(new Label(0, 34+aa, "XV.",Border()));
                s.addCell(new Label(1, 34+aa, "PEKERJAAN BESI DAN ALUMUNIUM",WCF(10)));
                s.addCell(new Label(2, 34+aa, "",Border()));
                s.addCell(new Label(3, 34+aa, "",Border()));
                s.addCell(new Label(4, 34+aa, "",Border()));
                s.addCell(new Label(5, 34+aa, "",Border()));
                int qwsqrwwr = 1;
                for (int a=0;a<ReadPekerjaanProject(projectList).size();a++){
                    if(ReadPekerjaanProject(projectList).get(a).titlemain.equals("Pekerjaan Besi dan Alumunium")){
                        aa++;
                        s.addCell(new Label(0, 34+aa, qwsqrwwr+"",Border()));
                        qwsqrwwr++;
                        if(ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1 m1 kusen pintu aluminium")
                                ||ReadPekerjaanProject(projectList).get(a).title.contains("Pemasangan 1 m1 talang 1/2 lingkaran D - 15 c, seng plat bjls 20 lebar 45 cm")
                        ){
                            s.addCell(new Label(3, 32 + aa, "m1",Border()));
                        }else {
                            s.addCell(new Label(3, 32 + aa, "m2",Border()));
                        }
                        s.addCell(new Label(1, 34+aa, ReadPekerjaanProject(projectList).get(a).title,Border()));
                        s.addCell(new Label(2, 34+aa, ""+Check.getFloat(EditPekerjaan.POLUME+ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0),Border()));
                        s.addCell(new Label(4, 34+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(EditPekerjaan.HARGASATUAN +ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        s.addCell(new Label(5, 34+aa, "Rp."+ Angka.Rupiah((double) Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0)),Border()));
                        yu+=Check.getFloat(ReadPekerjaanProject(projectList).get(a).id+ReadPekerjaanProject(projectList).get(a).title,context, (float) 0);
                    }
                }
                s.addCell(new Label(5, 34+aa, "Rp."+ Angka.Rupiah((double)yu),Border()));

                w.write();
                w.close();
            } else {
                Toast.makeText(context, "Failed - Error Creating Folder", Toast.LENGTH_SHORT).show();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        listener.OnSucces(Environment.getExternalStorageDirectory().getPath()
                + "/RABDatas/"+nameSave);
    }
}
