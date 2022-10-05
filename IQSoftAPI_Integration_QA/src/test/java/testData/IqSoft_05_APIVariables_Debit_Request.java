package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IqSoft_05_APIVariables_Debit_Request {

    @SerializedName("ClientId")
    @Expose
    private String ClientId;

    @SerializedName("CurrencyId")
    @Expose
    private String CurrencyId ;

    @SerializedName("GameId")
    @Expose
    private int GameId;

    @SerializedName("TransactionId")
    @Expose
    private String TransactionId;

    @SerializedName("CreditTransactionId")
    @Expose
    private String CreditTransactionId;

    @SerializedName("Amount")
    @Expose
    private double Amount ;

    @SerializedName("BetState")
    @Expose
    private int BetState;

    @SerializedName("OperationTypeId")
    @Expose
    private int OperationTypeId;

    @SerializedName("Token")
    @Expose
    private String Token ;


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

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int gameId) {
        GameId = gameId;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
    }

    public String getCreditTransactionId() {
        return CreditTransactionId;
    }

    public void setCreditTransactionId(String creditTransactionId) {
        CreditTransactionId = creditTransactionId;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public int getBetState() {
        return BetState;
    }

    public void setBetState(int betState) {
        BetState = betState;
    }

    public int getOperationTypeId() {
        return OperationTypeId;
    }

    public void setOperationTypeId(int operationTypeId) {
        OperationTypeId = operationTypeId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
