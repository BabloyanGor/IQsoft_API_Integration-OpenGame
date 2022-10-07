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

    @Test(priority = 1)
    @Description("Verify Credit API_s response with Expired Token")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseWithExpiredToken() throws UnirestException, IOException {
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());


        HttpResponse<String> responseCredit = creditAPI(expiredSessionTokenConfig, currencyIDConfig, gameIdConfig, 1,
                                                        randomCreditTransactionID() , betAmountCreditConfig,1);
        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Credit API Response ResponseCode : " + iqSoft_04_apiVariables_credit_response.getResponseCode());

        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Credit API Response Description : " + iqSoft_04_apiVariables_credit_response.getDescription());


        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

        softAssert.assertEquals(statusCod, 200);

        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 29);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "SessionExpired",
                        "Error Description: " + iqSoft_04_apiVariables_credit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit);

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


        HttpResponse<String> responseCredit = creditAPI(sessionTokenConfig+"1", currencyIDConfig, gameIdConfig, 1,
                randomCreditTransactionID() , betAmountCreditConfig,1);
        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Credit API Response ResponseCode : " + iqSoft_04_apiVariables_credit_response.getResponseCode());

        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Credit API Response Description : " + iqSoft_04_apiVariables_credit_response.getDescription());


        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

        softAssert.assertEquals(statusCod, 200);

        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 22);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "error login",
                "Error Description: " + iqSoft_04_apiVariables_credit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit);

        softAssert.assertAll();
    }

    @Test(priority = 3)
    @Description("Verify Credit API_s response with invalid ProductID")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseWithInvalidProductID() throws UnirestException, IOException {
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());


        HttpResponse<String> responseCredit = creditAPI(sessionTokenConfig, currencyIDConfig, -10, 1,
                randomCreditTransactionID() , betAmountCreditConfig,1);
        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Credit API Response ResponseCode : " + iqSoft_04_apiVariables_credit_response.getResponseCode());

        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Credit API Response Description : " + iqSoft_04_apiVariables_credit_response.getDescription());



        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

        softAssert.assertEquals(statusCod, 200);

        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 43);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "game not available",
                "Error Description: " + iqSoft_04_apiVariables_credit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit);

        softAssert.assertAll();
    }


    @Test(priority = 4)
    @Description("Verify Credit API_s response  using same CreditTransactionID twice")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseUsingTransactionIDTwice() throws UnirestException, IOException {
        String creditTransactionID = randomCreditTransactionID();
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit1 = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit1.getBody());
        double amountBeforeCredit1 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

        creditAPI(sessionTokenConfig, currencyIDConfig, gameIdConfig, 1,
                creditTransactionID , betAmountCreditConfig,1);
        Unirest.shutdown();

        HttpResponse<String> responseGetBalanceBeforeCredit2 = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit2.getBody());
        double amountBeforeCredit2 = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

        HttpResponse<String> responseCredit2 = creditAPI(sessionTokenConfig, currencyIDConfig, gameIdConfig, 1,
                creditTransactionID , betAmountCreditConfig,1);
        Unirest.shutdown();
        statusCod = responseCredit2.getStatus();
        jsonObjectBody = new JSONObject(responseCredit2.getBody());

        Unirest.shutdown();
        statusCod = responseCredit2.getStatus();
        jsonObjectBody = new JSONObject(responseCredit2.getBody());

        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Credit API Response ResponseCode : " + iqSoft_04_apiVariables_credit_response.getResponseCode());

        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Credit API Response Description : " + iqSoft_04_apiVariables_credit_response.getDescription());


        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

        softAssert.assertEquals(statusCod, 200);

        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 69);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "ClientDocumentAlreadyExists",
                "Error Description: " + iqSoft_04_apiVariables_credit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit1, amountBeforeCredit2-betAmountCreditConfig);
        softAssert.assertEquals(amountBeforeCredit2, amountAfterCredit);


    }


    @Test(priority = 5)
    @Description("Verify Credit API_s response  using Amount Higher Then Balance")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseUsingAmountHigherThenBalance() throws UnirestException, IOException {
        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());


        HttpResponse<String> responseCredit = creditAPI(sessionTokenConfig, currencyIDConfig, gameIdConfig, 1,
                randomCreditTransactionID() , amountBeforeCredit+10,1);
        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Credit API Response ResponseCode : " + iqSoft_04_apiVariables_credit_response.getResponseCode());

        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Credit API Response Description : " + iqSoft_04_apiVariables_credit_response.getDescription());



        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

        softAssert.assertEquals(statusCod, 200);

        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 63);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "balance less then bet Amount",
                "Error Description: " + iqSoft_04_apiVariables_credit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit);

        softAssert.assertAll();

    }


    @Test(priority = 5, dataProvider = "invalidAmountData")
    @Description("Verify Credit API_s response with invalid Amount")
    @Severity(SeverityLevel.BLOCKER)
    public void CreditAPIValidateResponseWithInvalidAmount(double errAmount) throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> responseGetBalanceBeforeCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceBeforeCredit.getBody());
        double amountBeforeCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());


        HttpResponse<String> responseCredit = creditAPI(sessionTokenConfig, currencyIDConfig, gameIdConfig, 1,
                randomCreditTransactionID() , errAmount,1);
        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        Unirest.shutdown();
        statusCod = responseCredit.getStatus();
        jsonObjectBody = new JSONObject(responseCredit.getBody());

        iqSoft_04_apiVariables_credit_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("Credit API Response ResponseCode : " + iqSoft_04_apiVariables_credit_response.getResponseCode());

        iqSoft_04_apiVariables_credit_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("Credit API Response Description : " + iqSoft_04_apiVariables_credit_response.getDescription());



        HttpResponse<String> responseGetBalanceAfterCredit = getBalanceAPI(AuthorizationTokenVar, currencyIDConfig);
        jsonObjectBody = new JSONObject(responseGetBalanceAfterCredit.getBody());
        double amountAfterCredit = Double.parseDouble(jsonObjectBody.get("AvailableBalance").toString());

        softAssert.assertEquals(statusCod, 200);

        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getResponseCode(), 39);
        softAssert.assertEquals(iqSoft_04_apiVariables_credit_response.getDescription(), "WrongOperationAmount",
                "Error Description: " + iqSoft_04_apiVariables_credit_response.getDescription());
        softAssert.assertEquals(amountBeforeCredit, amountAfterCredit);

        softAssert.assertAll();

    }

    @DataProvider(name = "invalidAmountData")
    Object[][] AmountInvalidData()  {
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
