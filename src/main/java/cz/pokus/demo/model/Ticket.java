package cz.pokus.demo.model;

import java.util.Date;

public class Ticket {
    private int id;
    private Date date;
    // to jeste na to mohu udelat zvlast tridu Movie, atd. nebo tak neco
    private String movie;
    private String language;

    public Ticket(int id, Date date, String movie, String language) {
        this.id = id;
        this.date = date;
        this.movie = movie;
        this.language = language;
    }
    public Ticket(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
