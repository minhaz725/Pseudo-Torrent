

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Server {






    private int clientcount;
    private ServerSocket serverSocket;
    public int i = 1;
    private HashMap<String, NetworkUtil> clientMap;
//    public HashMap<String, String> userData;
    private HashMap<String, List<String>> filelist;
    private HashMap<String, String> sizelist;
    private List<String> filenames;

    Server() {
          clientMap = new HashMap<>();

  //      userData = new HashMap<>();
          filelist = new HashMap<>();
          sizelist =new HashMap<>();
          filenames=new ArrayList<>();
        try {
            serverSocket = new ServerSocket(33333);
            //new WriteThreadServer(clientMap, "Server");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server stats:" + e);
        }
    }

    public void serve(Socket clientSocket) {


        NetworkUtil nc = new NetworkUtil(clientSocket);
        Object o = nc.read();
        nc.write(++clientcount);
        //RMessage temp = null;
        Store store=null;

//        for (int j=0;j<2;j++) {
//            if (o != null) {
//                if (o instanceof RMessage) {
//                    temp = (RMessage) o;
//
//
//                    clientMap.put(temp.getUsername(), nc);
//                    userData.put(temp.getUsername(), temp.getPassword());
//                }
                if (o instanceof Store) {

                    store = (Store) o;
                    String templist[] = store.getStorefiles();
                    String tempSize[]= store.getSize();
                    clientMap.put(store.getName(), nc);


                    for (int i = 0; i < 5; i++) {

                        if (filelist.containsKey(templist[i])) {
                            List<String> tempArraylist = filelist.get(templist[i]);
                            if(tempArraylist.contains(store.getName()))
                                System.out.println("stored:" + "SIZE " + templist.length + " " + filelist.keySet());
                            else {
                                tempArraylist.add(store.getName());
                                System.out.println("stored:" + "SIZE " + templist.length + " " + filelist.keySet());
                            }/////remove then add??///////
                        } else {
                            List<String> tempArraylist = new ArrayList<>();
//                            nc = clientMap.get(store.getName());
                            tempArraylist.add(store.getName());

                            filelist.put(templist[i], tempArraylist);
                            sizelist.put(templist[i], tempSize[i]);
                            filenames.add(templist[i]);

                            System.out.println("stored:" + "SIZE " + templist.length + " " + filelist.keySet());
                        }
                    }

                }


        new ReadThreadServer(nc, clientMap, filelist,sizelist,filenames);
    }

    public static void main(String args[]) {
        Server server = new Server();
    }
}
