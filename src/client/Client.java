package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by aleksandr on 07.06.17.
 */
public class Client {
    private final InetAddress host;
    private final int port;

    public Client(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start(){
        try(Socket socket = new Socket(host, port)){

            ObjectInputStream  reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());

            writer.writeUTF(socket.getLocalSocketAddress()+ ": Соединение установлено");
            System.out.println(reader.readUTF());

            serviceStart();

        }catch (IOException ignore){

        }

    }

    private void serviceStart(){
        System.out.println("Введите ");
    }
}
