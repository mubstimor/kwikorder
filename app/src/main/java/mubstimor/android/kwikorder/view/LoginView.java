package mubstimor.android.kwikorder.view;

public interface LoginView {
    /**
     * print results to login screen.
     * @param response string to notify slack status
     */
    void responseReady(String response);
}