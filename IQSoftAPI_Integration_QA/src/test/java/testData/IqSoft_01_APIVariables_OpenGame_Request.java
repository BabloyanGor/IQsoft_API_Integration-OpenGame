package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IqSoft_01_APIVariables_OpenGame_Request {

    @SerializedName("PartnerId")
    @Expose
    private int PartnerId;

    @SerializedName("GameId")
    @Expose
    private int GameId;

    @SerializedName("Token")
    @Expose
    private String Token;

    @SerializedName("LanguageId")
    @Expose
    private String LanguageId;

    @SerializedName("IsForMobile")
    @Expose
    private boolean IsForMobile;

    @SerializedName("Domain")
    @Expose
    private String Domain;


    public int getPartnerId() {
        return PartnerId;
    }

    public void setPartnerId(int partnerId) {
        PartnerId = partnerId;
    }

    public int getGameId() {
        return GameId;
    }

    public void setGameId(int gameId) {
        GameId = gameId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getLanguageId() {
        return LanguageId;
    }

    public void setLanguageId(String languageId) {
        LanguageId = languageId;
    }

    public boolean isForMobile() {
        return IsForMobile;
    }

    public void setForMobile(boolean forMobile) {
        IsForMobile = forMobile;
    }
    public String getDomain() {
        return Domain;
    }
    public void setDomain(String domain) {
        Domain = domain;
    }
}
