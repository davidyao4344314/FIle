import java.util.List;
import java.util.ArrayList;
/**
 * This method for creating an node the parameter are
 * no paremeter 
 */
public class Foldernode{
    private String folder;
    private String file;
    private Integer size;
    private Foldernode parent = null;
    private List<Foldernode>subnode = new ArrayList<>();    
    // defined subnode 
    
    /*
    This method for initializeing the 
     */ 
    public Foldernode(String foldername, String filename, Integer filesize){
        this.folder = foldername;
        //this.file = filename;
        //this.size = sizenumber;

        // creat an tree node for file and size 
        Foldernode file = new Foldernode(filename, filesize);
        // set it as subnode of folder
        this.addsubnode(file);
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
        System.out.println(this.size);
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
    public void totalsize(){
        // if no children then return 
        if (subnode.isEmpty()){
            return;
        }
        int total = 0;
        // check all the subnode 
        for(Foldernode child : subnode ){
            // add the size     
            child.totalsize();
            total += child.size;
        }
        this.size = total;
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
                    System.out.println("Found the object");
                    return child;
                }
            }
            }

        return null;
            
}
    

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




