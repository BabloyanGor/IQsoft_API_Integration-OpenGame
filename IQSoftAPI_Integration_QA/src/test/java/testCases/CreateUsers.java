//package testCases;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;
//import io.qameta.allure.Description;
//import io.qameta.allure.Severity;
//import io.qameta.allure.SeverityLevel;
//import org.json.JSONObject;
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
//
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class CreateUsers extends BaseTest {
//
//
//    JSONObject jsonObjectBody;
//    int statusCod;
//
//    @Test(priority = 1)
//    @Description("create users")
//    @Severity(SeverityLevel.BLOCKER)
//    public void CreateUsers() throws UnirestException, IOException {
//        JsonArray DataTest = null;
//        int k = 0;
//        try {
//
//            DataTest = new JsonArray();
//            int err = 0;
//            for (k = 15546; k <= 20000; k++) {
//
//                Gson gson = new Gson();
//                HttpResponse<String> response = createUsers();
//                Unirest.shutdown();
//                statusCod = response.getStatus();
//                jsonObjectBody = new JSONObject(response.getBody());
//
//                genereteUsersResponce.setResponseCode(Integer.parseInt(jsonObjectBody.get("ResponseCode").toString()));
//                genereteUsersResponce.setId(Integer.parseInt(jsonObjectBody.get("Id").toString()));
//                genereteUsersResponce.setUserName(jsonObjectBody.get("UserName").toString());
//                genereteUsersResponce.setEmail(jsonObjectBody.get("EmailOrMobile").toString());
//
//                generateVarablesForNewJson.setClientIdentifier(jsonObjectBody.get("UserName").toString());
//                if (genereteUsersResponce.getResponseCode() == 0) {
//                    DataTest.add(gson.toJson(generateVarablesForNewJson));
//                } else {
//                    logger.info(k + "  ResponseCod " + genereteUsersResponce.getResponseCode() + "  UserName " + genereteUsersResponce.getUserName() + "  Email " + genereteUsersResponce.getEmail());
//                    err++;
//                }
//                if (err == 20) {
//                    break;
//                }
//                w++;
//            }
//            FileWriter fw = new FileWriter("JsonForEncryptingUsers" + k + ".txt");
//            fw.write(String.valueOf(DataTest));
//            fw.close();
//
//        }
//
//
//
//
//        catch (Exception e) {
//            logger.info("Exception");
//
//            FileWriter fw = new FileWriter("JsonForEncryptingUsersNumber" + k + ".txt");
//            fw.write(String.valueOf(DataTest));
//            fw.close();
//
//        }
////        System.out.println("This is Object that need to be added to exel   "+DataTest);
//
//
//    }
//
//
//}