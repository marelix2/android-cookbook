package cotam_kolego.cookbook.DiscoverPackage;

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

/**
 * Created by Michał on 21.06.2017.
 */

public class DiscoverManager {

    private final PodcastApi podcastApi;
    private final UserStorage userStorage;
    private Call<RecipeResponse> call;

    private DiscoverFragment discoverFragnemt;

    public DiscoverManager(PodcastApi podcastApi, UserStorage userStorage) {
        this.podcastApi = podcastApi;
        this.userStorage = userStorage;
    }

    public void onAttach(DiscoverFragment fragment){
        this.discoverFragnemt = fragment;

    };
    public  void onStop(){

        this.discoverFragnemt = null;
    }
    public void loadRecipes(List<String> products) {
        String[] tmp = new String[products.size()];

        for (int i = 0; i < products.size(); i++) {
            tmp[i] = products.get(i);


        }

        String where = " {\"objectId\": {\"$select\": {\"query\": {\"className\": \"Recipes\", \"where\": {\"products\": \"%s\"}},\"key\": \"objectId\"}}}";

        for (int i = 0; i < tmp.length; i++) {


        call = podcastApi.getRecipes(String.format(where, tmp[i]));
        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful()) {
                    if (discoverFragnemt != null) {
                        discoverFragnemt.saveRecords(response.body().results);
                    }


                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {


            }
        });
    }
    }




}
