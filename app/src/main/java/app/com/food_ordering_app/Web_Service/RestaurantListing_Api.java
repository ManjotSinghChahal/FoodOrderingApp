package app.com.food_ordering_app.Web_Service;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.com.food_ordering_app.activities.TakeOut_Menu;
import app.com.food_ordering_app.adapter.HomeAdapter;
import app.com.food_ordering_app.fragment.Home;
import app.com.food_ordering_app.global.Global;
import app.com.food_ordering_app.interfac.MyInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static app.com.food_ordering_app.fragment.Home.context_home;

/**
 * Created by admin on 10/12/2017.
 */

public class RestaurantListing_Api {
    Context context;
    String res_home;
     double lat_value,lng_value;

    ProgressDialog pd;
    public  ArrayList<HashMap<String, String>> restaurantList = new ArrayList<HashMap<String, String>>();   //open
    public  ArrayList<HashMap<String, String>> restaurantList_closed = new ArrayList<HashMap<String, String>>();  //open
    public ArrayList<HashMap<String, String>> restaurantList_both = new ArrayList<HashMap<String, String>>();  //both in one
  public   MyInterface inter;
    Global global;
    public RestaurantListing_Api(Context context,MyInterface inter){
        this.inter=inter;
        this.context=context;

        global=(Global)context.getApplicationContext();
    }

    public void resList(final Context activity, String res_home, double lat_value, double lng_value) {
      /* pd=new ProgressDialog(Home.context_home);
       pd.setMessage("Loading ......");
        //--------used for null token and also add in manifest-----------------------------------
        pd.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        pd.show();*/
        this.context=activity;
        this.res_home=res_home;
        this.lat_value=lat_value;
        this.lng_value=lng_value;

        Retrofit retrofit=new Retrofit.Builder()
                           .baseUrl(Global.base_url)
                           .addConverterFactory(GsonConverterFactory.create())
                           .build();


        Log.e("user_id",res_home);
        Log.e("aaaaaaa1111",lat_value+"");
        Log.e("aaaaaaa2222",lng_value+"");


        WebService webservise_home=retrofit.create(WebService.class);
        Call<RestaurantListingValue> call=webservise_home.restaurantList(res_home,lat_value,lng_value);
        call.enqueue(new Callback<RestaurantListingValue>() {
            @Override
            public void onResponse(Call<RestaurantListingValue> call, Response<RestaurantListingValue> response) {
                String status=response.body().getStatus().toString();


                if (status.equalsIgnoreCase("1"))
                {
                    Log.e("ssssssssssssss1111","22222");
                //  pd.dismiss();
                    //---------for open restaurant-----------------------------------------------------------------
                    for (int i=0;i<response.body().getResultH().getOpenResturant().size();i++) {
                        String restaurant_id = response.body().getResultH().getOpenResturant().get(i).getRestaurantId().toString();
                        String restaurant_name = response.body().getResultH().getOpenResturant().get(i).getRestaurantName().toString();
                        String restaurant_address = response.body().getResultH().getOpenResturant().get(i).getRestaurantAddress().toString();
                        String restaurant_image = response.body().getResultH().getOpenResturant().get(i).getRestaurantImage().toString();
                        String open_time = response.body().getResultH().getOpenResturant().get(i).getOpenTime().toString();
                        String closed_time = response.body().getResultH().getOpenResturant().get(i).getClosedTime().toString();
                        String status_get = response.body().getResultH().getOpenResturant().get(i).getStatus().toString();
                        String id_rest=response.body().getResultH().getOpenResturant().get(i).getRestaurantId().toString();

                       Log.e("rest_id",restaurant_id);

                        Map<String, String> restaurantList_Map = new HashMap<String, String>();

                        restaurantList_Map.put("restaurant_id",restaurant_id);
                        restaurantList_Map.put("restaurant_name",restaurant_name);
                        restaurantList_Map.put("restaurant_address",restaurant_address);
                        restaurantList_Map.put("restaurant_image",restaurant_image);
                        restaurantList_Map.put("open_time",open_time);
                        restaurantList_Map.put("closed_time",closed_time);
                        restaurantList_Map.put("status_get",status_get);

                        restaurantList.add((HashMap<String, String>) restaurantList_Map);

                    }



                    //------------for closed restaurant---------------------------------------------------
                    for (int j=0;j<response.body().getResultH().getClosedResturant().size();j++)
                    {
                        String restaurant_id_closed=response.body().getResultH().getClosedResturant().get(j).getRestaurantId().toString();
                        String restaurant_name_closed=response.body().getResultH().getClosedResturant().get(j).getRestaurantName().toString();
                        String restaurant_address_closed=response.body().getResultH().getClosedResturant().get(j).getRestaurantAddress().toString();
                        String restaurant_image_closed=response.body().getResultH().getClosedResturant().get(j).getRestaurantImage().toString();
                        String open_time_closed=response.body().getResultH().getClosedResturant().get(j).getOpenTime().toString();
                        String closed_time_closed=response.body().getResultH().getClosedResturant().get(j).getClosedTime().toString();
                        String status_get_closed=response.body().getResultH().getClosedResturant().get(j).getStatus().toString();

                        Map<String,String> restaurantList_Mapclosed=new HashMap<String, String>();
                        restaurantList_Mapclosed.put("restaurant_id",restaurant_id_closed);
                        restaurantList_Mapclosed.put("restaurant_name",restaurant_name_closed);
                        restaurantList_Mapclosed.put("restaurant_address",restaurant_address_closed);
                        restaurantList_Mapclosed.put("restaurant_image",restaurant_image_closed);
                        restaurantList_Mapclosed.put("open_time",open_time_closed);
                        restaurantList_Mapclosed.put("closed_time",closed_time_closed);
                        restaurantList_Mapclosed.put("status_get",status_get_closed);

                        restaurantList_closed.add((HashMap<String, String>) restaurantList_Mapclosed);

                    }



                    restaurantList_both.addAll(restaurantList);
                    restaurantList_both.addAll(restaurantList_closed);





                   //---set Status to show open and closed restaurant list------------------
                    HomeAdapter.status="77";


                    global.getRestaurantList_both().clear();
                    global.setRestaurantList_both(restaurantList_both);



                    //-------------------------------------------------------------------------------
                  inter.onSuccess();

                }
                //--clearing list on response=0-------------------------------------
                else
                {
                    global.getRestaurantList_both().clear();
              //    pd.dismiss();
                    Log.e("not_come_issue",global.getRestaurantList_both()+"");
                }
            }

            @Override
            public void onFailure(Call<RestaurantListingValue> call, Throwable t) {
          //     pd.dismiss();
                Toast.makeText(activity, "Failure", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
