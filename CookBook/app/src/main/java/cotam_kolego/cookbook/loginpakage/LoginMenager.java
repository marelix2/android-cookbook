package cotam_kolego.cookbook.loginpakage;

import java.io.IOException;
import java.lang.annotation.Annotation;

import cotam_kolego.cookbook.UserStorage;
import cotam_kolego.cookbook.api.ErrorResponse;
import cotam_kolego.cookbook.api.UserResponse;
import cotam_kolego.cookbook.api.PodcastApi;
import cotam_kolego.cookbook.loginpakage.LoginActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
* Created by Michał on 16.06.2017.
 *
*/

public class LoginMenager {

   private LoginActivity loginActivity;
   private final UserStorage userStorage;
    private Call<UserResponse> call;
    private PodcastApi podcastApi;
    private final Retrofit retrofit;

public LoginMenager(UserStorage userStorage, PodcastApi podcast, Retrofit retrofit ) {
    this.userStorage = userStorage;
    this.podcastApi = podcast;
    this.retrofit = retrofit;
}

void onAttach(LoginActivity loginActivity){

    this.loginActivity = loginActivity;
}

void onStop(){

    this.loginActivity = null;
}

public void login(String username, String password){




    if(call == null) {


        call = podcastApi.getLogin(username, password);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse body = response.body();
                    userStorage.save(body);
                    if (loginActivity != null) {
                        loginActivity.loginSucces();

                    }

                } else {
                    ResponseBody responseBody = response.errorBody();
                    try {
                        Converter<ResponseBody, ErrorResponse> converter = retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[]{});
                        ErrorResponse errorR = converter.convert(responseBody);
                        if (loginActivity != null) {
                            loginActivity.loginFailed(errorR.getError());

                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                    call = null;
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

                if (loginActivity != null) {
                    loginActivity.loginFailed(t.getLocalizedMessage());

                }
               call = null;
            }
        });
    }
    }


}
