package app.com.food_ordering_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.adapter.BasketAdapter;

public class Basket extends AppCompatActivity {

    Button btn_submit_basket;
    ListView list_view;
    BaseAdapter adapter;
    RelativeLayout rel_img_back_basket;
    RelativeLayout rel_delivery;
    RelativeLayout rel_pickup;
    ImageView img_pickup;
    ImageView img_delivry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        id();
        setAdapter();
        onclick();
        work();
    }

    public void id(){
        adapter=new BasketAdapter(this);
        rel_img_back_basket=(RelativeLayout)findViewById(R.id.rel_img_back_basket);
        rel_delivery=(RelativeLayout)findViewById(R.id.rel_delivery);
        rel_pickup=(RelativeLayout)findViewById(R.id.rel_pickup);
        btn_submit_basket=(Button)findViewById(R.id.btn_submit_basket);
        list_view=(ListView)findViewById(R.id.list_view);
        img_pickup=(ImageView) findViewById(R.id.img_pickup);
        img_delivry=(ImageView) findViewById(R.id.img_delivry);
    }
    public void setAdapter(){
        list_view.setAdapter(adapter);
    }

    public void onclick()
    {

     //-------------------------rel_delivery--------------------------------------
        rel_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_pickup.setBackgroundResource(R.drawable.unclick_circal);
                img_delivry.setBackgroundResource(R.drawable.click_circal);

            }
        });
     //-------------------------------rel_pickup----------------------------------
        rel_pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_pickup.setBackgroundResource(R.drawable.click_circal);
                img_delivry.setBackgroundResource(R.drawable.unclick_circal);
            }
        });

     //--------------------------submit_btn----------------------------------------
        btn_submit_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Basket.this,TakeOut_CheckOut.class));
            }
        });

     //--------------------------back_Button---------------------------------------
        rel_img_back_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

     //-----------------------------------------------------------------------------
    }


    public void work()
    {
        img_pickup.setBackgroundResource(R.drawable.unclick_circal);
        img_delivry.setBackgroundResource(R.drawable.click_circal);
    }

}
