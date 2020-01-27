package app.com.food_ordering_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.activities.CreditCard_Details;
import app.com.food_ordering_app.activities.Order_History_Details;
import app.com.food_ordering_app.fragment.Credit_Card_Details;

/**
 * Created by admin on 7/26/2017.
 */

public class History_Adapter extends BaseAdapter {

    Context context;
    ArrayList<HashMap<String,String>> orderHistory_arrayList = new ArrayList<>();


    public History_Adapter(Context context, ArrayList<HashMap<String, String>> orderHistory_arrayList) {
        this.context=context;
        this.orderHistory_arrayList=orderHistory_arrayList;
    }

    @Override
    public int getCount() {
        return orderHistory_arrayList.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.history_adapter_view,null);

        //------------ids----------------------------------------------------------
        RelativeLayout rel_order=(RelativeLayout)view.findViewById(R.id.rel_order);
        TextView restaurant_name_OrderHis = (TextView)view.findViewById(R.id.restaurant_name_OrderHis);
        TextView product_name_OrderHis = (TextView)view.findViewById(R.id.product_name_OrderHis);
        TextView date_OrderHis = (TextView)view.findViewById(R.id.date_OrderHis);
        TextView time_OrderHis = (TextView)view.findViewById(R.id.time_OrderHis);
        TextView amount_OrderHis = (TextView)view.findViewById(R.id.amount_OrderHis);
        ImageView img_OrderHis = (ImageView)view.findViewById(R.id.img_OrderHis);


        //----------setting data to fields-------------------------------------
        restaurant_name_OrderHis.setText(orderHistory_arrayList.get(i).get("rest_name")+"");
        product_name_OrderHis.setText(orderHistory_arrayList.get(i).get("product_name"));
        date_OrderHis.setText(orderHistory_arrayList.get(i).get("date"));
       // time_OrderHis.setText(orderHistory_arrayList.get(i).get("rest_name"));
        amount_OrderHis.setText(orderHistory_arrayList.get(i).get("total_amount"));



        if (orderHistory_arrayList.get(i).get("order_status").equalsIgnoreCase("A"))
        {
            img_OrderHis.setImageResource(R.drawable.green_tick);
        }
        else if (orderHistory_arrayList.get(i).get("order_status").equalsIgnoreCase("D"))
        {
            img_OrderHis.setImageResource(R.drawable.red_cross);
        }
        else if (orderHistory_arrayList.get(i).get("order_status").equalsIgnoreCase("I"))
        {
            img_OrderHis.setImageResource(R.drawable.icon_1);
        }



        rel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //  context.startActivity(new Intent(context, CreditCard_Details.class));
            }
        });



        return view;
    }
}
