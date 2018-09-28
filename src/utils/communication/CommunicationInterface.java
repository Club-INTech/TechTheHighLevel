package utils.communication;

public class CommunicationInterface{

    private AbstractComm device;

    public void send(Order order) {
        this.device.send(order.getOrderStr());
    }

    public void messageHandlerWrapper(String message){
        //Override cette méthode quand on instancie la connexion
        System.out.println("IL FAUT OVERRIDE CETTE PUTAIN DE METHODE : messageHandlerWrapper");
    }

    public CommunicationInterface(Connections connection){
        if (connection.getType().equals(Connections.IP_CLIENT.getType())){
            this.device = new TCPIPClient(connection.getParameters()[0],Integer.parseInt(connection.getParameters()[1])){
                @Override
                public void messageHandler(String message) {
                    messageHandlerWrapper(message);
                }
            };
        }
        else if (connection.getType().equals(Connections.IP_SERVER.getType())){
            this.device = new TCPIPServer(Integer.parseInt(connection.getParameters()[0])){
                @Override
                public void messageHandler(String message) {
                    messageHandlerWrapper(message);
                }
            };
        }
        else if (connection.getType().equals(Connections.SERIAL.getType())){

        }
    }
}
