package com.androidbuts.jsonparsing.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.androidbuts.jsonparsing.R;

public class CinemaFragmentManager {

    public final String MAINPAGEFRAGMENT = "MAIN";
    public final String LOGINPAGEFRAGMENT = "LOGIN";
    public final String REGISTRATIONPAGEFRAGMENT = "REGISTRATION";
    public final String ORDERPAGEFRAGMENT = "ORDERS";
    public final String MOVIELISTFRAGMENT = "MOVIES";

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private View fragmentFrame;
    private Fragment movieListFragment;
    private Fragment loginPageFragment;
    private Fragment mainPageFragment;
    private Fragment registrationPageFragment;
    private Fragment newMainPageFragment;

    public CinemaFragmentManager(FragmentManager fm){
        this.manager = fm;
        //fragmentFrame = fragmentFrame.findViewById(R.id.main_frame);
        movieListFragment = null;
        loginPageFragment = null;
        mainPageFragment = null;
        registrationPageFragment = null;
        newMainPageFragment = null;
    }

    public void loadFragment(String fragmentName){

        switch(fragmentName){
            case MAINPAGEFRAGMENT: {
                if (newMainPageFragment == null)
                    newMainPageFragment = new NewMainPageFragmet();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.main_frame, newMainPageFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            }
            case LOGINPAGEFRAGMENT: {
                if (loginPageFragment == null)
                    loginPageFragment = new LoginPageFragment();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.main_frame, loginPageFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            }
            case REGISTRATIONPAGEFRAGMENT: {
                if (registrationPageFragment == null)
                    registrationPageFragment = new RegistrationPageFragment();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.main_frame, registrationPageFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            }
            case ORDERPAGEFRAGMENT: {
                if (newMainPageFragment == null)
                    newMainPageFragment = new NewMainPageFragmet();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.main_frame, newMainPageFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            }
            case MOVIELISTFRAGMENT: {
                if (movieListFragment == null)
                    movieListFragment = new MovieListFragment();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.main_frame, movieListFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            }
        }

    }

}
