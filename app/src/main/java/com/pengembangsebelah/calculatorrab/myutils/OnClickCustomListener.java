package com.pengembangsebelah.calculatorrab.myutils;

import com.pengembangsebelah.calculatorrab.model.baru.KoefisienPk;

public interface OnClickCustomListener {
    interface Item{
        void OnClick();
        void LongClick();
    }
    interface Analisa{
        void OnClick(KoefisienPk koefisien);
    }
}
