package com.pengembangsebelah.calculatorrab.ui.adapte;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pengembangsebelah.calculatorrab.ui.fragment.AnalisaActivity;
import com.pengembangsebelah.calculatorrab.ui.fragment.HargaSatuanFragment;
import com.pengembangsebelah.calculatorrab.ui.fragment.HomeFragment;

public class SectionHomeAdapter extends FragmentPagerAdapter {
    int ai = 0;
    public SectionHomeAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
        ai=i;
        if(i==0){
            return HomeFragment.newInstance(1);
        }else if(i==1){
            return AnalisaActivity.newInstance(1);
        }else if(i==2){
            //return TVFragment.newInstance(1);
            return HargaSatuanFragment.newInstance(1);
            //}else if(i==3){
            //return PromoFragment.newInstance(1);
        }else {
            return HomeFragment.newInstance(1);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}