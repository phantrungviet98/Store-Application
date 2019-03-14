package com.example.storeapplication.Presenter.Home.MenuProcessing;

import com.example.storeapplication.ConnectInternet.DownloadJSON;
import com.example.storeapplication.Model.Home.MenuProcessing.JsonMenuProcessing;
import com.example.storeapplication.Model.Login_Register.LoginModel;
import com.example.storeapplication.Model.OjectClass.ProductType;
import com.example.storeapplication.View.Home.HomeActivity;
import com.example.storeapplication.View.Home.ViewMenuProcessing;
import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PresenterLogicMenuProcessing implements IPresenterMenuProcessing{

    ViewMenuProcessing viewMenuProcessing;
    public PresenterLogicMenuProcessing(ViewMenuProcessing viewMenuProcessing){
        this.viewMenuProcessing = viewMenuProcessing;
    }

    @Override
    public void getMenuList() {

        List<ProductType> productTypeList;
        String dataJson = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();



        String path = HomeActivity.SERVERNAME +  "WebStore/ProductType.php";

        HashMap<String,String> hsFunction = new HashMap<>();
        hsFunction.put("myFunction", "getMenuList");



        HashMap<String,String> hsParentProductTypeId = new HashMap<>();
        hsParentProductTypeId.put("parentProductTypeId", "0");
        attrs.add(hsParentProductTypeId);
        attrs.add(hsFunction);


        DownloadJSON downloadJSON = new DownloadJSON(path, attrs);
        downloadJSON.execute();

        try {

            dataJson = downloadJSON.get();

            JsonMenuProcessing jsonMenuProcessing = new JsonMenuProcessing();
            productTypeList = jsonMenuProcessing.parseJsonMenu(dataJson);
            viewMenuProcessing.displayMenuList(productTypeList);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccessToken getFacebookUserName() {
        LoginModel loginModel = new LoginModel();
        AccessToken accessToken = loginModel.getCurrentFacebookToken();




        return accessToken;
    }
}
