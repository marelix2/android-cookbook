package cotam_kolego.cookbook.MainPackage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cotam_kolego.cookbook.App;
import cotam_kolego.cookbook.R;
import cotam_kolego.cookbook.RecipeDetailsPakage.DetailsActivity;
import cotam_kolego.cookbook.api.Results;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserSessionFragment extends Fragment implements UserSessionAdapter.SessionCallback {

    @BindView(R.id.UserSessionRecyclerView)
    RecyclerView UserSessionRecyclerView;
    Unbinder unbinder;


    private UserSessionManager userSessionManager;
    private UserSessionAdapter userSessionAdapter;

    public UserSessionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userSessionManager = ((App) getActivity().getApplication()).getUserSessionManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        UserSessionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onStart() {
        super.onStart();
        userSessionManager.onAttach(this);
        userSessionManager.loadRecipes();
    }

    @Override
    public void onStop() {
        super.onStop();
        userSessionManager.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    public void showRecipes(List<Results> resultsList){

        userSessionAdapter = new UserSessionAdapter();
        UserSessionRecyclerView.setAdapter(userSessionAdapter);
        userSessionAdapter.setSessionCallback(this);
        userSessionAdapter.setResultses(resultsList);

    }

    @Override
    public void RecipesClicked(Results r) {

        DetailsActivity.start(getActivity(),r,false);
    }
}
