package net.larry1123.util.customPacket;

import java.util.ArrayList;

public class RemoteServer {

    private static ArrayList<RemoteServer> servers = new ArrayList<RemoteServer>();

    private String serverName;

    private RemoteServer(String name) {
        serverName = name;
    }

    public String getServerName() {
        return serverName;
    }

    public static RemoteServer getServer(String name) {
        for (RemoteServer server : servers) {
            if (server.serverName.equals(name)) {
                return server;
            }
        }
        RemoteServer ret = new RemoteServer(name);
        servers.add(ret);
        return ret;
    }

}
