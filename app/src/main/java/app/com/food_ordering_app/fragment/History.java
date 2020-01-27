package app.com.food_ordering_app.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.Web_Service.Order_History_Api;
import app.com.food_ordering_app.activities.MainActivity;
import app.com.food_ordering_app.activities.Order_History_Details;
import app.com.food_ordering_app.adapter.History_Adapter;
import app.com.food_ordering_app.global.Global;

/**
 * A simple {@link Fragment} subclass.
 */
public class History extends Fragment {

    ListView list_history;
    SharedPreferences sharedPreferences_OrderHistory;
    SwipeRefreshLayout swipe_home_page;
    int page=1;
    public History() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_history, container, false);

        //---------setting title------------------------
        MainActivity.txt_header.setText("Order History");

        //-------------------ids-------------------------------------
        list_history=(ListView)view.findViewById(R.id.list_history);
        swipe_home_page=(SwipeRefreshLayout)view.findViewById(R.id.swipe_home_page);


        //-----------shared preferences to get user_id---------------------------------------------
        sharedPreferences_OrderHistory =getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        final String user_id =  sharedPreferences_OrderHistory.getString("user_id","");



        //---------------Order history api----------------
        Global.getOrderHistory_arrayList().clear();
        Order_History_Api order_history_api = new Order_History_Api();
        order_history_api.OrderHistory(getActivity(),user_id,page,list_history);


        //------------------swipe refresh listener----------------------------------
        swipe_home_page.setColorScheme(android.R.color.holo_blue_dark,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_green_light);

        swipe_home_page.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipe_home_page.setRefreshing(true);

                ( new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_home_page.setRefreshing(false);
                        page = page+1;

                        //---------------get all orders api hit--------------------------------
                        Order_History_Api order_history_api = new Order_History_Api();
                        order_history_api.OrderHistory(getActivity(),user_id,page,list_history);

                    }
                }, 3000);


            }
        });




        return view;
    }

}
