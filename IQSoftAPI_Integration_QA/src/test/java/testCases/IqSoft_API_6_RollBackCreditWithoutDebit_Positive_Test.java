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

public class IqSoft_API_6_RollBackCreditWithoutDebit_Positive_Test extends BaseTest {
    JSONObject jsonObjectBody;
    int statusCod;
    double beforeAll;
    double afterCredit;
    double afterDebit;
    double afterRollBackCredit;

    String CreditTransactionID = randomCreditTransactionID();
    String DebitTransactionID = randomDebitTransactionID();
    int operationTypeIdCredit = 3;
    int operationTypeIdRollBackCredit = 15;

    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        HttpResponse<String> responseGetBalanceBeforeRollBack = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeRollBack.getBody());
        beforeAll = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();
        logger.info("Balance Before Credit:" + beforeAll);


        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, operationTypeIdCredit, CreditTransactionID, betAmountCreditConfig);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        afterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();
        logger.info("Balance After Credit:" + afterCredit);

        HttpResponse<String> response = rollBackAPI(userNameConfig, gameIdConfig, CreditTransactionID, randomRollBackTransactionID(),  operationTypeIdRollBackCredit);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_06_apiVariables_rollBack_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_06_apiVariables_rollBack_response.setDescription(jsonObjectBody.get("Description").toString());

        HttpResponse<String> responseGetBalanceAfter = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfter.getBody());
        afterRollBackCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();
        logger.info("Balance After RollBackCredit:" + afterRollBackCredit);

    }


    @Test(priority = 1)
    @Description("Verify RollBackCredit API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void RollBackAPIValidateStatusCod() {
        logger.info("RollBackCredit API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200, statusCod, "StatusCod: " + statusCod);
    }

    @Test(priority = 2, dependsOnMethods = {"RollBackAPIValidateStatusCod"})
    @Description("Verify RollBackCredit API_s Positive Response")
    @Severity(SeverityLevel.BLOCKER)
    public void RollBackAPIValidatePositiveResponse() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(iqSoft_06_apiVariables_rollBack_response.getResponseCode(), 0,
                "ResponseCode: " + iqSoft_06_apiVariables_rollBack_response.getResponseCode());
//        softAssert.assertEquals(iqSoft_06_apiVariables_rollBack_response.getDescription(), "null",
//                "Description: " + iqSoft_06_apiVariables_rollBack_response.getDescription());
        softAssert.assertAll();
    }

    @Test(priority = 3, dependsOnMethods = {"RollBackAPIValidateStatusCod"})
    @Description("Verify RollBackCredit API_s Positive Response Balance Corrections")
    @Severity(SeverityLevel.BLOCKER)
    public void RollBackAPIValidatePositiveResponseBalanceCorrection() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(afterCredit, beforeAll - betAmountCreditConfig,
                "afterCredit: " + afterCredit + " = beforeAll: " + beforeAll + " - betAmountCreditConfig: " + betAmountCreditConfig);
        softAssert.assertEquals(afterRollBackCredit, beforeAll,
                "afterRollBackCredit: " + afterRollBackCredit + " = beforeAll: " + beforeAll);

        softAssert.assertAll();
    }
}
