package com.androidbuts.jsonparsing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Film {

    @SerializedName("CinemaName")
    @Expose
    private String cinemaName;
    @SerializedName("FilmName")
    @Expose
    private String filmName;
    @SerializedName("HallName")
    @Expose
    private String hallName;
    @SerializedName("DateTime")
    @Expose
    private String dateTime;
    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("SeatIsFree")
    @Expose
    private String seatIsFree;

    /**
     *
     * @return
     * The cinemaName
     */
    public String getCinemaName() {
        return cinemaName;
    }

    /**
     *
     * @param cinemaName
     * The CinemaName
     */
    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    /**
     *
     * @return
     * The filmName
     */
    public String getFilmName() {
        return filmName;
    }

    /**
     *
     * @param filmName
     * The FilmName
     */
    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    /**
     *
     * @return
     * The hallName
     */
    public String getHallName() {
        return hallName;
    }

    /**
     *
     * @param hallName
     * The HallName
     */
    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    /**
     *
     * @return
     * The dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     *
     * @param dateTime
     * The DateTime
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     *
     * @return
     * The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The Price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The seatIsFree
     */
    public String getSeatIsFree() {
        return seatIsFree;
    }

    /**
     *
     * @param seatIsFree
     * The SeatIsFree
     */
    public void setSeatIsFree(String seatIsFree) {
        this.seatIsFree = seatIsFree;
    }

}
