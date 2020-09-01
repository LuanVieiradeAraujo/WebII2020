package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
    public static Connection getConnection() { 
        try {
            Class.forName("org.postgresql.Driver");
            //return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "exercicioWeb", "admin");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "exercicioWeb", "*damiani*tech*");
        } 
        catch (SQLException | ClassNotFoundException ex) { 
            System.out.println("Erro ao conectar ao banco: " + ex);
            throw new RuntimeException(ex); 
        }
    }
    
    static ResultSet getResultSet(Connection conn, String sql) throws SQLException {
        Statement statament = conn.createStatement();
        return statament.executeQuery(sql);
    }

    static PreparedStatement getPreparedStatement(Connection conn, String sql) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(sql);
        return statement;
    }
}

