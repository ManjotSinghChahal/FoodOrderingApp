package app.com.food_ordering_app.Web_Service;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.food_ordering_app.activities.TakeOut_Menu;
import app.com.food_ordering_app.adapter.Menu_Item_Takeout_Adapter;
import app.com.food_ordering_app.adapter.MostPopular_Adapter;
import app.com.food_ordering_app.global.Global;
import app.com.food_ordering_app.utility.Helper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 10/17/2017.
 */

public class RestaurantListingMenu_Api {


    String user_id_get, restaurantList, t;
    ArrayList<HashMap<String, String>> mostPopularArraylist = new ArrayList<>();
    ArrayList<HashMap<String, String>> getMenuArraylist = new ArrayList<>();

    ArrayList<HashMap<String, String>> arrayListHM_mostPopular = new ArrayList<>();
    ArrayList<HashMap<String, String>> arrayListHM_MP_menuList = new ArrayList<>();
    ArrayList<HashMap<String, String>> arrayListHM_MP_menuListProducts = new ArrayList<>();
    ArrayList<HashMap<String, String>> menu_list = new ArrayList<>();
    ProgressDialog pd;


    public static ListView list_popular, list_menu_takeOut;
    public static  int most_Popular_Count;
    String restaurant_id_get;
    public  static String rest_address;
    RelativeLayout cart_rel_takeout_menu;
    TextView rel_item_txt_MoastPopular, rel_item_txt_takeout_Menu, price_getCartScreen_takeout_Menu;
    Global global;
    Context con;

    public RestaurantListingMenu_Api(ListView list_popular_takeOut, String restaurant_id_get, ListView list_menu_takeOut, RelativeLayout cart_rel_takeout_menu, TextView rel_item_txt_MoastPopular, TextView rel_item_txt_takeout_Menu, TextView price_getCartScreen_takeout_Menu,Context con) {
        pd=new ProgressDialog(con);
        pd.setMessage("Loading ......");
        //--------used for null token and also add in manifest-----------------------------------
        pd.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        pd.show();
        this.list_popular = list_popular_takeOut;
        this.restaurant_id_get = restaurant_id_get;
        this.list_menu_takeOut = list_menu_takeOut;
        this.cart_rel_takeout_menu = cart_rel_takeout_menu;
        this.rel_item_txt_MoastPopular = rel_item_txt_MoastPopular;
        this.rel_item_txt_takeout_Menu = rel_item_txt_takeout_Menu;
        this.price_getCartScreen_takeout_Menu = price_getCartScreen_takeout_Menu;
        this.con=con;
        global=(Global)con.getApplicationContext();


    }

