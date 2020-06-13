package com.pengembangsebelah.calculatorrab.model.baru;

import java.util.List;

public class PekerjaanProyek {
    public String title;
    public List<PekerjaanKoefisien> pekerjaanKoefisiens;

    public PekerjaanProyek(String title,List<PekerjaanKoefisien> pekerjaanKoefisiens){
        this.pekerjaanKoefisiens = pekerjaanKoefisiens;
        this.title = title;
    }
}
