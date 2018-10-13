package communication;

import robot.Order;

public enum Connections {

    LOCALHOST_CLIENT(new TCPIPClient("localhost",20000)),
    LOCALHOST_SERVER(new TCPIPServer(20000)),
    LIDAR_SOCKET(new TCPIPClient("localhost", 17685)),
    TEENSY(new TCPIPClient("192.168.0.1",23500));

    AbstractConnection connection;
    Connections(AbstractConnection connection){
        this.connection=connection;
    }

    public void establishConnection(){
        this.connection.connect();
    }

    public AbstractConnection getConnection(){
        return this.connection;
    }

    public void send(String message){
        this.connection.send(message);
    }

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

    public void close(){
        this.connection.close();
    }
}
