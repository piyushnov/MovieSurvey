package com.example.datastore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by piagarwa on 7/16/2015.
 */
public class PostgreSQL {

    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (null == conn) {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://ec2-107-20-223-116.compute-1.amazonaws.com:5432/dfni37lrqc3qa3", "scsarfozkixzaz",
                    "M--0mNeC5gLHzC-Iz1Pwd6qkm9");
        }
        return conn;
    }

    public static void closeConnection() throws SQLException {
        if (null != conn) {
            conn.close();
        }
        conn = null;
    }
}
