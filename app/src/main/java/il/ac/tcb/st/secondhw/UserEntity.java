package il.ac.tcb.st.secondhw;


import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users",
        indices = {@Index(value = {"tehudat_zeut"}, unique = true)})
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    public int studentID;
    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String lastName;
    @ColumnInfo(name = "age")
    public int age;
    @ColumnInfo(name = "Email")
    public String email;

    @ColumnInfo(name = "tehudat_zeut")
    public String tehudatZeut;
    @ColumnInfo(name = "country")
    public String country;
    @ColumnInfo(name = "city")
    public String city;
    @ColumnInfo(name = "Picture")
    public String picture;

}
