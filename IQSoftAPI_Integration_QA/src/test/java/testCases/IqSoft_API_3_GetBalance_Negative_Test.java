package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class IqSoft_API_3_GetBalance_Negative_Test extends BaseTest{
    JSONObject jsonObjectBody;
    int statusCod;

    @Test(priority = 1)
    @Description("Verify GetBalance API_s response with Expired Token")
    @Severity(SeverityLevel.BLOCKER)
    public void GetBalanceAPIValidateResponseWithExpiredToken() throws UnirestException, IOException {
        HttpResponse<String> response = getBalanceAPI(expiredSessionTokenConfig, currencyIDConfig);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_03_apiVariables_getBalance_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("GetBalance API Response ResponseCode : " + iqSoft_03_apiVariables_getBalance_response.getResponseCode());

        try{
            iqSoft_03_apiVariables_getBalance_response.setDescription(jsonObjectBody.get("Description").toString());
            logger.info("GetBalance API Response Description : " + iqSoft_03_apiVariables_getBalance_response.getDescription());
        }
        catch (Exception e){

        }
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getResponseCode(),29);
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getDescription(),"SessionExpired",
                "Error Description: " + iqSoft_03_apiVariables_getBalance_response.getDescription());

        softAssert.assertAll();
    }

    @Test(priority = 2)
    @Description("Verify GetBalance API_s response with invalid Token")
    @Severity(SeverityLevel.BLOCKER)
    public void GetBalanceAPIValidateResponseWithInvalidToken() throws UnirestException, IOException {
        HttpResponse<String> response = getBalanceAPI(sessionTokenConfig + "1", currencyIDConfig);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());


        iqSoft_03_apiVariables_getBalance_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("GetBalance API Response ResponseCode : " + iqSoft_03_apiVariables_getBalance_response.getResponseCode());

        iqSoft_03_apiVariables_getBalance_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("GetBalance API Response Description : " + iqSoft_03_apiVariables_getBalance_response.getDescription());

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(statusCod, 200);

        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getResponseCode(),22);
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getDescription(),"error login",
                "Error Description: " + iqSoft_03_apiVariables_getBalance_response.getDescription());

        softAssert.assertAll();
    }

    @Test(priority = 3)
    @Description("Verify GetBalance API_s response with invalid CurrencyID")
    @Severity(SeverityLevel.BLOCKER)
    public void GetBalanceAPIValidateResponseWithInvalidCurrencyID() throws UnirestException, IOException {
        HttpResponse<String> response = getBalanceAPI(sessionTokenConfig , currencyIDConfig+"1");
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_03_apiVariables_getBalance_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("GetBalance API Response ResponseCode : " + iqSoft_03_apiVariables_getBalance_response.getResponseCode());

        try{
            iqSoft_03_apiVariables_getBalance_response.setDescription(jsonObjectBody.get("Description").toString());
            logger.info("GetBalance API Response Description : " + iqSoft_03_apiVariables_getBalance_response.getDescription());
        }
        catch(Exception e){

        }
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(statusCod, 200);

        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getResponseCode(),20);
        softAssert.assertEquals(iqSoft_03_apiVariables_getBalance_response.getDescription(),"CurrencyNotExists",
                "Error Description: " + iqSoft_03_apiVariables_getBalance_response.getDescription());

        softAssert.assertAll();
    }

    @Test(priority = 4)
    @Description("Verify GetBalance API_s response with other CurrencyID")
    @Severity(SeverityLevel.BLOCKER)
    public void GetBalanceAPIValidateResponseWithOtherCurrencyID() throws UnirestException, IOException {
        HttpResponse<String> response1 = getBalanceAPI(sessionTokenConfig , "USD");
        Unirest.shutdown();
        statusCod = response1.getStatus();
        jsonObjectBody = new JSONObject(response1.getBody());

        iqSoft_03_apiVariables_getBalance_response.setAvailableBalance(Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString()));
        double balanceUSD = iqSoft_03_apiVariables_getBalance_response.getAvailableBalance();

        HttpResponse<String> response = getBalanceAPI(sessionTokenConfig , "EUR");
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_03_apiVariables_getBalance_response.setAvailableBalance(Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString()));
        double balanceEUR = iqSoft_03_apiVariables_getBalance_response.getAvailableBalance();

        Assert.assertNotEquals(balanceUSD,balanceEUR);
    }


}
