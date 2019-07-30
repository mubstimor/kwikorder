package mubstimor.android.kwikorder.service;

import mubstimor.android.kwikorder.request.LoginApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * instantiate retrofit instance.
 */
public class ApiService {

    private Retrofit retrofit = null;

    /**
     * This method creates a new instance of the API interface.
     *
     * @return The API interface
     */
    public LoginApi getAPI() {
        String baseURL = "http://10.0.2.2:8000/";
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit.create(LoginApi.class);
    }
}