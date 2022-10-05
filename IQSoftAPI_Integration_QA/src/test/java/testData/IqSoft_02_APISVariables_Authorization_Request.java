package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IqSoft_02_APISVariables_Authorization_Request {

    @SerializedName("Token")
    @Expose
    private String Token;
    @SerializedName("ProductId")
    @Expose
    private int ProductId ;
    @SerializedName("CurrencyId")
    @Expose
    private String CurrencyId = "EUR";

    @SerializedName("PartnerId")
    @Expose
    private int PartnerId = 1;

    @SerializedName("LanguageId")
    @Expose
    private String LanguageId = "";


    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(String currencyId) {
        CurrencyId = currencyId;
    }

    public int getPartnerId() {
        return PartnerId;
    }

    public void setPartnerId(int partnerId) {
        PartnerId = partnerId;
    }

    public String getLanguageId() {
        return LanguageId;
    }

    public void setLanguageId(String languageId) {
        LanguageId = languageId;
    }
}
