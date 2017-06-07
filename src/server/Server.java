package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

/**
 * Created by aleksandr on 07.06.17.
 */public class Server implements Runnable
{
    private final int port;

    public Server() {
        port = 4000;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket();
            while(true) {
                ss.accept();

                ObjectOutputStream writer = new ObjectOutputStream(ss.)

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
