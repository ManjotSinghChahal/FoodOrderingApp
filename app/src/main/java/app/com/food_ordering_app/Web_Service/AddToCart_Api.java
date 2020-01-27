package app.com.food_ordering_app.Web_Service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import app.com.food_ordering_app.activities.TakeOut_Menu;
import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 10/26/2017.
 */

public class AddToCart_Api {
    Context con;
    String user_id_get,restaurant_id,menu_id,product_id,addon;
    SharedPreferences sharedpreferencesAddToCart;
    String s="";
    ProgressDialog pd;

    public void addCart(final Context con, String user_id_get, String restaurant_id, String menu_id, String product_id, final String s, String addon)
    {
        this.con=con;
        this.user_id_get=user_id_get;
        this.restaurant_id=restaurant_id;
        this.menu_id=menu_id;
        this.product_id=product_id;
        this.s=s;
        this.addon=addon;

        pd=new ProgressDialog(con);
        pd.setMessage("Loading ......");
        //--------used for null token and also add in manifest-----------------------------------
        pd.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        pd.show();

        Log.e("user_id_get",user_id_get);
        Log.e("restaurant_id",restaurant_id);
        Log.e("menu_id",menu_id);
        Log.e("product_id",product_id);
        Log.e("product_ids",s);
        Log.e("addon",addon);

        Retrofit retrofit = new Retrofit.Builder()
                                          .baseUrl(Global.base_url)
                                          .addConverterFactory(GsonConverterFactory.create())
                                          .build();
        WebService webservice =retrofit.create(WebService.class);
        Call<AddToCartValue> call = webservice.add_Cart(user_id_get,restaurant_id,menu_id,product_id,s,addon);
        call.enqueue(new Callback<AddToCartValue>() {
           @Override
           public void onResponse(Call<AddToCartValue> call, Response<AddToCartValue> response) {
              if (response.isSuccessful())
              {
                  String status=response.body().getStatus().toString()
;
                  if (status.equalsIgnoreCase("1"))
                  {
                      pd.dismiss();

                    String restaurant_id =  response.body().getResult().getRestaurantId();
                    String user_id =  response.body().getResult().getUserId();



                            double get_Total = Double.parseDouble(response.body().getResult().getTotalPrice());
                            Global.product_price_Unique =Global.product_price_Unique+get_Total;
                            Log.e("get_price_here_Api",Global.product_price_Unique+"");
                     TakeOut_Menu.price_getCartScreen_takeout_Menu.setText("$"+Global.product_price_Unique + "");



                      //------storing user_id and restaurant_id to use to empty cart-------------------------
                      sharedpreferencesAddToCart = con.getSharedPreferences("MyCart", Context.MODE_PRIVATE);
                      SharedPreferences.Editor editor = sharedpreferencesAddToCart.edit();
                      editor.putString("cart_value_user_id",user_id);
                      editor.putString("cart_value_restaurant_id",restaurant_id);
                      editor.apply();



                  }
              }
           }

           @Override
           public void onFailure(Call<AddToCartValue> call, Throwable t) {
               Toast.makeText(con, "Failure", Toast.LENGTH_SHORT).show();
               pd.dismiss();
           }
       });

    }
}
