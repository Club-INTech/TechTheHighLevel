package junit;

import data.ConfigData;
import pfg.config.Config;

public class JUnit_Config {
    public static void main(String[] args) {
        Config config = new Config(ConfigData.values(), true, "config/config.txt", "Basic, Simple");
        System.out.println("ConfigData value : \t\t" + ConfigData.PRINT_LOG.getDefaultValue());
        System.out.println("config.txt value : \t\t" + config.getBoolean(ConfigData.PRINT_LOG));
        config.override(ConfigData.PRINT_LOG, true);
        System.out.println("Override TRUE value : \t" + config.getBoolean(ConfigData.PRINT_LOG));
        config.override(ConfigData.PRINT_LOG, false);
        System.out.println("Override FALSE value : \t" + config.getBoolean(ConfigData.PRINT_LOG));
    }
}
