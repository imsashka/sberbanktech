package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by aleksandr on 07.06.17.
 */public class Server implements Runnable
{
    private final int port;
    private final SessionsManager sessions = new SessionsManager();
    private boolean running = true;

    public SessionsManager getSessions() {
        return sessions;
    }

    public boolean isRunning() {
        return running;
    }

    public void changeRunning() {
        this.running = !running;
    }

    public Server() {
        this.port = 4000;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(this.port);
            //Цикл ожидания подключения
            while(isRunning()) {
                Socket clientSocket = ss.accept();
                System.out.println(clientSocket.getInetAddress().toString() + ":"+ clientSocket.getPort() + "/ подключен");
                //Создаем сессию
                ClientSession clientSession = new ClientSession(clientSocket, sessions);
                //Добавляем сессию в список
                this.sessions.add(clientSession);
                clientSession.start();
            }
            ss.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
