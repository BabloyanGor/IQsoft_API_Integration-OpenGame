//package testCases;
//
//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;
//import io.qameta.allure.Description;
//import io.qameta.allure.Severity;
//import io.qameta.allure.SeverityLevel;
//import org.json.JSONObject;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
//
//import java.io.IOException;
//
//public class IqSoft_API_3_GetBalance_Positive_Test extends BaseTest{
//    JSONObject jsonObjectBody;
//    int statusCod;
//
//    @BeforeClass
//    public void setUp() throws UnirestException, IOException {
//        HttpResponse<String> response = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(),clientProductID);
//        Unirest.shutdown();
//        statusCod = response.getStatus();
//        jsonObjectBody = new JSONObject(response.getBody());
//
//        iqSoft_03_apiVariables_getBalance_response.setClientId(jsonObjectBody.get("ClientId").toString());
//        logger.info("GetBalance API Response ClientId : " + iqSoft_03_apiVariables_getBalance_response.getClientId());
//
//        iqSoft_03_apiVariables_getBalance_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
//        logger.info("GetBalance API Response CurrencyId : " + iqSoft_03_apiVariables_getBalance_response.getCurrencyId());
//
//        iqSoft_03_apiVariables_getBalance_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
//        logger.info("GetBalance API Response Balance : " + iqSoft_03_apiVariables_getBalance_response.getBalance());
//
//        iqSoft_03_apiVariables_getBalance_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        logger.info("GetBalance API Response ResponseCode : " + iqSoft_03_apiVariables_getBalance_response.getResponseCode());
//
//        iqSoft_03_apiVariables_getBalance_response.setDescription(jsonObjectBody.get("Description").toString());
//        logger.info("GetBalance API Response Description : " + iqSoft_03_apiVariables_getBalance_response.getDescription());
//
//        iqSoft_03_apiVariables_getBalance_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));
//        logger.info("GetBalance API Response ResponseObject : " + iqSoft_03_apiVariables_getBalance_response.getResponseObject());
//
//    }
//
//
//    @Test(priority = 1)
//    @Description("Verify GetBalance API_s Response Status Cod equals to 200")
//    @Severity(SeverityLevel.BLOCKER)
//    public void GetBalanceAPIValidateStatusCod() {
//        logger.info("GetBalance API Status Cod is Equal: " + statusCod);
//        Assert.assertEquals(200,statusCod);
//    }
//
//    @Test(priority = 2,dependsOnMethods = { "GetBalanceAPIValidateStatusCod" })
//    @Description("Verify GetBalance API_s Validate Positive Response")
//    public void GetBalanceAPIValidatePositiveResponse() {
//        SoftAssert softAssert = new SoftAssert();
//
//        softAssert.assertNotEquals(iqSoft_03_apiVariables_getBalance_response.getClientId(),null);
//        Assert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getCurrencyId(), iqSoft02ApisVariables_authorization_request.getCurrencyId());
//        softAssert.assertNotEquals(iqSoft_03_apiVariables_getBalance_response.getClientId(),null);
//        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getResponseCode(), 0);
//        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getDescription(), "null");
//        softAssert.assertEquals( iqSoft_03_apiVariables_getBalance_response.getResponseObject(),"null");
//
//        softAssert.assertAll();
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
