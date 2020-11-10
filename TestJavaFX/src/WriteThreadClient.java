

import java.util.Scanner;

//import com.sun.org.apache.xpath.internal.SourceTree;
//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;


public class WriteThreadClient implements Runnable {

    private Thread thr;
    private NetworkUtil nc;
    String name;

    public static String getSearchString() {
        return searchString;
    }

    public static void setSearchString(String searchString) {
        WriteThreadClient.searchString = searchString;
    }

    private static String searchString;

    public WriteThreadClient(NetworkUtil nc, String name) {
        this.nc = nc;
        this.name = name;
        this.thr = new Thread(this);
        thr.start();
    }


    public void run() {
        try {


            Scanner input = new Scanner(System.in);
            while (true) {
                String s = null;
                Thread.sleep(500);
                System.out.println("Enter your file name");
//                do{
//                    s = input.nextLine();
  //              }while(s == null);

                while (true){
                    //System.out.println(s);
                    Thread.sleep(100);
                    if(s != null){
                        break;
                    }
                    s = getSearchString();
                }

                if( s.equalsIgnoreCase("") || s.contains(" ") || s.equals(null)){
                System.out.println("File not found" );}

                else if(s.equalsIgnoreCase("download") )
                {
                    String username, receipient,message;
                    username=Client.getClientname();
                    receipient=Client.getRequestname();
                    message=Client.getFilename();
                    int clientport=Client.getPortnum();
                    if(receipient==null || message==null) {
                        System.out.println("No file choosen.");
                        continue;
                    }
                    else {

                        CMessage cm = new CMessage(username, receipient, message, clientport);
                        System.out.println(cm.getFrom() + cm.getPort() + cm.getTo() + cm.getText());
                        nc.write(cm);
                        DownloadClient.finished=true;
                        searchString=null;

                        //DownloadClient.runner();
                    }
                }
                else
                {
                    String modS=s+"%"+Client.getClientname();

                   // System.out.println(modS);
                    Thread.sleep(100);

                    nc.write(modS);

                    setSearchString(null);
                }

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            nc.closeConnection();
        }
    }
}



