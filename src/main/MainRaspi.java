import utils.ConfigData;
import utils.Container;
import utils.communication.Connections;
import utils.communication.ConnectionsManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainRaspi {
    public static void main(String[] args){
        Container container;
        String hierarchy;
        try {
            hierarchy = Files.readAllLines(Paths.get("config/hierarchy.txt")).get(0);
        } catch (IOException e) {
            hierarchy=null;
            e.printStackTrace();
        }
        container = Container.getInstance(hierarchy);

        boolean isMaster = container.getConfig().getBoolean(ConfigData.MASTER);
        ConnectionsManager connManager = new ConnectionsManager();

        if (isMaster) {
            connManager.startAllConnections(Connections.MASTER_SERVER);
            System.out.println("Connections estalished !");
            for (int i=0; i<100; i++) {
                Connections.MASTER_SERVER.send(Integer.toString(i));
            }
        }
        else{
            connManager.startAllConnections(Connections.TO_MASTER);
            System.out.println("Connections estalished !");
            for (int i=100; i<200; i++) {
                Connections.TO_MASTER.send(Integer.toString(i));
            }
        }

    }
}
