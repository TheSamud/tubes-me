package com.pengembangsebelah.calculatorrab.model;

import com.pengembangsebelah.calculatorrab.model.baru.PekerjaanKoefisien;

import java.util.List;

public class PekerjaanProjek {
    public int id;
    public String titlemain;
    public String title;
    public String idKoef;

    public PekerjaanProjek(int id,String title,String idKoef,String titlemain){
        this.title = title;
        this.id = id;
        this.idKoef = idKoef;
        this.titlemain = titlemain;
    }
}
