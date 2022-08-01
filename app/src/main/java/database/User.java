package database;

import java.util.ArrayList;
import java.util.Stack;

public class User {

    //initializing username, pw, email, id variables
    private String username;
    private String password;
    private String email;
    private String id;

    //Budgeting scores
    private int qNum;
    private int qScore;

    //defines username, email, id, password
    public User(String username, String email, String id) {
        this.username = username;
        this.email = email;
        this.id = id;
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
    public void setPw(String pw){this.password = pw;}
    public void setEmail(String email){this.email = email;}

    //methods to return question number, score, pw
    public String pw(){
        return password;
    }
    public int qNum(){return this.qNum;}
    public int qScore(){return this.qScore;}

    //changes username, pw to proper format
    @Override
    public String toString() {
        return "User{" +
                "name='" + username + '\'' +
                "password='" + password + '\'' + '}';
    }

}
