import java.io.PrintWriter;

public class VigenereCipher {
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
            }
        } // for loop
        return true;
    }

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
    }// getCharCode

    /*
     * Preconditions:
     * str = string
     * willEncode = boolean
     * keyword = string
     * Prints str encoded using keyword according to Vigenere Cipher if willEncode is true,
     * or decode if false.
     */
    public static void printCipher(String str, String keyword, boolean willEncode) {
        PrintWriter text = new PrintWriter(System.out, true); // initialize output instance
        int base = (int) 'a'; // get base for ASCII
        int keycode = 0;
        char[] starr = str.toCharArray(); // convert to char array
        char[] keyarr = keyword.toCharArray(); // convert keyword to array
        for (int i = 0; i < starr.length; i++) {
            int chcode = ((int) starr[i]) - base; // get a character code between 0-25
            if (Character.isLetter(keyarr[i % keyarr.length])) {
                keycode = ((int) keyarr[i % keyarr.length]) - base;
            } else {
                keycode = 0;
            }
            starr[i] = (char) (getCharCode(chcode, keycode, willEncode) + base);
            // create cipher character and add to array
        } // for loop

        String res = new String(starr);
        text.println(res);
    }// printCipher

    public static void main(String[] args) throws Exception {
        PrintWriter pen = new PrintWriter(System.out, true); // new output instance
        if (args.length != 3) {
            pen.println("Incorrect number of parameters. Usage: [command] [message] [keyword]");
            System.exit(1);
        } // check for correct number of command line args

        if (isLower(args[1]) == false || isLower(args[2]) == false) {
            pen.println("Invaild message. Message and keyword must be all lowercase letters");
            System.exit(1);
        } // check for vaild message and key

        if (args[0].compareTo("encode") == 0) {
            printCipher(args[1], args[2], true);
        } // runs encode command
        else if (args[0].compareTo("decode") == 0) {
            printCipher(args[1], args[2], false);
        } // runs decode command
        else {
            pen.println("Invaild command. Vaild options are encode and decode");
            System.exit(1);
        } // end program if command is not valid

        System.exit(0);
    }// main
}// VigenereCipher
