package com.example.storeapplication.View.Home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.example.storeapplication.Adapter.ExpandAdapter;
import com.example.storeapplication.Adapter.ViewPagerApdapter;
import com.example.storeapplication.Model.Login_Register.LoginModel;
import com.example.storeapplication.Model.OjectClass.ProductType;
import com.example.storeapplication.Presenter.Home.MenuProcessing.PresenterLogicMenuProcessing;
import com.example.storeapplication.R;
import com.example.storeapplication.View.LogIn_Register.LogInActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements ViewMenuProcessing {

    public static final String SERVERNAME = "http://192.168.56.1:8080/";

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    PresenterLogicMenuProcessing presenterLogicMenuProcessing;
    String facebookUserName = "";
    AccessToken accessToken;
    Menu menu;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    LoginModel loginModel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.home_layout);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        drawerLayout = findViewById(R.id.drawerLayout);
        expandableListView = findViewById(R.id.epMenu);
        appBarLayout = findViewById(R.id.appbar);
        collapsingToolbarLayout = findViewById(R.id.collap_toolbar);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();

        ViewPagerApdapter adapter = new ViewPagerApdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        presenterLogicMenuProcessing = new PresenterLogicMenuProcessing(this);
        presenterLogicMenuProcessing.getMenuList();
        presenterLogicMenuProcessing.getFacebookUserName();

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu, menu);
        this.menu = menu;

        accessToken = presenterLogicMenuProcessing.getFacebookUserName();
        if(accessToken != null){
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        facebookUserName = object.getString("name");
                        menu.findItem(R.id.itLogin).setTitle(facebookUserName);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            Bundle parameter = new Bundle();
            parameter.putString("fields","name");
            graphRequest.setParameters(parameter);
            graphRequest.executeAsync();
        }

        loginModel = new LoginModel();

        String name = loginModel.getLoginCache(this);
        if(!name.equals("")){
            menu.findItem(R.id.itLogin).setTitle(name);
        }

        if(accessToken != null || !name.equals("")){
            MenuItem menuItemLogout = menu.findItem(R.id.itLogout);
            menuItemLogout.setVisible(true);
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        int id = item.getItemId();
        switch (id){
            case R.id.itLogin:
                if(accessToken == null && loginModel.getLoginCache(this).equals("")){
                    Intent iLogIn = new Intent(this, LogInActivity.class);
                    startActivity(iLogIn);
                };break;
            case R.id.itLogout:
                if(accessToken != null){
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }

                if(!loginModel.getLoginCache(this).equals("")){
                    loginModel.updateLoginCache(this,"");
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayMenuList(List<ProductType> productTypeList) {
        ExpandAdapter expandAdapter = new ExpandAdapter(this,productTypeList);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }
}
