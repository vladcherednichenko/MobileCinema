package com.androidbuts.jsonparsing.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;

import com.androidbuts.jsonparsing.R;
import com.androidbuts.jsonparsing.fragments.CinemaFragmentManager;
import com.androidbuts.jsonparsing.fragments.LoginPageFragment;
import com.androidbuts.jsonparsing.fragments.MainPageFragment;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoginPageFragment.LoginPageListener, MainPageFragment.MainPageListener {


    private FragmentTransaction transaction;
    private CinemaFragmentManager fragmentManager;
    private TextView menuName;
    private TextView menuLastName;


    @Override
    public void goToRegister() {
        fragmentManager.loadFragment(fragmentManager.REGISTRATIONPAGEFRAGMENT);
    }
    @Override
    public void loadCinema() {
        fragmentManager.loadFragment(fragmentManager.MOVIELISTFRAGMENT);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menuName = (TextView)findViewById(R.id.menu_name);
        menuLastName = (TextView)findViewById(R.id.menu_lastname);

        //new fragment manager
        fragmentManager = new CinemaFragmentManager(getSupportFragmentManager());
        fragmentManager.loadFragment(fragmentManager.MAINPAGEFRAGMENT);

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
        int id = item.getItemId();

        if (id == R.id.nav_log_in) {
            fragmentManager.loadFragment(fragmentManager.LOGINPAGEFRAGMENT);
        } else if (id == R.id.nav_home) {
            fragmentManager.loadFragment(fragmentManager.MAINPAGEFRAGMENT);
        } else if (id == R.id.nav_orders) {
            fragmentManager.loadFragment(fragmentManager.ORDERPAGEFRAGMENT);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
