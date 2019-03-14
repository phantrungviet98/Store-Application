package com.example.storeapplication.Model.Login_Register;

import android.util.Log;

import com.example.storeapplication.ConnectInternet.DownloadJSON;
import com.example.storeapplication.Model.OjectClass.Employee;
import com.example.storeapplication.View.Home.HomeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RegisterModel {
    public Boolean registerMember(Employee employee){
        String path = HomeActivity.SERVERNAME + "/WebStore/ProductType.php";
        Boolean check = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String, String> hsFunction = new HashMap<>();
        hsFunction.put("myFunction", "registerMember");

        HashMap<String, String> hsEmployeeName = new HashMap<>();
        hsEmployeeName.put("memberName", employee.getName());

        HashMap<String, String> hsUsername = new HashMap<>();
        hsUsername.put("memberUsername", employee.getUsername());

        HashMap<String, String> hsPassword = new HashMap<>();
        hsPassword.put("memberPassword", employee.getPassword());

        HashMap<String, String> hsEmployeeTypeId = new HashMap<>();
        hsEmployeeTypeId.put("employeeTypeId", String.valueOf(employee.getEmployeeTypeId()));

        attrs.add(hsEmployeeName);
        attrs.add(hsUsername);
        attrs.add(hsPassword);
        attrs.add(hsEmployeeTypeId);
        attrs.add(hsFunction);

        DownloadJSON downloadJSON = new DownloadJSON(path,attrs);
        downloadJSON.execute();


        try {
            String jsonData = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(jsonData);
            String result = jsonObject.getString("result");
            if(result.equals("true")){
                check = true;
            }else{
                check =  false;
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
}
