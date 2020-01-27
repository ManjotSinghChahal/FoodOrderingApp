package app.com.food_ordering_app.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.Web_Service.RestaurantListingMenu_Api;
import app.com.food_ordering_app.global.Global;
import app.com.food_ordering_app.utility.Helper;

/**
 * Created by admin on 7/29/2017.
 */

public class Menu_Item_Takeout_Adapter extends BaseAdapter {
    Context context;

    String check="";
    ArrayList<HashMap<String, String>> subItem_list;
    ArrayList<HashMap<String, String>> subItem_list_temp = new ArrayList<>();
    ArrayList<HashMap<String, String>> arrayListsHashMap_temp = new ArrayList<>();
    ArrayList<HashMap<String, String>> arrayListsHashMap_mostPopularAdpt = new ArrayList<>();
    ArrayList<HashMap<String, String>> arrayListsHashMap_temp2 = new ArrayList<>();

    LayoutInflater inflater;
    String restaurant_id, userId_get;
    public static ProgressDialog pd_menu_Takeout_Adapter;
    TextView rel_item_txt_MoastPopular, rel_item_txt_takeout_Menu, price_getCartScreen_takeout_Menu;
    RelativeLayout cart_rel_takeout_menu;
    Global global;
    //------------show header text starting-------------------------------
    String txt_mostpopular;

    public Menu_Item_Takeout_Adapter(Context context, ArrayList<HashMap<String, String>> subItem_list, String userId_get, String restaurant_id_get, TextView rel_item_txt_MoastPopular, RelativeLayout cart_rel_takeout_menu, TextView rel_item_txt_takeout_Menu, TextView price_getCartScreen_takeout_Menu) {
        this.context = context;
        global = (Global) context.getApplicationContext();
        this.subItem_list = subItem_list;
        inflater = LayoutInflater.from(context);
        this.userId_get = userId_get;
        this.restaurant_id = restaurant_id_get;
        this.rel_item_txt_MoastPopular = rel_item_txt_MoastPopular;
        this.cart_rel_takeout_menu = cart_rel_takeout_menu;
        this.rel_item_txt_takeout_Menu = rel_item_txt_takeout_Menu;
        this.price_getCartScreen_takeout_Menu = price_getCartScreen_takeout_Menu;
        check="";
        Log.e("inside"," takeout adapter");
    }

    @Override
    public int getCount() {
        return subItem_list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder {
        TextView txt_snacks,txt_menu_snacks_count;
        LinearLayout linr_snavks;

    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.menu_takeout_adapter_view, null);

            //-----------ids-----------------------------------------------------
            holder.linr_snavks = (LinearLayout) view.findViewById(R.id.linr_snavkss);
            holder.txt_snacks = (TextView) view.findViewById(R.id.txt_menu_snacks);
            holder.txt_menu_snacks_count = (TextView) view.findViewById(R.id.txt_menu_snacks_count);


            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }



        //------------------setting menu list to text view in list view---------------
        if (subItem_list.get(i).get("menu_name").toString() != null)
        {
            holder.txt_snacks.setText(subItem_list.get(i).get("menu_name").toString());

        }


        if (subItem_list.get(i).get("menu_count").toString() !=null)
        {
            holder.txt_menu_snacks_count.setText(subItem_list.get(i).get("menu_count").toString());
        }



       //----------------------getting value for textView popular------------------
        txt_mostpopular=rel_item_txt_MoastPopular.getText().toString().trim();


        //---------------(Menu)listview onClick Listener-------------------------

        holder.linr_snavks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String count_get = subItem_list.get(i).get("menu_count")+"";

                if (!count_get.equalsIgnoreCase("0"))

