package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class IqSoft_API_3_GetBalance_Positive_Test extends BaseTest {
    JSONObject jsonObjectBody;
    int statusCod;

    @BeforeClass
    public void setUp() throws UnirestException, IOException {

        HttpResponse<String> getBalanceResponse = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        Unirest.shutdown();
        statusCod = getBalanceResponse.getStatus();
        jsonObjectBody = new JSONObject(getBalanceResponse.getBody());

        iqSoft_03_apiVariables_getBalance_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        logger.info("GetBalance API Response CurrencyId : " + iqSoft_03_apiVariables_getBalance_response.getCurrencyId());

        iqSoft_03_apiVariables_getBalance_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("GetBalance API Response ResponseCode : " + iqSoft_03_apiVariables_getBalance_response.getResponseCode());

        iqSoft_03_apiVariables_getBalance_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("GetBalance API Response Description : " + iqSoft_03_apiVariables_getBalance_response.getDescription());

        iqSoft_03_apiVariables_getBalance_response.setAvailableBalance(Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString()));
        logger.info("GetBalance API Response Description : " + iqSoft_03_apiVariables_getBalance_response.getAvailableBalance());

    }

    @Test(priority = 1)
    @Description("Verify GetBalance API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void GetBalanceAPIValidateStatusCod() {
        logger.info("GetBalance API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200, statusCod, "StatusCod: " + statusCod);
    }

    @Test(priority = 2, dependsOnMethods = {"GetBalanceAPIValidateStatusCod"})
    @Description("Verify GetBalance API_s Validate Positive Response")
    public void GetBalanceAPIValidatePositiveResponse() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertNotEquals(iqSoft_03_apiVariables_getBalance_response.getCurrencyId(), null,
                "CurrencyId: " + iqSoft_03_apiVariables_getBalance_response.getCurrencyId());
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getCurrencyId().length(), 3,
                "CurrencyIdLength: " + iqSoft_03_apiVariables_getBalance_response.getCurrencyId().length());
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getCurrencyId(), iqSoft_03_apiVariables_getBalance_request.getCurrencyId(),
                "CurrencyIdRequest: " + iqSoft_03_apiVariables_getBalance_response.getCurrencyId() + "VS CurrencyIdResponse: " + iqSoft_03_apiVariables_getBalance_request.getCurrencyId());
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getResponseCode(), 0,
                "ResponseCode : " + iqSoft_03_apiVariables_getBalance_response.getResponseCode());
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getDescription(), "null",
                "Description: " + iqSoft_03_apiVariables_getBalance_response.getDescription());

        boolean balanceHigherOREqualZero = iqSoft_03_apiVariables_getBalance_response.getAvailableBalance() >= 0;
        softAssert.assertEquals(balanceHigherOREqualZero, true);
        softAssert.assertNotEquals(iqSoft_03_apiVariables_getBalance_response.getAvailableBalance(), null);

        softAssert.assertAll();
    }


}
