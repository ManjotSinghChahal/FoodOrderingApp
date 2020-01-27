package app.com.food_ordering_app.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.activities.MainActivity;
import app.com.food_ordering_app.adapter.HomeAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUs extends Fragment {

    Toolbar toolbar;
    EditText edt_text;

    public ContactUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_contact_us, container, false);

      /*  toolbar = (Toolbar)(getActivity()).findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Contact Us");*/
     //   toolbar.setTitleMarginStart(180);
        edt_text=(EditText)view.findViewById(R.id.edt_text) ;

        MainActivity.txt_header.setText("Contact Us");
        edt_text.setMovementMethod(new ScrollingMovementMethod());

        return view;

    }

}