                {




                //------------progress dialog box code-------------
                  /*  pd_menu_Takeout_Adapter = new ProgressDialog(context);
                    pd_menu_Takeout_Adapter.setMessage("Loading....");
                    pd_menu_Takeout_Adapter.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    pd_menu_Takeout_Adapter.show();*/


                //----------------------------------------------------------------
                String menu_id = subItem_list.get(i).get("menu_id");
                String menu_name = subItem_list.get(i).get("menu_name");
                String menu_count = subItem_list.get(i).get("menu_count");

                //---------------Setting whole product to menu list------------------
                arrayListsHashMap_temp.clear();
                arrayListsHashMap_temp.addAll(Global.getArrayListHM_MP_menuList_full_products());
                arrayListsHashMap_temp2.clear();


          //-------------------checks clicked one if most popular then adpt set----------------
         if(menu_name.equalsIgnoreCase("Most Popular"))
         {
           MostPopular_Adapter mostPopular_adapter = new MostPopular_Adapter(context, Global.getMost_popular(), userId_get, restaurant_id,rel_item_txt_MoastPopular);
           RestaurantListingMenu_Api.list_popular.setAdapter(mostPopular_adapter);
             Helper.getListViewSize(RestaurantListingMenu_Api.list_popular);
         }
       else
           {
           for (int k = 0; k < arrayListsHashMap_temp.size(); k++) {
               if (arrayListsHashMap_temp.get(k).get("menu_id").equalsIgnoreCase(menu_id))

               {
                   arrayListsHashMap_temp2.add(arrayListsHashMap_temp.get(k));
               }
           }
           //======================================================================

               Log.e("get_List_here22",subItem_list+"");

          //=========================================================================


           MostPopular_Adapter mostPopular_adapter = new MostPopular_Adapter(context, arrayListsHashMap_temp2, userId_get, restaurant_id,rel_item_txt_MoastPopular);
           RestaurantListingMenu_Api.list_popular.setAdapter(mostPopular_adapter);
           Helper.getListViewSize(RestaurantListingMenu_Api.list_popular);
       }




      //--------------checks most popular listview if most popular then again sets adpt-------------
      if (holder.txt_snacks.getText().toString().equalsIgnoreCase("Most Popular"))

        {
            check="";



                    Menu_Item_Takeout_Adapter menu_item_takeout_adapter = new Menu_Item_Takeout_Adapter(context, global.getMenu_list(), userId_get, restaurant_id, rel_item_txt_MoastPopular, cart_rel_takeout_menu, rel_item_txt_takeout_Menu, price_getCartScreen_takeout_Menu);
                    RestaurantListingMenu_Api.list_menu_takeOut.setAdapter(menu_item_takeout_adapter);

                    MostPopular_Adapter mostPopular_adapter = new MostPopular_Adapter(context, Global.getMost_popular(), userId_get, restaurant_id,rel_item_txt_MoastPopular);
                    RestaurantListingMenu_Api.list_popular.setAdapter(mostPopular_adapter);
                    Helper.getListViewSize(RestaurantListingMenu_Api.list_popular);


                }

     else{

            String menu_index = subItem_list.get(i).get("menu_index");

                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("menu_index",menu_index);
                hashMap.put("menu_id",menu_id);
                hashMap.put("menu_name",menu_name);
                hashMap.put("menu_count",menu_count);

                subItem_list.add(0,hashMap);


             Log.e("here_is_old",subItem_list+"");

                Set<HashMap<String, String>> set = new LinkedHashSet<>();
                set.addAll(subItem_list);

                subItem_list.clear();
                subItem_list.addAll(set);

        /*  for (int c = 0; c < (subItem_list.size() - 1); c++) {
              for (int d = 0; d < (subItem_list.size() - c - 1); d++) {

                  if (Integer.parseInt(subItem_list.get(d).get("menu_index")) > Integer
                          .parseInt(subItem_list.get(d + 1).get("menu_index"))) {

                      HashMap<String, String> temporary = subItem_list.get(d);
                      subItem_list.set(d, subItem_list.get(d + 1));
                      subItem_list.set(d + 1, temporary);

                  }
              }
          }

                Log.e("here_is_new",subItem_list+"");*/

               check="yes";
         }
                notifyDataSetChanged();

            }
            }
        });



        if(check.equalsIgnoreCase("yes"))
        {
            if(i==0)
            {
                holder.linr_snavks.setVisibility(View.VISIBLE);
                holder.txt_snacks.setText("Most Popular");
                holder.txt_menu_snacks_count.setText(RestaurantListingMenu_Api.most_Popular_Count+"");

            }
            else
            {
                holder.linr_snavks.setVisibility(View.VISIBLE);
            }

        }

        return view;
    }
}
