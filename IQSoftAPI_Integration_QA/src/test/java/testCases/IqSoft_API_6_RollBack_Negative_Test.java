package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;


public class IqSoft_API_6_RollBack_Negative_Test extends BaseTest {
    JSONObject jsonObjectBody;
    int statusCod;

//    @Test(priority = 1)
//    @Description("Verify RollBack API_s response with Expired Token")
//    @Severity(SeverityLevel.BLOCKER)
//    public void RollBackAPIValidateResponseWithExpiredToken() throws UnirestException, IOException {
//        String creditTransactionID = randomCreditTransactionID();
//        String rollBackTransactionID = randomRollBackTransactionID();
//        SoftAssert softAssert = new SoftAssert();
//
//        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
//        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//        logger.info("Balance Before Credit:" + amountBeforeCredit);
//
//        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, 1,
//                creditTransactionID, betAmountCreditConfig, 1);
//        Unirest.shutdown();
//
//        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
//        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//        logger.info("Balance After Credit:" + amountAfterCredit);
//
//
//        HttpResponse<String> response = rollBackAPI(userNameConfig, gameIdConfig, creditTransactionID, rollBackTransactionID, expiredSessionTokenConfig, 15);
//        Unirest.shutdown();
//        statusCod = response.getStatus();
//        jsonObjectBody = new JSONObject(response.getBody());
//
//        iqSoft_06_apiVariables_rollBack_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        iqSoft_06_apiVariables_rollBack_response.setDescription(jsonObjectBody.get("Description").toString());
//
//        HttpResponse<String> responseGetBalanceAfterRollBack = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceAfterRollBack.getBody());
//        double amountAfterRollBack = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//        logger.info("Balance After RollBack:" + amountAfterRollBack);
//
//
//        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);
//
//        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit + betAmountCreditConfig,
//                "amountBeforeCredit: " + amountBeforeCredit + " = amountAfterCredit : " + amountAfterCredit + " + betAmountCreditConfig : " + betAmountCreditConfig);
//        softAssert.assertEquals(amountAfterRollBack, amountBeforeCredit,
//                "amountAfterRollBack: " + amountAfterRollBack + " = amountBeforeCredit: " + amountBeforeCredit);
//
//        softAssert.assertAll();
//
//    }


//    @Test(priority = 2)
//    @Description("Verify RollBack API_s response with Invalid Token")
//    @Severity(SeverityLevel.BLOCKER)
//    public void RollBackAPIValidateResponseWithInvalidToken() throws UnirestException, IOException {
//        String creditTransactionID = randomCreditTransactionID();
//        String rollBackTransactionID = randomRollBackTransactionID();
//        SoftAssert softAssert = new SoftAssert();
//
//        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
//        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//        logger.info("Balance Before Credit:" + amountBeforeCredit);
//
//        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, 1,
//                creditTransactionID, betAmountCreditConfig);
//        Unirest.shutdown();
//
//        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
//        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//        logger.info("Balance After Credit:" + amountAfterCredit);
//
//
//        HttpResponse<String> response = rollBackAPI(userNameConfig, gameIdConfig, creditTransactionID, rollBackTransactionID, AuthorizationTokenVar + "_Error", 15);
//        Unirest.shutdown();
//        statusCod = response.getStatus();
//        jsonObjectBody = new JSONObject(response.getBody());
//
//        iqSoft_06_apiVariables_rollBack_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        iqSoft_06_apiVariables_rollBack_response.setDescription(jsonObjectBody.get("Description").toString());
//
//        HttpResponse<String> responseGetBalanceAfterRollBack = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceAfterRollBack.getBody());
//        double amountAfterRollBack = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//        logger.info("Balance After RollBack:" + amountAfterRollBack);
//
//
//        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);
//
//        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit + betAmountCreditConfig,
//                "amountBeforeCredit: " + amountBeforeCredit + " = amountAfterCredit : " + amountAfterCredit + " + betAmountCreditConfig : " + betAmountCreditConfig);
//        softAssert.assertEquals(amountAfterRollBack, amountAfterCredit,
//                "amountAfterRollBack: " + amountAfterRollBack + " = amountAfterCredit: " + amountAfterCredit);
//        softAssert.assertAll();
//
//    }



//    @Test(priority = 3)
//    @Description("Verify RollBack API_s response with Invalid ProductID")
//    @Severity(SeverityLevel.NORMAL)
//    public void RollBackAPIValidateResponseUsingInvalidProductID() throws UnirestException, IOException {
//        String creditTransactionID = randomCreditTransactionID();
//        String debitTransactionID = randomDebitTransactionID();
//        String rollBackTransactionID = randomRollBackTransactionID();
//        SoftAssert softAssert = new SoftAssert();
//
//        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
//        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//        logger.info("Balance Before Credit:" + amountBeforeCredit);
//
//        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, 1,
//                creditTransactionID, betAmountCreditConfig, 1);
//        Unirest.shutdown();
//
//        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
//        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//        logger.info("Balance After Credit:" + amountAfterCredit);
//
//
//        HttpResponse<String> response = rollBackAPI(userNameConfig, -10, creditTransactionID, rollBackTransactionID, AuthorizationTokenVar, 15);
//        Unirest.shutdown();
//        statusCod = response.getStatus();
//        jsonObjectBody = new JSONObject(response.getBody());
//
//        iqSoft_06_apiVariables_rollBack_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        iqSoft_06_apiVariables_rollBack_response.setDescription(jsonObjectBody.get("Description").toString());
//
//        HttpResponse<String> responseGetBalanceAfterRollBack = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceAfterRollBack.getBody());
//        double amountAfterRollBack = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//        logger.info("Balance After RollBack:" + amountAfterRollBack);
//
//
//        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);
//        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit + betAmountCreditConfig,
//                "amountBeforeCredit: " + amountBeforeCredit + " = amountAfterCredit : " + amountAfterCredit + " + betAmountCreditConfig : " + betAmountCreditConfig);
//        softAssert.assertEquals(amountAfterRollBack, amountAfterCredit,
//                "amountAfterRollBack : " + amountAfterRollBack + " = amountAfterCredit: " + amountAfterCredit);
//        softAssert.assertAll();
//
//    }


