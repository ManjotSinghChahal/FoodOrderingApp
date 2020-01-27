package app.com.food_ordering_app.Web_Service;

import android.content.Context;
import android.util.Log;

import app.com.food_ordering_app.activities.MainActivity;
import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 10/27/2017.
 */

public class UpdateDevice_id_Api {
    Context con;
    String res_updateLatLog,android_id,type_device;
    public void updateDevice_id(MainActivity mainActivity, String res_updateLatLog, String android_id,String type_device) {
        this.con=mainActivity;
        this.res_updateLatLog=res_updateLatLog;
        this.android_id=android_id;
        Log.e("device_id",android_id);
        this.type_device=type_device;

        Retrofit retrofit = new Retrofit.Builder()
                             .baseUrl(Global.base_url)
                             .addConverterFactory(GsonConverterFactory.create())
                             .build();

        WebService webservice = retrofit.create(WebService.class);
        Call<UpdateDeviceIdValue> call = webservice.updateDevice_Id(res_updateLatLog,android_id,type_device);
        call.enqueue(new Callback<UpdateDeviceIdValue>() {
            @Override
            public void onResponse(Call<UpdateDeviceIdValue> call, Response<UpdateDeviceIdValue> response) {

                if (response.isSuccessful())
                {

                    String status = response.body().getStatus();
                    if (status.equalsIgnoreCase("1"))
                    {





                    }

                }

            }

            @Override
            public void onFailure(Call<UpdateDeviceIdValue> call, Throwable t) {

            }
        });

    }
}
