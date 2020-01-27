package app.com.food_ordering_app.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.com.food_ordering_app.R;


/**
 * Created by admin on 9/12/2017.
 */

public class Product_Details extends Activity {
    RelativeLayout rel_layout_cart;
    RelativeLayout rel_layout_back_btn,rel_product_details_cart;
    RelativeLayout rel_minus_img,rel_plus_img;
    TextView txt_qty_number;
    int int_value__qty=0;
    String string_value__qty,value_of_qty;
    SharedPreferences sharedPreferences_cart;
    public static String status="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        init();
        work();

        //-------to set value of qty ordered-------------------------
        Intent intent=getIntent();
        value_of_qty= intent.getStringExtra("order_qty");
        txt_qty_number.setText(value_of_qty);




    }


    private void init() {
        rel_layout_back_btn=(RelativeLayout)findViewById(R.id.rel_img_back_hist);
        rel_layout_cart=(RelativeLayout) findViewById(R.id.rel_layout_cart);
        rel_minus_img=(RelativeLayout) findViewById(R.id.product_minus_icon);
        rel_plus_img=(RelativeLayout) findViewById(R.id.product_add_icon);
        txt_qty_number=(TextView) findViewById(R.id.product_numbering);
        rel_product_details_cart=(RelativeLayout)findViewById(R.id.cart_rel_details);

    }

    private void work() {

        //------add to cart onClick Listener-----------------------------------
        rel_layout_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status="11";
               // sharedPreferences_cart.
                SharedPreferences.Editor editor=sharedPreferences_cart.edit();
                editor.putString("cart_value",status);
                editor.commit();
                startActivity(new Intent(Product_Details.this, TakeOut_CheckOut.class));
            }
        });

        //-----------back button onClick Listener-----------------------------
        rel_layout_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //-----------plus qty onClick Listener--------------------
        rel_plus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                string_value__qty = txt_qty_number.getText().toString();
                string_value__qty.substring(1);
                int_value__qty = Integer.parseInt(string_value__qty);
                if (int_value__qty > 1) {
                    int_value__qty--;
                    txt_qty_number.setText(String.valueOf(int_value__qty));

                }
            }
        });


    }
    @Override
    public void onResume(){
        super.onResume();
        //-----------shared preferences of cart----------------------------
        sharedPreferences_cart = getSharedPreferences("PREFERENCES_CART", Context.MODE_PRIVATE);
        //---------------to show cart----------------------------------
        String res= sharedPreferences_cart.getString("cart_value","value");
        if (res.equalsIgnoreCase("11"))
        {
            rel_product_details_cart.setVisibility(View.VISIBLE);
        }
        else {
            rel_product_details_cart.setVisibility(View.GONE);
        }
    }


}

