package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IqSoft_05_APIVariables_Debit_Response {

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



    
}
