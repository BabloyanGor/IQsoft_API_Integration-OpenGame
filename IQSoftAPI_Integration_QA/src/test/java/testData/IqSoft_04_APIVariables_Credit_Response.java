package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IqSoft_04_APIVariables_Credit_Response {

    @SerializedName("BetId")
    @Expose
    private String BetId;

    @SerializedName("TransactionId")
    @Expose
    private String TransactionId;

    @SerializedName("ClientId")
    @Expose
    private String ClientId;

    @SerializedName("CurrencyId")
    @Expose
    private String CurrencyId;

    @SerializedName("Balance")
    @Expose
    private double Balance;

    @SerializedName("ResponseCode")
    @Expose
    private int ResponseCode;

    @SerializedName("Description")
    @Expose
    private String Description;

    @SerializedName("ResponseObject")
    @Expose
    private String ResponseObject;

    public String getBetId() {
        return BetId;
    }

    public void setBetId(String betId) {
        BetId = betId;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(String currencyId) {
        CurrencyId = currencyId;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
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

    public String getResponseObject() {
        return ResponseObject;
    }

    public void setResponseObject(String responseObject) {
        ResponseObject = responseObject;
    }
}
