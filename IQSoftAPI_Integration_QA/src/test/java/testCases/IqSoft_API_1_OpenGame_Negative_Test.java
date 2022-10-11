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

public class IqSoft_API_1_OpenGame_Negative_Test extends BaseTest {

    JSONObject jsonObjectBody;
    int statusCod;

    @Test(priority = 1)
    @Description("Verify OpenGame API_s response with invalid Token ")
    @Severity(SeverityLevel.BLOCKER)
    public void openGameAPIValidateResponseWithInvalidToken() throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> response = openGameAPI(partnerIdConfig, gameIdConfig, sessionTokenConfig + "1", languageIdConst, isForMobileConst, domainConfig);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_01_apiVariables_openGame_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("OpenGame API ResponseCode is Equal: " + iqSoft_01_apiVariables_openGame_response.getResponseCode());

        iqSoft_01_apiVariables_openGame_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("OpenGame API Description is Equal: " + iqSoft_01_apiVariables_openGame_response.getDescription());

        iqSoft_01_apiVariables_openGame_response.setResponseObject(jsonObjectBody.get("ResponseObject").toString());
        logger.info("OpenGame API ResponseObject is Equal: " + iqSoft_01_apiVariables_openGame_response.getResponseObject());

        logger.info("OpenGame API Response Status Cod is Equal: " + statusCod);
        softAssert.assertEquals(statusCod, 200,"StatusCod: " + statusCod);
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getResponseCode(),22,
                "ResponseCode: "+ iqSoft_01_apiVariables_openGame_response.getResponseCode());
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getDescription(),"Client not found",
                "Error Description: " + iqSoft_01_apiVariables_openGame_response.getDescription());
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getResponseObject(),"null",
                "ResponseObject: " + iqSoft_01_apiVariables_openGame_response.getResponseObject());
        softAssert.assertAll();
    }

    @Test(priority = 2)
    @Description("Verify OpenGame API_s response with Expired Token ")
    @Severity(SeverityLevel.BLOCKER)
    public void openGameAPIValidateResponseWithExpiredToken() throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> response = openGameAPI(partnerIdConfig, gameIdConfig, expiredSessionTokenConfig, languageIdConst, isForMobileConst, domainConfig);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_01_apiVariables_openGame_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("OpenGame API ResponseCode is Equal: " + iqSoft_01_apiVariables_openGame_response.getResponseCode());

        iqSoft_01_apiVariables_openGame_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("OpenGame API Description is Equal: " + iqSoft_01_apiVariables_openGame_response.getDescription());

        iqSoft_01_apiVariables_openGame_response.setResponseObject(jsonObjectBody.get("ResponseObject").toString());
        logger.info("OpenGame API ResponseObject is Equal: " + iqSoft_01_apiVariables_openGame_response.getResponseObject());

        logger.info("OpenGame API Response Status Cod is Equal: " + statusCod);
        softAssert.assertEquals(statusCod, 200,"StatusCod: " + statusCod);
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getResponseCode(),22,
                "ResponseCode: "+iqSoft_01_apiVariables_openGame_response.getResponseCode());
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getDescription(),"Client not found",
                "Error Description: " + iqSoft_01_apiVariables_openGame_response.getDescription());
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getResponseObject(),"null",
                "ResponseObject: "+iqSoft_01_apiVariables_openGame_response.getResponseObject());
        softAssert.assertAll();
    }

    @Test(priority = 3)
    @Description("Verify OpenGame API_s response with invalid PartnerID")
    @Severity(SeverityLevel.NORMAL)
    public void openGameAPIValidateResponseWithInvalidPartnerID() throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> response = openGameAPI(-1, gameIdConfig, sessionTokenConfig , languageIdConst, isForMobileConst, domainConfig);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_01_apiVariables_openGame_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("OpenGame API ResponseCode is Equal: " + iqSoft_01_apiVariables_openGame_response.getResponseCode());

        iqSoft_01_apiVariables_openGame_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("OpenGame API Description is Equal: " + iqSoft_01_apiVariables_openGame_response.getDescription());

        iqSoft_01_apiVariables_openGame_response.setResponseObject(jsonObjectBody.get("ResponseObject").toString());
        logger.info("OpenGame API ResponseObject is Equal: " + iqSoft_01_apiVariables_openGame_response.getResponseObject());


        logger.info("OpenGame API Response Status Cod is Equal: " + statusCod);
        softAssert.assertEquals(statusCod, 200,"StatusCod: " + statusCod);
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getResponseCode(),22,
                "ResponseCode " + iqSoft_01_apiVariables_openGame_response.getResponseCode());
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getDescription(),"ClientNotFound ",
                "Error Description: " + iqSoft_01_apiVariables_openGame_response.getDescription());
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getResponseObject(),"null",
                "ResponseObject " +iqSoft_01_apiVariables_openGame_response.getResponseObject());
        softAssert.assertAll();
    }


    @Test(priority = 4)
    @Description("Verify OpenGame API_s response with invalid GameID")
    @Severity(SeverityLevel.NORMAL)
    public void openGameAPIValidateResponseWithInvalidGameID() throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> response = openGameAPI(partnerIdConfig, -1, sessionTokenConfig , languageIdConst, isForMobileConst, domainConfig);
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_01_apiVariables_openGame_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("OpenGame API ResponseCode is Equal: " + iqSoft_01_apiVariables_openGame_response.getResponseCode());

        iqSoft_01_apiVariables_openGame_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("OpenGame API Description is Equal: " + iqSoft_01_apiVariables_openGame_response.getDescription());

        iqSoft_01_apiVariables_openGame_response.setResponseObject(jsonObjectBody.get("ResponseObject").toString());
        logger.info("OpenGame API ResponseObject is Equal: " + iqSoft_01_apiVariables_openGame_response.getResponseObject());

        logger.info("OpenGame API Response Status Cod is Equal: " + statusCod);
        softAssert.assertEquals(statusCod, 200,"StatusCod: " + statusCod);
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getResponseCode(),43,
                "ResponseCode: " + iqSoft_01_apiVariables_openGame_response.getResponseCode());
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getDescription(),"Product not found",
                "Error Description: " + iqSoft_01_apiVariables_openGame_response.getDescription());
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getResponseObject(),"null",
                "ResponseObject: " +iqSoft_01_apiVariables_openGame_response.getResponseObject());
        softAssert.assertAll();
    }

    @Test(priority = 5)
    @Description("Verify OpenGame API_s response with invalid Domain")
    @Severity(SeverityLevel.BLOCKER)
    public void openGameAPIValidateResponseWithInvalidDomain() throws UnirestException, IOException {

        SoftAssert softAssert = new SoftAssert();

        HttpResponse<String> response = openGameAPI(partnerIdConfig, gameIdConfig, sessionTokenConfig , languageIdConst, isForMobileConst, domainConfig+"1");
        Unirest.shutdown();
        statusCod = response.getStatus();
        jsonObjectBody = new JSONObject(response.getBody());

        iqSoft_01_apiVariables_openGame_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("OpenGame API ResponseCode is Equal: " + iqSoft_01_apiVariables_openGame_response.getResponseCode());

        iqSoft_01_apiVariables_openGame_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("OpenGame API Description is Equal: " + iqSoft_01_apiVariables_openGame_response.getDescription());

        iqSoft_01_apiVariables_openGame_response.setResponseObject(jsonObjectBody.get("ResponseObject").toString());
        logger.info("OpenGame API ResponseObject is Equal: " + iqSoft_01_apiVariables_openGame_response.getResponseObject());

        logger.info("OpenGame API Response Status Cod is Equal: " + statusCod);
        softAssert.assertEquals(statusCod, 200,"StatusCod: " + statusCod);
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getResponseCode(),43,
                "ResponseCode: " +iqSoft_01_apiVariables_openGame_response.getResponseCode());
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getDescription(),"ProductNotFound",
                "Error Description: " + iqSoft_01_apiVariables_openGame_response.getDescription());
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getResponseObject(),"null",
                "ResponseObject: " +iqSoft_01_apiVariables_openGame_response.getResponseObject());
        softAssert.assertAll();
    }




}
