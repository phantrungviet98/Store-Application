package com.example.storeapplication.Presenter.Register;

import com.example.storeapplication.Model.Login_Register.RegisterModel;
import com.example.storeapplication.Model.OjectClass.Employee;
import com.example.storeapplication.View.LogIn_Register.ViewRegister;

public class PresenterLogicRegister implements IPresenterRegister {

    ViewRegister viewRegister;
    RegisterModel registerModel;


    public PresenterLogicRegister(ViewRegister viewRegister){
        this.viewRegister = viewRegister;
        registerModel = new RegisterModel();
    }

    @Override
    public void register(Employee employee) {
        Boolean check = registerModel.registerMember(employee);
        if(check){
            viewRegister.RegisterSuccessfuly();
        }
        else{
            viewRegister.RegisterFail();
        }
    }
}
