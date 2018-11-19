package unitaires.container;

import pfg.config.Config;
import utils.container.Service;

/**
 * Classe de test pour le container
 */
public class E implements Service
{
    private boolean config;
    public E() {
        config = false;
    }

    @Override
    public void updateConfig(Config config) {
        this.config = true;
    }

    public boolean isConfig() {
        return config;
    }
}
