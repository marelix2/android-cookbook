package cotam_kolego.cookbook.api;


import android.support.annotation.ArrayRes;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;
import retrofit2.http.Streaming;

/**
 * Created by Michał on 16.06.2017.
 * interfejs pozalajacy na komunikacje z parse serverem za pomocą Postow i Getow.
 */

public interface PodcastApi {
    /*
     *  Headery określają sesję oraz aplikacje z którą się komunikujemy
     */
    @Headers({
             "X-Parse-Application-Id: CookBookId",
             "X-Parse-REST-API-Key: undefined",
             "X-Parse-Revocable-Session: 1"
    })
    /*
     * GET wysylajacy zapytanie do bazy w celu zalogowania sie, przyjmuje login( email) oraz haslo
     * Wykorzystywany w loginManagerze
     */
    @GET("login")
    Call<UserResponse> getLogin(@Query("username") String username, @Query("password") String password);

    @Headers({
            "X-Parse-Application-Id: CookBookId",
            "X-Parse-REST-API-Key: undefined",
            "X-Parse-Revocable-Session: 1"
    })
    /*
     * POST wysylajacy zapytanie do bazy w celu rejestracji, przyjmuje obiekt RegisterRequest
     * Wykorzystywany w registerManagerze
     */
    @POST("users")
    Call<UserResponse> postRegister(@Body ReisterRequest reisterRequest);

    @Headers({
            "X-Parse-Application-Id: CookBookId",
            "X-Parse-REST-API-Key: undefined",
            "X-Parse-Revocable-Session: 1"
    })
    /*
     * Podzapytanie łączące POST i GET wysylajace zapytanie do bazy w celu znalezienia przepisów
     * z odpowiednimi skladnikami. Wykorzystywany w DiscoverManagerze
     */
    @GET("classes/Recipes")
    Call<RecipeResponse> getRecipes(@Query("where")String where);

    @Headers({
            "X-Parse-Application-Id: CookBookId",
            "X-Parse-REST-API-Key: undefined",
            "X-Parse-Revocable-Session: 1"
    })
     /*
     * POST wysylajacy zapytanie do bazy w celu zapisania bierzącego przepisu dla określonego użytkownia, przyjmuje obiekt SessionRequest
     * Wykorzystywany w SessionManagerze
     */
    @POST("classes/SavedSessions")
    Call<RecipeResponse> postSessions(@Body SessionRequest sessionRequest);


    @Headers({
            "X-Parse-Application-Id: CookBookId",
            "X-Parse-REST-API-Key: undefined",
            "X-Parse-Revocable-Session: 1"
    })
     /*
     * Podzapytanie łączące POST i GET wysylajace zapytanie do bazy w celu znalezienia przepisów dla określonego użytkownika
     * z odpowiednimi skladnikami. Wykorzystywany w UserSessionManagerze
     */
    @GET("classes/SavedSessions")
    Call<RecipeResponse> getSubscribedRecipes(@Query("where")String where, @Header("X-Parse-Session-Token") String Token);

}
