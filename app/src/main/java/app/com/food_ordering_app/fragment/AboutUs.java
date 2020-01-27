package app.com.food_ordering_app.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.activities.MainActivity;
import app.com.food_ordering_app.adapter.About_US_Adapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUs extends Fragment {

    ListView list_about_Us;
    About_US_Adapter adapter;
    Toolbar toolbar;
    DrawerLayout mDrawerLayout;

    public AboutUs() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_about_us, container, false);

    /*    toolbar = (Toolbar)(getActivity()).findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("About Us");*/
     //   toolbar.setTitleMarginStart(140);
        MainActivity.txt_header.setText("About Us");


        mDrawerLayout = (DrawerLayout)getActivity().findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);

        // Inflate the layout for this fragment
        adapter=new About_US_Adapter(getActivity());
        list_about_Us=(ListView)view.findViewById(R.id.list_about_Us);
        list_about_Us.setAdapter(adapter);
        return view;
    }
}
