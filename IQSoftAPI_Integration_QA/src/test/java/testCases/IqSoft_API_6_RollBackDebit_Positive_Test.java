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

public class IqSoft_API_6_RollBackDebit_Positive_Test extends BaseTest{
    JSONObject jsonObjectBody;
    int statusCod;
    double beforeRollBackDebit;
    double afterRollBackDebit;
    @BeforeClass
    public void setUp() throws UnirestException, IOException {
        HttpResponse<String> responseGetBalanceBeforeDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeDebit.getBody());
        beforeRollBackDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());


        HttpResponse<String> response = rollBackAPI(userNameConfig, gameIdConfig, rollBackValidTransactionIDDebit, debitValidTransactionID, AuthorizationTokenVar,4);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());


        iqSoft_06_apiVariables_rollBack_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("RollBack API Response ResponseCode : " + iqSoft_06_apiVariables_rollBack_response.getResponseCode());

        iqSoft_06_apiVariables_rollBack_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("RollBack API Response Description : " + iqSoft_06_apiVariables_rollBack_response.getDescription());



        HttpResponse<String> responseGetBalanceAfter = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfter.getBody());
        afterRollBackDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

    }


    @Test(priority = 1)
    @Description("Verify RollBack API_s Response Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void RollBackAPIValidateStatusCod() {
        logger.info("RollBack API Status Cod is Equal: " + statusCod);
        Assert.assertEquals(200, statusCod);
    }

    @Test(priority = 2, dependsOnMethods = {"RollBackAPIValidateStatusCod"})
    @Description("Verify RollBack API_s Positive Response")
    @Severity(SeverityLevel.BLOCKER)
    public void RollBackAPIValidatePositiveResponse() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(iqSoft_06_apiVariables_rollBack_response.getResponseCode(), 0);
        softAssert.assertEquals(iqSoft_06_apiVariables_rollBack_response.getDescription(), "null");
//        softAssert.assertEquals(beforeRollBackDebit , );


        softAssert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getBetId(), "null");
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getClientId(), iqSoft_05_apiVariables_debit_request.getClientId());
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getCurrencyId(), currencyIDConfig);
        softAssert.assertEquals(betAmountConfig ,  iqSoft_05_apiVariables_debit_response.getBalance()-iqSoft_05_apiVariables_debit_request.getAmount());

        softAssert.assertAll();
    }
}
