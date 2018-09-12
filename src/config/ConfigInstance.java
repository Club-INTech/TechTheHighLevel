package config;

import pfg.config.Config;

public final class ConfigInstance {

    private final static Config config = new Config(ConfigData.values(), true, "src/config/config.txt", "Basic, Simple");;

    public final static Config getConfig(){
        return config;
    }
}
