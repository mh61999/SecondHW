package il.ac.tcb.st.secondhw;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("/api")
    public Call<Array> getUser(
    );

}
