package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.RandomStringUtils;

public class IqSoft_04_APIVariables_Credit_Request {

    @SerializedName("Token")
    @Expose
    private String Token ;

    @SerializedName("CurrencyId")
    @Expose
    private String CurrencyId ;

    @SerializedName("GameId")
    @Expose
    private int GameId ;

    @SerializedName("OperationTypeId")
    @Expose
    private int OperationTypeId  ;

    @SerializedName("TransactionId")
    @Expose
    private String TransactionId;

    @SerializedName("BetState")
    @Expose
    private int BetState ;

    @SerializedName("Amount")
    @Expose
    private double Amount;


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

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int gameId) {
        GameId = gameId;
    }

    public int getOperationTypeId() {
        return OperationTypeId;
    }

    public void setOperationTypeId(int operationTypeId) {
        OperationTypeId = operationTypeId;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
    }

    public int getBetState() {
        return BetState;
    }

    public void setBetState(int betState) {
        BetState = betState;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }
}
