package app.com.food_ordering_app.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.Web_Service.DeliveryAddresses_Api;
import app.com.food_ordering_app.fragment.Profile;
import app.com.food_ordering_app.utility.GPS_traker;

public class Delivery_Location extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final int CONNECTION_RESOLUTION_REQUEST = 1;
    RelativeLayout rel_img_back_loc, rel_layout_done;
    GoogleMap mMap;
    String status_loc = "";
    LatLng latLng_EditBar, latLng_MapClick;
    GPS_traker gps_traker;
    LocationManager locationManager;
    GoogleApiClient mGoogleApiClient;
    AutoCompleteTextView del_loc;
    String provider;
    double lat_value, lng_value = 0;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static String status = "0";
    private BottomSheetBehavior mBottomSheetBehavior;
    NestedScrollView bottomSheet;
    ListView listview_delivery;
     CoordinatorLayout coordinator_layout;
     TextView address;
    //---static taken to access in takeout-------------
    public static String set_status;

    //autocomplete city and country
    private static final String LOG_TAG = "Request";
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    //  private static final String API_KEY = "AIzaSyDLpicWt-dCxIG6czO8dSaTt_3sBqUAn8g";
    private static final String API_KEY = "AIzaSyDPsL25TNhsm5iXxQ2rdJv9S9QS-C453ts";
    private String locationn;
    public static String loc_access;
    public static String txt_home_address_get, txt_work_address_get;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_location);



        //------------------------------------------------------------
        SharedPreferences sharedPreferences_MenuAdapter =getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        user_id=sharedPreferences_MenuAdapter.getString("user_id","");







        loc();
        init();
        init_persistent_bottomsheet();
        work();
       // bottomSheetBeh();



        //----------------AutoComplete Place onClicK listener---------------------------

        //------------autocomplete city and country------------------------------------------------
        del_loc.setAdapter(new GooglePlacesAutocompleteAdapter(Delivery_Location.this, R.layout.list_item));
        del_loc.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                String str = (String) parent.getItemAtPosition(position);
                 getLatLongFromGivenAddress(str);
                //Toast.makeText(AddTrip.this, "Location"+str, Toast.LENGTH_LONG).show();
                locationn = str;
                //-----to get home address of edit text-------------------
                String dd = del_loc.getText().toString();

                //----------to get longitude and latitude---------------------
               // getLocationFromAddress(dd);





            }
        });


    }

    //----------to get lat lng method------------------------------
    public void getLatLongFromGivenAddress(String strAddress)
    {
        //Create coder with Activity context - this
        Geocoder coder = new Geocoder(Delivery_Location.this);
        List<Address> address;

        try {
            //Get latLng from String
            address = coder.getFromLocationName(strAddress,5);

            //check for null
            if (address == null) {
                return;
            }

            //Lets take first possibility from the all possibilities.
            Address location=address.get(0);

            lat_value=location.getLatitude();
            lng_value=location.getLongitude();
            //    Toast.makeText(getActivity(),lat_value +" and " +lng_value+"", Toast.LENGTH_SHORT).show();

        } catch (IOException e)



        {
            e.printStackTrace();
        }
    }





    //--------------------loc methods---------------------------------------------------------
    private void loc() {

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //To setup location manager
        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        //To request location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);


    }
    //------------------------------init()--------------------------------------------------------------------

    public void init() {
        gps_traker = new GPS_traker(this);
        rel_img_back_loc = (RelativeLayout) findViewById(R.id.rel_img_back_loc);
        rel_layout_done = (RelativeLayout) findViewById(R.id.rel_layout_done);
        del_loc = (AutoCompleteTextView) findViewById(R.id.delivery_loc);


        coordinator_layout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);










    }


    //-------------------------------work()-------------------------------------------------------------------
    public void work() {


        //-------------keypad done button onClick  on search bar-------------------------------------------
        del_loc.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
                    Log.e("here_is_res", "Enter pressed");


                    //-----------getting latLng from address------------------------------------
                    Geocoder coder = new Geocoder(Delivery_Location.this);
                    List<Address> address;


                    try {
                        //Get latLng from String
                        address = coder.getFromLocationName(del_loc.getText().toString(), 5);


                        Address location = address.get(0);

                        lat_value = location.getLatitude();
                        lng_value = location.getLongitude();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    latLng_EditBar = new LatLng(lat_value, lng_value);


                    status_loc = "onEditOne";
                    // Creating a marker
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng_EditBar);
                    markerOptions.title(latLng_EditBar.latitude + " : " + latLng_EditBar.longitude);
                    mMap.clear();
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng_EditBar, 12));
                    mMap.addMarker(markerOptions);

                    mMap.setMyLocationEnabled(true);

                    LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                    Criteria criteria = new Criteria();
                    provider = locationManager.getBestProvider(criteria, true);
                    Location location = locationManager
                            .getLastKnownLocation(provider);

                    if (location != null) {
                        onLocationChanged(location);
                    }

                    locationManager
                            .requestLocationUpdates(provider, 20000, 0, Delivery_Location.this);


                }
                return false;

            }
        });


        //------------to fetch data of entered address---------------------
        del_loc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Log.e("value_txt", charSequence + "");

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //  loc_access=editable.getFilters().toString();
                loc_access = editable.toString();
                Log.e("locdata_D", loc_access);


            }
        });


        //-----------------location done onClick Listener-------------
        rel_layout_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status.equals("0")) {
                    if (del_loc.getText().toString().equals("")) {
                        Toast.makeText(Delivery_Location.this, "Select Location", Toast.LENGTH_SHORT).show();
                    } else {
                        set_status = "1";
                        finish();

                    }

                }
                status = "0";
            }
        });

        //-----------back button onClick Listener---------------------
        rel_img_back_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                del_loc.setText("");
                loc_access="";
                finish();
            }


        });



    }


    //------------------Bottom sheet behaviour methods---------------------------------------------
    private void bottomSheetBeh() {

        //--------------------swipe up mthod----------------------------------
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(100);

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {

               if (newState == BottomSheetBehavior.PEEK_HEIGHT_AUTO)

                {
                    mBottomSheetBehavior.setPeekHeight(0);
                }


            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });

    }



    //-------------------------onMapReady()----------------------------------------------------------------

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        permissionCheck();


        //------------get latLng on clicked place on Map(onClick Listener)----------------------
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Log.e("get_loc", latLng + "");
                latLng_MapClick = latLng;

                status_loc = "onMapClick";

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng_MapClick);
                Log.e("latLong_here", latLng + "");

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                // Clears the previously touched position
                mMap.clear();

                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng_MapClick));

                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);

                Log.e("lat_Get", latLng.latitude + "");
                Log.e("lat_Get", latLng.longitude + "");


                double latitude = latLng.latitude;
                double longitude = latLng.longitude;


                //-------to get address from latLong-----------------------------
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(Delivery_Location.this, Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();
                    String knownsubadmin = addresses.get(0).getSubAdminArea();
                    String subLocality = addresses.get(0).getSubLocality();
                    Locale getLocale = addresses.get(0).getLocale();



                    Log.e("gettttt", address);
                    del_loc.setText(address);


                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    //--------------------permissions start------------------------------------------------------

    private void permissionCheck() {

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                // buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            //  buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(Delivery_Location.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Permission was granted.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            // buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            //You can add here other case statements according to your requirement.
        }
    }


