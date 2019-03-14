package com.example.storeapplication.Model.Login_Register;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.storeapplication.ConnectInternet.DownloadJSON;
import com.example.storeapplication.View.Home.HomeActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class LoginModel {

    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;
    public AccessToken getCurrentFacebookToken(){

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };

        accessToken = AccessToken.getCurrentAccessToken();

        return accessToken;
    }

    public String getLoginCache(Context context){
        SharedPreferences loginCache = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        String name = loginCache.getString("name", "");
        return name;
    }

    public void updateLoginCache(Context context, String name){
        SharedPreferences loginCache = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginCache.edit();
        editor.putString("name", name);
        editor.commit();
    }

    public boolean checkLogin(Context context, String username, String password){
        boolean check = false;
        String path = HomeActivity.SERVERNAME + "WebStore/ProductType.php";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsFunction = new HashMap<>();
        hsFunction.put("myFunction", "checkLogin");

        HashMap<String,String> hsUsername = new HashMap<>();
        hsUsername.put("username", username);

        HashMap<String,String> hsPassword = new HashMap<>();
        hsPassword.put("password", password);

        attrs.add(hsFunction);
        attrs.add(hsUsername);
        attrs.add(hsPassword);

        DownloadJSON downloadJSON = new DownloadJSON(path, attrs);
        downloadJSON.execute();

        try {
            String data = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(data);
            String jsonResult = jsonObject.getString("result");
            if(jsonResult.equals("true")){
                check = true;
                String name = jsonObject.getString("name");
                updateLoginCache(context,name);
            }else{
                check = false;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return check;
    }


    public void cancelTokenTracker(){
        accessTokenTracker.stopTracking();
    }
}
