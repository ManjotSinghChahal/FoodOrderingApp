package app.com.food_ordering_app.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.Web_Service.PlaceOrder_Api;
import app.com.food_ordering_app.adapter.TakeoutCheckoutAdapter;
import app.com.food_ordering_app.global.Global;

import static app.com.food_ordering_app.activities.Delivery_Location.set_status;
import static app.com.food_ordering_app.activities.Delivery_Location.status;

public class TakeOut_CheckOut extends AppCompatActivity {

    RelativeLayout layout_delivery_instructions;
    Button btn_place_order;
    RelativeLayout rel_img_back_checkout;
    int tipTen,tipfifteen,tipTwenty;
    TextView title_name,txt_continue;
    public static TextView del_address;
    TextView sub_amnt,tax_amnt,fee_amnt,total_amnt;
    TextView ten_percent,fiteen_percent,twenty_percent;
    String status_ten="unselect";
    String status_fifteen="unselect";
    String status_twenty="unselect";
    ListView listView_TakeoutCheckout;
    TakeoutCheckoutAdapter takeoutCheckoutAdapter;
    ArrayList<HashMap<String,String>> arrayList_productDetails_getCart;
    String list_address,Getrestaurant_SubtotlP,restaurant_TaxCharges,restaurant_DeliveryFee,restaurant_TotalPrice;
    String restaurant_TipTen,restaurant_TipFifteen,restaurant_TipTwenty,restaurant_id;
    String restaurant_name;
    Global global;
    TextView  choose_del_tv;
    ImageView next_arrow_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_out_check_out);

        btn_place_order=(Button)findViewById(R.id.btn_place_order);






        init();

        //---------------------Change_to Take_out/Delivery-----------------------------------------

        if (TakeOut_Menu.txt_change_to.equals("Take Out Menu"))
        {
            layout_delivery_instructions.setVisibility(View.GONE);
            title_name.setText("TakeOut CheckOut");

        }

        else if (TakeOut_Menu.txt_change_to.equals("Delivery Menu"))
        {
            layout_delivery_instructions.setVisibility(View.VISIBLE);
            title_name.setText("Delivery CheckOut");

        }


        work();
    }

    public void init()
    {
        arrayList_productDetails_getCart = new ArrayList<HashMap<String, String>>();
        rel_img_back_checkout=(RelativeLayout)findViewById(R.id.rel_img_back_checkout);
        layout_delivery_instructions=(RelativeLayout) findViewById(R.id.delivery_instructions);
        title_name=(TextView) findViewById(R.id.tool_profile);
        txt_continue=(TextView) findViewById(R.id.txt_continue);
        sub_amnt=(TextView) findViewById(R.id.sub_amnt);
        tax_amnt=(TextView) findViewById(R.id.tax_amnt);
        fee_amnt=(TextView) findViewById(R.id.fee_amnt);
        total_amnt=(TextView) findViewById(R.id.total_amnt);
        ten_percent=(TextView) findViewById(R.id.ten);
        fiteen_percent=(TextView) findViewById(R.id.fiteen);
        twenty_percent=(TextView) findViewById(R.id.twenty);
        del_address=(TextView) findViewById(R.id.txt_yorkSt);
        choose_del_tv=(TextView) findViewById(R.id.choose_del_tv);
        next_arrow_img=(ImageView) findViewById(R.id.next_arrow_img);

        listView_TakeoutCheckout = (ListView)findViewById(R.id.listView_TakeoutCheckout);



        //------initializing global variable and adding to current class array list(Get_Cart array)------------
        global=(Global)this.getApplicationContext();
        arrayList_productDetails_getCart=Global.getProduct_ArrayList_TakeoutCheckout();


        //---------------------takeout checkout adapter---------------------------------------------------------
        takeoutCheckoutAdapter = new TakeoutCheckoutAdapter(TakeOut_CheckOut.this,arrayList_productDetails_getCart);




    }

    public void work()
    {

        //-------------getting values from intent and setting text to fields-------------------------------------------
         list_address = getIntent().getStringExtra("list_address");
         Getrestaurant_SubtotlP = getIntent().getStringExtra("restaurant_SubtotalPrice");
         restaurant_TaxCharges = getIntent().getStringExtra("restaurant_TaxCharges");
         restaurant_DeliveryFee = getIntent().getStringExtra("restaurant_DeliveryFee");
         restaurant_TotalPrice= getIntent().getStringExtra("restaurant_TotalPrice");
         restaurant_TipTen= getIntent().getStringExtra("restaurant_TipTen");
         restaurant_TipFifteen= getIntent().getStringExtra("restaurant_TipFifteen");
         restaurant_TipTwenty= getIntent().getStringExtra("restaurant_TipTwenty");
         restaurant_id= getIntent().getStringExtra("restaurant_id");
         restaurant_name= getIntent().getStringExtra("restaurant_name");





       //--------------setting text to charges and total-------------------------------------------
        sub_amnt.setText("$"+Getrestaurant_SubtotlP);
        tax_amnt.setText("$"+restaurant_TaxCharges);
        fee_amnt.setText("$"+restaurant_DeliveryFee);
        total_amnt.setText("$"+restaurant_TotalPrice);



        //---------------------checkout_back_btn onClick Listener---------------------------------------
          rel_img_back_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                del_address.setText("");
                list_address=null;
                finish();
            }
        });

        //--------------------next_arrow onClick Listener-------------------------------------------------
          layout_delivery_instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TakeOut_CheckOut.this,Delivery_Location.class);
                startActivity(intent);
            }
        });


        //--------------------ten_percent onClick Listener--------------------------------------------
        ten_percent.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                //--------------------conditions to select/unselect view on click---------------
                status_fifteen="unselect";
                status_twenty="unselect";



                if (status_ten.equalsIgnoreCase("unselect"))
                {
                    total_amnt.setText("$"+restaurant_TipTen);
                    ten_percent.setBackgroundResource(R.color.colorLightBrown1);
                    fiteen_percent.setBackgroundResource(R.drawable.discount_bg);
                    twenty_percent.setBackgroundResource(R.drawable.discount_bg);
                    status_ten="select";
                    tipTen=10;
                    tipfifteen=0;
                    tipTwenty=0;

                }
                else if (status_ten.equalsIgnoreCase("select"))
                {
                    total_amnt.setText("$"+restaurant_TotalPrice);
                    ten_percent.setBackgroundResource(R.drawable.discount_bg);
                    fiteen_percent.setBackgroundResource(R.drawable.discount_bg);
                    twenty_percent.setBackgroundResource(R.drawable.discount_bg);
                    status_ten="unselect";
                }




            }
        });

        //--------------------fifteen_percent onClick Listener--------------------------------------------
        fiteen_percent.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                total_amnt.setText("$"+restaurant_TipFifteen);

                //--------------------conditions to select/unselect view on click---------------
                status_ten="unselect";
                status_twenty="unselect";

                if (status_fifteen.equalsIgnoreCase("unselect"))
                {
                    total_amnt.setText("$"+restaurant_TipFifteen);
                    ten_percent.setBackgroundResource(R.drawable.discount_bg);
                    fiteen_percent.setBackgroundResource(R.color.colorLightBrown1);
                    twenty_percent.setBackgroundResource(R.drawable.discount_bg);
                    status_fifteen="select";
                    tipTen=0;
                    tipfifteen=15;
                    tipTwenty=0;

                }
                else if (status_fifteen.equalsIgnoreCase("select"))
                {
                    total_amnt.setText("$"+restaurant_TotalPrice);
                    ten_percent.setBackgroundResource(R.drawable.discount_bg);
                    fiteen_percent.setBackgroundResource(R.drawable.discount_bg);
                    twenty_percent.setBackgroundResource(R.drawable.discount_bg);
                    status_fifteen="unselect";
                }





            }
        });

        //--------------------twenty_percent onClick Listener--------------------------------------------
        twenty_percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //--------------------conditions to select/unselect view on click---------------
                total_amnt.setText("$"+restaurant_TipTwenty);
                status_ten="unselect";
                status_fifteen="unselect";



                if (status_twenty.equalsIgnoreCase("unselect"))
                {
                    total_amnt.setText("$"+restaurant_TipTwenty);
                    ten_percent.setBackgroundResource(R.drawable.discount_bg);
                    fiteen_percent.setBackgroundResource(R.drawable.discount_bg);
                    twenty_percent.setBackgroundResource(R.color.colorLightBrown1);
                    status_twenty="select";
                    tipTen=0;
                    tipfifteen=0;
                    tipTwenty=20;

                }
                else if (status_twenty.equalsIgnoreCase("select"))
                {
                    total_amnt.setText("$"+restaurant_TotalPrice);
                    ten_percent.setBackgroundResource(R.drawable.discount_bg);
                    fiteen_percent.setBackgroundResource(R.drawable.discount_bg);
                    twenty_percent.setBackgroundResource(R.drawable.discount_bg);
                    status_twenty="unselect";
                }











            }
        });


        //----------------------------------btn_order_placed-----------------------------------------
        btn_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //-----------shared preferences to get user_id---------------------------------------------
                SharedPreferences   sharedPreferences_Menu_Adpt =getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                String user_id =  sharedPreferences_Menu_Adpt.getString("user_id","");





                String delivery_address;
                String total_amount  = restaurant_TotalPrice;
                String tip =  String.valueOf(tipTen+tipfifteen+tipTwenty);



                String type;
                if (TakeOut_Menu.txt_takeOut.getText().toString().equalsIgnoreCase("Take Out Menu"))
                {
                    type = "P";
                    delivery_address="";


                    PlaceOrder_Api placeOrder_api = new PlaceOrder_Api();
                    placeOrder_api.placeOrder(getApplication(),user_id,restaurant_id,type,delivery_address,total_amount,tip);



                }
                else
                {

                    if (del_address.getText().toString().isEmpty())
                    {

                        Toast.makeText(global, "Select address", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        type = "D";
                        delivery_address =del_address.getText().toString();


                        PlaceOrder_Api placeOrder_api = new PlaceOrder_Api();
                        placeOrder_api.placeOrder(getApplication(),user_id,restaurant_id,type,delivery_address,total_amount,tip);



                    }


                }







            }
        });

        //-----------------Continue onClick Listener----------------------------
        txt_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                del_address.setText("");
                list_address=null;
                Intent intent=new Intent(TakeOut_CheckOut.this,TakeOut_Menu.class);
                intent.putExtra("restaurant_id",restaurant_id);
                intent.putExtra("restaurant_name",restaurant_name);
                intent.putExtra("restaurant_address",TakeOut_Menu.restaurant_address);
                startActivity(intent);

            }
        });



        //-------------------setting adapter--------------------------------------------------------

        listView_TakeoutCheckout.setAdapter((ListAdapter) takeoutCheckoutAdapter);


    }
    @Override
    public void onResume(){
        super.onResume();





        //-----to set location address-----------------------
        if (Delivery_Location.loc_access!="" && set_status=="1")
        {

            del_address.setVisibility(View.VISIBLE);
            choose_del_tv.setVisibility(View.GONE);
            del_address.setText(Delivery_Location.loc_access);


        }
            else if (!del_address.getText().toString().equalsIgnoreCase(""))
           {
               del_address.setVisibility(View.VISIBLE);
               choose_del_tv.setVisibility(View.GONE);
               del_address.setText(Delivery_Location.loc_access);

           }

           else if (del_address.getText().toString().isEmpty())
           {
            del_address.setVisibility(View.GONE);
            choose_del_tv.setVisibility(View.VISIBLE);

           }




        if (list_address!=null)
        {
            del_address.setVisibility(View.VISIBLE);
            choose_del_tv.setVisibility(View.GONE);
            del_address.setText(list_address);

        }
                }

    @Override
    public void onBackPressed() {

        //-----------Clearing values that on resume doesn't take previous values-----------------
        Delivery_Location.loc_access="";
        Delivery_Location.txt_home_address_get="";
        Delivery_Location.txt_work_address_get="";
        list_address=null;

        Intent intent = new Intent(TakeOut_CheckOut.this,TakeOut_Menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("restaurant_id",restaurant_id);
        intent.putExtra("restaurant_name",restaurant_name);
        intent.putExtra("restaurant_address",TakeOut_Menu.restaurant_address);
        startActivity(intent);

      }
    }


