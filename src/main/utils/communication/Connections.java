package utils.communication;

import robot.Order;

/** Enum qui regroupe les différentes connexions */
public enum Connections {

    LOCALHOST_CLIENT(new TCPIPClient("localhost",20000)),   //Connexion localhost en mode client
    LOCALHOST_SERVER(new TCPIPServer(20000)),                   //Connexion localhost en mode server
    LIDAR_SOCKET(new TCPIPClient("localhost", 17685)),      //Connexion localhost pour récupérer les données du lider
    TEENSY(new TCPIPClient("192.168.0.1",23500));           //Connexion à la Teensy

    private AbstractConnection connection;

    /** COnstructeur */
    Connections(AbstractConnection connection){
        this.connection=connection;
    }

    /** Permet d'établir la connexion */
    public void establishConnection(){
        this.connection.connect();
    }

    /** Permet de récupérer la connexion */
    public AbstractConnection getConnection(){
        return this.connection;
    }

    /** Permet d'envoyer un message avec une chaîne de caractère */
    public void send(String message){
        this.connection.send(message);
    }

    /** Permet d'envoyer un message en fonction d'un des ordres disponibles, avec des éventuels arguments */
    public void send(Order order, String... arguments){
        if (arguments!= null){
            StringBuilder message = new StringBuilder();
            message.append(order.getOrderStr());
            for (String argument : arguments) {
                message.append(" ");
                message.append(argument);
            }
            this.connection.send(message.toString());
        }
        else {
            this.connection.send(order.getOrderStr());
        }
    }

    /** Ferme la connexion */
    public void close(){
        this.connection.close();
    }
}
