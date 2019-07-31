package mubstimor.android.kwikorder.request;

import java.util.HashMap;

import mubstimor.android.kwikorder.model.LoginModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Interface to define calls to api.
 */
public interface LoginApi {

    /**
     * post to channel.
     *
     * @param loginInfo string to send
     * @return body this is usually a json object
     */
    @POST("/api/v1/users/login/")
    Call<LoginModel> postLogin(@Body HashMap<String, LoginModel> loginInfo);

}
