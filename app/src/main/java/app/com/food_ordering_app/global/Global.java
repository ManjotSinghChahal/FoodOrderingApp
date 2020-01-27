package app.com.food_ordering_app.global;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.HashMap;

import app.com.food_ordering_app.R;

/**
 * Created by admin on 7/25/2017.
 */

public class Global extends MultiDexApplication {

    public static String product_id_Unique="";
    public static int  product_qty_Unique=0;
    public static double  product_price_Unique=0;
    public static  String  device_id="";
    public static String base_url="http://35.183.23.19/food/api/" ; //if change then change also update Profile url
    ArrayList<HashMap<String, String>> restaurantList = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> restaurantList_closed= new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> add_on= new ArrayList<HashMap<String, String>>();
   public ArrayList<HashMap<String, String>> menu_list= new ArrayList<HashMap<String, String>>();

  public static   ArrayList<HashMap<String, String>> restaurantList_both = new ArrayList<HashMap<String, String>>();


    //-------------------------takeout checkout screen product array list----------------------------------------
    public static ArrayList<HashMap<String, String>> product_ArrayList_TakeoutCheckout = new ArrayList<>();

    public static ArrayList<HashMap<String, String>> getProduct_ArrayList_TakeoutCheckout() {
        return product_ArrayList_TakeoutCheckout;
    }

    public static void setProduct_ArrayList_TakeoutCheckout(ArrayList<HashMap<String, String>> product_ArrayList_TakeoutCheckout) {
        Global.product_ArrayList_TakeoutCheckout = product_ArrayList_TakeoutCheckout;
    }

    //---------------------------Most popular array list of takeout menu screen---------------------------------
    public static  ArrayList<HashMap<String,String>> most_popular=new ArrayList<HashMap<String,String>>();

    public static ArrayList<HashMap<String, String>> getMost_popular() {
        return most_popular;
    }

    public static void setMost_popular(ArrayList<HashMap<String, String>> most_popular) {
        Global.most_popular = most_popular;
    }

    //-------------------Menu array list of takeout menu screen---------------------------------------
    public static ArrayList<HashMap<String,String>> getMenu=new ArrayList<>();

    public static ArrayList<HashMap<String, String>> getGetMenu() {
        return getMenu;
    }

    public static void setGetMenu(ArrayList<HashMap<String, String>> getMenu) {
        Global.getMenu = getMenu;
    }
    //------------------all menu list(Takeout screen)-----------------------------------------------------------
    public static ArrayList<HashMap<String,String>> arrayListHM_MP_menuList_full = new ArrayList<>();

    public static ArrayList<HashMap<String, String>> getArrayListHM_MP_menuList_full() {
        return arrayListHM_MP_menuList_full;
    }

    public static void setArrayListHM_MP_menuList_full(ArrayList<HashMap<String, String>> arrayListHM_MP_menuList_full) {
        Global.arrayListHM_MP_menuList_full = arrayListHM_MP_menuList_full;
    }

    //-------------------all menu products(Takeout screen)--------------------------------------------------------------
    public static ArrayList<HashMap<String,String>> arrayListHM_MP_menuList_full_products =new ArrayList<>();

    public static ArrayList<HashMap<String, String>> getArrayListHM_MP_menuList_full_products() {
        return arrayListHM_MP_menuList_full_products;
    }

    public static void setArrayListHM_MP_menuList_full_products(ArrayList<HashMap<String, String>> arrayListHM_MP_menuList_full_products) {
        Global.arrayListHM_MP_menuList_full_products = arrayListHM_MP_menuList_full_products;
    }

    //-------------------array of Menu list of takeout menu screen---------------------------------------
    public static ArrayList<HashMap<String,String>> getMenuList=new ArrayList<>();

    public static ArrayList<HashMap<String, String>> getGetMenuList() {
        return getMenuList;
    }

    public static void setGetMenuList(ArrayList<HashMap<String, String>> getMenuList) {
        Global.getMenuList = getMenuList;
    }


    //----------array list to store specific product details of menu screen---------------------
    public static  ArrayList<HashMap<String,String>> arrayList_productDetails = new ArrayList<HashMap<String, String>>();

    public static ArrayList<HashMap<String, String>> getArrayList_productDetails() {
        return arrayList_productDetails;
    }

    public static void setArrayList_productDetails(ArrayList<HashMap<String, String>> arrayList_productDetails) {
        Global.arrayList_productDetails = arrayList_productDetails;
    }

    //-------------------array of open Restaurant list of home screen screen---------------------------------------
    public static ArrayList<HashMap<String,String>> openedRestaurantList=new ArrayList<>();

    public static ArrayList<HashMap<String, String>> getOpenedRestaurantList() {
        return openedRestaurantList;
    }

    public static void setOpenedRestaurantList(ArrayList<HashMap<String, String>> openedRestaurantList) {
        Global.openedRestaurantList = openedRestaurantList;
    }

    //-----------------both lists------------------------------------------------------------------
    public static ArrayList<HashMap<String, String>> getRestaurantList_both() {
        return restaurantList_both;
    }

    public void setRestaurantList_both(ArrayList<HashMap<String, String>> restaurantList_both) {
        this.restaurantList_both = restaurantList_both;
    }
    //------------------------closed list---------------------------------------------------------

    public ArrayList<HashMap<String, String>> getRestaurantList_closed() {
        return restaurantList_closed;
    }

    public void setRestaurantList_closed(ArrayList<HashMap<String, String>> restaurantList_closed) {
        this.restaurantList_closed = restaurantList_closed;
    }
    //------------------------opened list---------------------------------------------------------

    public ArrayList<HashMap<String, String>> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(ArrayList<HashMap<String, String>> restaurantList) {
        this.restaurantList = restaurantList;
    }
    //--------------------------------------------------------------------------

    public static void changeFragment(Context context, Fragment fragment)
    {
        FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container,fragment).addToBackStack(null).commit();

    }

    public ArrayList<HashMap<String, String>> getAdd_on() {
        return add_on;
    }

    public void setAdd_on(ArrayList<HashMap<String, String>> add_on) {
        this.add_on = add_on;
    }


    //-----------------------0rderHistory_arrayList-----------------------------
    public  static  ArrayList<HashMap<String,String>> orderHistory_arrayList = new ArrayList<>();

    public static ArrayList<HashMap<String, String>> getOrderHistory_arrayList() {
        return orderHistory_arrayList;
    }

    public static void setOrderHistory_arrayList(ArrayList<HashMap<String, String>> orderHistory_arrayList) {
        Global.orderHistory_arrayList = orderHistory_arrayList;
    }

    public ArrayList<HashMap<String, String>> getMenu_list() {
        return menu_list;
    }

    public void setMenu_list(ArrayList<HashMap<String, String>> menu_list) {
        this.menu_list = menu_list;
    }
}
