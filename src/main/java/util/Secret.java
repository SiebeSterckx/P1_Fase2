package util;

import java.util.Properties;

public class Secret extends Credentials {

    public static void setPass(Properties dbProperties) {
        dbProperties.setProperty("user", "hakkaton_10");
        dbProperties.setProperty("password", "tie6vooli(?9fahH");
    }
}
