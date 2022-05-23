package database;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database {
    private DatabaseReference dbRef;
    public Database(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbRef = db.getReference(User.class.getSimpleName());
    }

    public Task<Void> addUser(User user){
        return dbRef.push().setValue(user);
    }
}
