package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;

public class IqSoft_API_4_Credit_Negative_Test extends BaseTest {
    JSONObject jsonObjectBody;
    int statusCod;
    int operationTypeIdCredit = 1;

    @Test(priority = 1)
    @Description("Verify Credit API_s response with Expired Token")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseWithExpiredToken() throws UnirestException, IOException {
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        HttpResponse<String> responseCredit = creditAPI(expiredSessionTokenConfig, currencyIDConfig, gameIdConfig, operationTypeIdCredit,
                randomCreditTransactionID(), betAmountCreditConfig);
        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());


        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);

        softAssert.assertNotEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 0,
                "ResponseCode: " + iqSoft_04_apiVariables_credit_response.getResponseCode());
//        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "SessionExpired",
//                "Error Description: " + iqSoft_04_apiVariables_credit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit,
                "amountBeforeCredit = amountAfterCredit: " + amountBeforeCredit + " = " + amountAfterCredit);

        softAssert.assertAll();
    }

    @Test(priority = 2)
    @Description("Verify Credit API_s response with invalid Token")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseWithInvalidToken() throws UnirestException, IOException {
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        HttpResponse<String> responseCredit = creditAPI(AuthorizationTokenVar + "_Error", currencyIDConfig, gameIdConfig, operationTypeIdCredit,
                randomCreditTransactionID(), betAmountCreditConfig);
        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());


        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);

        softAssert.assertNotEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 0,
                "ResponseCode: " + iqSoft_04_apiVariables_credit_response.getResponseCode());
//        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "error login",
//                "Error Description: " + iqSoft_04_apiVariables_credit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit);

        softAssert.assertAll();
    }

//    @Test(priority = 3)
//    @Description("Verify Credit API_s response with invalid ProductID")
//    @Severity(SeverityLevel.NORMAL)
//    public void CreditAPIValidateResponseWithInvalidProductID() throws UnirestException, IOException {
//        SoftAssert softAssert = new SoftAssert();
//
//        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
//        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//
//        HttpResponse<String> responseCredit = creditAPI(AuthorizationTokenVar, currencyIDConfig, -10, operationTypeIdCredit,
//                randomCreditTransactionID(), betAmountCreditConfig, 1);
//        Unirest.shutdown();
//        statusCod = responseCredit.getStatus();
//        jsonObjectBody = new JSONObject(responseCredit.getBody());
//
//        Unirest.shutdown();
//        statusCod = responseCredit.getStatus();
//        jsonObjectBody = new JSONObject(responseCredit.getBody());
//
//        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
////        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
//
//
//        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
//        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
//        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
//        Unirest.shutdown();
//
//        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);
//
//        softAssert.assertNotEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 0,
//                "ResponseCode: " + iqSoft_04_apiVariables_credit_response.getResponseCode());
////        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "game not available",
////                "Error Description: " + iqSoft_04_apiVariables_credit_response.getDescription());
//        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit,
//                "Amount BeforeCredit: " + amountBeforeCredit + "Amount AfterCredit: " + amountAfterCredit);
//
//        softAssert.assertAll();
//    }


    @Test(priority = 4)
    @Description("Verify Credit API_s response  using same CreditTransactionID twice")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseUsingTransactionIDTwice() throws UnirestException, IOException {
        String creditTransactionID = randomCreditTransactionID();
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit1 = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit1.getBody());
        double amountBeforeCredit1 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, 3,
                creditTransactionID, betAmountCreditConfig);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceBeforeCredit2 = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit2.getBody());
        double amountBeforeCredit2 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

        HttpResponse<String> responseCredit2 = creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, operationTypeIdCredit,
                creditTransactionID, betAmountCreditConfig);
        Unirest.shutdown();
        statusCod = responseCredit2.getStatus();
        jsonObjectBody = new JSONObject(responseCredit2.getBody());

        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());


        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit3 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);

        softAssert.assertNotEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 0,
                "ResponseCode: " + iqSoft_04_apiVariables_credit_response.getResponseCode());
//        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "ClientDocumentAlreadyExists",
//                "Error Description: " + iqSoft_04_apiVariables_credit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit1, amountBeforeCredit2 - betAmountCreditConfig,
                "AmountBeforeCredit1: " + amountBeforeCredit1 + " = amountBeforeCredit2: " + amountBeforeCredit2 + " - betAmountCreditConfig: " + betAmountCreditConfig);
        softAssert.assertEquals(amountBeforeCredit2, amountAfterCredit3,
                "AmountBeforeCredit2: " + amountBeforeCredit2 + " = amountBeforeCredit3 : " + amountAfterCredit3);


    }


    @Test(priority = 5)
    @Description("Verify Credit API_s response  using Amount Higher Then Balance")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseUsingAmountHigherThenBalance() throws UnirestException, IOException {
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        HttpResponse<String> responseCredit = creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, operationTypeIdCredit,
                randomCreditTransactionID(), amountBeforeCredit + 10);
        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());


        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);

        softAssert.assertNotEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 0,
                "ResponseCode: " + iqSoft_04_apiVariables_credit_response.getResponseCode());
//        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "balance less then bet Amount",
//                "Error Description: " + iqSoft_04_apiVariables_credit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit,
                "AmountBeforeCredit: " + amountBeforeCredit + " = amountAfterCredit : " + amountAfterCredit);

        softAssert.assertAll();

    }


    @Test(priority = 6, dataProvider = "invalidAmountData")
    @Description("Verify Credit API_s response with invalid Amount")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseWithInvalidAmount(double errAmount) throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        HttpResponse<String> responseCredit = creditAPI(AuthorizationTokenVar, currencyIDConfig, gameIdConfig, operationTypeIdCredit,
                randomCreditTransactionID(), errAmount);
        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());

        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());
        Unirest.shutdown();

        softAssert.assertEquals(statusCod, 200, "StatusCod: " + statusCod);

        softAssert.assertNotEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 0,
                "ResponseCode: " + iqSoft_04_apiVariables_credit_response.getResponseCode());
//        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "WrongOperationAmount",
//                "Error Description: " + iqSoft_04_apiVariables_credit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit,
                "AmountBeforeCredit: " + amountBeforeCredit + " = amountAfterCredit : " + amountAfterCredit);

        softAssert.assertAll();

    }

    @DataProvider(name = "invalidAmountData")
    Object[][] AmountInvalidData() {
        Double[][] arr = {
//                {0.0},
                {-1.0},
                {-1.5},
                {-1.05},
                {-1.005}
        };
        return arr;
    }


}
