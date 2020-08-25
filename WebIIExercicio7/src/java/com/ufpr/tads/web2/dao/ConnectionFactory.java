//AMDG
package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    
    public Connection getConnection() throws SQLException { 
            try {
                Class.forName("com.mysql.jdbc.Driver");
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/tarefa2?useSSL=false", "root", "");
            } 
            catch (SQLException | ClassNotFoundException ex) { 
                System.out.println("Erro ao conectar ao banco: " + ex);
                throw new RuntimeException(ex); 
            }
    }

}

