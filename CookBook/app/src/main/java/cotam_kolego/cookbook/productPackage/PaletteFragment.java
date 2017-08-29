package cotam_kolego.cookbook.productPackage;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cotam_kolego.cookbook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaletteFragment extends Fragment implements ProductsAdapter.AdapterCallBack {


    public static final String KEY = "key";
    @BindView(R.id.ObjectsRecyclerView)
    RecyclerView ObjectsRecyclerView;
    Unbinder unbinder;
    private ProductsAdapter adapter;
    private AddProductsCallBack addProductsCallBack;


    private String productsList;

    public PaletteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_palette, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addProductsCallBack = (AddProductsCallBack) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        addProductsCallBack = null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProductObject[] serializable = (ProductObject[]) getArguments().getSerializable(KEY);

        ObjectsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapter = new ProductsAdapter(serializable);
        adapter.setCallback(this);
        ObjectsRecyclerView.setAdapter(adapter);

    }
    public static PaletteFragment newInstance(ProductObject[] productObjects) {

        Bundle args = new Bundle();

        PaletteFragment fragment = new PaletteFragment();

        args.putSerializable(KEY, productObjects);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void productObjectClicked(ProductObject object) {
        productsList = object.getName();
        addProductsCallBack.addSth(productsList);

    }

    public interface AddProductsCallBack{

        public void addSth(String name);
    }

}
