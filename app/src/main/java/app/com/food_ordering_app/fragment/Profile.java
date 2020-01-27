package app.com.food_ordering_app.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.Web_Service.Profile_Api;
import app.com.food_ordering_app.Web_Service.Register_with_image_api;
import app.com.food_ordering_app.Web_Service.UpdateProfile_Api;
import app.com.food_ordering_app.activities.Login;
import app.com.food_ordering_app.activities.MainActivity;
import app.com.food_ordering_app.global.Utility;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static app.com.food_ordering_app.R.id.spinner;
import static app.com.food_ordering_app.activities.MainActivity.img_edt;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {
    private static final String IMAGE_DIRECTORY_NAME = "";
    File  f;
    String profile_pic_path="";
    Uri selectedImage;
    public  int res;
    public static  EditText edit_Name;
    public static  EditText edit_LName;
    public static  EditText edit_email;
    public static   EditText edit_street_work;
    public static TextView edit_street;
    public static  AutoCompleteTextView edit_city,edit_city_work;
    public static TextView edt_province;
    public static  EditText edit_mobile;
    public static  TextView edit_postal,edit_postal_work;
    String  name="";
    String  lname="";
    String  email="";
    String  mobile="";
    String  postal_name="";
    String  postal="",postal_work="";
    Spinner spinner_Country,spinner_Country_work;
    String status="0";
    public static CircleImageView img_profile;
    public static TextView txt_spinner,txt_spinner_work;
    String userChoosenTask;
    private static final String LOG_TAG = "Request";
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY = "AIzaSyDLpicWt-dCxIG6czO8dSaTt_3sBqUAn8g";
    private String locationn;
    public static String street_name,street_name_work;
    public static String city_name,city_name_work,province_name;
    public static String country_name,country_name_work;
    String get_Country_txt="";
    String get_Country_Work_txt="";
    static String   countryCode="";
    Bitmap bm;


    //getting postal
    String addr,addr_work;
    public static  double lat_value,lng_value;
    SharedPreferences sharedpreferences_Profile;

    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);

        //---setting edit button on activity start----------------------------
        img_edt.setImageResource(R.drawable.edit);
        img_edt.setVisibility(View.VISIBLE);
        res=1;


        MainActivity.txt_header.setText("Profile");

        spinner_Country =(Spinner)view.findViewById(R.id.spinner_country);
        spinner_Country_work =(Spinner)view.findViewById(R.id.spinner_country_work);


        //----------profile api keys---------------------------
        sharedpreferences_Profile =getApplicationContext().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        String  res_sp= sharedpreferences_Profile.getString("user_id","");

        Profile_Api profile_Api;
        profile_Api=new Profile_Api("Profile");
        profile_Api.profile(getApplicationContext(),res_sp);







        //----------ids-------------------------------------------
        edit_Name=(EditText)view.findViewById(R.id.edit_Name);
        edit_street=(TextView)view.findViewById(R.id.edt_street);
        edit_street_work=(EditText)view.findViewById(R.id.edt_street_work);
        edit_city=(AutoCompleteTextView)view.findViewById(R.id.edt_city);
        edt_province=(TextView)view.findViewById(R.id.edt_province);
        edit_city_work=(AutoCompleteTextView)view.findViewById(R.id.edit_city_work);
        edit_LName=(EditText)view.findViewById(R.id.edit_LName);
        edit_email=(EditText)view.findViewById(R.id.edit_email);
        edit_mobile=(EditText)view.findViewById(R.id.edit_mobile);
        edit_postal=(TextView) view.findViewById(R.id.edt_postal);
        edit_postal_work=(TextView) view.findViewById(R.id.edit_postal_work);
        img_profile=(CircleImageView)view.findViewById(R.id.circleView);
        txt_spinner=(TextView)view.findViewById(R.id.txt_spinner);
        txt_spinner_work=(TextView)view.findViewById(R.id.txt_spinner_work);

        //------------------to set fb data to profile fields----------------------------
        if (Login.status.equalsIgnoreCase("9"))
        {
            edit_Name.setText(Login.f_name);
            edit_LName.setText(Login.l_name);
            edit_email.setText(Login.mail);
            Picasso.with(getContext()).load(Login.UserImageUrl).into(img_profile);
        }


        //------to put home address to string----------------------
        street_name=edit_street.getText().toString();
        postal_name=edit_postal.getText().toString();
        city_name=edit_city.getText().toString();
        province_name=edt_province.getText().toString();

        //------to put work address to string----------------------
        street_name_work=edit_street_work.getText().toString();
        city_name_work=edit_city_work.getText().toString();

        //----------setting edit text enabled false----------------
        img_profile.setEnabled(false);
        edit_Name.setEnabled(false);
        edit_LName.setEnabled(false);
        edit_street.setEnabled(false);
        edit_city.setEnabled(false);
        edt_province.setEnabled(false);
        edit_email.setEnabled(false);
        edit_postal.setEnabled(false);
        edit_mobile.setEnabled(false);

        edit_street_work.setEnabled(false);
        edit_city_work.setEnabled(false);
        edit_postal_work.setEnabled(false);
        txt_spinner.setEnabled(false);
        txt_spinner_work.setEnabled(false);



        work();

        //------------autocomplete city and country of home address------------------------------------------------
        edit_city.setAdapter(new GooglePlacesAutocompleteAdapter(getActivity(), R.layout.list_item));
        edit_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                String str = (String) parent.getItemAtPosition(position);
                locationn=str;
                int index_get= locationn.indexOf(",");
                city_name = locationn.substring(0,index_get);
                edit_city.setText(city_name);





                //-----to get home address of edit text-------------------
                addr=city_name;

                //----------to get longitude and latitude---------------------
                getLocationFromAddress(addr);

                //-------to get postal code---------------------------------------
              /*  final Geocoder gcd = new Geocoder(getApplicationContext());

                List<Address> addresses = null;
                try {
                    addresses = gcd.getFromLocation(lat_value,lng_value,10);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (android.location.Address address : addresses) {
                    if(address.getLocality()!=null && address.getPostalCode()!=null){
                    //    Toast.makeText(getActivity(), address.getPostalCode() +"", Toast.LENGTH_SHORT).show();
                        edit_postal.setText(address.getPostalCode());
                        break;
                    }
                }*/


                //----------------------------------------------------------------------------------

            }
        });


        //------------autocomplete city and country of work address------------------------------------------------
        edit_city_work.setAdapter(new GooglePlacesAutocompleteAdapter(getActivity(), R.layout.list_item));
        edit_city_work.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                String str = (String) parent.getItemAtPosition(position);
                locationn=str;
                Log.e("res",locationn);




                //-----to get home address of edit text-------------------
              //===  addr_work=edit_city_work.getText().toString();
                addr=city_name;

                //----------to get longitude and latitude---------------------
                getLocationFromAddress(addr_work);

                //-------to get postal code---------------------------------------
               /* final Geocoder gcd = new Geocoder(getApplicationContext());

                List<Address> addresses = null;
                try {
                    addresses = gcd.getFromLocation(lat_value,lng_value,10);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (android.location.Address address : addresses) {
                    if(address.getLocality()!=null && address.getPostalCode()!=null){
                        //    Toast.makeText(getActivity(), address.getPostalCode() +"", Toast.LENGTH_SHORT).show();
                        edit_postal_work.setText(address.getPostalCode());
                        break;
                    }
                }
*/

                //----------------------------------------------------------------------------------

            }
        });


        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
              //  Log.i(tag, "keyCode: " + keyCode);
                if( keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Log.e("back_key_listener", "onKey Back listener is working!!!");
                    img_edt.setVisibility(View.GONE);
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });




        //-------------------------------------------------------------
        return view;

    }




    public void work()
    {

        //-----------------------autocomplete address onClick Listener-----------------------

        edit_street.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //---------------------------------------

                AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                        .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS )
                        .build();

              //  autocompleteFragment.setFilter(typeFilter);


                //---------sending intent----------------------------
                try {
                    Intent intent =
                            new PlaceAutocomplete
                                    .IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .setFilter(typeFilter)
                                    .build(getActivity());
                    startActivityForResult(intent, 8);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
            }
        });

      //----------autocomplete province onClick Listener--------------------------------------------
        edt_province.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //---------------------------------------

                AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                        .setTypeFilter(AutocompleteFilter.TYPE_FILTER_REGIONS)
                        .build();

                //  autocompleteFragment.setFilter(typeFilter);


                //---------sending intent----------------------------
                try {
                    Intent intent =
                            new PlaceAutocomplete
                                    .IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .setFilter(typeFilter)
                                    .build(getActivity());
                    startActivityForResult(intent, 9);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }



            }
        });



        //-----------home address spinner text view onClick listener--------------------------
        txt_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //--------------spinner of home address-------------------------------------

                spinner_Country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                        country_name=adapterView.getItemAtPosition(i).toString();
                        Log.e("here_country_name",country_name);
                        //-----to get country code from country name----------------
                        getCountryCode(country_name);
                        txt_spinner.setText(country_name);
                        get_Country_txt = txt_spinner.getText().toString();


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });



                // Create an ArrayAdapter using the string array and a default spinner
                ArrayAdapter<CharSequence> adapt_Country = ArrayAdapter
                        .createFromResource(getActivity(), R.array.countries,
                                android.R.layout.simple_spinner_item);

                // Specify the layout to use when the list of choices appears
                adapt_Country.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Apply the adapter to the spinner
                spinner_Country.setAdapter(adapt_Country);
                spinner_Country.performClick();



            }
        });

        //----------------work address spinner text view onClick listener------------------------------------------

        txt_spinner_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //--------------spinner of work address-------------------------------------

                spinner_Country_work.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                        country_name_work=adapterView.getItemAtPosition(i).toString();
                        //-----to get country code from country name----------------
                        getCountryCode(country_name_work);
                        txt_spinner_work.setText(country_name_work);
                        get_Country_Work_txt = txt_spinner_work.getText().toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });



                // Create an ArrayAdapter using the string array and a default spinner
                ArrayAdapter<CharSequence>    adapt_Country_work = ArrayAdapter
                        .createFromResource(getActivity(), R.array.countries,
                                android.R.layout.simple_spinner_item);

                // Specify the layout to use when the list of choices appears
                adapt_Country_work.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Apply the adapter to the spinner
                spinner_Country_work.setAdapter(adapt_Country_work);

                spinner_Country_work.performClick();
                //-------------------------------------------------------------
            }
        });

        //-------profile pic onClick Listener--------------------------
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectImage();



            }
        });





