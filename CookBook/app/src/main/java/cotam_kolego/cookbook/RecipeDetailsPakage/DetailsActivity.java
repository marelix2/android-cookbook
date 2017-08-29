package cotam_kolego.cookbook.RecipeDetailsPakage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import cotam_kolego.cookbook.App;
import cotam_kolego.cookbook.R;
import cotam_kolego.cookbook.api.Results;

public class DetailsActivity extends AppCompatActivity {

    public static final String OBJECT_R_KEY = "objectR";
    private static boolean fabb;
    @BindView(R.id.RecipeImageView)
    ImageView RecipeImageView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.RecipeTextView)
    TextView RecipeTextView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private Results resultObject;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resultObject = (Results) getIntent().getSerializableExtra(OBJECT_R_KEY);

        RecipeTextView.setText(resultObject.Description);
        Glide.with(RecipeImageView.getContext())
                .load(resultObject.imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(RecipeImageView);

        sessionManager = ((App)getApplication()).getSessionManager();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sessionManager.register(resultObject);

                Snackbar.make(view, "Saved", Snackbar.LENGTH_SHORT)
                       .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(fabb == false) fab.setVisibility(View.INVISIBLE);
        sessionManager.onAttach(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sessionManager.onstop();
    }

    private void tryToReg(){

        sessionManager.register(resultObject);

    }


    public static void start(Activity activity, Results r, boolean fab) {

        fabb = fab;
        Intent intent = new Intent(activity, DetailsActivity.class);
        intent.putExtra(OBJECT_R_KEY, r);
        activity.startActivity(intent);
    }
}
