package app.com.food_ordering_app.Web_Service;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.food_ordering_app.adapter.HomeAdapter;
import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 10/25/2017.
 */

public class RestaurantSearchByName_Api {
    Context con;
    String restaurantname,user_id;
    ArrayList<HashMap<String,String>> arrayList_search_rest = new ArrayList<>();
    ListView listView;

    public void searchRestaurant(final Context context, String user_id, String restaurant_name, final ListView listView) {
        this.con=context;
       this.user_id=user_id;
       this.restaurantname=restaurant_name;
       this.listView=listView;

        Retrofit retrofit =new Retrofit.Builder()
                                      .baseUrl(Global.base_url)
                                      .addConverterFactory(GsonConverterFactory.create())
                                       .build();


        WebService webservice = retrofit.create(WebService.class);
        Call<SearchRestaurantNameValue> call = webservice.search_restaurant(user_id,restaurantname);
        call.enqueue(new Callback<SearchRestaurantNameValue>() {
            @Override
            public void onResponse(Call<SearchRestaurantNameValue> call, Response<SearchRestaurantNameValue> response) {


               if (response.isSuccessful())
               {

                   String status=response.body().getStatus();
                   if (status.equalsIgnoreCase("1"))
                   {
                       Log.e("ssssssssssssss2222","22222");

                       for (int i=0;i<response.body().getResult().getOpenRestaurants().size();i++)
                       {
                           HashMap<String,String> search_rest_Hashmap = new HashMap<String, String>();

                           String status_get = response.body().getStatus();
                           String restaurant_name = response.body().getResult().getOpenRestaurants().get(i).getRestaurantName();
                           String restaurant_id = response.body().getResult().getOpenRestaurants().get(i).getRestaurantId();
                           String restaurant_address = response.body().getResult().getOpenRestaurants().get(i).getRestaurantAddress();
                           String restaurant_image = response.body().getResult().getOpenRestaurants().get(i).getRestaurantImage();
                           String restaurant_opentime = response.body().getResult().getOpenRestaurants().get(i).getOpenTime();
                           String restaurant_closetime = response.body().getResult().getOpenRestaurants().get(i).getClosedTime();
                           String restaurant_distance = response.body().getResult().getOpenRestaurants().get(i).getDistance();

                         //---------adding opened rest details two hashmap-------------------
                           search_rest_Hashmap.put("restaurant_name",restaurant_name);
                           search_rest_Hashmap.put("restaurant_id",restaurant_id);
                           search_rest_Hashmap.put("restaurant_address",restaurant_address);
                           search_rest_Hashmap.put("restaurant_image",restaurant_image);
                           search_rest_Hashmap.put("open_time",restaurant_opentime);
                           search_rest_Hashmap.put("closed_time",restaurant_closetime);
                           search_rest_Hashmap.put("status_get",status_get);
                           search_rest_Hashmap.put("",restaurant_distance);





                           //-------adding hashmap to array list------------------------
                           arrayList_search_rest.add(search_rest_Hashmap);
                           Log.e("here_is_list_search",arrayList_search_rest+"");

                       }

                       //------------clearing opened restaurant list---------------------
                       Global.getOpenedRestaurantList().clear();

                       //-----------setting opened restaurant list----------------------
                       Global.setOpenedRestaurantList(arrayList_search_rest);


                       //----------home adapter to set restaurant details on home screen-----------------------
                           HomeAdapter.status="23";
                           HomeAdapter adpt =new HomeAdapter(context,arrayList_search_rest);
                           listView.setAdapter(adpt);



                   }
                   //-----------to clear restaurant list on home screen on searching wrong restaurant name--------------
                   else
                   {
                       Toast.makeText(context,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                     //  Toast.makeText(context,"Restaurant not Found", Toast.LENGTH_SHORT).show();
                       Global.getOpenedRestaurantList().clear();

                       //----------home adapter to clear restaurant details on home screen-----------------------
                       HomeAdapter.status="23";
                       HomeAdapter adpt =new HomeAdapter(context,arrayList_search_rest);
                       listView.setAdapter(adpt);

                   }


               }

            }



            @Override
            public void onFailure(Call<SearchRestaurantNameValue> call, Throwable t) {
                Toast.makeText(con, "Failure", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
