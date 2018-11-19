package unitaires.container;

import pfg.config.Config;
import utils.container.Service;

/**
 * Classe de test pour le container
 */
public class C implements Service
{
    public C() {}
    public C(A a) {}

    @Override
    public void updateConfig(Config config) {}
}
