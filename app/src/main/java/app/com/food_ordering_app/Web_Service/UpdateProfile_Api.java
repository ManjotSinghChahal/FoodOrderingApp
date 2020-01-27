package app.com.food_ordering_app.Web_Service;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;


import java.io.File;

import app.com.food_ordering_app.fragment.Profile;
import app.com.food_ordering_app.global.Global;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by admin on 10/10/2017.
 */

public class UpdateProfile_Api {
    Context con;
    String user_image;
    String res,name,lname,mobile,street_name,city_name,country_name,postal,city_name_work,country_name_work,postal_name_work;
    public void update_profile(final Context applicationContext, String resSp, String name, String l_name, String mobile, String street_name, String city_name, String country_name, String postal, String work_country, String postal_name_work,String city_name_work,  String user_image) {
        this.con=applicationContext;
        this.res=resSp;
        this.name=name;
        this.lname=l_name;
        this.mobile=mobile;
        this.street_name=street_name;
        this.city_name=city_name;
        this.country_name=country_name;
        this.postal=postal;

        this.country_name_work=work_country;
        this.postal_name_work=postal_name_work;
        this.city_name_work=city_name_work;
       this.user_image=user_image;


    Retrofit retrofit=new Retrofit.Builder()
                           .baseUrl(Global.base_url)
                          .addConverterFactory(GsonConverterFactory.create())
                          .build();

/*        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(user_image+"");

        // Parsing any Media type file
        RequestBody requestBodye = RequestBody.create(MediaType.parse("image"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBodye);
        RequestBody filename = RequestBody.create(MediaType.parse("image"), file.getName());*/




        WebService service=retrofit.create(WebService.class);

        Call<UpdateProfileValue> call=service.updateProfile(res,name,lname,mobile,street_name,city_name,country_name,postal,country_name_work,postal_name_work,city_name_work,"fileToUpload"+"");
        call.enqueue(new Callback<UpdateProfileValue>() {
            @Override
            public void onResponse(Call<UpdateProfileValue> call, Response<UpdateProfileValue> response) {
                String status=response.body().getStatus().toString();
                if (status.equalsIgnoreCase("1"))
                {
                    Log.e("stttt",response.body().getMessage());
                    Log.e("stttt",response.body().getStatus());
                    Toast.makeText(applicationContext ,response.body().getMessage().toString()+ "", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<UpdateProfileValue> call, Throwable t) {

            }
        });
}
}