package testCases;


import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateUsers extends BaseTest {

    //Default separator
    char SEPARATOR = ',';
    //get path of csv file (creates new one if its not exists)
    String csvFile = "";
    // for example '/User/Downloads/blabla.csv'
    String[] params = {"1112"};


    FileWriter fileWriter = new FileWriter(csvFile, true);

    public CreateUsers() throws IOException {
    }


    //function write line in csv
    public void writeLine(FileWriter writer, String[] params, char separator) throws IOException {
        boolean firstParam = true;
        StringBuilder stringBuilder = new StringBuilder();
        String param = "";
        for (int i = 0; i < params.length; i++) {
            //get param
            param = params[i];
            //log.info(param);
            //if the first param in the line, separator is not needed
            if (!firstParam) {
                stringBuilder.append(separator);
            }
            //Add param to line
            stringBuilder.append(param);
            firstParam = false;
        }
        //prepare file to next line
        stringBuilder.append("\n");
        //add to file the line
        //log.info(stringBuilder.toString());
        writer.append(stringBuilder.toString());
    }
    //proper close to



    @Test
    public void test() throws IOException {
        writeLine(fileWriter, params, SEPARATOR);
        fileWriter.flush();
        fileWriter.close();
    }


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


}
