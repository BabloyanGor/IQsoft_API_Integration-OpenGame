package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONArray;
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
                creditValidTransactionID, betAmountCreditConfig, 1);
        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());


        JSONArray jsonArrayOperationItems = jsonObjectBody.getJSONArray("OperationItems");
        for (int j = 0; j < jsonArrayOperationItems.length(); j++) {
            String first = String.valueOf(jsonArrayOperationItems.get(j));
            JSONObject jsonObjectGame = new JSONObject(first);

            iqSoft_04_apiVariables_credit_response.setBetId(jsonObjectGame.get("BetId").toString());
            iqSoft_04_apiVariables_credit_response.setBalance(Double.parseDouble(jsonObjectGame.get("Balance").toString()));
            iqSoft_04_apiVariables_credit_response.setClientId(jsonObjectGame.get("ClientId").toString());
            iqSoft_04_apiVariables_credit_response.setCurrencyId(jsonObjectGame.get("CurrencyId").toString());
        }

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
        Assert.assertEquals(200, statusCod, "StatusCod: " + statusCod);
    }

    @Test(priority = 2, dependsOnMethods = {"CreditAPIValidateStatusCod"})
    @Description("Verify Credit API_s Validate Positive Response")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidatePositiveResponse() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 0,
                "ResponseCode: " + iqSoft_04_apiVariables_credit_response.getResponseCode());
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "null",
                "Description: " + iqSoft_04_apiVariables_credit_response.getDescription());
        softAssert.assertNotEquals(iqSoft_04_apiVariables_credit_response.getBetId(), "null",
                "BetId: " + iqSoft_04_apiVariables_credit_response.getBetId());
        softAssert.assertEquals(betAmountCreditConfig, beforeCredit - afterCredit,
                "betAmountCreditConfig =  beforeCredit-afterCredit" + betAmountCreditConfig + "=" + beforeCredit + "-" + afterCredit);
        softAssert.assertNotEquals(iqSoft_04_apiVariables_credit_response.getClientId(), null,
                "ClientId: " + iqSoft_04_apiVariables_credit_response.getClientId());
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getCurrencyId(), currencyIDConfig,
                "ResponseCurrencyId = CurrencyIDConfig" + iqSoft_04_apiVariables_credit_response.getCurrencyId() + "   " + currencyIDConfig);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getCurrencyId().length(), 3,
                "CurrencyIdLength =" + iqSoft_04_apiVariables_credit_response.getCurrencyId().length());

        boolean balanceHigherOREqualZero = iqSoft_03_apiVariables_getBalance_response.getAvailableBalance() >= 0;
        softAssert.assertEquals(balanceHigherOREqualZero, true);
        softAssert.assertNotEquals(iqSoft_03_apiVariables_getBalance_response.getAvailableBalance(), null);

        String balance = String.valueOf(iqSoft_04_apiVariables_credit_response.getBalance());
        String balanceAfterSplit=null;
        try {
            balanceAfterSplit = balance.split("\\.")[1];
        }
        catch (Exception e){

        }
        if (balanceAfterSplit != null && balanceAfterSplit.length()>2){
            softAssert.fail( "Balance after . has more then 2 symbols");
        }
        else {
            softAssert.assertTrue(true);
        }

        softAssert.assertAll();
    }


}