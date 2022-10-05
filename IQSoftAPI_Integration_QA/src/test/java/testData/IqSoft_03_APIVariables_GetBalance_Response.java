package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IqSoft_03_APIVariables_GetBalance_Response {

    @SerializedName("CurrencyId")
    @Expose
    private String CurrencyId;

    @SerializedName("ResponseCode")
    @Expose
    private int ResponseCode;

    @SerializedName("Description")
    @Expose
    private String Description;

    @SerializedName("AvailableBalance")
    @Expose
    private int AvailableBalance;


    public String getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(String currencyId) {
        CurrencyId = currencyId;
    }

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

    public int getAvailableBalance() {
        return AvailableBalance;
    }

    public void setAvailableBalance(int availableBalance) {
        AvailableBalance = availableBalance;
    }
}
