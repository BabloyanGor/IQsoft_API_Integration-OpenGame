package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    Properties pro;

    public ReadConfig() {
        File src = new File("./Configuration/config.properties");

        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }


    public String getOpenGameURL() {
        return pro.getProperty("OpenGameURL");
    }
    public String getCallbackUrl() {
        return pro.getProperty("callbackUrl");
    }


    //region <Getting Variables for gameLaunchURL API>
    public String getDomain() {
        return pro.getProperty("Domain");
    }
    public int getPartnerID() {
        return Integer.parseInt(pro.getProperty("PartnerId"));
    }
    public int getClientId() {
        return Integer.parseInt(pro.getProperty("ClientId"));
    }
    public int getGameIdID() {
        return Integer.parseInt(pro.getProperty("GameId"));
    }
    public int getClientGameId() {
        return Integer.parseInt(pro.getProperty("ClientGameId"));
    }
    public double getBetAmount() {
        return Double.parseDouble(pro.getProperty("BetAmount"));
    }

    public String getCurrency() {
        return pro.getProperty("CurrencyId");
    }
    public String getSessionToken() {
        return pro.getProperty("SessionToken");
    }
    public String getExpiredSessionToken() {
        return pro.getProperty("ExpiredSessionToken");
    }



    //endregion




}
