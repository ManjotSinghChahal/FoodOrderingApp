package app.com.food_ordering_app.Web_Service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import app.com.food_ordering_app.activities.MainActivity;
import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 9/27/2017.
 */

public class Register_Api {

public static String reg_user_id;
    public void register(final Context context, String first_name, String last_name,String email,String password,String confirm_password,String country_code,String phone_number)
    {
        //-----------retrofit created-----------------------
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Global.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //-----------interface created-----------------------
        WebService interface_method=retrofit.create(WebService.class);


        //---------callback created----------------------------
        Call<RegisterValue> call=interface_method.register(first_name,last_name,email,password,confirm_password,country_code,phone_number);

        call.enqueue(new Callback<RegisterValue>() {
            @Override
            public void onResponse(Call<RegisterValue> call, Response<RegisterValue> response) {

                if (response.isSuccessful())

                {

                    String status=response.body().getStatus().toString();
                    if (status.equalsIgnoreCase("1"))
                    {
                        Toast.makeText(context, "Registered Successful", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context,MainActivity.class));

                        ((Activity)context).finish();
                        //---------shared preferences-----------------------------------
                        SharedPreferences sharedpreferences;
                       reg_user_id=response.body().getResult().getUserId().toString();

                        sharedpreferences =context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedpreferences.edit();
                        editor.putString("user_id",Register_Api.reg_user_id);
                        editor.commit();






                    }
                    else if (response.body().getStatus().equalsIgnoreCase("0"))
                    {
                        Toast.makeText(context,response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<RegisterValue> call, Throwable t) {
                Toast.makeText(context, "failure" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });










    }


}
