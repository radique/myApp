package model;

import controller.AlertBoxController;
import controller.HelperController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;


public class RegisterModel {

    private static DbModel DBModel;
    private static final java.sql.Date DATE_MILS = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
    private static final String INSERT_NEW_USER = "INSERT INTO users (login,password,created,updated) VALUES(?,?,?,?)";
    private static final String INSERT_NEW_USERS_INFO = "INSERT INTO user_info (user_id, name, surname, created, updated) VALUES (?,?,?,?,?)";
    private static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String GET_USER_INFO = "SELECT user_info.id, user_id,name,surname," +
            "user_info.created AS user_info_created," +
            "user_info.updated AS user_info_updated, " +
            "users.created AS user_created," +
            "users.updated AS user_updated\n" +
            "FROM user_info INNER JOIN users ON user_info.user_id =users.id\n" +
            "WHERE user_info.user_id=?";


    public UserModel login(String login, String input_pass) {
        System.out.println(login + input_pass);
        try {
            byte[] db_pass = new byte[0];
            int db_id = 0;
            UserModel userModel = new UserModel();
            DBModel = new DbModel();
            if (DBModel.getConnection() != null) {
                PreparedStatement userIdStatement = DBModel.getConnection().prepareStatement(GET_USER_BY_LOGIN);
                userIdStatement.setString(1, login);
                ResultSet resUserId = userIdStatement.executeQuery();
                while (resUserId.next()) {
                    db_pass = resUserId.getBytes("password");
                    db_id = resUserId.getInt("id");
                }
                userIdStatement.close();
                if (EncryptModel.CheckHash(input_pass, db_pass)) {
                    PreparedStatement userStatement = DBModel.getConnection().prepareStatement(GET_USER_INFO);
                    userStatement.setInt(1, db_id);
                    ResultSet res = userStatement.executeQuery();

                    while (res.next()) {
                        int id = res.getInt("id");
                        int user_id = res.getInt("user_id");
                        String name = res.getString("name");
                        String surname = res.getString("surname");
                        Date user_info_created = res.getDate("user_info_created");
                        Date user_info_updated = res.getDate("user_info_updated");
                        Date users_created = res.getDate("user_created");
                        Date users_updated = res.getDate("user_updated");

                        userModel.setId(id);
                        userModel.setUser_id(user_id);
                        userModel.setName(name);
                        userModel.setSurname(surname);
                        userModel.setLogin(login);
                        userModel.setPassword(db_pass);
                        userModel.setUser_info_created(user_info_created);
                        userModel.setUser_info_updated(user_info_updated);
                        userModel.setUser_created(users_created);
                        userModel.setUser_updated(users_updated);
                    }
                    res.close();
                    return userModel;
                } else {
                    new AlertBoxController().init("При авторизации произошла ошибка, проверьте введенные данные");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new HelperController().notificationHelper("Ошибка БД", "При выполнении sql запроса произошла ошибка", 5);
        }
        return null;
    }

    public static boolean registration(String login, String password, String name, String surname) {
        DBModel = new DbModel();
        try {
            int db_id = 0;
            //Creating new user
            PreparedStatement preparedStatement = DBModel.getConnection().prepareStatement(INSERT_NEW_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setBytes(2, EncryptModel.Encryption(password));
            preparedStatement.setDate(3, DATE_MILS);
            preparedStatement.setDate(4, DATE_MILS);
            preparedStatement.execute();
            preparedStatement.close();

            //Getting new user id
            PreparedStatement userIdStatement = DBModel.getConnection().prepareStatement(GET_USER_BY_LOGIN);
            userIdStatement.setString(1, login);
            ResultSet resUserId = userIdStatement.executeQuery();
            while (resUserId.next()) {
                db_id = resUserId.getInt("id");
            }
            userIdStatement.close();

            //Adding new user info
            PreparedStatement prepstmtUserInfo = DBModel.getConnection().prepareStatement(INSERT_NEW_USERS_INFO);
            prepstmtUserInfo.setInt(1, db_id);
            prepstmtUserInfo.setString(2, name);
            prepstmtUserInfo.setString(3, surname);
            prepstmtUserInfo.setDate(4, DATE_MILS);
            prepstmtUserInfo.setDate(5, DATE_MILS);
            prepstmtUserInfo.execute();
            prepstmtUserInfo.close();

            return true;
        } catch (SQLException e) {
            System.out.println("При регистрации произошла ошибка");
        }
        return false;
    }
}
