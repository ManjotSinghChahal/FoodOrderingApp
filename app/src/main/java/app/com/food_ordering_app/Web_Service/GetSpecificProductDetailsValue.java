package app.com.food_ordering_app.Web_Service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 11/2/2017.
 */







public class GetSpecificProductDetailsValue {


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private ResultSpecific result;

    public ResultSpecific getResult() {
        return result;
    }

    public void setResult(ResultSpecific result) {
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




class ResultSpecific {


    @SerializedName("product_details")
    @Expose
    private ProductDetailsSpecific productDetails;

    public ProductDetailsSpecific getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetailsSpecific productDetails) {
        this.productDetails = productDetails;
    }
}





class ProductDetailsSpecific {


    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_price")
    @Expose
    private String productPrice;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("product_desc")
    @Expose
    private String productDesc;
    @SerializedName("addons")
    @Expose
    private List<Addon> addons = null;

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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public List<Addon> getAddons() {
        return addons;
    }

    public void setAddons(List<Addon> addons) {
        this.addons = addons;
    }

}


//------------------------------------------------------------------------------------------------

 class Addon {


     @SerializedName("category_name")
     @Expose
     private String categoryName;
     @SerializedName("category_id")
     @Expose
     private String categoryId;
     @SerializedName("ads")
     @Expose
     private List<Ad> ads = null;

     public String getCategoryName() {
         return categoryName;
     }

     public void setCategoryName(String categoryName) {
         this.categoryName = categoryName;
     }

     public String getCategoryId() {
         return categoryId;
     }

     public void setCategoryId(String categoryId) {
         this.categoryId = categoryId;
     }

     public List<Ad> getAds() {
         return ads;
     }

     public void setAds(List<Ad> ads) {
         this.ads = ads;
     }

 }




 class Ad {


     @SerializedName("id")
     @Expose
     private String id;
     @SerializedName("add_name")
     @Expose
     private String addName;
     @SerializedName("add_price")
     @Expose
     private String addPrice;
     @SerializedName("add_image")
     @Expose
     private String addImage;
     @SerializedName("add_desc")
     @Expose
     private String addDesc;

     public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }

     public String getAddName() {
         return addName;
     }

     public void setAddName(String addName) {
         this.addName = addName;
     }

     public String getAddPrice() {
         return addPrice;
     }

     public void setAddPrice(String addPrice) {
         this.addPrice = addPrice;
     }

     public String getAddImage() {
         return addImage;
     }

     public void setAddImage(String addImage) {
         this.addImage = addImage;
     }

     public String getAddDesc() {
         return addDesc;
     }

     public void setAddDesc(String addDesc) {
         this.addDesc = addDesc;
     }

 }


