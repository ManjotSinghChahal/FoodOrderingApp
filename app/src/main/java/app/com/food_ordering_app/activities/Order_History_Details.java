package app.com.food_ordering_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import app.com.food_ordering_app.R;

public class Order_History_Details extends AppCompatActivity {

    RelativeLayout rel_img_back_hist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_details);
        init();
        work();

    }

    public void init() {
        rel_img_back_hist = (RelativeLayout) findViewById(R.id.rel_img_back_hist);
    }

    public void work() {
        rel_img_back_hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Order_History_Details.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                // finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //  startActivity(new Intent(TakeOut_Menu.this, MainActivity.class));
        Intent intent = new Intent(Order_History_Details.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
