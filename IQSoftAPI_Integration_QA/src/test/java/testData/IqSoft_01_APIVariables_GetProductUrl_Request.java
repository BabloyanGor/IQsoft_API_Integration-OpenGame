package testData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IqSoft_01_APIVariables_GetProductUrl_Request {
    @SerializedName("ActivationKey")
    @Expose
    private String ActivationKey = null;

    @SerializedName("CategoryId")
    @Expose
    private String CategoryId = null;

    @SerializedName("Code")
    @Expose
    private String Code = null;

    @SerializedName("Email")
    @Expose
    private String Email = null;

    @SerializedName("Index")
    @Expose
    private String Index = null;

    @SerializedName("MobileNumber")
    @Expose
    private String MobileNumber = null;

//   @SerializedName("ProviderIds")
//   @Expose
//   private Array RequestData = [];

    @SerializedName("Controller")
    @Expose
    private String Controller = "Main";

    @SerializedName("DeviceType")
    @Expose
    private int DeviceType = 1;


    @SerializedName("IsForDemo")
    @Expose
    private boolean IsForDemo = false;

    @SerializedName("IsForMobile")
    @Expose
    private boolean IsForMobile = false;

    @SerializedName("LanguageId")
    @Expose
    private String LanguageId = "en";

    @SerializedName("Loader")
    @Expose
    private boolean Loader = true;


    @SerializedName("PageIndex")
    @Expose
    private int PageIndex = 0;

    @SerializedName("PageSize")
    @Expose
    private int PageSize = 100;

    @SerializedName("Position")
    @Expose
    private String Position = "";


    @SerializedName("RequestData")
    @Expose
    private String RequestData = "{}";

    @SerializedName("TimeZone")
    @Expose
    private int TimeZone = 4;

    @SerializedName("Method")
    @Expose
    private String Method = "GetProductUrl";






    @SerializedName("ClientId")
    @Expose
    private int ClientId;

    @SerializedName("PartnerId")
    @Expose
    private int PartnerId;

    @SerializedName("ProductId")
    @Expose
    private int ProductId;

    @SerializedName("Token")
    @Expose
    private String Token;

    public int getClientId() {
        return ClientId;
    }
    public void setClientId(int clientId) {
        ClientId = clientId;
    }

    public int getPartnerId() {
        return PartnerId;
    }
    public void setPartnerId(int partnerId) {
        PartnerId = partnerId;
    }

    public int getProductId() {
        return ProductId;
    }
    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getUserToken() {
        return Token;
    }
    public void setUserToken(String userToken) {
        Token = userToken;
    }






   public String getActivationKey() {
      return ActivationKey;
   }

   public void setActivationKey(String activationKey) {
      ActivationKey = activationKey;
   }

   public String getCategoryId() {
      return CategoryId;
   }

   public void setCategoryId(String categoryId) {
      CategoryId = categoryId;
   }

   public String getCode() {
      return Code;
   }

   public void setCode(String code) {
      Code = code;
   }

   public String getController() {
      return Controller;
   }

   public void setController(String controller) {
      Controller = controller;
   }

   public int getDeviceType() {
      return DeviceType;
   }

   public void setDeviceType(int deviceType) {
      DeviceType = deviceType;
   }

   public String getEmail() {
      return Email;
   }

   public void setEmail(String email) {
      Email = email;
   }

   public String getIndex() {
      return Index;
   }

   public void setIndex(String index) {
      Index = index;
   }

   public boolean isForDemo() {
      return IsForDemo;
   }

   public void setForDemo(boolean forDemo) {
      IsForDemo = forDemo;
   }

   public boolean isForMobile() {
      return IsForMobile;
   }

   public void setForMobile(boolean forMobile) {
      IsForMobile = forMobile;
   }

   public String getLanguageId() {
      return LanguageId;
   }

   public void setLanguageId(String languageId) {
      LanguageId = languageId;
   }

   public boolean isLoader() {
      return Loader;
   }

   public void setLoader(boolean loader) {
      Loader = loader;
   }

   public String getMobileNumber() {
      return MobileNumber;
   }

   public void setMobileNumber(String mobileNumber) {
      MobileNumber = mobileNumber;
   }

   public int getPageIndex() {
      return PageIndex;
   }

   public void setPageIndex(int pageIndex) {
      PageIndex = pageIndex;
   }

   public int getPageSize() {
      return PageSize;
   }

   public void setPageSize(int pageSize) {
      PageSize = pageSize;
   }

   public String getPosition() {
      return Position;
   }

   public void setPosition(String position) {
      Position = position;
   }

   public String getRequestData() {
      return RequestData;
   }

   public void setRequestData(String requestData) {
      RequestData = requestData;
   }

   public int getTimeZone() {
      return TimeZone;
   }

   public void setTimeZone(int timeZone) {
      TimeZone = timeZone;
   }

   public String getMethod() {
      return Method;
   }
   public void setMethod(String method) {
      Method = method;
   }


}
