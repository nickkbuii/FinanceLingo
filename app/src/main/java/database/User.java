package database;

import java.util.ArrayList;
import java.util.Stack;

public class User {
    private String username;
    private String password;
    private String email;
    private String id;
    private Stack<Progress> progress;

    public User(String name, String password) {
        this.username = name;
        this.password = password;
        progress = new Stack<Progress>();
    }

    public User() {
        this.username ="";
        this.password = "";
    }

    //Getter
    public String getName() {
        return username;
    }
    public String getPw(){return password;};
    public String getId(){return id;};
    public String getEmail(){return email;}

    //Setter
    public void setName(String name) {
        this.username = name;
    }
    public void setId(String id){this.id = id;}

    //Temporary
    //Add Progress
    public void addProg(Progress prog){
        progress.push(prog);
    }

    public boolean checkComplete(){
        return progress.peek().isComplete();
    }
    //Temporary



    @Override
    public String toString() {
        return "User{" +
                "name='" + username + '\'' +
                "password='" + password + '\'' + '}';
    }
}
