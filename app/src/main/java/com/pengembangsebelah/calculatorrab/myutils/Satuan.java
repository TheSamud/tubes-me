package com.pengembangsebelah.calculatorrab.myutils;

import android.util.Log;

import com.pengembangsebelah.calculatorrab.EditPekerjaan;

import jxl.write.Label;

public class Satuan {
    public static String get(String title,String title2){
        Log.d("SATEAN", "get: "+title+" "+title2);
        if(title.equals("Pekerjaan Persiapan")){
            if(title2.contains("Pemasangan 1 m1 Bowplank")){
                return " m1";
            }else {
                return " m2";
            }
        }else if(title.equals("Pekerjaan Tanah")){
            return " m3";
        }else if(title.equals("Pekerjaan Pondasi")){
            return " m3";
        }else if(title.equals("Beton")){
            if(title2.contains("Pembesian 10 kg dengan besi polos atau besi ulir")||title2.equals("Pemasangan 10 kg jaring kawat (wiremesh)")){
                return " kg";
            }else if(title2.contains("Pembuatan 1 m2 bekisting")||
                    title2.contains("Pembuatan 1 m3 Beton Bertulang")){
                return " m2";
            }else if(title2.contains("Pembuatan 1m1 Ring Balok ( 10 x 15 cm)")) {
                return " m4";
            }else {
                return " m3";
            }
        }else if(title.equals("Pekerjaan dinding")||title.equals("Pekerjaan Plesteran")){
            return " m2";
        }else if(title.equals("Pekerjaan Penutup Lantai & Dinding")){
            return " m2";
        }else if(title.equals("Plafon")){
            return "m2";
        }else if(title.contains("Pekerjaan Penutup Atap")){

            if(title2.contains("Pemasangan 1 m1 bubung genteng")||
                    title2.contains("Pemasangan 1 m1 roof light fibreglass 90 x 180")||
                    title2.contains("Pemasangan 1 m1 Nok ")
            ){
                return " m1";
            }else {
                return " m2";
            }
        }else if(title.equals("Pekerjaan Kayu")){
            if(title2.contains("Pembuatan dan pemasangan 1m3 kusen pintu dan kusen jendela")||
                    title2.contains("Pemasangan 1m3 konstruksi kuda kuda konvensional, kayu kelas I, II dan III")||
                    title2.contains("Pemasangan 1m3 konstruksi kuda kuda expose, kayu kelas I")||
                    title2.contains("Pemasangan 1m3 konstruksi gording, kayu kelas II")
            ){
                return " m3";
            }else if(title2.contains("Pemasangan 1m' lisplank  kayu kelas I atau II")
            ){
                return " m1";
            }else {
                return " m2";
            }
        }else if(title.equals("Pekerjaan Kunci dan Kaca")){
            if(title2.contains("Pemasangan 1 m2 Kaca")
                    ||title2.contains("Pemasangan 1 m2 Kaca Buram Tebal 12mm")
                    ||title2.contains("Pemasangan 1 m2 Kaca Cermin")
                    ||title2.contains("Pemasangan 1 m2 Kaca Wireglassed Tebal 5mm")
                    ||title2.contains("Pemasangan 1 m2 Kaca Patri Tebal 5mm")
            ){
                return " m2";
            }else {
                return " Buah";
            }
        }else if(title.equals("Pekerjaan Pengecatan")){
            return " m2";
        }else if(title.equals("Pekerjaan Sanitasi dalam Gedung")){

            if(title2.contains("Pemasangan 1 m' pipa galvanis")
                    ||title2.contains("Pemasangan 1 m' pipa PVC tipe AW")
            ){
                return " m1";
            }else {
                return " Buah";
            }
        }else if(title.equals("Pekerjaan Mekanikal Eletrikal")){

            if(title2.contains("Pemasangan 1 titik Stop kontak")
                    ||title2.contains("Pemasangan 1 titik Saklar")
            ){
                return " Titik";
            }else {
                return " Buah";
            }
        }else if(title.equals("Pekerjaan Bongkaran")){

            if(title2.contains("Pembongkaran 1m3 Beton Bertulang")
                    ||title2.contains("Pembongkaran 1m3 Kuda-Kuda dipakai Lagi")
            ){
                return " m3";
            }else {
                return " m2";
            }
        }else if(title.equals("Pekerjaan Besi dan Alumunium")){

            if(title2.contains("Pemasangan 1 m1 kusen pintu aluminium")
                    ||title2.contains("Pemasangan 1 m1 talang 1/2 lingkaran D - 15 c, seng plat bjls 20 lebar 45 cm")
            ){
                return " m1";
            }else {
                return " m2";
            }
        }

        else{
            return " null";
        }
    }
}
