

import java.io.*;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.StringTokenizer;

public class UploadClient implements Runnable {

    private Socket s;
    private Thread t;
    private String file;

    public UploadClient(String host, int port, String file) {
        try {
            s = new Socket(host, port);
            this.file=file;
            this.t=new Thread(this);
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            sendFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendFile(String file) throws IOException {
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[65536];
        File temp = new File(file);
        StringTokenizer st=new StringTokenizer(temp.getName(),".");
        String ext=st.nextToken();
        ext=st.nextToken();
        String size=Long.toString(temp.length());
        ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
        String sizeext=size+"."+ext;
        oos.writeObject(sizeext);


        while (fis.read(buffer) > -1) {
            dos.write(buffer);
        }

        fis.close();
        dos.close();
    }

//    public static void main(String[] args) {
//        UploadClient fc = new UploadClient("localhost", 1988, "D:\\IDM\\1.mkv");
//    }



}
