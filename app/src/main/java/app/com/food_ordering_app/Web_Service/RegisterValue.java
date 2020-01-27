package app.com.food_ordering_app.Web_Service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 9/27/2017.
 */

public class RegisterValue {


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private ResultR result;

    public ResultR getResult() {
        return result;
    }

    public void setResult(ResultR result) {
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


 class ResultR {

    @SerializedName("user_id")
    @Expose
    private String userId;

     public String getUserId() {
         return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
