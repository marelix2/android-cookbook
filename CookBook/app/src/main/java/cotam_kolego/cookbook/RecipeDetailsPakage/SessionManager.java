package cotam_kolego.cookbook.RecipeDetailsPakage;

import java.io.IOException;
import java.lang.annotation.Annotation;

import cotam_kolego.cookbook.UserStorage;
import cotam_kolego.cookbook.api.ErrorResponse;
import cotam_kolego.cookbook.api.PodcastApi;
import cotam_kolego.cookbook.api.RecipeResponse;
import cotam_kolego.cookbook.api.ReisterRequest;
import cotam_kolego.cookbook.api.Results;
import cotam_kolego.cookbook.api.SessionRequest;
import cotam_kolego.cookbook.api.UserResponse;
import cotam_kolego.cookbook.registerPackage.RegisterActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Michał on 24.06.2017.
 */

public class SessionManager {

        private DetailsActivity detailsActivity;

        private final UserStorage userStorage;
        private final PodcastApi podcastApi;
        private final Retrofit retrofit;
        private Call<RecipeResponse> recipeResponseCall;

        public SessionManager(UserStorage userStorage, PodcastApi podcast, Retrofit retrofit ) {
            this.userStorage = userStorage;
            this.podcastApi = podcast;
            this.retrofit = retrofit;
        }

        void onAttach(DetailsActivity detailsActivity){

            this.detailsActivity =detailsActivity;
        }

        void onstop(){

            this.detailsActivity = null;
        }

        public void register(Results r){
            SessionRequest sessionRequest = new SessionRequest();

            sessionRequest.setImageUrl(r.imageUrl);
            sessionRequest.setUserId(userStorage.getUserId());
            sessionRequest.setRecipeId(r.objectId);
            sessionRequest.setDishName(r.dishName);
            sessionRequest.setDescription(r.Description);


            if(recipeResponseCall == null) {
                recipeResponseCall = podcastApi.postSessions(sessionRequest);
                recipeResponseCall.enqueue(new Callback<RecipeResponse>() {

                    @Override
                    public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {

                        recipeResponseCall = null;
                        if(response.isSuccessful()){
                            if(recipeResponseCall != null){


                            }
                        }else {
                            Converter<ResponseBody, ErrorResponse> converter = retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[]{});

                        }
                    }

                    @Override
                    public void onFailure(Call<RecipeResponse> call, Throwable t) {

                        if(detailsActivity != null){
                            recipeResponseCall = null;


                        }

                    }

                });







            }


        }



    }

