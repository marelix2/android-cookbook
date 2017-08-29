package cotam_kolego.cookbook.productPackage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import cotam_kolego.cookbook.R;

/**
 * Created by Michał on 18.06.2017.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.viewHolderAdapter> {

    private final ProductObject[] productObjects;
    private int saveditems;
    private AdapterCallBack callback;
    private Toast toast;

    public ProductsAdapter(ProductObject[] productObjects) {
        this.productObjects = productObjects;
        saveditems = 0;
    }

    @Override
    public viewHolderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_solar_object, parent, false);
        toast =  Toast.makeText(parent.getContext(),"Too many arguments clicked",Toast.LENGTH_SHORT);
        return new viewHolderAdapter(view);
    }
    @Override
    public void onBindViewHolder(viewHolderAdapter holder, int position) {

       ProductObject productObject = productObjects[position];
        holder.setProductObject(productObject);
    }

    public void setCallback(AdapterCallBack callback) {
        this.callback = callback;
    }

    @Override
    public int getItemCount() {
        return productObjects.length;
    }

    private void itemClicked(ProductObject productObject) {

        if(callback != null && saveditems < 6){
            saveditems++;
            callback.productObjectClicked(productObject);
        }else{
            toast.show();
        }
    }

    public interface AdapterCallBack{
        void productObjectClicked(ProductObject object);
    }


    class viewHolderAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.imageViewId)
        ImageView itemImageView;

        @BindView(R.id.textViewId)
        TextView itemTextView;

        private ProductObject productObject;

        public viewHolderAdapter(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setProductObject(ProductObject productObject) {
            this.productObject = productObject;

            String nameProduct = productObject.getName();
            nameProduct = getStringName(nameProduct);
            itemTextView.setText(nameProduct);
            Glide.with(itemImageView.getContext()).load("file:///android_asset/" + productObject.getImage())
                    .placeholder(R.drawable.placeholder)
                    .into(itemImageView);


        }

        @NonNull
        private String getStringName(String nameProduct) {
            if(nameProduct.contains("_")) {
                int index = nameProduct.indexOf("_");
               nameProduct =  nameProduct.substring(0, index) + " " + nameProduct.substring(index+1);
            }
            return nameProduct;
        }

        @Override
        public void onClick(View v) {
            itemClicked(productObject);
        }
    }



}
