package app.com.food_ordering_app.adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.Web_Service.AddToCart_Api;
import app.com.food_ordering_app.Web_Service.GetCart_Api;
import app.com.food_ordering_app.Web_Service.GetSpecificProductDetails_Api;
import app.com.food_ordering_app.Web_Service.RestaurantListing_Api;
import app.com.food_ordering_app.activities.TakeOut_Menu;
import app.com.food_ordering_app.global.Global;
import app.com.food_ordering_app.interfac.MyInterface;

/**
 * Created by admin on 7/29/2017.
 */

public class MostPopular_Adapter extends BaseAdapter implements MyInterface {

    Context context;
    ArrayList<HashMap<String, String>> arr_poplular;
    String userId_get, restaurant_id_get;
    public static ProgressDialog pd;
    int val_pos;
    public static TextView txt_qty_number;
    TextView txt_burger_txtView;
    int int_value__qty = 0;
    String string_value__qty;
    int present_value_int = 0;
    public static String status = "";
    String menu_id;
    String product_id;
    public static String qty_count;
    Global global;
    RelativeLayout rel_layout_back_addons;
    ListView listView_addons;

    public static Dialog dialog = null;
    public static  BottomSheetDialog  bottomSheetdialog=null;
    RelativeLayout rel_layout_cart;
    RelativeLayout rel_minus_img, rel_plus_img;
    TextView rel_item_txt_MoastPopular, price_getCartScreen_takeout_Menu;

    public MostPopular_Adapter(Context context, ArrayList<HashMap<String, String>> arr_poplular, String userId_get, String restaurant_id_get,TextView rel_item_txt_MoastPopular) {
        Log.e("inside"," popular adapter");
        this.context = context;
        this.arr_poplular = arr_poplular;
        this.userId_get = userId_get;
        this.restaurant_id_get = restaurant_id_get;
        this.rel_item_txt_MoastPopular = rel_item_txt_MoastPopular;

    }

    @Override
    public int getCount() {
        return arr_poplular.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.most_popular_adpter_view, null);

        //------creating global variable and clearing global list----------------
        global = (Global) context.getApplicationContext();


        //--------------to set most popular's place title and qty of item------------------------------
        rel_item_txt_MoastPopular.setText(arr_poplular.get(i).get("menu_name"));
        TakeOut_Menu.tv_item_total_mstPopular.setText(arr_poplular.size()+"");

        ids();

        //------------------ids------------------------------------------------------------
        TextView txt_product_name = (TextView) view.findViewById(R.id.txt_product_name);
        TextView txt_product_price = (TextView) view.findViewById(R.id.txt_product_price);
        TextView txt_product_detail = (TextView) view.findViewById(R.id.txt_product_detail);
        final RelativeLayout rel_product_single = (RelativeLayout) view.findViewById(R.id.rel_product);


        //------------------setting data to text view from array list--------------------
        txt_product_name.setText(arr_poplular.get(i).get("product_name").toString());
        txt_product_price.setText(arr_poplular.get(i).get("product_price").toString());
        txt_product_detail.setText(arr_poplular.get(i).get("product_desc").toString());




        //---------------to enable and diable click on open and close rest-----------------
        if (HomeAdapter.rest_status.equalsIgnoreCase("2")) {

        }
       else if ( HomeAdapter.rest_status.equalsIgnoreCase("1"))

