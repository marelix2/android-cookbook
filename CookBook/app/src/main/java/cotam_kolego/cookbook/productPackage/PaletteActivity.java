package cotam_kolego.cookbook.productPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import cotam_kolego.cookbook.DiscoverPackage.DiscoverActivity;
import cotam_kolego.cookbook.R;

public class PaletteActivity extends AppCompatActivity implements PaletteFragment.AddProductsCallBack{

    public static final String PRODUCTLIST_KEY = "productlist";
    private ProductObject[] productObjects;



    private ArrayList<String> productList;
    private ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        productList = new ArrayList<>();

        productObjects = ProductObject.loadArrayFromJson(this,"products");
        PaletteFragment paletteFragment = PaletteFragment.newInstance(productObjects);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.ProductLayout, paletteFragment);
        fragmentTransaction.commit();

    }

    public void setProductList(String product) {
       productList.add(product);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reqproducts, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_ask_req){
    Intent intent = new Intent(PaletteActivity.this, DiscoverActivity.class);
            intent.putStringArrayListExtra(PRODUCTLIST_KEY,productList);
            startActivity(intent);
            finish();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addSth(String name) {
        productList.add(name);


    }
}