    public void restaurantListMenu(Application application, final String userId_get, final String restaurant_id_get, final String t) {
        this.con = application;
        this.user_id_get = userId_get;
        this.restaurantList = restaurant_id_get;
        this.t = t;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Global.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebService webservice = retrofit.create(WebService.class);

        Call<RestaurantListingMenuValue> call = webservice.restaurantListMenu(user_id_get, restaurantList, t,"count");
        call.enqueue(new Callback<RestaurantListingMenuValue>() {
            @Override
            public void onResponse(Call<RestaurantListingMenuValue> call, Response<RestaurantListingMenuValue> response) {
                if (response.isSuccessful()) {

                    String status = response.body().getStatus().toString();
                    pd.dismiss();

                    if (t.equalsIgnoreCase("T"))
                    {
                        TakeOut_Menu.txt_delivery_price.setVisibility(View.GONE);
                    }
                    else if (t.equalsIgnoreCase("D"))
                    {
                        TakeOut_Menu.txt_delivery_price.setVisibility(View.VISIBLE);
                    }




                    if (status.equalsIgnoreCase("1")) {

                        //--------------restaurant details--------------------------------
                        String type = response.body().getResult().getType().toString();
                        String rest_id = response.body().getResult().getRestaurantDetails().getRestaurantId();
                        String rest_name = response.body().getResult().getRestaurantDetails().getRestaurantName();
                        String rest_image = response.body().getResult().getRestaurantDetails().getRestaurantImage();
                         rest_address = response.body().getResult().getRestaurantDetails().getRestauranAddress();


                        //===================to store most popular items===============================

                        for (int j = 0; j < response.body().getResult().getMostPopular().size(); j++)
                        {
                           String menu_id = response.body().getResult().getMostPopular().get(j).getMenuId();

                            for (int k = 0; k < response.body().getResult().getMostPopular().get(j).getProducts().size(); k++) {
                                String statusMostPopular = response.body().getResult().getMostPopular().get(j).getProducts().get(k).getPopularStatus();
                                if (statusMostPopular.equalsIgnoreCase("1")) {
                                    HashMap<String, String> hashMap_mostPopular = new HashMap<>();

                                    String product_name = response.body().getResult().getMostPopular().get(j).getProducts().get(k).getProductName();
                                    String product_id = response.body().getResult().getMostPopular().get(j).getProducts().get(k).getProductId();
                                    String product_desc = response.body().getResult().getMostPopular().get(j).getProducts().get(k).getProductDesc();
                                    String product_price = response.body().getResult().getMostPopular().get(j).getProducts().get(k).getProductPrice();


                                    hashMap_mostPopular.put("menu_name", "Most Popular");
                                    hashMap_mostPopular.put("product_id", product_id);
                                    hashMap_mostPopular.put("product_name", product_name);
                                    hashMap_mostPopular.put("product_desc", product_desc);
                                    hashMap_mostPopular.put("product_price", product_price);
                                    hashMap_mostPopular.put("menu_id", menu_id);
                                    hashMap_mostPopular.put("mostPopular_size",response.body().getResult().getMostPopular().get(j).getProducts().size()+"");


                                    arrayListHM_mostPopular.add(hashMap_mostPopular);
                                }
                            }

                        }

                  most_Popular_Count = arrayListHM_mostPopular.size();


                        Log.e("list_log_get000",arrayListHM_mostPopular+" hh");

                            if (!arrayListHM_mostPopular.isEmpty()) {
                                //------------setting total qty of items in textview on takeout screen(MOST POPULAR)-------
                                Global.setMost_popular(arrayListHM_mostPopular);
                                //-----------------set_adpater_most_popular----------------
                                list_popular.setVisibility(View.VISIBLE);
                                MostPopular_Adapter mostPopular_adapter = new MostPopular_Adapter(con, arrayListHM_mostPopular, userId_get, restaurant_id_get, rel_item_txt_MoastPopular);
                                list_popular.setAdapter(mostPopular_adapter);
                                Helper.getListViewSize(list_popular);

                                Log.e("list_log_up","tg5trv");
                            }
                            else
                            {
                                list_popular.setVisibility(View.GONE);
                                Log.e("list_log_down","tg5trv");
                                TakeOut_Menu.tv_item_total_mstPopular.setText("");
                            }




                        //-----------------all menus only-----------------------------------------------
                        for (int v = 0; v < response.body().getResult().getMostPopular().size(); v++) {

                            String menu_id = response.body().getResult().getMostPopular().get(v).getMenuId();
                            String menu_name = response.body().getResult().getMostPopular().get(v).getMenuName();
                            String menu_index = response.body().getResult().getMostPopular().get(v).getIndexNo();
                            String menu_count = response.body().getResult().getMostPopular().get(v).getCount();





                            HashMap<String, String> hashMap_MP_menuList = new HashMap<>();


                            hashMap_MP_menuList.put("menu_id", menu_id);
                            hashMap_MP_menuList.put("menu_name", menu_name);
                            hashMap_MP_menuList.put("menu_index", menu_index);
                            hashMap_MP_menuList.put("menu_count",menu_count);

                            menu_list.add(hashMap_MP_menuList);
                        }

                        global.getMenu_list().clear();
                        global.setMenu_list(menu_list);

                        Log.e("list_log_get",global.getMenu_list()+" hh");

              if (!global.getMenu_list().isEmpty())
              {


                  //-----------------set_adpater_menu_list----------------
                        TakeOut_Menu.rel_lay_listview_menuItem.setVisibility(View.VISIBLE);
                        Menu_Item_Takeout_Adapter menu_item_takeout_adapter = new Menu_Item_Takeout_Adapter(con, global.getMenu_list(), userId_get, restaurant_id_get, rel_item_txt_MoastPopular, cart_rel_takeout_menu, rel_item_txt_takeout_Menu, price_getCartScreen_takeout_Menu);
                        list_menu_takeOut.setAdapter(menu_item_takeout_adapter);
                        //---------used to set full list height inside scrollview----------
                        Helper.getListViewSize(list_menu_takeOut);

              }
              else

              {
                  TakeOut_Menu.rel_lay_listview_menuItem.setVisibility(View.GONE);
                  TakeOut_Menu.tv_item_total_mstPopular.setText("");


              }


                        //================all menus products=====================================
                        for (int m = 0; m < response.body().getResult().getMostPopular().size(); m++) {

                            String menu_id = response.body().getResult().getMostPopular().get(m).getMenuId();
                            String menu_name = response.body().getResult().getMostPopular().get(m).getMenuName();


                            for (int n = 0; n < response.body().getResult().getMostPopular().get(m).getProducts().size(); n++) {
                                HashMap<String, String> hashMap_MP_menuList_Products = new HashMap<>();

                                String product_id = response.body().getResult().getMostPopular().get(m).getProducts().get(n).getProductId();
                                String product_name = response.body().getResult().getMostPopular().get(m).getProducts().get(n).getProductName();
                                String product_price = response.body().getResult().getMostPopular().get(m).getProducts().get(n).getProductPrice();
                                String product_desc = response.body().getResult().getMostPopular().get(m).getProducts().get(n).getProductDesc();

                                hashMap_MP_menuList_Products.put("menu_id", menu_id);
                                hashMap_MP_menuList_Products.put("menu_name", menu_name);

                                hashMap_MP_menuList_Products.put("product_id", product_id);
                                hashMap_MP_menuList_Products.put("product_name", product_name);
                                hashMap_MP_menuList_Products.put("product_price", product_price);
                                hashMap_MP_menuList_Products.put("product_desc", product_desc);


                                arrayListHM_MP_menuListProducts.add(hashMap_MP_menuList_Products);


                            }


                        }
                       //---------------------------------Setting whole menu item-------------------
                        Global.getArrayListHM_MP_menuList_full_products().clear();
                        Global.setArrayListHM_MP_menuList_full_products(arrayListHM_MP_menuListProducts);


                    }

                }
            }

            @Override
            public void onFailure(Call<RestaurantListingMenuValue> call, Throwable t) {
                Toast.makeText(con, "Failure", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }
}
