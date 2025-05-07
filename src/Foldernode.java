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
    
    // constructor for folder file and size
    public Foldernode(String foldername, String filename, Integer sizenumber){
        this.folder = folder;
        this.file = filename;
        this.size = sizenumber;
    }
    // constructor for creating one instand of foler
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

    

}


