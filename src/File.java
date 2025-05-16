/*
a class for File struct conforming to FilesystemNode
 */
public class File  extends FilesystemNode {
    // file size
    private  int size=0;
/*
intitallizing file node 
parameter String : name
paramter int : size
 */
    public File(String name,int size) {
        super(name);
        this.size=size;
    }
    /*
    an file return true
    return bolean true 
     */
    @Override
    public boolean isfile() {
        return true;
    }
    
    /*
    an method for returning the file size size 
    return int : size
     */
    @Override
    public int totalsize() {
        return size;
    }

}


