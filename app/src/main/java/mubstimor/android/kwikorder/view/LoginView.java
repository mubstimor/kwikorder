package mubstimor.android.kwikorder.view;

import mubstimor.android.kwikorder.model.LoginModel;

public interface LoginView {
    /**
     * print results to login screen.
     * @param loginResponse string to notify slack status
     */
    void responseReady(LoginModel loginResponse);
}