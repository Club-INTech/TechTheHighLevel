package config;

import pfg.config.Config;

public final class ConfigInstance {

    /** On instancie la config un seule fois */
    private final static Config config = new Config(ConfigData.values(), true, "src/config/config.txt", "Basic, Simple");

    /** Méthode permettant de récupérer la config à partir de n'importe où dans le code tout le code */
    public final static Config getConfig(){
        return config;
    }
}
