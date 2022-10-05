package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IqSoft_03_APIVariables_GetBalance_Request {


    @SerializedName("Token")
    @Expose
    private String Token;

    @SerializedName("CurrencyId")
    @Expose
    private String CurrencyId;

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
}
