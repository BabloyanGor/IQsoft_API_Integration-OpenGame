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

    public int partnerIdConfig = readConfig.getPartnerID();
    public String openGameURL = "https://production.iqsoftllc.com/" + partnerIdConfig + "/api/Integration/OpenGame";
    public String callbackUrl = readConfig.getCallbackUrl();
    public String domainConfig = readConfig.getDomain();
    public String clientIdConfig = readConfig.getClientId();
    public String userNameConfig = readConfig.getUserName();

    public int gameIdConfig = readConfig.getGameIdID();
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
    //endregion

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
        String url = openGameURL;

        HttpResponse<String> openGameResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(openGameRequestBody)
                .asString();

        long end = System.currentTimeMillis();

        Allure.addAttachment("OpenGameAPI:  Url  " + url + "        RequestBody", openGameRequestBody);
        Allure.addAttachment("OpenGameAPI:  ResponseTime  " + (end - start) + "ms        ResponseBody", openGameResponse.getBody());
        logger.info("");
        logger.info(url);
        logger.info("OpenGameAPI:  RequestBody " + openGameRequestBody);
        logger.info("OpenGameAPI:  ResponseBody " + openGameResponse.getBody() + "  ResponseTime " + (end - start) + " ms");
        logger.info("");

        return openGameResponse;
    }

    public HttpResponse<String> authorizationAPI(String SessionToken, int ProductID) throws UnirestException {
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_02_apisVariables_authorization_request.setToken(SessionToken);
        iqSoft_02_apisVariables_authorization_request.setProductId(ProductID);
        String authorizationRequestBody = gson.toJson(iqSoft_02_apisVariables_authorization_request);

        String url = callbackUrl + "/Authorization";
        long start = System.currentTimeMillis();
        HttpResponse<String> authorizationResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(authorizationRequestBody)
                .asString();

        long end = System.currentTimeMillis();

        Allure.addAttachment("AuthorizationAPI:  Url  " + url + "        RequestBody", authorizationRequestBody);
        Allure.addAttachment("AuthorizationAPI:  ResponseTime  " + (end - start) + "ms        ResponseBody", authorizationResponse.getBody());
        logger.info("");
        logger.info(url);
        logger.info("AuthorizationAPI:  RequestBody  " + authorizationRequestBody);
        logger.info("AuthorizationAPI:  ResponseBody  " + authorizationResponse.getBody() + "  ResponseTime " + (end - start) + " ms");
        logger.info("");

        return authorizationResponse;
    }


    public HttpResponse<String> getBalanceAPI(String AuthorizationToken, String CurrencyId) throws UnirestException {
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);
        iqSoft_03_apiVariables_getBalance_request.setToken(AuthorizationToken);
        iqSoft_03_apiVariables_getBalance_request.setCurrencyId(CurrencyId);
        String getBalanceRequestBody = gson.toJson(iqSoft_03_apiVariables_getBalance_request);

        String url = callbackUrl + "/GetBalance";

        long start = System.currentTimeMillis();
        HttpResponse<String> getBalanceResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(getBalanceRequestBody)
                .asString();

        long end = System.currentTimeMillis();

        Allure.addAttachment("GetBalanceAPI:  Url:  " + url + "        RequestBody", getBalanceRequestBody);
        Allure.addAttachment("GetBalanceAPI:  ResponseTime  " + (end - start) + "ms        ResponseBody", getBalanceResponse.getBody());
        logger.info("");
        logger.info(url);
        logger.info("GetBalanceAPI:  RequestBody  "+ getBalanceRequestBody);
        logger.info("GetBalanceAPI:  ResponseBody  "+getBalanceResponse.getBody() + "  ResponseTime " + (end - start) + " ms");
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
        String url = callbackUrl + "/Credit";

        long start = System.currentTimeMillis();
        HttpResponse<String> creditResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(CreditRequestBody)
                .asString();

        long end = System.currentTimeMillis();

        Allure.addAttachment("CreditAPI:  Url:  " + url + "        RequestBody", CreditRequestBody);
        Allure.addAttachment("CreditAPI:  ResponseTime  " + (end - start) + "ms        ResponseBody", creditResponse.getBody());
        logger.info("");
        logger.info(url);
        logger.info("CreditAPI:  RequestBody  "+ CreditRequestBody);
        logger.info("CreditAPI:  ResponseBody  "+ creditResponse.getBody() + "  ResponseTime " + (end - start) + " ms");
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
        String url = callbackUrl + "/Debit";

        long start = System.currentTimeMillis();
        HttpResponse<String> debitResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(DebitRequestBody)
                .asString();
        long end = System.currentTimeMillis();

        Allure.addAttachment("DebitAPI  Url:  " + url + "        RequestBody", DebitRequestBody);
        Allure.addAttachment("DebitAPI    ResponseTime  " + (end - start) + "ms        ResponseBody", debitResponse.getBody());
        logger.info("");
        logger.info(url);
        logger.info("DebitAPI:  RequestBody  "+ DebitRequestBody);
        logger.info("DebitAPI:  ResponseBody  "+debitResponse.getBody() + "  ResponseTime " + (end - start) + " ms");
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
        String url = callbackUrl + "/Rollback";

        long start = System.currentTimeMillis();
        HttpResponse<String> rollBackResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(RollBackRequestBody)
                .asString();
        long end = System.currentTimeMillis();

        Allure.addAttachment("RollBackAPI:  Url:  " + url + "        RequestBody", RollBackRequestBody);
        Allure.addAttachment("RollBackAPI:    ResponseTime  " + (end - start) + "ms        ResponseBody", rollBackResponse.getBody());
        logger.info("");
        logger.info(url);
        logger.info("RollBackAPI:  RequestBody  "+ RollBackRequestBody);
        logger.info("RollBackAPI:  ResponseBody  "+ rollBackResponse.getBody() + "  ResponseTime " + (end - start) + " ms");
        logger.info("");

        return rollBackResponse;
    }


    GenerateUsersVariables generateUsersVariables = new GenerateUsersVariables();
    GenereteUsersResponce genereteUsersResponce = new GenereteUsersResponce();
    GenerateVarablesForNewJson generateVarablesForNewJson = new GenerateVarablesForNewJson();
    public int w = 1;
    public HttpResponse<String> createUsers() throws UnirestException {  //if type = 1 one time else IDArrayList size
        Gson gson = new Gson();
        Unirest.setTimeouts(0, 0);



        generateUsersVariables.setEmail("qatest00"+w+"@mail.com");
        generateUsersVariables.setUserName("Qa" + w);
        generateUsersVariables.setMobileNumber("7777"+w);


        String registerBody = gson.toJson(generateUsersVariables);
        String url = "https://websitewebapi.craftbetstage.com/1/api/Main/RegisterClient";

//        long start = System.currentTimeMillis();
        HttpResponse<String> createUserResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(registerBody)
                .asString();
//        long end = System.currentTimeMillis();

//        logger.info("RollBackAPI:  Url:  " + url + "        RequestBody   " + registerBody);
//        logger.info("");
//        logger.info("RollBackAPI:    ResponseTime  " + (end - start) + "ms        ResponseBody   "+ createUserResponse.getBody());
//        logger.info("");


        return createUserResponse;
    }


}

