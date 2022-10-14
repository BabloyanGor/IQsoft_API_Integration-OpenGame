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

public class IqSoft_API_5_Debit_Positive_Test extends BaseTest{

    JSONObject jsonObjectBody;
    int statusCod;
    double beforeDebit;
    double afterDebit;
    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        HttpResponse<String> responseGetBalanceBeforeDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeDebit.getBody());
        beforeDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        HttpResponse<String> response = debitAPI(clientIdConfig, currencyIDConfig, gameIdConfig, debitValidTransactionID,
                                                creditValidTransactionID,betAmountDebitConfig,2,4,AuthorizationTokenVar);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());


        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());


        JSONArray jsonArrayOperationItems = jsonObjectBody.getJSONArray("OperationItems");
        for (int j = 0; j < jsonArrayOperationItems.length(); j++) {
            String first = String.valueOf(jsonArrayOperationItems.get(j));
            JSONObject jsonObjectGame = new JSONObject(first);

            iqSoft_05_apiVariables_debit_response.setBetId(jsonObjectGame.get("BetId").toString());
            iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectGame.get("Balance").toString()));
            iqSoft_05_apiVariables_debit_response.setClientId(jsonObjectGame.get("ClientId").toString());
            iqSoft_05_apiVariables_debit_response.setCurrencyId(jsonObjectGame.get("CurrencyId").toString());
        }

//        String OperationItems = String.valueOf(jsonObjectBody.getJSONObject("OperationItems"));
//        JSONObject jsonObjectOperationItems = new JSONObject(OperationItems);
//
//        iqSoft_05_apiVariables_debit_response.setBetId(jsonObjectOperationItems.get("BetId").toString());
//        logger.info("Debit API Response BetId : " + iqSoft_05_apiVariables_debit_response.getBetId());
//
//        iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectOperationItems.get("Balance").toString()));
//        logger.info("Debit API Response Balance : " + iqSoft_05_apiVariables_debit_response.getBalance());
//
//        iqSoft_05_apiVariables_debit_response.setClientId(jsonObjectOperationItems.get("ClientId").toString());
//        logger.info("Debit API Response ClientId : " + iqSoft_05_apiVariables_debit_response.getClientId());
//
//        iqSoft_05_apiVariables_debit_response.setCurrencyId(jsonObjectOperationItems.get("CurrencyId").toString());
//        logger.info("Debit API Response CurrencyId : " + iqSoft_05_apiVariables_debit_response.getCurrencyId());

        HttpResponse<String> responseGetBalanceAfter = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfter.getBody());
        afterDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

    }


    @Test(priority = 1)
    @Description("Verify Debit API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateStatusCod() {
        logger.info("Debit API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200, statusCod, "StatusCod: " + statusCod);
    }


    @Test(priority = 2, dependsOnMethods = {"DebitAPIValidateStatusCod"})
    @Description("Verify Debit API_s Validate Positive Response")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidatePositiveResponse() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 0,
                "ResponseCode: " + iqSoft_05_apiVariables_debit_response.getResponseCode());
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "null",
                "Description: " + iqSoft_05_apiVariables_debit_response.getDescription());
        softAssert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getBetId(), "null",
                "BetID: " + iqSoft_05_apiVariables_debit_response.getBetId());
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getClientId(), iqSoft_05_apiVariables_debit_request.getClientId(),
                "Response ClientId: " + iqSoft_05_apiVariables_debit_response.getClientId()+ " = RequestClientId: " + iqSoft_05_apiVariables_debit_request.getClientId());
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getCurrencyId(), currencyIDConfig,
                "CurrencyId: " + iqSoft_05_apiVariables_debit_response.getCurrencyId() + " = currencyIDConfig: "+ currencyIDConfig);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getCurrencyId().length(), 3,
                "CurrencyId Length: " + iqSoft_05_apiVariables_debit_response.getCurrencyId());
        softAssert.assertEquals(betAmountDebitConfig , afterDebit-beforeDebit);

        softAssert.assertAll();
    }




}
