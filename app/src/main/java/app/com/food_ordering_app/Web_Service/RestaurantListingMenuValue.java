package app.com.food_ordering_app.Web_Service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 10/17/2017.
 */

public class RestaurantListingMenuValue {





    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private ResultRestMenu result;

    public ResultRestMenu getResult() {
        return result;
    }

    public void setResult(ResultRestMenu result) {
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


 class Menu {

    @SerializedName("index_no")
    @Expose
    private String indexNo;
    @SerializedName("menu_name")
    @Expose
    private String menuName;
    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("menu_id")
    @Expose
    private String menuId;
    @SerializedName("products")
    @Expose
    private List<ProductMenu> products = null;

     public List<ProductMenu> getProducts() {
         return products;
     }

     public void setProducts(List<ProductMenu> products) {
         this.products = products;
     }

     public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }



}


 class MostPopular {

    @SerializedName("index_no")
    @Expose
    private String indexNo;
    @SerializedName("menu_name")
    @Expose
    private String menuName;
    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("menu_id")
    @Expose
    private String menuId;
    @SerializedName("products")
    @Expose
    private List<ProductMP> products = null;

     public List<ProductMP> getProducts() {
         return products;
     }

     public void setProducts(List<ProductMP> products) {
         this.products = products;
     }

     public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }



}


 class ProductMP {

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
    @SerializedName("popular_status")
    @Expose
    private String popularStatus;

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

    public String getPopularStatus() {
        return popularStatus;
    }

    public void setPopularStatus(String popularStatus) {
        this.popularStatus = popularStatus;
    }

}


 class ProductMenu {

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
    @SerializedName("popular_status")
    @Expose
    private String popularStatus;

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

    public String getPopularStatus() {
        return popularStatus;
    }

    public void setPopularStatus(String popularStatus) {
        this.popularStatus = popularStatus;
    }

}


 class RestaurantDetails {

    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("restaurant_name")
    @Expose
    private String restaurantName;
    @SerializedName("restauran_address")
    @Expose
    private String restauranAddress;
    @SerializedName("restaurant_image")
    @Expose
    private String restaurantImage;

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

    public String getRestauranAddress() {
        return restauranAddress;
    }

    public void setRestauranAddress(String restauranAddress) {
        this.restauranAddress = restauranAddress;
    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

}


 class ResultRestMenu {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("restaurant_details")
    @Expose
    private RestaurantDetails restaurantDetails;
    @SerializedName("most_popular")
    @Expose
    private List<MostPopular> mostPopular = null;
    @SerializedName("menus")
    @Expose
    private List<Menu> menus = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RestaurantDetails getRestaurantDetails() {
        return restaurantDetails;
    }

    public void setRestaurantDetails(RestaurantDetails restaurantDetails) {
        this.restaurantDetails = restaurantDetails;
    }

    public List<MostPopular> getMostPopular() {
        return mostPopular;
    }

    public void setMostPopular(List<MostPopular> mostPopular) {
        this.mostPopular = mostPopular;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

}


















/*

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private ResultRestMenu result;

    public ResultRestMenu getResult() {
        return result;
    }

    public void setResult(ResultRestMenu result) {
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


 class Menu {

    @SerializedName("index_no")
    @Expose
    private String indexNo;
    @SerializedName("menu_name")
    @Expose
    private String menuName;
    @SerializedName("menu_id")
    @Expose
    private String menuId;
    @SerializedName("products")
    @Expose
    private List<ProductMenu> products = null;

     public List<ProductMenu> getProducts() {
         return products;
     }

     public void setProducts(List<ProductMenu> products) {
         this.products = products;
     }

     public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }



}

 class MostPopular {

    @SerializedName("index_no")
    @Expose
    private String indexNo;
    @SerializedName("menu_name")
    @Expose
    private String menuName;
    @SerializedName("menu_id")
    @Expose
    private String menuId;
    @SerializedName("products")
    @Expose
    private List<ProductMP> products = null;

     public List<ProductMP> getProducts() {
         return products;
     }

     public void setProducts(List<ProductMP> products) {
         this.products = products;
     }

     public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }



}


 class ProductMP {

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
    @SerializedName("popular_status")
    @Expose
    private String popularStatus;

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

    public String getPopularStatus() {
        return popularStatus;
    }

    public void setPopularStatus(String popularStatus) {
        this.popularStatus = popularStatus;
    }

}


 class ProductMenu {

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
    @SerializedName("popular_status")
    @Expose
    private String popularStatus;

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

    public String getPopularStatus() {
        return popularStatus;
    }

    public void setPopularStatus(String popularStatus) {
        this.popularStatus = popularStatus;
    }

}


 class RestaurantDetails {

    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("restaurant_name")
    @Expose
    private String restaurantName;
    @SerializedName("restauran_address")
    @Expose
    private String restauranAddress;
    @SerializedName("restaurant_image")
    @Expose
    private String restaurantImage;

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

    public String getRestauranAddress() {
        return restauranAddress;
    }

    public void setRestauranAddress(String restauranAddress) {
        this.restauranAddress = restauranAddress;
    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

}


 class ResultRestMenu {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("restaurant_details")
    @Expose
    private RestaurantDetails restaurantDetails;
    @SerializedName("most_popular")
    @Expose
    private List<MostPopular> mostPopular = null;
    @SerializedName("menus")
    @Expose
    private List<Menu> menus = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RestaurantDetails getRestaurantDetails() {
        return restaurantDetails;
    }

    public void setRestaurantDetails(RestaurantDetails restaurantDetails) {
        this.restaurantDetails = restaurantDetails;
    }

    public List<MostPopular> getMostPopular() {
        return mostPopular;
    }

    public void setMostPopular(List<MostPopular> mostPopular) {
        this.mostPopular = mostPopular;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

}

*/

















/*

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private ResultRestMenu result;

    public ResultRestMenu getResult() {
        return result;
    }

    public void setResult(ResultRestMenu result) {
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


 class Menu {

    @SerializedName("menu_name")
    @Expose
    private String menuName;
    @SerializedName("menu_id")
    @Expose
    private String menuId;
    @SerializedName("products")
    @Expose
    private List<ProductMenu> products = null;

     public List<ProductMenu> getProducts() {
         return products;
     }

     public void setProducts(List<ProductMenu> products) {
         this.products = products;
     }

     public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }



}


 class MostPopular {

    @SerializedName("menu_name")
    @Expose
    private String menuName;
    @SerializedName("menu_id")
    @Expose
    private String menuId;
    @SerializedName("products")
    @Expose
    private List<ProductMP> products = null;

     public List<ProductMP> getProducts() {
         return products;
     }

     public void setProducts(List<ProductMP> products) {
         this.products = products;
     }

     public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }




}

 class ProductMP {

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
    @SerializedName("popular_status")
    @Expose
    private String popularStatus;

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

    public String getPopularStatus() {
        return popularStatus;
    }

    public void setPopularStatus(String popularStatus) {
        this.popularStatus = popularStatus;
    }

}


 class ProductMenu {

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
    @SerializedName("popular_status")
    @Expose
    private String popularStatus;

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

    public String getPopularStatus() {
        return popularStatus;
    }

    public void setPopularStatus(String popularStatus) {
        this.popularStatus = popularStatus;
    }

}


 class RestaurantDetails {

    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("restaurant_name")
    @Expose
    private String restaurantName;
    @SerializedName("restauran_address")
    @Expose
    private String restauranAddress;
    @SerializedName("restaurant_image")
    @Expose
    private String restaurantImage;

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

    public String getRestauranAddress() {
        return restauranAddress;
    }

    public void setRestauranAddress(String restauranAddress) {
        this.restauranAddress = restauranAddress;
    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

}


 class ResultRestMenu {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("restaurant_details")
    @Expose
    private RestaurantDetails restaurantDetails;
    @SerializedName("most_popular")
    @Expose
    private List<MostPopular> mostPopular = null;
    @SerializedName("menus")
    @Expose
    private List<Menu> menus = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RestaurantDetails getRestaurantDetails() {
        return restaurantDetails;
    }

    public void setRestaurantDetails(RestaurantDetails restaurantDetails) {
        this.restaurantDetails = restaurantDetails;
    }

    public List<MostPopular> getMostPopular() {
        return mostPopular;
    }

    public void setMostPopular(List<MostPopular> mostPopular) {
        this.mostPopular = mostPopular;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

}*/


































