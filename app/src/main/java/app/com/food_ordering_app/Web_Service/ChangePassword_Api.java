package app.com.food_ordering_app.Web_Service;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import app.com.food_ordering_app.activities.MainActivity;
import app.com.food_ordering_app.fragment.Settings;
import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 10/16/2017.
 */

public class ChangePassword_Api {
Context con;
    String s,s1,s2,s3;
    public void change_password(FragmentActivity activity, String s, String s1, String s2, String s3) {
        this.con=activity;
        this.s=s;
        this.s1=s1;
        this.s2=s2;
        this.s3=s3;

        Retrofit retrofit=new Retrofit.Builder()
                                 .baseUrl(Global.base_url)
                                 .addConverterFactory(GsonConverterFactory.create())
                                 .build();
        WebService webservice=retrofit.create(WebService.class);

        Call<ChangePasswordValue> call=webservice.changePassword(s,s1,s2,s3);
        call.enqueue(new Callback<ChangePasswordValue>() {
            @Override
            public void onResponse(Call<ChangePasswordValue> call, Response<ChangePasswordValue> response) {
                String status = response.body().getStatus().toString();
                if (response.isSuccessful())

                {
                    if (status.equalsIgnoreCase("1")) {
                        Toast.makeText(con, response.body().getMessage().toString() + "", Toast.LENGTH_SHORT).show();
                        Settings.password_rel_layout.setVisibility(View.GONE);
                        con.startActivity(new Intent(con, MainActivity.class));

                    }
                }
                    else
                    {
                        Toast.makeText(con, "Invalid password", Toast.LENGTH_SHORT).show();
                    }



            }

            @Override
            public void onFailure(Call<ChangePasswordValue> call, Throwable t) {
                Toast.makeText(con, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