    @Test(priority = 4)
    @Description("Verify RollBack API_s response with Invalid TransactionID")
    @Severity(SeverityLevel.BLOCKER)
    public void RollBackAPIValidateResponseUsingSameTransactionIDForRollBackAndCredit() throws UnirestException, IOException {
        String creditTransactionID = randomCreditTransactionID();
        String rollBackTransactionID = randomRollBackTransactionID();
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeRollBack = getBalanceAPI(AuthorizationTokenVar, AuthorizationCurrencyId);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeRollBack.getBody());
        double amountBeforeRollBack = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();
        logger.info("Balance Before RollBack:" + amountBeforeRollBack);

        HttpResponse<String> response = rollBackAPI(AuthorizationUserName, gameIdConfig, creditTransactionID, rollBackTransactionID,  15);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_06_apiVariables_rollBack_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_06_apiVariables_rollBack_response.setDescription(jsonObjectBody.get("Description").toString());

        HttpResponse<String> responseGetBalanceAfterRollBack = getBalanceAPI(AuthorizationTokenVar, AuthorizationCurrencyId);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterRollBack.getBody());
        double amountAfterRollBack = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();
        logger.info("Balance After RollBack:" + amountAfterRollBack);


        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);

        softAssert.assertEquals(amountAfterRollBack, amountBeforeRollBack,
                "amountAfterRollBack: " + amountAfterRollBack + " = amountBeforeRollBack: " + amountBeforeRollBack);
        softAssert.assertAll();
    }


    @Test(priority = 5)
    @Description("Verify RollBack API_s response using Same RollBackTransactionID twice ")
    @Severity(SeverityLevel.BLOCKER)
    public void RollBackAPIValidateResponseUsingSameCreditTransactionIDTwice() throws UnirestException, IOException {
        String creditTransactionID = randomCreditTransactionID();
        String rollBackTransactionID1 = randomRollBackTransactionID();
        String rollBackTransactionID2 = randomRollBackTransactionID();
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, AuthorizationCurrencyId);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();
        logger.info("Balance Before Credit:" + amountBeforeCredit);

        creditAPI(AuthorizationTokenVar, AuthorizationCurrencyId, gameIdConfig, 1,
                creditTransactionID, betAmountCreditConfig);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, AuthorizationCurrencyId);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();
        logger.info("Balance After Credit:" + amountAfterCredit);


        rollBackAPI(AuthorizationUserName, gameIdConfig, creditTransactionID, rollBackTransactionID1,  15);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterRollBack1 = getBalanceAPI(AuthorizationTokenVar, AuthorizationCurrencyId);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterRollBack1.getBody());
        double amountAfterRollBack1 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();
        logger.info("Balance After RollBack_1:" + amountAfterRollBack1);

        HttpResponse<String> response = rollBackAPI(AuthorizationUserName, gameIdConfig, creditTransactionID, rollBackTransactionID2,  4);
        Unirest.shutdown();

        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_06_apiVariables_rollBack_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_06_apiVariables_rollBack_response.setDescription(jsonObjectBody.get("Description").toString());

        HttpResponse<String> responseGetBalanceAfterRollBack2 = getBalanceAPI(AuthorizationTokenVar, AuthorizationCurrencyId);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterRollBack2.getBody());
        double amountAfterRollBack2 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();
        logger.info("Balance After RollBack_2::" + amountAfterRollBack2);

        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);

        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit + betAmountCreditConfig,
                "amountBeforeCredit: " + amountBeforeCredit + " = amountAfterCredit : " + amountAfterCredit + " + betAmountCreditConfig : " + betAmountCreditConfig);
        softAssert.assertEquals(amountAfterRollBack1, amountBeforeCredit,
                "amountAfterRollBack1: " + amountAfterRollBack1 + " = amountBeforeCredit : " + amountBeforeCredit);
        softAssert.assertEquals(amountAfterRollBack2, amountAfterRollBack1,
                "amountAfterRollBack2 : " + amountAfterRollBack2 + " = amountAfterRollBack1: " + amountAfterRollBack1);
        softAssert.assertAll();
    }


    @Test(priority = 6)
    @Description("Verify RollBack API_s response using Same TransactionID twice ")
    @Severity(SeverityLevel.BLOCKER)
    public void RollBackAPIValidateResponseUsingSameRollBackTransactionIDTwice() throws UnirestException, IOException {
        String creditTransactionID1 = randomCreditTransactionID();
        String creditTransactionID2 = randomCreditTransactionID();
        String rollBackTransactionID = randomRollBackTransactionID();

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit1 = getBalanceAPI(AuthorizationTokenVar, AuthorizationCurrencyId);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit1.getBody());
        double amountBeforeCredit1 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();
        logger.info("Balance Before Credit_1:" + amountBeforeCredit1);

        creditAPI(AuthorizationTokenVar, AuthorizationCurrencyId, gameIdConfig, 1,
                creditTransactionID1, betAmountCreditConfig);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit1 = getBalanceAPI(AuthorizationTokenVar, AuthorizationCurrencyId);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit1.getBody());
        double amountAfterCredit1 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();
        logger.info("Balance After Credit_1:" + amountAfterCredit1);

        creditAPI(AuthorizationTokenVar, AuthorizationCurrencyId, gameIdConfig, 1,
                creditTransactionID2, betAmountCreditConfig);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit2 = getBalanceAPI(AuthorizationTokenVar, AuthorizationCurrencyId);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit2.getBody());
        double amountAfterCredit2 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();
        logger.info("Balance After Credit_2:" + amountAfterCredit2);

        rollBackAPI(AuthorizationUserName, gameIdConfig, creditTransactionID1, rollBackTransactionID,  15);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterRollBack1 = getBalanceAPI(AuthorizationTokenVar, AuthorizationCurrencyId);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterRollBack1.getBody());
        double amountAfterRollBack1 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();
        logger.info("Balance After RollBack_1:" + amountAfterRollBack1);

        HttpResponse<String> responseRollBack = rollBackAPI(AuthorizationUserName, gameIdConfig, creditTransactionID2, rollBackTransactionID,  4);
        Unirest.shutdown();

        statusCod = responseRollBack.getStatus();
        jsonObjectBody = new JSONObject(responseRollBack.getBody());

        iqSoft_06_apiVariables_rollBack_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_06_apiVariables_rollBack_response.setDescription(jsonObjectBody.get("Description").toString());

        HttpResponse<String> responseGetBalanceAfterRollBack2 = getBalanceAPI(AuthorizationTokenVar, AuthorizationCurrencyId);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterRollBack2.getBody());
        double amountAfterRollBack2 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();
        logger.info("Balance After RollBack_2:" + amountAfterRollBack2);

        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);

        softAssert.assertEquals(amountBeforeCredit1, amountAfterCredit1 + betAmountCreditConfig,
                "amountBeforeCredit1: " + amountBeforeCredit1 + " = amountAfterCredit1 : " + amountAfterCredit1 + " + betAmountCreditConfig : " + betAmountCreditConfig);
        softAssert.assertEquals(amountAfterCredit1, amountAfterCredit2 + betAmountCreditConfig,
                "amountAfterCredit1: " + amountAfterCredit1 + " = amountAfterCredit2 : " + amountAfterCredit2 + " + betAmountCreditConfig : " + betAmountCreditConfig);
        softAssert.assertEquals(amountAfterRollBack1, amountAfterCredit2 + betAmountCreditConfig,
                "amountAfterRollBack1: " + amountAfterRollBack1 + " = amountAfterCredit2 : " + amountAfterCredit2 + " + betAmountCreditConfig : " + betAmountCreditConfig);
        softAssert.assertEquals(amountAfterRollBack1, amountAfterRollBack2,
                "amountAfterRollBack1: " + amountAfterRollBack1 + " = amountAfterRollBack2 : " + amountAfterRollBack2);
        softAssert.assertAll();
    }


}
