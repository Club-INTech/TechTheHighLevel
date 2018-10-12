package unit;

import unit.container.A;
import unit.container.C;
import unit.container.D;
import unit.container.E;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utils.Container;
import utils.container.ContainerException;

/**
 * Tests du Container TODO Compléter pour tester le démarrage des Thread et la destruction des objets
 */
public class Test_Container
{
    /**
     * L'instance à tester
     */
    private Container container;

    @Before
    public void setUp()
    {
        container = Container.getInstance();
        Assert.assertNotNull(container.getConfig());
    }

    @After
    public void tearDown()
    {
        container = null;
    }

    @Test(expected = ContainerException.class)
    public void testCircularDependencies() throws Exception
    {
        container.getService(A.class);
    }

    @Test(expected = ContainerException.class)
    public void testMultipleConstructors() throws Exception
    {
        container.getService(C.class);
    }

    @Test
    public void testSimple() throws Exception
    {
        D d = container.getService(D.class);
        Assert.assertTrue(container.getInstanciedServices().containsKey(E.class.getSimpleName()));
        Assert.assertTrue(container.getInstanciedServices().containsKey(D.class.getSimpleName()));
        Assert.assertTrue(((E)container.getInstanciedServices().get(E.class.getSimpleName())).isConfig());
        Assert.assertTrue(((D)container.getInstanciedServices().get(D.class.getSimpleName())).isConfig());
    }
}
