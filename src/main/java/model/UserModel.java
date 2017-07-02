package model;

import java.util.Date;

public class UserModel {
    private int id;
    private int user_id;
    private String name, surname, login;
    private byte[] password;
    private Date user_created, user_updated, user_info_created, user_info_updated;

    public UserModel() {
    }

    public UserModel(String login, byte[] password) {
        this.login = login;
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getUser_created() {
        return user_created;
    }

    public void setUser_created(Date user_created) {
        this.user_created = user_created;
    }

    public Date getUser_updated() {
        return user_updated;
    }

    public void setUser_updated(Date user_updated) {
        this.user_updated = user_updated;
    }

    public Date getUser_info_created() {
        return user_info_created;
    }

    public void setUser_info_created(Date user_info_created) {
        this.user_info_created = user_info_created;
    }

    public Date getUser_info_updated() {
        return user_info_updated;
    }

    public void setUser_info_updated(Date user_info_updated) {
        this.user_info_updated = user_info_updated;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Name: '" + this.name
                + "', Surname: '" + this.surname
                + "', login: '" + this.login
                + "', password: '" + this.password
                + "', id: '" + this.id
                + "', user_id: '" + this.user_id;
    }
}
