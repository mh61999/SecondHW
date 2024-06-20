package il.ac.tcb.st.secondhw;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ImageService {
    @GET
    Call<ResponseBody> fetchCaptcha(@Url String url);
}
