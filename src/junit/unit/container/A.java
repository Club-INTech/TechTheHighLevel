package junit.unit.container;

import utils.container.Service;

/**
 * Classe de test pour le Container
 */
public class A implements Service
{
    private A(B b) {}

    @Override
    public void updateConfig() {}
}
