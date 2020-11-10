



import java.util.StringTokenizer;

public class ReadThread implements Runnable {
    private Thread thr;
    private NetworkUtil nc;
    public static volatile boolean fileExists=false;
    public static volatile boolean filenotfound=false;
    public static volatile String filefound;


    public ReadThread(NetworkUtil nc) {
        this.nc = nc;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = nc.read();
                CMessage temp = null;
                String s=null;
                if (o != null) {
                    if (o instanceof CMessage) {
                        temp = (CMessage)o;
                        ServerToClient mes = new ServerToClient(temp.getFrom(), temp.getText(),temp.getPort());
                        System.out.println(mes);
                    }
                    else if(o instanceof Integer)
                    {
                        int port=(Integer)o + 33333;
                        System.out.println("Port is " + port );
                        Client.setPortnum(port);
                    }
                    else if (o instanceof String) {
                        s = (String)o;
                        if(s.equalsIgnoreCase("File not found.")) {
                            System.out.println("File not found.");
                            filenotfound=true;
                            continue;
                        }
                        else if(s.equalsIgnoreCase("File already exists.")) {
                            System.out.println("File already exists.");
                            fileExists=true;
                            continue;
                        }
                        else {
                            //System.out.println("after reaaaaaaaad");
                            String[] st = s.split("%");
                            String s1 = st[0];
                            String s2 = st[1];
                            String s3 = st[2];

                            Client.setFilename(s3);
                            Client.setRequestname(s2);

                            System.out.println(s1);
                            filefound=s1;

                            //System.out.println(s2);
                            //System.out.println(s3);
                            System.out.println("Press DOWNLOAD to download file or search again.");


                        }
                    }
//                    else if (o instanceof N) {
//                        s = (String)o;
//
//                        System.out.println(s);
//                    }
                    else if(o instanceof ServerToClient){

                        ServerToClient t = (ServerToClient)o;
                        System.out.println(t.getUsername()+": "+t.getText());
                        //System.out.println("got itttttttttttttttttttt" + t.getPort());
                        UploadClient fc = new UploadClient("localhost", t.getPort(), t.getText());
                        //System.out.println("got itttttttttttttttttttt");
                        Thread.sleep(100);

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            nc.closeConnection();
        }
    }
}



