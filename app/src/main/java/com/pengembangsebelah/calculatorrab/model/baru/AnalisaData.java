package com.pengembangsebelah.calculatorrab.model.baru;

import java.util.List;

public class AnalisaData {
    public String id;
    public String nama;
    public String satuan;
    public List<Double> koef;
    public Double hargasatuan;

    public AnalisaData (String id,String nama,String satuan,List<Double> koef,Double hargasatuan){
        this.id=id;
        this.nama=nama;
        this.satuan=satuan;
        this.koef=koef;
        this.hargasatuan=hargasatuan;
    }
}
