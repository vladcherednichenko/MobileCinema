package com.androidbuts.jsonparsing.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidbuts.jsonparsing.R;
import com.androidbuts.jsonparsing.model.RegistrationResponse;
import com.androidbuts.jsonparsing.model.user.AllUserData;
import com.androidbuts.jsonparsing.model.user.LoginData;
import com.androidbuts.jsonparsing.retrofit.api.API;
import com.androidbuts.jsonparsing.utils.InternetConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPageFragment extends Fragment{

    private EditText login;
    private EditText password;
    private Button loginButton;
    private TextView registerText;
    private View parentView;

    LoginPageListener LoginPageCommander;
    public interface LoginPageListener{
        public void goToRegister();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            LoginPageCommander = (LoginPageListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_page, null);

        loginButton = (Button)view.findViewById(R.id.loginButton);
        registerText = (TextView) view.findViewById(R.id.registerText);
        login = (EditText)view.findViewById(R.id.login);
        password = (EditText)view.findViewById(R.id.password);
        this.parentView = view;

        registerText.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LoginPageCommander.goToRegister();
                    }
                }
        );

        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        login();
                    }
                }

        );

        return view;
    }

    private void login(){
        //sending request for login
        if (InternetConnection.checkConnection(this.getActivity().getApplicationContext())) {
            final ProgressDialog dialog;

            LoginData loginData = new LoginData(login.getText().toString(), password.getText().toString(), "password");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.108.121:56666")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            API api = retrofit.create(API.class);

            Call<AllUserData> call = api.singUserIn(loginData);
            call.enqueue(new Callback<AllUserData>() {
                @Override
                public void onResponse(Call<AllUserData> call, Response<AllUserData> response) {
                    if(response.isSuccessful()) {

                        //String res = response.body().getResponse();

                        Snackbar.make(parentView, response.body().getFirstName()+" singed in", Snackbar.LENGTH_LONG).show();


                    } else {
                        Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                    }

                }
                @Override
                public void onFailure(Call<AllUserData> call, Throwable t) {

                }
            });

            api.singUserIn(loginData);
        }else
            Snackbar.make(parentView, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();
    }


}
