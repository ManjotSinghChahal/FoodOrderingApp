package app.com.food_ordering_app.Web_Service;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.food_ordering_app.adapter.History_Adapter;
import app.com.food_ordering_app.global.Global;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 12/5/2017.
 */

public class Order_History_Api {
    Context con;
    String user_id;
    int page;
    ListView list_history;
    ArrayList<HashMap<String,String>> arrayList_orderHistory = new ArrayList<>();
    ArrayList<HashMap<String,String>> arrayList_orderHistory_temp = new ArrayList<>();
    public void OrderHistory(FragmentActivity activity, String user_id, int page, final ListView list_history) {
        this.con=activity;
        this.user_id=user_id;
        this.page=page;
        this.list_history=list_history;


        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(Global.base_url)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();


        WebService webService = retrofit.create(WebService.class);
        Call<OrderHistoryValue> call =  webService.orderHistory(user_id,page);
        call.enqueue(new Callback<OrderHistoryValue>() {
            @Override
            public void onResponse(Call<OrderHistoryValue> call, Response<OrderHistoryValue> response) {

                if (response.isSuccessful())
                {

                    String status = response.body().getStatus();
                    if (status.equalsIgnoreCase("1"))
                    {

                    String message = response.body().getMessage();
                    String currentPage= response.body().getCurrentPage().toString();
                    String totalPage= response.body().getTotalPage().toString();

                    for (int i=0;i< response.body().getResult().size();i++)
                    {

                     String order_id = response.body().getResult().get(i).getOrderId();
                     String rest_id = response.body().getResult().get(i).getRestaurantId();
                     String rest_name = response.body().getResult().get(i).getRestaurantName();
                     String user_id = response.body().getResult().get(i).getUserId();
                     String user_name = response.body().getResult().get(i).getUserName();
                     String total_amount = response.body().getResult().get(i).getTotalAmount();
                     String del_address = response.body().getResult().get(i).getDeliveryAddress();
                     String order_status = response.body().getResult().get(i).getOrderStatus();
                     String del_state = response.body().getResult().get(i).getOrderState();
                     String date = response.body().getResult().get(i).getDate();

                     for (int j=0;j<response.body().getResult().get(i).getProducts().size();j++)
                     {
                       HashMap<String,String> orderHis_HashMap = new HashMap<>();

                       String product_name = response.body().getResult().get(i).getProducts().get(j).getProductName();
                       String qty = response.body().getResult().get(i).getProducts().get(j).getQuantity();

                       orderHis_HashMap.put("currentPage",currentPage);
                       orderHis_HashMap.put("totalPage",totalPage);
                       orderHis_HashMap.put("order_id",order_id);
                       orderHis_HashMap.put("rest_id",rest_id);
                       orderHis_HashMap.put("rest_name",rest_name);
                       orderHis_HashMap.put("user_id",user_id);
                       orderHis_HashMap.put("user_name",user_name);
                       orderHis_HashMap.put("total_amount",total_amount);
                       orderHis_HashMap.put("del_address",del_address);
                       orderHis_HashMap.put("order_status",order_status);
                       orderHis_HashMap.put("del_state",del_state);
                       orderHis_HashMap.put("date",date);
                       orderHis_HashMap.put("product_name",product_name);
                       orderHis_HashMap.put("qty",qty);

                       arrayList_orderHistory.add(orderHis_HashMap);

                     }


                    }

                       //-------------setting and clearing list---------------------------------
                        arrayList_orderHistory_temp.clear();
                        arrayList_orderHistory_temp.addAll(arrayList_orderHistory);
                        arrayList_orderHistory_temp.addAll(Global.getOrderHistory_arrayList());

                        Global.getOrderHistory_arrayList().clear();
                        Global.setOrderHistory_arrayList(arrayList_orderHistory_temp);



                        History_Adapter  adapter=new History_Adapter(con,Global.getOrderHistory_arrayList());
                        list_history.setAdapter(adapter);

                    }

                    else
                    {
                        Global.getOrderHistory_arrayList().clear();
                    }
                }

            }

            @Override
            public void onFailure(Call<OrderHistoryValue> call, Throwable t) {
                Toast.makeText(con, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
