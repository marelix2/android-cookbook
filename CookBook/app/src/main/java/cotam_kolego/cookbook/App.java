package cotam_kolego.cookbook;

import android.app.Application;

import cotam_kolego.cookbook.DiscoverPackage.DiscoverManager;
import cotam_kolego.cookbook.MainPackage.UserSessionManager;
import cotam_kolego.cookbook.RecipeDetailsPakage.SessionManager;
import cotam_kolego.cookbook.api.PodcastApi;
import cotam_kolego.cookbook.loginpakage.LoginMenager;
import cotam_kolego.cookbook.registerPackage.RegisterMenager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Michał on 17.06.2017.
 */

public class App extends Application {

    private RegisterMenager registerMenager;
    private LoginMenager loginMenager;
    private UserStorage userStorage;
    private DiscoverManager discoverManager;


    private PodcastApi podcastApi;
    private Retrofit retrofit;
    private SessionManager sessionManager;
    private UserSessionManager userSessionManager;

    @Override
    public void onCreate() {
        super.onCreate();


        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(loggingInterceptor).build();

        Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl("https://cookbookappdev.herokuapp.com/parse/");
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.client(okHttpClient);
        retrofit = builder.build();
        podcastApi = retrofit.create(PodcastApi.class);


        userStorage =new UserStorage(getSharedPreferences("player",MODE_PRIVATE));
        loginMenager = new LoginMenager(userStorage,podcastApi,retrofit);
        registerMenager = new RegisterMenager(userStorage,podcastApi,retrofit);
        discoverManager = new DiscoverManager(podcastApi, userStorage);
        sessionManager = new SessionManager(userStorage,podcastApi,retrofit);
        userSessionManager = new UserSessionManager(podcastApi,userStorage,retrofit);


    }

    public DiscoverManager getDiscoverManager() {
        return discoverManager;
    }

    public LoginMenager getLoginMenager() {
        return loginMenager;
    }

    public UserStorage getUserStorage() {
        return userStorage;
    }

    public RegisterMenager getRegisterMenager() {
        return registerMenager;
    }

    public PodcastApi getPodcastApi() {
        return podcastApi;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public UserSessionManager getUserSessionManager() {
        return userSessionManager;
    }
}
