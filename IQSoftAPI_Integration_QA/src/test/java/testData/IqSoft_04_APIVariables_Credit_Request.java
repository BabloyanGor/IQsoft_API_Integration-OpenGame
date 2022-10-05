package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.RandomStringUtils;

public class IqSoft_04_APIVariables_Credit_Request {




    @SerializedName("RoundId")
    @Expose
    private String RoundId;

    @SerializedName("TransactionId")
    @Expose
    private String TransactionId;

    @SerializedName("CreditTransactionId")
    @Expose
    private String CreditTransactionId;
    @SerializedName("ProductId")
    @Expose
    private int ProductId = 4;

    @SerializedName("Amount")
    @Expose
    private double Amount;

    @SerializedName("Token")
    @Expose
    private String Token ;

    @SerializedName("CurrencyId")
    @Expose
    private String CurrencyId ;

    public String getRoundId() {
        return RoundId;
    }

    public void setRoundId(String roundId) {
        RoundId = roundId;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(String currencyId) {
        CurrencyId = currencyId;
    }

    @SerializedName("TypeId")
    @Expose
    private int TypeId;

    @SerializedName("OperationTypeId")
    @Expose
    private int OperationTypeId = 3 ;

    @SerializedName("BetState")
    @Expose
    private int BetState ;

    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int typeId) {
        TypeId = typeId;
    }

    public int getOperationTypeId() {
        return OperationTypeId;
    }

    public void setOperationTypeId(int operationTypeId) {
        OperationTypeId = operationTypeId;
    }

    public int getBetState() {
        return BetState;
    }

    public void setBetState(int betState) {
        BetState = betState;
    }
}
