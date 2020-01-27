package app.com.food_ordering_app.Web_Service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.food_ordering_app.activities.*;
import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 11/1/2017.
 */

public class GetCart_Api {
    Context con;
    String user_id_menuAdapter;
    HashMap<String,String> getCart_arrayList;
    ArrayList<HashMap<String,String>> getCart_arrayList_HashMap;
    String txt_qty_number;
    Global global;
    String comp_val;
    int    int_count;
    ProgressDialog pd;
   public static String restaurant_id,restaurant_name,restaurant_SubtotalPrice,restaurant_TaxCharges,restaurant_DeliveryFee;
    public static String restaurant_TotalPrice,restaurant_TipTen,restaurant_TipFifteen,restaurant_TipTwenty;

    public GetCart_Api(String txt_qty_number,String comp_val) {
        this.txt_qty_number=txt_qty_number;
        this.comp_val=comp_val;

    }

    public void getCartMethod(final Context con, String user_id_menuAdapter) {
        this.con=con;
        this.user_id_menuAdapter=user_id_menuAdapter;
        pd=new ProgressDialog(con);
        pd.setMessage("Loading ......");
        //--------used for null token and also add in manifest-----------------------------------
        pd.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        pd.show();


        Retrofit retrofit = new Retrofit.Builder()
                                     .baseUrl(Global.base_url)
                                     .addConverterFactory(GsonConverterFactory.create())
                                     .build();

        WebService webservice = retrofit.create(WebService.class);
        Call<GetCartValue> call = webservice.getCart(user_id_menuAdapter);
        call.enqueue(new Callback<GetCartValue>() {
            @Override
            public void onResponse(Call<GetCartValue> call, Response<GetCartValue> response) {

                pd.dismiss();
                if (response.isSuccessful())
                {
                    global=(Global)con.getApplicationContext();

                    String status = response.body().getStatus();

                    if (status.equalsIgnoreCase("1"))
                    {

                        //--------adding price value to global variable------------
                        String sub_TotalPrice = response.body().getResult().getSubtotalPrice();
                        Global.product_price_Unique=0;
                        Global.product_price_Unique=Double.parseDouble(sub_TotalPrice);



               //========================(If coming from menu then handle this way)==============================================

                        if (comp_val.equalsIgnoreCase("menu")) {


                             restaurant_id = response.body().getResult().getRestaurantId();
                             restaurant_name = response.body().getResult().getRestaurantName();



                             restaurant_SubtotalPrice = response.body().getResult().getSubtotalPrice();
                             restaurant_TaxCharges = response.body().getResult().getTaxCharges();
                             restaurant_DeliveryFee = response.body().getResult().getDeliveryFee();
                             restaurant_TotalPrice = response.body().getResult().getTotalPrice();
                             restaurant_TipTen = response.body().getResult().getTipTen();
                             restaurant_TipFifteen = response.body().getResult().getTipFifteen();
                             restaurant_TipTwenty = response.body().getResult().getTipTwenty();


                            //---------hashmap to store charges and tax--------------------------------
                            getCart_arrayList = new HashMap<String, String>();

                            getCart_arrayList.put("restaurant_SubtotalPrice", restaurant_SubtotalPrice);
                            getCart_arrayList.put("restaurant_TaxCharges", restaurant_TaxCharges);
                            getCart_arrayList.put("restaurant_DeliveryFee", restaurant_DeliveryFee);
                            getCart_arrayList.put("restaurant_TotalPrice", restaurant_TotalPrice);
                            getCart_arrayList.put("restaurant_TipTen", restaurant_TipTen);
                            getCart_arrayList.put("restaurant_TipFifteen", restaurant_TipFifteen);
                            getCart_arrayList.put("restaurant_TipTwenty", restaurant_TipTwenty);


                            //---------array list hashmap to store different products-----------------------
                            getCart_arrayList_HashMap = new ArrayList<HashMap<String, String>>();

                            for (int j = 0; j < response.body().getResult().getProducts().size(); j++) {
                                HashMap<String, String> getCart_HashMap = new HashMap<String, String>();

                                getCart_HashMap.put("product_id", response.body().getResult().getProducts().get(j).getProductId());
                                getCart_HashMap.put("product_name", response.body().getResult().getProducts().get(j).getProductName());
                                getCart_HashMap.put("product_price", response.body().getResult().getProducts().get(j).getProductPrice());
                                getCart_HashMap.put("quantity", response.body().getResult().getProducts().get(j).getQuantity());


                                getCart_arrayList_HashMap.add(getCart_HashMap);

                            }




                    //-----------------------------------------------------------
                            //----for additional working to calculate total------
                            //-------clearing global consonants to get new qty from get cart-----------------
                            Global.product_qty_Unique=0;


                            //-----------getting qty_values and adding them to show total----------------
                            for (int z=0;z<getCart_arrayList_HashMap.size();z++) {

                                //--------------qty addition-----------------------------------------------------
                                int_count = 0;
                                int_count = Integer.parseInt(getCart_arrayList_HashMap.get(z).get("quantity").toString());
                                Global.product_qty_Unique = Global.product_qty_Unique + int_count;

                            }


//--------------------------------------------------------------
                            //---------------clearing global array list------------------------------------------------
                            Global.getProduct_ArrayList_TakeoutCheckout().clear();

                            //-----------------------------setting product details to global array hashmap------------------------
                            Global.setProduct_ArrayList_TakeoutCheckout(getCart_arrayList_HashMap);


                            //---------------intent to pass charges and tax to next screen-----------------------------
                            Intent intent = new Intent(con, TakeOut_CheckOut.class);
                            intent.putExtra("restaurant_SubtotalPrice", getCart_arrayList.get("restaurant_SubtotalPrice"));
                            intent.putExtra("restaurant_TaxCharges", getCart_arrayList.get("restaurant_TaxCharges"));
                            intent.putExtra("restaurant_DeliveryFee", getCart_arrayList.get("restaurant_DeliveryFee"));
                            intent.putExtra("restaurant_TotalPrice", getCart_arrayList.get("restaurant_TotalPrice"));
                            intent.putExtra("restaurant_TipTen", getCart_arrayList.get("restaurant_TipTen"));
                            intent.putExtra("restaurant_TipFifteen", getCart_arrayList.get("restaurant_TipFifteen"));
                            intent.putExtra("restaurant_TipTwenty", getCart_arrayList.get("restaurant_TipTwenty"));
                            intent.putExtra("txt_qty_number", String.valueOf(txt_qty_number));
                            intent.putExtra("restaurant_id", restaurant_id);
                            intent.putExtra("restaurant_name", restaurant_name);
                           // intent.putExtra("restaurant_address", restaurant_address);

                          //  intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            con.startActivity(intent);


                        }


              //=================(If coming from home handle this way======================================================

                  //----------------comming from home screen----------------------------------------------
                        else  if (comp_val.equalsIgnoreCase("home"))
                        {

                            //---------array list hashmap to store different products-----------------------
                            getCart_arrayList_HashMap = new ArrayList<HashMap<String, String>>();

                            for (int j = 0; j < response.body().getResult().getProducts().size(); j++) {
                                HashMap<String, String> getCart_HashMap = new HashMap<String, String>();

                                getCart_HashMap.put("product_id", response.body().getResult().getProducts().get(j).getProductId());
                                getCart_HashMap.put("product_name", response.body().getResult().getProducts().get(j).getProductName());
                                getCart_HashMap.put("product_price", response.body().getResult().getProducts().get(j).getProductPrice());
                                getCart_HashMap.put("quantity", response.body().getResult().getProducts().get(j).getQuantity());

                                getCart_arrayList_HashMap.add(getCart_HashMap);



                            }

                           //-------clearing global consonants to get new qty from get cart-----------------
                            Global.product_qty_Unique=0;

                            //-----------getting qty_values,price_values and adding them to show total----------------
                            for (int z=0;z<getCart_arrayList_HashMap.size();z++)
                            {

                                //--------------adding qty and storing to global variable--------------------------------
                                int_count=0;
                                int_count = Integer.parseInt(getCart_arrayList_HashMap.get(z).get("quantity").toString());
                                Global.product_qty_Unique = Global.product_qty_Unique + int_count;

                                      }



                        }
               //================================================================================================================

                    }
                }


            }

            @Override
            public void onFailure(Call<GetCartValue> call, Throwable t) {
                Toast.makeText(con,  "Failure ", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }
}
