package app.com.food_ordering_app.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.Web_Service.CommonUtilsImages;
import app.com.food_ordering_app.Web_Service.GetCart_Api;
import app.com.food_ordering_app.Web_Service.ProductsMenu_Api;
import app.com.food_ordering_app.adapter.AddonsAdapter;
import app.com.food_ordering_app.global.Global;

/**
 * Created by admin on 10/7/2017.
 */

public class Menu extends Activity {
    ImageView img_bcakArrow;
    GridView gridview;
    String res2;
    ArrayList<HashMap<String,String>> arrayListMenuList;
    EditText edt_search;
    String edit_Search_Text;
    String restaurant_id,menu_id,user_id_get;
    TextView rel_item_txt,price_getCartScreen_tv;

    RelativeLayout rel_back_menu;
    RelativeLayout rel_layout_cross_menu;
    Boolean cross_status=false;

 public static    RelativeLayout rel_takeout_checkout_cart_menu;
    TextView title_menu_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        //----------------------ids--------------------------------------------------
        gridview = (GridView) findViewById(R.id.gridview_menu);
        edt_search = (EditText) findViewById(R.id.edt_search);
        rel_item_txt=(TextView)findViewById(R.id.rel_item_txt_takeout_Menu);
        price_getCartScreen_tv=(TextView)findViewById(R.id.price_getCartScreen_takeout_Menu);
        rel_back_menu=(RelativeLayout) findViewById(R.id.rel_back_menu);
        rel_layout_cross_menu=(RelativeLayout) findViewById(R.id.rel_layout_cross_menu);


        //-----------shared preferences to get user_id---------------------------------------------
        SharedPreferences sharedPreferences_TakeoutMenu=getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
         user_id_get =  sharedPreferences_TakeoutMenu.getString("user_id","");

        //------------getting restaurant id--------------------------------------
         restaurant_id=getIntent().getStringExtra("restaurant_id");


       //---------------getting menu id-----------------------------------
        menu_id=getIntent().getStringExtra("menu_id");



      //--------------product menu api hit------------------------------------------
      //  ProductsMenu_Api productsMenu_api=new ProductsMenu_Api(gridview,rel_item_txt,price_getCartScreen_tv);
     //   productsMenu_api.productMenu(this,user_id_get,restaurant_id,menu_id,"T");
        Log.e("rest_id",restaurant_id);
        Log.e("menu_id",menu_id);




      //  init();
      //  work();


    }

    private void init() {
       /*  img_bcakArrow=(ImageView)findViewById(R.id.img_bcakArrow);
         title_menu_screen=(TextView)findViewById(R.id.title_menu_screen);
         rel_takeout_checkout_cart_menu=(RelativeLayout)findViewById(R.id.rel_takeout_checkout_cart_menu);



        //--------------checking global variable is null to hide get cart as users comes   on this screen-------
          if(Global.product_qty_Unique==0){
            rel_item_txt.setVisibility(View.GONE);
            Menu.rel_takeout_checkout_cart_menu.setVisibility(View.GONE);
        }

          //--------------checking global variable has value and show get cart to users when he comes   on this screen-------
        else {
            Menu.rel_takeout_checkout_cart_menu.setVisibility(View.VISIBLE);
            rel_item_txt.setVisibility(View.VISIBLE);
            rel_item_txt.setText("Checkout(" + Global.product_qty_Unique + ")items");
            price_getCartScreen_tv.setText(Global.product_price_Unique+"");
              Log.e("hhhhhh",Global.product_price_Unique+"vv");
        }
*/
    }

    private void work() {

        //------------intent get of title name-----------------------------
        title_menu_screen.setText(getIntent().getStringExtra("menu_name"));



        //------back arrow onClick Listener----------------------------------
        rel_back_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //----------cross img onClick Listener-------------------------------
        rel_layout_cross_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_search.setText("");


                //--------setting visisbilty gone on click------------------------
                if (cross_status==false)
                {
                    cross_status=true;
                    rel_layout_cross_menu.setVisibility(View.GONE);

                }
                else if (cross_status==true)
                {
                    cross_status=false;
                   rel_layout_cross_menu.setVisibility(View.GONE);
                }

            }
        });



         //---------------edit_SearchBar text change listner----------------------------------
         edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                //-------setting visibility of cross button--------------------
                if (edt_search.getText().toString()!=null)
                {
                    rel_layout_cross_menu.setVisibility(View.VISIBLE);
                }

                if (edt_search.getText().length()==0)
                   {
                    rel_layout_cross_menu.setVisibility(View.GONE);

                   }



                //--getting text from search text bar----------------------
                edit_Search_Text =  edt_search.getText().toString();

                //--------comparing entered data with list of data showen already(CommonUtilsImages...class for filter)----------------------------------------------------------------
           //  gridview.setAdapter(new Menu_Adapter(Menu.this,CommonUtilsImages.filter(edit_Search_Text,Global.getMenu,"product_name"),restaurant_id,menu_id,rel_item_txt,price_getCartScreen_tv));


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





    }

    @Override
    public void onResume(){
        super.onResume();

/*

        //--------------checking global variable is null to hide get cart as users comes   on this screen-------
        if(Global.product_qty_Unique==0){
            rel_item_txt.setVisibility(View.GONE);
            Menu.rel_takeout_checkout_cart_menu.setVisibility(View.GONE);

        }

        //--------------checking global variable has value and show get cart to users when he comes   on this screen-------
        else {
            Menu.rel_takeout_checkout_cart_menu.setVisibility(View.VISIBLE);
            rel_item_txt.setVisibility(View.VISIBLE);
            rel_item_txt.setText("Checkout(" + Global.product_qty_Unique + ")items");
            price_getCartScreen_tv.setText(Global.product_price_Unique+"");
            Log.e("hhhhhh",Global.product_price_Unique+"vv");

        }

*/


    }

}
