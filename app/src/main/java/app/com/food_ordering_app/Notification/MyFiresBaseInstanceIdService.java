package app.com.food_ordering_app.Notification;

import android.content.Context;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import app.com.food_ordering_app.global.Global;

/**
 * Created by admin on 11/28/2017.
 */

public class MyFiresBaseInstanceIdService extends FirebaseInstanceIdService {
    Context context;

    public static String device_id="";
    private static final String TAG = "MyFirebaseIIDService";
    public MyFiresBaseInstanceIdService(Context context )
    {
        this.context=context;
        onTokenRefresh();
    }
    public MyFiresBaseInstanceIdService()
    {

    }

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("id_get_Token", "Refreshed token: " + refreshedToken);
        Log.e("gfgf","eqwefd");

        Global.device_id=refreshedToken;
        //  Refreshed token:   ddsqpZAruyI:APA91bFpHjUFQi9wPOlfpXj8euSM1zeJQEGK-MsXrzrSf5rtnMEGC3bZ39yD0wxINGCHP8d4PZXMfESA6PZiNFVCzmffz95lNwLr79CIfih-EmM_vpMDgvV2EoQysPPs4Xb7aJ5Bk8N2
        //  Refreshed token:  ddsqpZAruyI:APA91bFpHjUFQi9wPOlfpXj8euSM1zeJQEGK-MsXrzrSf5rtnMEGC3bZ39yD0wxINGCHP8d4PZXMfESA6PZiNFVCzmffz95lNwLr79CIfih-EmM_vpMDgvV2EoQysPPs4Xb7aJ5Bk8N2
        // google api  AIzaSyBIAPgS8aJVYO0swPeLFUgd8yVxzunzi-k
        // firebase app--FoodOrderingApp
        // gmail vllogics2(given by sonal sir)


        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {

    }
















}
