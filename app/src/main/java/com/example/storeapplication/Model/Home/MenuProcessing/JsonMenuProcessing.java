package com.example.storeapplication.Model.Home.MenuProcessing;

import android.os.Bundle;
import android.util.Log;

import com.example.storeapplication.ConnectInternet.DownloadJSON;
import com.example.storeapplication.Model.OjectClass.ProductType;
import com.example.storeapplication.View.Home.HomeActivity;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class JsonMenuProcessing {
    String facebookUserName = "";

    public List<ProductType> parseJsonMenu(String jsonData){
        List<ProductType> productTypeList = new ArrayList<>();

        try {
            Log.d("jsonData", jsonData);
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray productType = jsonObject.getJSONArray("ProductType");
            int count = productType.length();
            for (int i = 0; i<count; i++){
                JSONObject value = productType.getJSONObject(i);

                ProductType productType1 = new ProductType();

                productType1.setProductTypeId(Integer.parseInt(value.getString("ProductTypeId")));
                productType1.setParentProductTypeId(Integer.parseInt(value.getString("ParentProductTypeId")));
                productType1.setProductTypeName(value.getString("ProductTypeName"));

                productTypeList.add(productType1);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return productTypeList;
    }

    public List<ProductType> getProductTypeFProductTypeId(int productTypeId){
        List<ProductType> productTypeList = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJson = "";

        String path = HomeActivity.SERVERNAME + "WebStore/ProductType.php";

        HashMap<String,String> hsFunction = new HashMap<>();
        hsFunction.put("myFunction", "getMenuList");

        HashMap<String,String> hsParentProductTypeId = new HashMap<>();
        hsParentProductTypeId.put("parentProductTypeId", String.valueOf(productTypeId));
        attrs.add(hsParentProductTypeId);
        attrs.add(hsFunction);

        DownloadJSON downloadJSON = new DownloadJSON(path, attrs);
        downloadJSON.execute();

        try {

            dataJson = downloadJSON.get();

            JsonMenuProcessing jsonMenuProcessing = new JsonMenuProcessing();
            productTypeList = jsonMenuProcessing.parseJsonMenu(dataJson);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productTypeList;
    }


}
