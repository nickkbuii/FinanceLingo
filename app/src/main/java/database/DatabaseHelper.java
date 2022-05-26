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

import com.financelingo.financelingo.Create;
import com.financelingo.financelingo.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;

public class DatabaseHelper{
    private DatabaseReference userdb;
    private DatabaseReference progdb;
    private FirebaseAuth auth;
    private static int id;
    private HashMap<User, Integer> map;
    public DatabaseHelper(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        userdb = db.getReference(User.class.getSimpleName());
        userdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                id = (int)snapshot.getChildrenCount();
                Log.d("INFO", String.valueOf(id));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        progdb = db.getReference(Progress.class.getSimpleName());

        auth = FirebaseAuth.getInstance();
        
        map = new HashMap<>();
    }

    public void addUser(User user){
        map.put(user, id);
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPw())
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userdb.child(auth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Log.d("ACC INFO", "ACCOUNT CREATED");
                                }
                            });
                        } else {

                        }
                    }
                });
    }

    public int getId(){
        return id;
    }
    
    




}