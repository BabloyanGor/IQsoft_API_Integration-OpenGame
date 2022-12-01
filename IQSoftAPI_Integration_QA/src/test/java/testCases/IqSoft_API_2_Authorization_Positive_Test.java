package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.io.IOException;

public class IqSoft_API_2_Authorization_Positive_Test extends BaseTest {

    JSONObject jsonObjectBody;
    int statusCod;

    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        HttpResponse<String> authorizationResponse = authorizationAPI(sessionTokenConfig, gameIdConfig);
        Unirest.shutdown();
        statusCod = authorizationResponse.getStatus();
        jsonObjectBody = new JSONObject(authorizationResponse.getBody());

        iqSoft_02_apisVariables_authorization_response.setToken(jsonObjectBody.get("Token").toString());
        iqSoft_02_apisVariables_authorization_response.setGender(jsonObjectBody.get("Gender").toString());
        iqSoft_02_apisVariables_authorization_response.setClientId(jsonObjectBody.get("ClientId").toString());
        iqSoft_02_apisVariables_authorization_response.setLastName(jsonObjectBody.get("LastName").toString());
        iqSoft_02_apisVariables_authorization_response.setUserName(jsonObjectBody.get("UserName").toString());
        iqSoft_02_apisVariables_authorization_response.setBirthDate(jsonObjectBody.get("BirthDate").toString());
        iqSoft_02_apisVariables_authorization_response.setFirstName(jsonObjectBody.get("FirstName").toString());
        iqSoft_02_apisVariables_authorization_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        iqSoft_02_apisVariables_authorization_response.setDescription(jsonObjectBody.get("Description").toString());
        iqSoft_02_apisVariables_authorization_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));

        AuthorizationTokenVar = iqSoft_02_apisVariables_authorization_response.getToken();
        AuthorizationClientId = iqSoft_02_apisVariables_authorization_response.getClientId();
        AuthorizationUserName = iqSoft_02_apisVariables_authorization_response.getUserName();
        AuthorizationCurrencyId = iqSoft_02_apisVariables_authorization_response.getCurrencyId();
    }


    @Test(priority = 1)
    @Description("Verify Authorization API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationAPIValidateStatusCod() {

        logger.info("Authorization API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200, statusCod, "StatusCod: " + statusCod);
    }


    @Test(priority = 2, dependsOnMethods = {"AuthorizationAPIValidateStatusCod"})
    @Description("Verify Authorization API_s Validate Positive Response")
    @Severity(SeverityLevel.BLOCKER)
    public void AuthorizationAPIValidatePositiveResponse() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertNotEquals(iqSoft_02_apisVariables_authorization_response.getToken(), null,
                "AuthorizationToken: " + iqSoft_02_apisVariables_authorization_response.getToken());
//        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getToken() instanceof String,true);
        softAssert.assertNotEquals(iqSoft_02_apisVariables_authorization_response.getClientId(), null,
                "ClientId: " + iqSoft_02_apisVariables_authorization_response.getClientId());
        softAssert.assertNotEquals(iqSoft_02_apisVariables_authorization_response.getUserName(), null,
                "UserName: " + iqSoft_02_apisVariables_authorization_response.getUserName());
        softAssert.assertNotEquals(iqSoft_02_apisVariables_authorization_response.getBirthDate(), null,
                "BirthDate: " + iqSoft_02_apisVariables_authorization_response.getBirthDate());
        softAssert.assertNotEquals(iqSoft_02_apisVariables_authorization_response.getCurrencyId(), null,
                "CurrencyId: " + iqSoft_02_apisVariables_authorization_response.getCurrencyId());
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getCurrencyId().length(), 3,
                "CurrencyIdLength: " + iqSoft_02_apisVariables_authorization_response.getCurrencyId().length());
//        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getDescription(), "null",
//                "Description : " + iqSoft_02_apisVariables_authorization_response.getDescription());
        softAssert.assertEquals(iqSoft_02_apisVariables_authorization_response.getResponseCode(), 0,
                "ResponseCode : " + iqSoft_02_apisVariables_authorization_response.getResponseCode());

        softAssert.assertAll();
    }


}
