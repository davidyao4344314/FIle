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

    /*
     * This method is for adding subnode to parent node
     * parameter file or foolder: FilesystemNode 
     * parameter file or folder : node
     * 
     */
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
        // if size is smaller than 1 kb or bigger than 4194304 kb 
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
        // return the parent
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
    /*
    this method is for deleting subfolder  and sub filesfiles
    parameter no parmeters
    returns no returns 
     */
    public void deleteSubNode(){
        //System.out.println("delete folder "+getFullPath());
        // delet all sub files in the tree map
        subFiles.clear();
        // delete all sub folders(include sub sub folder and files)
        for (Map.Entry<String, FilesystemNode> entry : subFolders.entrySet()) {
            Foldernode subFoder = (Foldernode)entry.getValue();
            System.out.println("Will delete subfolder"+subFoder.getFullPath());
            subFoder.deleteSubNode();
        }
        // delet sub files in the tree map 
        subFolders.clear();

    }
    /*
        rmdir <foldername> to delete a folder in the current folder. If no such folder exists, tell
    the user
    rmdir also deletes all the files and subfolders contained within the specified
    folder
     */
    public void removefolder(String target){
        Foldernode subFoder = (Foldernode)subFolders.get(target);
        if (subFoder==null) {
            System.out.println("Folder:"+target+" dosnt exist");
            return;
        }
        subFoder.deleteSubNode();
        subFolders.remove(target);


    }




}


