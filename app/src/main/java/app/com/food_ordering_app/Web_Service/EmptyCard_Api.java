package app.com.food_ordering_app.Web_Service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import app.com.food_ordering_app.activities.TakeOut_Menu;
import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 11/6/2017.
 */

public class EmptyCard_Api {
    Context con;
    String user_id_cart_add;
    String restaurant_id_cart_add;
    String restaurant_name,restaurant_address,restaurant_id,str_cmpr;

    public EmptyCard_Api(String restaurant_name, String restaurant_address, String restaurant_id) {
        this.restaurant_name=restaurant_name;
        this.restaurant_address=restaurant_address;
        this.restaurant_id=restaurant_id;
    }

    public void emptyCard(final Context con, String user_id_cart_add, String restaurant_id_cart_add, final String str_cmpr) {
        this.con=con;
        this.user_id_cart_add=user_id_cart_add;
        this.restaurant_id_cart_add=restaurant_id_cart_add;
        this.str_cmpr=str_cmpr;

        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(Global.base_url)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

        WebService webservice = retrofit.create(WebService.class);
        Call<EmptyCardValue> call = webservice.emptyCard(user_id_cart_add,restaurant_id_cart_add);
        call.enqueue(new Callback<EmptyCardValue>() {
            @Override
            public void onResponse(Call<EmptyCardValue> call, Response<EmptyCardValue> response) {

                if (response.isSuccessful()) {

                    String status = response.body().getStatus();
                    if (status.equalsIgnoreCase("1")) {


                        Global.product_qty_Unique=0;
                        Global.product_price_Unique=0;
                        Log.e("global_value1",Global.product_qty_Unique+"");
                        Log.e("global_value2", Global.product_price_Unique+"");

                        String res = response.body().getResult().getUserId();

                        SharedPreferences sharedPreferences = con.getSharedPreferences("MyCart",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();


                   if (str_cmpr.equalsIgnoreCase("Cmpr_HomeAdpter"))
                   {
                       //---intent to carry value to takeout menu screen------------------------------------------
                        Intent intent = new Intent(con, TakeOut_Menu.class);
                        intent.putExtra("restaurant_name", restaurant_name);
                        intent.putExtra("restaurant_address", restaurant_address);
                        intent.putExtra("restaurant_id", restaurant_id);
                        Log.e("hereis", restaurant_id+" fbtfbv");
                        con.startActivity(intent);
                   }
                   else if (str_cmpr.equalsIgnoreCase("cmp_Takeout"))
                   {
                       TakeOut_Menu.cart_rel_takeout_menu.setVisibility(View.GONE);

                   }








                    }


                }

            }

            @Override
            public void onFailure(Call<EmptyCardValue> call, Throwable t) {
                Toast.makeText(con, " Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
