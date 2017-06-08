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
    private ObjectInputStream  reader;
    private ObjectOutputStream writer;

    public Client(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start(){

        try{
            Socket socket = new Socket("localhost", port);

            System.out.printf("%d/is Connected%n", socket.getPort());
            writer = new ObjectOutputStream(socket.getOutputStream());
            reader = new ObjectInputStream(socket.getInputStream());
            System.out.println("Введите команду: (1 или 2)");
            while (true) serviceStart();

        }catch (IOException e){
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.exit(2);
        }

    }

    private void serviceStart() throws IOException, ClassNotFoundException {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            writer.writeUTF(keyboard.readLine());
            writer.flush();
            System.out.println("Ответ с сервера: " + reader.readObject());
       }

    }
