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
    static Foldernode miyajima = new Foldernode("miyajima.jpg", 1);
 
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

        // inititalled current position 
        Foldernode current_postion = name ;

        while(true){  
            // prirnting the root 
            System.out.print(home );
            System.out.print(name);
            System.out.println();


            Scanner user_input = new Scanner(System.in);
            String choice = user_input.nextLine( );  
            String[] part = choice.split(" ");
            
            //String choice = user_input.nextLine();
            // if user choosed cd
            if (part[0].equals("cd") ){
                String searchname = part[1];
                if (searchname == ".."){

                }
                Foldernode found = current_postion.search(searchname);
                // change the current postion
                current_postion = found;
                

                // if found is nell
                System.out.println("choosed cd");
            // if user choosedd fd
            }else if (part[0].equals("fd")){
                System.out.println("choosed fd  ");
            // if user choose ls 

            }else if (part[0].equals("ls")){
                current_postion.totalsize();
                //int total_size = 0;
                // findd all subnode under current postion 
                for (Foldernode child : current_postion.getSubnode()){
                    // print out the subnode 
                    int file_size = child.getsize();
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
            int total_size = current_postion.getsize();
            System.out.println("Total size:(" + total_size + "KB)");
            }else if (part[0].equals("rm")){


            }
            else{
            System.out.println("invlaid input");
            }

        }
    }
}
