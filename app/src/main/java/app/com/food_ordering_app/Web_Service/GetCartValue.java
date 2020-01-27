package app.com.food_ordering_app.Web_Service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 11/1/2017.
 */

public class GetCartValue {




    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private ResultGetCart result;

    public ResultGetCart getResult() {
        return result;
    }

    public void setResult(ResultGetCart result) {
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


 class Product {

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("addons")
    @Expose
    private List<Object> addons = null;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public List<Object> getAddons() {
        return addons;
    }

    public void setAddons(List<Object> addons) {
        this.addons = addons;
    }

}


 class ResultGetCart {

    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("restaurant_name")
    @Expose
    private String restaurantName;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("subtotal_price")
    @Expose
    private String subtotalPrice;
    @SerializedName("tax_charges")
    @Expose
    private String taxCharges;
    @SerializedName("delivery_fee")
    @Expose
    private String deliveryFee;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("tip_ten")
    @Expose
    private String tipTen;
    @SerializedName("tip_fifteen")
    @Expose
    private String tipFifteen;
    @SerializedName("tip_twenty")
    @Expose
    private String tipTwenty;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getSubtotalPrice() {
        return subtotalPrice;
    }

    public void setSubtotalPrice(String subtotalPrice) {
        this.subtotalPrice = subtotalPrice;
    }

    public String getTaxCharges() {
        return taxCharges;
    }

    public void setTaxCharges(String taxCharges) {
        this.taxCharges = taxCharges;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTipTen() {
        return tipTen;
    }

    public void setTipTen(String tipTen) {
        this.tipTen = tipTen;
    }

    public String getTipFifteen() {
        return tipFifteen;
    }

    public void setTipFifteen(String tipFifteen) {
        this.tipFifteen = tipFifteen;
    }

    public String getTipTwenty() {
        return tipTwenty;
    }

    public void setTipTwenty(String tipTwenty) {
        this.tipTwenty = tipTwenty;
    }

}