        {

            //------------most popular item rel layout onClick Listener---------------------------------
            rel_product_single.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    dialog = null;
                    //------------progress dialog box code-------------
                    pd = new ProgressDialog(context);
                    pd.setMessage("Loading....");
                    //--------used for null token-----------------------------------
                    pd.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    pd.show();
                    val_pos = i;


                    product_id = arr_poplular.get(i).get("product_id").toString();
                    menu_id = arr_poplular.get(i).get("menu_id").toString();


                    //----------specific product details(For Get a Product of Specific menu item or product)--------
                    GetSpecificProductDetails_Api getSpecificProductDetails_Api = new GetSpecificProductDetails_Api(rel_product_single, rel_item_txt_MoastPopular);           //-----------listview,rel_layout,qty passed to access in next class--
                    getSpecificProductDetails_Api.getSpecificProductDetails(context, userId_get, restaurant_id_get, menu_id, product_id, MostPopular_Adapter.this);


                }
            });

        }



        return view;
    }

    private void ids() {

    }

    @Override
    public void onSuccess() {

        pd.dismiss();


        //--------checking addon list size---------------------------------------
            if (global.getAdd_on().size() != 0)

            {

                //------check dialog  null to avoid multiple times opeming of dialog box--------------
                if (dialog == null) {


                    //-----------custom dialog--------------------------------------------
                    dialog = new Dialog(context, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
                    // Dialog dialog = new Dialog(context,android.R.style.Theme_DeviceDefault_Light);
                    dialog.setContentView(R.layout.product_qty_view);
                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    dialog.setCancelable(false);
                    dialog.show();



                    //--------------ids of dialog box members-----------------------------------------
                    rel_layout_cart = (RelativeLayout) dialog.findViewById(R.id.rel_layout_cart);
                    rel_layout_back_addons = (RelativeLayout) dialog.findViewById(R.id.rel_layout_back_addons);
                    rel_minus_img = (RelativeLayout) dialog.findViewById(R.id.rel_img_minus_icon_dialog);
                    rel_plus_img = (RelativeLayout) dialog.findViewById(R.id.rel_img_add_icon_dialog);
                    txt_burger_txtView = (TextView) dialog.findViewById(R.id.txt_burger_txtView);
                    listView_addons = (ListView) dialog.findViewById(R.id.listView_addons);
                    txt_qty_number = (TextView) dialog.findViewById(R.id.txt_view_qty_count);


                    //-------------setting values to dialog box----------------------
                    txt_burger_txtView.setText(arr_poplular.get(val_pos).get("product_name"));


                    //----------to align dialog box at bottom----------------------------------------
                    Window window = dialog.getWindow();
                    WindowManager.LayoutParams wlp = window.getAttributes();
                    //  wlp.gravity = Gravity.BOTTOM;
                    //---here we set height and weight of layout------------------
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    window.setAttributes(wlp);


                    //-----------plus qty onClick Listener--------------------
                    rel_plus_img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            //---------item quantity------------------------------
                            string_value__qty = txt_qty_number.getText().toString();
                            int_value__qty = Integer.parseInt(string_value__qty);
                            int_value__qty++;
                            txt_qty_number.setText(String.valueOf(int_value__qty));

                        }
                    });


                    //-----------minus qty onClick Listener--------------------
                    rel_minus_img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            //---------item quantity------------------------------
                            string_value__qty = txt_qty_number.getText().toString();
                            string_value__qty.substring(1);
                            present_value_int = Integer.parseInt(string_value__qty);
                            if (present_value_int > 1) {
                                present_value_int--;
                                txt_qty_number.setText(String.valueOf(present_value_int));
                            }
                        }
                    });


                    //-------------------back button onClick Listener---------------------
                    rel_layout_back_addons.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                }


                //---Addons Adapter(if has addons then executes)------------------------------------------------------------------------
                AddonsAdapter addonsAdapter = new AddonsAdapter(context, global.getAdd_on(), userId_get, restaurant_id_get, menu_id, product_id, rel_layout_cart, rel_item_txt_MoastPopular, price_getCartScreen_takeout_Menu, arr_poplular.get(val_pos).get("product_price"), "MostPopular_Adapter");
                listView_addons.setAdapter(addonsAdapter);

            }

            //------------------------rel_layout_cart(No addons)----------------------------------
            else if (global.getAdd_on().size() == 0) {
                Log.e("hereisme3","hereisme");
                //------check dialog  null to avoid multiple times opeming of dialog box--------------
               // if (bottomSheetdialog == null) {

                    bottomSheetdialog = new BottomSheetDialog(context);
                    bottomSheetdialog.setContentView(R.layout.product_qty_view);
                    bottomSheetdialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

                    bottomSheetdialog.show();


                    //--------------ids of dialog box members-----------------------------------------
                    rel_layout_cart = (RelativeLayout) bottomSheetdialog.findViewById(R.id.rel_layout_cart);
                    rel_minus_img = (RelativeLayout) bottomSheetdialog.findViewById(R.id.rel_img_minus_icon_dialog);
                    rel_plus_img = (RelativeLayout) bottomSheetdialog.findViewById(R.id.rel_img_add_icon_dialog);
                    rel_layout_back_addons = (RelativeLayout) bottomSheetdialog.findViewById(R.id.rel_layout_back_addons);
                    txt_burger_txtView = (TextView) bottomSheetdialog.findViewById(R.id.txt_burger_txtView);
                    listView_addons = (ListView) bottomSheetdialog.findViewById(R.id.listView_addons);
                    txt_qty_number = (TextView) bottomSheetdialog.findViewById(R.id.txt_view_qty_count);





                    //-------------setting values to dialog box----------------------
                    txt_burger_txtView.setText(arr_poplular.get(val_pos).get("product_name"));


                    //----------to align dialog box at bottom----------------------------------------
                    Window windows = bottomSheetdialog.getWindow();
                    WindowManager.LayoutParams wlps = windows.getAttributes();
                    //  wlp.gravity = Gravity.BOTTOM;
                    //---here we set height and weight of layout------------------
                    windows.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    windows.setAttributes(wlps);


                //--------------------rel_layout_cart onClick Listener(Add to cart)---------------------
                rel_layout_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        bottomSheetdialog.dismiss();

                        //--------------getting qty of item selected by user---------------------------
                        qty_count = txt_qty_number.getText().toString();


                        //--------storing qty selected to global variable(setting qty on get cart)------------
                        int qty_int = Integer.parseInt(qty_count);
                        Global.product_qty_Unique = Global.product_qty_Unique + qty_int;


                        //-----------------checking qty null or not(setting visibility acc)---------------
                        if(Global.product_qty_Unique==0)
                        {
                            TakeOut_Menu.cart_rel_takeout_menu.setVisibility(View.GONE);
                        }
                        else
                        {
                            TakeOut_Menu.cart_rel_takeout_menu.setVisibility(View.VISIBLE);
                        }


                        TakeOut_Menu.rel_item_txt_takeout_Menu.setText("Checkout(" + Global.product_qty_Unique + ")items");


                        //----------add to cart api--------------------------------------------------
                        AddToCart_Api addToCart_api = new AddToCart_Api();
                        addToCart_api.addCart(context, userId_get, restaurant_id_get, menu_id, Global.product_id_Unique, qty_count, "");


                    }
                });
          //  }


            //-----------plus qty onClick Listener--------------------
            rel_plus_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //---------item quantity------------------------------
                    string_value__qty = txt_qty_number.getText().toString();
                    int_value__qty = Integer.parseInt(string_value__qty);
                    int_value__qty++;
                    txt_qty_number.setText(String.valueOf(int_value__qty));

                }
            });


            //-----------minus qty onClick Listener--------------------
            rel_minus_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //---------item quantity------------------------------
                    string_value__qty = txt_qty_number.getText().toString();
                    string_value__qty.substring(1);
                    present_value_int = Integer.parseInt(string_value__qty);
                    if (present_value_int > 1) {
                        present_value_int--;
                        txt_qty_number.setText(String.valueOf(present_value_int));
                    }
                }
            });


            //-------------------back button onClick Listener---------------------
                rel_layout_back_addons.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetdialog.dismiss();
                    }
                });

        }





    }

}
