package cotam_kolego.cookbook.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Array;
import java.util.List;

/**
 * Created by Michał on 19.06.2017.
 * Klasa wykorszystywana przez RecipeResponse oraz Retrofita. Służy jako szkielet pojedyńczego obiektu.
 */

 public class Results implements Serializable{

 public String objectId;
 public String imageUrl;
 public String[] products;
 public String Description;
 public String dishName;



 //{"objectId":"6158wPSooj","products":["chilli","milk","lemon"],"createdAt":"2017-06-21T06:07:20.285Z","updatedAt":"2017-06-23T21:16:45.106Z","imageUrl":"http://imgur.com/a/o3YKU","Description":"\" :)\"","dishName":"super dish 4"}


}
