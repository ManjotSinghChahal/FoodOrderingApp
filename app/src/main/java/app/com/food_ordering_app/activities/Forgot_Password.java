package app.com.food_ordering_app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.Web_Service.ForgotPassword_Api;

/**
 * Created by admin on 8/31/2017.
 */

public class Forgot_Password extends Activity {
    ImageView img_cross;
    EditText email;
    Button btn_signin;
    String email_get;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        init();
        work();


    }

    private void init() {
        img_cross=(ImageView)findViewById(R.id.img_cross);
        btn_signin=(Button)findViewById(R.id.btn_signin);
        email=(EditText)findViewById(R.id.forgot_email);
    }

    private void work() {
        //--------------------image_cross onClick listener------------------------------
        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //---------------------btn_sign in onClick listener------------------------------
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //-------------------forgot password validation-------------------------------------
                 email_get = email.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (email_get.length() == 0 )
                {
                    email.setError("Enter email ");
                }
                else  if (!email_get.matches(emailPattern))
                {
                    email.setError("Invalid email");
                }
                else
                {
                    ForgotPassword_Api forgotPassword_api=new ForgotPassword_Api();
                    forgotPassword_api.forgotPassword(getApplication(),email_get);


                }

                //-----------------------------------------------------------------------------------

            }
        });

    }
}

