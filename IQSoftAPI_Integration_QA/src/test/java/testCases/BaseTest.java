package testCases;


import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Allure;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import testData.*;
import utilities.ReadConfig;


public class BaseTest {
    public BaseTest() {
    }

    public static Logger logger;
    ReadConfig readConfig = new ReadConfig();
    public String openGameURL = readConfig.getOpenGameURL();
    public String callbackUrl = readConfig.getCallbackUrl();
    public String domainConfig = readConfig.getDomain();
    public int partnerIdConfig = readConfig.getPartnerID();
    public String clientIdConfig = readConfig.getClientId();
    public String userNameConfig = readConfig.getUserName();

    public int gameIdConfig = readConfig.getGameIdID();

    public int clientGameIdConfig = readConfig.getClientGameId();
    public double betAmountCreditConfig = readConfig.getBetAmountCredit();
    public double betAmountDebitConfig = readConfig.getBetAmountDebit();

    public String currencyIDConfig = readConfig.CurrencyId();
    public String sessionTokenConfig = readConfig.getSessionToken();
    public String expiredSessionTokenConfig = readConfig.getExpiredSessionToken();

    public static String AuthorizationTokenVar;
    public final String languageIdConst = "en";
    public final boolean isForMobileConst = false;


    public static String creditValidTransactionID = "QA_Test-" + RandomStringUtils.randomAlphanumeric(20) + "_C";
    public static String debitValidTransactionID = "QA_Test-" + RandomStringUtils.randomAlphanumeric(20) + "_D";

    public String randomCreditTransactionID() {
        return "QA_Test-" + RandomStringUtils.randomAlphanumeric(20) + "_C";
    }

    public String randomDebitTransactionID() {
        return "QA_Test-" + RandomStringUtils.randomAlphanumeric(20) + "_D";
    }

    public String randomRollBackTransactionID() {
        return "QA_Test-" + RandomStringUtils.randomAlphanumeric(20) + "_R";
    }


    //region <Request and Response Variables Integration>
    IqSoft_01_APIVariables_OpenGame_Request iqSoft_01_apiVariables_openGame_request = new IqSoft_01_APIVariables_OpenGame_Request();
    IqSoft_01_APIVariables_OpenGame_Response iqSoft_01_apiVariables_openGame_response = new IqSoft_01_APIVariables_OpenGame_Response();
    IqSoft_02_APISVariables_Authorization_Request iqSoft_02_apisVariables_authorization_request = new IqSoft_02_APISVariables_Authorization_Request();
    IqSoft_02_APISVariables_Authorization_Response iqSoft_02_apisVariables_authorization_response = new IqSoft_02_APISVariables_Authorization_Response();
    IqSoft_03_APIVariables_GetBalance_Request iqSoft_03_apiVariables_getBalance_request = new IqSoft_03_APIVariables_GetBalance_Request();
    IqSoft_03_APIVariables_GetBalance_Response iqSoft_03_apiVariables_getBalance_response = new IqSoft_03_APIVariables_GetBalance_Response();
    IqSoft_04_APIVariables_Credit_Request iqSoft_04_apiVariables_credit_request = new IqSoft_04_APIVariables_Credit_Request();
    IqSoft_04_APIVariables_Credit_Response iqSoft_04_apiVariables_credit_response = new IqSoft_04_APIVariables_Credit_Response();
    IqSoft_05_APIVariables_Debit_Request iqSoft_05_apiVariables_debit_request = new IqSoft_05_APIVariables_Debit_Request();
    IqSoft_05_APIVariables_Debit_Response iqSoft_05_apiVariables_debit_response = new IqSoft_05_APIVariables_Debit_Response();
    IqSoft_06_APIVariables_RollBack_Request iqSoft_06_apiVariables_rollBack_request = new IqSoft_06_APIVariables_RollBack_Request();
    IqSoft_06_APIVariables_RollBack_Response iqSoft_06_apiVariables_rollBack_response = new IqSoft_06_APIVariables_RollBack_Response();


    @BeforeSuite
    public void setupSuite() {
        logger = Logger.getLogger("ApiIntegration");
        PropertyConfigurator.configure("log4j.properties");
    }

    @AfterSuite
    public void tearDownSuite() {
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  Test Suite finished  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ");
        logger.info("");
        logger.info("");
        logger.info("");
    }

    //endregion

