package app.com.food_ordering_app.Web_Service;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import app.com.food_ordering_app.activities.Forgot_Password;
import app.com.food_ordering_app.activities.Login;
import app.com.food_ordering_app.activities.MainActivity;
import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 10/17/2017.
 */

public class ForgotPassword_Api {
    Context con;
   String email;
    public void forgotPassword(Application application, final String s) {
        this.con=application;
       this.email=s;

        Retrofit retrofit=new Retrofit.Builder()
                                     .baseUrl(Global.base_url)
                                     .addConverterFactory(GsonConverterFactory.create())
                                      .build();
        WebService webservice=retrofit.create(WebService.class);

        Call<ForgotPasswordValue> call=webservice.forgotpassword(email);
        call.enqueue(new Callback<ForgotPasswordValue>() {
            @Override
            public void onResponse(Call<ForgotPasswordValue> call, Response<ForgotPasswordValue> response) {
              if (response.isSuccessful())
              {

                  String status=response.body().getStatus().toString();
                  if (status.equalsIgnoreCase("1"))
                  {
                      Toast.makeText(con,response.body().getMessage().toString()+ "", Toast.LENGTH_SHORT).show();
                      Intent intent=new Intent(con,Login.class);
                      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                      con.startActivity(intent);

                  }
                  else if (status.equalsIgnoreCase("0"))
                  {
                      Toast.makeText(con,response.body().getMessage().toString()+ "", Toast.LENGTH_SHORT).show();
                  }

              }

            }

            @Override
            public void onFailure(Call<ForgotPasswordValue> call, Throwable t) {
                Toast.makeText(con, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
