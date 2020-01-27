package app.com.food_ordering_app.Web_Service;

import android.content.Context;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.food_ordering_app.activities.*;
import app.com.food_ordering_app.adapter.Menu_Item_Takeout_Adapter;
import app.com.food_ordering_app.adapter.MostPopular_Adapter;
import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 10/23/2017.
 */

public class ProductsMenu_Api {
    Context con;
    String user_id_get;
    String restaurant_id;
    String menu_id;
    String t,menu_name;
    ListView gridview;
    String edit_Search_Text;
    TextView rel_item_txt,price_getCartScreen_tv;
    ArrayList<HashMap<String,String>> productMenuArrayList =new ArrayList<>();

    public ProductsMenu_Api(ListView gridview, TextView rel_item_txt, TextView price_getCartScreen_tv) {
        this.gridview=gridview;
        this.rel_item_txt=rel_item_txt;
        this.price_getCartScreen_tv=price_getCartScreen_tv;


    }

    public void productMenu(Context menu, final String user_id_get, final String restaurant_id, final String menu_id, String t, final String menu_name) {
        this.con=menu;
        this.user_id_get=user_id_get;
        this.restaurant_id=restaurant_id;
        this.menu_id=menu_id;
        this.t=t;
        this.menu_name=menu_name;


        //-----------retrofit---------------------------------

        Retrofit retrofit=new Retrofit.Builder()
                                    .baseUrl(Global.base_url)
                                    .addConverterFactory(GsonConverterFactory.create())
                                     .build();

        WebService webservice=retrofit.create(WebService.class);
        Log.e("list_view","user_id " +user_id_get +" rest_id " +restaurant_id +" menu_id" +menu_id +" type " +t);
        Call<ProductMenuValue> call=webservice.productMenu(user_id_get,restaurant_id,menu_id,t);
        call.enqueue(new Callback<ProductMenuValue>() {
            @Override
            public void onResponse(Call<ProductMenuValue> call, Response<ProductMenuValue> response) {

                if (response.isSuccessful())
                {
                    String status=response.body().getStatus().toString();
                    if (status.equalsIgnoreCase("1"))

                    {
                        Menu_Item_Takeout_Adapter.pd_menu_Takeout_Adapter.dismiss();

                        for (int i=0;i<response.body().getResult().size();i++)
                        {
                          HashMap<String,String> getMenuList=new HashMap<String, String>();

                          String product_id =  response.body().getResult().get(i).getProductId().toString();
                          String product_name =  response.body().getResult().get(i).getProductName().toString();
                          String product_price =  response.body().getResult().get(i).getProductPrice().toString();
                          String product_desc =  response.body().getResult().get(i).getProductDesc().toString();
                          String product_image =  response.body().getResult().get(i).getProductImage().toString();

                            getMenuList.put("product_id",product_id);
                            getMenuList.put("product_name",product_name);
                            getMenuList.put("product_price",product_price);
                            getMenuList.put("product_desc",product_desc);
                            getMenuList.put("product_image",product_image);
                            getMenuList.put("menu_id",menu_id);
                            getMenuList.put("menu_name",menu_name);


                            productMenuArrayList.add(getMenuList);
                        }



                        //---------clearing list----------------------------------------------------
                        Global.getGetMenu().clear();

                       //-------------setting value to array list------------------------------------
                        Global.setGetMenu(productMenuArrayList);


                        Log.e("get_arrayList_new",Global.getGetMenu()+"");
                        //---------------set_Adapter_product_menu-------------------------------------
                       // gridview.setAdapter(new Menu_Adapter((Menu) con,Global.getGetMenu(),restaurant_id,menu_id));
                       // Log.e("hereismenu",menu_id);
                        /*MostPopular_Adapter menu_adapter = new MostPopular_Adapter(con,Global.getGetMenu(),user_id_get,restaurant_id,rel_item_txt_MoastPopular);
                        RestaurantListingMenu_Api.list_popular.setAdapter(menu_adapter);*/




                    }
                    //-------clearing list on no response------------------------------
                    else if (status.equalsIgnoreCase("0"))
                    {
                        //---------clearing list----------------------------------------------------
                        Global.getGetMenu().clear();
                    }

                }


            }

            @Override
            public void onFailure(Call<ProductMenuValue> call, Throwable t) {
                Toast.makeText(con, "Failure", Toast.LENGTH_SHORT).show();
                Menu_Item_Takeout_Adapter.pd_menu_Takeout_Adapter.dismiss();

            }
        });

    }
}
