package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by diego.almeida on 02/05/2017.
 */
public class SimpleFragmentPageAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[]{"NUMBERS","COLORS","FAMILY","PHRASES"};

    public SimpleFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return new NumbersFragment();
        }else if(position == 1){
            return new ColorsFragment();
        }else if(position == 2){
            return new FamilysFragment();
        }else if(position == 3){
            return new PhrasesFragment();
        }else{
            return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
