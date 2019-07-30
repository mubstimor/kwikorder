package mubstimor.android.kwikorder.model;

/**
 * Model for body of message to be posted.
 */
public class LoginModel {

    private String email;
    private String password;
    private String token;


    /**
     * constructor for model.
     * @param email string for username / email
     * @param password string for password
     */
    public LoginModel(String email, String password) {
        this.email = email;
        this.password = password;
     }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //    @Override
//    public String toString() {
//        String greeting = "{"
//                + "'username': 'convergebot',"
//                + "'text':'Hello " + email + ", "
//                + "Have a good day!"
//                + "' "
//                + "}";
//        return greeting;
//    }
}