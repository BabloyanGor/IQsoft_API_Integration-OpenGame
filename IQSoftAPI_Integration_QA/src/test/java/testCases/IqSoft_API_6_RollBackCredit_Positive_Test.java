package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class IqSoft_API_6_RollBackCredit_Positive_Test extends BaseTest{
    JSONObject jsonObjectBody;
    int statusCod;
    double beforeRollBack;
    double afterRollBack;
    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        HttpResponse<String> responseGetBalanceBeforeDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeDebit.getBody());
        beforeRollBack = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

//
//        HttpResponse<String> response = rollBackAPI(clientIdConfig, currencyIDConfig, gameIdConfig, debitValidTransactionID, creditValidTransactionID,betAmountConfig,2,4,AuthorizationTokenVar);
//        Unirest.shutdown();
//        statusCod = response.getStatus();
//        jsonObjectBody = new JSONObject(response.getBody());
//
//
//        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        logger.info("Debit API Response ResponseCode : " + iqSoft_05_apiVariables_debit_response.getResponseCode());
//
////        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
////        logger.info("Debit API Response Description : " + iqSoft_05_apiVariables_debit_response.getDescription());
//
//        String OperationItems = String.valueOf(jsonObjectBody.getJSONObject("OperationItems"));
//        JSONObject jsonObjectOperationItems = new JSONObject(OperationItems);
//        System.out.println();
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
        afterRollBack = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

    }
    @Test(priority = 1)
    @Description("Verify RollBack API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void RollBackAPIValidateStatusCod() {
        logger.info("RollBack API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200, statusCod);
    }

}
