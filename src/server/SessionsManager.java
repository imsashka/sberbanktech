package server;

import server.ClientSession;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fef on 08.06.17.
 */
public class SessionsManager {
    private final Set<ClientSession> sessions = new HashSet<ClientSession>();

    public SessionsManager() {
    }

    synchronized public void add(ClientSession session){
        sessions.add(session);
    }

    synchronized public void remove(ClientSession session){
        sessions.remove(session);
    }
}
