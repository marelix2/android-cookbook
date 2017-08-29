package cotam_kolego.cookbook.DiscoverPackage;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import cotam_kolego.cookbook.R;
import cotam_kolego.cookbook.productPackage.PaletteActivity;

/*
*   Szkielet który tworzy DiscoverFragmnet
 */

public class DiscoverActivity extends AppCompatActivity {


    private ArrayList<String> value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover2);
        /*
         * pobieranie  danych od PalleteActivity, konkretnie tablicy skadnikow
         */
        Intent i = getIntent();
        Bundle b = i.getExtras();
        if (b != null) {
            value = b.getStringArrayList(PaletteActivity.PRODUCTLIST_KEY);
        }
        DiscoverFragment paletteFragment = DiscoverFragment.newInstance(value);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.DiscoverActivityId, paletteFragment);
        fragmentTransaction.commit();
    }


}
