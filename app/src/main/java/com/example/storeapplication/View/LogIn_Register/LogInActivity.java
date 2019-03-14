package com.example.storeapplication.View.LogIn_Register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.storeapplication.Adapter.ViewPagerAdapterLogin;
import com.example.storeapplication.R;

public class LogInActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_log_in);

        tabLayout = findViewById(R.id.tabLogIn);
        viewPager = findViewById(R.id.viewPagerLogIn);
        toolbar = findViewById(R.id.toolBarLogIn);

        setSupportActionBar(toolbar);
        ViewPagerAdapterLogin viewPagerAdapterLogin = new ViewPagerAdapterLogin(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterLogin);
        viewPagerAdapterLogin.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);
    }
}
