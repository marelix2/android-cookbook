package cotam_kolego.cookbook.productPackage;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedReader;
import java.io.Serializable;

/**
 * Created by Michał on 18.06.2017.
 */

public class ProductObject implements Serializable {

    private String name;
    private String image;

    public ProductObject() {
    }

    public ProductObject(String name) {
        this.name = name;
    }

    public ProductObject(JSONObject jsonObject) throws JSONException {

        this.name = jsonObject.getString("name");
        this.image = String.format("images/%s.png", name.toLowerCase());

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ProductObject[] loadArrayFromJson(Context context, String name){

        try {
            String json = loadArrayFromAssets(context,"products.json");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(name);

            ProductObject[] productObjects = new ProductObject[jsonArray.length()];

            for (int i = 0; i <jsonArray.length() ; i++) {

                ProductObject product = new ProductObject(jsonArray.getJSONObject(i));
                productObjects[i] = product;

            }

            return productObjects;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ProductObject[0];
    }

    public static String loadArrayFromAssets(Context context, String fileName) throws IOException {

        InputStream inputStream = context.getAssets().open(fileName);

        int size = inputStream.available();

        byte[] buffer = new byte[size];

        inputStream.read(buffer);
        inputStream.close();

        return new String(buffer);
    }
    
}
