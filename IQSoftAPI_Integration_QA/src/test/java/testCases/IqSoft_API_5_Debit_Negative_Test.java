package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class IqSoft_API_5_Debit_Negative_Test extends BaseTest {
    JSONObject jsonObjectBody;
    int statusCod;
    int operationTypeIdCredit = 1;
    int operationTypeIdDebit = 1;


//    @Test(priority = 1)
//    @Description("Verify Debit API_s response with Invalid Token")
//    @Severity(SeverityLevel.BLOCKER)
//    public void DebitAPIValidateResponseWithInvalidToken() throws UnirestException, IOException {
//        String creditTransactionID = randomCreditTransactionID();
//        String debitTransactionID = randomDebitTransactionID();
//        SoftAssert softAssert = new SoftAssert();
//
//        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
//        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//
//        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, operationTypeIdCredit,
//                creditTransactionID, betAmountCreditConfig);
//        Unirest.shutdown();
//
//        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
//        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//        HttpResponse<String> response = debitAPI(clientIdConfig, currencyIDConfig, gameIdConfig, debitTransactionID,
//                creditTransactionID, betAmountDebitConfig, 2, operationTypeIdDebit, AuthorizationTokenVar + "_Error");
//        Unirest.shutdown();
//        statusCod = response.getStatus();
//        jsonObjectBody = new JSONObject(response.getBody());
//
//
//        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
////        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
//
//        HttpResponse<String> responseGetBalanceAfterDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit.getBody());
//        double amountAfterDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//
//        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);
//        softAssert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 0,
//                "ResponseCode: " + iqSoft_05_apiVariables_debit_response.getResponseCode());
////        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "error login",
////                "Error Description: " + iqSoft_05_apiVariables_debit_response.getDescription());
//        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit + betAmountCreditConfig,
//                "amountBeforeCredit =  amountAfterCredit + betAmountCreditConfig" + amountBeforeCredit + " != " + amountAfterCredit + betAmountCreditConfig);
//        softAssert.assertEquals(amountAfterCredit, amountAfterDebit,
//                "amountAfterCredit = amountAfterDebit" + amountAfterCredit + " != " + amountAfterDebit);
//
//        softAssert.assertAll();
//
//    }

    @Test(priority = 2)
    @Description("Verify Debit API_s response with Expired Token")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseWithExpiredToken() throws UnirestException, IOException {
        String creditTransactionID = randomCreditTransactionID();
        String debitTransactionID = randomDebitTransactionID();
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, operationTypeIdCredit,
                creditTransactionID, betAmountCreditConfig);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        HttpResponse<String> response = debitAPI(clientIdConfig, currencyIDConfig, gameIdConfig, debitTransactionID,
                creditTransactionID, betAmountDebitConfig, 2, operationTypeIdDebit, expiredSessionTokenConfig);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());


        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());

        HttpResponse<String> responseGetBalanceAfterDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit.getBody());
        double amountAfterDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 0,
                "ResponseCode: " + iqSoft_05_apiVariables_debit_response.getResponseCode());
//        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "error login",
//                "Error Description: " + iqSoft_05_apiVariables_debit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit + betAmountCreditConfig,
                "amountBeforeCredit =  amountAfterCredit + betAmountCreditConfig" + amountBeforeCredit + " != " + amountAfterCredit + betAmountCreditConfig);
        softAssert.assertEquals(amountAfterCredit, amountAfterDebit-betAmountDebitConfig,
                "amountAfterCredit = amountAfterDebit - betAmountDebitConfig" + amountAfterCredit + " != " + amountAfterDebit + " - " + betAmountCreditConfig);

        softAssert.assertAll();

    }


