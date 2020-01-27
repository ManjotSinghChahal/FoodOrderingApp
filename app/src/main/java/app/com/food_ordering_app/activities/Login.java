package app.com.food_ordering_app.activities;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.Web_Service.Facebook_Api;
import app.com.food_ordering_app.Web_Service.LoginValue;
import app.com.food_ordering_app.Web_Service.Login_Api;
import app.com.food_ordering_app.Web_Service.WebService;
import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

import static app.com.food_ordering_app.fragment.Profile.edit_Name;

public class Login extends AppCompatActivity {


    Button btn_signin;
    public static EditText edt_username,edt_password;
    TextView txt_signUp,txt_forgot_password;
    CallbackManager callbackManager;
    ImageView img_fb,img_twitter,img_google;
    LoginButton loginButton;
   // TwitterLoginButton twitterLoginButton;
    TwitterAuthClient mTwitterAuthClient;
    SignInButton signInButton;
    GoogleSignInOptions gso;
    GoogleApiClient mGoogleApiClient;
    private GoogleApiClient googleApiClient;
    final static int REQUEST_LOCATION = 199;
    SharedPreferences sharedpreferences;
    public static  String mail;
    public static  String l_name,f_name;
    public static  String[] f_l_name;
    public static  String UserImageUrl="";
    String res;
    public static String status="0";
    //---------------

    Login_Api login_api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        Twitter.initialize(this);
        setContentView(R.layout.activity_login);


        //-----integration=facebok,twitter,google+-----------
        callbackManager = CallbackManager.Factory.create();
        mTwitterAuthClient= new TwitterAuthClient();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient=new GoogleApiClient.Builder(Login.this)
                .enableAutoManage(Login.this,null)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        //---------shared preferences-----------------------------------
         sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
         res=sharedpreferences.getString("user_id","");
         Log.e("userId_get",res);
         if (res.equalsIgnoreCase(""))
         {

         }
       else
           {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
           }
        //---------------------------------------------------------------------------------