//-----------------------onLocationChanged()------------------------------------------------------------

    @Override
    public void onLocationChanged(Location location) {

        //----------checking status to set markers to different positions--------------

        //-------------edit bar status------------------------------
        if (status_loc.equalsIgnoreCase("onEditOne")) {
            LatLng gps = latLng_EditBar;
            Log.e("get_Lat_Long", gps + "");
            //To create marker in map and adding marker to the map
            mMap.addMarker(new MarkerOptions()
                    .position(gps)
                    // snippet is used to show more data with title
                    .snippet("Your current position")
                    .title("Current location"));


            //opening position with some zoom level in the map
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gps, 12));

        }

        //--------------------map click status------------------------------
        else if (status_loc.equalsIgnoreCase("onMapClick")) {

            LatLng gps = latLng_MapClick;
            Log.e("get_Lat_Long", gps + "");
            //To create marker in map and adding marker to the map
            mMap.addMarker(new MarkerOptions()
                    .position(gps)
                    // snippet is used to show more data with title
                    .snippet("Your current position")
                    .title("Current location"));


            //opening position with some zoom level in the map
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gps, 12));

        }

        //----------------------current or default status------------------------
        else {

            if (mMap != null) {
                //To clear map data
                mMap.clear();
                //To hold location
                LatLng gps = new LatLng(location.getLatitude(), location.getLongitude());
                Log.e("get_Lat_Long", gps + "");
                //To create marker in map and adding marker to the map
                mMap.addMarker(new MarkerOptions()
                        .position(gps)
                        // snippet is used to show more data with title
                        .snippet("Your current position")
                        .title("Current location"));


                //opening position with some zoom level in the map
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gps, 12));
            }
        }


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    @Override
    public void onConnected(Bundle bundle) {


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, CONNECTION_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                // There was an error with the resolution intent. Try again.
                mGoogleApiClient.connect();
            }
        } else {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, 1);
            dialog.show();
        }

    }


    //-----------------------onActivityResult-------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //---------------------------permissions---------------------------------------------
        if (requestCode == CONNECTION_RESOLUTION_REQUEST && resultCode == RESULT_OK) {
            mGoogleApiClient.connect();
        }

        //------------------AutoCompletePlace-------------------------------------------

       /* if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // retrive the data by using getPlace() method.
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());

                ((TextView) findViewById(R.id.delivery_loc))
                        .setText(place.getName() + ",\n" +
                                place.getAddress() + "\n" + place.getPhoneNumber());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }*/


        //----------------------------------------------------------------------------


    }


    //-----another class of autocomplete city-----------------------------------------------
  public static  class GooglePlacesAutocompleteAdapter extends ArrayAdapter implements Filterable
    {
        public ArrayList resultList;

        public GooglePlacesAutocompleteAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return resultList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            //Toast.makeText(AddTrip.this, "ghjhg", 2000).show();

            return resultList.get(position);

        }
        // @Override
        // public int getCount() {
        // return resultList.size();
        // }
        //
        // @Override
        // public String getItem(int index) {
        // return resultList.get(index);
        // }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        // Retrieve the autocomplete results.
                        Log.d("constraints", constraint + "");
                        resultList = autocomplete(constraint.toString());
                        // Toast.makeText(Request.this, ""+resultList.size(),
                        // 2000).show();
                        // Assign the data to the FilterResults
                        filterResults.values = resultList;

                        filterResults.count = resultList.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results != null && results.count > 0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            };
            return filter;
        }
    }







    public static ArrayList autocomplete(String input) {
        ArrayList resultList = null;
        Log.d("input", input);
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);

    /*
     * sb.append("&components=country:in");
     */
            sb.append("&types=(cities)");
            sb.append("&input=" + URLEncoder.encode(input, "utf-8"));

            //
            // StringBuilder sb = new StringBuilder(PLACES_API_BASE
            // + TYPE_AUTOCOMPLETE + OUT_JSON);
            // // sb.append("&components=country:in&radius=5");
            // sb.append("&input=" + URLEncoder.encode(input, "utf8"));
            // //sb.append("&types=food");
            // //
            // sb.append("&types=establishment&location=30.7800,76.6900&radius=1000");
            // sb.append("&location=");
            // /*+ URLEncoder.encode(lat, "utf8") + ","
            // + URLEncoder.encode(lng, "utf8"));*/
            // sb.append("&radius=" + URLEncoder.encode("1000", "utf8"));
            // sb.append("&key=" + API_KEY);

            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            Log.e("data", sb + "//"+jsonResults);
            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {

            Log.d("final block","1");
            if (conn != null) {
                Log.d("final block","2");
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
                System.out.println("============================================================");
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));

                Log.e("result_here", resultList.get(i) + "");
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }

        return resultList;
    }


    //-------------on Back pressed--------------------------------------
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        del_loc.setText("");
        loc_access="";


    }

    //-------------bottom sheet behaviour method----------------------------------------
    public void init_persistent_bottomsheet() {

        View persistentbottomSheet = coordinator_layout.findViewById(R.id.listView_rel_layout);
        address = (TextView) persistentbottomSheet.findViewById(R.id.address);
        listview_delivery=(ListView)persistentbottomSheet.findViewById(R.id.listview_delivery);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(persistentbottomSheet);

        //-----------delivery address list api hit-----------------------------------------
        DeliveryAddresses_Api deliveryAddresses_api = new DeliveryAddresses_Api();
        deliveryAddresses_api.deliveryAddress(Delivery_Location.this,user_id,listview_delivery);


        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        if (behavior != null)
            behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    //showing the different states
                  switch (newState) {
                          case BottomSheetBehavior.STATE_HIDDEN:
                            break;
                        case BottomSheetBehavior.STATE_EXPANDED:
                            break;
                        case BottomSheetBehavior.STATE_COLLAPSED:
                            break;
                        case BottomSheetBehavior.STATE_DRAGGING:
                            break;
                        case BottomSheetBehavior.STATE_SETTLING:
                            break;

                }
    }
                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    // React to dragging events

                }
            });

    }
}

