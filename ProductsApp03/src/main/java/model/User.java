package model;


public class User {

    public final static String SEPARATOR = "#";

    private Integer id;
    private String login;
    private String password;

    public User(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return id + SEPARATOR + login + SEPARATOR + password;
    }
}

