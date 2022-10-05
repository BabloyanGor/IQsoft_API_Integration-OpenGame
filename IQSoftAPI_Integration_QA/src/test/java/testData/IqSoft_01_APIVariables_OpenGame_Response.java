package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IqSoft_01_APIVariables_OpenGame_Response {
    @SerializedName("ResponseCode")
    @Expose
    private int ResponseCode;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("ResponseObject")
    @Expose
    private String ResponseObject;


    public int getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(int responseCode) {
        ResponseCode = responseCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getResponseObject() {
        return ResponseObject;
    }

    public void setResponseObject(String responseObject) {
        ResponseObject = responseObject;
    }


}
