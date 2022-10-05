package testCases;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.*;
import testData.*;
import utilities.ReadConfig;




public class BaseTest {

    ReadConfig readConfig = new ReadConfig();
    public String openGameURL = readConfig.getOpenGameURL();
    public String callbackUrl = readConfig.getCallbackUrl();

    public int partnerID = readConfig.getPartnerID();
    public int clientId = readConfig.getClientId();
    public int productID = readConfig.getProductID();
    public int clientProductID = readConfig.getClientProductID();
    public double betAmount = readConfig.getBetAmount();
    public String currency = readConfig.getCurrency();
    public String sessionToken = readConfig.getSessionToken();

    public static Logger logger;


    static String ID = "QA_Test-" + RandomStringUtils.randomAlphanumeric(20);

    static public String randomCreditTransactionID() {
        return "QA_Test-" + RandomStringUtils.randomAlphanumeric(20) + "_C";
    }
    static public String randomDebitTransactionID() {
        return "QA_Test-" + RandomStringUtils.randomAlphanumeric(20)+ "_D";
    }
    static public String randomRollBackTransactionID() {
        return "QA_Test-" + RandomStringUtils.randomAlphanumeric(20)+ "_R";
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

    public BaseTest() {
    }

    @BeforeSuite
    public void setupSuite() throws InterruptedException {
        logger = Logger.getLogger("API");
        PropertyConfigurator.configure("Log4j.properties");
    }

    @AfterSuite
    public void tearDownSuite() {
        logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  Test Suite finished  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  ");
        logger.info("");
        logger.info("");
        logger.info("");
    }

    //endregion

    public HttpResponse<String> OpenGameAPI(int PartnerID, int GameID, String UserToken, String LanguageId,
                                            boolean isForMobile, String Domain) throws UnirestException {
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_01_apiVariables_openGame_request.setPartnerId(PartnerID);
        iqSoft_01_apiVariables_openGame_request.setGameId(GameID);
        iqSoft_01_apiVariables_openGame_request.setToken(UserToken);
        iqSoft_01_apiVariables_openGame_request.setLanguageId(LanguageId);
        iqSoft_01_apiVariables_openGame_request.setForMobile(isForMobile);
        iqSoft_01_apiVariables_openGame_request.setDomain(Domain);

        String openGameRequestBody = gson.toJson(iqSoft_01_apiVariables_openGame_request);
        logger.info("getURLRequestBody :" + openGameRequestBody);
        HttpResponse<String> openGameResponse = Unirest.post(openGameURL)
                .header("Content-Type", "application/json")
                .body(openGameRequestBody)
                .asString();
        return openGameResponse;
    }


    public HttpResponse<String> authorizationAPI(String SessionToken, int ProductID) throws UnirestException {
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_02_apisVariables_authorization_request.setToken(SessionToken);
        iqSoft_02_apisVariables_authorization_request.setProductId(ProductID);
        String authorizationRequestBody = gson.toJson(iqSoft_02_apisVariables_authorization_request);
        logger.info("AuthorizationRequestBody : " + authorizationRequestBody);

        HttpResponse<String> authorizationResponse = Unirest.post(callbackUrl + "/Authorization")
                .header("Content-Type", "application/json")
                .body(authorizationRequestBody)
                .asString();
        return authorizationResponse;
    }


    public HttpResponse<String> getBalanceAPI(String SessionToken, String CurrencyId) throws UnirestException {
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_03_apiVariables_getBalance_request.setToken(SessionToken);
        iqSoft_03_apiVariables_getBalance_request.setCurrencyId(CurrencyId);
        String getBalanceRequestBody = gson.toJson(iqSoft_03_apiVariables_getBalance_request);
        logger.info("GetBalanceRequestBody : " + getBalanceRequestBody);

        HttpResponse<String> getBalanceResponse = Unirest.post(callbackUrl + "/GetBalance")
                .header("Content-Type", "application/json")
                .body(getBalanceRequestBody)
                .asString();
        return getBalanceResponse;
    }


    public HttpResponse<String> creditAPI(String AuthorizationToken, String CurrencyID, int GameID, int OperationTypeId,
                                          String TransactionId, double Amount, int BetState) throws UnirestException {
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
        logger.info("CreditRequestBody : " + CreditRequestBody);
        HttpResponse<String> creditResponse = Unirest.post(callbackUrl + "/Credit")
                .header("Content-Type", "application/json")
                .body(CreditRequestBody)
                .asString();
        return creditResponse;
    }


    public HttpResponse<String> debitAPI(String ClientId, String CurrencyID, int GameID, String TransactionId, String CreditTransactionId,
                                         double Amount, int BetState, int OperationTypeId, String AuthorizationToken) throws UnirestException {  //if type = 1 one time else IDArrayList size
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
        logger.info("DebitRequestBody : " + DebitRequestBody);
        HttpResponse<String> debitResponse = Unirest.post(callbackUrl + "/Debit")
                .header("Content-Type", "application/json")
                .body(DebitRequestBody)
                .asString();
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
        HttpResponse<String> rollBackResponse = Unirest.post(callbackUrl + "/Rollback")
                .header("Content-Type", "application/json")
                .body(RollBackRequestBody)
                .asString();
        return rollBackResponse;
    }


}

