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

public class IqSoft_API_6_RollBackCreditWithDebit_Positive_Test extends BaseTest{
    JSONObject jsonObjectBody;
    int statusCod;
    double beforeAll;
    double afterCredit;
    double afterDebit;
    double afterRollBackCredit;

    String CreditTransactionID = randomCreditTransactionID();
    String DebitTransactionID = randomDebitTransactionID();
    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        HttpResponse<String> responseGetBalanceBeforeRollBack = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeRollBack.getBody());
        beforeAll = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        logger.info("Balance Before Credit:" + beforeAll);


        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, 1, CreditTransactionID,betAmountCreditConfig,1);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        afterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        logger.info("Balance After Credit:" + afterCredit);


        debitAPI(clientIdConfig, currencyIDConfig, gameIdConfig, DebitTransactionID, CreditTransactionID,betAmountDebitConfig,2,
                4,AuthorizationTokenVar);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit.getBody());
        afterDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        logger.info("Balance After Debit:" + afterDebit);

        HttpResponse<String> response = rollBackAPI(userNameConfig, gameIdConfig, CreditTransactionID, randomRollBackTransactionID(), AuthorizationTokenVar,4);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_06_apiVariables_rollBack_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("RollBackCredit API Response ResponseCode : " + iqSoft_06_apiVariables_rollBack_response.getResponseCode());

        iqSoft_06_apiVariables_rollBack_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("RollBackCredit API Response Description : " + iqSoft_06_apiVariables_rollBack_response.getDescription());



        HttpResponse<String> responseGetBalanceAfter = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfter.getBody());
        afterRollBackCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        logger.info("Balance After RollBackCredit:" + afterRollBackCredit);

    }


    @Test(priority = 1)
    @Description("Verify RollBackCredit API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void RollBackAPIValidateStatusCod() {
        logger.info("RollBackCredit API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200, statusCod);
    }

    @Test(priority = 2, dependsOnMethods = {"RollBackAPIValidateStatusCod"})
    @Description("Verify RollBackCredit API_s Positive Response")
    @Severity(SeverityLevel.BLOCKER)
    public void RollBackAPIValidatePositiveResponse() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(iqSoft_06_apiVariables_rollBack_response.getResponseCode(), 0);
        softAssert.assertEquals(iqSoft_06_apiVariables_rollBack_response.getDescription(), "null");
        softAssert.assertAll();
    }

    @Test(priority = 3, dependsOnMethods = {"RollBackAPIValidateStatusCod"})
    @Description("Verify RollBackCredit API_s Positive Response Balance Corrections")
    @Severity(SeverityLevel.BLOCKER)
    public void RollBackAPIValidatePositiveResponseBalanceCorrection() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(afterCredit, beforeAll - betAmountCreditConfig, "Verify Balance After Credit ");
        softAssert.assertEquals(afterDebit,  afterCredit + betAmountDebitConfig, "Verify Balance After Debit ");
        softAssert.assertEquals(afterRollBackCredit, beforeAll , "Verify Balance After RollBackCredit ");

        softAssert.assertAll();
    }

}