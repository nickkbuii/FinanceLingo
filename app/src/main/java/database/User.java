package database;

import java.util.ArrayList;
import java.util.Stack;

public class User {
    private String username;
    private String password;
    private String email;
    private static int userId;
    private int id;
    private Stack<Progress> progress;

    public User(String name, String password) {
        this.username = name;
        this.password = password;
        progress = new Stack<Progress>();
    }

    public User() {
        this.username ="";
        this.password = "";
        id = userId++;
    }

    //Getter setter
    public String getName() {
        return username;
    }
    public void setName(String name) {
        this.username = name;
    }
    public String getPw(){return password;};
    public int getId(){return id;};
    public String getEmail(){return email;}

    //Add Progress
    public void addProg(Progress prog){
        progress.push(prog);
    }

    public boolean checkComplete(){
        return progress.peek().isComplete();
    }




    @Override
    public String toString() {
        return "User{" +
                "name='" + username + '\'' +
                "password='" + password + '\'' + '}';
    }
}
