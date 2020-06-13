package com.pengembangsebelah.calculatorrab.model.baru;

import java.util.List;

public class KoefisienPk {
    public String a ;
    public String id;
    public String analisa;

    public String volume;
    public List<AnalisaData> analisa2;

    public void TambahAnalisa(String analisa){
        this.analisa = analisa;
    }

    public void TambahVolume(String volume){
        this.volume = volume;
    }

    public void TambahanAnalisa(List<AnalisaData> analisa2){
        this.analisa2 = analisa2;
    }
    public KoefisienPk(String a,String id){
        this.a = a;
        this.id = id;
    }
    public KoefisienPk(){
    }
}
