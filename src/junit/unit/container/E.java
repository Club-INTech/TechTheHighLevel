package junit.unit.container;

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
    public void updateConfig() {
        config = true;
    }

    public boolean isConfig() {
        return config;
    }
}
