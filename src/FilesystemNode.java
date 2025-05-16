/*
a FilesystemNode protocol, describing what a filesystem node contains (i.e. a name, an
optional parent)
 */
public abstract class FilesystemNode {
    public String name;
    public FilesystemNode(String name){
        this.name=name;
    }
    // we don't know the node is file or not
    public abstract boolean isfile();
    // if is file sumarise all subnode
    // will be implement in sub class folder or file
    public abstract int totalsize();
    /*
    An method for seting parent
    parameter FilesystemNode : parent
     */
    public void setparent(FilesystemNode parent ){
        this.parent = parent;
    }
    // sub class could get parent straight away through protected
    protected FilesystemNode parent = null;
    /*
     an extension to FilesystemNode so that it conforms 
     to CustomStringConvertible
     */
    @Override
    public String toString(){
        if (isfile()){
            return name;
        }
        else{
            return name+"/";
        }

    }
    /*
    An method for geting the full path of current filesystemnode
    return full path to file or folder
     */
     public String getFullPath(){
        if(parent ==null){
            return toString();
        }
        return parent.getFullPath()+ toString();
     }

}