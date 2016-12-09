package com.androidbuts.jsonparsing.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidbuts.jsonparsing.R;
import com.androidbuts.jsonparsing.adapter.CinemaAdapter;
import com.androidbuts.jsonparsing.fragments.LoginPageFragment;
import com.androidbuts.jsonparsing.model.Film;
import com.androidbuts.jsonparsing.model.FilmSessionList;
import com.androidbuts.jsonparsing.retrofit.api.ApiService;
import com.androidbuts.jsonparsing.retrofit.api.RetroClient;
import com.androidbuts.jsonparsing.utils.InternetConnection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.androidbuts.jsonparsing.R;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager mfragment;

    private ListView listView;
    private View parentView;
    private ArrayList<Film> filmSessionList;
    private CinemaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
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
                    dialog = new ProgressDialog(StartActivity.this);
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
                                adapter = new CinemaAdapter(StartActivity.this, filmSessionList);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.nav_log_in) {
            fragment = new LoginPageFragment();
            // Handle the camera action
        } else if (id == R.id.nav_log_in) {

            Intent intent = new Intent(StartActivity.this, StartActivity.class);
            startActivity(intent);
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
