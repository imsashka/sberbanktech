package client;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by aleksandr on 07.06.17.
 */
public class ClientLauncher {
    public static void main(String[] args) {
        try {
            new Client(InetAddress.getByName("localhost"), 4000).start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
