package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.financelingo.financelingo.Login;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper{
    private DatabaseReference dbRef;
    public DatabaseHelper(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbRef = db.getReference(User.class.getSimpleName());
    }

    public Task<Void> addUser(User user){
        return dbRef.push().setValue(user);
    }

    //Temp
    public User getUser(String user, String pass){
        loadData().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data : snapshot.getChildren()){
                    User temp = data.getValue(User.class);
                    if(temp.getName().equals(user) && temp.getPw().equals(pass)){
                        Toast.makeText(new AppCompatActivity(), "USER FOUND", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(new AppCompatActivity(), ".getMessage()", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return new User();
    }

    private Query loadData(){
        return dbRef.orderByKey();
    }

}