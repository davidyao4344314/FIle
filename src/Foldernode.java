import java.util.List;
import java.util.ArrayList;

public class Foldernode{
    private String folder;
    private String file;
    private Integer size;
    private Foldernode parent = null;
    private List<Foldernode> 
    // defined subnode 
    subNode = new ArrayList<>();
    
    // constructor for folder 
    public Foldernode(String foldername, String filename, Integer sizenumber){
        this.folder = foldername;
        //this.file = filename;
        //this.size = sizenumber;

        // creat an tree node for file and set it as subnode of folder
        Foldernode file = new Foldernode(filename, sizenumber);
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
        System.out.println("hello");
        node.setparent(this);
        subNode.add(node);
    }
    @Override
    public String toString(){

        return folder + file + size;
    }

    

    }




