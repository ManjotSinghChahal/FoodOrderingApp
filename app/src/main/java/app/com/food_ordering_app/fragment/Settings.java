package app.com.food_ordering_app.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.Web_Service.ChangePassword_Api;
import app.com.food_ordering_app.activities.MainActivity;
import app.com.food_ordering_app.adapter.HomeAdapter;

import static app.com.food_ordering_app.activities.MainActivity.img_edt;

/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment {

    Toolbar toolbar;
   public static   RelativeLayout notification_layout_rel_layout,password_rel_layout;
    RelativeLayout general_settings_layout,change_password_lay;
    RelativeLayout rel_submit_button,rel_layout_cart;
    EditText current_password_edt,new_password_edt,confirm_password_edt;
    String status_arrow_setting="downward";
    String status_arrow_password="downward";
    ImageView img_genaral_setting,password_rel_layut;
    RelativeLayout rel_layout_profile_btn;
    ImageView img_profile_button;

    public Settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_settings, container, false);

        MainActivity.txt_header.setText("Settings");

        img_edt.setImageResource(R.drawable.profile_button);
        img_edt.setVisibility(View.VISIBLE);

        //------------ids--------------------------------------
         notification_layout_rel_layout=(RelativeLayout)view.findViewById(R.id.notification_layout_rel_layout);
         password_rel_layout=(RelativeLayout)view.findViewById(R.id.password_rel_layout);
         rel_submit_button=(RelativeLayout)view.findViewById(R.id.rel_submit_button);
         rel_layout_cart=(RelativeLayout)view.findViewById(R.id.rel_layout_cart);
         general_settings_layout=(RelativeLayout)view.findViewById(R.id.general_settings_layout);
         change_password_lay=(RelativeLayout)view.findViewById(R.id.change_password_lay);
         current_password_edt=(EditText)view.findViewById(R.id.current_password_edt);
         new_password_edt=(EditText)view.findViewById(R.id.new_password_edt);
         confirm_password_edt=(EditText)view.findViewById(R.id.confirm_password_edt);
         img_genaral_setting=(ImageView) view.findViewById(R.id.img_genaral_setting);
         password_rel_layut=(ImageView) view.findViewById(R.id.img_password_setting);



        //---------OnClickListener-----------------------------------------------------------

        //-------------profile button onClick Listener-----------------------
        img_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //------code to move from one fragment to another fragment----------------------
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new Profile());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


            }
        });

        //-------------to show hidden password layout--------------------------------
        change_password_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (status_arrow_password.equalsIgnoreCase("downward"))
                {
                    password_rel_layut.setImageResource(R.drawable.upper_arrow_brown);
                    status_arrow_password="upward";
                    password_rel_layout.setVisibility(View.VISIBLE);

                }
                else if (status_arrow_password.equalsIgnoreCase("upward"))
                {
                    password_rel_layut.setImageResource(R.drawable.down_arrow_brown);
                    status_arrow_password="downward";
                    password_rel_layout.setVisibility(View.GONE);
                }

            }
        });


      //-----------to show hidden settings layout----------------------------------
        general_settings_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //   notification_layout_rel_layout.setVisibility(View.VISIBLE);

                if (status_arrow_setting.equalsIgnoreCase("downward"))
                {
                  //  img_genaral_setting.setImageResource(R.drawable.upward_button);
                //    status_arrow_setting="upward";
                  //  notification_layout_rel_layout.setVisibility(View.VISIBLE);

                }
                else if (status_arrow_setting.equalsIgnoreCase("upward"))
                {
                  //  img_genaral_setting.setImageResource(R.drawable.down_arrow_brown);
                //    status_arrow_setting="downward";
                 //   notification_layout_rel_layout.setVisibility(View.GONE);
                }


            }
        });

        //---------------submit button------------------------------------------------
        rel_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //---------to get edit text value------------------------------------
              String currentPassword =  current_password_edt.getText().toString();
              String newPassword =  new_password_edt.getText().toString();
              String confirmPassword =  confirm_password_edt.getText().toString();

                SharedPreferences sharedPreferences_setting=getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                String  res_setting=sharedPreferences_setting.getString("user_id","");
               if (current_password_edt.getText().toString().equalsIgnoreCase("") )
                {
                    current_password_edt.setError("Enter Current password");
                }
               else if (new_password_edt.getText().toString().equalsIgnoreCase("") )
                {

                    new_password_edt.setError("Enter new password");
                }
               else if (confirm_password_edt.getText().toString().equalsIgnoreCase("") )
               {
                   confirm_password_edt.setError("Enter confirm password");
               }

             else   if (newPassword.equals(confirmPassword))
                {

                    //---------------api hit------------------------------------------------------
                    ChangePassword_Api changePassword_api = new ChangePassword_Api();
                    changePassword_api.change_password(getActivity(), res_setting, currentPassword, newPassword, confirmPassword);


                }
                else

                {
                    Toast.makeText(getActivity(), "Password does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //----------------------cancel button-----------------------------------------
        rel_layout_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password_rel_layout.setVisibility(View.GONE);
            }
        });



        //-----------back key press listener-------------------------------------------
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //  Log.i(tag, "keyCode: " + keyCode);
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                 //   Log.e("back_key_listener", "onKey Back listener is working!!!");
                    img_edt.setVisibility(View.GONE);
                    getFragmentManager().popBackStack(null, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });





        //---------------------------------------------------------------------------------------
        return view;
    }

}
