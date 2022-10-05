package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IqSoft_02_APISVariables_Authorization_Response {

    @SerializedName("Token")
    @Expose
    private String Token;

    @SerializedName("Gender")
    @Expose
    private String Gender;

    @SerializedName("ClientId")
    @Expose
    private String ClientId;

    @SerializedName("LastName")
    @Expose
    private String LastName;

    @SerializedName("UserName")
    @Expose
    private String UserName;

    @SerializedName("BirthDate")
    @Expose
    private String BirthDate;

    @SerializedName("FirstName")
    @Expose
    private String FirstName;

    @SerializedName("CurrencyId")
    @Expose
    private double CurrencyId;

    @SerializedName("Description")
    @Expose
    private String Description;

    @SerializedName("ResponseCode")
    @Expose
    private int ResponseCode;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public double getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(double currencyId) {
        CurrencyId = currencyId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(int responseCode) {
        ResponseCode = responseCode;
    }
}
