package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class IqSoft_API_2_Authorization_Negative_Test extends BaseTest {

    JSONObject jsonObjectBody;
    int statusCod;

    @Test(priority = 1)
    @Description("Verify Authorization API_s response with Expired Token")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationAPIValidateResponseWithExpiredToken() throws UnirestException, IOException {
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> response = authorizationAPI(expiredSessionTokenConfig, gameIdConfig);
        ;
        Unirest.shutdown();
        statusCod = response.getStatus();
        logger.info("Authorization API Response Status cod : " + statusCod);

        jsonObjectBody = new JSONObject(response.getBody());
        iqSoft_02_apisVariables_authorization_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        iqSoft_02_apisVariables_authorization_response.setDescription(jsonObjectBody.get("Description").toString());

        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);

        softAssert.assertNotEquals(iqSoft_02_apisVariables_authorization_response.getResponseCode(), 0,
                "ResponseCode: " + iqSoft_02_apisVariables_authorization_response.getResponseCode());
//        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getDescription(), "SessionExpired",
//                "Error Description: " + iqSoft_02_apisVariables_authorization_response.getDescription());

        softAssert.assertAll();
    }


    @Test(priority = 2)
    @Description("Verify Authorization API_s response with invalid Token")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationAPIValidateResponseWithInvalidToken() throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> response = authorizationAPI(sessionTokenConfig + "1", gameIdConfig);
        Unirest.shutdown();

        statusCod = response.getStatus();
        logger.info("Authorization API Response Status cod : " + statusCod);
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_02_apisVariables_authorization_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        iqSoft_02_apisVariables_authorization_response.setDescription(jsonObjectBody.get("Description").toString());

        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);

        softAssert.assertNotEquals(iqSoft_02_apisVariables_authorization_response.getResponseCode(), 0,
                "ResponseCode: " + iqSoft_02_apisVariables_authorization_response.getResponseCode());
//        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getDescription(), "ClientNotFound",
//                "Error Description: " + iqSoft_02_apisVariables_authorization_response.getDescription());

        softAssert.assertAll();
    }

//    @Test(priority = 3)
//    @Description("Verify Authorization API_s response with invalid gameIdConfig")
//    @Severity(SeverityLevel.NORMAL)
//    public void AuthorizationAPIValidateResponseWithInvalidProductID() throws UnirestException, IOException {
//        SoftAssert softAssert = new SoftAssert();
//
//        HttpResponse<String> response = authorizationAPI(sessionTokenConfig, -10);
//        Unirest.shutdown();
//        statusCod = response.getStatus();
//        logger.info("Authorization API Response Status cod : " + statusCod);
//
//        jsonObjectBody = new JSONObject(response.getBody());
//
//        iqSoft_02_apisVariables_authorization_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
////        iqSoft_02_apisVariables_authorization_response.setDescription(jsonObjectBody.get("Description").toString());
//
//        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);
//        softAssert.assertNotEquals(iqSoft_02_apisVariables_authorization_response.getResponseCode(), 0,
//                "ResponseCode: " + iqSoft_02_apisVariables_authorization_response.getResponseCode());
////        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getDescription(), "ProductNotFound",
////                "Error Description: " + iqSoft_02_apisVariables_authorization_response.getDescription());
//
//        softAssert.assertAll();
//    }



}
