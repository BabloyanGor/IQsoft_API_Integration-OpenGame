//package testCases;
//
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;
//import org.json.JSONObject;
//import org.testng.annotations.BeforeClass;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class IqSoft_API_6_RollBack_Negative_Test extends BaseTest{
//    JSONObject jsonObjectBody;
//    int statusCod;
//    int amount = 1;
//    static ArrayList<String> IDForCreditArrayList = new ArrayList<>();
//    static ArrayList<String> IDForDebitArrayList = new ArrayList<>();
//
//    @BeforeClass
//    public void setUp() throws UnirestException, IOException {
////        for (int i = 0; i < 8; i++) {
////            String TransactionID = randomID();
////            creditAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID, amount, TransactionID, TransactionID, currency);
////            IDForDebitArrayList.add(TransactionID);
////            Unirest.shutdown();
////        }
////        for (int i = 0; i < 8; i++) {
////            String TransactionID = randomID();
////            debitAPI(iqSoft01ApiVariables_getProductUrl_response.getAuthorizationToken(), clientProductID, amount, TransactionID, TransactionID, currency);
////            IDForDebitArrayList.add(TransactionID);
////            Unirest.shutdown();
////        }
//    }
//}
