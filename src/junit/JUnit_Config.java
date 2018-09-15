package junit;

import config.ConfigData;
import config.ConfigInstance;
import pfg.config.Config;

public class JUnit_Config {
    public static void main(String[] args) {
        Config config = ConfigInstance.getConfig();
        System.out.println("ConfigData value : \t\t\t" + ConfigData.PRINT_LOG.getDefaultValue());
        System.out.println("config.txt value : \t\t\t" + config.getBoolean(ConfigData.PRINT_LOG));
        config.override(ConfigData.PRINT_LOG, true);
        System.out.println("Override TRUE value : \t\t" + config.getBoolean(ConfigData.PRINT_LOG));
        config.override(ConfigData.PRINT_LOG, false);
        System.out.println("Override FALSE value : \t\t" + config.getBoolean(ConfigData.PRINT_LOG));
        System.out.println("Ethernet default delay :\t" + config.getInt(ConfigData.MIN_TIME_BETWEEN_TWO_ORDERS));
    }
}
