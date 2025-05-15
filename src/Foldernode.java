import java.util.List;
import java.util.ArrayList;
/**
 * This class for  an node that store evrything
 * no parameter
 */
public class Foldernode{
    private String folder;
    private String file;
    private Integer size;
    private Foldernode parent = null;
    private List<Foldernode>subnode = new ArrayList<>();
    // defined subnode

    /*
    This method for initializeing the foldernode
    parameter string : foldername
    parameter string : filename
    parameter Interger : file size

     */
    public Foldernode(String foldername, String filename, Integer filesize){
        //this.folder = foldername;
        if (filename== null){
            this.folder = foldername;
            this.file = null;
            this.size = filesize;
        }
        else{
            // creat an tree node for file and size
            this.folder = foldername;
            Foldernode file = new Foldernode(filename, filesize);
            // set it as subnode of folder
            this.addsubnode(file);

        }
        //System.out.println(this.folder);
        //this.file = filename;
        //this.size = sizenumber;
    }
    // constructor for creating files
    public Foldernode(String filename, Integer filesize) {
        this.file = filename;
        this.size = filesize;

    }
    // set the parent of the node
    public void setparent(Foldernode parent ){
        this.parent = parent;
    }
    // method add subbnode in folder
    public void addsubnode(Foldernode node){
        //System.out.println("hello");
        node.setparent(this);
        subnode.add(node);

        this.totalsize();
        //System.out.println(this.size);
        //int parentsize = this.getsize();
        //int childsize = node.getsize();

        // prevent it for not being null

        // add file size to the foldeer size
        //this.size += node.size;
        //int currentsize =+ this.size;
        //System.out.println("this.size ="+ currentsize);
        // set the folder size with new size

        //this.size = parentsize;
    }
    public List<Foldernode>getSubnode(){
        return subnode;
    }
    public int getsize(){
        if (this.size == null){
            return 0;
        }
        else{
            return this.size ;

        }
    }

    /*The method for calculating total size of folder
     * no parameter and no return
     */
    public int totalsize(){
       // if is file ,just return size
        if (isfile()){
            return size;

        }
        // if folder ,sum all subnode.
        // if no children then return
        if (subnode.isEmpty()){
            return 0;
        }
        int total = 0;
        // check all the subnode
        for(Foldernode child : subnode ){
            // add the size
            total +=child.totalsize();
            //total += child.size;
        }
        this.size = total;
        return total;
    }
    /*
     * The method to check is it file or not
     * return : true if it is an file
     * return : flase if it not an file
     */
    public boolean isfile(){
        if(this.file != null){
            return true;

        }else{
            return false;
        }

    }
    /*
     * For searching child of an node
     * paremeter : item (the search object)
     * return  : child (if it found the child)
     * return : null (if they havent find anything)
     */
    public Foldernode search(String item){
        for (Foldernode child : subnode ){
            if (child.folder != null){
                if (child.folder.equals(item)){
                    return child;
                }
            }
        }

        return null;

    }
    /* Checkparent for method to find parent of node
     * return : null (if parent not found return null)
     * return : this.parent (when parent if found reuturn it)
     */
    public Foldernode checkparent(){
        if (this.parent == null){
            return null;
        }
        else  return this.parent;

    }
    /*
    This mathod is for print the root
    dosnt has any paremeter and returns.
     *
     */
    public void printallroot(){
        // if they are no parennt for node then return
        if(this.parent == null){
            System.out.println(this);
            return;
        }
        Foldernode current = this.parent;
        // keep printing untill all parent is printed
        while(current != null){
            System.out.print(current);
            current = current.parent;
            // print out current location
            System.out.print(this);
            System.out.println();
        }
    }
    public void removefile(String target){
        for (Foldernode child : subnode){
            if (child.file != null){
                System.out.println("run in to delet");
                if (child.file.equals(target)){
                    subnode.remove(child);
                    return;
                }
            }

        }
        System.out.println("file dosnt exist");

    }
    public void removefolder(String target){
        for (Foldernode child : subnode){
            if (child.folder != null){
                System.out.println("run in to delet");
                if (child.folder.equals(target)){
                    subnode.remove(child);
                    return;
                }
            }

        }
        System.out.println("folder dosnt exist");

    }
    /*
     */

    @Override
    public String toString(){

        if(file == null) {
            return folder + "/";

        }

        else{
            return file + ".";


        }
    }
}


