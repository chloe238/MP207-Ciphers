import java.io.PrintWriter;

/**
 * A basic Vigenere Cipher program that takes input from the command line
 *
 * @author Chloe Kelly
 */

public class CaesarCipher {
  /*
   * Preconditions:
   * str = String
   * Returns true if string is all lowercase letters, false otherwise
   */
  public static boolean isLower(String str) {
    char[] starr = str.toCharArray(); // convert to char array
    for (int i = 0; i < starr.length; i++) {
      if (Character.isUpperCase(starr[i])) {
        return false;
      }// if
    } // for loop
    return true;
  }// isLower(String)

  /*
   * Preconditions:
   * code = integer between 0-25
   * key = int between 0-25
   * isEncode = boolean
   * Returns integer between 0-25, corresponding to modulo of
   * code + key if encode is true, and code - key otherwise.
   */
  public static int getCharCode(int code, int key, boolean isEncode) {
    int nomod; // initalize variable for modulo
    if (isEncode) {
      nomod = (code + key); // add key for encoding
    } else {
      nomod = (code - key); // subtract key for decoding
      if (nomod < 0) {
        nomod += 26;
      } // if result is negative, add 26 to get correct mod value
    }
    int ch = nomod % 26; // mod to get correct character code
    return ch;
  }// getCharCode(int, int, boolean)

  /*
   * Preconditions:
   * str = string
   * willEncode = boolean
   * Prints str encoded for all 26 caeser cipher keys if willEncode
   * is true, and decodes if willEncode is false.
   */
  public static void printCipher(String str, boolean willEncode) {
    PrintWriter text = new PrintWriter(System.out, true); // initialize output instance
    int base = (int) 'a'; // get base for ASCII
    char[] starr = str.toCharArray(); // convert to char array
    for (int n = 0; n < 26; n++) {
      for (int i = 0; i < starr.length; i++) {
        int chcode = ((int) starr[i]) - base; //get a character code between 0-25
        starr[i] = (char) (getCharCode(chcode, n, willEncode) + base); //create cipher character and add to array
      } // for loop

      String res = new String(starr);
      starr = str.toCharArray();
      text.println("n = " + n + ": " + res);
    } // for loop
  }// printCipher(String, String, boolean)

  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true); // new output instance
    if (args.length != 2) {
      pen.println("Incorrect number of parameters. Usage: [command] [message]");
      System.exit(1);
    } // check for correct number of command line args

    if (isLower(args[1]) == false) {
      pen.println("Invaild message. Message must be all lowercase letters");
      System.exit(1);
    } // check for vaild message for encode/decode

    if (args[0].compareTo("encode") == 0) {
      printCipher(args[1], true);
    } // runs encode command
    else if (args[0].compareTo("decode") == 0) {
      printCipher(args[1], false);
    } // runs decode command
    else {
      pen.println("Invaild command. Vaild options are encode and decode");
      System.exit(1);
    } // end program if command is not valid

    System.exit(0);
  }// main(String)
}// CaesarCipher