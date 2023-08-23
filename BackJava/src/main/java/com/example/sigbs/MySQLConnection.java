package com.example.sigbs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MySQLConnection {
    private static final String URL = "jdbc:mysql://root:j47KsvnV69ATXigNOpC0@containers-us-west-205.railway.app:5770/railway";
    private static final String USER = "root";
    private static final String PASSWORD = "j47KsvnV69ATXigNOpC0";

    private Connection connection;

    public MySQLConnection() {
        try {
            // Carga el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver de MySQL: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        MySQLConnection dbConnection = new MySQLConnection();

        // Realiza operaciones en la base de datos utilizando dbConnection.getConnection()

        dbConnection.closeConnection();
    }
}