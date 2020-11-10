



import java.io.Serializable;

public class Store implements Serializable {
    public String[] getStorefiles() {
        return Storefiles;
    }

    public void setStorefiles(String[] storefiles) {
        Storefiles = storefiles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String[] Storefiles;
    private String name;
    private String[] size;
//
    public String[] getSize() {
        return size;
    }
//
//

    public Store(String[] storefiles, String name,String[] size) {
        this.Storefiles = storefiles;
        this.name = name;
        this.size=size;
    }

//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}


}
