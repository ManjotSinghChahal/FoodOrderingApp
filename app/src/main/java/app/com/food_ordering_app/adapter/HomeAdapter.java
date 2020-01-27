package app.com.food_ordering_app.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.Web_Service.EmptyCard_Api;
import app.com.food_ordering_app.activities.TakeOut_Menu;
import app.com.food_ordering_app.global.Global;


/**
 * Created by chinnu on 7/21/2017.
 */

public class HomeAdapter extends BaseAdapter {
    Context con;
    LayoutInflater inflater;
    ImageView image_home,overlap_image_closed;
    TextView restaurant_name,restaurant_time,restaurant_address,restaurant_del_type;
    LinearLayout linr_restaurant;
    ArrayList<HashMap<String,String>> restaurantlist_both =new ArrayList<HashMap<String,String>>();
    public static String status="";
    SharedPreferences sharedPreferencesHomeAdapt;
    public static String rest_status="";
    public HomeAdapter(Context con, ArrayList<HashMap<String,String>> restaurantlist){
        this.con=con;
        inflater=LayoutInflater.from(con);
        this.restaurantlist_both=restaurantlist;
    }
    @Override
    public int getCount() {
        return restaurantlist_both.size();
        //----------------change size also------
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
    public View getView(final int i, final View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater=(LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.home_listview,null);

          ids(view);
          onClick(i);


        return view;
    }

    private void ids(View view) {


        //----------------------------ids-----------------------------------------------------------
         linr_restaurant=(LinearLayout)view.findViewById(R.id.linr_restaurant);
         image_home=(ImageView)view.findViewById(R.id.image_home);
         overlap_image_closed=(ImageView)view.findViewById(R.id.overlap_image_closed);
         restaurant_name=(TextView)view.findViewById(R.id.restaurant_name);
         restaurant_time=(TextView)view.findViewById(R.id.restaurant_time);
         restaurant_address=(TextView)view.findViewById(R.id.restaurant_address);
         restaurant_del_type=(TextView)view.findViewById(R.id.restaurant_del_type);



    }

    private void onClick(final int i) {

        //------------setting list is not empty to avoid null pointer crush----------------
        if (restaurantlist_both.size()!=0) {


            //------status to show only opened restaurants after hitting search restaurant api-----------------------------
            if (status.equalsIgnoreCase("23"))

            {

                //-----------------------to get only opened restaurant details and set in textview----------------------
                restaurant_name.setText(restaurantlist_both.get(i).get("restaurant_name"));
                restaurant_address.setText(restaurantlist_both.get(i).get("restaurant_address"));
                restaurant_del_type.setText("Type");

                restaurant_time.setText("Close at " + restaurantlist_both.get(i).get("closed_time"));
                Picasso.with(con).load(restaurantlist_both.get(i).get("restaurant_image")).resize(600, 150).into(image_home);

                image_home.setScaleType(ImageView.ScaleType.FIT_XY);


            }

            //---------------shows opened and closed restaurants on hitting restaurant list api---------------------
            else {

                //-----------------------------------------------------------------------------------------
                if (restaurantlist_both.get(i).get("status_get").equalsIgnoreCase("2")) {
                    overlap_image_closed.setVisibility(View.VISIBLE);
                    restaurant_time.setText("Open at " + restaurantlist_both.get(i).get("open_time"));
                    Picasso.with(con).load(restaurantlist_both.get(i).get("restaurant_image")).resize(600, 150).into(image_home);
                    image_home.setScaleType(ImageView.ScaleType.FIT_XY);

                } else {
                    restaurant_time.setText("Close at " + restaurantlist_both.get(i).get("closed_time"));
                    Picasso.with(con).load(restaurantlist_both.get(i).get("restaurant_image")).resize(600, 150).into(image_home);
                    image_home.setScaleType(ImageView.ScaleType.FIT_XY);


                }


            }

            //-----------------------to get restaurant details and set in textview----------------------
            restaurant_name.setText(restaurantlist_both.get(i).get("restaurant_name"));
            restaurant_address.setText(restaurantlist_both.get(i).get("restaurant_address"));
            restaurant_del_type.setText("Type");

            String restaurant_distance = restaurantlist_both.get(i).get("restaurant_distance");
            String restaurant_opentime = restaurantlist_both.get(i).get("restaurant_opentime");


        }





        //--------------restaurant OnClick Listener---------------------------------------------
        linr_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                //------------to check whether list is empty or not-----------------------------------
                if (restaurantlist_both.size()!=0)
                {

                    //-----------restaurant details(Current restaurant selected)---------------------------------------------------
                    final String restaurant_name = restaurantlist_both.get(i).get("restaurant_name");
                    final String restaurant_address = restaurantlist_both.get(i).get("restaurant_address");
                    final String restaurant_id = restaurantlist_both.get(i).get("restaurant_id");
                    rest_status = restaurantlist_both.get(i).get("status_get");

                    //-----------getting user_id and restaurant_id from add_cart to empty cart-------------------------------
                    sharedPreferencesHomeAdapt = con.getSharedPreferences("MyCart",Context.MODE_PRIVATE);
                    final String user_id_cart_Add = sharedPreferencesHomeAdapt.getString("cart_value_user_id","");
                    final String restaurant_id_cart_Add = sharedPreferencesHomeAdapt.getString("cart_value_restaurant_id","");



                    //----------getting id of current entered restaurant----------------------------------
                    String current_selected_restaurant_id = restaurant_id;



                    //--------checking cart restaurant id either empty or not--------------------------
                    if (TextUtils.isEmpty(restaurant_id_cart_Add)) {

                        //---intent to carry value to takeout menu screen------------------------------------------
                        Intent intent = new Intent(con, TakeOut_Menu.class);
                        intent.putExtra("restaurant_name", restaurantlist_both.get(i).get("restaurant_name"));
                        intent.putExtra("restaurant_address", restaurantlist_both.get(i).get("restaurant_address"));
                        intent.putExtra("restaurant_id", restaurantlist_both.get(i).get("restaurant_id"));
                        con.startActivity(intent);

                        //---------clearing global variables-----------
                        Global.product_qty_Unique=0;
                        Global.product_price_Unique=0;



                    }
                    else
                    {

                        //-------------checking add to cart id with current entered(if matched moves next-------------
                        if (current_selected_restaurant_id.equalsIgnoreCase(restaurant_id_cart_Add)) {



                            //---intent to carry value to takeout menu screen------------------------------------------
                            Intent intent = new Intent(con, TakeOut_Menu.class);
                            intent.putExtra("restaurant_name", restaurantlist_both.get(i).get("restaurant_name"));
                            intent.putExtra("restaurant_address", restaurantlist_both.get(i).get("restaurant_address"));
                            intent.putExtra("restaurant_id", restaurantlist_both.get(i).get("restaurant_id"));
                            con.startActivity(intent);


                        }
                        //---------------if not dialogue box opens for user opinion to remove cart-------------
                        else {

                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(con);
                            alertDialogBuilder.setTitle("Are you sure want to  remove cart");

                            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    //----------empty cart api hit-------------------------------------------------------------------------
                                    EmptyCard_Api emptyCard_Api = new EmptyCard_Api(restaurant_name,restaurant_address,restaurant_id);
                                    emptyCard_Api.emptyCard(con, user_id_cart_Add, restaurant_id_cart_Add,"Cmpr_HomeAdpter");


                                    //-------clearing global variable--------
                                    Global.product_qty_Unique=0;
                                    Global.product_price_Unique=0;





                                }
                            });

                            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });

                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();


                        }


                    }


                }

            }
        });




    }

}