//---------------------edit / save image onClick Listener---------------------------------------------
        img_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (status.equalsIgnoreCase("0")) {
                    img_profile.setEnabled(true);
                    edit_Name.setEnabled(true);
                    edit_LName.setEnabled(true);
                    edit_email.setEnabled(false);
                    edit_street.setEnabled(true);
                    edit_city.setEnabled(true);
                    edt_province.setEnabled(true);
                    edit_postal.setEnabled(true);
                    //  edit_pass.setEnabled(false);
                    edit_mobile.setEnabled(true);
                    txt_spinner.setEnabled(true);
                    spinner_Country.setEnabled(true);
                    // addr_edit.setEnabled(true);
                    status="1";
                    img_edt.setImageResource(R.drawable.save);

                    edit_city_work.setEnabled(true);
                    edit_street_work.setEnabled(true);
                    edit_postal_work.setEnabled(true);
                    txt_spinner_work.setEnabled(true);


                }
                else if (status.equalsIgnoreCase("1"))
                {



                  if (edit_LName.getText().toString().equalsIgnoreCase(""))
                  {
                      Toast.makeText(getContext(), "Enter last name", Toast.LENGTH_SHORT).show();
                  }
                  else if (edit_Name.getText().toString().equalsIgnoreCase(""))
                    {
                        Toast.makeText(getContext(), "Enter first name", Toast.LENGTH_SHORT).show();
                    }
                  else if (edit_mobile.getText().toString().equalsIgnoreCase(""))
                  {
                      Toast.makeText(getContext(), "Enter mobile number", Toast.LENGTH_SHORT).show();
                  }
                  else if (edit_postal.getText().toString().equalsIgnoreCase(""))
                  {
                      Toast.makeText(getContext(), "Enter postal code", Toast.LENGTH_SHORT).show();
                  }
                    else
                  {

                     status="0";
                    img_edt.setImageResource(R.drawable.edit);

                      txt_spinner.setEnabled(false);
                      spinner_Country.setEnabled(false);
                      img_profile.setEnabled(false);
                      edit_Name.setEnabled(false);
                      edit_LName.setEnabled(false);
                      edit_email.setEnabled(false);
                      edit_street.setEnabled(false);
                      edit_city.setEnabled(false);
                      edt_province.setEnabled(false);
                      edit_postal.setEnabled(false);
                      //  edit_pass.setEnabled(false);
                      edit_mobile.setEnabled(false);
                      //    addr_edit.setEnabled(false);

                      txt_spinner_work.setEnabled(false);
                      edit_city_work.setEnabled(false);
                      edit_street_work.setEnabled(false);
                      edit_postal_work.setEnabled(false);

                      update_profile_method();


                  }




                    //--------------------------------------------------------------



                }

            }

            public void update_profile_method() {


                //---getting value from profile fields----------------------------
                name=edit_Name.getText().toString();
                lname=edit_LName.getText().toString();
                email=edit_email.getText().toString();
                mobile=edit_mobile.getText().toString();
                street_name=edit_street.getText().toString();
                city_name=edit_city.getText().toString();
                province_name=edt_province.getText().toString();
                postal=edit_postal.getText().toString();

                street_name_work=edit_street_work.getText().toString();
                city_name_work=edit_city_work.getText().toString();
                postal_work=edit_postal_work.getText().toString();


                //---------update profile api------------------------------------
                SharedPreferences sharedpreferences_Profile;
                sharedpreferences_Profile =getApplicationContext().getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                String  user_id= sharedpreferences_Profile.getString("user_id","");

                Register_with_image_api sendRegister=new Register_with_image_api();
                sendRegister.sendRegisterPostRequest(getApplicationContext(),user_id,name,lname,mobile,street_name,city_name,get_Country_txt,postal,get_Country_Work_txt,postal_work,city_name_work,profile_pic_path,street_name_work,province_name);


                //----------setting value to profile fields-------------------------
                edit_Name.setText(name);
                edit_LName.setText(lname);
                edit_mobile.setText(mobile);
                edit_street.setText(street_name);
                edit_city.setText(city_name);
                edt_province.setText(province_name);
                edit_postal.setText(postal);

                edit_postal_work.setText(postal_work);
                edit_city_work.setText(city_name_work);

            }

        });

    }
