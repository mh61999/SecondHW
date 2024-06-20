package il.ac.tcb.st.secondhw;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase instance;
    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            UserDatabase.class,
                            "Users_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract UserEntityDao userDao();

}
