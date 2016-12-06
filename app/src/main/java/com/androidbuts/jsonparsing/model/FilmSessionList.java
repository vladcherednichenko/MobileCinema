package com.androidbuts.jsonparsing.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FilmSessionList {

    @SerializedName("FilmSessionList")
    @Expose
    private ArrayList<Film> filmSessionList = new ArrayList<Film>();

    /**
     *
     * @return
     * The filmSessionList
     */
    public ArrayList<Film> getFilmSessionList() {
        return filmSessionList;
    }

    /**
     *
     * @param filmSessionList
     * The FilmSessionList
     */
    public void setFilmSessionList(ArrayList<Film> filmSessionList) {
        this.filmSessionList = filmSessionList;
    }

}

