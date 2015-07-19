package com.example.model;

import com.example.Track;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by piagarwa on 7/16/2015.
 */
public class User {
    private static long counter = 0;

    public static synchronized long nextId() {
        return ++counter;
    }
}
