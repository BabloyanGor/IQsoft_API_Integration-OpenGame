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

public class IqSoft_API_5_Debit_Positive_Test extends BaseTest{

    JSONObject jsonObjectBody;
    int statusCod;
    double beforeDebit;

    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        HttpResponse<String> responseGetBalance = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
        jsonObjectBody = new JSONObject(responseGetBalance.getBody());
        beforeDebit = Double.parseDouble(jsonObjectBody.get("Balance").toString());

        HttpResponse<String> response = debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID, betAmount, ID, ID+"D",ID+"C",currency);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_05_apiVariables_debit_response.setBetId(jsonObjectBody.get("BetId").toString());
        logger.info("Debit API Response BetId : " + iqSoft_05_apiVariables_debit_response.getBetId());

        iqSoft_05_apiVariables_debit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
        logger.info("Debit API Response TransactionId : " + iqSoft_05_apiVariables_debit_response.getTransactionId());

        iqSoft_05_apiVariables_debit_response.setClientId(jsonObjectBody.get("ClientId").toString());
        logger.info("Debit API Response ClientId : " + iqSoft_05_apiVariables_debit_response.getClientId());

        iqSoft_05_apiVariables_debit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
        logger.info("Debit API Response CurrencyId : " + iqSoft_05_apiVariables_debit_response.getCurrencyId());

        iqSoft_05_apiVariables_debit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
        logger.info("Debit API Response Balance : " + iqSoft_05_apiVariables_debit_response.getBalance());

        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Debit API Response ResponseCode : " + iqSoft_05_apiVariables_debit_response.getResponseCode());

        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Debit API Response Description : " + iqSoft_05_apiVariables_debit_response.getDescription());

        iqSoft_05_apiVariables_debit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));
        logger.info("Debit API Response ResponseObject : " + iqSoft_05_apiVariables_debit_response.getResponseObject());

    }


    @Test(priority = 1)
    @Description("Verify Credit API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateStatusCod() {
        logger.info("Debit API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200, statusCod);
    }

    @Test(priority = 2, dependsOnMethods = {"DebitAPIValidateStatusCod"})
    @Description("Verify Debit API_s Validate Positive Response")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidatePositiveResponse() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 0);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseObject(), "null");
        softAssert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getBetId(), "null");
        softAssert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getTransactionId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getClientId(), String.valueOf(clientId));
        softAssert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getCurrencyId(), "null");
        double afterCredit = iqSoft_04_apiVariables_credit_response.getBalance();
        softAssert.assertEquals(afterCredit , afterCredit+iqSoft_04_apiVariables_credit_request.getAmount());

        softAssert.assertAll();

    }


    double balanceAfter = 0;
    double check = 0;
    int num = repeatNum;

    @Test(priority = 20, dependsOnMethods = {"DebitAPIValidateStatusCod"})
    @Description("Verify Debit API_s Response Balance = Balance - BetAmount * num")
    public void CreditAPIValidateBalance() throws UnirestException {
        double balance = iqSoft_05_apiVariables_debit_response.getBalance();
        HttpResponse<String> responseSecond = debitAPINumTimes();
        jsonObjectBody = new JSONObject(responseSecond.getBody());
        balanceAfter = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        check =  balanceAfter - balance;
        Assert.assertEquals(check, (iqSoft_05_apiVariables_debit_request.getAmount())*num);
    }

    @Test(priority = 21, dependsOnMethods = {"DebitAPIValidateStatusCod"})
    @Description("Verify GetBalance API_s Balance after Debit")
    public void CreditAPIValidateBalanceAfterCreditGetBalance() throws UnirestException {
        HttpResponse<String> response = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(),4);
        jsonObjectBody = new JSONObject(response.getBody());
        double balanceAfterCredit = Double.parseDouble(jsonObjectBody.get("Balance").toString());
        Assert.assertEquals(balanceAfterCredit,balanceAfter);
    }

}
