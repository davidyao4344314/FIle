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
    /*
File and folder names must contain at least one (1) character, up to a maximum of twelve
(12)
File names must have a dot ( . ) followed by a three (3) character file extension
File and folder names must only contain lower-case alphanumeric characters (no
punctuation or spaces other than the dot for the extension)
parameter String : filename
parameter bolean : isFile 
Return String : null
if failed
return Str : error message
*/
    public static String checkName(String fileName,boolean isFile){
        // if file name is null
        if(fileName==null)
            return "File and folder names must contain at least one!";
        int len=fileName.length();
        // if length is shorter than 1
        if(len<1)
            return  "File and folder names must contain at least one!";
        // if length is longer than 12 
        if(len>12)
            return "File and folder names size must be less than 12!";
        int dotCnt=0;
        String extension=null;
        int extCharn=0;
        // for loop each character in the string
        for (int i = 0; i < len; i++) {
            char ch = fileName.charAt(i);

            // if character is equal to dot
            if (ch=='.') {
                extCharn=0;
                // if it is an file
                if(isFile) {
                    // calculate how much dot in name
                    dotCnt=dotCnt + 1;
                    // allow only one dot
                    if (dotCnt>1)
                        return "File names must  contain only one dot!";
                }
                // error message
                else
                    return "Folder names must NOT contain dot!";

                continue;
            }
            // if they are more than one dot extrad the extenstion
            if(dotCnt >0 ) {
                extCharn=extCharn+1;
            }
            // if the new number character from 0 to 9
            if(ch>='0' && ch<='9') {
                continue;
            }
            // if the a to to can countine
            if(ch>='a' && ch<='z') {
                continue;
            }
            // other 
            //(punctuation or spaces other than the dot for the extension)
            return "File and folder names must only contain lower-case alphanumeric characters ";
        }
        // if it file the extenstion charcter legnth is the same as 3
        if (extCharn !=3 && isFile){
            return "File names must have a dot ( . ) followed by a three (3) character file extension!!";
        }

        return null;

    }
    }