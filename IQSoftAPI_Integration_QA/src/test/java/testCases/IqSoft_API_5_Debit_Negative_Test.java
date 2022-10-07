package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;

public class IqSoft_API_5_Debit_Negative_Test extends BaseTest {
    JSONObject jsonObjectBody;
    int statusCod;


    @Test(priority = 2)
    @Description("Verify Debit API_s response with Invalid Token")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseWithInvalidToken() throws UnirestException, IOException {
        String creditTransactionID = randomCreditTransactionID();
        String debitTransactionID = randomDebitTransactionID();
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, 1,
                creditTransactionID , betAmountCreditConfig,1);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();
        HttpResponse<String> response = debitAPI(clientIdConfig, currencyIDConfig, gameIdConfig, debitTransactionID,
                creditTransactionID,betAmountDebitConfig,2,4, AuthorizationTokenVar+"1");
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());


        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Debit API Response ResponseCode : " + iqSoft_05_apiVariables_debit_response.getResponseCode());

        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Debit API Response Description : " + iqSoft_05_apiVariables_debit_response.getDescription());

        HttpResponse<String> responseGetBalanceAfterDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit.getBody());
        double amountAfterDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 22);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "error login",
                "Error Description: " + iqSoft_05_apiVariables_debit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit+betAmountCreditConfig);
        softAssert.assertEquals(amountAfterCredit, amountAfterDebit);

        softAssert.assertAll();

    }


    @Test(priority = 3)
    @Description("Verify Debit API_s response with Invalid ProductID")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseUsingInvalidProductID() throws UnirestException, IOException {
        String creditTransactionID = randomCreditTransactionID();
        String debitTransactionID = randomDebitTransactionID();
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, 1,
                creditTransactionID , betAmountCreditConfig,1);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        HttpResponse<String> response = debitAPI(clientIdConfig, currencyIDConfig, -10, debitTransactionID,
                creditTransactionID,betAmountDebitConfig,2,4, AuthorizationTokenVar);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());


        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Debit API Response ResponseCode : " + iqSoft_05_apiVariables_debit_response.getResponseCode());

        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Debit API Response Description : " + iqSoft_05_apiVariables_debit_response.getDescription());

        HttpResponse<String> responseGetBalanceAfterDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit.getBody());
        double amountAfterDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 43);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "game not available",
                "Error Description: " + iqSoft_05_apiVariables_debit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit+betAmountCreditConfig);
        softAssert.assertEquals(amountAfterCredit, amountAfterDebit);

        softAssert.assertAll();
    }


    @Test(priority = 4)
    @Description("Verify Debit API_s response using same DebitTransactionID twice")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseUsingTransactionIDTwice() throws UnirestException, IOException {

        String creditTransactionID1 = randomCreditTransactionID();
        String creditTransactionID2 = randomCreditTransactionID();
        String debitTransactionID = randomDebitTransactionID();
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double amountBeforeCredit1 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, 1,
                creditTransactionID1 , betAmountCreditConfig,1);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit1 = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit1.getBody());
        double amountAfterCredit1 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, 1,
                creditTransactionID2 , betAmountCreditConfig,1);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit2 = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit2.getBody());
        double amountAfterCredit2 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();


        debitAPI(clientIdConfig, currencyIDConfig, -10, debitTransactionID,
                creditTransactionID1,betAmountDebitConfig,2,4, AuthorizationTokenVar);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterDebit1 = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit1.getBody());
        double amountAfterDebit1 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        HttpResponse<String> response = debitAPI(clientIdConfig, currencyIDConfig, -10, debitTransactionID,
                creditTransactionID2,betAmountDebitConfig,2,4, AuthorizationTokenVar);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Debit API Response ResponseCode : " + iqSoft_05_apiVariables_debit_response.getResponseCode());

        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Debit API Response Description : " + iqSoft_05_apiVariables_debit_response.getDescription());

        HttpResponse<String> responseGetBalanceAfterDebit2 = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit2.getBody());
        double amountAfterDebit2 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 69);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "ClientDocumentAlreadyExists",
                "Error Description: " + iqSoft_05_apiVariables_debit_response.getDescription());

        softAssert.assertEquals(amountBeforeCredit1, amountAfterCredit1+betAmountCreditConfig);
        softAssert.assertEquals(amountAfterCredit1, amountAfterCredit2+betAmountCreditConfig);
        softAssert.assertEquals(amountAfterCredit2, amountAfterDebit1-betAmountDebitConfig);
        softAssert.assertEquals(amountAfterDebit1, amountAfterDebit2);

        softAssert.assertAll();
    }

    @Test(priority = 5)
    @Description("Verify Debit API_s response using Invalid Amount")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseUsingInvalidCreditTransactionID() throws UnirestException, IOException {
        String creditTransactionID = "QA_Test-" + RandomStringUtils.randomAlphanumeric(20) + "_C_ErrorID";
        String debitTransactionID = randomDebitTransactionID();
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeDebit.getBody());
        double amountBeforeDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        HttpResponse<String> responseDebit = debitAPI(clientIdConfig, currencyIDConfig, gameIdConfig,debitTransactionID,
                creditTransactionID , betAmountDebitConfig,2,4, AuthorizationTokenVar);
        Unirest.shutdown();
        statusCod = responseDebit.getStatus();
        jsonObjectBody = new JSONObject(responseDebit.getBody());


        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Debit API Response ResponseCode : " + iqSoft_05_apiVariables_debit_response.getResponseCode());

        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Debit API Response Description : " + iqSoft_05_apiVariables_debit_response.getDescription());

        HttpResponse<String> responseGetBalanceAfterDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit.getBody());
        double amountAfterDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 50);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "CanNotConnectCreditAndDebit",
                "Error Description: " + iqSoft_05_apiVariables_debit_response.getDescription());
        softAssert.assertEquals(amountBeforeDebit, amountAfterDebit);

        softAssert.assertAll();

    }

    @Test(priority = 6, dataProvider = "invalidAmount")
    @Description("Verify Debit API_s response using Invalid Amount")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseUsingInvalidAmount(Double errorAmount) throws UnirestException, IOException {
        String creditTransactionID = randomCreditTransactionID();
        String debitTransactionID = randomDebitTransactionID();
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, 1,
                creditTransactionID , betAmountCreditConfig,1);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        HttpResponse<String> response = debitAPI(clientIdConfig, currencyIDConfig, gameIdConfig, debitTransactionID,
                creditTransactionID,errorAmount,2,4, AuthorizationTokenVar);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());


        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Debit API Response ResponseCode : " + iqSoft_05_apiVariables_debit_response.getResponseCode());

        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Debit API Response Description : " + iqSoft_05_apiVariables_debit_response.getDescription());

        HttpResponse<String> responseGetBalanceAfterDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit.getBody());
        double amountAfterDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

        softAssert.assertEquals(statusCod, 200);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 39);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "WrongOperationAmount",
                "Error Description: " + iqSoft_05_apiVariables_debit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit+betAmountCreditConfig);
        softAssert.assertEquals(amountAfterCredit, amountAfterDebit);

        softAssert.assertAll();

    }

    @DataProvider(name = "invalidAmount")
    Object[][] invalidAmount() {
        Double[][] arr = {
                {0.0},
                {-1.0},
                {-1.5},
                {-1.05},
                {-1.005}
        };
        return arr;
    }

}
