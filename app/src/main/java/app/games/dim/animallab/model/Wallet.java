package app.games.dim.animallab.model;

import java.util.Date;

import app.games.dim.animallab.tools.DurationFormater;

/**
 * Created by Igor on 09/02/2018.
 */

public class Wallet {

    private double account;
    private Date birth;
    private Date availability;

    public Wallet(){
        this.account = 25_000;
        this.birth = new Date();
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public Date getBirth() {
        return birth;
    }

    public Date getAvailability() {
        return availability;
    }

    public void setAvailability(Date availability) {
        this.availability = availability;
    }
}
