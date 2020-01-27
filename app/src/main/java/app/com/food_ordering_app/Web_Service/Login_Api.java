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
 * Created by admin on 9/26/2017.
 */

public class Login_Api {
   public static String user_id;

    public void login(final Context context, String username, String password)
    {
        //-----------retrofit created-----------------------
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Global.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //-----------interface created-----------------------
        WebService interface_method=retrofit.create(WebService.class);


        //---------callback created----------------------------
        Call<LoginValue> call=interface_method.login(username,password);


    call.enqueue(new Callback<LoginValue>() {
        @Override
        public void onResponse(Call<LoginValue> call, Response<LoginValue> response) {
            if (response.isSuccessful())

            {
                String status=response.body().getStatus().toString();
                if (status.equalsIgnoreCase("1"))
                {
                    context.startActivity(new Intent(context,MainActivity.class));
                    ((Activity)context).finish();

                    SharedPreferences sharedpreferences;
                    user_id=response.body().getResult().getUserId().toString();
                    //---------shared preferences-----------------------------------
                    sharedpreferences =context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedpreferences.edit();
                    editor.putString("user_id",user_id);
                    editor.commit();

                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                    Toast.makeText(context,"Login Successful", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(context,response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();

                }

            }
        }

        @Override
        public void onFailure(Call<LoginValue> call, Throwable t) {
            Toast.makeText(context,"Failed to connect", Toast.LENGTH_SHORT).show();
        }
    });










    }
}
