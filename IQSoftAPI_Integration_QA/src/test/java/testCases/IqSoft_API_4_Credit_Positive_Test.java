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
    int statusCod;


    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        HttpResponse<String> responseGetBalance = getBalanceAPI(AuthorizationTokenVar, currencyConfig);
        jsonObjectBody = new JSONObject(responseGetBalance.getBody());
        beforeCredit = Double.parseDouble(jsonObjectBody.get("Balance").toString());

        HttpResponse<String> responseCredit = creditAPI(AuthorizationTokenVar, currencyConfig, gameIdConfig, 1, ID+"C",betAmountConfig,1);
        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

//        iqSoft_04_apiVariables_credit_response.setBetId(jsonObjectBody.get("BetId").toString());
//        logger.info("Credit API Response BetId : " + iqSoft_04_apiVariables_credit_response.getBetId());
//
//        iqSoft_04_apiVariables_credit_response.setTransactionId(jsonObjectBody.get("TransactionId").toString());
//        logger.info("Credit API Response TransactionId : " + iqSoft_04_apiVariables_credit_response.getTransactionId());
//
//        iqSoft_04_apiVariables_credit_response.setClientId(jsonObjectBody.get("ClientId").toString());
//        logger.info("Credit API Response ClientId : " + iqSoft_04_apiVariables_credit_response.getClientId());
//
//        iqSoft_04_apiVariables_credit_response.setCurrencyId(jsonObjectBody.get("CurrencyId").toString());
//        logger.info("Credit API Response CurrencyId : " + iqSoft_04_apiVariables_credit_response.getCurrencyId());
//
//        iqSoft_04_apiVariables_credit_response.setBalance(Double.parseDouble(jsonObjectBody.get("Balance").toString()));
//        logger.info("Credit API Response Balance : " + iqSoft_04_apiVariables_credit_response.getBalance());
//
//        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        logger.info("Credit API Response ResponseCode : " + iqSoft_04_apiVariables_credit_response.getResponseCode());
//
//        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
//        logger.info("Credit API Response Description : " + iqSoft_04_apiVariables_credit_response.getDescription());
//
//        iqSoft_04_apiVariables_credit_response.setResponseObject((jsonObjectBody.get("ResponseObject").toString()));
//        logger.info("Credit API Response ResponseObject : " + iqSoft_04_apiVariables_credit_response.getResponseObject());

    }

    @Test(priority = 1)
    @Description("Verify Credit API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateStatusCod() {
        logger.info("Credit API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200, statusCod);
    }
//
//    @Test(priority = 2, dependsOnMethods = {"CreditAPIValidateStatusCod"})
//    @Description("Verify Credit API_s Validate Positive Response")
//    @Severity(SeverityLevel.BLOCKER)
//    public void CreditAPIValidatePositiveResponse() {
//        SoftAssert softAssert = new SoftAssert();
//
//        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 0);
//        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "null");
//        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseObject(), "null");
//        softAssert.assertNotEquals(iqSoft_04_apiVariables_credit_response.getBetId(), "null");
//        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getTransactionId(), "null");
//        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getClientId(), String.valueOf(clientId));
//        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getCurrencyId(), currency);
//        double afterCredit = iqSoft_04_apiVariables_credit_response.getBalance();
//        softAssert.assertEquals(afterCredit , beforeCredit-iqSoft_04_apiVariables_credit_request.getAmount());
//
//        softAssert.assertAll();
//    }
//
//
//    double balanceAfter = 0;
//    double check = 0;
//    int num = repeatNum;
//
//    @Test(priority = 20, dependsOnMethods = {"CreditAPIValidateStatusCod"})
//    @Description("Verify Credit API_s Response Balance = Balance - BetAmount * num")
//    public void CreditAPIValidateBalance() throws UnirestException {
//        double balance = iqSoft_04_apiVariables_credit_response.getBalance();
//        HttpResponse<String> responseSecond = creditAPINumTimes(num);
//        jsonObjectBody = new JSONObject(responseSecond.getBody());
//        balanceAfter = Double.parseDouble(jsonObjectBody.get("Balance").toString());
//        check = balance - balanceAfter;
//        Assert.assertEquals(check, (iqSoft_04_apiVariables_credit_request.getAmount()) * num);
//    }
//
//    @Test(priority = 21, dependsOnMethods = {"CreditAPIValidateStatusCod"})
//    @Description("Verify GetBalance API_s Balance after Credit")
//    public void CreditAPIValidateBalanceAfterCreditGetBalance() throws UnirestException {
//        HttpResponse<String> response = getBalanceAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID);
//        jsonObjectBody = new JSONObject(response.getBody());
//        double balanceAfterCredit = Double.parseDouble(jsonObjectBody.get("Balance").toString());
//        Assert.assertEquals(balanceAfterCredit, balanceAfter);
//    }


}