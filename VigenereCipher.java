import java.io.PrintWriter;

public class VigenereCipher {
    
    public static void main(String[] args) throws Exception{
        PrintWriter pen = new PrintWriter(System.out, true);
        if (args.length != 2){
          pen.println("Incorrect number of parameters. Usage: [command] [message]");
          System.exit(1);
        }
    
        if(args[0].compareTo("encode") == 0){
          //call encode with arg[1] param
          //
          pen.println("success");
          pen.flush();
          
        } else if(args[0].compareTo("decode") == 0){
          //call decode method
    
        } else {
          pen.println("Invaild command. Vaild options are encode and decode");
          System.exit(1);
        }
        System.exit(0);
      }
}
