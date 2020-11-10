

import java.io.Serializable;


public class ServerToClient implements Serializable{


    public ServerToClient(String username, String text, int port) {
        this.username = username;
        this.text = text;
        this.port = port;
    }

    private String username;
    private String text;
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }



    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }
}
