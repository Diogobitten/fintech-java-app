package br.com.fiap.fintech.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager connectionManager;

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

     public Connection getConnection() {
        Connection connection = null;

         try {
             Class.forName("oracle.jdbc.driver.OracleDriver");
             connection = DriverManager.getConnection(
                     "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                     "RM555027",
                     "200306"
             );
             System.out.println("Senha recebida: " + connection);

         } catch (ClassNotFoundException e) {
             throw new RuntimeException(e);
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
         return connection;
     }
}