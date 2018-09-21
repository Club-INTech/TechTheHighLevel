package junit.unit.container;

import utils.container.Service;

/**
 * Classe de test pour le container
 */
public class C implements Service
{
    public C() {}
    public C(A a) {}

    @Override
    public void updateConfig() {}
}
