import connection.ConnectionManager;
import utils.ConfigData;
import utils.Container;
import utils.container.ContainerException;

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
        try {
            ConnectionManager connectionManager = container.getService(ConnectionManager.class);
        } catch (ContainerException e) {
            e.printStackTrace();
        }

    }
}
