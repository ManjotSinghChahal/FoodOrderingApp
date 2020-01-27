package app.com.food_ordering_app.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import app.com.food_ordering_app.Notification.MyFireBaseMessagingService;
import app.com.food_ordering_app.Notification.MyFiresBaseInstanceIdService;
import app.com.food_ordering_app.Web_Service.Login_Api;
import app.com.food_ordering_app.Web_Service.Profile_Api;
import app.com.food_ordering_app.Web_Service.RestaurantListing_Api;
import app.com.food_ordering_app.Web_Service.UpdateDevice_id_Api;
import app.com.food_ordering_app.Web_Service.UpdateLatLong_Api;
import app.com.food_ordering_app.adapter.About_US_Adapter;
import app.com.food_ordering_app.fragment.AboutUs;
import app.com.food_ordering_app.fragment.ContactUs;
import app.com.food_ordering_app.R;
import app.com.food_ordering_app.adapter.HomeAdapter;
import app.com.food_ordering_app.fragment.Credit_Card_Details;
import app.com.food_ordering_app.fragment.History;
import app.com.food_ordering_app.fragment.Home;
import app.com.food_ordering_app.fragment.Profile;
import app.com.food_ordering_app.fragment.Settings;
import app.com.food_ordering_app.global.Global;

import static app.com.food_ordering_app.global.Global.changeFragment;
import static com.facebook.FacebookSdk.getApplicationContext;

