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
}
