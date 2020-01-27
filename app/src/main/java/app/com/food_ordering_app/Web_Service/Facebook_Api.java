package app.com.food_ordering_app.Web_Service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import app.com.food_ordering_app.activities.Login;
import app.com.food_ordering_app.activities.MainActivity;
import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 10/5/2017.
 */

public class Facebook_Api {

    public void facebook_login(final Context applicationContext, String fb_id, String f,String a,String b,String c,String d,String e,String g,String h,String i,String j,String k) {

        Retrofit retrofit_fb=new Retrofit.Builder()
                .baseUrl(Global.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebService interface_fb=retrofit_fb.create(WebService.class);

        Call<FacebookValue> call=interface_fb.facebook_login(fb_id,f,a,b,c,d,e,g,h,i,j,k);
        call.enqueue(new Callback<FacebookValue>() {
            @Override
            public void onResponse(Call<FacebookValue> call, Response<FacebookValue> response) {

                if (response.isSuccessful())
                {
                    String status=response.body().getStatus().toString();

                    if (status.equalsIgnoreCase("1"))
                    {

                     String id=response.body().getResult().getId().toString();


                       /* SharedPreferences sharedPreferences_fb=applicationContext.getSharedPreferences("Type_preferences",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences_fb.edit();
                        editor.putString("type_id",id);
                        editor.commit();*/

                        SharedPreferences sharedPreferences_fb=applicationContext.getSharedPreferences("MyPREFERENCES",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences_fb.edit();
                        editor.putString("user_id",id);
                        editor.commit();

                        Intent intent = new Intent(applicationContext, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        applicationContext.startActivity(intent);


                    }
                    else {

                    }
                }
            }

            @Override
            public void onFailure(Call<FacebookValue> call, Throwable t) {
                Toast.makeText(applicationContext,  "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
