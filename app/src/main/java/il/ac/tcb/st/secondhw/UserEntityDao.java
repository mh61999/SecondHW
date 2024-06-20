package il.ac.tcb.st.secondhw;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import retrofit2.http.DELETE;

@Dao
public interface UserEntityDao {
    @Query("SELECT * FROM Users")
    List<UserEntity> getAll();

    @Query("DELETE FROM Users")
    void nukeTable();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudent(UserEntity user);

}
