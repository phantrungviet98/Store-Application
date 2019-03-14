package com.example.storeapplication.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.storeapplication.View.Home.Fragment.BranchFragment;
import com.example.storeapplication.View.Home.Fragment.ElectronicFragment;
import com.example.storeapplication.View.Home.Fragment.FashionFragment;
import com.example.storeapplication.View.Home.Fragment.HotFragment;
import com.example.storeapplication.View.Home.Fragment.HouseAndLifeFragment;
import com.example.storeapplication.View.Home.Fragment.MakeupFragment;
import com.example.storeapplication.View.Home.Fragment.MotherAndBabyFragment;
import com.example.storeapplication.View.Home.Fragment.PromotionFragment;
import com.example.storeapplication.View.Home.Fragment.SportAndTralvelFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerApdapter extends FragmentPagerAdapter {

    List<Fragment> listFragment = new ArrayList<Fragment>();
    List<String> titleFragment = new ArrayList<String>();

    public ViewPagerApdapter(FragmentManager fm) {
        super(fm);

        listFragment.add(new HotFragment());
        listFragment.add(new PromotionFragment());
        listFragment.add(new ElectronicFragment());
        listFragment.add(new HouseAndLifeFragment());
        listFragment.add(new MotherAndBabyFragment());
        listFragment.add(new MakeupFragment());
        listFragment.add(new FashionFragment());
        listFragment.add(new SportAndTralvelFragment());
        listFragment.add(new BranchFragment());

        titleFragment.add("Nổi bật");
        titleFragment.add("Chương trình khuyến mãi");
        titleFragment.add("Điện tử");
        titleFragment.add("Nhà cửa và đời sống");
        titleFragment.add("Mẹ và bé");
        titleFragment.add("Làm đẹp");
        titleFragment.add("Thời trang");
        titleFragment.add("Thể thao và du lịch");
        titleFragment.add("Thương hiệu");
    }

    @Override
    public Fragment getItem(int i) {
        return listFragment.get(i);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}
