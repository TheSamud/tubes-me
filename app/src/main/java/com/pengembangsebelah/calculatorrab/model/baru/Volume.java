package com.pengembangsebelah.calculatorrab.model.baru;

public class Volume {
    public String name;
    public String id;
    public String type;
    public boolean IsCanAdd = false;
    public Volume volume =null;
    public int total;

    public Volume(String name,String id,String type){
        this.name = name;
        this.id=id;
        this.type=type;
    }

    public void SetTotal(int total){
        this.total = total;
    }

    public void SetCanUpdate(boolean value){
        this.IsCanAdd = value;
    }

    public void SetVolumeUpdateObject(Volume volume){
        this.volume=volume;
    }
}
