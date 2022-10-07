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

public class IqSoft_API_4_Credit_Positive_Test extends BaseTest {
    JSONObject jsonObjectBody;
    double beforeCredit;
    double afterCredit;
    int statusCod;


    @BeforeClass
    public void setUp() throws UnirestException, IOException {

        HttpResponse<String> responseGetBalanceBefore = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBefore.getBody());
        beforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();


        HttpResponse<String> responseCredit = creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, 1,
                                                        creditValidTransactionID, betAmountCreditConfig,1);
        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Credit API Response ResponseCode : " + iqSoft_04_apiVariables_credit_response.getResponseCode());

        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Credit API Response Description : " + iqSoft_04_apiVariables_credit_response.getDescription());

        String OperationItems = String.valueOf(jsonObjectBody.getJSONObject("OperationItems"));
        JSONObject jsonObjectOperationItems = new JSONObject(OperationItems);

        iqSoft_04_apiVariables_credit_response.setBetId(jsonObjectOperationItems.get("BetId").toString());
        logger.info("Credit API Response BetId : " + iqSoft_04_apiVariables_credit_response.getBetId());

        iqSoft_04_apiVariables_credit_response.setBalance(Double.parseDouble(jsonObjectOperationItems.get("Balance").toString()));
        logger.info("Credit API Response Balance : " + iqSoft_04_apiVariables_credit_response.getBalance());

        iqSoft_04_apiVariables_credit_response.setClientId(jsonObjectOperationItems.get("ClientId").toString());
        logger.info("Credit API Response ClientId : " + iqSoft_04_apiVariables_credit_response.getClientId());

        iqSoft_04_apiVariables_credit_response.setCurrencyId(jsonObjectOperationItems.get("CurrencyId").toString());
        logger.info("Credit API Response CurrencyId : " + iqSoft_04_apiVariables_credit_response.getCurrencyId());



        HttpResponse<String> responseGetBalanceAfter = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfter.getBody());
        afterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

    }

    @Test(priority = 1)
    @Description("Verify Credit API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateStatusCod() {
        logger.info("Credit API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200, statusCod);
    }

    @Test(priority = 2, dependsOnMethods = {"CreditAPIValidateStatusCod"})
    @Description("Verify Credit API_s Validate Positive Response")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidatePositiveResponse() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 0);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "null","Description: " + iqSoft_04_apiVariables_credit_response.getDescription());
        softAssert.assertNotEquals(iqSoft_04_apiVariables_credit_response.getBetId(), "null");
        softAssert.assertEquals(betAmountCreditConfig , beforeCredit-afterCredit);
        softAssert.assertNotEquals(iqSoft_04_apiVariables_credit_response.getClientId(),null);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getCurrencyId(), currencyIDConfig);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getCurrencyId().length(), 3);

        softAssert.assertAll();
    }



}