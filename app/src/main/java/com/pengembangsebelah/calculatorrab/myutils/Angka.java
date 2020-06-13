package com.pengembangsebelah.calculatorrab.myutils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Angka {
    public static String Rupiah(double val){
        NumberFormat formatter = new DecimalFormat("#,###");
        return formatter.format(val);
    }
}
