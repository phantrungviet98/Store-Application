package com.example.storeapplication.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.storeapplication.View.LogIn_Register.Fragment.FragmentLogIn;
import com.example.storeapplication.View.LogIn_Register.Fragment.FragmentRegister;

public class ViewPagerAdapterLogin extends FragmentPagerAdapter {
    public ViewPagerAdapterLogin(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                FragmentLogIn fragmentLogIn = new FragmentLogIn();
                return fragmentLogIn;
            case 1:
                FragmentRegister fragmentRegister = new FragmentRegister();
                return fragmentRegister;
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Đăng nhập";
            case 1:
                return "Đăng kí";
            default: return null;
        }

    }
}