//    @Test(priority = 2)
//    @Description("Verify Debit API_s response with Invalid ProductID")
//    @Severity(SeverityLevel.NORMAL)
//    public void DebitAPIValidateResponseUsingInvalidProductID() throws UnirestException, IOException {
//        String creditTransactionID = randomCreditTransactionID();
//        String debitTransactionID = randomDebitTransactionID();
//        SoftAssert softAssert = new SoftAssert();
//
//        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
//        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//
//        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, operationTypeIdCredit,
//                creditTransactionID, betAmountCreditConfig, 1);
//        Unirest.shutdown();
//
//        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
//        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//
//        HttpResponse<String> response = debitAPI(clientIdConfig, currencyIDConfig, -10, debitTransactionID,
//                creditTransactionID, betAmountDebitConfig, 2, operationTypeIdDebit, AuthorizationTokenVar);
//        Unirest.shutdown();
//        statusCod = response.getStatus();
//        jsonObjectBody = new JSONObject(response.getBody());
//
//
//        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
////        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());
//
//        HttpResponse<String> responseGetBalanceAfterDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit.getBody());
//        double amountAfterDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//
//        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);
//
//        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 0,
//                "ResponseCode: " + iqSoft_05_apiVariables_debit_response.getResponseCode());
////        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "game not available",
////                "Error Description: " + iqSoft_05_apiVariables_debit_response.getDescription());
//        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit + betAmountCreditConfig,
//                "amountBeforeCredit = amountAfterCredit + betAmountCreditConfig" + amountBeforeCredit + " !=  " + amountAfterCredit + "+" + betAmountCreditConfig);
//        softAssert.assertEquals(amountAfterCredit, amountAfterDebit,
//                "amountAfterCredit = amountAfterDebit " + amountAfterCredit + "  =  " + amountAfterDebit);
//        softAssert.assertAll();
//    }


    @Test(priority = 3)
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

        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, operationTypeIdCredit,
                creditTransactionID1, betAmountCreditConfig);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit1 = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit1.getBody());
        double amountAfterCredit1 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, operationTypeIdCredit,
                creditTransactionID2, betAmountCreditConfig);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit2 = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit2.getBody());
        double amountAfterCredit2 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();


        debitAPI(clientIdConfig, currencyIDConfig, gameIdConfig, debitTransactionID,
                creditTransactionID1, betAmountDebitConfig, 2, operationTypeIdDebit, AuthorizationTokenVar);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterDebit1 = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit1.getBody());
        double amountAfterDebit1 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        HttpResponse<String> response = debitAPI(clientIdConfig, currencyIDConfig, gameIdConfig, debitTransactionID,
                creditTransactionID2, betAmountDebitConfig, 2, operationTypeIdDebit, AuthorizationTokenVar);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());

        HttpResponse<String> responseGetBalanceAfterDebit2 = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit2.getBody());
        double amountAfterDebit2 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 0,
                "ResponseCode: " + iqSoft_05_apiVariables_debit_response.getResponseCode());
//        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "null",
//                "Error Description: " + iqSoft_05_apiVariables_debit_response.getDescription());

        softAssert.assertEquals(amountBeforeCredit1, amountAfterCredit1 + betAmountCreditConfig, "amountBeforeCredit1 = amountAfterCredit1+betAmountCreditConfig");
        softAssert.assertEquals(amountAfterCredit1, amountAfterCredit2 + betAmountCreditConfig, "amountAfterCredit1 = amountAfterCredit2+betAmountCreditConfig");
        softAssert.assertEquals(amountAfterCredit2, amountAfterDebit1 - betAmountDebitConfig, "amountAfterCredit2 = amountAfterCredit2+betAmountCreditConfig");
        softAssert.assertEquals(amountAfterDebit1, amountAfterDebit2, "amountAfterDebit2: " + amountAfterDebit2 + " = amountAfterDebit1: " + amountAfterDebit1);

        softAssert.assertAll();
    }

    @Test(priority = 4)
    @Description("Verify Debit API_s response using Invalid Credit Transaction ID")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseUsingInvalidCreditTransactionID() throws UnirestException, IOException {
        String creditTransactionID = "QA_Test-" + RandomStringUtils.randomAlphanumeric(20) + "_C_ErrorID";
        String debitTransactionID = randomDebitTransactionID();
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeDebit.getBody());
        double amountBeforeDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        HttpResponse<String> responseDebit = debitAPI(clientIdConfig, currencyIDConfig, gameIdConfig, debitTransactionID,
                creditTransactionID, betAmountDebitConfig, 2, operationTypeIdDebit, AuthorizationTokenVar);
        Unirest.shutdown();
        statusCod = responseDebit.getStatus();
        jsonObjectBody = new JSONObject(responseDebit.getBody());


        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());

        HttpResponse<String> responseGetBalanceAfterDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit.getBody());
        double amountAfterDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);
        softAssert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 0,
                "ResponseCode: " + iqSoft_05_apiVariables_debit_response.getResponseCode());
