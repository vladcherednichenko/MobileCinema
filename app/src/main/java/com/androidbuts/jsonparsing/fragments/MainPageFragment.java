package com.androidbuts.jsonparsing.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.androidbuts.jsonparsing.R;
import com.androidbuts.jsonparsing.adapter.CinemaAdapter;
import com.androidbuts.jsonparsing.model.Film;
import com.androidbuts.jsonparsing.model.FilmSessionList;
import com.androidbuts.jsonparsing.retrofit.api.API;
import com.androidbuts.jsonparsing.retrofit.api.RetroClient;
import com.androidbuts.jsonparsing.utils.InternetConnection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPageFragment extends Fragment {

    private int tabNumber;
    private ListView listView;
    private View parentView;
    private ArrayList<Film> cinemas;
    private ArrayList<Film> filmSessionList;
    private CinemaAdapter adapter;

    MainPageFragment.MainPageListener MainPageCommander;
    public interface MainPageListener{
        public void loadCinema();
    }

    public void setTabNumber(int number){
        this.tabNumber = number;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            MainPageCommander = (MainPageFragment.MainPageListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page, null);

        /**
         * Array List for Binding Data from JSON to this List
         */
        filmSessionList = new ArrayList<>();
        cinemas = new ArrayList<>();
        parentView = view.findViewById(R.id.drawer_layout);
        /**
         * Getting List and Setting List Adapter
         */
        listView = (ListView) view.findViewById(R.id.list_cinema);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MainPageCommander.loadCinema();

//                Snackbar.make(parentView, filmSessionList.get(position).getName() + " => " + filmSessionList.get(position).getPhone().getHome(), Snackbar.LENGTH_LONG).show();
            }
        });

        downloadData();
        return view;
    }
    private void setCinemaList(){
        Film cinema;
        int c=0;

        for (int i = 0; i< filmSessionList.size(); i++){
            c = 0;
            for(int j = 0; j< cinemas.size(); j++){
                if (cinemas.get(j).getCinemaName().equals(filmSessionList.get(i).getCinemaName()))
                    c = 1;


            }
            if(c == 0)
            cinemas.add(filmSessionList.get(i));
        }
    }
    private void downloadData(){
        if (InternetConnection.checkConnection(this.getActivity().getApplicationContext())) {
            final ProgressDialog dialog;

        //System.out.print("downloading data");
//            dialog = new ProgressDialog(this.getContext());
//            dialog.setTitle(getString(R.string.string_getting_json_title));
//            dialog.setMessage(getString(R.string.string_getting_json_message));
//            dialog.show();

            //Creating an object of our api interface
            API api = RetroClient.getApiService();

            //calling json
            Call<FilmSessionList> call = api.getMyJSON();

             //Enqueue Callback will be call when get response...
            call.enqueue(new Callback<FilmSessionList>() {
                @Override
                public void onResponse(Call<FilmSessionList> call, Response<FilmSessionList> response) {
                    //Dismiss Dialog
                    //dialog.dismiss();

                    if(response.isSuccessful()) {
                        filmSessionList = response.body().getFilmSessionList();
                        setCinemaList();
                        //adapter = new CinemaAdapter(getContext(), filmSessionList);
                        adapter = new CinemaAdapter(getContext(), cinemas);
                        listView.setAdapter(adapter);

                    } else {
                        //Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<FilmSessionList> call, Throwable t) {
                    //dialog.dismiss();
                }
            });

        } else {
            Snackbar.make(parentView, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();
        }
    }

}
