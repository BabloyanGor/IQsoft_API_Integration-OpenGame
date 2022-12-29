//package testData;
////
////import com.google.gson.annotations.Expose;
////import com.google.gson.annotations.SerializedName;
////
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Arrays;
//            import java.io.Writer;
//            import java.util.List;
//
//public class GenereteUsersResponce {
//    protected GenereteUsersResponce() throws IOException {
//    }
//
//
//    //Default separator
//    char SEPARATOR=',';
//
//    //get path of csv file (creates new one if its not exists)
//    String csvFile="";
//    // for example '/User/Downloads/blabla.csv'
//    String[] params = {"1", "1", String.valueOf(3)};
//    FileWriter fileWriter=new FileWriter(csvFile,true);
//
//
//    writeLine(fileWriter, params, SEPARATOR);
////proper close to file
//        fileWriter.flush();
//        fileWriter.close();
//
//
//    //function write line in csv
//    public void writeLine(FileWriter writer, String[] params, char separator) throws IOException {
//        boolean firstParam = true;
//        StringBuilder stringBuilder = new StringBuilder();
//        String param = "";
//        for (int i = 0; i < params.length; i++) {
//            //get param
//            param=params[i];
////            log.info(param);
////if the first param in the line, separator is not needed
//            if (!firstParam) {
//                stringBuilder.append(separator);
//            }
////Add param to line
//            stringBuilder.append(param);
//            firstParam = false;
//        }
////prepare file to next line
//        stringBuilder.append("\n");
////add to file the line
////        log.info(stringBuilder.toString());
//        writer.append(stringBuilder.toString());
//
//
//    }
//
////
////    @SerializedName("ResponseCode")
////    @Expose
////    private int ResponseCode;
////
////    @SerializedName("Id")
////    @Expose
////    private int Id;
////
////    @SerializedName("UserName")
////    @Expose
////    private String UserName;
////
////    @SerializedName("EmailOrMobile")
////    @Expose
////    private String Email;
////
////    public int getResponseCode() {
////        return ResponseCode;
////    }
////
////    public void setResponseCode(int responseCode) {
////        ResponseCode = responseCode;
////    }
////
////    public int getId() {
////        return Id;
////    }
////
////    public void setId(int id) {
////        Id = id;
////    }
////
////    public String getUserName() {
////        return UserName;
////    }
////
////    public void setUserName(String userName) {
////        UserName = userName;
////    }
////
////    public String getEmail() {
////        return Email;
////    }
////
////    public void setEmail(String email) {
////        Email = email;
////    }
////}
//
//
//
//
//}
