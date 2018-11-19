package unitaires.container;

import pfg.config.Config;
import utils.container.Service;

/**
 * Classe de test pour le container
 */
public class D implements Service
{
    private E e;
    private boolean config;
    public D(E e)
    {
        this.e = e;
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
