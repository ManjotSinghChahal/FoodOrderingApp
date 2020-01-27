package app.com.food_ordering_app.Web_Service;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import app.com.food_ordering_app.activities.MainActivity;
import app.com.food_ordering_app.fragment.Profile;
import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 9/28/2017.
 */

public class Profile_Api  {
    // public static    String home_City,home_Country;
    public static String home_Street="";
    public static String home_City="";
    public static String home_Country="";
    public static String home_Province="";
     public static   String work_Street,work_City,work_Country;
     SharedPreferences sharedPreferences_Profile;
    String value_from_class;

    public Profile_Api(String value_from_class) {
        this.value_from_class=value_from_class;
    }

    public void profile(final Context context, String user_id)
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Global.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebService interface_method1=retrofit.create(WebService.class);


   Call<ProfileValue> call=interface_method1.profile(user_id);

   call.enqueue(new Callback<ProfileValue>() {
    @Override
    public void onResponse(Call<ProfileValue> call, Response<ProfileValue> response) {
        String response_get=response.body().getStatus().toString();
        if (response_get.equalsIgnoreCase("1")) {


            //------------getting home address----------------------------------------------------
           home_Street=response.body().getResult().getAddress().getHome().getStreet().toString();
           home_City=response.body().getResult().getAddress().getHome().getCity().toString();
           home_Country=response.body().getResult().getAddress().getHome().getCountry().toString();
           home_Province=response.body().getResult().getAddress().getHome().getProvince().toString();

          Log.e("4",response.body().getResult().getAddress().getHome().getPostalCode().toString());
          Log.e("5",response.body().getResult().getAddress().getHome().getLat().toString());
          Log.e("6",response.body().getResult().getAddress().getHome().getLong().toString());




           //-----------comming from main actitvity executes to set on drawer------------------------------------------
            if (value_from_class.equalsIgnoreCase("Profile"))

            {
                Log.e("pic_url_", response.body().getResult().getUserImage().toString());
                Picasso.with(context).load(response.body().getResult().getUserImage().toString()).into(Profile.img_profile);
                Profile.edit_Name.setText(response.body().getResult().getFirstName().toString());
                Profile.edit_LName.setText(response.body().getResult().getLastName().toString());
                Profile.edit_email.setText(response.body().getResult().getEmail().toString());
                Profile.edit_mobile.setText(response.body().getResult().getPhoneNumber().toString());
                response.body().getResult().getAddress().getHome().getCountry();

                Profile.edit_street.setText(home_Street);
                Profile.edit_city.setText(home_City);
                Profile.edit_postal.setText(response.body().getResult().getAddress().getHome().getPostalCode().toString());

                Profile.txt_spinner.setText(home_Country + " ");
                Profile.edt_province.setText(home_Province + " ");


            }
            //-----------comming from main actitvity executes to set on drawer------------------------------------------
            else if (value_from_class.equalsIgnoreCase("MainActivity"))
            {
                MainActivity.text_name.setText(response.body().getResult().getFirstName().toString());
                Picasso.with(context).load(response.body().getResult().getUserImage().toString()).into(MainActivity.profile_pic);
            }



        }
        else

        {


        }
    }

    @Override
    public void onFailure(Call<ProfileValue> call, Throwable t) {
        Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
    }
});


    }
}
