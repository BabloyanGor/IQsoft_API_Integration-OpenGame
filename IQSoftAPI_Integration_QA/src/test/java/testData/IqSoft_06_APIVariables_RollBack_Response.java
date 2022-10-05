package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IqSoft_06_APIVariables_RollBack_Response {

    @SerializedName("ResponseCode")
    @Expose
    private int ResponseCode;

    @SerializedName("Description")
    @Expose
    private String Description;


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
}
