import java.util.List;
import java.util.ArrayList;
/**
 * This  a class for Folder struct conforming to FilesystemNode ,
 * which additionally contains child nodes
 * no parameter
 */
public class Foldernode extends FilesystemNode   {
    // all the subnodes container
    private List<FilesystemNode> subnodes = new ArrayList<>();
    // initlizing folder by name
    public Foldernode(String foldername){
        // initliazing the base class node by name
        super(foldername);
    }

    // method add subbnode in folder
    public void addsubnode(FilesystemNode node){
        //System.out.println("hello");
        node.setparent(this);
        subnodes.add(node);

    }
    /*
     * method for finding the sunode
     * return foldernode: subnode
     */
    public List<FilesystemNode>getSubnodes(){
        return subnodes;
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

        // if folder ,sum all subnode.
        // if no children then return
        if (subnodes.isEmpty()){
            return 0;
        }
        int total = 0;
        // check all the subnode
        for(FilesystemNode child : subnodes){
            // add the size
            total +=child.totalsize();
            //total += child.size;
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
        for (FilesystemNode child : subnodes){
            if (child.isfile() ==false){
                if (child.name.equals(item) ){
                    return (Foldernode)child;
                }
            }
        }

        return null;

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
    public void ls(){

        //int total_size = 0;
        // findd all subnode under current postion
        for (FilesystemNode child : subnodes){
            // print out the subnode
            int file_size = child.totalsize();
            System.out.print(child );
            //System.out.print("/");
            System.out.print("(");
            System.out.print(file_size);
            System.out.print("KB");
            System.out.print(")");
            //total_size += file_size;
            System.out.println();
        }
        // get total size
        int total_size = totalsize();
        System.out.println("Total size:(" + total_size + "KB)");
    }

    /*
    This mathod is for print the root
    dosnt has any paremeter and returns.
     *
     */
    public void printallroot(){
        System.out.println(getFullPath());
    }
    public void removefile(String target){
        for (FilesystemNode child : subnodes){
            if (child.isfile() ==true){
                System.out.println("run in to delet");
                if (child.name.equals(target)){
                    subnodes.remove(child);
                    return;
                }
            }

        }
        System.out.println("file dosnt exist");

    }

    public void removefolder(String target){
        for (FilesystemNode child : subnodes){
            if (child.isfile()==false){
                System.out.println("run in to delet");
                if (child.name.equals(target)){
                    subnodes.remove(child);
                    return;
                }
            }

        }
        System.out.println("folder dosnt exist");

    }




}