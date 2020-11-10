

import java.io.Serializable;


public class CMessage implements Serializable{
    private String from;

    public CMessage(String from, String to, String text, int port) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.port = port;
    }

    private String to;
    private String text;
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }



    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFrom() {
        return from;
    }


    public String getTo() {
        return to;
    }

    public String getText() {
        return text;
    }


}
