package com.pengembangsebelah.calculatorrab.model;

public class ProjectList {
    public String Name;
    public String idKoef;
    public String Location;
    public long Price;
    public int ID;

    public ProjectList(int ID,String Name,String Location,String idKoef){
        this.Name = Name;
        this.Location = Location;
        this.idKoef = idKoef;
        Price = 0;
        this.ID = ID;
    }
}
