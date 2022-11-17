package testCases;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileWriter;
import java.io.IOException;

public class CreateUsers  extends BaseTest{


    JSONObject jsonObjectBody;
    int statusCod;

    @Test(priority = 1)
    @Description("create users")
    @Severity(SeverityLevel.BLOCKER)
    public void CreateUsers() throws UnirestException, IOException {
        JsonArray DataTest = new JsonArray();
        int err =0;
        for (int k=0; k<=1000; k++){

            Gson gson = new Gson();
            HttpResponse<String> response = createUsers();
            Unirest.shutdown();
            statusCod = response.getStatus();
            jsonObjectBody = new JSONObject(response.getBody());

            genereteUsersResponce.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
            genereteUsersResponce.setId(Integer.parseInt(jsonObjectBody.get("Id").toString()));
            genereteUsersResponce.setUserName(jsonObjectBody.get("UserName").toString());
            genereteUsersResponce.setEmail(jsonObjectBody.get("EmailOrMobile").toString());

            generateVarablesForNewJson.setClientIdentifier(jsonObjectBody.get("UserName").toString());
            if (genereteUsersResponce.getResponseCode() == 0){
                DataTest.add(gson.toJson(generateVarablesForNewJson));
            }
            else{
               // logger.info(k+ "  ResponseCod " + genereteUsersResponce.getResponseCode() + "  UserName "+genereteUsersResponce.getUserName() + "  Email "+ genereteUsersResponce.getEmail());
                err++;
            }
            if (err==10000){
                break;
            }
            w++;

        }


            FileWriter fw = new FileWriter("JsonForEncryptingUsers.txt");
            fw.write(String.valueOf(DataTest));
            fw.close();

//        System.out.println("This is Object that need to be added to exel   "+DataTest);



    }


}
