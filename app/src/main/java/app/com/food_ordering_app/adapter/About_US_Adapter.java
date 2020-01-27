package app.com.food_ordering_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import app.com.food_ordering_app.R;

/**
 * Created by admin on 7/26/2017.
 */

public class About_US_Adapter extends BaseAdapter {

    Context context;

    public About_US_Adapter(Context context) {
        this.context=context;
    }

    @Override
    public int getCount() {
        return 4;
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
        view=inflater.inflate(R.layout.about_us_adapter_view,null);

        return view;
    }
}
