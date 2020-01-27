package app.com.food_ordering_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import app.com.food_ordering_app.R;

public class CreditCard_Details extends AppCompatActivity {

    Button button_submit_card;
    Button button_cancel_card;
    RelativeLayout rel_img_back_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card_details);
        init();
        work();
    }

    public void init()
    {
        rel_img_back_card=(RelativeLayout)findViewById(R.id.rel_img_back_card);
        button_submit_card=(Button)findViewById(R.id.button_submit_card);
        button_cancel_card=(Button)findViewById(R.id.button_cancel_card);
    }
    public  void work()
    {
        //-----------------------------btn_submit-----------------------------------
        button_submit_card.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(CreditCard_Details.this,Delivery_Location.class));
        }
    });


        //-------------------------btn_cancel-----------------------

        button_cancel_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //-------------------------------btn_back--------------------------------------
        rel_img_back_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
