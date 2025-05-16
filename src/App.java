import java.util.Scanner;

public class App {
    // creat an tree and put node in
    //static Foldernode home = new Foldernode("home",0);
    static Foldernode root = new Foldernode("home");



    public static int convertToInt(String str) {
        try {
            // try to past intger
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            // if an exception occur return -1
            return -1;
        }
    }

    public static void main(String[] args) throws Exception {
        // creat the folders
        Foldernode download = root.mkdir("download");
        Foldernode documents = root.mkdir("documents");
        documents.touch("cv.pdf",3);
        documents.touch("data.dat",3);

        Foldernode photos = root.mkdir("photos");
        photos.touch("passport.jpg",10);
        photos.touch("photoid.png",12);
        Foldernode japan2026 = photos.mkdir("japan2026");
        japan2026.touch("tokyo.png",300);
        japan2026.touch("kyoto.png",330);
        japan2026.touch("miyajima.gif",300);


        Foldernode current_postion = root ;

        // creat the music file
        Foldernode music = root.mkdir("music");
        // loop for ten times 
        for (int i = 0; i <= 10; i++) {
            // creat is with i mp3 and i increase every loop
            music.touch(""+i+".mp3",i);

        }
        // set running to true
        Boolean running = true;
        // while running is true it will keep looping
        while(running){
            // prirnting the root
            current_postion.printallroot();


            Scanner user_input = new Scanner(System.in);
            String choice = user_input.nextLine( );
            String[] part = choice.split(" ");


            //String choice = user_input.nextLine();
            // if user choosed cd
            part[0]=part[0].toLowerCase();
            if (part[0].equals("cd") ){
                int partlenth = part.length;
                if (partlenth <= 1){
                    System.out.println("Cd requires pass parameter");
                    continue;
                }
                String searchname = part[1];
                
                // check if it the cd .. command
                if (searchname.equals( "..")){
                    Foldernode past = current_postion.checkparent();
                    // if no parent for node
                    if (past == null){
                        System.out.println("You can't return the root of root");
                    }
                    // if they are a parent for node
                    else{
                        current_postion = past;
                    }
                }
                // the cd command
                else{
                    // use the search method
                    Foldernode found = current_postion.search(searchname);
                    // if something is found
                    if (found != null){
                        current_postion = found;
                    }
                    // if it didnt found folder
                    else{
                        System.out.println("didn't find folder ");
                    }
                }
                // if found is nell
                System.out.println("choosed cd");
            }else if (part[0].equals("ls")){
                current_postion.ls();

                // if user input rm
            }else if (part[0].equals("rm")){
                if (part.length<= 1){
                    System.out.println("input can't be null");
                    continue;
                }
                // delet file from part 1 of the input
                String deletfile = part[1];
                current_postion.removefile(deletfile);
            
            }else if (part[0].equals("rmdir")){
                if (part.length<= 1){
                    System.out.println("input can't be null");
                    continue;
                }
            String deletfile = part[1];
            current_postion.removefolder(deletfile);
            }
            else if (part[0].equals("mkdir")){
                // check if input is null or not
                if (part.length<= 1){
                    System.out.println("File and folder names must contain at least one (1) character");
                    continue;                    
                }
                String file = part[1];
                current_postion.mkdir(file);
            }
            // if user choose touch
            else if (part[0].equals("touch")){
                int size=1;
                // check input is null ornot 
                if (part.length<= 1){
                    System.out.println("File and folder names must contain at least one (1) character");
                    continue;
                }
                if (part.length>2)
                    size=convertToInt(part[2]);
                
                if(size>=1)

                    current_postion.touch(part[1],size);
                else{
                    System.out.println("invalited parmeter for touch !!!!");
                }

            }
            else{
                System.out.println("invlaid input");
            }

        }
        }
    }

    