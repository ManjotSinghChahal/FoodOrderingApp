package app.com.food_ordering_app.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Credit_Card_Details extends Fragment {
Toolbar toolbar;

    public Credit_Card_Details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_credit_card_details, container, false);
/*
        toolbar = (Toolbar)(getActivity()).findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Payment");*/
    //    toolbar.setTitleMarginStart(180);
        MainActivity.txt_header.setText("Payment");
        return view;


    }

}
