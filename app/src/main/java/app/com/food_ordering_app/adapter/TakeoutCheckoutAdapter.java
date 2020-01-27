package app.com.food_ordering_app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.activities.TakeOut_CheckOut;

/**
 * Created by admin on 11/1/2017.
 */

public class TakeoutCheckoutAdapter extends BaseAdapter {
    Context con;
    ArrayList<HashMap<String,String>> arrayList_productDetails_getCart;
    LayoutInflater layoutInflater;

    public TakeoutCheckoutAdapter(Context con, ArrayList<HashMap<String, String>> arrayList_productDetails_getCart) {
        this.con=con;
        this.arrayList_productDetails_getCart=arrayList_productDetails_getCart;
        layoutInflater=LayoutInflater.from(con);

    }

    @Override
    public int getCount() {
        return arrayList_productDetails_getCart.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder
    {
       TextView product_name_takeoutCheckoutAdp;
       TextView product_price_takeoutCheckoutAdpt;
       TextView selected_qty;

    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;

        if (view==null)
        {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.takeout_checkout_adapter,null);


            //-------------------ids---------------------------------------------------------------------------
            viewHolder.product_name_takeoutCheckoutAdp = (TextView)view.findViewById(R.id.product_name_takeoutCheckoutAdp);
            viewHolder.product_price_takeoutCheckoutAdpt = (TextView)view.findViewById(R.id.product_price_takeoutCheckoutAdpt);
            viewHolder.selected_qty = (TextView)view.findViewById(R.id.txt1);



            view.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder) view.getTag();
        }



        //------------------setting item and its qty and price--------------------------------------------
        viewHolder.selected_qty.setText(arrayList_productDetails_getCart.get(i).get("quantity")+"X");
        viewHolder.product_name_takeoutCheckoutAdp.setText(arrayList_productDetails_getCart.get(i).get("product_name"));
        viewHolder.product_price_takeoutCheckoutAdpt.setText(arrayList_productDetails_getCart.get(i).get("product_price"));








        return view;
    }
}
