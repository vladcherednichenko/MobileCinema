package com.androidbuts.jsonparsing.fragments;

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

import com.androidbuts.jsonparsing.R;
import com.androidbuts.jsonparsing.adapter.CinemaAdapter;
import com.androidbuts.jsonparsing.model.RegistrationResponse;
import com.androidbuts.jsonparsing.model.UserAccount;
import com.androidbuts.jsonparsing.retrofit.api.API;
import com.androidbuts.jsonparsing.utils.InternetConnection;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;


public class RegistrationPageFragment extends Fragment{


    private View parentView;
    private Button signInButton;
    private EditText name;
    private EditText lastName;
    private EditText email;
    private EditText login;
    private EditText password;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration_page, null);

        signInButton = (Button)view.findViewById(R.id.sign_in_button);
        name = (EditText)view.findViewById(R.id.registration_name);
        lastName = (EditText)view.findViewById(R.id.registration_lastname);
        email = (EditText)view.findViewById(R.id.registration_email);
        login = (EditText)view.findViewById(R.id.registration_login);
        password = (EditText)view.findViewById(R.id.registration_password);
        this.parentView = view;

        signInButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        signIn();
                    }
                }
        );
        return view;
    }

    private void signIn(){

        UserAccount userAccount = new UserAccount(
                login.getText().toString(),
                name.getText().toString(),
                lastName.getText().toString(),
                email.getText().toString(),
                password.getText().toString(),
                password.getText().toString());
        //UserAccount userAccount = new UserAccount("login","name","lastname","email","pass","pass");


        if (InternetConnection.checkConnection(this.getActivity().getApplicationContext())) {
            final ProgressDialog dialog;

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.108.121:56666")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            API api = retrofit.create(API.class);

            Call<RegistrationResponse> call = api.registerUser(userAccount);
            call.enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    if(response.isSuccessful()) {

//                        JsonObject go = new Gson().fromJson(response.toString(), JsonObject.class);
//                        Snackbar.make(parentView, go.get("response").getAsString(), Snackbar.LENGTH_LONG).show();
                        String res = response.body().getResponse();

                        Snackbar.make(parentView, res, Snackbar.LENGTH_LONG).show();


                    } else {
                        Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                    }

                }
                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {

                }
            });

            api.registerUser(userAccount);
        }else
            Snackbar.make(parentView, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();
    }
}
