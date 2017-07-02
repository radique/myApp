package model;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import controller.HelperController;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbModel {

    private final String URL = "jdbc:mysql://localhost:3306/bookmarksapplication";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private Connection connection;
    public static int calls = 0;

    public DbModel() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.setLoginTimeout(1);
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            new HelperController().notificationHelper("Ошибка БД", "Не удалось зарегистрировать драйвер БД", 5);
            e.printStackTrace();
        }

        try {


            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            new HelperController().notificationHelper("Ошибка БД", "Не удалось установить соединение с БД", 5);
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        calls += 1;
        return connection;
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}

