package com.androidbuts.jsonparsing.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidbuts.jsonparsing.R;
import com.androidbuts.jsonparsing.adapter.MyContactAdapter;
import com.androidbuts.jsonparsing.model.Contact;
import com.androidbuts.jsonparsing.model.ContactList;
import com.androidbuts.jsonparsing.model.Film;
import com.androidbuts.jsonparsing.model.FilmSessionList;
import com.androidbuts.jsonparsing.retrofit.api.ApiService;
import com.androidbuts.jsonparsing.retrofit.api.RetroClient;
import com.androidbuts.jsonparsing.utils.InternetConnection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    /**
     * Views
     */
    private ListView listView;
    private View parentView;

    private ArrayList<Film> filmSessionList;
    private MyContactAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * Array List for Binding Data from JSON to this List
         */
        filmSessionList = new ArrayList<>();

        parentView = findViewById(R.id.parentLayout);

        /**
         * Getting List and Setting List Adapter
         */
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Snackbar.make(parentView, filmSessionList.get(position).getName() + " => " + filmSessionList.get(position).getPhone().getHome(), Snackbar.LENGTH_LONG).show();
            }
        });

        /**
         * Just to know onClick and Printing Hello Toast in Center.
         */
        Toast toast = Toast.makeText(getApplicationContext(), R.string.string_click_to_load, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull final View view) {

                /**
                 * Checking Internet Connection
                 */
                if (InternetConnection.checkConnection(getApplicationContext())) {
                    final ProgressDialog dialog;
                    /**
                     * Progress Dialog for User Interaction
                     */
                    dialog = new ProgressDialog(MainActivity.this);
                    dialog.setTitle(getString(R.string.string_getting_json_title));
                    dialog.setMessage(getString(R.string.string_getting_json_message));
                    dialog.show();

                    //Creating an object of our api interface
                    ApiService api = RetroClient.getApiService();

                    /**
                     * Calling JSON
                     */
                    Call<FilmSessionList> call = api.getMyJSON();

                    /**
                     * Enqueue Callback will be call when get response...
                     */
                    call.enqueue(new Callback<FilmSessionList>() {
                        @Override
                        public void onResponse(Call<FilmSessionList> call, Response<FilmSessionList> response) {
                            //Dismiss Dialog
                            dialog.dismiss();

                            if(response.isSuccessful()) {
                                /**
                                 * Got Successfully
                                 */
                                filmSessionList = response.body().getFilmSessionList();

                                /**
                                 * Binding that List to Adapter
                                 */
                                adapter = new MyContactAdapter(MainActivity.this, filmSessionList);
                                listView.setAdapter(adapter);

                            } else {
                                Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<FilmSessionList> call, Throwable t) {
                            dialog.dismiss();
                        }
                    });

                } else {
                    Snackbar.make(parentView, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}