package utils.communication;

public enum Connections {

    IP_CLIENT("IP_CLIENT"),
    IP_SERVER("IP_SERVER"),
    SERIAL("SERIAL"),

    LIDAR("SERIAL"),
    TEENSY("IP_CLIENT", "192.168.0.2", "23500"),
    RASPI_SECONDAIRE("IP_SERVER", "20000"),
    RASPI_PRIMAIRE("IP_CLIENT", "192.168.0.1", "20000");

    private String type;
    private String[] parameters;
    Connections(String type, String... parameters){
        this.type=type;
        this.parameters=parameters;
    }

    public String getType(){
        return this.type;
    }

    public String[] getParameters(){
        return this.parameters;
    }
}
