package mubstimor.android.kwikorder.presenter;

import java.util.HashMap;

import mubstimor.android.kwikorder.model.LoginModel;
import mubstimor.android.kwikorder.service.ApiService;
import mubstimor.android.kwikorder.view.LoginView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class to invoke post method.
 */
public class LoginPresenter {

    private ApiService apiService;

    /**
     * Initialises a service that connects to API endpoints.
     */
    public LoginPresenter() {
        if (this.apiService == null) {
            this.apiService = new ApiService();
        }
    }

    /**
     * post login info.
     *
     * @param loginModel view to which response is mapped
     */
    public void postLoginInfo(final LoginModel loginModel, final LoginView loginView) {

        HashMap<String, LoginModel> loginInfo = new HashMap<>();
        loginInfo.put("user", loginModel);
        System.out.println(loginInfo);

        apiService
                .getAPI()
                .postLogin(loginInfo)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(
                            Call<ResponseBody> call,
                            Response<ResponseBody> response
                    ) {
                        if (response.isSuccessful()) {
                            loginView.responseReady("Login Successful");
                        } else {
                            loginView.responseReady("Invalid Login Credentials");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        loginView.responseReady("unable to send message");
                    }
                });
    }
}