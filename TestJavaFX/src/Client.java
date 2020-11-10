import java.io.File;
import java.util.Random;
import java.util.Scanner;

//import static com.sun.activation.registries.LogSupport.log;


//import static jdk.nashorn.internal.objects.NativeMath.log;


public class Client implements Runnable{
    final String[] fileNames = {
            "D:\\IDM\\1.jpg",
            "D:\\IDM\\2.jpg",
            "D:\\IDM\\3.jpg",
            "D:\\IDM\\4.jpg",
            "D:\\IDM\\5.jpg",

            "D:\\IDM\\od.mp3",
            "D:\\IDM\\pd.mp3",
            "D:\\IDM\\cse.mp3",
            "D:\\IDM\\dm.mp3",
            "D:\\IDM\\che.mp3",


    };

    public static String getClientname() {
        return clientname;
    }

    public static void setClientname(String cliname) {
        clientname = cliname;
    }

    public static String getRequestname() {
        return requestname;
    }

    public static void setRequestname(String reqname) {
        requestname = reqname;
    }

    public static String getFilename() {
        return filename;
    }

    public static void setFilename(String filename) {
        Client.filename = filename;
    }

    public static int getPortnum() {
        return portnum;
    }

    public static void setPortnum(int portnum) {
        Client.portnum = portnum;
    }

    private static String clientname;
    private static String requestname;
    private static String filename;
    private static int portnum;

    public static String getFshome() {
        return fshome;
    }

    public static void setFshome(String fshome) {
        Client.fshome = fshome;
    }

    private static String fshome;

    private String serverAddress;
    private int serverPort;

    private Thread t;

    public static String fileshowtohome(String str){
        //System.out.println("hfhfhfhfhfhfh");
        //String str = "sajib\nhello";
        return  str;
    }




    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.t = new Thread(this);
        t.start();
    }
    public void run(){
        try {
           // Scanner input = new Scanner(System.in);
            System.out.println("New Client Registration: ");
            String username = null;

            while (true){
                //System.out.println(username);
                Thread.sleep(100);
                if(username != null){
                    break;
                }
                username = getClientname();
            }

            Random rnd = new Random();
            String[] Storedfiles = new String[5];
            String[] StoredfileSizes = new String[5];


            for(int i=0;i<5;i++) {
                Storedfiles[i] = fileNames[rnd.nextInt(10)];
                //System.out.println(Storedfiles[i]);
            }


            File myFiles[] = new File[5];
            String passedtoshow="Your name: "+clientname+"\n";

            for (int i = 0; i < 5; i++) {
                File tempfile = new File(Storedfiles[i]);
                myFiles[i] = tempfile;
                StoredfileSizes[i] = Long.toString(tempfile.length());
                if (!tempfile.exists()) {
                   // log("File does not exist!", null);
                } else
                {
                    String tempString=myFiles[i].getAbsolutePath() + " " + myFiles[i].length() + "bytes.";
                    passedtoshow=passedtoshow+"\n"+tempString;
                    System.out.println(tempString);
                }
            }
            fshome=passedtoshow;



            NetworkUtil nc = new NetworkUtil(serverAddress, serverPort);
            Store store= new Store(Storedfiles,username,StoredfileSizes);

            nc.write(store);
            new ReadThread(nc);

            new WriteThreadClient(nc, username);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
          //  System.out.println(getPortnum());
            DownloadClient fs = new DownloadClient(getPortnum());


           // UploadClient uc = new UploadClient("127.0.0.1", 33334, "D:\\IDM\\1.mkv");
          //DownloadClient dc = new DownloadClient(33334);
            //new WriteThreadClient(nc, Storedfiles);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    public static void main(String args[]) {
//        String serverAddress = "127.0.0.1";
//        int serverPort = 33333;
//        Client client = new Client(serverAddress, serverPort);
//
//    }
}

