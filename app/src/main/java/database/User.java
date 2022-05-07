package database;

public class User {
    private String username;
    private String password;

    public User(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public User() {}

    //Getter setter
    public String getName() {
        return username;
    }
    public void setName(String name) {
        this.username = name;
    }
    public String getPw(){return password;};

    @Override
    public String toString() {
        return "User{" +
                "name='" + username + '\'' +
                "password='" + password + '\'' + '}';
    }
}
