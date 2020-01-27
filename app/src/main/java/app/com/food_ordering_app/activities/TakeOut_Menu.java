package app.com.food_ordering_app.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.Web_Service.EmptyCard_Api;
import app.com.food_ordering_app.Web_Service.GetCart_Api;
import app.com.food_ordering_app.Web_Service.RestaurantListingMenu_Api;
import app.com.food_ordering_app.adapter.Menu_Item_Takeout_Adapter;
import app.com.food_ordering_app.adapter.MostPopular_Adapter;
import app.com.food_ordering_app.fragment.Home;
import app.com.food_ordering_app.global.Global;

public class TakeOut_Menu extends AppCompatActivity {


    ListView list_popular_takeOut;
    ListView list_menu_takeOut;
    RelativeLayout rel_back_takeOut_menu;
    public static RelativeLayout cart_rel_takeout_menu,rel_lay_listview_menuItem;
    TextView txt_change_delivery,txt_resturnt_loc;
     public static TextView txt_delivery_price;
    TextView tool_menu_Takeout_screen;
    public static TextView txt_takeOut;
    TextView txt_resturnt_name;
    String restaurant_id_get,rest_name;
    public static TextView tv_item_total_mstPopular;
    public static String txt_change_to;
    TextView rel_item_txt_MoastPopular;
    public static  TextView rel_item_txt_takeout_Menu,price_getCartScreen_takeout_Menu;
    String show_view="delivery";
    ScrollView mScrollView;
    String type_D_T="T";
    public static String restaurant_address;
    String restaurant_name,restaurant_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_out_menu);

        //----------to set visibility gone on first come--------------
        txt_change_to="Take Out Menu";



        //-------------------ids-----------------------------------------------------
        list_popular_takeOut=(ListView)findViewById(R.id.list_popular_takeOut);
        rel_item_txt_MoastPopular=(TextView)findViewById(R.id.txt_most_polpular);
        list_menu_takeOut=(ListView)findViewById(R.id.list_menu_takeOut);
        txt_change_delivery=(TextView)findViewById(R.id.txt_change_delivery);
        cart_rel_takeout_menu=(RelativeLayout)findViewById(R.id.cart_rel_takeout_menu);
        rel_lay_listview_menuItem=(RelativeLayout)findViewById(R.id.rel_lay_listview_menuItem);
        rel_item_txt_takeout_Menu=(TextView)findViewById(R.id.rel_item_txt_takeout_Menu);
        price_getCartScreen_takeout_Menu=(TextView)findViewById(R.id.price_getCartScreen_takeout_Menu);
        tv_item_total_mstPopular=(TextView)findViewById(R.id.tv_item_total_mstPopular);

        mScrollView= (ScrollView)findViewById(R.id.scrollview_takeout);
        mScrollView.setFillViewport(true);


       //----------getting restaurant id----------------------------------
         restaurant_id_get = getIntent().getStringExtra("restaurant_id");
         rest_name = getIntent().getStringExtra("restaurant_name");







        //-----------shared preferences to get user_id---------------------------------------------
        SharedPreferences sharedPreferences_TakeoutMenu=getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        String user_id_get =  sharedPreferences_TakeoutMenu.getString("user_id","");


        //-------------------reataurant listing api hit------------------------------------------------------
        RestaurantListingMenu_Api restaurantListingMenu_api=new RestaurantListingMenu_Api(list_popular_takeOut,restaurant_id_get,list_menu_takeOut,cart_rel_takeout_menu,rel_item_txt_MoastPopular,rel_item_txt_takeout_Menu,price_getCartScreen_takeout_Menu,TakeOut_Menu.this);
        restaurantListingMenu_api.restaurantListMenu(getApplication(),user_id_get,restaurant_id_get,type_D_T);


        init();
        work();


    }

    public void init()
    {
        txt_delivery_price=(TextView)findViewById(R.id.txt_delivery_price);
        txt_takeOut=(TextView)findViewById(R.id.txt_takeOut);
        txt_resturnt_name=(TextView)findViewById(R.id.txt_resturnt_name);
        txt_resturnt_loc=(TextView)findViewById(R.id.txt_resturnt_loc);
        rel_back_takeOut_menu=(RelativeLayout) findViewById(R.id.rel_back_takeOut_menu);
        tool_menu_Takeout_screen=(TextView)findViewById(R.id.tool_menu_Takeout_screen);
    }

    public void work()
    {
        //------------------------intent to get restaurant details and set details----------------------------------
        txt_resturnt_name.setText(getIntent().getStringExtra("restaurant_name"));
        restaurant_address = getIntent().getStringExtra("restaurant_address");
        txt_resturnt_loc.setText(restaurant_address);


        restaurant_name = getIntent().getStringExtra("restaurant_name");

        //----------setting title name-------------------------------------------------
        tool_menu_Takeout_screen.setText(restaurant_name);
        tool_menu_Takeout_screen.setText(restaurant_id);







        //---------------------change_Delivery-----------------------------
        txt_change_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (show_view.equalsIgnoreCase("delivery"))
                {
                    txt_takeOut.setText("Delivery Menu");
                    txt_change_delivery.setText(" Change to \nTake Out");
                    txt_delivery_price.setVisibility(View.VISIBLE);
                    txt_change_to="Delivery Menu";
                    show_view="takeout";
                    type_D_T="D";




                    //----------empty cart api hit-------------------------------------------------------------------------

                   /* //-----------getting user_id and restaurant_id from add_cart to empty cart-------------------------------
                    SharedPreferences  sharedPreferencesHomeAdapt = getSharedPreferences("MyCart",Context.MODE_PRIVATE);
                    final String user_id_cart_Add = sharedPreferencesHomeAdapt.getString("cart_value_user_id","");
                    final String restaurant_id_cart_Add = sharedPreferencesHomeAdapt.getString("cart_value_restaurant_id","");
*/
                    //-----------shared preferences to get user_id---------------------------------------------
                    SharedPreferences sharedPreferences_TakeoutMenu=getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                    String user_id_get =  sharedPreferences_TakeoutMenu.getString("user_id","");


                   /* if (user_id_get!=null && restaurant_id!=null)
                    {
                    EmptyCard_Api emptyCard_Api = new EmptyCard_Api(restaurant_name,restaurant_address,restaurant_id);
                    emptyCard_Api.emptyCard(TakeOut_Menu.this, user_id_get, restaurant_id,"cmp_Takeout");
                    }*/
               //     Log.e("jldsfvjklwer1111111",user_id_get+" dsfde");
                //    Log.e("jldsfvjklwer111111",restaurant_id_get+" dsfde");



                  /*  //-----------shared preferences to get user_id---------------------------------------------
                    SharedPreferences sharedPreferences_TakeoutMenu=getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                    String user_id_get =  sharedPreferences_TakeoutMenu.getString("user_id","");

*/
                    //-------------------reataurant listing api hit------------------------------------------------------
                    RestaurantListingMenu_Api restaurantListingMenu_api=new RestaurantListingMenu_Api(list_popular_takeOut,restaurant_id_get,list_menu_takeOut,cart_rel_takeout_menu,rel_item_txt_MoastPopular,rel_item_txt_takeout_Menu,price_getCartScreen_takeout_Menu,TakeOut_Menu.this);
                    restaurantListingMenu_api.restaurantListMenu(getApplication(),user_id_get,restaurant_id_get,type_D_T);
                }

                else if (show_view.equalsIgnoreCase("takeout"))
                {
                    txt_takeOut.setText("Take Out Menu");
                    txt_change_delivery.setText(" Change to \nDelivery");
                    txt_delivery_price.setVisibility(View.GONE);
                    txt_change_to="Take Out Menu";
                    show_view="delivery";
                    type_D_T="T";


                    //-----------shared preferences to get user_id---------------------------------------------
                    SharedPreferences sharedPreferences_TakeoutMenu=getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                    String user_id_get =  sharedPreferences_TakeoutMenu.getString("user_id","");

                  /* if (user_id_get!=null && restaurant_id_get!=null)
                    {
                        EmptyCard_Api emptyCard_Api = new EmptyCard_Api(restaurant_name,restaurant_address,restaurant_id);
                        emptyCard_Api.emptyCard(TakeOut_Menu.this, user_id_get, restaurant_id_get,"cmp_Takeout");
                        Log.e("jldsfvjklwer",user_id_get+" dsfde");
                        Log.e("jldsfvjklwer",restaurant_id_get+" dsfde");
                    }*/

                    //-------------------reataurant listing api hit------------------------------------------------------
                    RestaurantListingMenu_Api restaurantListingMenu_api=new RestaurantListingMenu_Api(list_popular_takeOut,restaurant_id_get,list_menu_takeOut,cart_rel_takeout_menu,rel_item_txt_MoastPopular,rel_item_txt_takeout_Menu,price_getCartScreen_takeout_Menu,TakeOut_Menu.this);
                    restaurantListingMenu_api.restaurantListMenu(getApplication(),user_id_get,restaurant_id_get,type_D_T);
                }


            }
        });


        //-----------------get cart onClick Listener(Takeout_Menu)------------------------------------------
       cart_rel_takeout_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                SharedPreferences sharedPreferences_MenuAdapter =getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                String user_id_MenuAdapter=sharedPreferences_MenuAdapter.getString("user_id","");



                //------------get Cart api hitting---------------------------------------------------------
                String count_qty=String.valueOf(Global.product_qty_Unique);
                GetCart_Api getCart_api = new GetCart_Api(count_qty,"menu");
                getCart_api.getCartMethod(TakeOut_Menu.this,user_id_MenuAdapter);




            }
        });




        //------------------------------back_button----------------------------
        rel_back_takeOut_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TakeOut_Menu.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

       //------------------------------------------------------------------------------------------


    }

     @Override
    public void onBackPressed()
     {
        super.onBackPressed();

         Intent intent = new Intent(this,MainActivity.class);
         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         startActivity(intent);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //-----------shared preferences of cart----------------------------
        SharedPreferences   sharedPreferences_cart = getSharedPreferences("PREFERENCES_CART", Context.MODE_PRIVATE);
        //---------------to show cart----------------------------------
        String res= sharedPreferences_cart.getString("cart_value","value");



            //--------------checking global variable is null to hide get cart as users comes   on this screen-------
            if(Global.product_qty_Unique==0)
            {
              cart_rel_takeout_menu.setVisibility(View.GONE);
            }

            //--------------checking global variable has value and show get cart to users when he comes   on this screen-------
            else {
                cart_rel_takeout_menu.setVisibility(View.VISIBLE);
                rel_item_txt_takeout_Menu.setText("Checkout(" + Global.product_qty_Unique + ")items");
                Log.e("get_price_here_Resume",Global.product_price_Unique+"");
               price_getCartScreen_takeout_Menu.setText("$"+Global.product_price_Unique+"");

            }
       }

}
