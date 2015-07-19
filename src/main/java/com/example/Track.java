package com.example;

/**
 * Created by piagarwa on 7/16/2015.
 */
public class Track {

    String Name;
    String mail;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    @Override
    public String toString() {
        return "Track [Name=" + Name + ", mail=" + mail + "]";
    }
}
