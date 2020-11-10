



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class ReadThreadServer implements Runnable {
    private Thread thr;
    private NetworkUtil nc;
    private HashMap<String, NetworkUtil> clientMap;
    //public HashMap<String, String> userData;
    private HashMap<String, List<String>> filelist;
    private HashMap<String, String> sizelist;
    private List<String> filenames=new ArrayList<>();

    public ReadThreadServer(NetworkUtil nc, HashMap<String, NetworkUtil> clientMap,
                            HashMap<String, List<String>> filelist,
                            HashMap<String, String> sizelist,
                            List<String> filenames) {
        this.nc = nc;
        this.clientMap = clientMap;
        this.sizelist = sizelist;
        this.filelist = filelist;
        this.filenames=filenames;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {

                Object o = nc.read();
                String tempstr=null;
                SMessage temp = null;
                CMessage ctemp = null;
                if (o != null) {
                    if (o instanceof SMessage) {
                        temp = (SMessage) o;

                            System.out.println(temp.getFrom()+": "+temp.getText());

                    }
                    else if (o instanceof String) {
                        tempstr = (String) o;
                        //System.out.println(tempstr);
                        StringTokenizer tst=new StringTokenizer(tempstr,"%");
                        String str=tst.nextToken();
                        String cliname=tst.nextToken();
                       // System.out.println(cliname + " " + str);
                        //if (userData.get(temp.getFrom()).equals(temp.getPassword())) {
                        boolean error=true;

                            for(int i=0;i<filenames.size();i++)

                            {
                                //System.out.println(filenames.get(i) + "   " + str);

                                if (filenames.get(i).contains(str))  {

                                    str=filenames.get(i);
                                    int count = filelist.get(str).size();
                                    String seedstr = Integer.toString(count);
                                    String sizestr = sizelist.get(str);
                                    List<String> nameStr = filelist.get(str);
                                    if (nameStr.contains(cliname))
                                    {
                                        String msg="File already exists.";
                                        System.out.println(msg);
                                        nc.write(msg);
                                        error=false;
                                        break;
                                    }
                                    else {
                                        String msg = "File found.   Total Seeds: " + seedstr + "  .File size:  " + sizestr + " bytes.%" + nameStr.get(nameStr.size()-1) + "%" + str;
                                        nc.write(msg);
                                        error = false;
                                        break;
                                    }

                                }

                            }
                            if (error)
                            {
                                String msg = "File not found.";
                                System.out.println(msg);
                                nc.write(msg);
                            }

                    }
                    else if(o instanceof CMessage) {
                        ctemp = (CMessage)o;
                        ServerToClient t = new ServerToClient(ctemp.getFrom(), ctemp.getText(),ctemp.getPort());
                        System.out.println("got req");

                            if(clientMap.get(ctemp.getTo())!= null)
                            {
                                clientMap.get(ctemp.getTo()).write(t);
                            }


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