public class MainActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //---------------------------------------------
    HomeAdapter adapter;
    ListView listView;
    DrawerLayout drawer;
    public static ImageView profile_pic;
    RelativeLayout rel_menu, rel_home_cart;
    public static TextView txt_header;
    Global global;
    //-----------------------------------------------
    public static ImageView img_edt;
    RelativeLayout relativeLayout;
    SharedPreferences sharedpreferences_UpdateLatLog;
    String res_updateLatLog;
    public static TextView text_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //----------------Firebase constructor call---------------------------------------------
        MyFiresBaseInstanceIdService id_service = new MyFiresBaseInstanceIdService(MainActivity.this);
        id_service.onTokenRefresh();
        MyFireBaseMessagingService msg = new MyFireBaseMessagingService();


        //---------update lat and long api-----------------------------
        sharedpreferences_UpdateLatLog = getApplicationContext().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        res_updateLatLog = sharedpreferences_UpdateLatLog.getString("user_id", "");

        UpdateLatLong_Api updateLatLong_api;
        updateLatLong_api = new UpdateLatLong_Api();
        updateLatLong_api.updateLatLong(getApplicationContext(), res_updateLatLog, Profile.lat_value + "", Profile.lng_value + "");

        //-----to get android id and hit update device id----------------------------------------------------------


        String type_device = "A";
        UpdateDevice_id_Api updateDevice_id_api = new UpdateDevice_id_Api();
        updateDevice_id_api.updateDevice_id(this, res_updateLatLog, Global.device_id, type_device);


        //---------------save and edit visibility gone-----------------------
        relativeLayout = (RelativeLayout) findViewById(R.id.rel_edit);
        txt_header = (TextView) findViewById(R.id.txt_header);
        relativeLayout.setVisibility(View.GONE);
        img_edt = (ImageView) findViewById(R.id.edit_img);

        global = (Global) getApplicationContext();


        rel_menu = (RelativeLayout) findViewById(R.id.rel_menu);
        //------------------------------------------------------------------------
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);




        //=============================================

       // getKeyHash();

        //==============================================







        //------ids 0f drawer items------------------------------------------------------
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        profile_pic = (ImageView) headerview.findViewById(R.id.circleView);
        text_name = (TextView) headerview.findViewById(R.id.username_txt_main);


        //---to handle drawer------------------------------------
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle(mTitle);

            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle(mDrawerTitle);
                hideSoftKeyboard();

                //----------profile api keys hit---------------------------
                Profile_Api profile_Api;
                profile_Api = new Profile_Api("MainActivity");
                profile_Api.profile(getApplicationContext(), res_updateLatLog);

            }
        };
        toggle.syncState();

        drawer.setDrawerListener(toggle);


        //-------------------------Profile pic onClick Listener---------------------
        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(GravityCompat.START);
                changeFragment(MainActivity.this, new Profile());
                img_edt.setTag(1);
                relativeLayout.setVisibility(View.VISIBLE);
            }
        });


        //---------------------------------------------------------------------------------------------


        navigationView.setNavigationItemSelectedListener(this);
        //-------------to_select_home_first-------------------
        navigationView.getMenu().getItem(0).setChecked(true);
        //-------------------------------------------------------
        init();
        work();


    }

    public void getKeyHash() {
        try {

            //   axzJeKD0cmBW0hBN7QKPtLpYuEM=
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                Log.e("KeyHash1111111111111:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


  //-----------------------------onBackPress---------------------------


    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();


       //------------id(Container) to fit in frame layout(exculding app bar)------------------
        Fragment mFragment = getSupportFragmentManager().findFragmentById(R.id.container);

        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (mFragment instanceof Home)
        {
            relativeLayout.setVisibility(View.GONE);
            finish();
        }
        else if (mFragment instanceof Settings)
        {
            changeFragment(MainActivity.this,new Home());
        }
        else if (mFragment instanceof ContactUs)
        {
            changeFragment(MainActivity.this,new Home());
        }
        else if (mFragment instanceof History)
        {
            changeFragment(MainActivity.this,new Home());
        }
        else if (mFragment instanceof AboutUs)
        {
            changeFragment(MainActivity.this,new Home());
        }
        else if (mFragment instanceof Profile)
        {
            changeFragment(MainActivity.this,new Home());
        }
        else if (mFragment instanceof Credit_Card_Details)
        {
            changeFragment(MainActivity.this,new Home());
        }

        else if (count>1)
        {
            super.onBackPressed();
            finish();

        }

    }

    //-----------------------intialize---------------------
    public void init()
    {
        adapter=new HomeAdapter(MainActivity.this,global.getRestaurantList_both());
        listView=(ListView)findViewById(R.id.list_view);

    }
    //----------------------work--------------------
    public void work()
    {

        //------------------notification handling----------------------------
        if(getIntent()!=null) {
        if (getIntent().hasExtra("compare_val")) {
            if (getIntent().getStringExtra("compare_val").equalsIgnoreCase("notification")) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.container, new History()).commit();
            }
        }
        else{
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.container, new Home()).commit();
        }
    }
    else {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.container, new Home()).commit();
    }


       //-----------------Menu onClick listener------------------------------------------------
        rel_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!drawer.isDrawerOpen(GravityCompat.START))
                {
                    drawer.openDrawer(GravityCompat.START);
                }


            }
        });

    }
    //----------------------HidE_keybord----------------------------
    public void hideSoftKeyboard()
    {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
    //------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //-------------------------------------------------------
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment mFragment = getSupportFragmentManager().findFragmentById(R.id.container);

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            if (mFragment instanceof Home) {
            } else {
                changeFragment(MainActivity.this, new Home());
               relativeLayout.setVisibility(View.GONE);
            }

        } else if (id == R.id.nav_history) {
            if (mFragment instanceof History) {
            } else {
                changeFragment(MainActivity.this, new History());
               relativeLayout.setVisibility(View.GONE);
            }
        } else if (id == R.id.nav_payment) {
            if (mFragment instanceof Credit_Card_Details) {
            }
            else
            {
                changeFragment(MainActivity.this,new Credit_Card_Details());
                relativeLayout.setVisibility(View.GONE);
            }
        }else if (id == R.id.nav_setting) {
            if (mFragment instanceof Settings)
            {
            }
            else {
                changeFragment(MainActivity.this,new Settings());
                relativeLayout.setVisibility(View.VISIBLE);
              //  img_edt.setImageResource(R.drawable.profile_button);
            }
        } else if (id == R.id.nav_aboutUs) {

            if (mFragment instanceof AboutUs)
            {
            }
            else {
                changeFragment(MainActivity.this,new AboutUs());
                relativeLayout.setVisibility(View.GONE);
            }

        } else if (id == R.id.nav_ContactUs) {
            if (mFragment instanceof ContactUs)
            {
            }
            else {
                changeFragment(MainActivity.this,new ContactUs());
               relativeLayout.setVisibility(View.GONE);
            }

        }
        else if (id==R.id.nav_Logout)
        {
            SharedPreferences preferences=getSharedPreferences("MyPREFERENCES",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();


            startActivity(new Intent(MainActivity.this,Login.class));
            Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show();
            relativeLayout.setVisibility(View.GONE);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        //-----------shared preferences of cart----------------------------
   SharedPreferences     sharedPreferences_cart = getSharedPreferences("PREFERENCES_CART", Context.MODE_PRIVATE);
        //---------------to show cart----------------------------------
        String res= sharedPreferences_cart.getString("cart_value","value");
        if (res.equalsIgnoreCase("11"))
        {
            rel_home_cart.setVisibility(View.VISIBLE);
        }
    }



}
