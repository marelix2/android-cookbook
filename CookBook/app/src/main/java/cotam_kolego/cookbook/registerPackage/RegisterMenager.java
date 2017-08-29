package cotam_kolego.cookbook.registerPackage;

import java.io.IOException;
import java.lang.annotation.Annotation;

import cotam_kolego.cookbook.UserStorage;
import cotam_kolego.cookbook.api.ErrorResponse;
import cotam_kolego.cookbook.api.PodcastApi;
import cotam_kolego.cookbook.api.ReisterRequest;
import cotam_kolego.cookbook.api.UserResponse;
import cotam_kolego.cookbook.registerPackage.RegisterActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Michał on 17.06.2017.
 */

public class RegisterMenager {

    private RegisterActivity registerActivity;

    private final UserStorage userStorage;
    private final PodcastApi podcastApi;
    private final Retrofit retrofit;
    private Call<UserResponse> userResponseCall;

    public RegisterMenager(UserStorage userStorage, PodcastApi podcast, Retrofit retrofit ) {
       this.userStorage = userStorage;
        podcastApi = podcast;
        this.retrofit = retrofit;
    }

    void onAttach(RegisterActivity registerActivity){

        this.registerActivity =registerActivity;
        updateProgress();
    }

    void onstop(){

        this.registerActivity = null;
    }

    public void register(String first_name, String last_name, String email , String password){

        ReisterRequest reisterRequest= new ReisterRequest();
        reisterRequest.setFirstName(first_name);
        reisterRequest.setEmail(email);
        reisterRequest.setLastName(last_name);
        reisterRequest.setPassword(password);
        reisterRequest.setUsername(email);

        if(userResponseCall == null) {
            userResponseCall = podcastApi.postRegister(reisterRequest);
            updateProgress();
            userResponseCall.enqueue(new Callback<UserResponse>() {

                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                    userResponseCall = null;
                    updateProgress();
                    if(response.isSuccessful()){
                        //UserStorage.register(response.body());
                        if(registerActivity != null){

                            registerActivity.registerSuccesful();
                        }
                    }else {
                        Converter<ResponseBody, ErrorResponse> converter = retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[]{});

                        try {
                            ErrorResponse errorR = converter.convert(response.errorBody());

                            if(registerActivity != null){

                                registerActivity.showError(errorR.getError().toString());

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {

                    if(registerActivity != null){
                        userResponseCall = null;
                        updateProgress();

                        registerActivity.showError(t.getLocalizedMessage());

                    }

                }

            });







        }


    }

    private void updateProgress(){

        if(registerActivity != null){

            registerActivity.showProgress(userResponseCall != null);

        }

    }


}
