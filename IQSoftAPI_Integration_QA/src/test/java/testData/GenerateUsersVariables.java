package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenerateUsersVariables {
    public String getMobileCode() {
        return MobileCode;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public String getBirthMonth() {
        return BirthMonth;
    }

    public String getBirthYear() {
        return BirthYear;
    }

    public boolean isTermsConditionsAccepted() {
        return TermsConditionsAccepted;
    }

    public String getLanguageId() {
        return LanguageId;
    }

    public String getPassword() {
        return Password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return Email;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public String getUserName() {
        return UserName;
    }

    @SerializedName("MobileCode")
    @Expose
    private final String MobileCode = "+374";

    @SerializedName("BirthDay")
    @Expose
    private final String BirthDay= "11";

    @SerializedName("BirthMonth")
    @Expose
    private final String BirthMonth = "10";

    @SerializedName("BirthYear")
    @Expose
    private final String BirthYear = "2000";

    @SerializedName("TermsConditionsAccepted")
    @Expose
    private final boolean TermsConditionsAccepted = true;

    @SerializedName("LanguageId")
    @Expose
    private final String LanguageId = "en";




    @SerializedName("Password")
    @Expose
    private final String Password =  "Test123456";

    @SerializedName("confirmPassword")
    @Expose
    private final String confirmPassword = "Test123456";



    @SerializedName("Email")
    @Expose
    private String Email;

    @SerializedName("MobileNumber")
    @Expose
    private String MobileNumber;

    @SerializedName("UserName")
    @Expose
    private String UserName;

    public void setEmail(String email) {
        Email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
