package server;

import helper.InfoSessions;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * Created by fef on 08.06.17.
 */
public class ClientSession extends Thread {
    private final Socket socket;
    private boolean runining = true;
    private SessionsManager sessions;
    private final ObjectInputStream  reader;
    private final ObjectOutputStream writer;


    public ClientSession(Socket socket, SessionsManager sessions) throws IOException {
        this.socket = socket;
        this.sessions = sessions;
        this.writer = new ObjectOutputStream(socket.getOutputStream());
        this.reader = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        String line;

        while(isRunining()) {
            try {

                line = reader.readUTF();

                System.out.printf("%s:%s/ %s\n",socket.getInetAddress(), socket.getPort(),line);

                switch (line) {
                    case "1":
                        writer.writeObject(new Date());
                        break;
                    case "2":
                        writer.writeObject("Температура воздуха 100 градусов");
                        break;
                    case "exit":
                        socket.close();
                        throw new IOException();
                    default:
                        writer.writeObject("ERROR");
                        break;
                }

                writer.flush();

            } catch (IOException e) {
                switchOff();
                sessions.remove(this);
                System.out.printf("%s:%s/ %s\n",socket.getInetAddress(), socket.getPort(),"is disconnected!");
            }
        }
    }
    @Override
    public int hashCode() {
        return socket.getPort();
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    public boolean isRunining() {
        return runining;
    }

    public void switchOff(){
        this.runining = false;
    }
}
