package app.com.food_ordering_app.Web_Service;

import android.content.Context;
import android.provider.Settings;
import android.widget.Toast;

import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 10/4/2017.
 */

public class UpdateLatLong_Api {
    public void updateLatLong(final Context applicationContext, String user, String lat, String log) {

        Retrofit retrofit=new Retrofit.Builder()
                          .baseUrl(Global.base_url)
                          .addConverterFactory(GsonConverterFactory.create())
                           .build();
        WebService webService=retrofit.create(WebService.class);
        Call<UpdateLatLongValue> call=webService.updateLatLog(user,lat,log);
        call.enqueue(new Callback<UpdateLatLongValue>() {
            @Override
            public void onResponse(Call<UpdateLatLongValue> call, Response<UpdateLatLongValue> response) {
                String status=response.body().getStatus();
                if (status.equalsIgnoreCase("1"))
                {
                   // Toast.makeText(applicationContext,response.body().toString()+ " done", Toast.LENGTH_SHORT).show();
                }
                else
                {
                 //   Toast.makeText(applicationContext, "else part", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateLatLongValue> call, Throwable t) {
                Toast.makeText(applicationContext, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
