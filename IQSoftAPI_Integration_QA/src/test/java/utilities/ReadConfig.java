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
    public String getClientId() {
        return pro.getProperty("ClientId");
    }
    public String getUserName() {
        return pro.getProperty("UserName");
    }


    public int getGameIdID() {
        return Integer.parseInt(pro.getProperty("GameId"));
    }
    public int getClientGameId() {
        return Integer.parseInt(pro.getProperty("ClientGameId"));
    }
    public double getBetAmountCredit() {
        return Double.parseDouble(pro.getProperty("BetAmountCredit"));
    }
    public double getBetAmountDebit() {
        return Double.parseDouble(pro.getProperty("BetAmountDebit"));
    }
    public String CurrencyId() {
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
