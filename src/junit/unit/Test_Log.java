package junit.unit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;

public class Test_Log
{
    /**
     * Stream & Fichier de sortie pour contrôler le résultat des tests
     */
    private PrintStream output;
    private File file;
    private BufferedReader input;

    @Before
    public void setUp() throws Exception
    {
        file = new File("LogTest");
        output = new PrintStream(file);
        input = new BufferedReader(new FileReader(file));
        System.setOut(output);
        Log.activeAllChannels();
        Log.init();
    }

    @After
    public void tearDown() throws Exception
    {
        file.delete();
        file = null;
        output = null;
        input = null;
        System.setOut(System.out);
        Log.disableAllChannels();
        Log.close();
    }

    @Test
    public void testLogSimple() throws Exception
    {
        Log.COMMUNICATION.debug("TC");
        Log.LOCOMOTION.debug("TL");
        Log.DATA_HANDLER.warning("TD");
        Log.STRATEGY.critical("TS");
        output.flush();

        Assert.assertTrue(input.readLine().endsWith("TC"));
        Assert.assertTrue(input.readLine().endsWith("TL"));
        Assert.assertTrue(input.readLine().endsWith("TD"));
        Assert.assertTrue(input.readLine().endsWith("TS"));
    }

    @Test
    public void testLogChannel() throws Exception
    {
        Log.COMMUNICATION.setActive(false);
        Log.DATA_HANDLER.setActive(false);

        Log.COMMUNICATION.debug("TC");
        Log.LOCOMOTION.debug("TL");
        Log.DATA_HANDLER.warning("TD");
        Log.STRATEGY.critical("TS");
        output.flush();

        Assert.assertTrue(input.readLine().endsWith("TL"));
        Assert.assertTrue(input.readLine().endsWith("TS"));
    }
}
