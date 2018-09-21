package junit.unit.container;

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
    public void updateConfig() {
        config = true;
    }

    public boolean isConfig() {
        return config;
    }
}
