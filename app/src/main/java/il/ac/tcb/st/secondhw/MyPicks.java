package il.ac.tcb.st.secondhw;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyPicks implements Parcelable {
    String name;
    String last;
    int age;
    String email;
    String city;
    String country;
    String picture;
    Bitmap picutreLoaded;

    protected MyPicks(Parcel in) {
        name = in.readString();
        last = in.readString();
        age = in.readInt();
        email = in.readString();
        city = in.readString();
        country = in.readString();
        picture = in.readString();
        picutreLoaded = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<MyPicks> CREATOR = new Creator<MyPicks>() {
        @Override
        public MyPicks createFromParcel(Parcel in) {
            return new MyPicks(in);
        }

        @Override
        public MyPicks[] newArray(int size) {
            return new MyPicks[size];
        }
    };

    public void setPicutreLoaded(Bitmap picutreLoaded) {
        this.picutreLoaded = picutreLoaded;
    }


    public MyPicks(String name,String last,int age,String email,String city,String country,String picture){
        this.name = name;
        this.last = last;
        this.age = age;
        this.email = email;
        this.city = city;
        this.country =country;
        this.picture = picture;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(last);
        parcel.writeInt(age);
        parcel.writeString(email);
        parcel.writeString(city);
        parcel.writeString(country);
        parcel.writeString(picture);
        parcel.writeParcelable(picutreLoaded, i);
    }
    public void LoadImage(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://someplaceholder.com/").build();
        ImageService service = retrofit.create(ImageService.class);
        Call<ResponseBody> call = service.fetchCaptcha(picture);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
               picutreLoaded = (BitmapFactory.decodeStream(response.body().byteStream()));
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {

            }
        });
    }
}
