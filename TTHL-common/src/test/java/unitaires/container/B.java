package unitaires.container;

import pfg.config.Config;
import utils.container.Service;

/**
 * Classe de test pour le Container
 */
public class B implements Service
{
    public B(A a) {}

    @Override
    public void updateConfig(Config config) {}
}