    public HttpResponse<String> openGameAPI(int PartnerID, int GameID, String SessionToken, String LanguageId,
                                            boolean isForMobile, String Domain) throws UnirestException {
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_01_apiVariables_openGame_request.setPartnerId(PartnerID);
        iqSoft_01_apiVariables_openGame_request.setGameId(GameID);
        iqSoft_01_apiVariables_openGame_request.setToken(SessionToken);
        iqSoft_01_apiVariables_openGame_request.setLanguageId(LanguageId);
        iqSoft_01_apiVariables_openGame_request.setForMobile(isForMobile);
        iqSoft_01_apiVariables_openGame_request.setDomain(Domain);

        String openGameRequestBody = gson.toJson(iqSoft_01_apiVariables_openGame_request);

        long start = System.currentTimeMillis();
        HttpResponse<String> openGameResponse = Unirest.post(openGameURL)
                .header("Content-Type", "application/json")
                .body(openGameRequestBody)
                .asString();

        long end = System.currentTimeMillis();

        Allure.addAttachment("OpenGameAPI RequestBody ", openGameRequestBody);
        Allure.addAttachment("OpenGameAPI ResponseBody ", openGameResponse.getBody() + "  ResponseTime " + (end - start) + " ms");
        logger.info("");
        logger.info("OpenGameAPI RequestBody "+ openGameRequestBody);
        logger.info("");
        logger.info("OpenGameAPI ResponseBody "+ openGameResponse.getBody() + "  ResponseTime " + (end - start) + " ms");
        logger.info("");
        return openGameResponse;
    }


    public HttpResponse<String> authorizationAPI(String SessionToken, int ProductID) throws UnirestException {
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_02_apisVariables_authorization_request.setToken(SessionToken);
        iqSoft_02_apisVariables_authorization_request.setProductId(ProductID);
        String authorizationRequestBody = gson.toJson(iqSoft_02_apisVariables_authorization_request);

        long start = System.currentTimeMillis();
        HttpResponse<String> authorizationResponse = Unirest.post(callbackUrl + "/Authorization")
                .header("Content-Type", "application/json")
                .body(authorizationRequestBody)
                .asString();

        long end = System.currentTimeMillis();

        Allure.addAttachment("AuthorizationAPI RequestBody", authorizationRequestBody);
        Allure.addAttachment("AuthorizationAPI ResponseBody", authorizationResponse.getBody() + "  ResponseTime "+ (end - start) + " ms");
        logger.info("");
        logger.info("AuthorizationAPI RequestBody "+ authorizationRequestBody);
        logger.info("");
        logger.info("AuthorizationAPI ResponseBody "+ authorizationResponse.getBody() + "  ResponseTime " + (end - start) + " ms");
        logger.info("");
        return authorizationResponse;
    }


    public HttpResponse<String> getBalanceAPI(String AuthorizationToken, String CurrencyId) throws UnirestException {
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_03_apiVariables_getBalance_request.setToken(AuthorizationToken);
        iqSoft_03_apiVariables_getBalance_request.setCurrencyId(CurrencyId);
        String getBalanceRequestBody = gson.toJson(iqSoft_03_apiVariables_getBalance_request);

        long start = System.currentTimeMillis();
        HttpResponse<String> getBalanceResponse = Unirest.post(callbackUrl + "/GetBalance")
                .header("Content-Type", "application/json")
                .body(getBalanceRequestBody)
                .asString();

        long end = System.currentTimeMillis();

        Allure.addAttachment("GetBalanceAPI RequestBody", getBalanceRequestBody);
        Allure.addAttachment("GetBalanceAPI ResponseBody", getBalanceResponse.getBody() + "  ResponseTime "+ (end - start) + " ms");
        logger.info("");
        logger.info("GetBalanceAPI RequestBody "+ getBalanceRequestBody);
        logger.info("");
        logger.info("GetBalanceAPI ResponseBody "+ getBalanceResponse.getBody() + "  ResponseTime " + (end - start) + " ms");
        logger.info("");

        return getBalanceResponse;
    }


    public HttpResponse<String> creditAPI(String AuthorizationToken, String CurrencyID, int GameID, int OperationTypeId,
                                          String TransactionId, double Amount, int BetState) throws UnirestException {

        // OperationTypeId  3_Bet, 4_Win, 15_BetRollBack,  17_WinRollBack
        // BetStates  2_Won, 3_Lost, 4_Returned
        // BetTypes  1_Single, 2_Multiple, 3_System

        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_04_apiVariables_credit_request.setToken(AuthorizationToken);
        iqSoft_04_apiVariables_credit_request.setCurrencyId(CurrencyID);
        iqSoft_04_apiVariables_credit_request.setGameId(GameID);
        iqSoft_04_apiVariables_credit_request.setOperationTypeId(OperationTypeId);
        iqSoft_04_apiVariables_credit_request.setTransactionId(TransactionId);
        iqSoft_04_apiVariables_credit_request.setAmount(Amount);
        iqSoft_04_apiVariables_credit_request.setBetState(BetState);

        String CreditRequestBody = gson.toJson(iqSoft_04_apiVariables_credit_request);

        long start = System.currentTimeMillis();
        HttpResponse<String> creditResponse = Unirest.post(callbackUrl + "/Credit")
                .header("Content-Type", "application/json")
                .body(CreditRequestBody)
                .asString();

        long end = System.currentTimeMillis();

        Allure.addAttachment("CreditAPI RequestBody", CreditRequestBody);
        Allure.addAttachment("CreditAPI ResponseBody", creditResponse.getBody() + "  ResponseTime " + (end - start) + " ms");
        logger.info("");
        logger.info("CreditAPI RequestBody "+ CreditRequestBody);
        logger.info("");
        logger.info("CreditAPI ResponseBody "+ creditResponse.getBody() + "  ResponseTime " + (end - start) + " ms");
        logger.info("");

        return creditResponse;
    }


