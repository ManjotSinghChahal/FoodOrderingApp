package app.com.food_ordering_app.Web_Service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 10/3/2017.
 */

public class ProfileValue {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private ResultP result;

    public ResultP getResult() {
        return result;
    }

    public void setResult(ResultP result) {
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

 class HomeP {

    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("postal_code")
    @Expose
    private String postalCode;
     @SerializedName("province")
     @Expose
     private String province;

     public String getProvince() {
         return province;
     }

     public void setProvince(String province) {
         this.province = province;
     }

     @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

}





 class ResultP {

    @SerializedName("user_image")
    @Expose
    private String userImage;
     @SerializedName("first_name")
     @Expose
     private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("address")
    @Expose
    private AddressP address;

     public AddressP getAddress() {
         return address;
     }

     public void setAddress(AddressP address) {
         this.address = address;
     }

     public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

     public String getFirstName() {
         return firstName;
     }

     public void setFirstName(String firstName) {
         this.firstName = firstName;
     }



    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}


 class WorkP {

     @SerializedName("street")
     @Expose
     private String street;
     @SerializedName("country")
     @Expose
     private String country;
     @SerializedName("city")
     @Expose
     private String city;
     @SerializedName("postal_code")
     @Expose
     private String postalCode;
     @SerializedName("province")
     @Expose
     private String province;

     public String getProvince() {
         return province;
     }

     public void setProvince(String province) {
         this.province = province;
     }

     @SerializedName("lat")
     @Expose
     private String lat;
     @SerializedName("long")
     @Expose
     private String _long;

     public String getStreet() {
         return street;
     }

     public void setStreet(String street) {
         this.street = street;
     }

     public String getCountry() {
         return country;
     }

     public void setCountry(String country) {
         this.country = country;
     }

     public String getCity() {
         return city;
     }

     public void setCity(String city) {
         this.city = city;
     }

     public String getPostalCode() {
         return postalCode;
     }

     public void setPostalCode(String postalCode) {
         this.postalCode = postalCode;
     }

     public String getLat() {
         return lat;
     }

     public void setLat(String lat) {
         this.lat = lat;
     }

     public String getLong() {
         return _long;
     }

     public void setLong(String _long) {
         this._long = _long;
     }

 }


     class AddressP {
         @SerializedName("home")
         @Expose
         private HomeP home;
         @SerializedName("work")
         @Expose
         private WorkP work;

         public HomeP getHome() {
             return home;
         }

         public void setHome(HomeP home) {
             this.home = home;
         }

         public WorkP getWork() {
             return work;
         }

         public void setWork(WorkP work) {
             this.work = work;
         }

     }
