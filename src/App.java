import java.util.Scanner;

public class App {
    // creat an tree and put node in
    static Foldernode home = new Foldernode("home",0);
    static Foldernode name = new Foldernode("yourname", 0);
    static Foldernode document = new Foldernode("document", 0);
    static Foldernode download = new Foldernode("download",0 );
    static Foldernode music = new Foldernode("music",0);

    static Foldernode photos = new Foldernode("photo","passport.jpg",1);
    static Foldernode photoid = new Foldernode("photoid.png", 0);


    static Foldernode japan2026 = new Foldernode("japan2026","tokoy.jpg", 1);
    static Foldernode kyoto = new Foldernode("kyoto.jpg", 1);
    static Foldernode miyajima = new Foldernode("miyajima.jpg", 0);
 
    static Foldernode bad_code = new Foldernode("Bad code/", 0);
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        // adding subnode/file to the folders
        home.addsubnode(name);
        name.addsubnode(document);
        name.addsubnode(download);
        name.addsubnode(music);
        name.addsubnode(photos);
        photos.addsubnode(japan2026);
        japan2026.addsubnode(kyoto);
        japan2026.addsubnode(miyajima);
        download.addsubnode(bad_code);

        while(true){
            Scanner user_input = new Scanner(System.in);
            //String curent_position = "name";
            Foldernode current_posion = name ;
            // prirnting the root 
            System.out.print(home + "/");
            System.out.print(name+ "/");
            System.out.println();
            
            String choice = user_input.nextLine();
            // if user choosed cd
            if (choice.equals("cd") ){
                System.out.println("choosed cd");
            // if user choosedd fd
            }else if (choice.equals("fd")){
                System.out.println("choosed fd");
            // if user choose ls 
            }else if (choice.equals("ls")){
                // findd all subnode under name
                for (Foldernode child : current_posion.getSubnode()){
                    // print out the subnode 
                    System.out.println(child +"/");
                }

            }
            else{
            System.out.println("invlaid input");

            }

        }
    }
}
