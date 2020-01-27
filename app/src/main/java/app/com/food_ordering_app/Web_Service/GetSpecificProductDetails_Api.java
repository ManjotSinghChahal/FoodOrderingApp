package app.com.food_ordering_app.Web_Service;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import app.com.food_ordering_app.adapter.AddonsAdapter;
import app.com.food_ordering_app.adapter.MostPopular_Adapter;
import app.com.food_ordering_app.global.Global;
import app.com.food_ordering_app.interfac.MyInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 11/2/2017.
 */

public class GetSpecificProductDetails_Api {
    Context con;
    String user_id_get,restaurant_id,menu_id,product_id;
    ArrayList<HashMap<String,String>> category_ArrayLishHashMap= new ArrayList<HashMap<String, String>>();
    RelativeLayout rel_layout_cart;
    TextView rel_item_txt;
    MyInterface inter;
    Global global;
    ArrayList<String> product_arrayList;




    public GetSpecificProductDetails_Api( RelativeLayout rel_layout_cart,TextView rel_item_txt) {

        this.rel_layout_cart=rel_layout_cart;
        this.rel_item_txt=rel_item_txt;



    }

    public void getSpecificProductDetails(final Context con, final String user_id_get, final String restaurant_id, final String menu_id,final String product_id, final MyInterface inter) {
        this.con=con;
        this.user_id_get=user_id_get;
        this.restaurant_id=restaurant_id;
        this.menu_id=menu_id;
        this.product_id=product_id;
        this.inter=inter;


        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(Global.base_url)
                                    .addConverterFactory(GsonConverterFactory.create())
                                     .build();

        WebService webservice = retrofit.create(WebService.class);

        Call<GetSpecificProductDetailsValue> call = webservice.getProductDetails(user_id_get,restaurant_id,menu_id,product_id);
        call.enqueue(new Callback<GetSpecificProductDetailsValue>() {
            @Override
            public void onResponse(Call<GetSpecificProductDetailsValue> call, Response<GetSpecificProductDetailsValue> response) {

               if (response.isSuccessful())
               {
                   global=(Global)con.getApplicationContext();
                   String status = response.body().getStatus().toString();


                     Log.e("cgfvgeafv",response.body().getMessage());
                     Log.e("cgfvgeafv",response.body().getStatus());

                   product_arrayList = new ArrayList<String>();

                   if (status.equalsIgnoreCase("1"))
                   {



                       String product_id  =response.body().getResult().getProductDetails().getProductId();
                       String product_name =response.body().getResult().getProductDetails().getProductName();
                       String product_price  =response.body().getResult().getProductDetails().getProductPrice();
                       String product_image =response.body().getResult().getProductDetails().getProductImage();
                       String product_desc =response.body().getResult().getProductDetails().getProductDesc();



                       //--------making product_id global to access in add to cart--------------
                       Global.product_id_Unique = product_id;


                       HashMap<String,String> category_HashMap;

                       for (int j=0;j<response.body().getResult().getProductDetails().getAddons().size();j++)
                       {


                           String category_id = response.body().getResult().getProductDetails().getAddons().get(j).getCategoryId();
                           String category_name = response.body().getResult().getProductDetails().getAddons().get(j).getCategoryName();

                           for (int k=0;k<response.body().getResult().getProductDetails().getAddons().get(j).getAds().size();k++)
                           {


                               category_HashMap = new HashMap<String, String>();

                               String id = response.body().getResult().getProductDetails().getAddons().get(j).getAds().get(k).getId();
                               String add_name = response.body().getResult().getProductDetails().getAddons().get(j).getAds().get(k).getAddName();
                               String add_price = response.body().getResult().getProductDetails().getAddons().get(j).getAds().get(k).getAddPrice();
                               String add_image = response.body().getResult().getProductDetails().getAddons().get(j).getAds().get(k).getAddImage();
                               String add_desc = response.body().getResult().getProductDetails().getAddons().get(j).getAds().get(k).getAddDesc();



                               category_HashMap.put("id",id);
                               category_HashMap.put("add_name",add_name);
                               category_HashMap.put("add_price",add_price);
                               category_HashMap.put("category_id",category_id);
                               category_HashMap.put("category_name",category_name);



                               category_ArrayLishHashMap.add(category_HashMap);





                           }




                       }

                       //--clearing global list and adding array to it----------
                       global.getAdd_on().clear();
                       global.setAdd_on(category_ArrayLishHashMap);
                       Log.e("list_g;obal",global.getAdd_on()+"");




                       inter.onSuccess();

                   }
                   else
                   {
                       //--clearing global list and adding array to it----------
                       global.getAdd_on().clear();
                   }



               }

            }

    @Override
    public void onFailure(Call<GetSpecificProductDetailsValue> call, Throwable t) {

        Toast.makeText(con,  "Failure", Toast.LENGTH_SHORT).show();
       }
    });

        }
        }
