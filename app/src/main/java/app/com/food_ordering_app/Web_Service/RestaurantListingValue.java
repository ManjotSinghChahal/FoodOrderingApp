package app.com.food_ordering_app.Web_Service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 10/12/2017.
 */

public class RestaurantListingValue {




    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private ResultH resultH;

    public ResultH getResultH() {
        return resultH;
    }

    public void setResultH(ResultH resultH) {
        this.resultH = resultH;
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


 class OpenResturant {

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

    public void setStatus(String status) {
        this.status = status;
    }

}


class Object {

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

    public void setStatus(String status) {
        this.status = status;
    }

}


 class ResultH {

    @SerializedName("open_resturant")
    @Expose
    private List<OpenResturant> openResturant = null;
    @SerializedName("closed_resturant")
    @Expose
    private List<Object> closedResturant = null;

    public List<OpenResturant> getOpenResturant() {
        return openResturant;
    }

    public void setOpenResturant(List<OpenResturant> openResturant) {
        this.openResturant = openResturant;
    }

    public List<Object> getClosedResturant() {
        return closedResturant;
    }

    public void setClosedResturant(List<Object> closedResturant) {
        this.closedResturant = closedResturant;
    }
//--------------------------------------------------------------------------------------------------------------



     //--------------------------------------------------
}
