package app.com.food_ordering_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import app.com.food_ordering_app.Web_Service.GetCart_Api;
import app.com.food_ordering_app.activities.TakeOut_CheckOut;

/**
 * Created by admin on 1/9/2018.
 */

public class DeliveryAdapter extends BaseAdapter {
    Context con;
    ArrayList<HashMap<String,String>> arrayList_delAddress;
    TextView address_listview;
    LayoutInflater layoutInflater;
    public DeliveryAdapter(Context context, ArrayList<HashMap<String, String>> arrayList_DelAddress) {
        this.con=context;
        layoutInflater=(LayoutInflater.from(con));
        this.arrayList_delAddress=arrayList_DelAddress;


    }

    @Override
    public int getCount() {
                return arrayList_delAddress.size();
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


        final ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.delivery_listview, null);
            viewHolder.address_listview = (TextView) view.findViewById(R.id.address_listview);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.address_listview.setText(arrayList_delAddress.get(i).get("address"));

        viewHolder.address_listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(con, TakeOut_CheckOut.class);
                intent.putExtra("list_address",arrayList_delAddress.get(i).get("address"));
                intent.putExtra("restaurant_id", GetCart_Api.restaurant_id);
                intent.putExtra("restaurant_name", GetCart_Api.restaurant_name);
                intent.putExtra("restaurant_DeliveryFee", GetCart_Api.restaurant_DeliveryFee);
                intent.putExtra("restaurant_TaxCharges", GetCart_Api.restaurant_TaxCharges);
                intent.putExtra("restaurant_SubtotalPrice", GetCart_Api.restaurant_SubtotalPrice);
                intent.putExtra("restaurant_TotalPrice", GetCart_Api.restaurant_TotalPrice);
                intent.putExtra("restaurant_TipTen", GetCart_Api.restaurant_TipTen);
                intent.putExtra("restaurant_TipFifteen", GetCart_Api.restaurant_TipFifteen);
                intent.putExtra("restaurant_TipTwenty", GetCart_Api.restaurant_TipTwenty);

              //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                con.startActivity(intent);


            }
        });

        return view;


    }
    class  ViewHolder
    {
        TextView address_listview;

    }
}
