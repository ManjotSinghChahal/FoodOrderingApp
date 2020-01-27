package app.com.food_ordering_app.Web_Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by admin on 9/26/2017.
 */

public interface WebService {
    @FormUrlEncoded
    @POST("login")
        //method name(login)
    Call<LoginValue> login(@Field("email") String user, @Field("password") String pass);

    @FormUrlEncoded
    @POST("register")
    Call<RegisterValue> register(@Field("first_name") String first_name, @Field("last_name") String last_name, @Field("email") String email, @Field("password") String password, @Field("confirm_password") String confirm_password, @Field("country_code") String country_code, @Field("phone_number") String phone_number);

    @FormUrlEncoded
    @POST("profile")
    Call<ProfileValue> profile(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("update_user_lat_long")
    Call<UpdateLatLongValue> updateLatLog(@Field("user_id") String user_id, @Field("lat") String lat, @Field("long") String log);

    @FormUrlEncoded
    @POST("social_login")
    Call<FacebookValue> facebook_login(@Field("id") String id, @Field("login_type") String type, @Field("email") String a, @Field("first_name") String b, @Field("last_name") String c, @Field("gender") String d, @Field("user_image") String e, @Field("country") String g, @Field("city") String h, @Field("street") String i, @Field("postal_code") String j, @Field("country_code") String k);

    @FormUrlEncoded
    @POST("update_profile")
    Call<UpdateProfileValue> updateProfile(@Field("user_id") String res_sp, @Field("first_name") String name, @Field("last_name") String l_name, @Field("phone_number") String phone, @Field("street") String street_name, @Field("city") String city_name, @Field("country") String country_name, @Field("postal_code") String postal, @Field("work_country") String country_name_work, @Field("work_postal_code") String work_postal_code, @Field("work_city") String work_city, @Field("user_image") String user_image);


    @FormUrlEncoded
    @POST("restaurants_list")
    Call<RestaurantListingValue> restaurantList(@Field("user_id") String res_home, @Field("latitude") double lat_value, @Field("longitude") double lng_value);


    @FormUrlEncoded
    @POST("change_password")
    Call<ChangePasswordValue> changePassword(@Field("user_id") String s, @Field("old_password") String s1, @Field("new_password") String s2, @Field("confirm_password") String s3);


    @FormUrlEncoded
    @POST("forgot_password")
    Call<ForgotPasswordValue> forgotpassword(@Field("email") String email);


    @FormUrlEncoded
    @POST("restaurant_details")
    Call<RestaurantListingMenuValue> restaurantListMenu(@Field("user_id") String user_id_get, @Field("restaurant_id") String restaurantList, @Field("type") String t,@Field("count") String count);

    @FormUrlEncoded
    @POST("get_submenus")
    Call<ProductMenuValue> productMenu(@Field("user_id") String user_id_get, @Field("restaurant_id") String restaurant_id, @Field("menu_id") String userIdGet, @Field("type") String t);


    @FormUrlEncoded
    @POST("search_restaurant")
    Call<SearchRestaurantNameValue> search_restaurant(@Field("user_id") String user_id, @Field("restaurant_name") String restaurant_name);


    @FormUrlEncoded
    @POST("add_to_cart")
    Call<AddToCartValue> add_Cart(@Field("user_id") String user_id_get, @Field("restaurant_id") String restaurant_id, @Field("menu_id") String menu_id, @Field("product_id") String product_id, @Field("quantity") String s, @Field("addons") String addon);


    @FormUrlEncoded
    @POST("get_device_id")
    Call<UpdateDeviceIdValue> updateDevice_Id(@Field("user_id") String res_updateLatLog, @Field("device_id") String android_id,@Field("device_type") String device_type);


    @FormUrlEncoded
    @POST("get_cart")
    Call<GetCartValue> getCart(@Field("user_id") String user_id_menuAdapter);


    @FormUrlEncoded
    @POST("get_product_detail")
    Call<GetSpecificProductDetailsValue> getProductDetails(@Field("user_id") String user_id_get, @Field("restaurant_id") String restaurant_id, @Field("menu_id") String menu_id, @Field("product_id") String product_id);


    @FormUrlEncoded
    @POST("empty_cart")
    Call<EmptyCardValue> emptyCard(@Field("user_id") String user_id_cart_add, @Field("restaurant_id") String restaurant_id_cart_add);


    @FormUrlEncoded
    @POST("place_order")
    Call<PlaceOrderValue> placeOrder(@Field("user_id") String userId, @Field("restaurant_id") String restId, @Field("type") String type, @Field("delivery_address") String delivery_address, @Field("total_amount") String user_id, @Field("tip") String rest_id);


    @FormUrlEncoded
    @POST("order_history")
    Call<OrderHistoryValue> orderHistory(@Field("user_id") String user_id,@Field("page") int page);




    @FormUrlEncoded
    @POST("get_delivery_addresses")
    Call<DeliveryAddressValue> deliveryAddress(@Field("user_id") String user_id_del_loc);
}