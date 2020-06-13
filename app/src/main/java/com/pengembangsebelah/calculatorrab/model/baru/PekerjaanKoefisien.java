package com.pengembangsebelah.calculatorrab.model.baru;

import java.util.List;

public class PekerjaanKoefisien {
    public String title;
    public List<KoefisienPk> koefisienPkList;
    public PekerjaanKoefisien(String title,List<KoefisienPk> koefisienPks){
        this.koefisienPkList = koefisienPks;
        this.title = title;
    }

    public void addKoefisien(KoefisienPk koefisienPk){
        this.koefisienPkList.add(koefisienPk);
    }
}
