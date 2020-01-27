package app.com.food_ordering_app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.food_ordering_app.R;
import app.com.food_ordering_app.Web_Service.AddToCart_Api;
import app.com.food_ordering_app.activities.TakeOut_Menu;
import app.com.food_ordering_app.global.Global;

/**
 * Created by admin on 11/2/2017.
 */

public class AddonsAdapter extends BaseAdapter {
    Context con;
    LayoutInflater layoutInflater;
    ArrayList<HashMap<String,String>> arrayList_specificProductDetails;
    String[] arr;
    Boolean array_check[];
    ArrayList arrayList=new ArrayList();
    String user_id_get,restaurant_id,menu_id,product_id;
    public static   String qty_count="";
    RelativeLayout rel_layout_cart;
    public  String addon,product_price;
    TextView rel_item_txt,price_getCartScreen_tv;
    String Menu_Most_Adapter;





    public AddonsAdapter(Context con, ArrayList<HashMap<String, String>> category_ArrayLishHashMap, String user_id_get, String restaurant_id, String menu_id, String product_id,RelativeLayout rel_layout_cart,TextView rel_item_txt,TextView price_getCartScreen_tv,String product_price,String Menu_Most_Adapter) {
        this.con=con;
        this.user_id_get=user_id_get;
        this.restaurant_id=restaurant_id;
        this.menu_id=menu_id;
        this.product_id=product_id;
        this.rel_layout_cart=rel_layout_cart;
        this.arrayList_specificProductDetails=category_ArrayLishHashMap;
        this.rel_item_txt=rel_item_txt;
        this.price_getCartScreen_tv=price_getCartScreen_tv;
        this.product_price=product_price;
        this.Menu_Most_Adapter=Menu_Most_Adapter;
        layoutInflater=(LayoutInflater.from(con));

        array_check = new Boolean[arrayList_specificProductDetails.size()];
        for (int v=0;v<arrayList_specificProductDetails.size();v++)
        {
            array_check[v]=false;
        }

    }

    @Override
    public int getCount() {
        return arrayList_specificProductDetails.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    class  ViewHolder
    {
              TextView addons_heading;
              TextView addons_types;
              TextView addons_typesPrice;
              RelativeLayout rel_layout_addons_typesPrice;
              ImageView tick_sides_img;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
       final ViewHolder viewHolder;

        if (view==null)
        {


            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.addons_adapter,null);

            //------------------ids-----------------------------------------------------------------
            viewHolder.addons_heading= (TextView)view.findViewById(R.id.addons_heading);
            viewHolder.addons_types= (TextView)view.findViewById(R.id.addons_types);
            viewHolder.addons_typesPrice= (TextView)view.findViewById(R.id.addons_typesPrice);
            viewHolder.rel_layout_addons_typesPrice= (RelativeLayout) view.findViewById(R.id.rel_layout_addons_typesPrice);
            viewHolder.tick_sides_img = (ImageView) view.findViewById(R.id.tick_sides_img);





            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        //-------------------setting text to fields------------------------------------------------
        viewHolder.addons_heading.setText(arrayList_specificProductDetails.get(i).get("category_name"));
        viewHolder.addons_types.setText(arrayList_specificProductDetails.get(i).get("add_name"));
        viewHolder.addons_typesPrice.setText("$"+arrayList_specificProductDetails.get(i).get("add_price"));



        //-----------------------code to hide repeated addons heading to set invisible----------------------------------------

            //initially intialize category_name to check with comming category_name from array list------------
            String category_name = "";

            //------storing arrayList size to string array----------------------------
            arr = new String[arrayList_specificProductDetails.size()];


            //-----executing for loop for arrayList size times(EX:topping,drink,spicy i.e 3 times)--------------------
            for (int j = 0; j < arrayList_specificProductDetails.size(); j++) {


                //-----------------comparing 2 continous category_names comming from arrayList of addons heading---------------------------
                if (arrayList_specificProductDetails.get(i).get("category_name").equals(category_name)) {

                    arr[j] = "false";

                } else {

                    arr[j] = "true";

                    //------------storing executed category name to another category_name for next comparison--------------
                    category_name = arrayList_specificProductDetails.get(j).get("category_name");
                }
            }


            //-------setting visibilty of  addons heading-------------------------------------------------------------------------
               if(arr[i].equalsIgnoreCase("false"))
                 {

                    viewHolder.addons_heading.setVisibility(View.GONE);

                  }
                     else {

                       viewHolder.addons_heading.setVisibility(View.VISIBLE);



                     }


           //------------------------end of to hide repeated addons heading------------------------------------------






        //--------------------setting check button visibility acc to value------------------------------
            if (array_check[i])
            {
                viewHolder.tick_sides_img.setVisibility(View.VISIBLE);

            }
            else if (!array_check[i])
            {
               viewHolder.tick_sides_img.setVisibility(View.GONE);

            }





              //-----------rel_layout_Addons_Types_Price onClick Listener-----------------------
              viewHolder.rel_layout_addons_typesPrice.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {



    //--------------to reverse value of every click to show or remove check button----------------
    if (array_check[i]) {
        array_check[i] = false;

        //----remove values to this class array List---------------------------
        arrayList.remove(arrayList_specificProductDetails.get(i).get("id"));

    } else if (!array_check[i]) {
        array_check[i] = true;



        //----adding values to this class array List--------------()-------------
        arrayList.add(arrayList_specificProductDetails.get(i).get("id"));



    }

                             notifyDataSetChanged();
                  }


              });




              //----------storing arraylist data to string and removing braces----------------------
                addon=arrayList.toString().replace("[", "").replace("]", "");






              //------------add to cart onClick Listener(Moved from menu_adapter to here)------------------------------
       rel_layout_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


          //------------------Adding selected qty to global variable--------------------------
          int count =Integer.parseInt(MostPopular_Adapter.txt_qty_number.getText().toString());
                        Global.product_qty_Unique=Global.product_qty_Unique+count;


                       //--------dismissing dailog and showing cart view ---------------
                        MostPopular_Adapter.dialog.dismiss();
                            if(Global.product_qty_Unique==0)
                            {
                                TakeOut_Menu.cart_rel_takeout_menu.setVisibility(View.GONE);


                            }
                            else
                            {
                         TakeOut_Menu.cart_rel_takeout_menu.setVisibility(View.VISIBLE);

                        //----setting qty selected by user on get cart layout-------------
                         rel_item_txt.setVisibility(View.VISIBLE);
                         TakeOut_Menu.rel_item_txt_takeout_Menu.setText("Checkout("+Global.product_qty_Unique+")items");



                            }

                        //----------add to cart api--------------------------------------------------
                        String   qty_count = MostPopular_Adapter.txt_qty_number.getText().toString();
                        AddToCart_Api addToCart_api = new AddToCart_Api();
                        addToCart_api.addCart(con,user_id_get,restaurant_id,menu_id,Global.product_id_Unique,qty_count,addon);




                    }


                });





            return view;
       }
 }



































