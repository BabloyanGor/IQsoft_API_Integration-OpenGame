package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class IqSoft_API_1_OpenGame_Positive_Test extends BaseTest {
    JSONObject jsonObjectBody;
    int statusCod;


    @BeforeClass
    public void setUpTestCase() throws UnirestException, IOException {
        HttpResponse<String> responseForTimeOutToken = getUrlAPI(partnerID,productID,clientId,capturedToken);
        Unirest.shutdown();
        statusCod = responseForTimeOutToken.getStatus();
        jsonObjectBody = new JSONObject(responseForTimeOutToken.getBody());
        iqSoft01ApiVariables_getProductUrl_response.setResponseObject(jsonObjectBody.get("ResponseObject").toString());
        logger.info("getGameUrl API ResponseObject is Equal: " + iqSoft01ApiVariables_getProductUrl_response.getResponseObject());
        try {
            String partsTimeOut[] = iqSoft01ApiVariables_getProductUrl_response.getResponseObject().split("=");
            authorizationTimeOutToken = partsTimeOut[2];

        } catch (Exception e) {
            logger.info("authorizationTimeOutToken has Exception : " + e);
        }
        logger.info("authorizationTimeOutToken : " + authorizationTimeOutToken);


        HttpResponse<String> response = getUrlAPI(partnerID,productID,clientId,capturedToken);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft01ApiVariables_getProductUrl_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("getGameUrl API ResponseCode is Equal: " + iqSoft01ApiVariables_getProductUrl_response.getResponseCode());

        iqSoft01ApiVariables_getProductUrl_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("getGameUrl API Description is Equal: " + iqSoft01ApiVariables_getProductUrl_response.getDescription());

        iqSoft01ApiVariables_getProductUrl_response.setResponseObject(jsonObjectBody.get("ResponseObject").toString());
        logger.info("getGameUrl API ResponseObject is Equal: " + iqSoft01ApiVariables_getProductUrl_response.getResponseObject());

        try {
            String parts[] = iqSoft01ApiVariables_getProductUrl_response.getResponseObject().split("=");
            iqSoft01ApiVariables_getProductUrl_response.setAuthorizationToken(parts[2]);
        } catch (Exception e) {
            logger.info("authorizationToken has Exception : " + e);
        }
        logger.info("authorizationToken : " + iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken());
    }


    @Test(priority = 1)
    @Description("Verify getURL API_s Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void getUrlAPIValidateStatusCod() {
        logger.info("getGameUrl API Response Status Cod is Equal: " + statusCod);
        Assert.assertEquals(statusCod, 200);
    }


    @Test(priority = 2, dependsOnMethods = {"getUrlAPIValidateStatusCod"})
    @Description("Verify getGameUrl API_s Response AuthorizationToken != null")
    @Severity(SeverityLevel.BLOCKER)
    public void getUrlAPIValidateAuthorizationTokenNotNull() {
        Assert.assertNotEquals(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), null);
    }


    @Test(priority = 3, dependsOnMethods = {"getUrlAPIValidateStatusCod"})
    @Description("Verify getGameUrl Validate Positive Response")
    @Severity(SeverityLevel.BLOCKER)
    public void getUrlAPIValidatePositiveResponse() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getResponseCode(), 0);
        softAssert.assertEquals(iqSoft01ApiVariables_getProductUrl_response.getDescription(), "null", "Error Description: "+iqSoft01ApiVariables_getProductUrl_response.getDescription());
        softAssert.assertNotEquals(iqSoft01ApiVariables_getProductUrl_response.getResponseObject(),"null");
        softAssert.assertTrue(iqSoft01ApiVariables_getProductUrl_response.getResponseObject().contains("http://"));
        softAssert.assertAll();

    }



    @AfterClass
    public void tearDownTestCase() {

    }

}
