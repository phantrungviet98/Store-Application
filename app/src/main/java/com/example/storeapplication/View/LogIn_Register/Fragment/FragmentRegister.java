package com.example.storeapplication.View.LogIn_Register.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.storeapplication.Model.OjectClass.Employee;
import com.example.storeapplication.Presenter.Register.PresenterLogicRegister;
import com.example.storeapplication.R;
import com.example.storeapplication.View.LogIn_Register.ViewRegister;

import java.util.regex.Pattern;

public class FragmentRegister extends Fragment implements ViewRegister {

    PresenterLogicRegister presenterLogicRegister;
    Button btnRegister;
    EditText edName, edPassword, edRePassword, edMail;
    TextInputLayout input_edName, input_edPassword, input_edRePassword, input_edMail;
    Boolean validateInfor = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_register,container, false);

        btnRegister = view.findViewById(R.id.btnRegister);
        edName = view.findViewById(R.id.edNameRegister);
        edPassword = view.findViewById(R.id.edPasswordRegister);
        edRePassword = view.findViewById(R.id.edRePasswordRegister);
        edMail = view.findViewById(R.id.edMailRegister);
        input_edName = view.findViewById(R.id.input_edNameRegister);
        input_edPassword = view.findViewById(R.id.input_edPasswordRegister);
        input_edRePassword = view.findViewById(R.id.input_edRePasswordRegister);
        input_edMail = view.findViewById(R.id.input_edMailRegister);

        presenterLogicRegister = new PresenterLogicRegister(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegister();
            }
        });

        edName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    String s = ((EditText)view).getText().toString();
                    if(s.trim().equals("") || s.equals(null)){
                        input_edName.setErrorEnabled(true);
                        input_edName.setError("Bạn chưa nhập mục này");
                        validateInfor = false;
                    }else{
                        input_edName.setErrorEnabled(false);
                        input_edName.setError("");
                        validateInfor = true;
                    }
                }
            }
        });
        edPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    String s = ((EditText)view).getText().toString();
                    if(s.trim().equals("") || s.equals(null)){
                        input_edPassword.setErrorEnabled(true);
                        input_edPassword.setError("Bạn chưa nhập mục này");
                        validateInfor = false;
                    }else{
                        if(s.length()!=6){
                            input_edPassword.setErrorEnabled(true);
                            input_edPassword.setError("Mật khẩu gồm 6 kí tự và 1 chữ hoa");
                            validateInfor = false;
                        }else{
                            input_edPassword.setErrorEnabled(false);
                            input_edPassword.setError("");
                            validateInfor = true;
                        }



                    }
                }
            }
        });

        edRePassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    String rePass = ((EditText) view).getText().toString();
                    String pass = edPassword.getText().toString();
                    if (!rePass.equals(pass)) {
                        input_edRePassword.setErrorEnabled(true);
                        input_edRePassword.setError("Mật khẩu không trùng khớp");
                        validateInfor = false;
                    } else {
                        input_edRePassword.setErrorEnabled(false);
                        input_edRePassword.setError("");
                        validateInfor = true;
                    }
                }
            }
        });

        edMail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    String s = ((EditText)view).getText().toString();
                    Boolean checkEmail = Patterns.EMAIL_ADDRESS.matcher(s).matches();
                    if(s.trim().equals("") || s.equals(null)){
                        input_edMail.setErrorEnabled(true);
                        input_edMail.setError("Bạn chưa nhập mục này");
                        validateInfor = false;
                    }else{
                        if(!checkEmail){
                            input_edMail.setErrorEnabled(true);
                            input_edMail.setError("Kiểm tra lại địa chỉ email");
                            validateInfor = false;
                        }else{
                            input_edMail.setErrorEnabled(false);
                            input_edMail.setError("");
                            validateInfor = true;
                        }


                    }
                }
            }
        });

        return view;
    }

    private void btnRegister(){
        String name = edName.getText().toString();
        String email = edMail.getText().toString();
        String password = edPassword.getText().toString();
        String rePassword = edRePassword.getText().toString();

        if(validateInfor = true) {
            Employee employee = new Employee();
            employee.setName(name);
            employee.setUsername(email);
            employee.setPassword(password);
            employee.setEmployeeTypeId(2);

            presenterLogicRegister.register(employee);
        }
    }

    @Override
    public void RegisterSuccessfuly() {
        Toast.makeText(getActivity(), "Đăng kí thành công", Toast.LENGTH_LONG).show();
    }

    @Override
    public void RegisterFail() {
        Toast.makeText(getActivity(), "Đăng kí thất bại", Toast.LENGTH_LONG).show();
    }
}
