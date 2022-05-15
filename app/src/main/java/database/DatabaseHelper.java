package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    //Creates new Table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String stmnt = "CREATE TABLE USERS (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "USERNAME TEXT, " +
                "PASSWORD VARCHAR," +
                "LESSON INTEGER)";

        sqLiteDatabase.execSQL(stmnt);
    }
    //Version number of database changes
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("USERNAME", user.getName());
        cv.put("PASSWORD", user.getPw());

        long insert = db.insert("USERS", null, cv);

        return insert != -1;
    }

    //Returns single user from database
    //Used for Login
    public User getUser(String user, String pw){
        List<User> list = getAll();
        for(User i : list){
            if(i.getName().equals(user) && i.getPw().equals(pw)){
                return i;
            }
        }
        return null;
    }

    public boolean checkUser(String user){
        for(User i : getAll()){
            if(i.getName().equals(user)){
                return false;
            }
        }

        return true;
    }

    //Returns all users in database
    public List<User> getAll(){
        List<User> temp = new ArrayList<>();

        String query = "SELECT * FROM USERS";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery(query, null);
        if(result.moveToFirst()){
            do{
                int id = result.getInt(0);
                String username = result.getString(1);
                String password = result.getString(2);

                User tempUser = new User(username, password);
                temp.add(tempUser);
            }while(result.moveToNext());
        }
        else{
            Log.d("info", "none");
        }

        result.close();
        return temp;
    }

}
