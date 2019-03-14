package com.example.storeapplication.View.LogIn_Register.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.storeapplication.Model.Login_Register.LoginModel;
import com.example.storeapplication.R;
import com.example.storeapplication.View.Home.HomeActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

public class FragmentLogIn extends Fragment {

    Button btnFacebookLogin, btnLogin;
    CallbackManager callbackManager;
    LoginModel loginModel;
    EditText edUsername, edPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view = (View) inflater.inflate(R.layout.layout_fragment_login,container,false);

        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent iHome = new Intent(getActivity(), HomeActivity.class);
                startActivity(iHome);
            }

            @Override
            public void onCancel() {


            }

            @Override
            public void onError(FacebookException error) {
                Log.d("kiemtra", "LOI");

            }
        });

        btnFacebookLogin = view.findViewById(R.id.btnFacebookLogin);
        btnLogin = view.findViewById(R.id.btnLogin);
        edUsername = view.findViewById(R.id.edEmail);
        edPassword = view.findViewById(R.id.edPassword);

        btnFacebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(FragmentLogIn.this, Arrays.asList("public_profile"));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                loginModel = new LoginModel();
                boolean check = loginModel.checkLogin(getActivity(),username,password);
                if(check == true){
                    Intent iHome = new Intent(getActivity(), HomeActivity.class);
                    startActivity(iHome);
                }else{
                    Toast.makeText(getActivity(), "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
