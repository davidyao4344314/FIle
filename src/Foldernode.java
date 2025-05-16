import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * This  a class for Folder struct conforming to FilesystemNode ,
 * which additionally contains child nodes
 * no parameter
 */
public class Foldernode extends FilesystemNode   {
    // use treem map can automactilay sort of alphatical order for ls
    // this is for all sub folders
    private  Map<String, FilesystemNode> subFolders = new TreeMap<>();
    // this for all subfiles
    private  Map<String, FilesystemNode> subFiles = new TreeMap<>();
    // initlizing folder by name
    public Foldernode(String foldername){
        // initliazing the base class node by name
        super(foldername);
    }

    // method add subbnode in folder
    public void addsubnode(FilesystemNode node){
        //System.out.println("hello");
        node.setparent(this);
        if(node.isfile()){
            subFiles.put(node.name,node);
        }
        else{
            subFolders.put(node.name,node);
        }


    }


    /* Method for make new folder in folder node
     * parameter String : path
     * no return
     */
    public Foldernode mkdir(String path){

        // new folder
        String reason = FilesystemNode.checkName(path, false);
        if(reason !=null){
            System.out.println(reason);
            return null;
        }

        Foldernode folder = new Foldernode( path);
        addsubnode(folder);
        return folder;

    }
    /*
    Method for touch creating file
    parameter String : path
    parameter int : size
    return file : newfile
     */
    public File touch(String path,int size){
        String reason = FilesystemNode.checkName(path, true);
        if(reason !=null){
            System.out.println(reason);
            return null;
        }

        if (size<1 || size>4194304 ){
            System.out.println("Size Must more than 1KB and less than 4 GB（4194304）");
            return null;
        }
        File newfile = new File( path, size);
        addsubnode(newfile);
        return newfile;

    }
    /*The method for calculating total size of folder
     * return int: total
     */
    public int totalsize(){
        
        int total = 0;
        // check all the subnode summary for total files
        for (Map.Entry<String, FilesystemNode> entry : subFolders.entrySet()) {
            total+=entry.getValue().totalsize();
        }
        // for all files size 
        for (Map.Entry<String, FilesystemNode> entry : subFiles.entrySet()) {
            total+=entry.getValue().totalsize();
        }


        return total;
    }
    /*
     * The method to check is it file or not
     * return : true if it is an file
     * return : flase if it not an file
     */
    public boolean isfile(){
            return false;
    }
    /*
     * For searching child of an node
     * paremeter : item (the search object)
     * return  : child (if it found the child)
     * return : null (if they havent find anything)
     */
    public Foldernode search(String item){


        return (Foldernode) subFolders.get(item);

    }
    /*
    Checkparent for method to find parent of node
     * return : null (if parent not found return null)
     * return : this.parent (when parent if found reuturn it)
     */
    public Foldernode checkparent(){
        // if no parent
        if (this.parent == null){
            return null;
        }
        else  return (Foldernode) parent;

    }
    /*
    This method is for ls in this method it show name in alpbaticle order
    parameter no parameter
    no returns
     */
    public void ls(){
        
        // list folder first in alphabetical order
        for (Map.Entry<String, FilesystemNode> entry : subFolders.entrySet()) {
            FilesystemNode node = entry.getValue();
            System.out.println(node + ": (" + node.totalsize()+"KB)");

        }
        // list the files other folder in alphabetical order
        for (Map.Entry<String, FilesystemNode> entry : subFiles.entrySet()) {
            FilesystemNode node = entry.getValue();
            System.out.println(node + ": (" + node.totalsize()+"KB)");

        }
        // get total size
        int total_size = totalsize();
        System.out.println("\nTotal size:(" + total_size + "KB)");
    }

    /*
    This mathod is for print the root
    dosnt has any paremeter and returns.
     *
     */
    public void printallroot(){
        System.out.println(getFullPath());
    }
    /*
    this is an method for remove file it remove file from node
    parameter String : target
     */
    public void removefile(String target){
        // find target
        FilesystemNode file = subFiles.get(target);
        // if file dosn't exist print error message and return
        if (file==null) {
            System.out.println("file:"+target+" dosnt exist");
            return;
        }
        // remove the subfiles
        subFiles.remove(target);
    }
    // the file and subfolder under folder can't be deleted fix later
    public void removefolder(String target){
        FilesystemNode file = subFolders.get(target);
        if (file==null) {
            System.out.println("Folder:"+target+" dosnt exist");
            return;
        }
        subFolders.remove(target);


    }




}