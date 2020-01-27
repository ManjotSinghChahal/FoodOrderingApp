package app.com.food_ordering_app.fragment;


import android.content.Context;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.Web_Service.GetCart_Api;
import app.com.food_ordering_app.Web_Service.RestaurantListing_Api;
import app.com.food_ordering_app.Web_Service.RestaurantSearchByName_Api;
import app.com.food_ordering_app.activities.MainActivity;
import app.com.food_ordering_app.adapter.HomeAdapter;
import app.com.food_ordering_app.global.Global;
import app.com.food_ordering_app.interfac.MyInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements MyInterface, LocationListener {

    private GoogleApiClient googleApiClient;
    final static int REQUEST_LOCATION = 199;
    HomeAdapter adapter;
    ListView listView;
    Global global;
    EditText header_Search;
    LocationManager locationManager;
    Location location;
    double latitude;
    double longitude;
    boolean canGetLocation;
    RelativeLayout relative_Search_icon,relative_cross_icon;
    RestaurantListing_Api restaurantListing_api;
    SharedPreferences sharedpreferences_home;
    String user_id_get;
    double lat_value,long_value;
    public static Context context_home;

    public Home()
        {
        // Required empty public constructor
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context_home=getActivity();

        //-----------global variable initializer-------------------
        global = (Global) getActivity().getApplicationContext();

//=================================================================================

        //-----------shared preferences to get user_id---------------------------------------------
        SharedPreferences   sharedPreferences_Menu_Adpt =getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        user_id_get =  sharedPreferences_Menu_Adpt.getString("user_id","");

        //------------get Cart api hitting---------------------------------------------------------
        GetCart_Api getCart_api = new GetCart_Api("","home");
        getCart_api.getCartMethod(getActivity(),user_id_get);



        //-------------ids----------------------------------
        listView = (ListView) view.findViewById(R.id.list_view);
        header_Search = (EditText) view.findViewById(R.id.header_Search);
        relative_Search_icon = (RelativeLayout) view.findViewById(R.id.relative_Search_icon);
        relative_cross_icon = (RelativeLayout) view.findViewById(R.id.relative_cross_icon);

        //-------setting title of home screen---------------------
        MainActivity.txt_header.setText("Current Location");

        //------------location method-----------------------------
        enableLoc();


        //-----------------to get current device location----------------------
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);

        //----method to get device location------
        getNetworkInfo();






        //--------update lat long api hitting------------------------------------------
         sharedpreferences_home = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
         String res_home = sharedpreferences_home.getString("user_id", "");
         restaurantListing_api = new RestaurantListing_Api(getActivity(), this);
         restaurantListing_api.resList(getActivity(), res_home, latitude, longitude);



        //----------------on search bar onClick listener, adding text ChangedListener---------------------------

        header_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //------checking edit text is empty or not-----------------
                if (header_Search.getText().toString().equals(""))

                {
                    relative_cross_icon.setVisibility(View.GONE);
                }
                else
                {
                    relative_cross_icon.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //------------cross sign onClick Listener---------------------------------------
        relative_cross_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 sharedpreferences_home = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
               String  res_user_id = sharedpreferences_home.getString("user_id", "");

                //------checking edit text is empty or not-----------------
                if (header_Search.getText().toString().equals(""))

                {

                }
                //-------------------if not empty hit api----------------------------
                else
                {
                restaurantListing_api = new RestaurantListing_Api(getActivity(), Home.this);
                restaurantListing_api.resList(getActivity(), res_user_id, latitude, longitude);

                    //----------clearing edit text-------------------
                    header_Search.setText("");
                    relative_cross_icon.setVisibility(View.GONE);
                }


            }
        });

        //---------search button onClick Listener(enter press of keypad)------------------------------------------------------------------
          header_Search.setOnEditorActionListener(new TextView.OnEditorActionListener()
          {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {


                Log.e("ssssssssssssss2222","22222");
                if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {


                    String restaurant_name = header_Search.getText().toString().trim();

                    sharedpreferences_home = getActivity().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                    String res_home = sharedpreferences_home.getString("user_id", "");


                    //-----------search restaurant by name api hit------------------------------------------
                    RestaurantSearchByName_Api searchRestaurantName_api=new RestaurantSearchByName_Api();
                    searchRestaurantName_api.searchRestaurant(getContext(),res_home,restaurant_name,listView);


                }

                return false;

            }
        });



        return view;
    }


    //--------------------method to get device location--------------------------------------
    private void getNetworkInfo() {

        // getting GPS status
        boolean isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        boolean isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled) {
            // no network provider is enabled
        } else {
            this.canGetLocation = true;

            if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        1,
                        1, this);
                // Log.d("activity", "LOC Network Enabled");
                if (locationManager != null) {
                    location = locationManager
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        //   Log.d("activity", "LOC by Network");
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        //   Log.e("lat_long",latitude+" and " +longitude);
                    }
                }
            }
            // if GPS Enabled get lat/long using GPS Services
            if (isGPSEnabled) {
                if (location == null) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            1,
                            1, this);
                    //   Log.d("activity", "RLOC: GPS Enabled");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            //       Log.d("activity", "RLOC: loc by GPS");

                            latitude = location.getLatitude();
                            longitude = location.getLongitude();

                        }
                    }
                }
            }
        }

    }


    //-----------------------For GPS location----------------------------------------------------------------

    private void enableLoc() {

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(Bundle bundle) {

                        }

                        @Override
                        public void onConnectionSuspended(int i) {
                            googleApiClient.connect();


                        }
                    })
                    .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(ConnectionResult connectionResult) {

                            Log.e("Location error", "Location error " + connectionResult.getErrorCode());
                        }
                    }).build();
            googleApiClient.connect();
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            builder.setAlwaysShow(true);

            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                // Log.e("try","try");
                                status.startResolutionForResult(getActivity(), REQUEST_LOCATION);


                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                                //  Log.e("catch","e");
                            }
                            break;
                    }
                }
            });
        }


    }



    //-------------------Location listener overidded methods------------------------------
    @Override
    public void onLocationChanged(Location location) {
        Log.e("lat",  user_id_get+ "   "+lat_value+"   " +"  " +long_value);
        Log.e("loc1", location.getLatitude() + "" + location.getLongitude());
        LatLng gps = new LatLng(location.getLatitude(), location.getLongitude());

         lat_value = location.getLatitude();
         long_value = location.getLongitude();


        //----------restaurant api hit on location access click---------------------------
      //  restaurantListing_api = new RestaurantListing_Api(context_home, Home.this);
      //  restaurantListing_api.resList(context_home, user_id_get, lat_value, long_value);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

       //------------here is executed when ok of location is selected


    }

    @Override
    public void onProviderDisabled(String s) {

    }
    //----------------------------------------------------------------------------------



    //----------------------interface method--------------------
    @Override
    public void onSuccess() {

        //------------setting restaurant list adapter-------------------------------------
        adapter = new HomeAdapter(context_home, global.getRestaurantList_both());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }







}




