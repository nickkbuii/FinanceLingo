package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LessonDatabase extends SQLiteOpenHelper {


    public LessonDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "lesson.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String stmnt = "CREATE TABLE LESSONS (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "USERNAME TEXT, " +
                "PASSWORD VARCHAR," +
                "LESSON INTEGER)";

        sqLiteDatabase.execSQL(stmnt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
