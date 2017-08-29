package cotam_kolego.cookbook.MainPackage;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cotam_kolego.cookbook.UserStorage;
import cotam_kolego.cookbook.api.ErrorResponse;
import cotam_kolego.cookbook.api.PodcastApi;
import cotam_kolego.cookbook.api.RecipeResponse;
import cotam_kolego.cookbook.api.Results;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Michał on 24.06.2017.
 */

public class UserSessionManager {



        private final PodcastApi podcastApi;
        private final UserStorage userStorage;
        private final Retrofit retrofit;
        private Call<RecipeResponse> call;

        UserSessionFragment userSessionFragment;



        public UserSessionManager(PodcastApi podcastApi, UserStorage userStorage, Retrofit retrofit) {
            this.podcastApi = podcastApi;
            this.userStorage = userStorage;
            this.retrofit = retrofit;
        }

        public void onAttach(UserSessionFragment fragment){
            this.userSessionFragment = fragment;

        };
        public  void onStop(){

            this.userSessionFragment = null;
        }


        public void loadRecipes() {


            String where = " {\"objectId\": {\"$select\": {\"query\": {\"className\": \"SavedSessions\", \"where\": {\"userId\": \"%s\"}},\"key\": \"objectId\"}}}";


                call = podcastApi.getSubscribedRecipes(String.format(where,userStorage.getUserId()),userStorage.getToken());
                call.enqueue(new Callback<RecipeResponse>() {
                    @Override
                    public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                        if (response.isSuccessful()) {

                            if(userSessionFragment != null){

                                userSessionFragment.showRecipes(response.body().results);
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<RecipeResponse> call, Throwable t) {


                    }
                });
            }
        }







