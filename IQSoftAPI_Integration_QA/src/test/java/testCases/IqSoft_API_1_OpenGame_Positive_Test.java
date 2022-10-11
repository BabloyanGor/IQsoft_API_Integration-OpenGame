package testCases;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class IqSoft_API_1_OpenGame_Positive_Test extends BaseTest {
    JSONObject jsonObjectBody;
    int statusCod;

    @BeforeClass
    public void setUpTestCase() throws UnirestException, IOException {

//        Allure.addAttachment("OpenGameAPI RequestBody", openGameRequestBody);

        HttpResponse<String> openGameResponse = openGameAPI(partnerIdConfig, gameIdConfig, sessionTokenConfig, languageIdConst, isForMobileConst, domainConfig);
        Unirest.shutdown();
        statusCod = openGameResponse.getStatus();
        jsonObjectBody = new JSONObject(openGameResponse.getBody());

//        Allure.addAttachment("OpenGameAPI ResponseBody",jsonObjectBody.toString());



        iqSoft_01_apiVariables_openGame_response.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
        logger.info("getGameUrl API ResponseCode is Equal: " + iqSoft_01_apiVariables_openGame_response.getResponseCode());

        iqSoft_01_apiVariables_openGame_response.setDescription(jsonObjectBody.get("Description").toString());
        logger.info("getGameUrl API Description is Equal: " + iqSoft_01_apiVariables_openGame_response.getDescription());

        iqSoft_01_apiVariables_openGame_response.setResponseObject(jsonObjectBody.get("ResponseObject").toString());
        logger.info("getGameUrl API ResponseObject is Equal: " + iqSoft_01_apiVariables_openGame_response.getResponseObject());

    }


    @Test(priority = 1)
    @Description("Verify openGame API_s Status Cod equals to 200")
    @Severity(SeverityLevel.BLOCKER)
    public void openGameAPIValidateStatusCod() {

        logger.info("openGame API Response Status Cod is Equal: " + statusCod);
        Assert.assertEquals(statusCod, 200,"StatusCod: " + statusCod);
    }


    @Test(priority = 3, dependsOnMethods = {"openGameAPIValidateStatusCod"})
    @Description("Verify openGame API Positive Response")
    @Severity(SeverityLevel.BLOCKER)
    public void openGameAPIValidatePositiveResponse() {


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getResponseCode(), 0,
                "ResponseCode: " +iqSoft_01_apiVariables_openGame_response.getResponseCode());
        softAssert.assertEquals(iqSoft_01_apiVariables_openGame_response.getDescription(), "null",
                "Error Description: "+iqSoft_01_apiVariables_openGame_response.getDescription());
        softAssert.assertNotEquals(iqSoft_01_apiVariables_openGame_response.getResponseObject(),"null",
                "ResponseObject: " + iqSoft_01_apiVariables_openGame_response.getResponseObject());
        softAssert.assertTrue(iqSoft_01_apiVariables_openGame_response.getResponseObject().contains("http://"),"ResponseObject Don't Contain http://");
        softAssert.assertAll();

    }



}
