package database;

import java.util.ArrayList;
import java.util.Stack;

public class User {
    private String username;
    private String password;
    private String email;
    private String id;

    //Budgeting scores
    private int qNum;
    private int qScore;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public User(String username, String password, String email, String id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.id = id;
    }

    public User() {
        this.username = "";
        this.password = "";
        this.email = "";
    }

    //Getter
    public String getUsername() {
        return username;
    }
    //public String getPw(){return password;};
    public String getId(){return id;};
    public String getEmail(){return email;}

    //Setter
    public void setUsername(String name) {
        this.username = name;
    }
    public void setId(String id){this.id = id;}
    public void setQNum(int num){this.qNum = num;}
    public void setQScore(int num){this.qScore = num;}

    public String pw(){
        return password;
    }
    public int qNum(){return this.qNum;}
    public int qScore(){return this.qScore;}

    @Override
    public String toString() {
        return "User{" +
                "name='" + username + '\'' +
                "password='" + password + '\'' + '}';
    }
}
