package it.polimi.ingsw.network;

import it.polimi.ingsw.network.message.Message;

public abstract class ClientConnection {
    private boolean connected;
    private String username;
    public void sendMessage(Message msg){};

    public boolean isConnected() {
        return connected;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