        init();
        work();



    }

    public  void init()
    {
        btn_signin=(Button)findViewById(R.id.btn_signin);
        edt_username=(EditText)findViewById(R.id.fName);
        edt_password=(EditText)findViewById(R.id.passName);
        txt_signUp=(TextView)findViewById(R.id.txt_signUp);
        txt_forgot_password=(TextView)findViewById(R.id.forgot_password);
        loginButton = (LoginButton)findViewById(R.id.login_fb);
        img_fb=(ImageView)findViewById(R.id.image_fb);
        img_twitter=(ImageView)findViewById(R.id.image_twitter);
        img_google=(ImageView)findViewById(R.id.image_google);
        login_api=new Login_Api();
    // twitterLoginButton = (TwitterLoginButton) findViewById(R.id.twitterLogin);
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);



    }


    public  void work() {
        //-------------------------btn_signIn-------------------------------------
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //------------email vqalidation----------------------

                String email = edt_username.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (email.length() ==0)
                {
                    edt_username.setError("Enter email");
                }
              else  if (!email.matches(emailPattern))
                {
                  edt_username.setError("Invalid email");
                }
                else if (edt_password.length()<6)
                {
                    edt_password.setError("Password should be atleast 6 characters");
                }

             else
                {

                login_api.login(Login.this,edt_username.getText().toString(),edt_password.getText().toString());

                }






            }
        });

        //-------------------------btn_signUp-------------------------------------
        txt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
                finish();
            }
        });


        //-----------------facebook_integration onClickListener-------------------------
        img_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginButton.performClick();

                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                       final String fb_id=loginResult.getAccessToken().getUserId();
                       Log.e("adsfjw","dfkjew");
                       Log.e("adsfjw",fb_id);


                        // Facebook Email address
                         GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {
                                        Log.e("LoginActivity Response ", response.toString());

                                        try
                                        {
                                             f_name = object.getString("first_name");
                                             l_name = object.getString("last_name");
                                             mail = object.getString("email");
                                             UserImageUrl="https://graph.facebook.com/" +  fb_id + "/picture?type=large";

                                            status="9";

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(Login.this, "Failure", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id, first_name, last_name, email");
                        request.setParameters(parameters);
                        request.executeAsync();


                      //  LoginManager.getInstance().logOut();





                          String mail_str=mail;
                          String f_name_str=f_name;
                          String l_name_str=l_name;
                          String gender="";
                          String userImage="";
                          String country_str="";
                          String city_str="";
                          String street_str="";
                          String postal_code="";
                          String country_code="";

                       Facebook_Api facebook_api=new Facebook_Api();
                       facebook_api.facebook_login(getApplicationContext(),fb_id,"F",mail_str,f_name_str,l_name_str,gender,userImage,country_str,city_str,street_str,postal_code,country_code);

                    }

                    @Override
                    public void onCancel() {
                        Log.e("cancel", "cancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.e("error", error + "");

                    }
                });
            }
        });

        //----------------twitter_integration onClickListener-----------------------------

        img_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mTwitterAuthClient.authorize(Login.this, new com.twitter.sdk.android.core.Callback<TwitterSession>() {

                    @Override
                    public void success(Result<TwitterSession> twitterSessionResult) {
                        status="9";
                        Log.e("success2", twitterSessionResult.data.getUserName() + "  nooooo");
                        Log.e("success5", twitterSessionResult.data.getId() + "  nooooo1");
                        mail=twitterSessionResult.data.getUserName();
//-----------------------------------------------------
                        String twitter_id=twitterSessionResult.data.getUserId()+"";
                        String username=twitterSessionResult.data.getUserName()+"";

                      /*  Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);*/

                        String mail_str=username;
                        String f_name_str="";
                        String l_name_str="";
                        String gender="";
                        String userImage="";
                        String country_str="";
                        String city_str="";
                        String street_str="";
                        String postal_code="";
                        String country_code="";
                        Facebook_Api facebook_api=new Facebook_Api();
                       facebook_api.facebook_login(getApplicationContext(),twitter_id,"T",mail_str,f_name_str,l_name_str,gender,userImage,country_str,city_str,street_str,postal_code,country_code);


                        //--------to get profile url-----------------------------------
                        Call<User> user1 = TwitterCore.getInstance().getApiClient().getAccountService().verifyCredentials(false, false,false);
                        user1.enqueue(new com.twitter.sdk.android.core.Callback<User>() {
                            @Override
                            public void success(Result<User> result) {
                                String photoUrlNormalSize   = result.data.profileImageUrl;
                                String name   = result.data.name;
                                Log.e("image_url",photoUrlNormalSize);
                                Log.e("name",name);
                               String  full_name=result.data.name;


                                //----------------here is problem---------
                                f_l_name=full_name.split(" ");
                                f_l_name=full_name.split(" ");
                                if (f_l_name.length==2)
                                {
                                f_name=f_l_name[0];
                                l_name=f_l_name[1];
                                }
                                else
                                {
                                    f_name=" ";
                                    l_name=" ";
                                }

                              /*  f_name=full_name.split(" ")[0];
                                l_name=full_name.split(" ")[1];
*/
                                if (result.data.profileImageUrl!=null)
                                {

                                    UserImageUrl=result.data.profileImageUrl;
                                }
                                else if (result.data.profileImageUrl==null)
                                {
                                    UserImageUrl="http://abs.twimg.com/sticky/default_profile_images/default_profile_normal.png";
                                }

                            }

                            @Override
                            public void failure(TwitterException exception) {
                            }
                        });
                        //--------------------------------------------


                    }

                    @Override
                    public void failure(TwitterException e) {
                        e.printStackTrace();
                        Toast.makeText(Login.this, "Failures", Toast.LENGTH_SHORT).show();
                    }
                });





            }
        });


        //-------------------Google+ integration onClickListener-------------------------------
        img_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInButton.performClick();

                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, 1);

            }
        });
        //---------------------forgot_password Click listener--------------------------------------

        txt_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Forgot_Password.class));

            }
        });


    }

    //------------------onActivityResult----------------------------

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("Hello",requestCode+"");
        //------------------facebook_integration onActivityResult----------------------------
        callbackManager.onActivityResult(requestCode, resultCode, data);

        //---------------twitter_integration onActivityResult-------------------
        mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);

        //-------------------google+_integration onActivityResult-------------------------------------------
        if (requestCode==1) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                status="9";
                GoogleSignInAccount account=result.getSignInAccount();
                //-----setting data to profile fields-----------
                mail =account.getEmail().toString();
                String full_name= account.getDisplayName().toString();
              //  f_name=full_name.split(" ")[0];
              //  l_name=full_name.split(" ")[1];
             //   UserImageUrl=" https://plus.google.com/s2/photos/profile/" + account.getId().toString() + "?sz=100";

                f_l_name=full_name.split(" ");
                f_l_name=full_name.split(" ");
                if (f_l_name.length==2)
                {
                    f_name=f_l_name[0];
                    l_name=f_l_name[1];
                }
                else
                {
                    f_name=" ";
                    l_name=" ";
                }











                if (account.getPhotoUrl()!=null)
                {

                  Log.e("image_url1",account.getPhotoUrl()+"ghrth");
                  UserImageUrl= account.getPhotoUrl().toString();
                }
                else if (account.getPhotoUrl()==null)
                {
                    UserImageUrl="http://abs.twimg.com/sticky/default_profile_images/default_profile_normal.png";
                }


           //     Toast.makeText(this,account.getPhotoUrl().toString()+ " url", Toast.LENGTH_SHORT).show();

                String google_plus_id= account.getId().toString();
                Log.e("googlesss",google_plus_id);
                String mail_str=account.getEmail().toString();
                String f_name_str="";
                String l_name_str="";
                String gender="";
                String userImage="";
                String country_str="";
                String city_str="";
                String street_str="";
                String postal_code="";
                String country_code="";
                Facebook_Api facebook_api=new Facebook_Api();
                facebook_api.facebook_login(getApplicationContext(),google_plus_id,"G",mail_str,f_name_str,l_name_str,gender,userImage,country_str,city_str,street_str,postal_code,country_code);


            }
        }

        Log.e("onact",data+"");
        finish();
    }

}
