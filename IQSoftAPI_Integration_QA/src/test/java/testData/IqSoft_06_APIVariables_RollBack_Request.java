package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IqSoft_06_APIVariables_RollBack_Request {

    @SerializedName("UserName")
    @Expose
    private String UserName;

    @SerializedName("GameId")
    @Expose
    private int GameId;

    @SerializedName("RollbackTransactionId")
    @Expose
    private String RollbackTransactionId;

    @SerializedName("TransactionId")
    @Expose
    private String TransactionId;

    @SerializedName("ProductId")
    @Expose
    private int OperationTypeId ;  //3-Bet, 4-Win, 15-BetRollback, 17-WinRollback.

    @SerializedName("Token")
    @Expose
    private String Token;


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int gameId) {
        GameId = gameId;
    }

    public String getRollbackTransactionId() {
        return RollbackTransactionId;
    }

    public void setRollbackTransactionId(String rollbackTransactionId) {
        RollbackTransactionId = rollbackTransactionId;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
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
