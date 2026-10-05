package base;

import utilities.ConfigUtility;

public class BaseClass {

    public static String initializeApplication(){
        String url = ConfigUtility.getProp("baseurl");
        return url;
    }

}
