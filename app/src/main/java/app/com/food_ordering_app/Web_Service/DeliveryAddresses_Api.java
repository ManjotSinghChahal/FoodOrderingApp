package app.com.food_ordering_app.Web_Service;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import app.com.food_ordering_app.activities.Delivery_Location;
import app.com.food_ordering_app.adapter.DeliveryAdapter;
import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 1/9/2018.
 */

public class DeliveryAddresses_Api {
    Context con;
    String user_id_del_loc;
    ListView listview_delivery;
    ProgressDialog pd;
    ArrayList<HashMap<String,String>> arrayList_DelAddress = new ArrayList<>();
    public void deliveryAddress(Delivery_Location delivery_location, String user_id_del_loc, final ListView listview_delivery) {
        this.con= delivery_location;
        this.user_id_del_loc= user_id_del_loc;
        this.listview_delivery= listview_delivery;
        pd=new ProgressDialog(con);
        pd.setMessage("Loading ......");
        //--------used for null token and also add in manifest-----------------------------------
        pd.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        pd.show();


        Retrofit retrofit = new Retrofit.Builder()
                               .baseUrl(Global.base_url)
                               .addConverterFactory(GsonConverterFactory.create())
                               .build();
        WebService webService = retrofit.create(WebService.class);
        Call<DeliveryAddressValue> call = webService.deliveryAddress(user_id_del_loc);
        call.enqueue(new Callback<DeliveryAddressValue>() {
            @Override
            public void onResponse(Call<DeliveryAddressValue> call, Response<DeliveryAddressValue> response) {

                String status = response.body().getStatus().toString();

                if (response.isSuccessful())
                {

                    if (status.equalsIgnoreCase("1"))
                    {
                        pd.dismiss();

                       for (int i=0;i<response.body().getResult().getAddresses().size();i++)
                        {

                            String address=response.body().getResult().getAddresses().get(i).toString();

                            HashMap<String,String> hashMap_DelAddress = new HashMap<>();
                            hashMap_DelAddress.put("address",address);

                            arrayList_DelAddress.add(hashMap_DelAddress);
                        }


                        if (arrayList_DelAddress!=null) {

                            Set<HashMap<String, String>> set = new LinkedHashSet<>();
                            set.addAll(arrayList_DelAddress);

                            arrayList_DelAddress.clear();
                            arrayList_DelAddress.addAll(set);




                            DeliveryAdapter deliveryAdapter = new DeliveryAdapter(con, arrayList_DelAddress);
                            listview_delivery.setAdapter(deliveryAdapter);
                        }
                        else
                        {
                            Toast.makeText(con, "No Address", Toast.LENGTH_SHORT).show();
                        }



                    }

                }

            }

            @Override
            public void onFailure(Call<DeliveryAddressValue> call, Throwable t) {
                Toast.makeText(con, "Failure", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });



    }
}
