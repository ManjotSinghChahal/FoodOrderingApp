package app.com.food_ordering_app.Web_Service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;



import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import app.com.food_ordering_app.fragment.Profile;
import app.com.food_ordering_app.global.Global;

/**
 * Created by admin on 7/28/2017.
 */

public class ImageUploadRegister {
    private URL connectURL;
    private String response;
    public static Context c;


    Context context;
    String aa[] = {"user_image"}; //---key of user_image of hmtl---
    String user_image="";
    String user_id, name, lname, mobile, street_name,province_name, city_name, country_name, postal, city_name_work, country_name_work, postal_name_work,street_name_work;


    public ImageUploadRegister(Context c, String user_id, String name, String l_name, String mobile, String street_name, String city_name, String country_name, String postal, String work_country, String postal_name_work, String city_name_work, String user_image,String street_name_work,String province_name)

    {

        {


            try {

                String url = "http://35.183.23.19/food/api/update_profile";

                connectURL = new URL(url);

            } catch (Exception ex) {
                Log.i("URL FORMATION", "MALFORMATED URL");
            }

            this.context = c;
            this.user_id = user_id;
            this.name = name;
            this.lname = l_name;
            this.mobile = mobile;
            this.street_name = street_name;
            this.city_name = city_name;
            this.country_name = country_name;
            this.postal = postal;

            this.country_name_work = work_country;
            this.postal_name_work = postal_name_work;
            this.city_name_work = city_name_work;
            this.user_image = user_image;
            this.street_name_work = street_name_work;
            this.province_name = province_name;


        }

    }



    public String doStart() {

        return thirdTry();

    }

    FileInputStream fileInputStream = null;

    public String thirdTry() {
        String exsistingFileName = "";
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        String Tag = "3rd";




        try {
            HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();



            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            //------user_id
            dos.writeBytes("Content-Disposition: form-data;  name=\"user_id\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(user_id + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);



            //------first_name
            dos.writeBytes("Content-Disposition: form-data;  name=\"first_name\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(name + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);


            //------last_name
            dos.writeBytes("Content-Disposition: form-data; name=\"last_name\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(lname + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);


            //------mobile
            dos.writeBytes("Content-Disposition: form-data; name=\"phone_number\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(mobile + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);


            //-------street_name
            dos.writeBytes("Content-Disposition: form-data; name=\"street\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(street_name + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            //------city
            dos.writeBytes("Content-Disposition: form-data; name=\"city\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(city_name + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            //------province
            dos.writeBytes("Content-Disposition: form-data; name=\"province\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(province_name + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            //------country_name
            dos.writeBytes("Content-Disposition: form-data; name=\"country\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(country_name + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            //------postal
            dos.writeBytes("Content-Disposition: form-data; name=\"postal_code\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(postal + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            //------work_country
            dos.writeBytes("Content-Disposition: form-data; name=\"work_country\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(country_name_work + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            //------work_city
            dos.writeBytes("Content-Disposition: form-data; name=\"work_city\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(city_name_work + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            //------work_postal_code
            dos.writeBytes("Content-Disposition: form-data; name=\"work_postal_code\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(postal_name_work + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            //------work_street
            dos.writeBytes("Content-Disposition: form-data; name=\"work_street\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(street_name_work + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);


            //-------------Auth_Key
            dos.writeBytes("Content-Disposition: form-data; name=\"auth_key\"" + lineEnd);
            dos.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes("123" + lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);



            if (!user_image.equalsIgnoreCase("")) {

                Log.e("images", user_image);
                File f = new File(user_image);
                exsistingFileName = f.getName();
                Log.e("exsistingFileName", exsistingFileName);
                fileInputStream = new FileInputStream(f);


                dos.writeBytes("Content-Disposition: form-data; name=\"" + aa[0] + "\";filename=\"" + exsistingFileName + "\"" + lineEnd);
                if (exsistingFileName.endsWith(".jpg")) {
                    Log.i("imagetype", "1");
                    dos.writeBytes("Content-type: image/jpg;" + lineEnd);
                }
                if (exsistingFileName.endsWith(".png")) {
                    Log.i("imagetype", "2");
                    dos.writeBytes("Content-type: image/png;" + lineEnd);
                }
                if (exsistingFileName.endsWith(".gif")) {
                    Log.i("imagetype", "3");
                    dos.writeBytes("Content-type: image/gif;" + lineEnd);
                }
                if (exsistingFileName.endsWith(".jpeg")) {
                    Log.i("imagetype", "4");
                    dos.writeBytes("Content-type: image/jpeg;" + lineEnd);
                }
                if (exsistingFileName.endsWith(".mp4")) {
                    Log.i("videotype", "1");
                    dos.writeBytes("Content-type: video/mp4;" + lineEnd);
                }
                if (exsistingFileName.endsWith(".avi")) {
                    Log.i("videotype", "2");
                    dos.writeBytes("Content-type: video/avi;" + lineEnd);
                }
                if (exsistingFileName.endsWith(".ogg")) {
                    Log.i("videotype", "3");
                    dos.writeBytes("Content-type: video/ogg;" + lineEnd);
                }
                if (exsistingFileName.endsWith(".3gp")) {
                    Log.i("videotype", "4");
                    dos.writeBytes("Content-type: video/3gp;" + lineEnd);
                }
                dos.writeBytes(lineEnd);


                int bytesAvailable = fileInputStream.available();
                int maxBufferSize = 1024 * 1024;
                int bufferSize = Math.min(bytesAvailable, maxBufferSize);
                byte[] buffer = new byte[bufferSize];

                int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);







            dos.flush();
            dos.close();
        }


            Log.e(Tag, "File is written");


            InputStream is = conn.getInputStream();
            Log.e("url",conn.getURL().toString());
            Log.e(Tag, " "+conn.getErrorStream());
            int ch;

            StringBuffer b = new StringBuffer();
            while ((ch = is.read()) != -1) {
                b.append((char) ch);
            }
            String s = b.toString();

            Log.e("Resp ImageUpload webS",s);
            try {
                //  pd.dismiss();
                JSONObject object = new JSONObject(s);
                String result = object.getString("result");
                JSONObject resultt = new JSONObject(result);
                String status = resultt.getString("status");


                if (status.equalsIgnoreCase("0")) {
                    String message = resultt.getString("message");
                    // Toast.makeText(c, message, Toast.LENGTH_SHORT).show();

                    response = resultt.getString("message");

                    if(message.equalsIgnoreCase("Already Registered but need to verified"))
                    {
                        Intent intent=new Intent(c, Profile.class);
                        intent.putExtra("id",resultt.getString("id"));
                        c.startActivity(intent);
                        ((Activity)c).finish();
                    }

                } else {
                    response = "true";
                    String message = resultt.getString("message");
//                    Toast.makeText(c, message, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(c, Profile.class);
                    intent.putExtra("id",resultt.getString("id"));
                    c.startActivity(intent);
                    ((Activity)c).finish();


                    //----------------------For sorting alphabetically--------------------------------------------------

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            dos.close();

			/*SharedPreferences sp = ctx.getSharedPreferences(GlobalConstant.Shared_pref, Context.MODE_PRIVATE);
		     Editor ed = sp.edit();
		     ed.putString("sh_Image", filename);*/
            //  ed.commit();

             } catch (MalformedURLException ex) {
            Log.e(Tag, "error: " + ex.getMessage(), ex);
        }

        catch (IOException ioe) {
            Log.e(Tag, "error: " + ioe.getMessage(), ioe);
        }
        return response;
    }

}
