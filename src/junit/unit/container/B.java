package junit.unit.container;

import utils.container.Service;

/**
 * Classe de test pour le Container
 */
public class B implements Service
{
    public B(A a) {}

    @Override
    public void updateConfig() {}
}
