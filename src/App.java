public class App {
    // creat an tree and put node in
    static Foldernode home = new Foldernode("home",0);
    static Foldernode name = new Foldernode("yourname", 0);
    static Foldernode document = new Foldernode("document", 0);
    static Foldernode download = new Foldernode("download",0 );
    static Foldernode music = new Foldernode("music",0);
    static Foldernode photos = new Foldernode("photo",0);

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        // adding subnode/file to the folders
        home.addsubnode(name);
        name.addsubnode(document);
        // prirnting the root 
        System.out.println(home);
        System.out.println(name);


    }
}
