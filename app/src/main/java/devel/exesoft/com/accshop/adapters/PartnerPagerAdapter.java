package devel.exesoft.com.accshop.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import devel.exesoft.com.accshop.fragments.PartnerTab1;
import devel.exesoft.com.accshop.fragments.PartnerTab2;
import devel.exesoft.com.accshop.fragments.PartnerTab3;


public class PartnerPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    private String titles[] = new String[] { "Позиции", "Продажи", "Долги" };

    public PartnerPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0 : return  new PartnerTab1();
            case 1 : return  new PartnerTab2();
            case 2 : return  new PartnerTab3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