//------------------profile pic methods-------------------------------------------------------------------------------------------------------------

    //----------showing dialog box-------------------------------------------------
    private void selectImage() {

        final CharSequence[] items = {"Camera", "Gallery",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(getActivity());

                if (items[item].equals("Camera")) {
                    userChoosenTask = "Camera";
                    if (result)
                        cameraIntent();
                } else if (items[item].equals("Gallery")) {
                    userChoosenTask = "Gallery";
                    if (result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }


    //----------------------------gallery intent-------------------------------------------
    private void galleryIntent() {
     /*   Intent intent = new Intent();
        intent.setType("image*//*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), 0);*/


        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);

    }

    //----------------------camera intent-------------------------------------------------
    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);

    }


    //-----------------------camera and gallery request permission result---------------------
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Camera"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Gallery"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    //---------camera and gallery onActivity result method--------------------------------------
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {


            if (requestCode == 0)
            //   onSelectFromGalleryResult(data);
            {
                selectedImage = data.getData();


                //  convertImageToByte(selectedImage);


                // get the base 64 string

                //----------------getting image path from gallery-------------------------------------
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getApplicationContext().getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                // Log.e("getpic_profile",picturePath);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                thumbnail = thumbnail.createScaledBitmap(thumbnail, 400, 400, true);
                f = storeImage(thumbnail);
                profile_pic_path = f.getAbsolutePath();
                Log.e("profile_pic_path__", selectedImage+"");
                // content://media/external/images/media/57543


               Picasso.with(getApplicationContext()).load(selectedImage).into(img_profile);

                //---save image on restart -----------------------------
                img_edt.setImageResource(R.drawable.save);
            }
            if (requestCode == 1) {
                onCaptureImageResult(data);

                //---save image on restart-----------------------------
                img_edt.setImageResource(R.drawable.save);

            }
        }
        //-----------------------autocomplete address---------------------------------
        if (requestCode == 8) {
            if (resultCode == RESULT_OK) {
                // retrive the data by using getPlace() method.
                Place place = PlaceAutocomplete.getPlace(getApplicationContext(), data);
                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());


                edit_street.setText(place.getName()+",\n"+
                                place.getAddress() +"\n" + place.getPhoneNumber());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getApplicationContext(), data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }

        //----------------------autocomplete province-----------------------------------------------
        if (requestCode == 9) {
            if (resultCode == RESULT_OK) {
                // retrive the data by using getPlace() method.
                Place place = PlaceAutocomplete.getPlace(getApplicationContext(), data);
                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());

                 /*     edt_province.setText(place.getName()+",\n"+
                        place.getAddress() +"\n" + place.getPhoneNumber());*/



                edt_province.setText(place.getName());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getApplicationContext(), data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }


    }
    //-------to create file from bitmap(converting bitmap into file)-----------------------
    public File storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);

        Log.e("NEW FILE", pictureFile.toString());

        f=new File(pictureFile.toString());

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("Error", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("Error", "Error accessing file: " + e.getMessage());
        }
        return pictureFile;
    }



    //------------to store image to file method(Gallery)----------------------

    private File getOutputMediaFile(int type) {

        //  Food IMAGE_DIRECTORY_NAME;
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
             /*   Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");*/
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;

        if (type == MEDIA_TYPE_IMAGE)
        {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator+ "IMG_" + timeStamp + ".jpg");
        }
        else
        {
            return null;
        }

        return mediaFile;
    }





    //------------camera method----------------------------------------------
    private void onCaptureImageResult(Intent data) {
        bm = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        profile_pic_path=  destination.getAbsolutePath();
        Log.e("pic_profile_camera",profile_pic_path);
        Picasso.with(getApplicationContext()).load(profile_pic_path).into(img_profile);
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("profile_pic_bm",bm+" gg");
        img_profile.setImageBitmap(bm);


    }





    //------------------------------------------

    private void onSelectFromGalleryResult(Intent data) {
        bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

//---------------------------------end of select pic from camera ang gallery-------------------------------------------------------------------------




    //-------------------------onResume method-----------------------------
    @Override
    public void onResume() {
        super.onResume();

    }





    //-----another class of autocomplete city-----------------------------------------------
    public static class GooglePlacesAutocompleteAdapter extends ArrayAdapter implements Filterable {
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
                        Log.e("results",results+"");
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            };
            return filter;
        }
    }

    //------------to get 2 letter country code----------------------------------------------
    public String getCountryCode(String countryName) {

        // Get all country codes in a string array.
        String[] isoCountryCodes = Locale.getISOCountries();
        Map<String, String> countryMap = new HashMap<>();

        // Iterate through all country codes:
        for (String code : isoCountryCodes) {
            // Create a locale using each country code
            Locale locale = new Locale("", code);
            // Get country name for each code.
            String name = locale.getDisplayCountry();
            // Map all country names and codes in key - value pairs.
            countryMap.put(name, code);
        }
        // Get the country code for the given country name using the map.
        // Here you will need some validation or better yet
        // a list of countries to give to user to choose from.
        countryCode = countryMap.get(countryName); // "NL" for Netherlands.
        // Log.e("countrycode",countryCode);
        return countryCode;

    }


    //--------------------------------------------------------------------------


    public static ArrayList autocomplete(String input) {
        ArrayList resultList = null;
        Log.d("input", input);
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);
//------------to filter acc to country(short_name)------------------------------
            // sb.append("&components=country:in");

           sb.append("&components=country:" +countryCode+"");
           sb.append("&types=(cities)");
         //   sb.append("&types=(province)");
          // sb.append("&types=(street_number)");
          //  sb.append("&types=(address)");

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

                Log.e("result_list", resultList.get(i) + "");
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }

        return resultList;
    }

    //----------to get lat lng method------------------------------
    public void getLocationFromAddress(String strAddress)
    {
        //Create coder with Activity context - this
        Geocoder coder = new Geocoder(getActivity());
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



              }

