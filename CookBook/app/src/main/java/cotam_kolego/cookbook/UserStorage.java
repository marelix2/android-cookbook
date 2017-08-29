package cotam_kolego.cookbook;

import android.content.SharedPreferences;

import cotam_kolego.cookbook.api.SessionRequest;
import cotam_kolego.cookbook.api.UserResponse;

/**
 * Created by Michał on 17.06.2017.
 */

public class UserStorage {

          public static final String SESION_TOKEN_KEY = "sesionToken";
        public static final String EMAIL_KEY = "email";
        public static final String USER_ID_KEY = "userId";
        public static final String USERNAME_KEY = "username";
        public static final String FIRSTNAME_KEY = "firstname";
        public static final String LASTNAME_KEY = "lastname";
        private final SharedPreferences sharedPreferences;

    public UserStorage(SharedPreferences sharedPreferences) {
            this.sharedPreferences = sharedPreferences;
        }

    public void save(UserResponse userResponse){

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(SESION_TOKEN_KEY, userResponse.getSessionToken());
        editor.putString(EMAIL_KEY, userResponse.getEmail());
        editor.putString(USER_ID_KEY, userResponse.getObjectId());
        editor.putString(USERNAME_KEY, userResponse.getUsername());
        editor.putString(FIRSTNAME_KEY,userResponse.getFirstName());
        editor.putString(LASTNAME_KEY,userResponse.getLastName());
        editor.apply();

    }

    public boolean hasToLogin() {
        return sharedPreferences.getString(SESION_TOKEN_KEY,"").isEmpty();

    }

    public String getToken() {
        return sharedPreferences.getString(SESION_TOKEN_KEY,"");

    }

    public String getUserId(){

        return sharedPreferences.getString(USER_ID_KEY,"");
    }

    public void logout() {

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(SESION_TOKEN_KEY)
                .remove(EMAIL_KEY)
                .remove(USERNAME_KEY)
                .remove(USERNAME_KEY)
                .remove(FIRSTNAME_KEY)
                .remove(LASTNAME_KEY);

        edit.apply();

    }




}