//        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "CanNotConnectCreditAndDebit",
//                "Error Description: " + iqSoft_05_apiVariables_debit_response.getDescription());
        softAssert.assertEquals(amountAfterDebit, amountBeforeDebit, "amountAfterDebit: " + amountAfterDebit + " = amountBeforeDebit: " + amountBeforeDebit);

        softAssert.assertAll();

    }


    @Test(priority = 5)
    @Description("Verify Debit API_s response using Debit amount 0")
    @Severity(SeverityLevel.BLOCKER)
    public void DebitAPIValidateResponseUsingAmount_0() throws UnirestException, IOException {

        String creditTransactionID = randomCreditTransactionID();
        String debitTransactionID = randomDebitTransactionID();
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, operationTypeIdCredit,
                creditTransactionID, betAmountCreditConfig);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        HttpResponse<String> response = debitAPI(clientIdConfig, currencyIDConfig, gameIdConfig, debitTransactionID,
                creditTransactionID, 0, 2, operationTypeIdDebit, AuthorizationTokenVar );
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());


        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());

        HttpResponse<String> responseGetBalanceAfterDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit.getBody());
        double amountAfterDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);
        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 0,
                "ResponseCode: " + iqSoft_05_apiVariables_debit_response.getResponseCode());
//        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "error login",
//                "Error Description: " + iqSoft_05_apiVariables_debit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit + betAmountCreditConfig,
                "amountBeforeCredit =  amountAfterCredit + betAmountCreditConfig" + amountBeforeCredit + " != " + amountAfterCredit + betAmountCreditConfig);
        softAssert.assertEquals(amountAfterCredit, amountAfterDebit,
                "amountAfterCredit = amountAfterDebit" + amountAfterCredit + " != " + amountAfterDebit);

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

        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, operationTypeIdCredit,
                creditTransactionID, betAmountCreditConfig);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        HttpResponse<String> response = debitAPI(clientIdConfig, currencyIDConfig, gameIdConfig, debitTransactionID,
                creditTransactionID, errorAmount, 2, operationTypeIdDebit, AuthorizationTokenVar);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());


        iqSoft_05_apiVariables_debit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        iqSoft_05_apiVariables_debit_response.setDescription(jsonObjectBody.get("Description").toString());

        HttpResponse<String> responseGetBalanceAfterDebit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterDebit.getBody());
        double amountAfterDebit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);
        softAssert.assertNotEquals(iqSoft_05_apiVariables_debit_response.getResponseCode(), 0,
                "ResponseCode: " + iqSoft_05_apiVariables_debit_response.getResponseCode());
//        softAssert.assertEquals(iqSoft_05_apiVariables_debit_response.getDescription(), "WrongOperationAmount",
//                "Error Description: " + iqSoft_05_apiVariables_debit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit + betAmountCreditConfig);
        softAssert.assertEquals(amountAfterCredit, amountAfterDebit);

        softAssert.assertAll();

    }

    @DataProvider(name = "invalidAmount")
    Object[][] invalidAmount() {
        Double[][] arr = {
                {-1.0},
                {-1.5},
                {-1.05},
                {-1.005}
        };
        return arr;
    }

}
