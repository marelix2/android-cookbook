package cotam_kolego.cookbook.DiscoverPackage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
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
public class DiscoverFragment extends Fragment  implements DiscoverAdapter.ListenerCallBack{


    public static final String LIST_KEY = "key";
    @BindView(R.id.RecycrelViewdiscower)
    RecyclerView RecycrelViewdiscower;
    Unbinder unbinder;

    List<String> productList;

    List<Results> save = new ArrayList<>();

    private DiscoverManager discoverManager;


    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        discoverManager = ((App)getActivity().getApplication()).getDiscoverManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productList = getArguments().getStringArrayList(LIST_KEY);
        RecycrelViewdiscower.setLayoutManager(new GridLayoutManager(getContext(),2));
        discoverManager.loadRecipes(productList);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static DiscoverFragment newInstance(ArrayList<String> string) {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();

        args.putStringArrayList(LIST_KEY, string);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        discoverManager.onAttach(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        discoverManager.onStop();
    }

    public void saveRecords(List<Results> resultses) {

        if(save.isEmpty()) save.addAll(resultses);
        else {
            for (Results res : resultses) {
                boolean exist = false;
                for (int i = 0; i < save.size(); i++) {
                    if (save.get(i).objectId.equals(res.objectId)) {
                        exist = true;
                        break;
                    }

                }

                if (!exist) save.add(res);

            }

        }
        DiscoverAdapter discoverAdapter = new DiscoverAdapter();
        discoverAdapter.setResultses(save);
        discoverAdapter.setListenerCallBack(this);
        RecycrelViewdiscower.setAdapter(discoverAdapter);

    }

    @Override
    public void RecipesClicked(Results r) {

        DetailsActivity.start(getActivity(),r,true);

    }
}
