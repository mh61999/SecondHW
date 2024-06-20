package il.ac.tcb.st.secondhw;

import static il.ac.tcb.st.secondhw.Request.getUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import il.ac.tcb.st.secondhw.databinding.ActivityMainBinding;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    Array array;
    Bitmap profileImage;
    private MyPicks currentuser;
    ArrayList<MyPicks> saveCollection = new ArrayList<MyPicks>();
    UserDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = UserDatabase.getInstance(this);
        loadArray();
        binding.Next.setOnClickListener(nextButton());
        binding.Add.setOnClickListener(saveProfile());
        binding.ViewColl.setOnClickListener(goToCollection());
        binding.btnClear.setOnClickListener(clearDB());
    }
    View.OnClickListener clearDB(){
        return view -> {
            db.userDao().nukeTable();
            saveCollection = new ArrayList<MyPicks>();
        };
    }
    View.OnClickListener nextButton(){
        return v ->{
            loadRequest();
        };
    }
    View.OnClickListener saveProfile(){
        return v ->{
            saveCollection.add(currentuser);
            UserEntity u = new UserEntity();
            u.firstName = currentuser.name;
            u.lastName = currentuser.last;
            u.age = currentuser.age;
            u.city = currentuser.city;
            u.email = currentuser.email;
            u.country = currentuser.country;
            u.picture = currentuser.picture;
            db.userDao().insertStudent(u);
        };
    }
    View.OnClickListener goToCollection(){
        return v ->{

            Intent i=new Intent(MainActivity.this, UsersActivity.class);
            i.putExtra("content",saveCollection);
            startActivity(i);
        };
    }

    private void loadArray(){
        List<UserEntity> users = db.userDao().getAll();
        for (UserEntity user : users) {
            MyPicks tmp = new MyPicks(user.firstName,user.lastName,user.age,user.email,user.city,user.country,user.picture);
            tmp.LoadImage();
            saveCollection.add(tmp);
        }
    }
    private void loadRequest(){
        Retrofit retrofit = Request.getUser();
        UserService service = retrofit.create(UserService.class);
        Call<Array> callAsynce = service.getUser();
        callAsynce.enqueue(new Callback<Array>() {
            @Override
            public void onResponse(Call<Array> call, Response<Array> response) {
                array = response.body();
                assert array != null;
                List<User> userList = array.results;
                User user = userList.get(0);
                currentuser = new MyPicks(user.name.first,user.name.last,user.dob.age,user.email,user.location.city,user.location.country,user.picture.large);
                LoadImage();
                try {
                    setStuff();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            public void onFailure(Call<Array> call, Throwable throwable) {
                currentuser = new MyPicks("errr","errr",0,"errr","errr","errr","errr");

                try {
                    setStuff();
                    binding.Picture.setImageDrawable(getDrawable(R.drawable.ic_launcher_background));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRequest();
    }

    private void setStuff() throws IOException {
        binding.FirstName.setText(currentuser.name);
        binding.LastName.setText(currentuser.last);
        binding.Age.setText(String.valueOf(currentuser.age));
        binding.Email.setText(currentuser.email);
        binding.Country.setText(currentuser.country);
        binding.City.setText(currentuser.city);
    }

    private void LoadImage(){
        binding.Picture.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://someplaceholder.com/").build();
        ImageService service = retrofit.create(ImageService.class);
        Call<ResponseBody> call = service.fetchCaptcha(currentuser.picture);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                assert response.body() != null;
                currentuser.setPicutreLoaded(BitmapFactory.decodeStream(response.body().byteStream()));
                binding.Picture.setImageBitmap(currentuser.picutreLoaded);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {

            }
        });
    }
}