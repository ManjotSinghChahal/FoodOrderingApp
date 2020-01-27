package app.com.food_ordering_app.Web_Service;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.HashMap;

/*
 * Created by admin on 7/28/2017.

*/

public class Register_with_image_api {

    Context context;
    ProgressDialog pdia;
    public void sendRegisterPostRequest(final Context context, final String user_id, final String name, final String l_name, final String mobile, final String street_name, final String city_name, final String country_name, final String postal, final String work_country, final String postal_name_work, final String city_name_work, final String user_image,final  String street_name_work,final String province_name)
    {


        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            protected void onPreExecute() {
                super.onPreExecute();

            //    pdia = ProgressDialog.show(context, "Saving Profile...", "Please Wait.");
            //    pdia = new ProgressDialog(context);
             //   pdia.setMessage("Please wait...");
           //     pdia.show();
              // progress_lay.setVisibility(View.VISIBLE);
            }

            @Override
            protected String doInBackground(String... params) {



                ImageUploadRegister addMenuWebService = new ImageUploadRegister(context,user_id,name,l_name,mobile,street_name,city_name,country_name,postal,work_country,postal_name_work,city_name_work,user_image,street_name_work,province_name);
                String result = addMenuWebService.doStart();


                return result;

            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
//                Log.e("new",result);
          //     pdia.dismiss();
            //    progress_lay.setVisibility(View.GONE);

                try {




                    if (result.equalsIgnoreCase("true")) {



                    } else {
                        // pdia.dismiss();
                        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



            }

        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute();

    }
}

