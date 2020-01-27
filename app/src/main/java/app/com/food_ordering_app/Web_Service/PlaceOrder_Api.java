package app.com.food_ordering_app.Web_Service;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import app.com.food_ordering_app.activities.Delivery_Location;
import app.com.food_ordering_app.activities.Order_History_Details;
import app.com.food_ordering_app.activities.TakeOut_CheckOut;
import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 11/28/2017.
 */

public class PlaceOrder_Api {
    Context con;
    String user_id,rest_id,type,delivery_address,total_amount,tip;
    public void placeOrder(Application application, String userId, String restId, String type, String delivery_address, String total_amount , String tip) {
        this.con=application;
        this.user_id=userId;
        this.rest_id=restId;
        this.type=type;
        this.delivery_address=delivery_address;
        this.total_amount =total_amount ;
        this.tip =tip ;



                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(Global.base_url)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                Log.e("user_id",user_id);
                Log.e("rest_id",rest_id);
                Log.e("type",type);
                Log.e("delivery_address",delivery_address);
                Log.e("total_amount",total_amount);
                Log.e("tip",tip);

                 WebService webservice = retrofit.create(WebService.class);
                 Call<PlaceOrderValue> call = webservice.placeOrder(user_id,rest_id,type,delivery_address,total_amount,tip);
        call.enqueue(new Callback<PlaceOrderValue>() {
            @Override
            public void onResponse(Call<PlaceOrderValue> call, Response<PlaceOrderValue> response) {

                if (response.isSuccessful())
                {

                    String status = response.body().getStatus();
                    if (status.equalsIgnoreCase("1"))
                    {

                        Global.product_qty_Unique=0;
                        Global.product_id_Unique="";
                        Global.product_price_Unique=0;
                        TakeOut_CheckOut.del_address.setText("");
                        Delivery_Location.loc_access="";

                        Log.e("hetedfw","dfgvtfrvb");

                        Toast.makeText(con,response.body().getMessage()+ "", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(con,Order_History_Details.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        con.startActivity(intent);
                    }

                }

            }

            @Override
            public void onFailure(Call<PlaceOrderValue> call, Throwable t) {
                Toast.makeText(con, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
