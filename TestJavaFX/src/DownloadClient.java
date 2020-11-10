

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class DownloadClient implements Runnable {
    Frame myFrame = new Frame();

    public static volatile boolean finished=false;






    private ServerSocket ss;

    private Thread t;
    public DownloadClient(int port) {
        try {
            ss = new ServerSocket(port);
            this.t=new Thread(this);
            t.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) if(finished==true) break;
        while (finished) {
            try {
                Socket clientSock = ss.accept();
                saveFile(clientSock);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile(Socket clientSock) throws IOException {





        byte[] buffer = new byte[65536];

        ObjectInputStream ois = new ObjectInputStream(clientSock.getInputStream());
        String temp=null;
        try {
            temp=(String)ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        StringTokenizer st=new StringTokenizer(temp,".");
        String tempsize=st.nextToken();
        String ext=st.nextToken();

        System.out.println("dloadingggggggggggggggg");
        ////////////dialog///////////////////


        FileDialog fileDialog = new FileDialog(myFrame,
                "Choose Destination to save your file: ", FileDialog.SAVE);
        fileDialog.setDirectory(null);
        fileDialog.setFile("enter file name here");
        fileDialog.setVisible(true);



        String targetFileName = fileDialog.getDirectory()
                + fileDialog.getFile()  + "." + ext;

        System.out.println("File will be saved to: " + targetFileName);





        DataInputStream dis = new DataInputStream(clientSock.getInputStream());
        FileOutputStream fos = new FileOutputStream(targetFileName);

        int filesize =Integer.parseInt(tempsize) ; // Send file size in separate msg
        int read = 0;
        int totalRead = 0;
        int remaining = filesize;
        while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
            totalRead += read;
            remaining -= read;
            System.out.println("read " + totalRead + " bytes.remaining " + remaining + " bytes.");
            fos.write(buffer, 0, read);
        }

        fos.close();
        dis.close();
        finished=false;
    }

//    public static void main(String[] args) {
//        DownloadClient fs = new DownloadClient(1988);
//
//    }

}