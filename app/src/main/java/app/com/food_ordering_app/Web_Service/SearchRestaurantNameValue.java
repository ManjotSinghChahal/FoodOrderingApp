package app.com.food_ordering_app.Web_Service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 10/24/2017.
 */

public class SearchRestaurantNameValue {





    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private ResultSearchRestaurant result;

    public ResultSearchRestaurant getResult() {
        return result;
    }

    public void setResult(ResultSearchRestaurant result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    }




 class OpenRestaurant {

    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("restaurant_name")
    @Expose
    private String restaurantName;
    @SerializedName("restaurant_address")
    @Expose
    private String restaurantAddress;
    @SerializedName("restaurant_image")
    @Expose
    private String restaurantImage;
    @SerializedName("open_time")
    @Expose
    private String openTime;
    @SerializedName("closed_time")
    @Expose
    private String closedTime;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("status")
    @Expose
    private String status;

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getClosedTime() {
        return closedTime;
    }

    public void setClosedTime(String closedTime) {
        this.closedTime = closedTime;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}


 class ResultSearchRestaurant {

    @SerializedName("open_restaurants")
    @Expose
    private List<OpenRestaurant> openRestaurants = null;

    public List<OpenRestaurant> getOpenRestaurants() {
        return openRestaurants;
    }

    public void setOpenRestaurants(List<OpenRestaurant> openRestaurants) {
        this.openRestaurants = openRestaurants;
    }

}


























   /* @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private ResultResturent result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultResturent getResult() {
        return result;
    }

    public void setResult(ResultResturent result) {
        this.result = result;
    }

}


 class OpenRestaurant {

    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("restaurant_name")
    @Expose
    private String restaurantName;
    @SerializedName("restaurant_address")
    @Expose
    private String restaurantAddress;
    @SerializedName("restaurant_image")
    @Expose
    private String restaurantImage;
    @SerializedName("open_time")
    @Expose
    private String openTime;
    @SerializedName("closed_time")
    @Expose
    private String closedTime;
    @SerializedName("status")
    @Expose
    private String status;

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getClosedTime() {
        return closedTime;
    }

    public void setClosedTime(String closedTime) {
        this.closedTime = closedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatusR(String status) {
        this.status = status;
    }

}


class ResultResturent {

    @SerializedName("open_restaurants")
    @Expose
    private List<OpenRestaurant> openRestaurants = null;

    public List<OpenRestaurant> getOpenRestaurants() {
        return openRestaurants;
    }

    public void setOpenRestaurants(List<OpenRestaurant> openRestaurants) {
        this.openRestaurants = openRestaurants;
    }


}
*/
/*
 private String status;
 private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}*/