    public HttpResponse<String> debitAPI(String ClientId, String CurrencyID, int GameID, String TransactionId, String CreditTransactionId,
                                         double Amount, int BetState, int OperationTypeId, String AuthorizationToken) throws UnirestException {  //if type = 1 one time else IDArrayList size

        // OperationTypeId  3_Bet, 4_Win, 15_BetRollBack,  17_WinRollBack
        // BetStates  2_Won, 3_Lost, 4_Returned
        // BetTypes  1_Single, 2_Multiple, 3_System

        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_05_apiVariables_debit_request.setClientId(ClientId);
        iqSoft_05_apiVariables_debit_request.setCurrencyId(CurrencyID);
        iqSoft_05_apiVariables_debit_request.setGameId(GameID);
        iqSoft_05_apiVariables_debit_request.setTransactionId(TransactionId);
        iqSoft_05_apiVariables_debit_request.setCreditTransactionId(CreditTransactionId);
        iqSoft_05_apiVariables_debit_request.setAmount(Amount);
        iqSoft_05_apiVariables_debit_request.setBetState(BetState);
        iqSoft_05_apiVariables_debit_request.setOperationTypeId(OperationTypeId);
        iqSoft_05_apiVariables_debit_request.setToken(AuthorizationToken);

        String DebitRequestBody = gson.toJson(iqSoft_05_apiVariables_debit_request);

        long start = System.currentTimeMillis();
        HttpResponse<String> debitResponse = Unirest.post(callbackUrl + "/Debit")
                .header("Content-Type", "application/json")
                .body(DebitRequestBody)
                .asString();
        long end = System.currentTimeMillis();

        Allure.addAttachment("DebitAPI RequestBody", DebitRequestBody);
        Allure.addAttachment("DebitAPI ResponseBody", debitResponse.getBody() + "  ResponseTime "+ (end - start) + " ms");
        logger.info("");
        logger.info("DebitAPI RequestBody "+ DebitRequestBody);
        logger.info("");
        logger.info("DebitAPI ResponseBody "+ debitResponse.getBody() + "  ResponseTime " + (end - start) + " ms");
        logger.info("");
        return debitResponse;
    }


    public HttpResponse<String> rollBackAPI(String UserName, int GameId, String RollbackTransactionId, String TransactionId,
                                            String AuthorizationToken, int OperationTypeId) throws UnirestException {  //if type = 1 one time else IDArrayList size
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_06_apiVariables_rollBack_request.setUserName(UserName);
        iqSoft_06_apiVariables_rollBack_request.setGameId(GameId);
        iqSoft_06_apiVariables_rollBack_request.setRollbackTransactionId(RollbackTransactionId);
        iqSoft_06_apiVariables_rollBack_request.setTransactionId(TransactionId);
        iqSoft_06_apiVariables_rollBack_request.setToken(AuthorizationToken);
        iqSoft_06_apiVariables_rollBack_request.setOperationTypeId(OperationTypeId);

        String RollBackRequestBody = gson.toJson(iqSoft_06_apiVariables_rollBack_request);
        logger.info("RollBackRequestBody : " + RollBackRequestBody);
        long start = System.currentTimeMillis();
        HttpResponse<String> rollBackResponse = Unirest.post(callbackUrl + "/Rollback")
                .header("Content-Type", "application/json")
                .body(RollBackRequestBody)
                .asString();
        long end = System.currentTimeMillis();

        Allure.addAttachment("RollBackAPI RequestBody", RollBackRequestBody);
        Allure.addAttachment("RollBackAPI ResponseBody", rollBackResponse.getBody() + "  ResponseTime "+ (end - start) + " ms");
        logger.info("");
        logger.info("RollBackAPI RequestBody "+ RollBackRequestBody);
        logger.info("");
        logger.info("RollBackAPI ResponseBody "+ rollBackResponse.getBody() + "  ResponseTime " + (end - start) + " ms");
        logger.info("");
        return rollBackResponse;
    }


}

